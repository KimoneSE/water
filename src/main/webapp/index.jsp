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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="./resources/img/favicon.ico">

    <script src="resources/js/user_main.js"></script>
</head>

<body onload="load()">

<!-- Top menu -->

<jsp:include page="public/navigation.jsp"></jsp:include>

<!--&lt;!&ndash; Page Title &ndash;&gt;-->
<div class="page-title-container">
    <!--Slider -->
    <div class="slider-container">
        <div class="container">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1 slider">
                    <div class="flexslider">
                        <ul class="slides">
                            <li>
                                <a href="public/engagement.jsp"><img src="./resources/img/public.jpg"></a>
                            </li>
                            <li>
                                <a href="./projectIntro/init"><img src="./resources/img/info.jpg"></a>
                            </li>
                            <li>
                                <a href="public/knowledge.jsp"><img src="./resources/img/intro.jpg"></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- content -->
<div class="content" style="padding: 10px">
    <div id="briIntro" class="panel panel-primary" style="width: 24%">
        <div class="panel-heading">
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
    <div id="latestPro" class="panel panel-primary" style="width: 42%">
        <div class="panel-heading">
            <h3 class="panel-title">最新项目</h3>
        </div>
        <div class="panel-body" style="text-align: left">
            <a onclick="gotoProDetail()"><h3 id="proName" style="color: #1F7CC1"></h3></a>
            <span id="proDate"></span>
            <div id="proContent" style="font-size: medium;color: black"></div>
            <a id="proDetail" onclick="gotoProDetail()">查看详情</a>
        </div>
    </div>
    <div id="promotion" class="panel panel-primary" style="width: 24%">
        <div class="panel-heading">
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
<script src="resources/jquery/3.2.1/jquery-3.2.1.min.js"></script>
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
    chooseActive("#index");
</script>
</html>