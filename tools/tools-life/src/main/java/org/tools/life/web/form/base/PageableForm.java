package org.tools.life.web.form.base;


import org.tools.life.domain.base.PageableVO;

/**
 * 
 * 分页表单属性
 *
 */
public class PageableForm {
    /**
     * 开始索引
     */
    private int pageNo;

    /**
     * 要显示的数量
     */
    private int pageSize;

    /**
     * 排序字符串
     */
    private String sortString;

    /**
     * 订单中心，财务中心排序字符串
     */
    private String oldSortString;

    public String getOldSortString() {
        return oldSortString;
    }

    public void setOldSortString(String oldSortString) {
        this.oldSortString = oldSortString;
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

    public String getSortString() {
        return sortString;
    }

    public void setSortString(String sortString) {
        this.sortString = sortString;
    }

    /**
     * 把分页参数复制到PageableVO
     * 
     * @author 李贺[of253]
     * @date 2013-12-7 下午4:02:44
     * @param pageableVO
     */
    public void copyPropertiesToVO(PageableVO pageableVO) {
        pageableVO.setPageNo(pageNo);
        pageableVO.setPageSize(pageSize);
        pageableVO.setSortString(sortString);
    }

}