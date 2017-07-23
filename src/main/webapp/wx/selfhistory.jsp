<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>个人中心</title>
    <!-- 引入 WeUI -->
    <link rel="stylesheet" href="//res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css"/>
    <script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
</head>
<body>
<input type="hidden" id="userID" value="${userID}"/>
<div class="weui-tab">
    <div class="weui-navbar">
        <div id="unchecked_tab" class="weui-navbar__item weui-bar__item_on">
            待审核
        </div>
        <div id="checked_tab" class="weui-navbar__item">
            已审核
        </div>
        <div id="sampling_tab" class="weui-navbar__item">
            已采样
        </div>
    </div>
    <div class="weui-tab__panel">
        <div id="unchecked_tab_panel" style="display: block">
            <div class="unchecked_item" style="padding-left: 4%;padding-right:4%;padding-top: 2.6%;
            padding-bottom: 2.6%;color: grey;font-size: 15px;">
                <div class="unchecked_message" style="width: 90%;float: left">
                    <p>水域地址：<label style="color: black">江苏省南京市栖霞区</label></p>
                    <p>姓名： <label style="color: black">曾虎双</label>
                        <label style="color: black;float:right">12345678910</label></p>
                    <p>收货地址：<label style="color: black">江苏省南京市栖霞区仙林大道163号</label></p>
                </div>
                <div style="height:12%;float: right;padding-left: 2%;border-left: 1px;
                    border-left-style: dashed;margin-bottom: 3%">
                    <br/>
                    <img class="delete_unchecked" src="/resources/img/delete.png" style="width:25px"/>
                </div>
            </div>
            <hr style="width: 100%">
            <div class="unchecked_item"
                 style="padding-left: 4%;padding-right:4%;padding-top: 2.6%;padding-bottom: 2.6%;
                 color: grey;font-size: 15px;">
                <div class="unchecked_message" style="width: 90%;float: left">
                    <p>水域地址：<label style="color: black">江苏省南京市栖霞区</label></p>
                    <p>姓名： <label style="color: black">曾虎双</label>
                        <label style="color: black;float:right">12345678910</label></p>
                    <p>收货地址：<label style="color: black">江苏省南京市栖霞区仙林大道163号</label></p>
                </div>
                <div style="height:12%;float: right;padding-left: 2%;border-left: 1px;
                    border-left-style: dashed;margin-bottom: 3%">
                    <br/>
                    <img class="delete_unchecked" src="/resources/img/delete.png" style="width:25px"/>
                </div>
            </div>
            <hr style="width: 100%">
        </div>
        <div id="checked_tab_panel" style="display: none">
            <div class="checked_item" style="padding-left: 4%;padding-right:4%;padding-top: 2.6%;
            color: grey;font-size: 15px;">
                <div class="checked_message" style="width: 90%;float: left">
                    <p>水域地址：<label style="color: black">江苏省南京市栖霞区</label></p>
                    <p>姓名： <label style="color: black">曾虎双</label>
                        <label style="color: black;float:right">12345678910</label></p>
                    <p>收货地址：<label style="color: black">江苏省南京市栖霞区仙林大道163号</label></p>
                    <p>状态：<img src="/resources/img/pass.png" style="width: 15px"/>已通过</p>
                </div>
                <div style="height:86px;float: right;padding-left: 2%;border-left: 1px;
                    border-left-style: dashed;margin-bottom: 3%">
                    <br/>
                    <img class="upload_pass" src="/resources/img/upload.png" style="padding-top:10px;width:25px"/>
                </div>
            </div>
            <hr style="width: 100%">
        </div>
        <div id="sampling_tab_panel" style="display: none">
            <div class="sampling_item"
                 style="padding-left: 15px;padding-right:15px;padding-top: 10px;color: grey;font-size: 15px">
                <p>采样地点：<label style="color: black">江苏省南京市栖霞区</label></p>
                <p>经纬度：<label style="color: black">（12,120）</label>
                    <label style="float: right">采样体积：<label style="color: black">12</label></label></p>
                <p style="padding-bottom: 5px">采样备注：<label style="color: black">呜呜呜呜呜呜呜呜呜</label></p>
                <p style="font-size: 14px">采样时间：2017-07-20 17:33
                    <label style="float:right">编号：123</label></p>
            </div>
            <hr>
            <div class="sampling_item"
                 style="padding-left: 15px;padding-right:15px;padding-top: 10px;color: grey;font-size: 15px">
                <p>采样地点：<label style="color: black">江苏省南京市栖霞区</label></p>
                <p>经纬度：<label style="color: black">（12,120）</label>
                    <label style="float: right">采样体积：<label style="color: black">12</label></label></p>
                <p style="padding-bottom: 5px">采样备注：<label style="color: black">呜呜呜呜呜呜呜呜呜</label></p>
                <p style="font-size: 14px">采样时间：2017-07-20 17:33
                    <label style="float:right">编号：123</label></p>
            </div>
            <hr>
        </div>
    </div>
</div>
<!--toast-->
<div id="toast" style="display: none;">
    <div class="weui-mask_transparent"></div>
    <div class="weui-toast">
        <i class="weui-icon-success-no-circle weui-icon_toast"></i>
        <p class="weui-toast__content">已删除</p>
    </div>
</div>
</body>
<script src="/resources/js/selfhistory.js"></script>
<script>
    $(".delete_unchecked").click(function () {
        var index=$(".delete_unchecked").index(this);
        ondelete(index);
    });
    $(".unchecked_message").click(function () {
       var index=$(".unchecked_message").index(this);
       var isChecked=0;
       onConcreteApply(index,isChecked);
    });
</script>
</html>