package com.rui.pro1.common.bean.page;

/**
 * PaginatorResult 分页返回结果
 * 
 * @author ruiliang
 *
 */
public class PaginatorResult {

	private Object rows;
	private long total;

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
