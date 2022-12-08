var currentTitle = document.getElementById('current-year-month');
var calendarBody = document.getElementById('calendar-body');
var mainTodayDay = document.getElementById('main-day');
var mainTodayDate = document.getElementById('main-date');
var today = new Date();
var first = new Date(today.getFullYear(), today.getMonth(),1);
var dayList = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
var monthList = ['January','February','March','April','May','June','July','August','September','October','November','December'];
var leapYear=[31,29,31,30,31,30,31,31,30,31,30,31];
var notLeapYear=[31,28,31,30,31,30,31,31,30,31,30,31];
var pageFirst = first;
var pageYear;

if(first.getFullYear() % 4 === 0){
    pageYear = leapYear;
}else{
    pageYear = notLeapYear;
}
        
function showCalendar(){
    let monthCnt = 100;
    let cnt = 1;
    for(var i = 0; i < 6; i++){
        var $tr = document.createElement('tr');
        $tr.setAttribute('id', monthCnt);  
        for(var j = 0; j < 7; j++){
            if((i === 0 && j < first.getDay()) || cnt > pageYear[first.getMonth()]){
                var $td = document.createElement('td');
                $tr.appendChild($td);     
            }else{
                var $td = document.createElement('td');
                $td.textContent = cnt;
                $td.setAttribute('id', cnt);
                $tr.appendChild($td);
                cnt++;
            }
        }
        monthCnt++;
        calendarBody.appendChild($tr);
        showMain();
    }
}
currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'+ first.getFullYear();
showCalendar();


function removeCalendar(){
    let catchTr = 100;
    for(var i = 100; i< 106; i++){
        var $tr = document.getElementById(catchTr);
        $tr.remove();
        catchTr++;
    }
}

function prev(){
    inputBox.value = "";
    const $divs = document.querySelectorAll('#input-list > div');
    $divs.forEach(function(e){
      e.remove();
    });
    const $btns = document.querySelectorAll('#input-list > button');
    $btns.forEach(function(e1){
      e1.remove();
    });
    if(pageFirst.getMonth() === 1){
        pageFirst = new Date(first.getFullYear()-1, 12, 1);
        first = pageFirst;
        if(first.getFullYear() % 4 === 0){
            pageYear = leapYear;
        }else{
            pageYear = notLeapYear;
        }
    }else{
        pageFirst = new Date(first.getFullYear(), first.getMonth()-1, 1);
        first = pageFirst;
    }
    today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());
    currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'+ first.getFullYear();
    removeCalendar();
    showCalendar();
    showMain();
    clickedDate1 = document.getElementById(today.getDate());
    clickedDate1.classList.add('active');
    clickStart();
    reshowingList();
}

function next(){
    inputBox.value = "";
    const $divs = document.querySelectorAll('#input-list > div');
    $divs.forEach(function(e){
      e.remove();
    });
    const $btns = document.querySelectorAll('#input-list > button');
    $btns.forEach(function(e1){
      e1.remove();
    });
    if(pageFirst.getMonth() === 12){
        pageFirst = new Date(first.getFullYear()+1, 1, 1);
        first = pageFirst;
        if(first.getFullYear() % 4 === 0){
            pageYear = leapYear;
        }else{
            pageYear = notLeapYear;
        }
    }else{
        pageFirst = new Date(first.getFullYear(), first.getMonth()+1, 1);
        first = pageFirst;
    }
    today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
    currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'+ first.getFullYear();
    removeCalendar();
    showCalendar(); 
    showMain();
    clickedDate1 = document.getElementById(today.getDate());
    clickedDate1.classList.add('active');  
    clickStart();
    reshowingList();
}

function showMain(){
    mainTodayDay.innerHTML = dayList[today.getDay()];
    mainTodayDate.innerHTML = today.getDate();
}
var clickedDate1 = document.getElementById(today.getDate());
clickedDate1.classList.add('active');
var prevBtn = document.getElementById('prev');
var nextBtn = document.getElementById('next');
prevBtn.addEventListener('click',prev);
nextBtn.addEventListener('click',next);
var tdGroup = [];
function clickStart(){
    for(let i = 1; i <= pageYear[first.getMonth()]; i++){
        tdGroup[i] = document.getElementById(i);
        tdGroup[i].addEventListener('click',changeToday);
    }
}
function changeToday(e){
    for(let i = 1; i <= pageYear[first.getMonth()]; i++){
        if(tdGroup[i].classList.contains('active')){
            tdGroup[i].classList.remove('active');
        }
    }
    clickedDate1 = e.currentTarget;
    clickedDate1.classList.add('active');
    today = new Date(today.getFullYear(), today.getMonth(), clickedDate1.id);
    showMain();
    keyValue = today.getFullYear() + '' + today.getMonth()+ '' + today.getDate();
    reshowingList();
}

