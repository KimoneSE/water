// function onClickWaterAddr() {
//     window.location.href = basePath + "address/waters";
// };

$(document).ready(function () {
    $("#imageForm").attr("target", "rfFrame");
});

$("#applyUpload").click(function () {
    var idUser = $("#userID").val();
    var date = new Date();
    var applyDate = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    var applyDateStr=date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds();
    // $("#applyDate").val(applyDate);
    // $("#img_userID").val(idUser);
    // $("#imageForm").submit();
    var url = "../applyUpload";
    var longitude = "";
    var latitude = "";

    var number = "";
    var address = "";
    var state = 0;
    var name = "";
    var waterAddress = "";
    var projectID = "";

    longitude = $("#longitude").val();
    latitude = $("#latitude").val();
    number = $("#contact").text();
    address = $("#add").text();
    name = $("#name").text();
    waterAddress = $("#river_place").text();
    projectID = $("#projectID").val();
    var imgUrl = "";
    imgUrl = $("#url").val();
    var sampleNum = $("#sampleNum").val();

    if (projectID == "") {
        alert("请选择项目名称");
    } else if (waterAddress == "") {
        alert("请选择水域地址");
    } else if (sampleNum == ""){
        alert("请输入样品数量");
    } else if ($("#file").val() == "") {
        alert("请上传河流图片");
    } else {
        $.ajax({
            type: "POST",
            url: url,
            async: true,
            // dataType:"json",
            data: {
                "longitude": longitude,
                "latitude": latitude,
                "number": number,
                "address": address,
                "applyDate": applyDate,
                "state": state,
                "imgUrl": imgUrl,
                "name": name,
                "waterAddress": waterAddress,
                "idUser": idUser,
                "projectID": projectID,
                "sampleNum": sampleNum
            },
            success: function (data) {
                if (data) {
                    alert("提交成功");
                    $.cookie('ret2', null, {path: '/'});
                    $.cookie('name', null, {path: '/'});
                    $.cookie('tel', null, {path: '/'});
                    $.cookie('add2', null, {path: '/'});
                    $.cookie('ret3', null, {path: '/'});
                    $.cookie('longitude', null, {path: '/'});
                    $.cookie('latitude', null, {path: '/'});
                    $.cookie('concrete_address', null, {path: '/'});
                    $.cookie('ret4',null, { expires: -1, path: '/'});
                    $.cookie('projectName',null, { expires: -1, path: '/' });
                    // $.cookie('projectName','选择项目',{path:'/'});
                    // $("#projectName").text("选择项目");
                    $("#url").empty();
                    window.location.href = basePath + "user/j" + idUser + "/history?type=0";
                } else {
                    alert("提交申请失败");
                }
            }
            // error : function(XMLHttpRequest, textStatus, errorThrown) {
            //     //这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
            //     alert(XMLHttpRequest.responseText);
            //     alert(XMLHttpRequest.status);
            //     alert(XMLHttpRequest.readyState);
            //     alert(textStatus); // parser error;
            // }
        });
    }
});

