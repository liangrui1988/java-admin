package com.huiwan.gdata.modules.sys.init;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

public class LibMain {
	// File libPath = new File("G:/gdata-1.0.0.jar");
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IOException {
		File libPath = new File("G:/123");
		JarFile jf=new JarFile("G:/123");
		// gdata-1.0.0.jar
//		File[] jarFiles = libPath.listFiles();
		File[] jarFiles = libPath.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				System.out.println(name);
				boolean accept = name.endsWith(".jar");
				return accept;
			}
		});

		System.out.println(jarFiles.length);
		if (jarFiles != null) {
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			// 获取系统类加载器
			URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			for (File file : jarFiles) {
				URL url = file.toURI().toURL();
				try {
					method.invoke(classLoader, url);
					System.out.println("读取jar文件[name={}]" + file.getName());
				} catch (Exception e) {
					System.out.println("读取jar文件[name={}]失败" + file.getName());
				}
			}
		}
	}

	// 获取所有的.jar和.zip文件
	// File[] jarFiles = libPath.listFiles(new FilenameFilter() {
	// public boolean accept(File dir, String name) {
	// return name.endsWith(".jar") || name.endsWith(".zip");
	// }
	// });
}
