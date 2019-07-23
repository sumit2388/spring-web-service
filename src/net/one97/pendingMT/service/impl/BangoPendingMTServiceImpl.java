package net.one97.pendingMT.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.apache.geronimo.mail.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.one97.pendingMT.bean.BangoRequestBean;
import net.one97.pendingMT.bean.BangoResponseBean;
import net.one97.pendingMT.bean.BangoUserHistory;
import net.one97.pendingMT.bean.Constants;
import net.one97.pendingMT.bean.DataBean;
import net.one97.pendingMT.bean.ExtensionData;
import net.one97.pendingMT.bean.HttpResponse;
import net.one97.pendingMT.bean.PropertyLoader;
import net.one97.pendingMT.bean.RequestBean;
import net.one97.pendingMT.dao.BangoPendingMTDao;
import net.one97.pendingMT.service.BangoPendingMTService;

@Service
public class BangoPendingMTServiceImpl implements BangoPendingMTService {

	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(BangoPendingMTServiceImpl.class);
	private Random random = new Random();
	private static Client client = null;

	@Autowired
	private BangoPendingMTDao bangoPendingMTDao;

	// null = not subscribe
	// 2
	// other than 2

	@Override
	public String processPendingMTRequest(RequestBean requestBean) {
		
		String msisdn = requestBean.getMsisdn();
		logger.info(msisdn+" inside processPendingMTRequest " );
		
		DataBean msisdnDetails = bangoPendingMTDao.getMsisdnDetails(requestBean);
		
		if (msisdnDetails != null && (msisdnDetails.getParam2() != null || msisdnDetails.getParam3() != null
				|| msisdnDetails.getStatus() != null)) {			
			logger.info(msisdn+" msisdnDetails "+msisdnDetails);
			Map<String, String> param3Map = getParamsMap(msisdnDetails.getParam3());
			String customerId = (param3Map != null && param3Map.containsKey("custid")) ? param3Map.get("custid") : "";
			String entid = (param3Map != null && param3Map.containsKey("entid")) ? param3Map.get("entid") : "";
			String usertype = (param3Map != null && param3Map.containsKey("usertype")) ? param3Map.get("usertype") : "";
			String rec = (param3Map != null && param3Map.containsKey("rec")) ? (param3Map.get("rec").equalsIgnoreCase("true")?"1":"0") : "0";
			
			if (!msisdnDetails.getStatus().equals("2")) {				
				logger.info(msisdn+" is not in pending state");
 				BangoUserHistory log = new BangoUserHistory(msisdn, customerId, entid, usertype, Integer.parseInt(rec), null, null, "sub", null, "pending", Double.parseDouble(msisdnDetails.getLastBilledAmount()),"Not in pending state");
				bangoPendingMTDao.insertLog(log);
				return "1|102|Msisdn in not in valid status.";
				
			} else {
				
				logger.info(msisdn+" is in pending state");
				
				DataBean data = bangoPendingMTDao.getParams(requestBean.getMsisdn());
				logger.info(msisdn+" params "+data);
				
				Map<String, String> gatewayParams = getParamsMap2(data.getGwParams());
				Map<String, String> chargingCodeParams = getParamsMap(data.getChargingCode());

				String authorization = createAuthorizationString(gatewayParams.get("username"),gatewayParams.get("password"));
				logger.info(msisdn+" authorization "+authorization);
				
				Gson responseJSON = new Gson();
				BangoRequestBean request = createObject(chargingCodeParams, msisdnDetails,msisdn);
				logger.info(msisdn+" BangoRequestBean "+request);
				
				Gson gson = new Gson();
				String jsonString = gson.toJson(request);
				logger.info(msisdn+" jsonString "+jsonString);
				
				HttpResponse resp = hitPostHttpsRequest(msisdn, data.getUrl(), jsonString, authorization);
				logger.info(msisdn+" resp "+resp);
				
				BangoResponseBean responseBean = null;
				responseBean = responseJSON.fromJson(resp.getResponseBody(), BangoResponseBean.class);
				logger.info(msisdn+" responseBean "+responseBean);
				
				if (responseBean != null && (Constants.OK.equalsIgnoreCase(responseBean.getResponseCode())
						|| Constants.CLIENT_ACTION_REQUIRED.equalsIgnoreCase(responseBean.getResponseCode()))) {
					logger.info(msisdn+"");
					
					String enS = responseBean.getEntitlementId();
					logger.info(msisdn+" NEW entid "+enS);
					
					String url = responseBean.getParameters().getUrl();
					logger.info(msisdn+" NEW url "+url);
					
					logger.info(msisdn+" old param3 "+msisdnDetails.getParam3());
			
				//	int index = msisdnDetails.getParam3().indexOf(',');
				//	String sub = "entid=" + enS + "" + msisdnDetails.getParam3().substring(index);
					
					String uType=requestBean.getUserType()==null?"FREE":requestBean.getUserType();
					String recurring=param3Map.get("rec")==null?"false":param3Map.get("rec");
					String sub = "entid="+enS+",custid="+param3Map.get("custid")+",usertype="+uType+",planid="+request.getProductKey()+",rec="+recurring;
					logger.info(msisdn+" new param3 "+sub);
					
					bangoPendingMTDao.updateParams(url, sub, requestBean.getMsisdn(), requestBean.getPackName());
					logger.info(msisdn+" url and entid updated successfully");
					BangoUserHistory log = new BangoUserHistory(msisdn, customerId, entid, usertype, Integer.parseInt(rec), request.getProductKey(), responseBean.getResponseCode(), responseBean.getResponseMessage(), "sub", "pending", Double.parseDouble(msisdnDetails.getLastBilledAmount()),"You have successfully subscribed");
					bangoPendingMTDao.insertLog(log);
					return "0|306|You have successfully subscribed in TESTB.You are in Pending State.|"+url+"|"+sub;
				} else {
					logger.info(msisdn+" failure from bango");
					BangoUserHistory log = new BangoUserHistory(msisdn, customerId, entid, usertype, Integer.parseInt(rec), request.getProductKey(), responseBean.getResponseCode(), responseBean.getResponseMessage(), "sub", "pending", Double.parseDouble(msisdnDetails.getLastBilledAmount()),"failure from bango");
					bangoPendingMTDao.insertLog(log);
					return "1|103|Unable to subscribe. Please try after some time.";

				}

			}

		} else {
			logger.info(msisdn+" msisdn details not found in DB");
			BangoUserHistory log = new BangoUserHistory(msisdn, null, null, null, null, null, null,null, "sub", "pending", Double.parseDouble(msisdnDetails.getLastBilledAmount()),"msisdn details not found in DB");
			bangoPendingMTDao.insertLog(log);
			return "1|104|you are not subscribed in the pack";
		}
		
	}

