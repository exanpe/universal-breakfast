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

if($.fn.iCheck){
    $(document).ready(function(){
        $('input.icb').iCheck({
            checkboxClass: 'icheckbox_square-red',
            radioClass: 'iradio_square-red',
            increaseArea: '20%' // optional
        });
    });
}

if(window['messages']){
    var tour = {
        id: "tutorial",
        i18n : {
            nextBtn :  messages.ub.js.tour.nextBtn(),
            doneBtn : messages.ub.js.tour.doneBtn(),
            closeTooltip : messages.ub.js.tour.closeTooltip(),
            stepNums : ["!", "!", "!", "!", "1", "2", "3", "..."]
        },
        showPrevButton : false,
        steps: [
            {
                target: ".navbar",
                title: messages.ub.js.tour.step.welcome.title(),
                content: messages.ub.js.tour.step.welcome.content(),
                placement: "bottom",
                xOffset : "center",
                arrowOffset : "center"
            },
            {
                target: ".navbar",
                title: messages.ub.js.tour.step.welcome2.title(),
                content: messages.ub.js.tour.step.welcome2.content(),
                placement: "bottom",
                xOffset : "center",
                arrowOffset : "center"
            },
            {
                target: ".tour_members",
                title: messages.ub.js.tour.step.members.title(),
                content: messages.ub.js.tour.step.members.content(),
                placement: "right",
                yOffset : "center"
            },
            {
                target: ".sidebar .well",
                title: messages.ub.js.tour.step.process.title(),
                content: messages.ub.js.tour.step.process.content(),
                placement: "right",
                yOffset : "center",
                arrowOffset : "center"
            },
            {
                target: ".tour_prepare",
                title: messages.ub.js.tour.step.prepare.title(),
                content: messages.ub.js.tour.step.prepare.content(),
                placement: "right"
            },
            {
                target: ".tour_gather",
                title: messages.ub.js.tour.step.gather.title(),
                content: messages.ub.js.tour.step.gather.content(),
                placement: "right"
            },
            {
                target: ".tour_complete",
                title: messages.ub.js.tour.step.complete.title(),
                content: messages.ub.js.tour.step.complete.content(),
                placement: "right"
            },
            {
                target: ".tour_settings",
                title: messages.ub.js.tour.step.settings.title(),
                content: messages.ub.js.tour.step.settings.content(),
                placement: "right",
                yOffset : "center",
                arrowOffset : "center"
            }
        ]
    };
}