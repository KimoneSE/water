var head={};
var content={};
var map;
var isPrivates={};
var overlays={};
var overlay;
var userNameList=[];
var phoneNumberList=[];

//加载界面使用
function load(){
    initMap();
    var projectUrl="./allProject";
    $.ajax({
        type:"Post",
        url:projectUrl,
        async:true,
        success:function (data) {
            var objects=$.parseJSON(data);
            for(var i=0;i<data.length;i++){
                var id=objects[i].idProject;
                var headline=objects[i].name;
                var markupstr=objects[i].description;
                var isPrivate=objects[i].isPrivate;
                var lngMax=objects[i].lngMax;
                var lngMin=objects[i].lngMin;
                var latMax=objects[i].latMax;
                var latMin=objects[i].latMin;
                overlays[id] = new BMap.Polygon([
                    new BMap.Point(lngMin,latMin),
                    new BMap.Point(lngMax,latMin),
                    new BMap.Point(lngMax,latMax),
                    new BMap.Point(lngMin,latMax)
                ], {strokeColor:"#337AB7",
                        fillColor:"#337AB7",
                        strokeWeight:2,
                        strokeOpacity:0.8,
                        fillOpacity: 0.6,
                        strokeStyle: 'solid'});  //创建矩形

                isPrivates[id]=isPrivate;
                addOne(id,headline,markupstr);
            }
        }
    })

    var userUrl="./user/getAll"
    $.ajax({
        type:"Post",
        url:userUrl,
        async:true,
        success:function (data) {
            result = $.parseJSON(data)
            numbers = result.numbers
            userNames = result.userNames
            // console.log(numbers);
            // console.log(numbers.length)
            // console.log(userNames)
            // console.log(userNames.length)
            for(i=0;i<numbers.length;i++) {
                if(numbers[i]!=""){
                    phoneNumberList.push(numbers[i]);
                    userNameList.push(userNames[i]);
                }
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.responseText);
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    })

    $('input[type=radio][name=optionsRadiosInline]').change(function () {
        if (this.value == 'option2') {
            $("#private").show();
        }
        else {
            $("#private").hide();
        }
    })

    $("#addUser").click(function () {
        // console.log("startAdd")
        var phone = document.getElementById("phone").value;
        if (phone != "") {
            phone = phone+'; ';
        }
        var content = document.getElementById("userAdded");
        if (document.selection) {
            console.log("1")
            //IE support
            content.focus();
            sel = document.selection.createRange();
            sel.text = phone;
            sel.select();
        }else if (content.selectionStart || content.selectionStart =='0') {
            console.log("2")
            //MOZILLA/NETSCAPE support
            var startPos = content.selectionStart;
            var endPos = content.selectionEnd;
            var beforeValue = content.value.substring(0, startPos);
            var afterValue = content.value.substring(endPos, content.value.length);

            content.value = beforeValue + phone + afterValue;
            content.selectionStart = startPos + phone.length;
            content.selectionEnd = startPos + phone.length;
            content.focus();
        } else {
            console.log("3")
            content.value += phone;
            content.focus();
        }
    })

    $("#phone").bind('input propertychange',function(){
        // console.log("searchUser")
        var arr = [];
        var arr2 = [];
        var reg = new RegExp($("#phone").val());
        $("#sel").find("option").remove();
        for(var i=0;i<phoneNumberList.length;i++){
            //如果字符串中不包含目标字符会返回-1
            if(phoneNumberList[i].match(reg)){
                arr.push(phoneNumberList[i]);
                arr2.push(userNameList[i]);
            }
        }
        // console.log(arr);
        for(var i=0;i<arr.length;i++){//循环每一个满足条件的记录
            sel.options[i]=new Option(arr[i],i);
            sel.options[i].innerHTML+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+arr2[i];
        }
        if(arr.length>0){
            sel.style.display='inline-block';
        }else{
            sel.style.display='none';
        }
        //当用户按下上下键时获取相应的值
        if(event.keyCode==40){
            sel.focus();
        }
    })
}

function select(){
    //输入回车，获取输入框内容焦点
    $("#sel").keypress(function(){
        $("#phone").focus();
        $("#sel").css("display","none");
    });
    //双击，获取输入框内容焦点
    $("#sel").dblclick(function(){
        $("#phone").focus();
        $("#sel").css("display","none");
    });
    //将选中的下拉列表中的内容添加到输入框中
    var selected = $("option:selected").html();
    var end = selected.search(/&nbsp;/);
    // console.log(selected.substring(0,end));
    $("#phone").val(selected.substring(0,end));
}

function initMap(rectangle) {

    map = new BMap.Map('map');
    var poi = new BMap.Point(116.307852,40.057031);
    map.centerAndZoom(poi, 16);
    map.enableScrollWheelZoom();
    overlay = rectangle;
    map.addOverlay(rectangle);

    var styleOptions = {
        strokeColor:"#337AB7",    //边线颜色。
        fillColor:"#337AB7",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 2,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    }
    //实例化鼠标绘制工具
    var drawingManager = new BMapLib.DrawingManager(map, {
        isOpen: false, //是否开启绘制模式
        enableDrawingTool: true, //是否显示工具栏
        drawingToolOptions: {
            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
            offset: new BMap.Size(5, 5), //偏离值
            drawingModes: [BMAP_DRAWING_RECTANGLE]
        },
        // circleOptions: styleOptions, //圆的样式
        // polylineOptions: styleOptions, //线的样式
        // polygonOptions: styleOptions, //多边形的样式
        rectangleOptions: styleOptions //矩形的样式
    });

    //添加鼠标绘制工具监听事件，用于获取绘制结果
    drawingManager.addEventListener('overlaycomplete', overlaycomplete);
}

var overlaycomplete = function(e){
    map.removeOverlay(overlay);
    overlay = e.overlay;
};

function publish(){
    var headline=document.getElementById("headline").value;
    var userAdded=document.getElementById("userAdded").value;
    if(null===headline||""===headline){
        alert("标题不能为空！");
    }
    else {
        var markupStr = $('#summernote').summernote('code');
        var active=$("#scro1").find(".active");
        var id=active.eq(0).attr('id');
        var isPrivate;
        if ($("#private").is(":hidden")){
            isPrivate=0;
        }
        else {
            isPrivate=1;
        }
        if(id!=="add"){
            var url="./modifyProject";
            $.ajax({
                type:"POST",
                url:url,
                async:true,
                data:{
                    "id":id,
                    "headline":headline,
                    "body":markupStr,
                    "isPrivate":isPrivate,
                    "userAdded":userAdded,
                    "lngMax":overlay.Ou.Ge,
                    "lngMin":overlay.Ou.Le,
                    "latMax":overlay.Ou.Fe,
                    "latMin":overlay.Ou.Ke,
                },
                // dataType:"json",
                success:function (data) {
                    if(data==false){
                        alert("修改失败！请重试！");
                    }
                    else{
                        head[id]=headline;
                        content[id]=markupStr;
                        isPrivates[id]=isPrivate;
                        overlays[id]=overlay;
                        alert("修改成功！");
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.responseText);
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            })
        }
        else{
            var url="./publish";
            $.ajax({
                type:"POST",
                url:url,
                async:true,
                data:{
                    "headline":headline,
                    "body":markupStr,
                    "isPrivate":isPrivate,
                    "userAdded":userAdded,
                    "lngMax":overlay.Ou.Ge,
                    "lngMin":overlay.Ou.Le,
                    "latMax":overlay.Ou.Fe,
                    "latMin":overlay.Ou.Ke,
                },
                // dataType:"json",
                success:function (data){
                    // head.push(headline);
                    // content.push(markupStr);
                    if(parseInt(data)==-1){
                        alert("发布失败！请重试！");
                    }
                    else{
                        addOne(data,headline,markupStr);
                        isPrivates[data]=isPrivate;
                        overlays[data]=overlay;
                        alert("发布成功！");
                        deleteAll();
                    }
                }
            });
        }
    }
}

function deleteAll(){
    document.getElementById("headline").value= null;
    $('#summernote').summernote('code', "");
    $('#summernote').summernote('focus',true);
    $("#optionsRadiosInline1").prop("checked",true);
    $("#private").hide();
    $("#userAdded").val(null);
    $("#phone").val(null);
    map.removeOverlay(overlay);
}

function show(id){
    $("#scro1").find("li").each(function() {
        $(this).removeClass("active");
    });
    document.getElementById(id).setAttribute("class","active");

    var cancel = document.getElementById("cancel");
    if(id=="add"){
        document.getElementById("headline").value="";
        $('#summernote').summernote('code', "");
        $("#optionsRadiosInline1").prop("checked",true);
        $("#private").hide();
        $("#userAdded").val(null);
        $("#phone").val(null);
        initMap();
        cancel.innerHTML = "取消";
        cancel.setAttribute("onclick", "javascript:deleteAll();");
    }

    else {
        document.getElementById("headline").value = head[id];
        $('#summernote').summernote('code', content[id]);
        if (isPrivates[id]) {
            $("#optionsRadiosInline2").prop("checked",true);
            $("#private").show();
            $("#phone").val(null);
            $.ajax({
                type:"POST",
                url:"./getUserList",
                async:true,
                data:{
                    "projectID":id,
                },
                // dataType:"json",
                success:function (data){
                    $("#userAdded").val(data);
                },
                error : function(XMLHttpRequest,textStatus) {
                    alert(XMLHttpRequest.responseText);
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        }
        else {
            $("#optionsRadiosInline1").prop("checked",true);
            $("#private").hide();
        }

        var rectangle = new BMap.Polygon([
            new BMap.Point(overlays[id].Ou.Le,overlays[id].Ou.Ke),
            new BMap.Point(overlays[id].Ou.Ge,overlays[id].Ou.Ke),
            new BMap.Point(overlays[id].Ou.Ge,overlays[id].Ou.Fe),
            new BMap.Point(overlays[id].Ou.Le,overlays[id].Ou.Fe)
        ], {strokeColor:"#337AB7",
            fillColor:"#337AB7",
            strokeWeight:2,
            strokeOpacity:0.8,
            fillOpacity: 0.6,
            strokeStyle: 'solid'});
        initMap(rectangle);
        // map.addOverlay(rectangle);
        cancel.innerHTML = "删除";
        cancel.setAttribute("onclick", "javascript:del(" + id + ");");
    }
}

//to be continued
function del(id){
    var url="./deleteProject";
    $.ajax({
        type:"POST",
        url:url,
        async:true,
        data:{"id":id},
        dataType:"json",
        success:function (data){
            if(data==false){
                alert("删除失败！项目已经有人申请！");
            }
            else{
                var node=document.getElementById(id);
                node.parentNode.removeChild(node);

                delete head[id];
                delete content[id];
                delete isPrivates[id];
                delete overlays[id];

                alert("删除成功！");
                show("add");
            }
        }
    })
}

function addOne(id,headline,markupStr) {
    head[id]=headline;
    content[id]=markupStr;

    var list=document.getElementById("projectList");
    var item=document.createElement("li");
    item.setAttribute("id",id);
    item.setAttribute("onclick","javascript:show("+id+");");

    var a=document.createElement("a");
    a.innerHTML="&nbsp;&nbsp;&nbsp;"+headline;
    item.appendChild(a);
    var span=document.createElement("span");
    span.setAttribute("class","fa fa-angle-right");
    item.appendChild(span);

    list.appendChild(item);
}

function topnavclick(type) {
    if(type.name==="1"){
        window.location.href="toAdmin.do"
    }
    if(type.name==="2"){
        window.location.href="toAdmin_Sample.do"
    }
    if(type.name==="3"){
        window.location.href="toAdmin_Sample_Result.do"
    }
    if(type.name==="4"){
        window.location.href="toAdmin_Project.do"
    }
    if(type.name==="5"){
        window.location.href="toAdmin_News.do"
    }
}
//导航点击事件的监听
function  applyClick(type) {
    var id = type.innerHTML;
    $.ajax({
        url: './getApplyInfo',
        type: 'post',
        async: 'false',
        data: {"id": id},
        success: function (data) {
            var obj1 = $.parseJSON(data);
            setactive(type, obj1);
            setinitinfo(obj1);
        }

    });
}
//设置导航为active状态的方法
function setactive(type,temp) {
    if(temp.state===0){
        $("#scro1").find("li").each(function() {
            $(this).removeClass("active");
        });
        $(type.parentNode).addClass("active");
    }
    if(temp.state===1) {
        $("#scro2").find("li").each(function () {
            $(this).removeClass("active");
        });
        $(type.parentNode).addClass("active");
    }
    if(temp.state===2){
        $("#scro3").find("li").each(function() {
            $(this).removeClass("active");
        });
        $(type.parentNode).addClass("active");
    }

}

function listTabToggle(data) {
    $("#projectList").slideToggle();
    if($(data).hasClass("open")){
        $("#pull_toggle_img").attr("src","./resources/img/pullDown.png");
        $(data).removeClass("open");
    }else{
        $("#pull_toggle_img").attr("src","./resources/img/pullUp.png");
        $(data).addClass("open");
    }
}

// function showPrivate() {
    // var ishidden = $("#private").is(":hidden");
    // if(ishidden){
    //     $("#private").show();
    // }
    // else {
    //     $("#private").hide();
    // }
// }

