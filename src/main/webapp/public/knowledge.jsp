<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>eRivermap-科普知识</title>

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
                <h1><span>KNOWLEDGE</span>&nbsp;&nbsp;&nbsp;<span>POPULARIZATION</span>/</h1>
                <p>科普知识</p>
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
                            <%--<li class=''><a class="open" onclick='knowledgeTabToggle(this)'>科普知识
                                <label style="float: right"><img id="pull_toggle_img" src="../resources/img/pullUp.png" style="width: 15px"/></label></a>
                            </li>--%>
                            <div id="knowledge_content" style="display: block">
                                <li class='active'><a onclick='tabClick(this)'>河流生态的意义</a>
                                    <span class='fa fa-angle-right'></span></li>
                                <li class=''><a onclick='tabClick(this)'>生物多样性</a>
                                    <span class='fa fa-angle-right'></span></li>
                                <li class=''><a onclick='tabClick(this)'>基因组学</a>
                                    <span class='fa fa-angle-right'></span></li>
                            </div>
                        </ul>
                        <div id="scrol_content">

                            <div id="river_mean_panel" style="display: none" class="tab_panel indicator-group-content">
                                <div class="left_title">
                                    <i class="fa fa-paper-plane-o blue"></i>
                                    <h1 class="number" style="font-size: 25px">河流生态的意义</h1>
                                    <a class="time">2017-7-30</a>
                                </div>
                                <div class="left_content" style="text-align: left">
                                    <p>
                                        当然，我们对于生态系统的关注并非只是一种情怀，或仅仅出于“对地球的无私保护”，事实上一个健康的生态系还能给人类提供很多好处，包括干净的水和空气，提供食物或其他原料的来源，作为人们娱乐观赏的场所和对象，为多个领域的科学研究提供生物材料，例如医药、转基因作物等等……</p>
                                    <p>
                                        我们的城市中有大量受到不同程度污染的河流，其中受污染严重，氮磷含量高，化学需氧量高，色度较深，散发恶臭的被称为黑臭河道。这些河流的生态系统往往遭到严重破坏，生物多样性和完整性明显不足。</p>
                                    <p>
                                        对人类而言，城市河流污染一方面影响感官体验，另一方面，污染物会通过各种途径影响到人类健康。一是随着食物链不断放大，在较高营养等级的动物如鱼虾体内富集，部分水生生物还会进入近海，污染物随之迁移到更大的范围内。最终由于人类的垂钓、捕捞、食用等一系列活动，这些污染物通过动物进入人类体内。二是部分河流作为饮用水源或生活用水水源，受污染后直接接触人体。</p>
                                    <p>对自然而言，污染物影响着种群的存续，破坏了正常的群落结构，使生态系统的功能受到损伤。从人与自然和谐的角度而言，保护生态环境是十分必要的。</p>
                                </div>
                            </div>
                            <div id="biodiversity_panel" style="display: none" class="tab_panel indicator-group-content">
                                <div class="left_title">
                                    <i class="fa fa-paper-plane-o blue"></i>
                                    <h1 class="number" style="font-size: 25px">生物多样性</h1>
                                    <a class="time">2017-7-30</a>
                                </div>
                                <div class="left_content" style="text-align: left">
                                    <p>什么样的河流生态系统才是健康的？</p>
                                    <br/>
                                    <p>
                                        自然界的生态系统就如同人类社会中的工厂一样，有着许多不同的“部门”，各自行使一定的功能，互相联系从而形成一个稳定、平衡的结构。如将太阳光和无机物转化成可利用能量和有机物的生产者，以生产者为食同时又为肉食者提供能量和物质初级消费者，以其他消费者为食的高级消费者，促进物质循环的分解者等等……我们将生态系统中生物的丰富程度成为生物多样性。生态系统中不同的物种数量越多，每一物种的丰度越大，生物多样性就越高，生态系统往往也就越稳定——设想，一个生态系统有多种生产者就如同一个工厂有多个原料供应商，当其中一个因故停止供应原料时，仍然可以依靠其余的几个维持正常的运行。对于生产者以外的其他生态系统成分，也是同样的道理。总之，生物多样性越高，生态系统就越稳定，越能够行使其正常功能，这样的河流生态系统也就越健康。</p>
                                    <p>
                                        和人一样，生态系统也会“生病”。一个不健康的生态系统会因为丧失部分敏感的物种或植被而退化。一条不健康的河流会由于各种人为因素而导致鱼类减少甚至绝迹，在严重的化学污染下可能只有几种对污染物耐受性较高的无脊椎动物和部分微生物。因此，通过对生态系统群落结构的确定，我们可以判断出河流生态系统的健康与否。相比于传统的物理、化学指标，生态监测可以更加直接地反映河流生态系统的实际污染状况，并且综合了除理化因素之外的其他因素如河道流量、底质结构等的影响，更加全面。在大量调查的基础上，今后还可以建立更加量化的标准，以达到比理化指标更好的评价效果。</p>
                                </div>
                            </div>
                            <div id="genomics_panel" style="display: none" class="tab_panel indicator-group-content">
                                <div class="left_title">
                                    <i class="fa fa-paper-plane-o blue"></i>
                                    <h1 class="number" style="font-size: 25px">基因组学（我们的方法）</h1>
                                    <a class="time">2017-7-30</a>
                                </div>
                                <div class="left_content" style="text-align: left">
                                    <p>
                                        我们团队采用基因组学的方法对河流生态进行监测。DNA是生物的遗传物质，每个物种的DNA上都有独一无二的序列，据此可以对生物进行分类。我们使用滤膜截留水样中的细菌、藻类、浮游动物等，并提取它们的DNA进行分析，从而确定所采水样所在的河流有着怎样的群落结构，包括有哪些物种，每个物种的相对丰度，所处的营养级等等。分析过程包括提取、扩增、测序等步骤。</p>
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
    chooseActive("#knowledge");

    for(var i=0;i<$(".tab_panel").length;i++){
        $(".tab_panel").hide();
    }
    $("#river_mean_panel").show();

    // function knowledgeTabToggle(data) {
    //     $("#knowledge_content").slideToggle();
    //     if($(data).hasClass("open")){
    //         $("#pull_toggle_img").attr("src","../resources/img/pullDown.png");
    //         $(data).removeClass("open");
    //     }else{
    //         $("#pull_toggle_img").attr("src","../resources/img/pullUp.png");
    //         $(data).addClass("open");
    //     }
    // }

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
            $("#river_mean_panel").show();
        }else if(index==1) {
            $("#biodiversity_panel").show();
        }else if(index==2) {
            $("#genomics_panel").show();
        }
    }

</script>
</body>

</html>