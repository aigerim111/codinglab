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

// const login = $(".modal");
// M.Modal.init(login,{
//     opacity:0.7
// });



