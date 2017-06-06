package com.huiwan.gdata.common.utils.doc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huiwan.gdata.common.utils.doc.CSVFieldStyle;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

public class CSVExport {
	static Logger log = LoggerFactory.getLogger(CSVExport.class);

	/**
	 * 把数据转换为输出流
	 * 
	 * @param m
	 *            表头map
	 * @param users
	 *            数据集合
	 * 
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws Exception
	 */
	public static void csvOputput(Map<String, CSVFieldStyle> m, List<?> dataList, OutputStream os) {

		CsvWriter writer = null;
		try {
			// 设置标题
			CsvWriterSettings parserSettings = new CsvWriterSettings();

			// sets what is the default value to use when the parsed value is
			// null
			parserSettings.setNullValue("<NULL>");

			// sets what is the default value to use when the parsed value is
			// empty
			parserSettings.setEmptyValue("<EMPTY>"); // for CSV only

			// settings.setRowWriterProcessor(new
			// BeanWriterProcessor<User>(User.class));

			// 用于设置表格样式的对象，可以设置某一列的样式指定 FieldStyle
			List<CSVFieldStyle> tempSort = new ArrayList<CSVFieldStyle>();

			String[] keys = new String[m.size()];
			int keys_i = 0;
			for (Entry<String, CSVFieldStyle> es : m.entrySet()) {
				tempSort.add(es.getValue());
				keys[keys_i] = es.getKey();
				keys_i++;
			}

			// sets the headers of the parsed file. If the headers are set then
			// 'setHeaderExtractionEnabled(true)'
			// will make the parser simply ignore the first input row.
			parserSettings.setHeaders(keys);

			// prints the columns in reverse order.
			// NOTE: when fields are selected, all rows produced will have the
			// exact
			// same number of columns
			// parserSettings.selectFields("e", "d", "c", "b", "a");

			// does not skip leading whitespaces
			parserSettings.setIgnoreLeadingWhitespaces(false);

			// does not skip trailing whitespaces
			parserSettings.setIgnoreTrailingWhitespaces(false);

			// does not skip empty lines
			parserSettings.setSkipEmptyLines(false);

			//
			// CsvWriter writer = new CsvWriter(new OutputStreamWriter(new
			// FileOutputStream(file),"gbk"), settings);
			// FileOutputStream fos = new FileOutputStream("D:/tewst.xls");

			// OutputStream output=null;
			// ByteArrayOutputStream os = new ByteArrayOutputStream();// 字节数组输出流
			OutputStreamWriter osw = null;
			try {
				osw = new OutputStreamWriter(os, "gbk");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 输出流

			// FileOutputStream fileOut = new
			// FileOutputStream("E:\\csvTest.csv");
			// File fileOut = new File("E:\\csvTest.csv");
			writer = new CsvWriter(osw, parserSettings);
			writer.writeHeaders();

			if (dataList != null) {
				for (int i = 0; i < dataList.size(); ++i) {
					Object obj = dataList.get(i);

					String[] value_obj = new String[tempSort.size()];
					// 根据 v 找到方法 并获取值
					for (int r = 0; r < tempSort.size(); r++) {
						// 获取样式对象
						CSVFieldStyle styleObject = tempSort.get(r);
						Object value = null;
						// 如果是对象的对象
						// String objInnerObjFields[] =
						// styleObject.getTitle().split("\\.");
						// TODO 》》 styleObject 暂不做样式和对象的对象值 问题处理，以后扩展
						// 获取值
						value = getMethodValue(obj.getClass(), obj, styleObject.getFieldName());
						String strValue = converType(value, styleObject);
						// writer.writeRow(strValue);
						value_obj[r] = strValue;

					}
					writer.writeRow(value_obj);
				}
			}

			// byte[] bArry = os.toByteArray();// 获取字节数组
			// 将数组字节 写入到输入流对象
			// InputStream isba = new ByteArrayInputStream(bArry);
			// System.err.println(fileOut.toString());

		} catch (Exception e) {
			log.error("csv导出异常={}", e.getMessage());
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}

			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 根据字段获取值
	 * 
	 * @param clz
	 * @param o
	 * @param field
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static Object getMethodValue(Class clz, Object o, String field) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Character charss = field.charAt(0);
		field = field.replaceFirst(charss.toString(), Character.toUpperCase(charss) + "");
		// System.out.println(field);

		// 获取值
		@SuppressWarnings("unchecked")
		Method getMethod = clz.getDeclaredMethod("get" + field, new Class[] {});
		Object value = getMethod.invoke(o, new Object[] {});
		return value;
	}

	public static void main(String[] args) {
		// String strValue = converType(value, styleObject);

	}

	public static String converType(Object value, CSVFieldStyle fieldStyle) {

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
		// } elseif (value instanceof byte[]) {
		//
		// }
		if (value instanceof Float) {
			java.text.DecimalFormat df = null;
			if (fieldStyle.getDateFormat() == null) {
				df = new java.text.DecimalFormat("0.00");
				df.setRoundingMode(RoundingMode.FLOOR);
			} else {
				df = fieldStyle.getDecimalFormat();
			}
			float result = (Float) value;
			return df.format(result);

		} else if (value instanceof BigDecimal) {
			BigDecimal resultBigDeci = (BigDecimal) value;
			// setScale
			double result = resultBigDeci
					.setScale(fieldStyle.getBigDecimalNewScale(), fieldStyle.getBigDecimalRoundingMode()).doubleValue();
			// double result=resultBigDeci.doubleValue();
			// java.text.DecimalFormat df = null;
			// if (fieldStyle.getDateFormat() == null) {
			// df = new java.text.DecimalFormat("0.00");
			// df.setRoundingMode(RoundingMode.FLOOR);
			// } else {
			// df = fieldStyle.getDecimalFormat();
			// }

			return String.valueOf(result);
		} else if (value instanceof Double) {
			java.text.DecimalFormat df = null;
			if (fieldStyle.getDateFormat() == null) {
				df = new java.text.DecimalFormat("0.00");
				df.setRoundingMode(RoundingMode.FLOOR);
			} else {
				df = fieldStyle.getDecimalFormat();
			}
			double result = (Double) value;

			return df.format(result);
		} else if (value instanceof Date) {
			String dateFormat = fieldStyle.getDateFormat();
			Date date = (Date) value;
			if (null == dateFormat || "".equals(dateFormat)) {
				dateFormat = "yyyy-MM-dd HH:mm:ss";
			}
			// IllegalArgumentException
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			rResult = sdf.format(date);

		} else {

			return value + "";
		}
		return rResult;
	}

}
