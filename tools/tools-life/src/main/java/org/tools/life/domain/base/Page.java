package org.tools.life.domain.base;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public class Page {

	// 页码，默认是第一页
	private int pageNo = 0;

	// 每页显示的记录数
	private int pageSize ;

	// 总记录数
	private int totalRecord;

	// 总页数
	private int totalPage;

	// 排序信息
	private String sortInfo; 

	private Object params;

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public String getSortInfo() {
		return sortInfo;
	}

	public void setSortInfo(String sortInfo) {
		this.sortInfo = sortInfo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		// 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		int totalPageInt = totalRecord % pageSize == 0 ? 
				totalRecord / pageSize : totalRecord / pageSize + 1;
		this.setTotalPage(totalPageInt);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public static Page getInstance(PageableVO baseListVO) {
		Page page = new Page();
		page.setPageNo(baseListVO.getPageNo());
		page.setPageSize(baseListVO.getPageSize());
		page.setSortInfo(baseListVO.getSortString());
		page.setParams(baseListVO);
		return page;
	}
}