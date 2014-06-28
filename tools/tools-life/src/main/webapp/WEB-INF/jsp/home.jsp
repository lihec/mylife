<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理后台</title>
<%--<link href="/static/css/life/home.css" rel="stylesheet">--%>
<%--<link href="/static/css/life/cp/default.css" rel="stylesheet">--%>
<%--<link href="/static/css/life/cp/frame.css" rel="stylesheet">--%>


<life:script isHome="yes" />
<script type="text/javascript" src="/static/js/common/ui-frame.js"></script>
<script>
	$(document).ready(function() {
		/*
		$('#loginForm').validate();
		$("#loginBtn").click(function() {
			var checked = $("#remember").prop("checked");
			if (checked) {
				$("remember").val('1');
			}
			if ($('#loginForm').valid()) {
				$('#loginForm').submit();
			}
		});
		$('#loginForm').ofajaxForm({
			url : '/login',
			success : function(json) {
				if (json.result == 'ok') {
					//window.location.href='/home';
				} else {
					showMessage(json.msg, 'error');
				}
			}
		});
*/
        pageView();
	});

    //页面展示效果
    function pageView() {
        $("#mainFrame,.uiCol,.spanfix .jq-trigger,.sidenav-wrapper").css("height", $(window).height() - $("#header").height() - 11);
        $(window).resize(function () {
            $("#mainFrame,.uiCol,.spanfix .jq-trigger,.sidenav-wrapper").css("height", $(window).height() - $("#header").height() - 11);
        });
    }

	function unfoldMenu(furl,self) {
        $(".jq-trigger").parentsUntil("spanfix").removeClass("shrinked").prev(".spanfluid").removeClass("extended");
        $("#mainMenu li").removeClass("current");
        $(self).parent().addClass("current");
        var url = '/sideColumn?url='+ furl + '&r=' + Math.round(999999 * Math.random());
        $('#sideColumn').load(url, function () {
            //左侧菜单点击效果
            if ($(".spanfix .nav li ul a").length > 0) {
                $(".spanfix .nav li ul a").click(function () {
                    $(".spanfix .nav li ul li").removeClass("current");
                    $(this).parent("li").addClass("current");
                });
            }
            //包含子菜单项
            $("ul.vertical-nav li ul li").removeClass("current");
        });

    }


</script>
</head>

<body>
<div id="header">
    <div class="module">
        <h1 id="logo"><a title="生活管理" href=""><img alt="" src=""/></a></h1>
            <span class="label label-info version-info">
				<a href="#">生活管理</a>
            </span>
        <!--顶部主导航-->
        <ul class="nav horizonal-nav" id="mainNav">
            <li><a href="javascript:void(0)" onclick="unfoldMenu('money',this)"><span>财务管理</span></a></li>
            <li><a href="javascript:void(0)" onclick="unfoldMenu('learn',this)"><span>学习</span></a></li>
        </ul>
        <!--功能按钮-上部-->
        <ul class="funcLink-list pos-top nav horizonal-nav">
            <li><a href="javascript:unfoldMenu('/setting')"><i class="icon icon-white icon-cog"></i>设置</a></li>
            <li><a href="/logout"><i class="icon icon-white icon-off"></i>退出</a></li>
        </ul>
        <!--用户信息-下部-->
        <ul class="funcLink-list pos-bottom nav horizonal-nav">
            <li>lih (unc)</li>
        </ul>
    </div>
</div>
<div id="container" class="row-fixSide">
    <div class="span-5 spanfluid">
        <div class="uiCol">
            <iframe width="100%" scrolling="auto" frameborder="0" id="mainFrame" name="mainFrame" src=""></iframe>
        </div>
    </div>
    <div class="span5 spanfix">
        <div class="uiCol">
            <a href="javascript:void(0)" class="jq-trigger">&nbsp;</a><!-- 收缩栏 -->
            <div id="sideColumn">
                <div class="sidenav-wrapper"></div>
                <!-- 左侧菜单 --></div>
        </div>
    </div>
</div>




