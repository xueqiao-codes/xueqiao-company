package com.longsheng.trader.utils.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.soldier.base.logger.AppLog;

import java.io.UnsupportedEncodingException;

public class MD5 {

	public static String crypto(String input) {

		try {
			return DigestUtils.md5Hex(input.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			AppLog.d(e);
			e.printStackTrace();
		}
		return "";
	}
}
