package com.huiwan.gdata.common.utils.dir;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 扫描菜单和权限    包括子包  有正则表达式 
 * @author liangrui
 *
 */
public class ScanningMP
{

	public static void main(String[] args)
	{
		String[] argss = new String[1];

		argss[0] = ".";

		// argss[0] = ".*.rec.*.java";// "D.*\\.java";

		String path = "C:\\Users\\lenovo\\Pictures\\screen";

		if (argss.length == 0)
		{
			return;
		} else
		{
			for (String arg : argss)
			{
				TreeInfo info = TreeInfo.walk(path, ".*.a.*.jpg");

				System.out.println(info.files.size());
				System.out.println(info.dirs.size());
				for (File f : info.files)
				{
					System.out.println(f.getName());
				}
				// System.out.println(TreeInfo.walk(path,".*.a.*.jpg"));
			}
		}

	}

	/**
	 * 给定目录下的由整个目录树中所有文件构成的list<File> 这些文件是基于你提供正则表达式而补选中的
	 * @author liangrui
	 *
	 */
	public static class TreeInfo // implements Iterable<File>
	{
		public List<File> files = new ArrayList<File>();// 文件
		public List<File> dirs = new ArrayList<File>();// 目录

		/*
		 * public Iterator<File> iterator() { return files.iterator(); }
		 */

		void addAll(TreeInfo other)
		{
			files.addAll(other.files);
			dirs.addAll(other.dirs);
		}

		public String toString()
		{
			return "dirs: " + PPrint.pformat(dirs) + "\n\nfiles:"
					+ PPrint.pformat(files);
		}

		/**
		 *  开始递归
		 * @param start
		 * @param regex
		 * @return
		 */
		public static TreeInfo walk(String start, String regex)
		{
			return recurseDirs(new File(start), regex);
		}

		/**
		 * 递归
		 * @param f  文件目录
		 * @param reg 正则表达式
		 * @return
		 */
		static TreeInfo recurseDirs(File f, String reg)
		{
			TreeInfo result = new TreeInfo();
			for (File item : f.listFiles())
			{
				if (item.isDirectory())
				{
					result.dirs.add(item);// 添加目录集合
					result.addAll(recurseDirs(item, reg));// 递归回方法 循环子目录
				} else
				{
					if (item.getName().matches(reg))
					{
						result.files.add(item);// 添加文件
					}
				}
			}
			return result;
		}

	}
}
