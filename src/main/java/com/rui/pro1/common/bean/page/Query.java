package com.rui.pro1.common.bean.page;

/**
 * 分页查询条件
 * 
 * @date 2015/5/15
 * @author liangrui
 *
 */
public class Query {
	private int pageIndex;// 当前页
	private int pageSize = 20;// 显示行数
	private boolean needPage = true;// 是否分页
	private Object bean;// 组合查询条件对象
	
	//排序

	public Query() {
		super();
	}

	public Query(int pageIndex, int pageSize) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex > 0 ? pageIndex : 1;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize > 0 ? pageSize : 0;
	}

	public void setPageSize(int mPageSize) {
		this.pageSize = mPageSize;
	}

	public int getStartRow() {
		if (pageIndex <= 0) {
			return 0;
		}
		return (pageIndex - 1) * pageSize;

	}

	public boolean isNeedPage() {
		return needPage;
	}

	public void setNeedPage(boolean needPage) {
		this.needPage = needPage;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	@Override
	public String toString() {
		return "Query [pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", needPage=" + needPage + "]";
	}

}
