package com.longsheng.xueqiao.duck.inf;

import java.nio.ByteBuffer;

/**
 *  
 * @author WangLi
 *
 */
public interface ISessionProvider {
	public ByteBuffer getSession(String sessionKey);
	
	public boolean updateSession(String sessionKey,
			ByteBuffer sessionValue, int expireSeconds);
	
	public boolean deleteSession(String sessionKey);
	
	public void destroy();
}