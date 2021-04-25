package com.longsheng.trader.utils;

import com.longsheng.xueqiao.hawk.thriftapi.UserSession;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenHanlder {

	private final static String PRE_PLACE_HOLDER = "USER";

	private final static String END_PLACE_HOLDER = "SOURCE";

	private final static String SEPARATOR = "|";

	//"^(.+?)\\|USER\\|([0-9]+)\\|SOURCE$"
	private static  String tokenPatten =  "(.+)\\"+SEPARATOR+PRE_PLACE_HOLDER+"\\"+SEPARATOR+"([0-9]+)\\"+SEPARATOR+"([0-9]+)";

	private static Pattern p = Pattern.compile(tokenPatten);

	private int userId;
	private String secretKey;
	private boolean isTokenValidate;
	private int companyId;

	public TokenHanlder(String token) {

		Matcher m = p.matcher(token);
		
		if (m.matches()) {
			secretKey = m.group(1);
			userId = Integer.parseInt(m.group(2));
			companyId = Integer.parseInt(m.group(3));
			isTokenValidate= true;
			AppLog.d("Token match, secretKey: "+secretKey + ", userId: "+userId +", companyId: "+companyId);
		}
		else
		{
			isTokenValidate= false;
		}		
	}

	public static String generateToken(String secretKey, int userId, int companyId) {
		
		StringBuilder builder = new StringBuilder();
		builder.append(secretKey);
		builder.append(SEPARATOR);
		builder.append(PRE_PLACE_HOLDER);
		builder.append(SEPARATOR);
		builder.append(userId);
		builder.append(SEPARATOR);
		builder.append(companyId);

		return builder.toString();
	}

	public int getUserId() {
		return userId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public boolean isTokenValidate() {
		return isTokenValidate;
	}

//	public static UserSession setToken(UserSession userSession) throws ErrorInfo, TException {
//		UUID uuid = UUID.randomUUID();
//		String token = generateToken(uuid.toString(), userSession.getUserId(), userSession.getCompanyId());
//		userSession.setToken(token);
//		SessionOperator sessionOperator = new SessionOperator(userSession, 0);
//		sessionOperator.setSession();
//		return userSession;
//	}

	public int getCompanyId() {
		return companyId;
	}
}
