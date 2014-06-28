<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<%@ attribute name="isQueyPage" type="java.lang.String" required="false" description="" %>
<%@ attribute name="isJQplot" type="java.lang.String" required="false" description="" %>
<%@ attribute name="isJQValidation" type="java.lang.String" required="false" description="" %>
<%@ attribute name="isJQcookie" type="java.lang.String" required="false" description="" %>
<%@ attribute name="isZTree" type="java.lang.String" required="false" description="" %>
<%@ attribute name="isTreeTable" type="java.lang.String" required="false" description="" %>
<%@ attribute name="isSecurity" type="java.lang.String" required="false" description="" %>
<%@ attribute name="isHome" type="java.lang.String" required="false" description="" %>

<% if ("yes".equals(isHome)) {%>
<link href="/static/css/life/cp/frame.css" rel="stylesheet">
<%}else{%>
<link href="/static/css/life/cp/page.css" rel="stylesheet">
<%}%>
<%--<link rel="stylesheet" type="text/css" href="<sys:cfg name="staticserver"/>/css/bootstrap/css/bootstrap.min.css"/>--%>
<%--<link rel="stylesheet" type="text/css" href="<sys:cfg name="staticserver"/>/css/bootstrap/css/bootstrap-theme.min.css"/>--%>
<link rel="stylesheet" type="text/css" href="<sys:cfg name="staticserver"/>/js/artdialog/skins/default.css"/>
<%--<link rel="stylesheet" type="text/css" href="<sys:cfg name="staticserver"/>/css/life/frame.css"/>--%>

<script type="text/javascript" src="<sys:cfg name="staticserver"/>/js/jquery/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="<sys:cfg name="staticserver"/>/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="<sys:cfg name="staticserver"/>/css/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<sys:cfg name="staticserver"/>/js/artdialog/jquery.artDialog.js"></script>
<script type="text/javascript" src="/static/js/common/pagecommon.js" charset="UTF-8"></script>
<!--[if lt IE 9]><script src="../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->

<% if ("yes".equals(isQueyPage)) { //是否是数据表格查询页 %>
<link rel="stylesheet" type="text/css" href="<sys:cfg name="staticserver"/>/js/datatable/css/jquery.dataTables.css"/>
<script type="text/javascript" src="<sys:cfg name="staticserver"/>/js/datatable/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<sys:cfg name="staticserver"/>/js/datatable/js/dataTableCommon.js"></script>
<script type="text/javascript" src="<sys:cfg name="staticserver"/>/js/common/checkboxHelper.js"></script>
<script type="text/javascript" src="<sys:cfg name="staticserver"/>/js/common/config-page.js"></script>
<%}%>
<% if ("yes".equals(isJQplot)) { //是否调用图表插件 %>
<link href="<sys:cfg name="staticserver"/>/themes/admin/plugins/jqplot/jquery.jqplot.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<sys:cfg name="staticserver"/>/themes/admin/plugins/jqplot/jquery.jqplot.min.js"></script>
<%}%>
<% if ("yes".equals(isJQValidation)) { //是否调用jQueryvalidation插件 %>
<script type="text/javascript"
        src="<sys:cfg name="staticserver"/>/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript"
        src="<sys:cfg name="staticserver"/>/js/validate/jquery.validate.extends.js"></script>

<%}%>
<% if ("yes".equals(isJQcookie)) { //是否调用cookie插件 %>
<script type="text/javascript" src="<sys:cfg name="staticserver"/>/js/jquery/jquery.cookie.js"></script>
<%}%>
<% if ("yes".equals(isZTree)) { //是否调用zTree树插件 %>
<link rel="stylesheet" type="text/css"
      href="<sys:cfg name="staticserver"/>/js/ztree/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript"
        src="<sys:cfg name="staticserver"/>/js/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript"
        src="<sys:cfg name="staticserver"/>/js/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript"
        src="<sys:cfg name="staticserver"/>/js/ztree/js/jquery.ztree.exedit-3.5.min.js"></script>
<%}%>
<% if ("yes".equals(isTreeTable)) { //是否调用treeTable树表插件 %>
<link rel="stylesheet" type="text/css"
      href="<sys:cfg name="staticserver"/>/js/treetable/jquery.treetable.css"/>
<script type="text/javascript"
        src="<sys:cfg name="staticserver"/>/js/treetable/jquery.treeTable.js"></script>
<script type="text/javascript"
        src="<sys:cfg name="staticserver"/>/js/treetable/jquery.treetable-ajax-persist.js"></script>
<script type="text/javascript"
        src="<sys:cfg name="staticserver"/>/js/treetable/persist-min.js"></script>
<%}%>
<% if ("yes".equals(isSecurity)) { //是否调用页面加密插件 %>
<script type="text/javascript" src="<sys:cfg name="staticserver"/>/js/common/security.js"></script>
<%}%>