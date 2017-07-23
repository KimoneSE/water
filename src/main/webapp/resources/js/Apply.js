document.getElementById("chooseRiver").onclick = function () {
    $.ajax({
        url:"/chooseRiver",
        type:'POST',
        async:false,
        data:{"userName":$("#apply_username").val(),"userContact":$("#apply_userContact").val(),"address":$("#apply_address").val()},
        success:function (data) {
        }

    })
    window.location.href = "../wx/confirm_address.jsp";
};
document.getElementById("chooseAddress").onclick = function () {
    window.location.href = "../wx/locate.html";
};

j = 1;
$(document).ready(function () {
    $("#btn_add").click(function () {
        alert(22);
        document.getElementById("newUpload").innerHTML += '<div id="div_' + j + '"><input  name="image" type="file"  /><input type="button" value="删除"  onclick="del(' + j + ')"/></div>';
        j = j + 1;

    });
});
function del(o) {
    document.getElementById("newUpload").removeChild(document.getElementById("div_" + o));
}

function getApply(longitude,latitude,number,address,applyDate,state,image,name,waterAddress,idUser){
    this.longitude = longitude;
    this.latitude = latitude;
    this.number = number;
    this.address = address;
    this.applyDate = applyDate;
    this.state = state;
    this.image = image;
    this.name = name;
    this.waterAddress = waterAddress;
    this.idUser = idUser;
}

$("#applyUpload").click(function submit(){
    $("#imageForm").submit();
    var url = "./getApply";
    var number = document.getElementsByName("contact").toString();
    var address = document.getElementsByName("address").toString();
    var myDate = new Date();
    var applyDate = myDate.toLocaleDateString();    //获取当前日期
    var num = j;
    alert(j);
    var imgUrl = "";
    for (var i=0;i<num;i++){
        var key = "file_"+i;
        imgUrl += '${key}';
    }
    var applyData = JSON.stringify(new getApply(sessionStorage.getItem("longitude"),sessionStorage.getItem("latitude"),number,address,applyDate,"待审核",imgUrl,$("#apply_username").val(),$("#river_place").text(),sessionStorage.getItem("userId")));

    $.ajax({
        type:"POST",
        url:url,
        async:true,
        data:applyData,
        dataType:"json",
        success:function (data) {
            alert("提交成功");
        }
    })
})

