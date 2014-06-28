<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript">
$(document).ready(function(){
	$('ul.horizonal').on('click', 'input[type=radio]', function() {
    	var ptype = $(this).val();
    	if(ptype=='0'||ptype=='1'){
    		//收入
    		$('#toCard_tr').hide();
    		$('#withdrawFee_tr').hide();
            $('#type_tr').show();
            getPayType(ptype);
    	}else if(ptype=='2'){
    		//支出
    		$('#toCard_tr').show();
            $('#withdrawFee_tr').hide();
            $('#type_tr').hide();
    	}else if(ptype=='3'){
    		//转账
            $('#toCard_tr').show();
            $('#withdrawFee_tr').show();
            $('#type_tr').hide();
    	}
    	//获取交易类型
    	if(true){
    		return;
    	}
    	ofajax({
	        url: '',
	        data: {},
	        success: function(json) {
	            if(json.result=='ok'){
	            	
	            }else{
	            	showMessage(json.msg,"error");
	            }
	        }
	    });
    });
});

function getPayType(payType){
    ofajax({
        url: "/finance/pamentTypeChildren",
        data: {ptype : payType},
        success: function (json) {
            if (json.result == 'ok') {
                var payTypeList = json.data.payTypeList;
                selectHtml='';
                getSelectHtml(payTypeList);
                $('#type').html(selectHtml);
            } else {
                showMessage("处理失败，原因是：" + json.msg, "error");
            }
        }
    });
}

function getSelectHtml(payTypeList,isChild){
    var tab = isChild?'&nbsp;&nbsp;&nbsp;&nbsp;':'';
    $.each(payTypeList,function(index,elm){
        selectHtml+='<option value="'+elm.pid+'">'+tab+elm.pname+'</option>';
        if(elm.childPayments!=null&&elm.childPayments.length>0){
            getSelectHtml(elm.childPayments,true);
        }
    });
    return selectHtml;
}


function saveTransRecord(){
	/* if($("#addCardForm").valid()){ */
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
	/* } */
}

</script>

<div class="pop-wrapper pop-wrapper-540">
    <form id="transForm">
        <c:if test="${act=='edit'}"><input type="hidden" name="id" id="id" value="${id}" /></c:if>
        <div class="block">
            <table class="form">
                <tbody>
                <tr>
                    <td class="label-td">&nbsp;&nbsp;</td>
                    <td>
                        <ul class="horizonal" data-role="ptype">
                            <li><input type="radio" name="ptype" id="ptype0" value="0" class="input-check" /><label for="ptype0" class="inline-label">收入</label></li>
                            <li><input type="radio" name="ptype" id="ptype1" value="1" class="input-check" /><label for="ptype1" class="inline-label">支出</label></li>
                            <li><input type="radio" name="ptype" id="ptype2" value="2" class="input-check" /><label for="ptype2" class="inline-label">转账</label></li>
                            <li><input type="radio" name="ptype" id="ptype3" value="3" class="input-check" /><label for="ptype3" class="inline-label">提现</label></li>
                        </ul>
                    </td>
                </tr>
                <tr id="type_tr">
                    <td class="label-td"><label for="">交易类型：</label></td>
                    <td>
                        <select name="type" id="type">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="label-td"><label for="">账号：</label></td>
                    <td>
                        <select name="fromCard" id="fromCard"></select>
                    </td>
                </tr>
                <tr id="toCard_tr" style="display: none">
                    <td class="label-td"><label for="">转入账号：</label></td>
                    <td>
                        <select name="toCard" id="toCard"></select>
                    </td>
                </tr>
                <tr>
                    <td class="label-td"><label for="">金额：</label></td>
                    <td>
                        <input type="text" id="money" name="money" placeholder="金额">
                    </td>
                </tr>
                <tr id="withdrawFee_tr" style="display: none">
                    <td class="label-td"><label for="">提现手续费：</label></td>
                    <td id="withdrawFee_td">

                    </td>
                </tr>
                <tr>
                    <td class="label-td"><label for="">时间：</label></td>
                    <td>
                        <input type="text" id="addtime" name="addtime" placeholder="时间">
                    </td>
                </tr>
                <tr>
                    <td class="label-td"><label for="">备注：</label></td>
                    <td>
                        <input type="text" id="mark" name="mark" placeholder="备注">
                    </td>
                </tr>

                </tbody></table>

            <div class="form-btn-content">
                <button class="btn btn-primary pushr" onclick="saveTransRecord()" type="button">保存</button>
                <button class="btn" onclick="parent.closeDialog()" type="button">取消</button>
            </div>
        </div>

    </form>
</div>

</html>
