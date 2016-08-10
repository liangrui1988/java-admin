package com.rui.pro1.common.utils.doc;

/**
 * 字段样式对象
 * 
 * @author rui
 *
 */
public class FieldStyle {

	/**
	 * 导出字段名（默认调用当前字段的“get”方法，如指定导出字段为对象，请填写“对象名.对象属性”，例：“area.name”、“office.
	 * name”）
	 * 暂不支持
	 */
	private String value;

	/**
	 * 导出字段标题（需要添加批注请用“**”分隔，标题**批注，仅对导出模板有效）
	 */
	private String title;

	/**
	 * 字段类型（0：导出导入；1：仅导出；2：仅导入）
	 */
	private int type;

	/**
	 * 导出字段对齐方式（0：自动；1：靠左；2：居中；3：靠右）
	 */
	private int align;

	/**
	 * 导出字段字段排序（升序）
	 */
	private int sort;

	/**
	 * 如果是字典类型，请设置字典的type值
	 */
	private String dictType;

	/**
	 * 反射类型
	 */
	private Class<?> fieldType;

	/**
	 * 字段归属组（根据分组导出导入）
	 */
	private int[] groups;

	/**
	 * 日期格式
	 */
	private String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 100为1个px
	 * 
	 * @return
	 */
	private int colunmWidth;

	private boolean isSkipZero;
	
	
	
	
	public FieldStyle(String title) {
		this.title = title;
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

	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
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



	public FieldStyle(String value, String title, int type, int align,
			int sort, String dictType, Class<?> fieldType, int[] groups,
			String dateFormat, int colunmWidth, boolean isSkipZero) {
		super();
		this.value = value;
		this.title = title;
		this.type = type;
		this.align = align;
		this.sort = sort;
		this.dictType = dictType;
		this.fieldType = fieldType;
		this.groups = groups;
		this.dateFormat = dateFormat;
		this.colunmWidth = colunmWidth;
		this.isSkipZero = isSkipZero;
	}
	
	

}