<%--<div id="wrap">--%>
<%--<!-- Fixed navbar -->--%>
    <%--<div class="navbar navbar-default navbar-fixed-top" role="navigation">--%>
      <%--<div class="container">--%>
        <%--<div class="navbar-header">--%>
         <%--<!--  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">--%>
            <%--<span class="sr-only">Toggle navigation</span>--%>
            <%--<span class="icon-bar"></span>--%>
            <%--<span class="icon-bar"></span>--%>
            <%--<span class="icon-bar"></span>--%>
          <%--</button> -->--%>
          <%--<a class="navbar-brand" href="javascript:void(0)">Mylife</a>--%>
        <%--</div>--%>
        <%--<div class="navbar-collapse collapse">--%>
          <%--<ul class="nav navbar-nav" id="mainMenu">--%>
            <%--<li><a href="javascript:void(0)" onclick="unfoldMenu('money',this)">财务管理</a></li>--%>
            <%--<li><a href="javascript:void(0)" onclick="unfoldMenu('learn',this)">学习</a></li>--%>
            <%--<li class="dropdown">--%>
              <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">实用工具 <b class="caret"></b></a>--%>
              <%--<ul class="dropdown-menu">--%>
                <%--<li><a href="#">Action</a></li>--%>
                <%--<li><a href="#">Another action</a></li>--%>
                <%--<li><a href="#">Something else here</a></li>--%>
                <%--<li class="divider"></li>--%>
                <%--<li class="dropdown-header">Nav header</li>--%>
                <%--<li><a href="#">Separated link</a></li>--%>
                <%--<li><a href="#">One more separated link</a></li>--%>
              <%--</ul>--%>
            <%--</li>--%>
          <%--</ul>--%>
          <%--<ul class="nav navbar-nav navbar-right">--%>
            <%--<li><a href="javascript:void(0)">李贺</a></li>--%>
            <%--<li><a href="../navbar-static-top/">退出</a></li>--%>
            <%--<!-- <li class="active"><a href="./">Fixed top</a></li> -->--%>
          <%--</ul>--%>
        <%--</div><!--/.nav-collapse -->--%>
      <%--</div>--%>
    <%--</div>--%>

	<%--<div class="container" style="width: 100%">--%>
		<%--<div class="row">--%>
			<%--<div class="col-md-2">--%>
				<%--<div role="complementary" class="bs-sidebar hidden-print affix-top">--%>
				<%--<div class="list-group" id="sideColumn">--%>
				  <%--<!-- <a href="/cardManage" class="list-group-item" target="mainFrame">银行卡管理</a>--%>
				  <%--<a href="#" class="list-group-item">财务流水</a>--%>
				  <%--<a href="#" class="list-group-item">财务分析</a> -->--%>
				<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<div class="col-md-10">--%>
				<%--<iframe width="100%" height="800px;" scrolling="auto" frameborder="0" id="mainFrame" name="mainFrame" src="">--%>
				<%----%>
				<%--</iframe>--%>
			<%--</div>--%>
		<%--</div>--%>
	<%--</div>--%>
	<%----%>
	<%--</div>--%>
	<%----%>
	<%--<div id="footer">--%>
      <%--<div class="container">--%>
        <%--<p class="text-muted">个人开发使用，如有bug请高抬贵手，联系13776607509@163.com，谢谢合作！</p>--%>
      <%--</div>--%>
    <%--</div>--%>

</body>
<script type="text/javascript">
var dialog;
//弹层方法
function dialogPOP(url, title1, width1, height1) {
//    dialog = art.dialog.open(url);

    dialog = art.dialog({
        title: title1,
        width: width1,
        height: height1,
//        padding:'250px 255px',
        lock: true,
        drag: true,
        resize: true,
        background: '#000', // 背景色
        opacity: 0.67	// 透明度
    });
    $.ajax({
        url: url,
        dataType: 'html',
        success: function (data) {
            dialog.content(data);
        },
        cache: false
    });
}
//关闭弹层
function closeDialog() {
    try {
        if (timer) {
            clearInterval(timer);
        }
    } catch (err) {
    }
    if (dialog) {
        dialog.close();
    }
}
//重新载入弹层内容页面
function reLoadDialog(url, title, width, height) {
    try {
        if (timer) {
            //清理安全中心弹层所产生的timer
            clearInterval(timer);
        }
    } catch (err) {
    }
    ;

    if (title) {
        dialog.title(title);
    }
    if (width && height) {
        dialog.size(width, height);
        var left = (document.body.clientWidth - width) / 2;
        var top = (document.body.clientHeight - height) / 2;
        dialog.position(left, top);
    }
    $.ajax({
        url: url,
        dataType: 'html',
        cache: false,
        success: function (data) {
            dialog.content(data);
        }
    });
}
</script>
</html>