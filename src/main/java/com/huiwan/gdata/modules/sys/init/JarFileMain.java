package com.huiwan.gdata.modules.sys.init;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huiwan.gdata.common.utils.dir.Directory.TreeInfo;

/**
 * linux 系统上扫描项目 目录下的jar
 * 
 * @author rui
 * @date 2018/01/20
 *
 */
public class JarFileMain {

	protected static Logger log = LoggerFactory.getLogger(JarFileMain.class);

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IOException {
		get("G:/123");
	}

	/**
	 * 根据指定path,扫描
	 * 
	 * @param path
	 * @return
	 */
	public static List<String> get(String path) {
		List<String> clzList = new ArrayList<String>();
		// 开始扫描
		TreeInfo info = TreeInfo.walk(path, "gdata-.*.jar");// .*.a.*.jpg
		log.info("=======linux files.size:========>" + info.files.size());
		log.info("=======linux  info.dirs.size():========>" + info.dirs.size());
		for (File f : info.files) {
			List<String> list = getClz(f.getPath());
			for (String clzName : list) {
				log.info("=======linux  扫描到cotroller:========>" + clzName);
				clzList.add(clzName);
			}
		}
		return clzList;
	}

	/**
	 * 根据path扫描Controller
	 * 
	 * @param filePaht
	 * @return
	 */
	public static List<String> getClz(String filePaht) {
		List<String> clazzs = new ArrayList<String>();
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(filePaht);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<JarEntry> jarEntryList = new ArrayList<JarEntry>();
		Enumeration<JarEntry> ee = jarFile.entries();
		while (ee.hasMoreElements()) {
			JarEntry entry = (JarEntry) ee.nextElement();
			// 过滤我们出满足我们需求的东西
			if (entry.getName().matches(".*.Controller.*.class")) {
				jarEntryList.add(entry);
			}
		}
		for (JarEntry entry : jarEntryList) {
			String path = entry.getName();
			int start = path.indexOf("com");
			String clzName = path.substring(start, path.length() - 6);
			clzName = clzName.replace("\\", ".");
			clzName = clzName.replace("/", ".");
			// String className = entry.getName().replace('/', '.');
			// className = className.substring(0, className.length() - 6);
			// Class clz =
			// Thread.currentThread().getContextClassLoader().loadClass(className);
			clazzs.add(clzName);
		}
		return clazzs;
	}

}
