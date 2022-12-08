let mainText1 = document.querySelector(".hob div h2");
let mainText2 = document.querySelector(".hob div p");
let mainText3 = document.querySelector(".parimg1");
let mainText4 = document.querySelector(".parimg2");

window.addEventListener('scroll',function(){
    let value = window.scrollY;
    console.log("scrollY", value);
   
    if(value > 1000 && value < 2000){
        mainText1.style.animation="hobs1 1.7s ease-in-out";
        mainText2.style.animation="hobs2 1.7s ease-in-out";
    }else{
        mainText1.style.animation="desappear1 1.7s ease-in-out forwards";
        mainText2.style.animation="desappear2 1.7s ease-in-out forwards";
    }
    
    if((value > 1500) && (value < 2800)){
        mainText3.style.animation="hobs3 2s ease-in-out";
        mainText4.style.animation="hobs4 2s ease-in-out";
    }else{
        mainText3.style.animation="desappear3 1.7s ease-in-out forwards";
        mainText4.style.animation="desappear4 1.7s ease-in-out forwards";
    }
});