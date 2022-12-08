var key = "5d9357a1e849df69dd6243f09510416d";
var city = "seoul"; // My test case was "London"
var url = "https://api.openweathermap.org/data/2.5/forecast";

$.ajax({
  url: url, //API Call
  dataType: "json",
  type: "GET",
  data: {
    q: city,
    appid: key,
    units: "metric",
    cnt: "5",
    lang: "kr"
  },
  success: function(data) {
    console.log('Received data:', data) // For testing
    var wf = "";
    wf += "<h3>" + data.city.name + " Weather" + "</h3>" // City (displays once)
    $.each(data.list, function(index, val) {
      wf += "<div><title>" + val.dt_txt + "</title>" // Icon
      wf += "<img src='https://openweathermap.org/img/w/" + val.weather[0].icon + ".png'>" // Icon
      wf += "<p>"+val.main.temp+"&degC</p>"
      wf += "<ul>"
      wf += "<li>체감온도 : "+val.main.feels_like + "&degC </li>"
      wf += "<li>최저온도 : "+val.main.temp_min + "&degC </li>"
      wf += "<li>최고온도 : "+val.main.temp_max + "&degC </li>"
      wf += "<li>습 도 : "+val.main.humidity + "% </li>"
      wf += "<li>풍 속 : "+val.wind.speed + "m/s </li>"
      if(val.rain != undefined){
      wf += "<li>강수량(3시간) : "+val.rain['3h'] + "mm </li>"
      }
      wf += " </ul></div>" // Closing paragraph tag
    });
    $("#showWeatherForcast").html(wf);
  }
})
