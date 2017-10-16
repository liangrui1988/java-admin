package com.huiwan.gdata.common.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * 导入xls表格 把数据转换为数据流
 * 
 * 
 * @author liangrui
 * 
 *         encodFileNmae = java.net.URLEncoder.encode(tableName+".xls",
 *         "utf-8");
 *         response.setContentType("application/vnd.ms-excel;charset=utf-8");
 *         response.setHeader("Content-Disposition", "attachment;filename=" +
 *         encodFileNmae);
 *
 */
public class XlsUtil
{

	/**
	 * 把数据转换为输出流
	 * 
	 * @param sheetName
	 *            sheet名称
	 * @param header
	 *            表头map
	 * @param list
	 *            数据集合
	 * @param clz
	 *            对象字节码
	 * @param dateFormat
	 *            如果有日期 格式化的日期 默认 yyyy-MM-dd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static InputStream getXlsIO(String sheetName,
			Map<String, String> header, List<Object> list, Class clz,
			String dateFormat) throws Exception
	{

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);

		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		// 设置这些样式
		// style.setFillForegroundColor(HSSFColor.CORAL.index);//back
		// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 生成一个字体
		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		HSSFCell cell;

		List<String> tempSort = new ArrayList<String>();
		int cellIndex = 0;
		for (Entry<String, String> es : header.entrySet())
		{
			tempSort.add(es.getValue());
			cell = row.createCell(cellIndex);// 创建一个单元格,并放入数据
			cell.setCellStyle(style);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 指定编码
			cell.setCellValue(es.getKey()); // 设值
			cellIndex++;
		}

		if (list != null)
		{
			for (int i = 0; i < list.size(); ++i)
			{
				Object obj = clz.newInstance();
				obj = list.get(i);
				row = sheet.createRow(i + 1);// 创建一行,从0开始

				// 根据 v 找到方法 并获取值
				for (int r = 0; r < tempSort.size(); r++)
				{
					// 获取值
					Object value = getMethodValue(clz, obj, tempSort.get(r));
					String strValue = converType(value, dateFormat);
					cell = row.createCell(r);
					cell.setCellValue(strValue);
				}
			}
		}

		// FileOutputStream fos = new FileOutputStream("D:/tewst.xls");
		ByteArrayOutputStream os = new ByteArrayOutputStream();// 字节数组输出流

		try
		{
			wb.write(os);// 写入字节输出流对象是
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (os != null)
			{
				os.close();
			}

		}

		byte[] bArry = os.toByteArray();// 获取字节数组

		// 将数组字节 写入到输入流对象
		InputStream isba = new ByteArrayInputStream(bArry);

		return isba;
	}

	/**
	 * 根据字段获取值
	 * 
	 * @param clz
	 * @param o
	 * @param field
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getMethodValue(Class clz, Object o, String field)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException
	{

		Character charss = field.charAt(0);
		field = field.replaceFirst(charss.toString(),
				Character.toUpperCase(charss) + "");
		System.out.println(field);

		// 获取值
		@SuppressWarnings("unchecked")
		Method getMethod = clz.getDeclaredMethod("get" + field, new Class[] {});

		Object value = getMethod.invoke(o, new Object[] {});

		return value;
	}

	public static String converType(Object value, String dateFormat)
	{

		String rResult = "";

		// if (value instanceof Integer)
		// {
		// int result = (Integer) value;
		// return result;
		//
		// } else if (value instanceof Float)
		// {
		// float result = (Float) value;
		// return result;
		//
		// } else if (value instanceof Double)
		// {
		// double result = (Double) value;
		// return result;
		//
		// } else if (value instanceof Long)
		// {
		// long result = (Long) value;
		// return result;
		// }
		// if (value instanceof Boolean)
		// {
		// boolean result = (Boolean) value;
		// return result;
		//
		// } else
		if (value instanceof Date)
		{
			Date date = (Date) value;
			if ("".equals(dateFormat) || null == dateFormat)
			{
				dateFormat = "yyyy-MM-dd";

			}
			// IllegalArgumentException

			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			rResult = sdf.format(date);

		} else if (value instanceof byte[])
		{
			//
		} else
		{

			return value + "";
		}
		return rResult;
	}

}