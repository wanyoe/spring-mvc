package com.glp.admin.dwz;

public class DWZPage {

	int pageNum = 1;	// current page
	int numPerPage = 50;// num per page
	int totalNum = 0;	// ����
	
	String keyword;		// �����Ĺؼ���
	String status;		// ������status
	String keyword_type;// ���ѵĹؼ�������
	String orderby;		// ����
	String is_all;		// �Ƿ�ȫ��
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	public int getStart(){
		return (pageNum - 1) * numPerPage;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKeyword_type() {
		return keyword_type;
	}
	public void setKeyword_type(String keyword_type) {
		this.keyword_type = keyword_type;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getIs_all() {
		return is_all;
	}
	public void setIs_all(String is_all) {
		this.is_all = is_all;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getTotalPage() {
		int result = this.totalNum / this.numPerPage;
		if ((totalNum % numPerPage) != 0) {
			result++;
		}
		return result;
	}
	
}