$(document).ready(function(){
    accordionUI();
});
function accordionUI(){
    var listTarget = $(".accordionContainer li");
    $(listTarget).click(function(){
        $(this).toggleClass('active');
    });
}