	public BangoRequestBean createObject(Map<String, String> chargingCodeParams, DataBean dataBean, String msisdn) {
		
		logger.info(msisdn+" log1");
		Map<String, String> map = getParamsMap(dataBean.getParam3());
		
		BangoRequestBean billingJSON = new BangoRequestBean();
		
		String customerIdentifier = (map != null && map.containsKey("custid")) ? map.get("custid") : ("" + System.currentTimeMillis() + (random.nextInt(700) + 200));
	
		billingJSON.setCustomerIdentifier(customerIdentifier);
		billingJSON.setMerchantAccountKey(chargingCodeParams.get("merchantAccountKey"));

		String productKey ="";
		
//		if(map != null && map.containsKey("usertype") && StringUtils.isNotBlank(map.get("usertype"))){
//			if(map.get("usertype").equalsIgnoreCase("FREE")){
//				productKey=chargingCodeParams.get("freeUserProductKey");
//			}
//			else if (map.get("usertype").equalsIgnoreCase("PAID")){
//				productKey= chargingCodeParams.get("paidUserProductKey");
//			}
//		}else{
//			productKey=chargingCodeParams.get("freeUserProductKey");
//		}
		
//		String productKey = (map != null && !map.containsKey("custid")) ? chargingCodeParams.get("freeUserProductKey") : chargingCodeParams.get("paidUserProductKey");
		
		//rec = true == paid    //rec = false = free   //default	rec not avalable = free
		if(map != null && map.containsKey("rec") && StringUtils.isNotBlank(map.get("rec"))){
			if(map.get("rec").equalsIgnoreCase("true")){
				productKey=chargingCodeParams.get("paidUserProductKey");  // plan id
			}
			else if (map.get("rec").equalsIgnoreCase("false")){
				productKey= chargingCodeParams.get("freeUserProductKey");
			}
		}else{
			productKey=chargingCodeParams.get("freeUserProductKey");
		}
		
	
		logger.info(msisdn+" productKey "+productKey);
	
		billingJSON.setProductKey(productKey);
		billingJSON.setNotificationUrl(PropertyLoader.getProperty("du.notification.url"));
		ExtensionData extensionData = new ExtensionData();
		extensionData.setChannelSource(dataBean.getChannel());
		extensionData.setCurrencyIso3("AED");
		extensionData.setPrice(dataBean.getLastBilledAmount());
		billingJSON.setExtensionData(extensionData);
		logger.info(msisdn+" log3");
		
		return billingJSON;
	}

