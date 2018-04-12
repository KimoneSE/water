<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>eRivermap-首页</title>

    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster">
    <link href="//cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/animate.css/3.5.2/animate.css" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/styles.css">
    <link rel="stylesheet" href="./resources/css/test.css">
    <link rel="stylesheet" href="./resources/css/user_main.css">
    <link rel="stylesheet" href="./resources/flexslider/flexslider.css">
    <link rel="stylesheet" href="./resources/css/andiaStyle.css">

    <script src="resources/jquery/3.2.1/jquery-3.2.1.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="./resources/img/favicon.ico">

    <script src="resources/js/user_main.js"></script>

    <style>
        .panel {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 3px rgba(0,0,0,.2);
            margin: 20px 0 30px;
            min-height:400px;
        }
        .my-panel{
            margin-left: 4%;
        }
        .my-heading {
            color: gray;
            background-color: white;
            border-color: gray;
        }
        .flex-control-nav{
            bottom:0px
        }
    </style>
</head>

<body onload="load()">

<!-- Top menu -->

<jsp:include page="public/navigation.jsp"></jsp:include>

<!--&lt;!&ndash; Page Title &ndash;&gt;-->
<%--<div class="container">--%>
    <%--<div id="myCarousel" class="carousel slide">--%>
        <%--<!-- 轮播（Carousel）指标 -->--%>
        <%--<ol class="carousel-indicators" id="carousel-indicators">--%>
            <%--<li data-target="#myCarousel" data-slide-to="0" class="active"></li>--%>
        <%--</ol>--%>
        <%--<!-- 轮播（Carousel）项目 -->--%>
        <%--<div class="carousel-inner" id="carousel-inner">--%>
            <%--<div class="item active">--%>
                <%--<img src="${pageContext.request.contextPath}/resources/img/background.jpg" alt="First slide">--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<!-- 轮播（Carousel）导航 -->--%>
        <%--<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">--%>
            <%--<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>--%>
        <%--</a>--%>
        <%--<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">--%>
            <%--<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>--%>
        <%--</a>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<div class="page-title-container">--%>
    <%--<!--Slider -->--%>
    <%--<div class="slider-container">--%>
        <%--<div class="container">--%>
            <%--<div class="row">--%>
                <%--<div class="col-sm-10 col-sm-offset-1 slider">--%>
<div class="content">
    <div >
        <div class="row">
        <div class="col-sm-10 col-sm-offset-1 slider">
        <div class="panel" style="margin-left: 5%;margin-right: 5%">

                <div class="flexslider">
                        <ul class="slides" id="news">
                            <%--<li>--%>
                                <%--<a href="<%=basePath%>public/engagement.jsp"><img src="http://test.ufeng.top/web_upload/2018-3-31 18-0-21.jpg"></a>--%>
                            <%--</li>--%>
                        </ul>
                    </div>
        </div>
            </div>
    </div>
    </div>

</div>

<div class="content" style="padding: 10px">
    <div class="row">
    <div id="briIntro" class="panel my-panel col-sm-3 col-sm-offset-1">
        <div class="panel-heading my-heading">
            <h3 class="panel-title">项目介绍</h3>
        </div>
        <div id="briIntro-body" class="panel-body" style="color: #3c3c3c;font-size: medium">
            <img src="./resources/img/water_pollution4.jpg">
            <p>
                近年来，环境污染和全球气候变化造成水生生物多样性下降、
                生物完整性缺失和物种灭绝，最终导致生态系统功能受损。
            </p>
            <p>
                本项目通过鼓励公众参与水环境监测的采样流程，利用宏基因条形码技术对
                来自全国各地的水环境进行生物多样性监测，进一步评价全国的水环境健康
                状况,提供河流等湿地生态系统的生物多样性分布地图，建立河流生态健康评
                估报告。
            </p>
        </div>
    </div>
    <div id="latestPro" class="panel my-panel col-sm-4 ">
        <div class="panel-heading my-heading">
            <h3 class="panel-title">最新项目</h3>
        </div>
        <div class="panel-body" style="text-align: left">
            <a onclick="gotoProDetail()"><h3 id="proName" style="color: #1F7CC1"></h3></a>
            <span id="proDate"></span>
            <div id="proContent" style="font-size: medium;color: black"></div>
            <a id="proDetail" onclick="gotoProDetail()">查看详情</a>
        </div>
    </div>
    <div id="promotion" class="panel my-panel col-sm-3 ">
        <div class="panel-heading my-heading" >
            <h3 class="panel-title">加入我们</h3>
        </div>
        <div class="panel-body" style="font-size: medium">
            <img src="./resources/img/code.png">
            <p>
                欢迎扫描二维码，关注我们的微信公众号“河流生态地图”，获取最新资讯并参与志愿活动。
            </p>
            <P>
                河流生态的改善需要你我共同努力！
            </P>
        </div>
    </div>
    </div>
</div>




<!-- content -->


<!-- Footer -->
<footer>
    <div class="container">
        <!--        <div class="row">
                    <div class="col-sm-12 wow fadeIn">
                        <div class="footer-border"></div>
                    </div>
                </div>-->
        <div class="row">
            <div class="col-sm-7 footer-copyright wow fadeIn">
                <p>
                    Copyright 2017 河流生态地图后台 - by <a
                        href="http://azmind.com/free-bootstrap-themes-templates/">Noah's Ark</a>.
                </p>
            </div>
            <div class="col-sm-5 footer-social wow fadeIn">
                <a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
                    class="fa fa-qq"></i></a> <a href="#"><i class="fa fa-weibo"></i></a>
                <a href="#"><i class="fa fa-wechat"></i></a>
            </div>
        </div>
    </div>
</footer>


<!-- Javascript -->
<script src="//cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-hover-dropdown/2.2.1/bootstrap-hover-dropdown.min.js"></script>
<script src="resources/andiajs/jquery.backstretch.min.js"></script>
<script src="//cdn.bootcss.com/wow/1.1.2/wow.min.js"></script>
<script src="resources/andiajs/retina-1.1.0.min.js"></script>
<script src="resources/andiajs/jquery.magnific-popup.min.js"></script>
<script src="resources/flexslider/jquery.flexslider.js"></script>
<script src="resources/andiajs/jflickrfeed.min.js"></script>
<script src="resources/andiajs/masonry.pkgd.min.js"></script>
<script src="resources/andiajs/jquery.ui.map.min.js"></script>
<script src="resources/andiajs/scripts.js"></script>

</body>
<script type="text/javascript">
//    $("#myCarousel").css("width", window.innerWidth);

$(function () {
    getIndexNews();
    setInterval(function () {
        //=========首页头部滚动广告=========
        $('.flexslider').flexslider({
            slideshowSpeed: 3000, //展示时间间隔ms
            animationSpeed: 1000, //滚动时间ms
            touch: true, //是否支持触屏滑动
            directionNav: true,
            prevText: '',
            nextText: '',
            temWidth: 220,//一个滚动项目的宽度
            itemMargin: 20,//滚动项目之间的间距
            pauseOnHover: true,
            animation: "slide",
            start: function (slider) { }
        });
    }, 0);
});
    chooseActive("#index");
</script>
</html>