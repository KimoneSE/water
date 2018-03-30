<%@ page import="com.water.entity.Project" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/31 0031
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>eRivermap-样本地图</title>

    <!-- CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster">
    <link href="//cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/animate.css/3.5.2/animate.css" rel="stylesheet">
    <link rel="stylesheet" href="../resources/css/styles.css">
    <link rel="stylesheet" href="../resources/css/test.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="../resources/jquery/3.2.1/jquery-3.2.1.min.js"></script>
    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="../resources/img/favicon.ico">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=KMeS1wUAPKRLXZVwClhw8pODhqxxP0bz"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../resources/js/user_main.js"></script>
</head>
<body>
<!-- Top menu -->
<jsp:include page="navigation.jsp"></jsp:include>

<div class="table-content" style="display: block;">
    <br/>
    <ul id="scro1" style="overflow:auto;height:auto;" class="scroll indicator-group-title">
        <li class=''><a class="open" onclick='listTabToggle(this)'>样品列表
            <label style="float: right"><img id="pull_toggle_img" src="../resources/img/pullUp.png" style="width: 15px"/></label></a>
        </li>
        <div id="sampleList" style="display: block">
        </div>
    </ul>
</div>
<div id="map" style="height: 700px"></div>
<!-- 模态框（Modal） -->
<%--<div class="modal fade"  id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-close"></i>
                    <button>
                        <h4 class="modal-title" id="myModalLabel">
                            样本编号：100001
                        </h4>
                    </button>
                </button>
            </div>
            <div class="modal-body">
                <div class="pop-wrap">
                    <div style="display: block; height: 480px; width:560px; overflow-y: auto; overflow-x:hidden ">
                        <p id="reason" style="text-align: left;color:#555;"></p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-close" data-dismiss="modal">关闭
                    <tton>
                    </tton>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>--%>
