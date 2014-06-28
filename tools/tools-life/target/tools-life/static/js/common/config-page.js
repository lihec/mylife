$(function(){
        // Selector fixes
        $("input[type='checkbox']").addClass("input-check");
        $("input[type='text'],input[type='password']").addClass("input-text");
        /*fix ie6 advanced selector support*/
        $(".trigger-content .toggle-link").click(function(){
            $(".search-form ul.hide").toggle();
        });
        $(".toggle-content").hover(
            function(){$(this).children(".dropdown-menu").show();},
            function(){$(this).children(".dropdown-menu").hide();}
        );/*dropdown menu*/
        $(".form-btn-content .edit").click(function(){$(".editable").hide().next("input").removeClass("hide");});/*switch editable status*/
        $(".form-btn-content .edit").click(function(){$(this).hide();});
        $(".form-btn-content .edit").click(function(){$(".form-btn-content .save,.form-btn-content .cancel").removeClass("hide-i");});

});