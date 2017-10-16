package com.huiwan.gdata.common.bean.page;

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

	private int startNo;// 起始号
	private int showNum = 10;// 显示号的数量

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

	// public int getStartNo() {
	// if (startNo <= 0) {
	// return 1;
	// }
	// return startNo;
	// }

	public int getStartNo() {

		if (currentPage < pages) {
			if (pages > currentPage + showNum) {
				startNo = pages - showNum + 1;
			} else {
				// 当前页-显示行数/2
				startNo = currentPage - (showNum / 2);
			}
		}
		// 当前页大于(行数/2)
		if (currentPage > showNum / 2) {
			// 当前页-显示行数/2
			startNo = currentPage - (showNum / 2);
		}
		if (currentPage < showNum || currentPage < 0) {
			startNo = 1;
		}

		if (startNo <= 0) {
			return 1;
		}

		return startNo;
	}

	public static int getStartNo2(int pages, int showNum, int cIndex) {

		// 根据当前页计算起如行
		if (pages < cIndex) {
			cIndex = pages;
		} else if (cIndex <= 0) {
		}

		if (cIndex % showNum == 0) {
			return cIndex - showNum + 1;
		} else {
			if (cIndex < showNum) {
				return 1;
			} else {
				return cIndex - (cIndex % showNum) + 1;
			}

		}

	}

	/**
	 * SOA 会用到这样的方法计算 单项目忽略
	 * <p>
	 * 12 3 45
	 * 
	 * @param pages
	 * @param showNum
	 * @param cIndex
	 * @return
	 */
	public static int getStartNo3(int pages, int showNum, int cIndex) {
		int starti = 1;

		if (cIndex < pages) {

			if (pages > cIndex + showNum) {
				starti = pages - showNum + 1;
			} else {
				// 当前页-显示行数/2
				starti = cIndex - (showNum / 2);
			}

		}

		// 当前页大于(行数/2)
		if (cIndex > showNum / 2) {
			// 当前页-显示行数/2
			starti = cIndex - (showNum / 2);
		}
		if (cIndex < 5 || cIndex < 0) {
			starti = 1;
		}

		return starti;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	@Override
	public String toString() {
		return "QueryResult [pages=" + pages + ", count=" + count + ", mItems="
				+ items + ", mCurrentPage=" + currentPage + "]";
	}

}
