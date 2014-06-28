// Ajax动态加载内容
    function myload(id,url){
    $(function() { 
        $("#"+id).load(url); 
    });
    };

$(document).ready(function(){
	//$('.dropdown-toggle').dropdown();
	$("#mainFrame,.uiCol,.spanfix .jq-trigger").css("height",$(window).height() - 93);
	$(".jq-trigger").click(
		function(){
			$(this).parent(".uiCol").parent(".spanfix").toggleClass("shrinked").prev(".spanfluid").toggleClass("extended");}
		);
	$(".spanfix .nav li ul a").click(function(){
		$(".spanfix .nav li ul li").removeClass("current");
		$(this).parent("li").addClass("current");});


    $("#mainNav li").click(function(){
        if($(this).hasClass("current"))
        {
          $(this).removeClass("current");
        }
        else
        {
          $("#mainNav li").removeClass("current");
          $(this).addClass("current");
        }
      });
      $(".dropdown-menu .closeArea a").click(function() {$(".accountid").removeClass("open");});
});
$(window).resize(function(){
	$("#mainFrame,.uiCol,.spanfix .jq-trigger").css("height",$(window).height() - 93);
})