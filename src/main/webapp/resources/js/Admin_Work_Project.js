var head={};
var content={};
var map;
var overlays={};
var overlay;

//加载界面使用
function load(){
    initMap();
    var url="./allProject";
    $.ajax({
        type:"Post",
        url:url,
        async:true,
        success:function (data) {
            var objects=$.parseJSON(data);
            for(var i=0;i<data.length;i++){
                var id=objects[i].idProject;
                var headline=objects[i].name;
                var markupstr=objects[i].description;
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

                addOne(id,headline,markupstr);
            }
        }

    })

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
    if(null===headline||""===headline){
        alert("标题不能为空！");
    }
    else {
        var markupStr = $('#summernote').summernote('code');
        var active=$("#scro1").find(".active");
        var id=active.eq(0).attr('id');
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
                    "lngMax":overlay.Nu.Ge,
                    "lngMin":overlay.Nu.Le,
                    "latMax":overlay.Nu.Fe,
                    "latMin":overlay.Nu.Ke,
                },
                dataType:"json",
                success:function (data) {
                    if(data==false){
                        alert("修改失败！请重试！");
                    }
                    else{
                        head[id]=headline;
                        content[id]=markupStr;
                        overlays[id]=overlay;
                        alert("修改成功！");
                    }
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
                    "lngMax":overlay.Nu.Ge,
                    "lngMin":overlay.Nu.Le,
                    "latMax":overlay.Nu.Fe,
                    "latMin":overlay.Nu.Ke,
                },
                dataType:"json",
                success:function (data){
                    // head.push(headline);
                    // content.push(markupStr);
                    if(parseInt(data)==-1){
                        alert("发布失败！请重试！");
                    }
                    else{
                        addOne(data,headline,markupStr);
                        overlays[data]=overlay;
                        alert("发布成功！");
                        deleteAll();
                    }
                },
                error:function () {
                    alert("发布失败！可能是格式错误！")
                }
            });
        }
    }
}

function deleteAll(){
    var markupStr = "";
    document.getElementById("headline").value= null;
    $('#summernote').summernote('code', markupStr);
    $('#summernote').summernote('focus',true);
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
        initMap();
        cancel.innerHTML = "取消";
        cancel.setAttribute("onclick", "javascript:deleteAll();");
    }

    else {
        document.getElementById("headline").value = head[id];
        $('#summernote').summernote('code', content[id]);

        var rectangle = new BMap.Polygon([
            new BMap.Point(overlays[id].Nu.Le,overlays[id].Nu.Ke),
            new BMap.Point(overlays[id].Nu.Ge,overlays[id].Nu.Ke),
            new BMap.Point(overlays[id].Nu.Ge,overlays[id].Nu.Fe),
            new BMap.Point(overlays[id].Nu.Le,overlays[id].Nu.Fe)
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
                alert("删除失败！请重试！");
            }
            else{
                var node=document.getElementById(id);
                node.parentNode.removeChild(node);

                delete head[id];
                delete content[id];
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