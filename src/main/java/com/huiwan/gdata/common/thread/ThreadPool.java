package com.huiwan.gdata.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

@Component
public class ThreadPool {
	
	public static final int CORES = Runtime.getRuntime().availableProcessors();
	private final Logger logger = LoggerFactory.getLogger(getClass());
	public  boolean jvmExit = false;
	
	private final static ExecutorService pools = Executors.newFixedThreadPool((CORES > 2 ? CORES-2 : CORES), 
			new ThreadFactoryBuilder().setNameFormat("pro1-worker-%d").build());
	
	public void run(Runnable task) {
		try {
			pools.submit(task);
		} catch (Exception e) {
			logger.warn("ThreadPool - run error ", e);
		}
	}
	/**
	 * 获取执行器。
	 * @return 执行器
	 */
	public ExecutorService getPools(){
		return pools;
	}
	@PostConstruct
	public void safeDealPool(){
		Thread poolSafeThread = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					logger.warn("jvm exit.thread pool start attempt shutdown.");
					jvmExit = true;
					try {
						Thread.sleep(2000);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					pools.shutdown();
					if (!pools.awaitTermination(60, TimeUnit.SECONDS)) {
						logger.warn("thread pool exit fail.start force exit.");
						pools.shutdownNow();
					} else {
						logger.warn("thread pool exit success.");
					}
				} catch (Exception ex) {
					logger.warn("thread pool exit fail because of ex:{}.",ex);
				}
			}
		});
		Runtime.getRuntime().addShutdownHook(poolSafeThread);
	}
	
}
