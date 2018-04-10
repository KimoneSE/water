<%@ page import="java.util.ArrayList" %>
<%@ page import="com.water.entity.News" %>
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
    <title>eRivermap-新闻动态</title>

    <!-- CSS -->

    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster">
    <link href="//cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/animate.css/3.5.2/animate.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>resources/css/styles.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="<%=basePath%>resources/jquery/3.2.1/jquery-3.2.1.min.js"></script>
    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="../resources/img/favicon.ico">

    <style>
        .imgcard {
            width: 350px;
            height:300px;
            text-align: center;
            border-radius: 10px;
            box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.15);
            margin-left: 3%;
            margin-top: 2%;
            float:left;
        }
        .img_container{
            padding: 10px;
        }
        .myImg{
            max-height:80%;
        }
    </style>
</head>
<body>
<!-- Top menu -->
<jsp:include page="navigation.jsp"></jsp:include>

<!-- Page Title -->
<div class="page-title-container">
    <div class="container">
        <!--<img src="../resources/img/background.jpg" height="100px" width="100%"/>-->
        <div class="row">
            <div class="col-sm-7 wow fadeIn">
                <i class="fa fa-file-text-o"></i>
                <h1><span>NEWS</span>/</h1>
                <p>新闻动态</p>
            </div>
        </div>
    </div>
</div>

<div class="content">
    <div class="container" id="newsContainer">
            <div id="mainwrapper" class="main_wrapper" style="display: block">
                <div id="newsBlock">

                </div>
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
                        href="#">Noah's Ark</a>.
                </p>
            </div>
            <div class="col-sm-5 footer-social wow fadeIn">
                <!--                <a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
                                    class="fa fa-qq"></i></a> <a href="#"><i class="fa fa-weibo"></i></a>
                                <a href="#"><i class="fa fa-wechat"></i></a>-->
            </div>
        </div>
    </div>
</footer>

<!-- Javascript -->
<script src="//cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-hover-dropdown/2.2.1/bootstrap-hover-dropdown.min.js"></script>
<script src="//cdn.bootcss.com/wow/1.1.2/wow.min.js"></script>
<script src="<%=basePath%>resources/js/user_main.js"></script>


<script>
    chooseActive("#newsNav");

    $("#newsContainer").css("min-height", window.innerHeight);
    $("#newsContainer").css("background-color", "white");
    $("#newsContainer").css("box-shadow", "0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.15)");
    $("#newsContainer").css("border-radius", "5px");

    <% ArrayList<News> newsList = (ArrayList)request.getAttribute("newsList");
    if(newsList != null){
        for(int i=0;i<newsList.size();i++) { %>
        var tmp = '<div class="imgcard">' +
            '<img class="myImg" src="http://test.ufeng.top/web_upload/<%=newsList.get(i).getCover()%>" style="width:100%"/>'+
            '<div class="img_container"><a href="http://test.ufeng.top/water/concrete_news?id=<%=newsList.get(i).getId()%>"><%=newsList.get(i).getTitle()%></a></div></div>';
            $("#newsBlock").append(tmp);
    <%  }
    }else {%>
            var tmp = "暂无新闻动态";
            $("#newsBlock").append(tmp);
    <%}%>;
</script>
</body>
</html>
