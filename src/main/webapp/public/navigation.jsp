<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>eRivermap</title>

    <!-- CSS -->

    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster">
    <link href="//cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/animate.css/3.5.2/animate.css" rel="stylesheet">
    <link rel="stylesheet" href="../resources/css/styles.css">
    <link rel="stylesheet" href="../resources/css/test.css">
    <link rel="stylesheet" href="../resources/css/engagement.css ">
    <link rel="stylesheet" href="../resources/css/user_main.css">
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
<nav class="navbar" role="navigation">

    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="top-navbar-1">
            <ul class="nav navbar-nav navbar-right" id="selectNav">
                <li id="index"><a href="../index.jsp"><i
                        class="fa fa-2x fa-tasks"></i><br>首页</a></li>

                <li id="engagement"><a href="../public/engagement.jsp"><i
                        class="fa fa-2x fa-handshake-o"></i><br>公众参与</a>
                </li>

                <li id="projectIntro"><a href="../projectIntro/init"><i
                        class="fa fa-2x fa-list-alt"></i><br>项目介绍</a>
                </li>
                <li id="teamIntro"><a href="../public/teamIntro.jsp"><i
                        class="fa fa-2x fa-file-text-o"></i><br>团队介绍</a>
                </li>
                <li id="knowledge"><a href="../public/knowledge.jsp"><i
                        class="fa fa-2x fa-file-text-o"></i><br>科普知识</a>
                </li>
                <%--<li id="sampleMap"><a href="../public/sampleMap.jsp"><i--%>
                        <%--class="fa fa-2x fa-map-marker "></i><br>样本地图</a>--%>
                <%--</li>--%>

            </ul>

        </div>
    </div>

</nav>

</body>

</html>