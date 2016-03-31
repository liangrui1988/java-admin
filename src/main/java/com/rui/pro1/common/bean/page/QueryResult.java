package com.rui.pro1.common.bean.page;

import java.io.Serializable;
import java.util.List;

/**
 * 返回分页信息实体
 * 
 * @date 2015/5/15
 * @author liangrui
 *
 * @param <T>
 */
public class QueryResult<T> implements Serializable {
	private static final long serialVersionUID = 1000L;
	private int pages; // 总页数
	private long count;// 总记录数
	private List<T> items;// 数据
	private int currentPage = 1;// 当前页

	// private int startNo;// 起始号
	// private int showNum = 10;// 显示号的数量

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	// 计算总页数
	public void setPages(long count, long mpageSize) {
		this.count = count;

		if (count <= 0) {
			this.pages = 1;
			return;
		}
		if (count <= mpageSize) {
			this.pages = 1;
			return;
		}

		long totalPages = count / mpageSize;
		this.pages += (totalPages + (count % mpageSize == 0 ? 0 : 1));

	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getCurrentPage() {

		if (currentPage <= 0) {
			currentPage = 1;
		}

		if (this.getPages() < currentPage) // 如果当前页大于数据页
		{
			currentPage = this.getPages();
		}
		return currentPage;
	}

	public void setCurrentPage(int mCurrentPage) {
		this.currentPage = mCurrentPage;
	}

	@Override
	public String toString() {
		return "QueryResult [pages=" + pages + ", count=" + count + ", mItems="
				+ items + ", mCurrentPage=" + currentPage + "]";
	}

}
