(function ($) {
    "use strict"

    $(function(){
        var $firstmenu = $('nav > ul > li'),
            $header = $('.layout-navigation-dropdown'),
            $dropdownMenu = $('.layout-navigation-primary-menu-dropdown');
        $firstmenu.mouseenter(function(){
            $dropdownMenu.css('visibility', 'visible');
            $header.stop().animate({height:'250px'}, 300); // 내려왔을 때의 사이즈는 몇으로 할거냐
        })
            .mouseleave(function(){
                $dropdownMenu.css('visibility', 'hidden');
                $header.stop().animate({height:'80px'}, 300); // 끝났을 때는 nav-fixed 와 size 가 같게
            })
    });
})(jQuery);