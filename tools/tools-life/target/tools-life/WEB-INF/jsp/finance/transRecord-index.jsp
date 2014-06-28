<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <life:script isQueyPage="yes"/>
    <script type="text/javascript" src="/static/js/My97DatePicker/WdatePicker.js"></script>
    <script>
        $(document).ready(function () {
            loadBind();
            initDataTable();
            initColumn();
        });
        function loadBind() {
            $("#querybtn").click(function () {
                flushPage();
            });
        }
        function initColumn() {
            $('#dataTable').dtColumnManager({
                "listTargetID": "columnsDisplayEdit-ul", "hideInList": [0]
            });
        }
        //初始化表格
        function initDataTable() {
            $('#dataTable').comDataTable({
                "sAjaxSource": "/finance/transRecord/list",
                //"bSort" : false,
                "fnServerData": function (sSource, aoData, fnCallback) {
                    if (!validate()) {
                        return false;
                    }
                    var postData = aoData.concat($('#thisfrm').serializeArray());
                    ofajax({
                        url: sSource,
                        data: postData,
                        success: function (json) {
                            if (json.result == 'ok') {
                                fnCallback(json.data);
                                ChkHelper.setAutoCheckAll("checkall", "chks");
                            } else {
                                showMessage(json.msg, "error");
                            }
                        }
                    });
                },
                "aoColumns": [
                    { "mDataProp": "tid", "sName": "tid"},
                    { "sName": "toptype", "mDataProp": function (aData, type, val) {
                        var type = '';
                        switch (aData.toptype) {
                            case 0:
                                type = '收入';
                                break;
                            case 1:
                                type = '支出';
                                break;
                            case 2:
                                type = '转账';
                                break;
                            case 3:
                                type = '提现';
                                break;
                            default:
                                break;
                        }
                        return type;
                    }
                    },

                    { "mDataProp": "typeName", "sName": "typeName"},
                    { "mDataProp": "fromCard", "sName": "fromCard"},
                    { "mDataProp": "toCard", "sName": "toCard"},
                    { "mDataProp": "money", "sName": "money"},
                    { "mDataProp": "addtime", "sName": "addtime"},
                    { "bSortable": false, "mDataProp": function (aData, type, val) {
                        var html = '<p">';
                        html += '<button class="btn btn-default btn-sm" type="button" onclick="delTrands(' + aData.tid + ')"><span class="glyphicon glyphicon-trash"></span> 删除</button>';
                        html += '</p>';
                        return html;
                    }
                    }
                ],
                "aaSorting": [
                    [5, "asc"]
                ]
            });
        }
        function flushPage() {
            $('#dataTable').refreshData();
        }
        function validate() {
            return true;
        }

        function addTrans() {
            parent.dialogPOP("/finance/transRecord/add", "新增流水", 540, 400);
        }

        function delCard(id) {
            art.dialog({
                content: '您确认删除该银行卡吗？',
                ok: function () {
                    ofajax({
                        url: '/cardManage/' + id + '/remove',
                        data: {},
                        success: function (json) {
                            if (json.result == 'ok') {
                                flushPage();
                            } else {
                                showMessage(json.msg, "error");
                            }
                        }
                    });
                },
                cancel: function () {

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
        &nbsp;&gt;&nbsp; <span style="font-weight: 600;">交易流水</span>
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
                        <span class="add-on">转出账号</span>
                        <span>
                            <input type="text" id="fromCard" name="fromCard" placeholder="转出账号"/>
                        </span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on">转入账号</span>
                        <span>
                            <input type="text" id="toCard" name="toCard" placeholder="转入账号"/>
                        </span>
                    </div>
                </li>
                <li>
                    <select name="type" id="type" class="input-40p">
                        <option value="">收支类型</option>
                        <option value="0">收入</option>
                        <option value="1">支出</option>
                        <option value="2">转账</option>
                        <option value="3">提现</option>
                    </select>
                </li>
            </ul>
            <ul>
                <li>
                    <div class="input-prepend">
                        <span class="add-on">开始时间</span>
                    <span>
                        <input type="text" id="starttime" name="starttime"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime\')||\'%y-%M-%d %H:%m:%s\'}'})"/>
                    </span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on">结束时间</span>
                        <span><input type="text" id="endtime" name="endtime"
                                     onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime\')||\'\#{%y-10}-%M-%d %H:%m:%s\'}'})"/></span>
                    </div>
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
                <a href="javascript:void(0);" onclick="addTrans()" class="btn">新增</a>
            </div>
        </div>

        <table id="dataTable" class="table table-bordered table-striped centered">
            <thead>
            <tr>
                <th>流水号</th>
                <th>收支类型</th>
                <th>交易类型</th>
                <th>转出账号</th>
                <th>转入账号</th>
                <th>交易金额</th>
                <th>交易时间</th>
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