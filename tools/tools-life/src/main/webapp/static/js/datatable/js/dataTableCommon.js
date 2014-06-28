//常用项配置和汉化
$.fn.comDataTable=function (init){
	var sStateKey = init.sStateKey;
	var oInit={
		"bDestroy":true,
    	"sDom": 'rt<"bottom"lip>', // 元素布局
	    "bPaginate":true, //翻页功能
		"bLengthChange":true, //改变每页显示数据数量
		"bFilter": false, //过滤功能
		"bSort": true, //排序功能
		"bInfo":true,//页脚信息
		"bAutoWidth":false,//自动宽度
		"bStateSave": false,//保存条件等状态在cookie里
		"fnStateLoadParams": function (oSettings, oData) {
			//总是从第一页开始
		    oData.iStart = 0; 
		    //不读取搜索相关的
		    oData.oSearch = "";
		    oData.aoSearchCols = "";
		    oData.aaSorting = init.aaSorting;
		} ,
		"fnStateLoad" : function(oSettings, oData) {
			var o;
			if (typeof (sStateKey) != 'undefined' && sStateKey.length > 0) {
				$.ajax({
					url : '/dataTable/stateLoad',
					data : {sStateKey : sStateKey},
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					timeout : 1000,
					success : function(json) {
						if (json.result == 'ok' && json.data != null && json.data != '') {
							o = json.data;
						}
					}
				});
			}
			return o;
		}, 
		"fnStateSaveParams": function (oSettings, oData) {
			if (typeof (sStateKey) != 'undefined' && sStateKey.length > 0) {
				oData.sStateKey = sStateKey;
			    //不保存搜索相关的
			    oData.oSearch = "";
			    oData.aoSearchCols = "";
			    oData.aaSorting = init.aaSorting;
			}
		},
		"fnStateSave" : function(oSettings, oData) {
			if (typeof (sStateKey) != 'undefined' && sStateKey.length > 0) {
				$.ajax({
					url : '/dataTable/stateSave',
					"data": 'data=' + JSON.stringify(oData),
					type : 'POST',
					dataType : 'json',
					cache : false,
					timeout : 3000,
					success : function(json) {
						
					}
				});
			}
		},
        "oLanguage": {
			"sLengthMenu": "每页显示 _MENU_ 条",
			"sZeroRecords": "请选择条件后，按搜索按钮开始查询",
			"sProcessing": "正在查询...",
			"sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
			"sInfoEmpty": "没有符合条件的记录",
			"sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
			"sSearch": "搜索：",
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "上一页",
				"sNext": "下一页",
				"sLast": "尾页"
			}
		},
	    "sPaginationType": "full_numbers",
	    "aLengthMenu": [[10, 20, 30], [10, 20, 30]],
		 "bProcessing": true,
	 	 "bServerSide": true
	};
	$.extend(true,oInit,init);
	$(this).dataTable(oInit);	
};

/**
 * 指定刷新到某页(不传参数默认为首页，last,next,preview)
 */
$.fn.refreshData=function(index){
	var oTable = $(this).dataTable();
  	oTable.fnPageChange(index||"first");
};

/**
 * 获取当前分页状态
 */
$.fn.pagination=function(){
	var config = {};
	var oTable = $(this).dataTable();
	var settings = oTable.dataTableSettings[0];
	var start = settings._iDisplayStart;
	config.pageIndex =  Math.ceil(start / settings._iDisplayLength);
	config.pageSize = settings._iDisplayLength;
	config.pageStart = settings._iDisplayStart;
	return config;
};

/**
 * 获取当前设置
 */
$.fn.settings=function(){
	var oTable = $(this).dataTable();
	return oTable.dataTableSettings[0];
};

/**
 * 刷新当前页
 */
$.fn.refreshCurrentData=function(length){
	length = (typeof length == "number"?length:0);
	var oTable = $(this).dataTable();
	var settings = oTable.dataTableSettings[0];
	var total = settings._iRecordsTotal;
	var start = settings._iDisplayStart;
	if(start >= total-length){
		start = total-length-settings._iDisplayLength;
	}
	var pageIndex= Math.ceil(start / settings._iDisplayLength);
	oTable.fnPageChange(pageIndex);
};

/**
 * 获取总页数
 * @returns {number}
 */
$.fn.totalPages = function(oSettings) {
    return  oSettings._iDisplayLength === -1 ?
        0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength );
}

//控制dataTable列的显示和隐藏
$.fn.dtColumnManager=function(init){
	var oTable = $(this).dataTable();
	var tableId = $(this).attr("id");
	var  showid=init.listTargetID;
	var hideInList=init.hideInList;
	var colList='';
	var settings=oTable.fnSettings().aoColumns;
	$(settings).each(function(index,element){
		if($.inArray(index,hideInList)==-1){
			colList+='<li><a href="javascript:void(0)">';
			if(element.bVisible)
				colList+='<input id="colum_'+index+'" type="checkbox" class="input-check" checked="checked" onclick="fnShowHide(\''+tableId+'\','+index+')"/> ';
			else
				colList+='<input  id="colum_'+index+'" type="checkbox" class="input-check" onclick="fnShowHide(\''+tableId+'\','+index+')"/> ';
			colList+='<label for="colum_'+index+'">'+element.sTitle+'</label>';
			colList+='</a></li>';
		}
	});
	if($('#'+showid)){
		$('#'+showid).append(colList);
	}
};
//dataTable列的显示和隐藏切换实现
var fnShowHide = function (tableId, iCol ) {
    var oTable = $('#'+tableId).dataTable();
    var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
    oTable.fnSetColumnVis( iCol, bVis ? false : true );
};