function reshowingList(){
    keyValue = today.getFullYear() + '' + today.getMonth()+ '' + today.getDate();
    getsublist(keyValue);
    if(todoList[keyValue] === undefined){
        inputList.textContent = '';
        todoList[keyValue] = [];
        const $divs = document.querySelectorAll('#input-list > div');
        $divs.forEach(function(e){
          e.remove();
        });
        const $btns = document.querySelectorAll('#input-list > button');
        $btns.forEach(function(e1){
          e1.remove();
        });
    }else if(todoList[keyValue].length ===0){
        inputList.textContent = "";
        const $divs = document.querySelectorAll('#input-list > div');
        $divs.forEach(function(e){
          e.remove();
        });
        const $btns = document.querySelectorAll('#input-list > button');
        $btns.forEach(function(e1){
          e1.remove();
        });
    }else{
        const $divs = document.querySelectorAll('#input-list > div');
        $divs.forEach(function(e){
          e.remove();
        });
        const $btns = document.querySelectorAll('#input-list > button');
        $btns.forEach(function(e1){
          e1.remove();
        });

    }

}
var inputBox = document.getElementById('input-box');
var inputDate = document.getElementById('input-data');
var inputList = document.getElementById('input-list');
var delText = 'X';

var dataCnt = 1;
var keyValue = today.getFullYear() + '' + today.getMonth()+ '' + today.getDate();
let todoList = [];
todoList[keyValue] = [];
function addTodoList(){
    var $div = document.createElement('div');
    $div.textContent = '- th:text=' +  "${postList.content}";
    var $btn = document.createElement('button');
    $btn.setAttribute('type', 'button');
    $btn.setAttribute('id', 'del-ata');
    $btn.setAttribute('id', dataCnt+keyValue);
    $btn.setAttribute('class', "del-data");
    $btn.textContent = delText;
    inputList.appendChild($div);
    inputList.appendChild($btn);
    todoList[keyValue].push(inputBox.value);
    dataCnt++;
    inputBox.value = '';
    $div.addEventListener('click',checkList);
    $btn.addEventListener('click',deleteTodo);

    function deleteTodo(){
        $div.remove();
        $btn.remove();
    }
}
function getsublist(keyValue ){
    const params = {
        strdate:  keyValue,
        creator: "test"
    };
    $.ajax({
        url: 'MyPage-list',
        type: 'POST',
        //cache: false,
        data: params,
        dataType: "json",
        beforeSend: function (jqXHR, settings) {
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");
            jqXHR.setRequestHeader(header, token);
        },
        success:function(response) {
            console.log(response);

            if(response.length > 0){
                for(let i = 0; i < response.length; i++){
                    console.log(today.getMonth(),response[i].strmonth);
                    if(today.getMonth() == response[i].strmonth){
                        document.getElementById(response[i].strdate).style.textDecorationLine = "underline";
                        document.getElementById(response[i].strdate).style.textDecorationColor = "white";
                        document.getElementById(response[i].strdate).style.textDecorationStyle = "dotted";
                        if(today.getDate() == response[i].strdate){
                            Showlist(response[i].content, response[i].idx);
                        }
                    }
                }
            }
        },
        error:function(xhr,status,msg){
            console.log("상태값 : " + status + " Http에러메시지 : "+msg);
        }
    });
}
function Showlist( strtxt, idx){
    let $div = document.createElement('div');
    let $btn = document.createElement('button');
    $btn.setAttribute('type', 'button');
    $btn.setAttribute('id', 'del-ata');
    $btn.setAttribute('id', idx);
    $btn.setAttribute('class', "del-data");
    $btn.textContent = delText;
    $div.textContent = '·' + strtxt ;
    inputList.appendChild($div);
    inputList.appendChild($btn);
    $btn.addEventListener('click',deleteTo);
}
function save() {

    let creator = "test";
    let content = document.getElementById('input-box').value;

    if (!content.trim()) {
        alert('내용을 입력해 주세요.');
        content.value = '';
        content.focus();
        return false;
    }
    const params = {
        creator: creator,
        strmonth: today.getMonth().toString(),
        strdate: today.getDate().toString(),
        content: content
    };
    console.log(params);

    $.ajax({
        url: 'MyPage',
        type: 'POST',
        //cache: false,
        data: params,
        dataType: "text",
        beforeSend: function (jqXHR, settings) {
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");
            jqXHR.setRequestHeader(header, token);
        },
        success:function(response) {
            console.log(response);
            inputBox.value = '';
            reshowingList();
        },
        error:function(xhr,status,msg){
            console.log("상태값 : " + status + " Http에러메시지 : "+msg);
        }
    });

}
function deleteTo() {

    const params = {
        idx: this.id
    };
    $.ajax({
        url: 'MyPage-del',
        type: 'POST',
        //cache: false,
        data: params,
        dataType: "text",
        beforeSend: function (jqXHR, settings) {
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");
            jqXHR.setRequestHeader(header, token);
        },
        success:function(response) {
            console.log(response);
            inputBox.value = '';
            reshowingList();
        },
        error:function(xhr,status,msg){
            console.log("상태값 : " + status + " Http에러메시지 : "+msg);
        }
    });

}
console.log(keyValue);
function checkList(e){
    e.currentTarget.classList.add('checked');
}
clickStart();
reshowingList();