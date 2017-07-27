function onClickWaterAddr() {
    // $.ajax({
    //     url:"/chooseRiver",
    //     type:'POST',
    //     async:false,
    //     data:{"userName":$("#apply_username").val(),"userContact":$("#apply_userContact").val(),"address":$("#apply_address").val()},
    //     success:function (data) {
    window.location.href = "/address/waters";
    //     }.
};

j = 1;
$(document).ready(function(){
    $("#imageForm").attr("target","rfFrame");
    $("#btn_add").click(function () {
        $("#newUpload").append("<div id='div_" + j + "'><input name='image' type='file' accept='image/jpeg,image/png,image/gif' /><input id='button_"+j+"' type='button' value='删除'  onclick='del(" + j + ")'/></div>");
        j = j + 1;
    });
});

function del(o) {
    document.getElementById("newUpload").removeChild(document.getElementById("div_" + o));
}

// $("#river_place").on('input propertychange', function () {
//     checkComplete();
// });
//
// function checkComplete(){
//     if($("#river_place").text() == "" || $("#river_place").text() == null){
//         $("#applyUpload").addClass("weui-btn_plain-disabled");
//         return;
//     }
//     $("#applyUpload").removeClass("weui-btn_plain-disabled");
// }

$("#applyUpload").click(function(){
    // if($("#applyUpload").hasClass("weui-btn_plain-disabled")){
    //     return;
    // }
    var idUser = $("#userID").val();
    var date = new Date();
    var applyDate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes();
    // alert(applyDate);
    $("#applyDate").val(applyDate);
    $("#img_userID").val(idUser);
    $("#imageForm").submit();
    var url = "/applyUpload";
    var longitude = "";
    var latitude = "";
    var number = "";
    var address = "";
    var state = 0;
    var name = "";
    var waterAddress ="";
    var projectID = "";

    longitude = $("#longitude").val();
    latitude = $("#latitude").val();
    number = $("#contact").text();
    address = $("#add").text();
    name = $("#name").text();
    waterAddress = $("#river_place").text();
    alert(waterAddress);
    projectID = $("#projectID").val();
    var imgUrl = "";

    var myArray = document.getElementsByName("image");
    for(var i=0;i<myArray.length;i++){
        imgUrl += idUser + "_" + applyDate + "_" + i.toString() + ".jpg" + ";";
    }
    // alert(imgUrl);
    if(projectID == ""){
        alert("请选择项目名称");
    }else if(waterAddress == ""){
        alert("请选择水域地址");
    }else if(imgUrl == ""){
        alert("请上传河流图片");
    }else{
        $.ajax({
            type:"POST",
            url:url,
            async:true,
            data:{"longitude":longitude,"latitude":latitude,"number":number,"address":address,"applyDate":applyDate,
            "state":state,"imgUrl":imgUrl,"name":name,"waterAddress":waterAddress,"idUser":idUser,"projectID":projectID},
            dataType:"json",
            success:function (data) {
                alert(data);
                if(data){
                    alert("提交成功");
                    $.cookie('ret2', null,{path:'/'});
                    $.cookie('name',null,{path:'/'});
                    $.cookie('tel',null,{path:'/'});
                    $.cookie('add2', null,{path:'/'});
                    $.cookie('ret3', null,{path:'/'});
                    $.cookie('longitude',null,{path:'/'});
                    $.cookie('latitude',null,{path:'/'});
                    $.cookie('concrete_address', null,{path:'/'});
                    alert("/user/j"+idUser+"/history");
                    window.location.href = "/user/j"+idUser+"/history";
                }else{
                    alert("提交申请失败");
                }
            }
            
        })
    }
})

