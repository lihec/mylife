<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <life:script isZTree="yes"/>
    <title>收支类型管理</title>
    <style type="text/css">
        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
        	$('.table-toolbar').on('click', 'a', function() {
		    	$('#payType').val($(this).attr("payType"));
		    	reloadzTree();
		    });
        });
        //根据类目模板列表index重载树
        function reloadzTree() {
        	var payType = $('#payType').val();
        	var pname = '';
        	if(payType=='0'){
        		pname = '收入';
        	}else if(payType=='1'){
        		pname = '支出';
        	}else if(payType=='2'){
        		pname = '转账';
            }else if(payType=='3'){
        		pname = '提现';
        	}
            var setting = {
                check: { enable: false },
                edit: { enable: false },
                data: {
                    key: { name: "pname", childs: "childs", title: "pname" },
                    simpleData: { enable: true, idKey: "pid", pIdKey: "topPid", rootPId: "0" }
                },
                view: { addHoverDom: addHoverDom, removeHoverDom: removeHoverDom }
                //callback: { beforeClick: beforeClick, onClick: onClick }
            };
            ofajax({
                url: "/finance/pamentType",
                data: {ptype : payType},
                success: function (json) {
                    var zTreeNodes = json.data.payTypeList;
                    if (zTreeNodes == null || zTreeNodes.length == 0) {
                        zTreeNodes = new Array();
                    }
                    zTreeNodes.push({"pid": "0", "open": "true", "topPid": null, "pname": pname, "ptype": payType});
                    if (json.result == 'ok') {
                        $.fn.zTree.init($("#zTree_ul"), setting, zTreeNodes);
                    } else {
                        showMessage("处理失败，原因是：" + json.msg, "error");
                    }
                }
            });
        }
        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.pid).length > 0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.pid + "' title='新增' onfocus='this.blur();'></span>";
            sObj.append(addStr);
            var btn = $("#addBtn_" + treeNode.pid);
            if (btn) btn.bind("click", function () {
                addPayType(treeNode.pid, treeNode.topPid, treeNode.ptype);
            });
			if(treeNode.pid==0){
				//根目录只有新增按钮
            	return;
            }
            if (treeNode.editNameFlag || $("#editBtn_" + treeNode.pid).length > 0) return;
            var editStr = "<span class='button edit' id='editBtn_" + treeNode.pid + "' title='修改' onfocus='this.blur();'></span>";
            sObj.append(editStr);
            var editBtn = $("#editBtn_" + treeNode.pid);
            if (editBtn) {
                editBtn.bind("click", function () {
                    editPayType(treeNode.pid, treeNode.topPid, "0");
                });
            }
            if (treeNode.editNameFlag || $("#removeBtn_" + treeNode.pid).length > 0) return;
            var removeStr = "<span class='button remove' id='removeBtn_" + treeNode.pid + "' title='删除' onfocus='this.blur();'></span>";
            sObj.append(removeStr);
            var removeBtn = $("#removeBtn_" + treeNode.pid);
            if (removeBtn) {
                removeBtn.bind("click", function () {
                    removePayType(treeNode.pid, treeNode.topPid, treeNode.ptype);
                });
            }
        }
        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_" + treeNode.pid).unbind().remove();
            if(treeNode.pid==0){
				//根目录只有新增按钮
            	return;
            }
            $("#editBtn_" + treeNode.pid).unbind().remove();
            $("#removeBtn_" + treeNode.pid).unbind().remove();
        }
        //新增分类
        function addPayType(pid, topPid, type) {
            var url = "/finance/pamentType/add?topPid=" + pid+"&type="+type;
            parent.dialogPOP(url, "新增分类", 500, 300);
        }
        //删除分类
        function removePayType(pid, topPid, type) {
            art.dialog({
                content: '您确定要删除这一项吗？',
                ok: function () {
                    ofajax({
                        url: "/finance/pamentType/"+pid+"/remove",
                        data: {},
                        success: function (json) {
                            if (json.result == 'ok') {
                            	 reloadzTree();
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
        //编辑分类
        function editPayType(pid, topPid, type) {
            var url = "/finance/pamentType/" + pid + "/edit";
            parent.dialogPOP(url, "编辑分类", 500, 300);
        }
    </script>
</head>
<body>
<%--<div class="btn-group" id="payGroup">--%>
  <%--<button type="button" class="btn btn-default" payType="0">收入</button>--%>
  <%--<button type="button" class="btn btn-default" payType="1">支出</button>--%>
  <%--<button type="button" class="btn btn-default" payType="2">转账</button>--%>
  <%--<button type="button" class="btn btn-default" payType="3">提现</button>--%>
  <%--<input type="hidden" id="payType"/>--%>
<%--</div>--%>

<div class="table-toolbar push">
    <div class="left">
        <a href="javascript:void(0);" payType="0" class="btn">收入</a>
        <a href="javascript:void(0);" payType="1" class="btn">支出</a>
        <a href="javascript:void(0);" payType="2" class="btn">转账</a>
        <a href="javascript:void(0);" payType="3" class="btn">提现</a>
        <input type="hidden" id="payType"/>
    </div>
</div>

<div class="container">
    <ul id="zTree_ul" class="ztree" ></ul>
</div>
</body>
</html>