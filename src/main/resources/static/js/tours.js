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
    $('.datepicker').datepicker();
});

$('.dropdown-trigger').dropdown();

$(document).ready(function(){
    $('.parallax').parallax();
});

$(document).ready(function(){
    $('select').formSelect();
});

