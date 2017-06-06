package com.huiwan.gdata.modules.sys.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.huiwan.gdata.modules.sys.entity.User;

/**
 * 导入xls工具
 * 
 * @author liangrui
 * @date 2015/12/3
 */
@Component
public class ExportXls {

	private final static Logger logger = LoggerFactory
			.getLogger(ExportXls.class);

	/**
	 * 旁通导入
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public List<User> exportXls(String path) throws IOException {

		List<User> list = new LinkedList<User>();

		// 创建对Excel工作簿文件的引用
		XSSFWorkbook workbook = new XSSFWorkbook(path);
		// 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
		// HSSFSheet sheet = workbook.getSheet("Sheet1");
		XSSFSheet sheet = workbook.getSheetAt(0);
		// HSSFSheet sheet = workbookBack.getSheetAt(0);
		// 读取一行
		// HSSFRow row = sheet.getRow(0);
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
			User User = new User();
			boolean isadd = true;
			while (it.hasNext()) {
				Cell ce = it.next();
				// HSSFCell ce = it.next();
				// 检查是否合法
				if (j == 0) {
					String titleName = ce.getStringCellValue();
					Class clz = User.getClass();
					if (!isOk(clz, titleName)) {
						// System.out.println("表格格式不符合导入的数据格式!");
						logger.error("表格格式不符合导入的数据格式!");
					}

				} else {
					switch (cellIndex) {
					case 0:
						DecimalFormat df = new DecimalFormat("#");
						String Stringd = df.format(ce.getNumericCellValue());
//						User.setCode(Stringd);
						break;
					case 1:
						try {
//							User.setCity(ce.getStringCellValue());
						} catch (Exception e) {
							isadd = false;
							logger.error(e.getMessage());
						}
						break;
					case 2:
						int nums = (int) ce.getNumericCellValue();
						if (nums <= 0) {
							isadd = false;
							break;
						}
//						User.setNum(nums);
						break;
					default:
						break;
					}

				}
				cellIndex++;
			}
			if (j != 0 && isadd) {
				list.add(User);
			}

			j++;
		}

		return list;

	}

	/**
	 * Request processing failed; nested exception is
	 * org.apache.poi.poifs.filesystem.OfficeXmlFileException: The supplied data
	 * appears to be in the Office 2007+ XML. POI only supports OLE2 Office
	 * documents 兼容老版本 xls问题
	 * 
	 * @param is
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public List<User> exportXlsStrem(InputStream is, String path)
			throws IOException {

		List<User> list = new LinkedList<User>();
		// 创建对Excel工作簿文件的引用
		// HSSFWorkbook workbook = new HSSFWorkbook(is);
		Workbook workbook;
		if (StringUtils.isBlank(path)) {
			workbook = new HSSFWorkbook(is);
		} else { // 兼容老版本 xls问题
			workbook = new XSSFWorkbook(path);

		}

		// 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
		// HSSFSheet sheet = workbook.getSheet("Sheet1");
		// HSSFSheet sheet = workbook.getSheetAt(0);
		Sheet sheet = workbook.getSheetAt(0);

		// 读取一行
		// HSSFRow row = sheet.getRow(0);
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
			User User = new User();

			boolean isadd = true;
			while (it.hasNext()) {
				Cell ce = it.next();
				// HSSFCell ce = it.next();
				// 检查是否合法
				if (j == 0) {
					String titleName = ce.getStringCellValue();
					Class clz = User.getClass();
					if (!isOk(clz, titleName)) {
						// System.out.println("表格格式不符合导入的数据格式!");
						
						logger.error("表格格式不符合导入的数据格式!");
						 throw new IOException("表格格式不符合导入的数据格式!");
					}

				} else {
					switch (cellIndex) {
					case 0:
						DecimalFormat df = new DecimalFormat("#");
						String Stringd = df.format(ce.getNumericCellValue());
//						User.setCode(Stringd);
						break;
					case 1:
						try {
//							User.setCity(ce.getStringCellValue());
						} catch (Exception e) {
							isadd = false;
							logger.error(e.getMessage());
						}
						break;
					case 2:
						int nums = (int) ce.getNumericCellValue();
						if (nums <= 0) {
							isadd = false;
							break;
						}
//						User.setNum(nums);
						break;
					default:
						break;
					}

				}
				cellIndex++;
			}
			if (j != 0 && isadd) {
			
				list.add(User);
			}

			j++;
		}

		if (!StringUtils.isBlank(path)) {
			is.close();
			del(path);
		}
		return list;

	}

	/**
	 * 检查表格是否和对象一致
	 * 
	 * @param clz
	 * @param titleName
	 * @return
	 */
	public static boolean isOk(Class clz, String titleName) {
		boolean isExist = false;
		Field[] fa = clz.getDeclaredFields();
		for (int i = 0; i < fa.length; i++) {
			// System.out.println(fa[i].getName());
			if (titleName.equals(fa[i].getName())) {
				isExist = true;
				break;
			}
		}

		return isExist;
	}

	/**
	 * 写入文件
	 * 
	 * @param is
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	public void writeFile(InputStream is, String path, String fileName)
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

	/**
	 * 刪除文件
	 * 
	 * @param path
	 */
	public void del(final String path) {

		File file = new File(path);
		if (file.isFile() && file.exists()) {
			file.delete();
		}

		if (file.exists()) {
			String cmd = "cmd /c del ";
			String system = System.getProperties().getProperty("os.name");
			// System.getProperties().getProperty("file.separator");
			if (!system.toLowerCase().startsWith("win")) {
				cmd = "rm -rf ";
			}
			Runtime run = Runtime.getRuntime();
			try {
				cmd = cmd + path;
				System.out.println(cmd);
				Process p = run.exec(cmd);
				p.waitFor();
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
//				Timer timer = new Timer();
//				timer.schedule(new TimerTask() {
//					@Override
//					public void run() {
//						 del(path);
//					}
//				}, 10000);
			}

		}
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		String file = "E:/优质用户热门城市.xls";
		List<User> list = new ExportXls().exportXls(file);
		System.out.println("=============================================");
		for (User u : list) {
//			System.out.println("name:" + u.getCode() + " \t " + u.getCity()
//					+ " \t " + u.getNum());
		}
	}
}