	private HttpResponse hitPostHttpsRequest(String msisdn, String url, String jsonString, String authorization) {
		logger.debug(msisdn + " Inside hitPostHttpsRequest method");
		HttpResponse httpResponse = new HttpResponse();
		ClientResponse clientResponse = null;
		try {
			logger.info(msisdn + "  url " + url + " Header Authorization " + authorization);
			logger.info(msisdn + " json body " + jsonString);
			if (client == null) {
				client = Client.create();
				client.setReadTimeout(30000);
			}
			logger.info(msisdn+"");
			WebResource webResource = client.resource(url);
			clientResponse = webResource.header("Authorization", authorization).type(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class, jsonString);
			logger.info(msisdn+"");
			clientResponse.getStatus();
			String result = getStringFromInputStream(clientResponse.getEntityInputStream());
			logger.info(msisdn + " SatusCode " + clientResponse.getStatus() + " response " + result);
			httpResponse.setResponseBody(result);
			httpResponse.setStatusCode(clientResponse.getStatus());
		} catch (Exception e) {
			logger.error(msisdn + " Exception ", e);
		} finally {
			if (clientResponse != null) {
				clientResponse.close();
				clientResponse = null;
			}
		}
		return httpResponse;
	}

	private String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		final StringBuilder sb = new StringBuilder();
		logger.info(" inside getStringFromInputStream ");
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.info(" IOException " + e);
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	private String createAuthorizationString(String username, String password) {
		String credentials = username + ":" + password;
		String authHeaderValue = new String(Base64.encode(credentials.getBytes()));
		return "Basic " + authHeaderValue;
	}

	private Map<String, String> getParamsMap(String params) {
		Map<String, String> hm = new HashMap<String, String>();
		String[] parameters = params.trim().split(",");
		logger.info("Params :: " + params);
		for (int i = 0; i < parameters.length; i++) {
			String[] keyValue = parameters[i].split("=");
			if (keyValue != null && keyValue.length == 2) {
				hm.put(keyValue[0].trim(), keyValue[1].trim());
			}
		}
		return hm;
	}

	private Map<String, String> getParamsMap2(String params) {
		Map<String, String> hm = new HashMap<String, String>();
		String[] parameters = params.trim().split("&");
		logger.info("Params :: " + params);
		for (int i = 0; i < parameters.length; i++) {
			String[] keyValue = parameters[i].split("=");
			if (keyValue != null && keyValue.length == 2) {
				hm.put(keyValue[0].trim(), keyValue[1].trim());
			}
		}
		return hm;
	}
}