<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<%@ attribute name="startText" type="java.lang.String" required="false" description="自定义开始文本" %>
<%@ attribute name="startTime" type="java.lang.String" required="false" description="初始化开始时间" %>
<%@ attribute name="endText" type="java.lang.String" required="false" description="自定义结束文本" %>
<%@ attribute name="endTime" type="java.lang.String" required="false" description="初始化结束时间" %>

<%
if(startTime == null){
    startTime = "";
}
if(endTime == null){
    endTime = "";
}
%>

<script type="text/javascript" src="<sys:cfg name="staticserver"/>/themes/admin/plugins/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(function(){   
	$("#startTime").focus(function(){
        WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});
    }).val('${startTime}');
    $("#endTime").focus(function(){
        WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});
    }).val('${endTime}');
});
</script>
<li>
    <div class="input-prepend">
        <span class="add-on">
            <c:choose>
                <c:when test="${empty startText}">起始</c:when>
                <c:otherwise>${startText}</c:otherwise>
            </c:choose>
        </span>
        <span><input type="text" name="startTime" id="startTime" 
                class="placeholderB tips input-60p input-text" value="${startTime}" /></span>
    </div>
    -
    <div class="input-prepend">
        <span class="add-on">
            <c:choose>
                <c:when test="${empty endText}">结束</c:when>
                <c:otherwise>${endText}</c:otherwise>
            </c:choose>
        </span>
        <span><input type="text" name="endTime" id="endTime" 
            class="placeholderB tips input-60p input-text"  value="${endTime}" /></span>
    </div>
</li>
