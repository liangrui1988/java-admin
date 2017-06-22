package com.huiwan.gdata.common.utils.dir;

import java.util.Arrays;
import java.util.Collection;

/**
 * 灵巧打印机 以使输出更容易浏览
 * 
 * @author lenovo
 * 
 */
public class PPrint
{
	public static String pformat(Collection<?> c)
	{
		if (c.size() == 0)
			return "[]";
		StringBuilder result = new StringBuilder("[");
		for (Object elem : c)
		{
			if (c.size() != 1)
				result.append("\n");
			result.append(elem);
		}
		if (c.size() != 1)
			result.append("\n");
		result.append("]");
		return result.toString();

	}

	public static void pprint(Collection<?> c)
	{
		System.out.println(pformat(c));
	}

	public static void pprint(Object[] c)
	{
		System.out.println(pformat(Arrays.asList(c)));
	}

}