<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<script type="text/javascript">
    chooseActive("#projectIntro");
    // var poi = new Array();
    // $.ajax({
    //     url:"../getSampleList",
    //     type:"post",
    //     async:false,
    //     success:function (data) {
    //         var obj = $.parseJSON(data);
    //         for(var i = 0;i<obj.length;i++){
    //
    //             if(obj[i].state=="2") {
    //                 poi.push([obj[i].apply.longitude, obj[i].apply.latitude, 1]);
    //             }
    //         }
    //     }
    // })
    // var map = new BMap.Map("map");// 创建Map实例
    // map.centerAndZoom(new BMap.Point(116.404, 39.915), 4);     // 初始化地图,设置中心点坐标和地图级别
    // map.enableScrollWheelZoom();// 判断当前浏览器是否支持绘制海量点
    //     var markers = [];
    //     var pt = null;
    //     var points = [];  // 添加海量点数据
    //     var options = {
    //         size: BMAP_POINT_SIZE_BIG,
    //         color: '#d340c3'
    //     }
    //     for (var i = 0; i <poi.length; i++) {
    //        points.push(new BMap.Point(poi[i][0], poi[i][1]));
    //         pt = new BMap.Point(poi[i][0], poi[i][1]);
    //         markers.push(new BMap.Marker(pt,options));
    //
    //     }
    //
    //     var markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});
    //
    //         for(var j = 0;j<markers.length;j++){
    //         markers[j].addEventListener("click",attribute);
    //         markers[j].ini
    //         }
    //
    // function attribute(e){
    //     var p = e.target;
    //     $.ajax({
    //             url:"../getSampleReport",
    //             type:"post",
    //             data:{"longtitude":p.getPosition().lng,"latitude":p.getPosition().lat},
    //             success:function (data) {
    //                 var obj1 = $.parseJSON(data);
    //                 $("#myModalLabel").html( "样本编号："+obj1.idResult);
    //                 $("#reason").html(obj1.description);
    //                 $("#myModal").modal("show");
    //             }
    //     })
    // }

    // var json1=[
    //     {
    //         "featureType": "road",
    //         "elementType": "all",
    //         "stylers": {
    //             "visibility": "off"
    //         }
    //     },
    //     {
    //         "featureType": "manmade",
    //         "elementType": "all",
    //         "stylers": {
    //             "visibility": "off"
    //         }
    //     },
    //     {
    //         "featureType": "building",
    //         "elementType": "all",
    //         "stylers": {
    //             "visibility": "off"
    //         }
    //     },
    //     {
    //         "featureType": "label",
    //         "elementType": "labels",
    //         "stylers": {
    //             "lightness": 60
    //         }
    //     },
    //     {
    //         "featureType": "label",
    //         "elementType": "labels.icon",
    //         "stylers": {
    //             "visibility": "off"
    //         }
    //     },
    //     {
    //         "featureType": "water",
    //         "elementType": "all",
    //         "stylers": {
    //             "lightness": -47
    //         }
    //     }
    // ]
    // map.setMapStyle({
    //     styleJson:json1
    // });

    <% Project project = (Project) request.getAttribute("project");
    String projectId = String.valueOf(project.getIdProject());
    Double lngMax = project.getLngMax();
    Double lngMin = project.getLngMin();
    Double latMax = project.getLatMax();
    Double latMin = project.getLatMin();%>
    <%--<%Double lngMid = (lngMax+lngMin)/2;
    Double latMid = (latMax+latMin)/2;%>--%>

    var map;
    var markers = [];
    initSamples();
    // initMap();
    // drawPoints();

    function initSamples() {
        var poi = new Array();
        $.ajax({
            type:'POST',
            url:"../getSamplesByProID",
            data:{"projectID":<%=projectId%>},
            success:function (data) {
                // alert(data)
                result = $.parseJSON(data)
                samples = result.sampleList
                applies = result.applyList
                // console.log(samples)
                // console.log(applies)
                // console.log(samples.length)
                // console.log(applies.length)
                if(samples.length != 0){
                    for(i=0;i<samples.length;i++) {
                        if(samples[i].state=="2"){
                            var sampleId = samples[i].sample_id;
                            var name = "<li class=''><a onclick='changeContent(this,"+sampleId+")'>&nbsp;&nbsp;&nbsp;"+sampleId+"</a> <span class='fa fa-angle-right'></span></li>";
                            $("#sampleList").append(name);
                            poi.push([applies[i].longitude, applies[i].latitude, sampleId]);
                        }
                    }

                }else{
                    var name = "<li class=''><a>&nbsp;&nbsp;&nbsp;暂无样品</a> <span class='fa fa-angle-right'></span></li>";
                    $("#sampleList").append(name);
                }
                // for(i=0;i<applies.length;i++){
                //     if(applies[i].state=="2") {
                //         poi.push([applies[i].longitude, applies[i].latitude, 1]);
                //     }
                // }
                // console.log(poi)
                initMap(poi);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.responseText);
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        })
    }

    function initMap(poi) {
        map = new BMap.Map("map");// 创建Map实例
        var points = [];
        // 初始化地图,设置中心点坐标和地图级别
        for (var i = 0; i <poi.length; i++) {
            points.push(new BMap.Point(poi[i][0], poi[i][1]));
        }
        // console.log(points)
        // setTimeout(function(){
            setZoom(points);
        // }, 3000)
        // console.log("setZoom suc")
        // 设置地图边界
        var b = new BMap.Bounds(new BMap.Point(<%=lngMin%>, <%=latMin%>),new BMap.Point(<%=lngMax%>, <%=latMax%>));
        try {
            BMapLib.AreaRestriction.setBounds(map, b);
            // console.log("setBounds suc")
        } catch (e) {
            alert(e);
        }
        drawPoints(poi)
    }

    function drawPoints(poi) {
        // 绘制聚合点
        map.enableScrollWheelZoom();

        var myIcon = new BMap.Icon("../resources/img/markers.png", new BMap.Size(23, 32));
        for (var i = 0; i <poi.length; i++) {
            pt = new BMap.Point(poi[i][0], poi[i][1]);
            var marker = new BMap.Marker(pt,{icon:myIcon});
            map.addOverlay(marker);
            markers.push(marker);
            markers[i].id = poi[i][2];
            markers[i].setTitle("样品编号"+poi[i][2]);
        }
        // var markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});
        // for(var j = 0;j<markers.length;j++){
        //     markers[j].addEventListener("click",attribute);
        // }
    }

    function listTabToggle(data) {
        $("#sampleList").slideToggle();
        if($(data).hasClass("open")){
            $("#pull_toggle_img").attr("src","../resources/img/pullDown.png");
            $(data).removeClass("open");
        }else{
            $("#pull_toggle_img").attr("src","../resources/img/pullUp.png");
            $(data).addClass("open");
        }
    }

    function changeContent(data, sampleId){
        for(var i=0;i<$("#sampleList li").length;i++) {
            $("#sampleList li").removeClass("active");
        }
        $(data).parent().addClass("active");
        setMarkerLabel(sampleId);
    }

    function setMarkerLabel(sampleId) {
        for(var j = 0;j<markers.length;j++){
            map.removeOverlay(markers[j].getLabel())
            markers[j].setAnimation(null)
        }
        $.ajax({
            url:"../getSampleReport",
            type:"post",
            data:{"sampleId":sampleId},
            success:function (data) {
                var obj1 = $.parseJSON(data);
                var label = new BMap.Label(obj1.description,{offset:new BMap.Size(30,-30)});
                label.setStyle({
                    color : "#337AB7",
                    fontSize : "16px",
                    height : "30px",
                    lineHeight : "25px",
                    maxWidth:"none",
                    padding : "5px",
                    backgroundColor : "#B9D3EE",
                    fontFamily:"微软雅黑",
                    border :"0",
                    borderRadius: "8px",
                    fontWeight :"normal"
                });
                for(var j = 0;j<markers.length;j++){
                    if(markers[j].id == sampleId){
                        // setTimeout(function(){
                            map.setCenter(new BMap.Point(markers[j].point.lng,markers[j].point.lat));
                        // }, 2000)
                        markers[j].setLabel(label);
                        markers[j].setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                    }
                }

            }
        })
    }

    // 根据点的数组自动调整缩放级别
    function setZoom (bPoints) {
        // console.log(bPoints)
        var view = map.getViewport(eval(bPoints));
        var mapZoom = view.zoom;
        var centerPoint = view.center;
        // console.log(mapZoom)
        // console.log(centerPoint)
        map.centerAndZoom(centerPoint,mapZoom);
    }

</script>
</body>
</html>
