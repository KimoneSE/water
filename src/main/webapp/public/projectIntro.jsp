<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.water.entity.Project" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<% String parameter = request.getParameter("name"); %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>eRivermap-项目介绍</title>

    <!-- CSS -->

    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster">
    <link href="//cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/animate.css/3.5.2/animate.css" rel="stylesheet">
    <link rel="stylesheet" href="../resources/css/styles.css">
    <link rel="stylesheet" href="../resources/css/test.css">
    <link rel="stylesheet" href="../resources/css/projectIntro.css">
    <link rel="stylesheet" href="../resources/js/jquery.media.js">

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
        <div class="row">
            <div class="col-sm-7 wow fadeIn">
                <i class="fa fa-file-text-o"></i>
                <h1><span>PROJECT </span>&nbsp;&nbsp;&nbsp;<span>INTRODUCTION</span>/</h1>
                <p>项目介绍</p>
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
                        <ul id="scro1" style="overflow:auto;height:auto;" class="scroll indicator-group-title">
                            <li class='active'><a onclick='tabClick(this)'>总体项目简介</a>
                                <span class='fa fa-angle-right'></span></li>
                            <li class=''><a class="open" onclick='listTabToggle(this)'>项目列表
                                <label style="float: right"><img id="pull_toggle_img" src="../resources/img/pullUp.png" style="width: 15px"/></label></a>
                            </li>
                            <div id="projectList" style="display: block">
                            </div>
                        </ul>
                        <div id="scrol_content">
                            <div id="total_intro_panel" class="tab_panel indicator-group-content">
                                <div class="left_title">
                                    <i class="fa fa-paper-plane-o blue"></i>
                                    <h1 class="number" style="font-size: 25px">总体项目简介</h1>
                                    <a class="time">2017-7-30</a>
                                </div>
                                <div class="left_content" style="text-align: left">
                                    <p> 本项目的目标</p>
                                    <p>通过鼓励公众参与水环境监测的采样流程，利用宏基因条形码技术对来自全国各地的水环境进行生物多样性监测，进一步评价全国的水环境健康状况。</p>
                                    <p> 项目成果展示</p>
                                    <p>提供河流等湿地生态系统的生物多样性分布地图，建立河流生态健康评估报告。</p>
                                    <p> 本项目的特点</p>
                                    <p>1、 以水生生物多样性为检测指标</p>
                                    <p>
                                        目前，间接的物理化学指标是水环境监测的主要指标，而生物多样性指标没有被纳入监测体系。但事实上，生物多样性是保持生态系统稳定健康的关键因素。生物多样性有助于直接评估生态健康程度。</p>
                                    <p>2、 运用宏基因条形码技术</p>
                                    <p>宏基因条形码技术 (Metabarcoding)
                                        是一种新型的生物多样性分析方法，具有敏感、快速、高通量的优点。运用此技术可以实现对水生生态系统生物多样性的快速监测。</p>
                                    <p>3、 鼓励公众参与</p>
                                    <p>鼓励公众参与水环境监测的采样环节，不仅可以增进人们对身边的水体健康的了解，也是实现全国水环境生物多样性监测的重要途径。</p>
                                    <p> 项目框架</p>
                                    <p>
                                        本项目由南京大学环境学院、软件学院和环境协会合作完成：环境学院提供技术及器材支持；软件学院负责微信公众平台开发和网站设计；环境协会负责项目宣传、人员招募及系统的后期运行。</p>
                                    <p>项目框架主要分为三个模块：微信公众平台主要提供采样信息收集的功能；网页是成果展示的主要渠道；实验室提供实验、数据分析等技术支持。</p>
                                </div>
                            </div>
                            <div id="total_project_panel" style="overflow: auto;" class="tab_panel indicator-group-content">
                                <div class="left_title">
                                    <i class="fa fa-edit blue"></i>
                                    <h1>
                                        <span class="number" id="projectName" style="font-size: 25px">${projectName}</span>
                                        <span style="display: none;float: right;font-size: 16px;" class="time" id="projectReport">${report}</span>
                                    </h1>
                                </div>
                                <div class="left_content" style="text-align: left">
                                    <div id="projectDescription">

                                    </div>
                                    <p style="float: left"><a id="mapHref" class="media">查看样本地图</a></p>
                                    <p style="float: right"><a id="repostHref" class="media">查看项目报告</a></p>
                                    <input type="hidden" id="projectState">
                                    <input type="hidden" id="projectId">
                                </div>
                            </div>
                        </div>
                    </div>

                    <%--<div id="annotation" style="display: none">--%>
                        <%--<p>暂未上传实验报告</p>--%>
                    <%--</div>--%>

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
<%--                <a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
                    class="fa fa-qq"></i></a> <a href="#"><i class="fa fa-weibo"></i></a>
                <a href="#"><i class="fa fa-wechat"></i></a>--%>
            </div>
        </div>
    </div>
