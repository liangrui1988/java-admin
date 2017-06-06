package com.huiwan.gdata.common.utils.dir;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 目录实用工具
 * 
 * @author ruiliang
 * 
 */
public class Directory
{
	/**
	 * 本地目录中的文件构成的File对象数组
	 * @param dir
	 * @param regex
	 * @return
	 */
	public static File[] local(File dir, final String regex)
	{
		return dir.listFiles(new FilenameFilter()
		{
			private Pattern pa = Pattern.compile(regex);

			public boolean accept(File dir, String name)
			{
				return pa.matcher(name).matches();
			}
		});
	}

	// overloaded重载
	public static File[] local(String path, final String regex)
	{
		return local(new File(path), regex);
	}

	/**
	 * 给定目录下的由整个目录树中所有文件构成的list<File> 这些文件是基于你提供正则表达式而补选中的
	 * @author liangrui
	 *
	 */
	public static class TreeInfo implements Iterable<File>
	{
		public List<File> files = new ArrayList<File>();// 文件
		public List<File> dirs = new ArrayList<File>();// 目录

		public Iterator<File> iterator()
		{
			return files.iterator();
		}

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

		public static TreeInfo walk(String start, String regex)// 开始递归
		{
			return recurseDirs(new File(start), regex);
		}

		public static TreeInfo walk(File f, String regex)
		{
			return recurseDirs(f, regex);
		}

		public static TreeInfo walk(File f)// 所有的
		{
			return recurseDirs(f, ".*");
		}

		public static TreeInfo walk(String start)// 所有的
		{
			return recurseDirs(new File(start), ".*");
		}

		// 递归
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

		// ////////////////////////mian
		public static void main(String[] args) throws IOException
		{
			String[] argss = new String[1];
			
			argss[0] = ".";
			File f=new File("C:\\Users\\lenovo\\Pictures\\screen");
			for (File item : f.listFiles())
			{
				System.out.println(f.isDirectory());
				System.out.println(item.getName());
			}

			if (argss.length == 0)
			{
				System.out.println(walk("."));
			} else
			{
				for (String arg : argss)
				{
					System.out.println(walk(arg));
				}
			}
		}

	}

}
