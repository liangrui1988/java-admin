package com.huiwan.gdata.common.utils.doc;

public class ImprotBean {

	private String name;

	/**
	 * -1=自动识别！<br>
	 * 1=字符串，2=数字时间转字符串，待扩展更多转换.....<br>
	 * 自定义扩展类型，防数字转换字符串带有.0<br>
	 * 
	 */
	private int cellType = -1;// XSSFCell.CELL_TYPE_STRING;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCellType() {
		return cellType;
	}

	public void setCellType(int cellType) {
		this.cellType = cellType;
	}

}
