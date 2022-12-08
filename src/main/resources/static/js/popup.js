var tabBtn = null;
var tabPg = null;

function tabpageJS(tabBtn,tabPg){
    $(tabBtn).click(function(){
        $(tabBtn).removeClass('active');
        $(this).addClass('active');
       
        $(tabPg).removeClass('active');
        $('#' + $(this).attr('data-tab')).addClass('active')
    });
}