</footer>


<!-- Javascript -->
<script src="//cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-hover-dropdown/2.2.1/bootstrap-hover-dropdown.min.js"></script>
<script src="//cdn.bootcss.com/wow/1.1.2/wow.min.js"></script>
<script src="../resources/js/user_main.js"></script>
</body>

<script type="text/javascript">

    chooseActive("#projectIntro");

    <% ArrayList<String> projectNames = (ArrayList)request.getAttribute("projectNameArray");
    for(int i=0;i<projectNames.size();i++){%>
    var name = "<li class=''><a onclick='changeContent(this)'>&nbsp;&nbsp;&nbsp;<%=projectNames.get(i)%></a> <span class='fa fa-angle-right'></span></li>";
    $("#projectList").append(name);
    <%}%>

    <%--<% if(parameter == null) {
        parameter = projectNames.get(0);%>
        var parameter = '<%=parameter%>'.toString()+"";
    <%}else{%>
        var parameter = decodeURI('<%=parameter%>');
    <%}%>

    for(var i=0;i<$("#projectList li").length;i++) {
        $("#projectList li").removeClass("active");
        var element = $("#projectList li").eq(i);
        var tem = element.text().toString();
        var tem2=tem.substr(0,tem.length-1);
        var tem1 = parameter.toString();
        if(tem1 == tem2){
            $("#projectList li").eq(i).addClass("active");
            <% ArrayList<Project> projectList = (ArrayList)request.getAttribute("projectArray");
               for(int i=0;i<projectList.size();i++){%>
            if(tem1 == '<%=projectList.get(i).getName()%>'){
                $("#projectDescription").empty();
                $("#projectName").html('<%=projectList.get(i).getName()%>');
                $("#projectDescription").append('<%=projectList.get(i).getDescription()%>');
                $("#projectReport").empty();
                var d = '<%=projectList.get(i).getReport()%>';
                var d1 = d.substr(0,d.length-2);
                $("#projectReport").text(d);
                $("#projectState").val('<%=projectList.get(i).getState()%>');
            }
            <%}%>
            break;
        }
    }--%>

    $("#total_intro_panel").show();
    $("#total_project_panel").hide();

    function changeContent(data){
        $("#total_intro_panel").hide();
        $("#total_project_panel").show();
        for(var i=0;i<$("#scro1 li").length;i++) {
            $("#scro1 li").removeClass("active");
        }
        for(var i=0;i<$("#projectList li").length;i++) {
            $("#projectList li").removeClass("active");
        }
        $(data).parent().addClass("active");
        var url="../projectIntro/getInfo";
        // alert(data.innerHTML.replace(/&nbsp;/ig,''));
        $.ajax({
            type:'POST',
            url:url,
            data:{"projectName":data.innerHTML.replace(/&nbsp;/ig,'')},
            success:function (data) {
                // alert(data)
                if(data != null){
                    $("#projectDescription").empty();
                    $("#projectName").html(data.projectName2);
                    $("#projectDescription").append("<p>"+data.description2+"</p>");
                    $("#projectReport").empty();
                    $("#projectReport").text(data.report2);
                    $("#projectState").val(data.state2);
                    $("#projectId").val(data.projectId2);
                }else{
                    alert("fail");
                }
            }
        })
    }

    function tabClick(data) {
        //修改tab样式
        for(var i=0;i<$("#scro1 li").length;i++) {
            $("#scro1 li").removeClass("active");
        }
        $(data).parent().addClass("active");
        $("#total_intro_panel").show();
        $("#total_project_panel").hide();
    }

    function listTabToggle(data) {
        $("#projectList").slideToggle();
        if($(data).hasClass("open")){
            $("#pull_toggle_img").attr("src","../resources/img/pullDown.png");
            $(data).removeClass("open");
        }else{
            $("#pull_toggle_img").attr("src","../resources/img/pullUp.png");
            $(data).addClass("open");
        }
    }

    $("#repostHref").click(function () {
        if ($("#projectState").val() == 0){
            alert("暂未上传实验报告");
        }else{
            var d = $("#projectReport").text();
            $("#repostHref").prop("href","http://test.ufeng.top/web_upload/" + d);
        }
    })

    $("#mapHref").click(function () {
        var projectId = $("#projectId").val();
        // alert(projectId)
        window.location.href = "../projectIntro/getMap?projectId="+projectId;
    })
</script>
</html>