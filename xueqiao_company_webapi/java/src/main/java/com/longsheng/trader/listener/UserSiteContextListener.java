package com.longsheng.trader.listener;

import javax.servlet.ServletContextEvent;

import org.soldier.platform.web_framework.WebContextListener;


public class UserSiteContextListener  extends WebContextListener {
	@Override
	public String getLoggerName() {
		return "xueqiao_company_webapi";
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		super.contextInitialized(context);
	}

	@Override
	public void contextDestroyed(ServletContextEvent context) {
		super.contextDestroyed(context);
	}
}