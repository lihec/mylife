<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<c:choose>
	<c:when test="${param.url eq 'money'}">
        <ul class="nav vertical-nav">
            <li>
                <h3>财务管理</h3>
                <ul>
                     <li><a href="/cardManage" target="mainFrame">银行卡管理</a></li>
                     <li><a href="/finance" target="mainFrame">资产负债</a></li>
                     <li><a href="/finance/pamentType" target="mainFrame">收支类型</a></li>
                     <li><a href="/finance/transRecord" target="mainFrame">交易流水</a></li>
                </ul>
            </li>
        </ul>
	</c:when>
	
	<c:when test="${param.url eq 'learn'}">
        <ul class="nav vertical-nav">
            <li>
                <h3>学习</h3>
                <ul>
                    <li><a href="#" target="mainFrame">java相关</a></li>
                    <li><a href="#" target="mainFrame">javascript相关</a></li>
                    <li><a href="#" target="mainFrame">sql相关</a></li>
                </ul>
            </li>
        </ul>
	</c:when>
	<c:otherwise>
		<a href="#" class="list-group-item" target="mainFrame">菜单1</a>
	    <a href="#" class="list-group-item">菜单2</a>
	    <a href="#" class="list-group-item">菜单3</a>
	</c:otherwise>
</c:choose>