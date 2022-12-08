

$(document).ready(function(){
    var url = 'http://openapi.seoul.go.kr:8088/77445a7a5673736532377070516153/json/vNtcnNgc002/1/100/';
    var Detail='';
    $('#modal_btn').click(function(){
        $(".modal-dialog").toggleClass('active');
        Detail = $(this).val();
        console.log(Detail);
        
        $.ajax({			
            url: url,  
            type:"GET",
            dataType:'json',
             success:function(response) {
                showList(response);
             },
             error:function(xhr,status,msg){
                 console.log("상태값 : " + status + " Http에러메시지 : "+msg);
             }
         });
    });

    function showList(data){
        var CNList = '';
        $(".modal-body").empty();
        var head = '<span>'+Detail+'</span>';
        $(".modal-body").append(head);
        $.each(data, function(item){
            CNList = '<p>';
            CNList += '<p>' + item.CN + '</p>';
            CNList += '</p>';
            $(".modal-body").append(CNList); // tmp = CNList
        })
    }
});