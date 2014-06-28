<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript">
$(function(){
    <c:if test="${act=='add'}">
        $('select[name=ptype]>option[value=${param.type}]').prop('selected',true);
    </c:if>
});
function savePaymentType(){
    var postData=$('#paymentTypeForm').serializeArray();
    var url = '';
    <c:if test="${act=='add'}">url='/finance/pamentType/add';</c:if>
    <c:if test="${act=='edit'}">url='/finance/pamentType/${pid}/edit';</c:if>
    ofajax({
        url: url,
        data: postData,
        success: function(json) {
            if(json.result=='ok'){
                parent.closeDialog();
                parent.frames['mainFrame'].reloadzTree();
            }else{
                showMessage(json.msg,"error");
            }
        }
    });
}

</script>
<div class="pop-wrapper pop-wrapper-460">
    <form id="paymentTypeForm">
        <c:if test="${act=='add'}"><input type="hidden" name="topPid" id="topPid" value="${param.topPid}"/></c:if>
        <div class="block">
            <table class="form">
                <tbody>
                <tr>
                    <td class="label-td"><label for="">名称：</label></td>
                    <td>
                        <input type="text" id="pname" name="pname" placeholder="名称" value="${paymentTypeInfo.pname}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label-td"><label for="">类型：</label></td>
                    <td>
                        <select id="ptype" name="ptype">
                            <option value="">类型</option>
                            <option value="0" <c:if test="${paymentTypeInfo.ptype==0}">selected="selected"</c:if>>收入</option>
                            <option value="1" <c:if test="${paymentTypeInfo.ptype==1}">selected="selected"</c:if>>支出</option>
                            <option value="2" <c:if test="${paymentTypeInfo.ptype==2}">selected="selected"</c:if>>转账</option>
                            <option value="3" <c:if test="${paymentTypeInfo.ptype==3}">selected="selected"</c:if>>提现</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="label-td"><label for="">说明：</label></td>
                    <td>
                        <input type="text" id="mark" name="mark" placeholder="说明" value="${paymentTypeInfo.mark}"/>
                    </td>
                </tr>

                </tbody></table>

            <div class="form-btn-content">
                <button class="btn btn-primary pushr" onclick="savePaymentType()" type="button">保存</button>
                <button class="btn" onclick="parent.closeDialog()" type="button">取消</button>
            </div>
        </div>

    </form>
</div>
</html>
