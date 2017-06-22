package com.huiwan.gdata.common.utils.doc;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 用于设置表格样式的对象，可以设置某一列的样式指定
 * 
 * @author rui
 *
 */
public class FieldStyle {

	/**
	 * 导出字段名（默认调用当前字段的“get”方法，如指定导出字段为对象，请填写“对象名.对象属性”，例：“department.name”）
	 */
	private String value;

	/**
	 * 导出字段标题（需要添加批注请用“**”分隔，标题**批注，仅对导出模板有效）
	 */
	private String title;

	/**
	 * 字段类型（0：导出导入；1：仅导出；2：仅导入）
	 * 
	 */
	@Deprecated
	private int type;

	/**
	 * 导出字段对齐方式（{@link org.apache.poi.ss.usermodel.CellStyle}）
	 * 
	 * CellStyle.ALIGN_LEFT 左
	 * <p>
	 * CellStyle.ALIGN_RIGHT 右
	 * 
	 * @default 剧中
	 */
	private short alignment = CellStyle.ALIGN_CENTER;

	/**
	 * 导出字段字段排序（升序）
	 */
	// private int sort;

	/**
	 * 反射类型
	 */
	@Deprecated
	private Class<?> fieldType;

	/**
	 * 字段归属组（根据分组导出导入）
	 */
	@Deprecated
	private int[] groups;

	/**
	 * 日期格式
	 */
	private String dateFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 100为1个px
	 * 
	 * @throws Exception
	 *             不能大于255*255=65025
	 * @return
	 */
	private int colunmWidth;

	/**
	 * 如果是小数，格式样式自定议。默认两位 不四舍五入
	 */
	private DecimalFormat decimalFormat;

	/**
	 * 如果是BigDecimal类型，格试化时指定小数位数。默认2位
	 */
	private int bigDecimalNewScale = 2;
	/**
	 * 如果是BigDecimal类型，格试化时 取舍定义，默认不四舍五入
	 */
	private int bigDecimalRoundingMode = BigDecimal.ROUND_DOWN;

	@Deprecated
	private boolean isSkipZero;

	public FieldStyle(String title) {
		this.title = title;
	}

	public FieldStyle(String title, short alignment, String dateFormat,
			int colunmWidth) {
		this.title = title;
		this.alignment = alignment;
		this.dateFormat = dateFormat;
		this.colunmWidth = colunmWidth;
	}

	public FieldStyle(String title, short alignment) {
		this.title = title;
		this.alignment = alignment;
	}

	public FieldStyle(String title, short alignment, int colunmWidth) {
		this.title = title;
		this.alignment = alignment;
		this.colunmWidth = colunmWidth;
	}

	public FieldStyle(String title, DecimalFormat decimalFormat) {
		this.title = title;
		this.decimalFormat = decimalFormat;
	}

	public FieldStyle(String value, String title) {
		super();
		this.value = value;
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Class<?> getFieldType() {
		return fieldType;
	}

	public void setFieldType(Class<?> fieldType) {
		this.fieldType = fieldType;
	}

	public int[] getGroups() {
		return groups;
	}

	public void setGroups(int[] groups) {
		this.groups = groups;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public int getColunmWidth() {
		return colunmWidth;
	}

	public void setColunmWidth(int colunmWidth) {
		this.colunmWidth = colunmWidth;
	}

	public boolean isSkipZero() {
		return isSkipZero;
	}

	public void setSkipZero(boolean isSkipZero) {
		this.isSkipZero = isSkipZero;
	}

	public short getAlignment() {
		return alignment;
	}

	public void setAlignment(short alignment) {
		this.alignment = alignment;
	}

	public DecimalFormat getDecimalFormat() {
		return decimalFormat;
	}

	public void setDecimalFormat(DecimalFormat decimalFormat) {
		this.decimalFormat = decimalFormat;
	}
	
	

	public int getBigDecimalNewScale() {
		return bigDecimalNewScale;
	}

	public void setBigDecimalNewScale(int bigDecimalNewScale) {
		this.bigDecimalNewScale = bigDecimalNewScale;
	}

	public int getBigDecimalRoundingMode() {
		return bigDecimalRoundingMode;
	}

	public void setBigDecimalRoundingMode(int bigDecimalRoundingMode) {
		this.bigDecimalRoundingMode = bigDecimalRoundingMode;
	}

	public FieldStyle(String value, String title, int type, short alignment,
			Class<?> fieldType, int[] groups, String dateFormat,
			int colunmWidth, boolean isSkipZero) {
		super();
		this.value = value;
		this.title = title;
		this.type = type;
		this.alignment = alignment;
		this.fieldType = fieldType;
		this.groups = groups;
		this.dateFormat = dateFormat;
		this.colunmWidth = colunmWidth;
		this.isSkipZero = isSkipZero;
	}

}
