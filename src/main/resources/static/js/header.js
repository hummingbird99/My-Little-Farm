$(document).ready(function(){

    /* ------ navigation background css ------ */
    $('nav button').click(function(){
      $('nav button').stop().toggleClass('close');
      $('nav > ul').stop().toggleClass('on');
    });
    
          
      /* -------- navigation sub ul -------- */
  
      $('header').on({
          'mouseenter':function(){if($('header').hasClass('scr')){
              $('header').find('.gnb').children('li').children('ul').addClass('show');}},
          'mouseleave':function(){if($('header').hasClass('scr')){
              $('header').find('.gnb').children('li').children('ul').removeClass('show');}}
      })
    
    $('.gnb>li').on({
      'mouseenter':function(){if($(window).scrollTop()<50){$(this).children('ul').addClass('show');}},
      'mouseleave':function(){if($(window).scrollTop()<50){ $(this).children('ul').removeClass('show') }}
    })
    
    function headerscroll() {
      if($(window).scrollTop()>50){
        $('header').addClass('scr');
      }else{
        $('header').removeClass('scr');
      }
    }
    
    
    $(window).scroll(function(){
      headerscroll();
    });
   
  });