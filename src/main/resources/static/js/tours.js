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

// $('#pagination-long').materializePagination({
//     align: 'right',
//     lastPage: 5,
//     firstPage: 1,
//     useUrlParameter: true,
//     onClickCallback: function(requestedPage) {
//         console.log('Requested page from #pagination-long: ' + requestedPage);
//     }
// });

