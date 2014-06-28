<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>银行卡管理</title>
<life:script isQueyPage="yes"/>
<script>
$(document).ready(function(){
	loadBind();
	initDataTable();
	initColumn();
});
function loadBind(){
	$("#querybtn").click(function(){flushPage();});
}
function initColumn(){
	$('#dataTable').dtColumnManager({
    	"listTargetID":"columnsDisplayEdit-ul","hideInList":[0]
    });
}
//初始化表格
function initDataTable(){
	$('#dataTable').comDataTable({
    	"sAjaxSource": "/cardManage/list",
    	//"bSort" : false,
	    "fnServerData": function( sSource, aoData, fnCallback ) {
			if(!validate()){
				return false;
			}
		    var postData=aoData.concat($('#thisfrm').serializeArray());
		    ofajax({
		        url: sSource,
		        data: postData,
		        success: function(json) {
		            if(json.result=='ok'){
		            	fnCallback(json.data); 
		            	ChkHelper.setAutoCheckAll("checkall","chks");
		            }else{
		            	showMessage(json.msg,"error");
		            }
		        }
		    });
		},
	    "aoColumns": [
			{ "mDataProp": "bankName" ,"sName":"bankName"},
			{ "sName":"type","mDataProp": function ( aData, type, val ) {
				var type ='';
			    switch (aData.type) {
				case 0:
					type='储蓄卡';
					break;
				case 1:
					type='信用卡';
					break;
				case 2:
					type='其他';
					break;
				default:
					break;
				}
				return type; }
			},
			{ "mDataProp": "owner" ,"sName":"owner"},
			{ "mDataProp": "bankNum" ,"sName":"bankNum"},
			{ "mDataProp": "netName" ,"sName":"netName"},
			{ "mDataProp": "netPwd" ,"sName":"netPwd","bVisible":false},
			{ "mDataProp": "udPwd" ,"sName":"udPwd","bVisible":false},
			{ "mDataProp": "billDay" ,"sName":"billDay"},
			{ "mDataProp": "payDay" ,"sName":"payDay"},
			{ "mDataProp": "validDate" ,"sName":"validDate"},
			{ "mDataProp": "safeCode" ,"sName":"safeCode","bVisible":false},
			{ "mDataProp": "creditAply" ,"sName":"creditAply","bVisible":false},
			{ "sName":"balance","mDataProp": function ( aData, type, val ) {
                var balance = '';
                switch (aData.type){
                    case 0:
                        balance = '<span style="color:green">'+aData.balance+'</span>';
                        break;
                    case 1:
                        balance = '<span style="color:red">'+aData.balance+'</span>';
                        break;
                    case 2:
                        balance = '<span style="color:'+(aData.balance>=0?'green':'red')+'">'+aData.balance+'</span>';
                }
				return balance; }
			},
			{ "mDataProp": "fixCredit" ,"sName":"fixCredit"},
			{ "mDataProp": "availableCredit" ,"sName":"availableCredit"},
			{ "bSortable": false,"mDataProp": function ( aData, type, val ) {
				var html = '<p">';
				html+='<button class="btn btn-default btn-sm" type="button" onclick="editCard('+aData.id+')"><span class="glyphicon glyphicon-pencil"></span> 编辑</button>&nbsp;';
				html+='<button class="btn btn-default btn-sm" type="button" onclick="delCard('+aData.id+')"><span class="glyphicon glyphicon-trash"></span> 删除</button>';
				html+='</p>';
				return html; }
			}
	    ],
	    "aaSorting": [[0,"asc"]]
    });
}
function flushPage(){
    var pageno = parseInt($.trim($("div#dataTable_paginate span a.paginate_active").text())) - 1;
    $("#dataTable").dataTable().fnPageChange(pageno||0);
}
function validate(){
	return true;
}

function addCard() {
    parent.dialogPOP("/cardManage/add", "新增银行卡", 550, 450);
}

function editCard(id) {
    parent.dialogPOP("/cardManage/"+id+"/edit", "编辑银行卡", 550, 450);
}

function delCard(id){
	art.dialog({
		content : '您确认删除该银行卡吗？',
	    ok: function () {
	    	ofajax({
	            url: '/cardManage/'+id+'/remove',
	            data: {},
	            success: function(json) {
	                if(json.result=='ok'){
	                	flushPage();
	                }else{
	                	showMessage(json.msg,"error");
	                }
	            }
	        });
	    },
		cancel : function(){
			
		}
	});
}
</script>
</head>

<body>
<div class="page-wrapper">

    <div class="breadCrumb">
        <a href="javascript:parent.location.reload();"> <i class="icon icon-home icon-orange"></i> 首页</a>
        &nbsp;&gt;&nbsp; <span>财务管理</span>
        &nbsp;&gt;&nbsp; <span style="font-weight: 600;">银行卡管理</span>
    </div>

    <form id="thisfrm">
    <div class="search-form">
        <div class="trigger-content">
            <a href="javascript:void(0)"
               class="btn btn-primary" id="querybtn">搜索</a>
        </div>
        <ul>
            <li>
                <div class="input-prepend">
                    <span class="add-on">开户银行</span>
                    <span>
                        <input type="text" class="form-control" id="bankName" name="bankName" placeholder="开户银行">
                        </span>
                </div>
            </li>
            <li>
                <div class="input-prepend">
                    <span class="add-on">卡号</span>
                    <span><input type="text" class="form-control" id="bankNum" name="bankNum" placeholder="卡号"></span>
                </div>
            </li>
            <li>
                <select name="type" id="type" class="input-40p">
                    <option value="">卡类型</option>
                    <option value="0">储蓄卡</option>
                    <option value="1">信用卡</option>
                    <option value="2">其他</option>
                </select>
            </li>
        </ul>
    </div>

    <div class="table-toolbar pusht">
        <div class="btn-group right toggle-content">
            <a href="#" class="btn">更改列显示</a>
            <a href="#" class="btn dropdown-toggle-link"><i class="icon icon-chevron-down"></i></a>
            <ul class="dropdown-menu" style="display: none;" id="columnsDisplayEdit-ul"></ul>
        </div>
        <div class="left">
            <a href="javascript:void(0);" onclick="addCard()" class="btn">新增</a>
        </div>
    </div>

    <table id="dataTable" class="table table-bordered table-striped centered">
        <thead>
        <tr>
            <th>开户银行</th>
            <th>卡类型</th>
            <th>姓名</th>
            <th>卡号</th>
            <th>网银账户</th>
            <th>网银密码</th>
            <th>U盾密码</th>
            <th>账单日</th>
            <th>还款日</th>
            <th>有效期</th>
            <th>安全码</th>
            <th>额度申请</th>
            <th>余额或欠款</th>
            <th>固定额度</th>
            <th>可用额度</th>
            <th width="158px;">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    </form>

</div>
</body>
</html>