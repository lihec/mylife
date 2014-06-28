<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript">
    $(function(){
        $('#balance').blur(function(){
            if($('#fixCredit').val()!=''&&$(this).val()!=''){
                $('#availableCredit').val($('#fixCredit').val()-$(this).val());
            }
        });
         $('#availableCredit').blur(function(){
            if($('#fixCredit').val()!=''&&$(this).val()!=''){
                $('#balance').val($('#fixCredit').val()-$(this).val());
            }
        });
    });

function saveCard(){
	/* if($("#addCardForm").valid()){ */
		var postData=$('#addCardForm').serializeArray();
		var url = '';
		<c:if test="${act=='add'}">url='/cardManage/add';</c:if>
		<c:if test="${act=='edit'}">url='/cardManage/${id}/edit';</c:if>
		ofajax({
	        url: url,
	        data: postData,
	        success: function(json) {
	            if(json.result=='ok'){
	            	parent.closeDialog();
	            	parent.frames['mainFrame'].flushPage();
	            }else{
	            	showMessage(json.msg,"error");
	            }
	        }
	    });
	/* } */
}

</script>
<body>
<div class="pop-wrapper">
<form id="addCardForm">
  <c:if test="${act=='edit'}"><input type="hidden" name="id" id="id" value="${id}" /></c:if>
    <div class="block">
        <table class="form">
            <tbody>
            <tr>
                <td class="label-td"><label for="">开户银行：</label></td>
                <td>
                    <input type="text" id="bankName" name="bankName" placeholder="开户银行" value="${cardInfo.bankName}">
                </td>

                <td class="label-td"><label for="">类型：</label></td>
                <td>
                    <select id="type" name="type">
                        <option value="">卡类型</option>
                        <option value="0" <c:if test="${cardInfo.type==0}">selected="selected"</c:if>>储蓄卡</option>
                        <option value="1" <c:if test="${cardInfo.type==1}">selected="selected"</c:if>>信用卡</option>
                        <option value="2" <c:if test="${cardInfo.type==2}">selected="selected"</c:if>>其他</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="label-td"><label for="">卡号：</label></td>
                <td>
                    <input type="text" id="bankNum" name="bankNum" placeholder="卡号" value="${cardInfo.bankNum}">
                </td>
                <td class="label-td"><label for="">姓名：</label></td>
                <td>
                    <input type="text" id="owner" name="owner" placeholder="姓名" value="${cardInfo.owner}">
                </td>
            </tr>
            <tr>
                <td class="label-td"><label for="">网银账号：</label></td>
                <td>
                    <input type="text" id="netName" name="netName" placeholder="网银账号" value="${cardInfo.netName}">
                </td>
                <td class="label-td"><label for="">网银密码：</label></td>
                <td>
                    <input type="text" id="netPwd" name="netPwd" placeholder="网银密码" value="${cardInfo.netPwd}">
                </td>
            </tr>
            <tr>
                <td class="label-td"><label for="">U盾密码：</label></td>
                <td>
                    <input type="text" id="udPwd" name="udPwd" placeholder="U盾密码" value="${cardInfo.udPwd}">
                </td>
                <td class="label-td"><label for="">账单日：</label></td>
                <td>
                    <input type="text" id="billDay" name="billDay" placeholder="账单日" value="${cardInfo.billDay}">
                </td>
            </tr>
            <tr>
                <td class="label-td"><label for="">还款日：</label></td>
                <td>
                    <input type="text" id="payDay" name="payDay" placeholder="还款日" value="${cardInfo.payDay}">
                </td>
                <td class="label-td"><label for="">有效期：</label></td>
                <td>
                    <input type="text" id="validDate" name="validDate" placeholder="有效期" value="${cardInfo.validDate}">
                </td>
            </tr>
            <tr>
                <td class="label-td"><label for="">安全码：</label></td>
                <td>
                    <input type="text" id="safeCode" name="safeCode" placeholder="安全码" value="${cardInfo.safeCode}">
                </td>
                <td class="label-td"><label for="">额度申请：</label></td>
                <td>
                    <input type="text" id="creditAply" name="creditAply" placeholder="额度申请" value="${cardInfo.creditAply}">
                </td>
            </tr>
            <tr>
                <td class="label-td"><label for="">余额：</label></td>
                <td>
                    <input type="text" id="balance" name="balance" placeholder="余额" value="${cardInfo.balance}">
                </td>
                <td class="label-td"><label for="">固定额度：</label></td>
                <td>
                    <input type="text" id="fixCredit" name="fixCredit" placeholder="固定额度" value="${cardInfo.fixCredit}">
                </td>
            </tr>
            <tr>
                <td class="label-td"><label for="">可用额度：</label></td>
                <td>
                    <input type="text" id="availableCredit" name="availableCredit" placeholder="固定额度" value="${cardInfo.availableCredit}">
                </td>
            </tr>
            </tbody></table>

        <div class="form-btn-content">
            <button class="btn btn-primary pushr" onclick="saveCard()" type="button">保存</button>
            <button class="btn" onclick="parent.closeDialog()" type="button">取消</button>
        </div>
    </div>

</form>
</div>
</body>
</html>
