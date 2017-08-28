package com.huiwan.gdata;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminMain {
	private static Logger logger = LoggerFactory.getLogger(AdminMain.class);

	private static List<String> webappPaths = new ArrayList<>();

	public static void main(String[] args) {
		int port = 9899;
		try {
			if (args.length > 0)
				port = Integer.parseInt(args[0]);
		} catch (Exception ignored) {
			ignored.printStackTrace();
		}
		Server server = new Server(port);

		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setMaxThreads(500);
//		server.setThreadPool(new ExecutorThreadPool(Executors.newFixedThreadPool(200)));
		server.setThreadPool(threadPool);

		// 关联一个已经存在的上下文 src/main/webapp
		// WebAppContext webAppContext = new WebAppContext("webapp", "/main");
		WebAppContext webAppContext = new WebAppContext(getWebAppPath(), "/zdata");
		// 设置描述符位置
		webAppContext.setDescriptor("webapp/WEB-INF/web.xml");
		// 设置Web内容上下文路径
		// webAppContext.setResourceBase("src/main/webapp");
		webAppContext.setResourceBase(getWebAppPath());
		webAppContext.setDisplayName("main");
		// 定位项目中class文件的位置
		webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		webAppContext.setConfigurationDiscovered(true);
		webAppContext.setParentLoaderPriority(true);
		server.setHandler(webAppContext);
		logger.info("ContextPath={}", webAppContext.getContextPath());
		logger.info("Descriptor={}", webAppContext.getDescriptor());
		logger.info("ResourceBase={}", webAppContext.getResourceBase());
		logger.info("BaseResource={}", webAppContext.getBaseResource().toString());
		try {
			// ServerContainer wscontainer =
			// WebSocketServerContainerInitializer.configureContext(webAppContext);
			server.start();
			server.join();// 线程阻塞
		} catch (Exception e) {
			logger.error("start Exception:{}", e.getMessage());
			e.printStackTrace();
		}
		logger.info("server is  start, port is " + port + "............");
	}

	public static String getWebAppPath() {
		webappPaths.add("lib/webapp");
		webappPaths.add("src/main/webapp");
		for (String webappPath : webappPaths) {
			if (new File(webappPath, "WEB-INF/web.xml").exists()) {
				logger.warn("find x {}", webappPath);
				return webappPath;

			}
		}
		throw new IllegalStateException("not find any webappPath");
	}
}
