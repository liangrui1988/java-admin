package com.rui.pro1.common.bean.page;

import org.apache.commons.lang3.StringUtils;

/**
 * bootstarp Paginator分页参数
 * <p>
 * order=asc&limit=10&offset=30
 * 
 * @author ruiliang
 * 
 */
public class Paginator {

	private String order;
	private int limit;
	private int offset;//起始行
	private String sort;//起始行
	private Object bean;//起始行



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
//		if(!StringUtils.isBlank(sort)){
//			if(!StringUtils.isBlank(order)){
//				sort=(sort+" "+order);
//			}
//		}
		return sort;
	}

	public void setSort(String sort) {
		
		if(!StringUtils.isBlank(sort)){
			if(!StringUtils.isBlank(order)){
				StringBuffer sb=new StringBuffer();
				sb.append(sort);
				sb.append(" ");
				sb.append(order);
				sort=sb.toString();
			}
		}
		
		this.sort = sort;
	}

	
	
	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

//	@Deprecated
//	public <T>  Page<T> toPageJSTL(Page<T> page){
//		if(page==null){
//			page=new Page<T>();
//		}
//		
//		if(!StringUtils.isBlank(sort)){
//			if(!StringUtils.isBlank(order)){
//				sort=(sort+" "+order);
//			}
//			page.setOrderBy(sort);
//		}
//		
//		if(offset<=0){
//			offset=1;
//		}
//		page.setPageNo(offset);
//		page.setPageSize(limit);
//		return page;
//	}

	
	
}
