package org.tools.life.support;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.tools.life.web.form.base.PageableForm;

/**
 * 
 * 用于绑定PageableForm的方法参数解析器
 * 
 * @author 彭文哲[OF789]
 * @date 2013-10-31 下午1:53:10
 *
 */
public class PageableMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 记录日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(PageableMethodArgumentResolver.class);

    /**
     * PageableForm
     */
    public boolean supportsParameter(MethodParameter parameter) {
        if (PageableForm.class.isAssignableFrom(parameter.getParameterType())) {
            return true;
        }
        return false;
    }

    /**
     * 根据前台参数组织后台分页参数
     */
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest request, WebDataBinderFactory binderFactory) {
        try {
            int iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
            int iDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
            int iSortingCols = request.getParameter("iSortingCols") == null ? 0 : Integer.valueOf(request
                    .getParameter("iSortingCols"));
            String[] sColumns = request.getParameter("sColumns").split(",");

            // admin排序
            List<String> sortList = new ArrayList<String>();
            // 订单财务排序
            List<String> oldSortList = new ArrayList<String>();
            for (int i = 0; i < iSortingCols; i++) {
                String sSortCol = sColumns[Integer.valueOf(request.getParameter("iSortCol_" + i))];
                String sSortDir = request.getParameter("sSortDir_" + i);

                if (StringUtils.isEmpty(sSortCol) || StringUtils.isEmpty(sSortDir)) {
                    continue;
                }

                sortList.add(sSortCol + " " + sSortDir);
                oldSortList.add(sSortCol + ":" + sSortDir);
            }
            String sortString = StringUtils.join(sortList, ",");
            String oldSortString = StringUtils.join(sortList, ";");

            // 默认是第一页
            int pageNo = 0;
            if (iDisplayStart <= 0) {
                pageNo = 0;
            } else {
                pageNo = iDisplayStart / iDisplayLength;
            }

            int pageSize = iDisplayLength;

            PageableForm form = new PageableForm();
            form.setPageNo(pageNo);
            form.setPageSize(pageSize);
            form.setSortString(sortString);
            form.setOldSortString(oldSortString);
            return form;
        } catch (Exception e) {
            LOG.error("分页参数解析错误，请检查datatable 配置[例如：sName,aaSorting等]", e);
            return null;
        }
    }
}