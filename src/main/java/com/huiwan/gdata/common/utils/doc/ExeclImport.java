package com.huiwan.gdata.common.utils.doc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.huiwan.gdata.modules.sys.entity.User;

public class ExeclImport {

	/**
	 * Request processing failed; nested exception is
	 * org.apache.poi.poifs.filesystem.OfficeXmlFileException: The supplied data
	 * appears to be in the Office 2007+ XML. POI only supports OLE2 Office
	 * documents 兼容老版本 xls问题
	 * 
	 * <br>
	 * 删除 不了的问题 SXSSFWorkbook.dispose()
	 * <p>
	 * https://bz.apache.org/bugzilla/show_bug.cgi?id=53493
	 * 
	 * @param is
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <E> List<E> exportXlsStrem(InputStream is, String path)
			throws IOException, InstantiationException, IllegalAccessException {

		List<E> list = new LinkedList<E>();
		// 创建对Excel工作簿文件的引用
		// HSSFWorkbook workbook = new HSSFWorkbook(is);
		Workbook workbook;

		// SXSSFWorkbook x=new SXSSFWorkbook();
		// x.dispose();

		// XSSFWorkbook xworkbook;
		if (StringUtils.isBlank(path)) {
			workbook = new HSSFWorkbook(is); // Excel '97(-2007)
		} else { // 兼容本 Excel 2007 OOXML (.xlsx)
			workbook = new XSSFWorkbook(path);

		}

		// 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
		// HSSFSheet sheet = workbook.getSheet("Sheet1");
		Sheet sheet = workbook.getSheetAt(0);

		// 读取一行
		Row sheetRow = sheet.getRow(0);

		int lastCellNum = sheetRow.getLastCellNum();

		// it读取行
		// Iterator<HSSFRow> itRow = sheet.rowIterator();
		Iterator<Row> itRow = sheet.rowIterator();

		int j = 0;
		while (itRow.hasNext()) {
			// HSSFRow row = itRow.next();
			Row row = itRow.next();
			// 读行格
			Iterator<Cell> it = row.cellIterator();
			// Iterator<HSSFCell> it = row.cellIterator();
			int cellIndex = 0;
			E qualityCity = null;

			qualityCity = (E) qualityCity.getClass().newInstance();

			boolean isadd = true;
			while (it.hasNext()) {
				Cell ce = it.next();
				// HSSFCell ce = it.next();
				// 检查是否合法
				if (j == 0) {
					String titleName = ce.getStringCellValue();
					Class clz = qualityCity.getClass();
					// if (!isOk(clz, titleName)) {
					// // System.out.println("表格格式不符合导入的数据格式!");
					//
					// logger.error("表格格式不符合导入的数据格式!");
					// throw new IOException("表格格式不符合导入的数据格式!");
					// }

				} else {
					switch (cellIndex) {
					case 0:
						DecimalFormat df = new DecimalFormat("#");
						String Stringd = df.format(ce.getNumericCellValue());
						// qualityCity.setCode(Stringd);
						break;
					case 1:
						// try {
						// qualityCity.setCity(ce.getStringCellValue());
						// } catch (Exception e) {
						// isadd = false;
						// logger.error(e.getMessage());
						// }
						break;
					case 2:
						// int nums = (int) ce.getNumericCellValue();
						// if (nums <= 0) {
						// isadd = false;
						// break;
						// }
						// qualityCity.setNum(nums);
						break;
					default:
						break;
					}

				}
				cellIndex++;
			}
			if (j != 0 && isadd) {

				list.add(qualityCity);
			}

			j++;
		}

		if (!StringUtils.isBlank(path)) {
			is.close();
			// del(path);
		}
		return list;

	}

	public static <E> List<E> exportXlsx(Class<E> e, String path,
			Map<String, ImprotBean> map) throws IOException,
			InstantiationException, IllegalAccessException,
			InvalidFormatException {
		List<E> list = new LinkedList<E>();
		// 创建对Excel工作簿文件的引用
		OPCPackage pkg = OPCPackage.open(path);
		XSSFWorkbook workbook = new XSSFWorkbook(pkg);
		// 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
		Sheet sheet = workbook.getSheetAt(0);

		// 读取一行
		Row sheetRow = sheet.getRow(0);
		// it读取行
		Iterator<Row> itRow = sheet.rowIterator();
		// cell下标对应对象属性
		Map<Integer, ImprotBean> objFleidIndex = new HashMap<Integer, ImprotBean>();

		int j = 0;
		while (itRow.hasNext()) {
			E eObj = e.newInstance();
			Row row = itRow.next();
			// 读行格
			Iterator<Cell> it = row.cellIterator();
			int cellIndex = 0;
			while (it.hasNext()) {
				Cell ce = it.next();
				// ce.getCachedFormulaResultType();
				// 检查是否合法
				if (j == 0) {
					// getValue(workbook, ce);
					// 先确定map name对应的cell下标是多少，根据下标进行设置值
					String cellTitleValue = ce.getStringCellValue();
					if (!map.containsKey(cellTitleValue)) {
						throw new RuntimeException(
								"给定的map模版中的标题和excel表格中的标题对应不上，请检查！");
					}

					ImprotBean iBean = map.get(cellTitleValue);
					// 下标关联 对象属性
					objFleidIndex.put(cellIndex, iBean);
				} else {

					if (!objFleidIndex.containsKey(cellIndex)) {
						throw new RuntimeException("表格数据和标题对应不上，请检查！");
					}

					// System.out.println(cellIndex + ">> 表格下标对应:>>"
					// + objFleidIndex.get(cellIndex));

					// 给对象设置值
					BeanWrapper beanWrapper = PropertyAccessorFactory
							.forBeanPropertyAccess(eObj);

					Object objValue = null;
					// 如果没有指定类型
					if (objFleidIndex.get(cellIndex).getCellType() == -1) {
						objValue = getValue(workbook, ce);
					} else {
						objValue = getValue(workbook, ce,
								objFleidIndex.get(cellIndex).getCellType());
					}
					beanWrapper.setPropertyValue(objFleidIndex.get(cellIndex)
							.getName(), objValue);

				}
				cellIndex++;
			}
			if (j != 0) {
				list.add(eObj);
			}
			j++;
		}

		pkg.close();// gracefully closes the underlying zip file
		return list;

	}

	public static Object getValue(Workbook wb, Cell cell) {
		Object obj = null;
		FormulaEvaluator evaluator = wb.getCreationHelper()
				.createFormulaEvaluator();
		CellValue cellValue = evaluator.evaluate(cell);
		switch (cellValue.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			// System.out.println(cellValue.getBooleanValue());
			obj = cellValue.getBooleanValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			// System.out.println(cellValue.getNumberValue());
			obj = cellValue.getNumberValue();
			break;
		case Cell.CELL_TYPE_STRING:
			// System.out.println(cellValue.getStringValue());
			obj = cellValue.getStringValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			break;
		case Cell.CELL_TYPE_ERROR:
			break;

		// CELL_TYPE_FORMULA will never happen
		case Cell.CELL_TYPE_FORMULA:// 公式
			break;

		}

		return obj;
	}

	public static Object getValue(Workbook workbook, Cell ce, int objType) {

		Object objx = null;
		FormulaEvaluator evaluator = workbook.getCreationHelper()
				.createFormulaEvaluator();
		CellValue cellValue = evaluator.evaluate(ce);
		switch (cellValue.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			objx = cellValue.getBooleanValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			objx = cellValue.getNumberValue();
			if (objType == 1) {

				System.out.println(objx);
				DecimalFormat d = new DecimalFormat("#");
				objx = d.format(objx);// 转string
			} else if (objType == 2) {
				objx = HSSFDateUtil.getJavaDate(ce.getNumericCellValue())
						.toString();
			}

			break;
		case Cell.CELL_TYPE_STRING:
			objx = cellValue.getStringValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			break;
		case Cell.CELL_TYPE_ERROR:
			break;

		// CELL_TYPE_FORMULA will never happen
		case Cell.CELL_TYPE_FORMULA:// 公式
			break;

		}

		return objx;
	}

	public static void importExecl(MultipartHttpServletRequest request)
			throws IOException, InstantiationException, IllegalAccessException {

		org.springframework.web.multipart.MultipartFile mf = request
				.getFile("file1");
		if (mf != null) {
			InputStream is = mf.getInputStream();
			String path = "";
			try {
				new HSSFWorkbook(is);
			} catch (Exception e) {
				String resFileName = mf.getOriginalFilename();// 获取原文件名
				String relativelyPath = System.getProperty("user.dir");
				// logger.warn("user.dir>>" + relativelyPath);
				if (!new File(relativelyPath + "/temp").exists()) {
					// logger.warn("mkdir:" + relativelyPath + "/temp");
					new File(relativelyPath + "/temp").mkdirs();
				}
				path = relativelyPath + "/temp/" + resFileName;
				writeFile(mf.getInputStream(), relativelyPath + "/temp/",
						resFileName);
				// mf.transferTo(f);
			}
			List list = exportXlsStrem(is, path);
			// 写入文件
			// if (list != null && list.size() > 0) {
			// qualityUserService.addCity(list);
			// }

		}

	}

	/**
	 * 写入文件
	 * 
	 * @param is
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	public static void writeFile(InputStream is, String path, String fileName)
			throws IOException {
		// 输入流
		// InputStream is = new FileInputStream(src);
		// File 对象
		File fileObj = new File(path, fileName);
		// 输出流
		OutputStream os = new FileOutputStream(fileObj);

		byte[] by = new byte[400];
		int length = 0;
		while (-1 != (length = is.read(by))) {
			os.write(by, 0, length);
		}

		os.close();
		is.close();
	}

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, InvalidFormatException, IOException {
		String file = "E:/用户导入.xlsx";
		Map<String, ImprotBean> m = new LinkedHashMap<String, ImprotBean>();
		ImprotBean bean = new ImprotBean();
		bean.setName("userName");
		m.put("登陆名", bean);
		ImprotBean bean2 = new ImprotBean();
		bean2.setName("password");
		bean2.setCellType(1);
		m.put("密码", bean2);
		ImprotBean bean3 = new ImprotBean();
		bean3.setName("fullName");
		m.put("姓名", bean3);
		ImprotBean bean4 = new ImprotBean();
		bean4.setName("status");
		m.put("状态", bean4);
		List<User> list = ExeclImport.exportXlsx(User.class, file, m);
		System.out.println(list);
		for (User u : list) {
			System.out.println(u.getUserName() + "," + u.getPassword() + ","
					+ u.getFullName() + "," + u.getStatus());
		}
	}

}
