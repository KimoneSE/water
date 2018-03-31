<%--
  Created by IntelliJ IDEA.
  User: Kimone
  Date: 2018/3/21
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新闻管理 - 后台管理</title>

    <!-- CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <link href="${pageContext.request.contextPath}/resources/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <style>
        .mylabel {
            margin-right: 20px;
            font-size:18px;
            padding-top:5px;
            color: #337AB7;
            font-weight: normal;
        }
        .container-img{
            margin-top: 34px;
            width:1025px;
            margin-left:50px;
        }
    </style>
</head>
<body>
<!--top menu-->
<nav class="navbar" role="navigation">

    <div class="container">
        <div class="navbar-header">
            <a href="#" class="logo">eRiver<span>Map</span></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="top-navbar-1">
            <ul id="nav" class="nav navbar-nav navbar-right">
                <li><a href="#"onclick="topnavclick(this)" name="1"><i
                        class="fa fa-2x fa-check-circle-o" ></i><br>审核申请</a></li>

                <li><a href="#" onclick="topnavclick(this)" name="2"><i
                        class="fa fa-2x fa-envelope-open-o"></i><br>采样信息</a>
                </li>

                <li><a href="#" onclick="topnavclick(this)" name="3"><i
                        class="fa fa-2x fa-sticky-note"></i><br>实验结果</a>
                </li>
                <li><a href="#" onclick="topnavclick(this)" name="4"><i
                        class="fa fa-2x fa-newspaper-o"></i><br>项目发布</a>
                </li>
                <li class="active"><a href="#" onclick="topnavclick(this)" name="5"><i
                        class="fa fa-2x fa-wpforms"></i><br>新闻管理</a>
                </li>

                <ul class="social-links">
                    <div id="top-navigation">
                        Welcome! <a><strong>Administrator</strong></a>
                        <span>|</span>
                        <a href="Admin">Log out</a>
                    </div>
                </ul>
            </ul>

        </div>
    </div>

</nav>

<!-- Page Title -->
<div class="page-title-container">
    <div class="container">
        <div class="row">
            <div class="col-sm-7 wow fadeIn">
                <i class="fa fa-wpforms"></i>
                <h1>NEWS MANAGEMENT/</h1>
                <p>新闻管理</p>
            </div>
            <%--<div class=" list-switch" style="margin-right:20px; margin-top:10px;"><ul><li id="show-list" class=""></li><li id="show-info" class="active"></li></ul></div>--%>
        </div>
    </div>
</div>

<!-- content -->
<div class="content">
    <div class="container">
        <div class="intro-panel">
            <div class="row" style="padding-top:30px;padding-left:50px">
                <div>
                    <label class="pull-left mylabel ">新闻标题：</label>
                    <input id="newsTitle" class="col-md-6 col-xs-6">
                </div>
            </div>

            <div class="row" style="padding-top:30px;padding-left:50px">
                <label class="pull-left mylabel">上传新闻封面：</label>
            </div>
            <div class="container-img">
                <div>
                    <input id="file-cover" type="file" name="file"  class="file" data-overwrite-initial="false" multiple data-min-file-count="1">
                </div>
                <input type="hidden" id="cover"/>
                <%--<form  enctype="multipart/form-data" >--%>
                    <%----%>
                    <%--<hr>--%>
                    <%--<br>--%>
                <%--</form>--%>
                <%--<div>--%>
                    <%--<input id="file-cover" type="file">--%>
                <%--</div>--%>
            </div>

            <div class="row" style="padding-top:10px;padding-left:50px">
                <label class="mylabel pull-left">上传新闻文件：</label>
            </div>
            <div class="container-img">
                <div>
                    <input id="file-word" type="file" name="file" multiple class="file" data-show-upload="false">
                </div>
                <input type="hidden" id="word"/>
                <%--<form  enctype="multipart/form-data" >--%>
                    <%----%>
                    <%--<hr>--%>
                    <%--<br>--%>
                <%--</form>--%>
            </div>

            <div style="margin-top: 30px;padding-bottom: 30px">
                <button id="save" onclick="save()" type="button" class="btn btn-info ">保存</button>
                <button id="reset" onclick="reset()" type="button" class="btn btn-default" style="margin-left:20px">重置</button>

            </div>
            <%--<div class="row" style="height: 500px">--%>
                <%--<input id="file-word" type="file">--%>
            <%--</div>--%>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/1.11.3/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Admin_Work_News.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/fileinput.js" type="text/javascript"></script>
<script src="//cdn.bootcss.com/wow/1.1.2/wow.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-ui-bootstrap/0.5pre/assets/js/jquery-ui-1.10.0.custom.min.js"></script>

<script>
    var date = new Date();
    var filename = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds();

    function reset() {
        window.location.href = "toAdmin_News.do";
    }

    function save() {
        if($("#newsTitle").val()=="") {
            alert("请填写新闻标题");
        } else if($("#cover").val()==''){
            alert("请上传封面");
        } else if($("#word").val()==''){
            alert("请上传文件");
        }
        else {
            $("#file-cover").fileinput("upload");
            $("#file-word").fileinput("upload");
            $.ajax({
                url:"./saveNews",
                type:"post",
                data:{"title":$("#newsTitle").val(),"cover":$("#cover").val(),"word":$("#word").val(),"filename":filename},
                success: function (data) {
                    alert("上传成功");
                    reset();
                }
            })
        }
    }

    $("#file-cover").fileinput({
        language: 'zh',
        uploadUrl: './uploadNewsImg',
        allowedFileExtensions: ['jpg', 'png', 'jpeg'],
        maxFileSize: 10000,
        maxFileCount: 1,
        showUpload: false, //是否显示上传按钮
       // showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        layoutTemplates :{
            actionDelete:'', //去除上传预览的缩略图中的删除图标
            actionUpload:'',//去除上传预览缩略图中的上传图片；
            actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
        },
        uploadExtraData: function () {
            return {"filename" : filename};
        }
    }).on("filebatchselected", function(event, files) {
        $("#cover").val(files[0].name)
    }).on("filecleared",function(event, data, msg){
        $("#cover").val("")
    });

    $("#file-word").fileinput({
        language: 'zh',
        uploadUrl: './uploadNewsFile',
        allowedFileExtensions: ['doc', 'docx'],
        maxFileSize: 10000,
        maxFileCount: 1,
        showUpload: false, //是否显示上传按钮
        //showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        previewFileIcon: "",
        layoutTemplates :{
            actionDelete:'', //去除上传预览的缩略图中的删除图标
            actionUpload:'',//去除上传预览缩略图中的上传图片；
            actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
        },
        uploadExtraData: function () {
            return {"filename" : filename};
        }
    }).on("filebatchselected", function(event, files) {
        $("#word").val(files[0].name)
    }).on("filecleared",function(event, data, msg){
        $("#word").val("")
    });
</script>
</body>
</html>
