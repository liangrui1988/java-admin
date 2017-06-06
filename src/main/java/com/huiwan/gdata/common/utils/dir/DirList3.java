package com.huiwan.gdata.common.utils.dir;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 目录列表器 测试 扫描目录下的文件
 * 
 * @author lenovo
 * 
 */

public class DirList3 {
	public static void main(String[] args) {
		final String[] argss = new String[1];
		argss[0] = ".*.rec.*.java";// "D.*\\.java";

		String s = DirList3.class.getPackage().getName().replace(".", "/");
		String dirPath = new File("").getAbsolutePath() + "\\src\\" + s;

		File path = new File(dirPath);
		String[] list = null;
		if (argss.length == 0) {
			list = path.list();
		} else {
			System.out.println("all file list==>"
					+ Arrays.toString(path.list()));
			list = path.list(new FilenameFilter() {// 文件过滤器
						private Pattern pattern = Pattern.compile(argss[0]);

						public boolean accept(File dir, String name) {
							return pattern.matcher(name).matches();
						}
					});
			// 显示文件夹下面的文件并用正则匹配
			// list = path.list(new DirFilter(argss[0]));

		}
		// 排序打印
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String dirItem : list) {
			System.out.println(dirItem);
		}
	}

}