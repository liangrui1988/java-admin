package com.huiwan.gdata.modules.sys.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class ProRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {

		HttpServletRequest httpServletRequest = (HttpServletRequest) sre
				.getServletRequest();
		System.out.println("ProRequestListener>requestDestroyed>"
				+ httpServletRequest.getRequestURI());

	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) sre
				.getServletRequest();

		System.out.println("ProRequestListener>requestInitialized>"
				+ httpServletRequest.getRequestURI());

	}

}
