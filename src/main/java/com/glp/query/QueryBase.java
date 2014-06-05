package com.glp.query;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Pagination algorithm.
 * TotalItem: 0 by default, should be set as PageSize in dao and web level.
 * QueryBase: 20 by default, subclasses can overwrite getDefaultPageSize() to change CurrengPage (1 by default),
 * Should applied on web level, TotalPage and FristItem(first item of the current page, start from 1) will be available. 
 * PageLastItem(last item of the current page). 
 * In the view, lines will represent the total items of current page, page will represent the current page.  
 * 
 * @author fish
 */
public abstract class QueryBase implements Serializable {

	private static final long serialVersionUID = 7603300820027561064L;

//	private static final Integer defaultPageSize = new Integer(1);
	private static final Integer defaultPageSize = new Integer(30);

	private static final Integer defaultFriatPage = new Integer(1);

	private static final Integer defaultTotleItem = new Integer(0);
	
	private static final String defaultOrderBy = "0";

	private Integer totalItem;

	private Integer pageSize;

	private Integer currentPage;
	
	private String orderBy;
	
	private String sort;

	/**
	 * @return Returns the defaultPageSize.
	 */
	protected final Integer getDefaultPageSize() {
		return defaultPageSize;
	}

	public boolean isFirstPage() {
		return this.getCurrentPage().intValue() == 1;
	}

	public int getPreviousPage() {
		int back = this.getCurrentPage().intValue() - 1;

		if (back <= 0) {
			back = 1;
		}

		return back;
	}

	public boolean isLastPage() {
		return this.getTotalPage() == this.getCurrentPage().intValue();
	}

	public int getNextPage() {
		int back = this.getCurrentPage().intValue() + 1;

		if (back > this.getTotalPage()) {
			back = this.getTotalPage();
		}

		return back;
	}

	/**
	 * @return Returns the currentPage.
	 */
	public Integer getCurrentPage() {
		if (currentPage == null) {
			return defaultFriatPage;
		}

		return currentPage;
	}

	/**
	 * @param current
	 *            The currentPage to set.
	 */
	public void setCurrentPage(Integer cPage) {
		if ((cPage == null) || (cPage.intValue() <= 0)) {
			this.currentPage = defaultFriatPage;
		} else {
			this.currentPage = cPage;
		}
	}

	public void setCurrentPageString(String s) {
		if (StringUtils.isBlank(s)) {
			return;
		}
		try {
			setCurrentPage(Integer.parseInt(s));
		} catch (NumberFormatException ignore) {
			this.setCurrentPage(defaultFriatPage);
		}
	}

	/**
	 * @return Returns the pageSize.
	 */
	public Integer getPageSize() {
		if (pageSize == null) {
			return getDefaultPageSize();
		}

		return pageSize;
	}

	public boolean hasSetPageSize() {
		return pageSize != null;
	}

	/**
	 * @param size
	 *            The pageSize to set.
	 */
	public void setPageSize(Integer pSize) {
		if (pSize == null) {
			this.pageSize = defaultPageSize;
			return;
			//throw new IllegalArgumentException("PageSize can't be null.");
		}

		if (pSize.intValue() <= 0) {
			this.pageSize = defaultPageSize;
			return;
			//throw new IllegalArgumentException("PageSize must great than zero.");
		}

		this.pageSize = pSize;
	}

	public void setPageSizeString(String pageSizeString) {
		if (StringUtils.isBlank(pageSizeString)) {
			return;
		}

		try {
			Integer integer = new Integer(pageSizeString);
			this.setPageSize(integer);
		} catch (NumberFormatException ignore) {
		}
	}

	/**
	 * @return Returns the totalItem.
	 */
	public Integer getTotalItem() {
		if (totalItem == null) {
			// throw new IllegalStateException("Please set the TotalItem
			// frist.");
			return defaultTotleItem;
		}
		return totalItem;
	}

	/**
	 * @param totalItem
	 *            The totalItem to set.
	 */
	public void setTotalItem(Integer tItem) {
		if (tItem == null) {
			// throw new IllegalArgumentException("TotalItem can't be null.");
			tItem = new Integer(0);
		}
		this.totalItem = tItem;
		int current = this.getCurrentPage().intValue();
		int lastPage = this.getTotalPage();
		if (current > lastPage) {
			this.setCurrentPage(new Integer(lastPage));
		}
	}

	public int getTotalPage() {
		int pgSize = this.getPageSize().intValue();
		int total = this.getTotalItem().intValue();
		int result = total / pgSize;
		if ((total % pgSize) != 0) {
			result++;
		}
		return result;
	}

	public int getPageFristItem() {
		int cPage = this.getCurrentPage().intValue();
		if (cPage == 1) {
			return 1;
		}
		cPage--;
		int pgSize = this.getPageSize().intValue();

		return (pgSize * cPage) + 1;
	}

	/**
	 * @return return 0 for MySQL.
	 */
	public int getMysqlPageFristItem() {
		return getPageFristItem() - 1;
	}

	public int getPageLastItem() {
		int assumeLast = getExpectPageLastItem();
		int totalItem = getTotalItem().intValue();

		if (assumeLast > totalItem) {
			return totalItem;
		} else {
			return assumeLast;
		}
	}

	public int getExpectPageLastItem() {
		int cPage = this.getCurrentPage().intValue();
		int pgSize = this.getPageSize().intValue();
		return pgSize * cPage;
	}

	protected String getSQLBlurValue(String value) {
		if (value == null) {
			return null;
		}

		return value + '%';
	}

	public String getOrderBy() {
		if (StringUtils.isBlank(orderBy)) {
			return defaultOrderBy;
		}
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * get parameters without pagination parameters.
	 * @return
	 */
	public abstract Map<String, Object> getParameters();
}
