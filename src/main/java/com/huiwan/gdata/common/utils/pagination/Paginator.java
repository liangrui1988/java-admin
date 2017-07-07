package com.huiwan.gdata.common.utils.pagination;

import org.apache.commons.lang.StringUtils;

/**
 * bootstarp Paginator分页参数
 * <p>
 * order=asc&limit=10&offset=30
 * 
 * @author ruiliang
 * @date 2017/03/08
 */
public class Paginator {

	private String order;// 排序方式
	private int limit=10;// 限制取多少行
	private int offset;// 起始行,忽略前面行数
	private String sort;// 排序名称
	private String pageId;// 排序名称
	private Object bean;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getSort() {
		return sort;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getSortAppendOrderBy() {
		if (!StringUtils.isBlank(sort)) {
			if (!StringUtils.isBlank(order)) {
				StringBuffer sb = new StringBuffer();
				sb.append(sort);
				sb.append(" ");
				sb.append(order);
				sort = sb.toString();
			}
		}
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	// @Deprecated
	// public <T> Page<T> toPageJSTL(Page<T> page) {
	// if (page == null) {
	// page = new Page<T>();
	// }
	//
	// if (!StringUtils.isBlank(sort)) {
	// if (!StringUtils.isBlank(order)) {
	// sort = (sort + " " + order);
	// }
	// page.setOrderBy(sort);
	// }
	//
	// if (offset <= 0) {
	// offset = 1;
	// }
	// page.setPageNo(offset);
	// page.setPageSize(limit);
	// return page;
	// }

}