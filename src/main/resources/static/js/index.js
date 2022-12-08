const timerContainer=document.querySelector(".js-clock"),
    timer=timerContainer.querySelector("p");

function getTime() {
  const xmasDay = new Date("2022-08-22:00:00:00+0900");
  const now=new Date();
  const gap=xmasDay-now;
  const day=Math.floor(gap / 1000 / 60 / 60 / 24); //일
  const hours=Math.floor(gap / 1000 / 60 / 60) % 24; //시
  const minutes=Math.floor((gap / 1000 / 60) % 60); //분
  const seconds=Math.floor((gap/1000)%60); //초
  
  timer.innerText=`수료까지... ${day}일 ${hours<10?`0${hours}`:hours}시간 ${minutes<10?`0${minutes}`:minutes}분 ${seconds<10? `0${seconds}`:seconds}초`;
}

function init() {
    getTime();
    setInterval(getTime,1000);
}
init();