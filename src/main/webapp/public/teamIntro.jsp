<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>eRivermap-团队介绍</title>

    <!-- CSS -->

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
                <h1><span>TEAM</span>&nbsp;&nbsp;&nbsp;<span>INTRODUCTION</span>/</h1>
                <p>团队介绍</p>
            </div>
        </div>
    </div>
</div>


<!-- content -->
<div class="content">
    <div class="container">

        <div class="intro-panel">
            <div id="mainwrapper" class="main_wrapper" style="display: block">
                <div id="main_content">
                    <div class="table-content" style="display: block;">
                        <br/>
                        <ul id="scro1" class="scroll indicator-group-title">
                            <li class='active'><a onclick='tabClick(this)'>研究团队</a>
                                <span class='fa fa-angle-right'></span></li>
                            <li class=''><a onclick='tabClick(this)'>团队负责人</a>
                                <span class='fa fa-angle-right'></span></li>
                        </ul>
                        <div id="scrol_content">
                            <div id="team_intro_panel" style="display: none" class="tab_panel indicator-group-content">
                                <div class="left_title">
                                    <i class="fa fa-paper-plane-o blue"></i>
                                    <h1 class="number" style="font-size: 25px">研究团队</h1>
                                    <a class="time">2017-7-30</a>
                                </div>
                                <div class="left_content" style="text-align: left">
                                    <p><b>南京大学环境学院“生态毒理和环境健康”研究团队</b></p>
                                    <br/>
                                    <p>
                                        南京大学环境学院“生态毒理和生态健康团队”以南京大学环境学院良好的科研基础为平台，在“十二五”期间建立了包含指示生物指标在内的多层次水生态健康综合评价指标体系。</p>
                                </div>
                            </div>
                            <div id="leader_intro_panel" style="display: none" class="tab_panel indicator-group-content">
                                <div class="left_title">
                                    <i class="fa fa-paper-plane-o blue"></i>
                                    <h1 class="number" style="font-size: 25px">团队负责人</h1>
                                    <a class="time">2017-7-30</a>
                                </div>
                                <div class="left_content" style="text-align: left">
                                    <p><b>张效伟教授</b></p>
                                    <br/>
                                    <p><img src="../resources/img/professor_image.jpg" style="width: 150px"/></p>
                                    <br/>
                                    <p>
                                        教授、博导，教育部“长江学者奖励计划”-青年学者。南京大学化学品环境安全研究中心主任，国家有机毒物污染控制与资源化工程技术研究中心副主任。主持完成“十二五”国家“863”计划课题，水专项子课题和国家自然科学优秀青年基金等；主要致力于水生生物多样性高通量监测与评估技术,
                                        化学物质环境风险控制理论与技术研究.近五年发表论文被SCI收录61篇；其中包括在 Environ. Sci. Technol., Water Res 和
                                        Environ. Int.上发表论文 21篇；全部文章被Web of Science他引1184次；H
                                        因子为30。申请国家发明专利5项，获得1项授权美国专利。</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
<script src="../resources/js/user_main.js"></script>
<script>
    chooseActive("#teamIntro");
    $("#team_intro_panel").show();
    function tabClick(data) {
        //修改tab样式
        for(var i=0;i<$("#scro1 li").length;i++) {
            $("#scro1 li").removeClass("active");
        }
        $(data).parent().addClass("active");
        //修改tabPanel的内容
        for(var i=0;i<$(".tab_panel").length;i++){
            $(".tab_panel").hide();
        }
        var index=$("#scro1 li").index($(data).parent());
        if(index==0) {
            $("#team_intro_panel").show();
        }else if(index==1) {
            $("#leader_intro_panel").show();
        }
    }

</script>
</body>

</html>