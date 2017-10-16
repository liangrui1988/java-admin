package com.huiwan.gdata;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebAppContext;

public class SimpleMain {

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		SelectChannelConnector connector = new SelectChannelConnector();
		// 设置线程
		server.setThreadPool(new ExecutorThreadPool());
		// 端口
		connector.setPort(9899);
		// 设置handler
		// 项目目录名/src/main/webapp
		// lib/webapp
		// demo
		// Handler handler=new WebAppContext("src/main/webapp", "/");

		server.setHandler(new WebAppContext("src/main/webapp", "/zdata"));
		// 连接器
		server.addConnector(connector);
		server.start();
	}
}
