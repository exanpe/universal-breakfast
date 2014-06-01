if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
            $(this).fadeOut();
        });

        // Scroll on Navbar link click
        $(".navbar a[href^='#']").click(function (evt) {
            var scrollToElm = $($(this).attr("href"));
            if (scrollToElm.length) {
                evt.preventDefault();
                $("body,html").animate({ scrollTop: scrollToElm.offset().top }, 500);
            }
        });
    })(jQuery);
}

//if import
if($.fn.datetimepicker){
    $(document).ready(function(){
        var yesterday = new Date();
        yesterday.setDate(new Date().getDate() - 1)

       $(".date-marker").datetimepicker(
           {
               pickTime : false,
               //defaultDate : new Date(),
               minDate : yesterday
           }
       );
    });
}
