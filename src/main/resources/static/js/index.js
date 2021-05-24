$(document).ready(function(){
    $('.sidenav').sidenav();
});
let slider = $('.slider');
let nav=$('.navigator');
M.Slider.init(slider,{
    indicators: false,
    height: 650 ,
    transition: 500,
    interval: 6000
});

$(document).ready(function(){
    $('.carousel').carousel({
        indicators: true,
        fullWidth: true
    });

    setInterval(function () {
        $('.carousel').carousel('next');
    }, 5000);
});

$('.dropdown-trigger').dropdown();

$(document).ready(function(){
    $("#check").change(function(){
        if($(this).is(':checked')){
            $("#password").attr("type","text");
        }else{
            // Changing type attribute
            $("#password").attr("type","password");
        }
    });
});

$(document).ready(function(){
    $("#check1").change(function(){
        if($(this).is(':checked')){
            $("#newpassword").attr("type","text");
        }else{
            // Changing type attribute
            $("#newpassword").attr("type","password");
        }
    });
});
$(document).ready(function(){
    $('.collapsible').collapsible();
});



