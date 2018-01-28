<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map"%>
<%@ page import="com.water.util.JsSignUtil"%>
<%// 注意要动态获取当前页面URL，否则invalid signature
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String url = (String)session.getAttribute("waterAddressURL");
    //String url = request.getScheme() + "://" + request.getServerName() + request.getRequestURI();
    // 注意这里的坑
    if (request.getQueryString() != null) {
        url = url + "?" + request.getQueryString();
    }
    // URL要取#号之前！！！
    Map<String, String> ret = JsSignUtil.sign(url.split("#")[0]);%>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>确认地址</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <script src="<%=basePath%>resources/jquery/1.11.3/jquery.min.js"></script>
    <!-- 引入 WeUI -->
    <link rel="stylesheet" href="//res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css"/>
    <!--引用百度地图API-->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="百度地图,百度地图API，百度地图自定义工具，百度地图所见即所得工具" />
    <meta name="description" content="百度地图API自定义地图，帮助用户在可视化操作下生成百度地图" />
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=X6CxGSyvVtNop7RGgaVAGtyWzM4xpYiG"></script>
    <!--加载检索信息窗口-->
    <script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.js"></script>
    <link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css" />
    <script src="http://code.changer.hk/jquery/plugins/jquery.cookie.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
</head>
<body>
<%--<input type="hidden" id="userID" value="${userID}"/>--%>
<%--<input type="hidden" id="longitude"/>--%>
<%--<input type="hidden" id="latitude"/>--%>
<%--<div id="location_message">--%>
    <%--<div class="weui-cells">--%>
        <%--<div class="weui-cell">--%>
            <%--<div class="weui-cell__hd">--%>
                <%--<img src="<%=basePath%>resources/img/position.png" alt="" style="width:20px;margin-right:10px;display:block">--%>
            <%--</div>--%>
            <%--<div class="weui-cell__bd">--%>
                <%--<div id="concrete_address" class="weui-cell__bd" style="font-size: 16px;color: grey"><p>详细地址</p></div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="weui-cell">--%>
            <%--<div class="weui-cell__hd">--%>
                <%--<label class="weui-label" style="font-size: 16px;color: grey;">搜索地址：</label>--%>
            <%--</div>--%>
            <%--<div class="weui-cell__bd">--%>
                <%--<input id="searchInput" class="weui-input input_address" type="text" placeholder="输入地址" style="font-size:16px;color:black;"/>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>--%>
    <%--</div>--%>
<%--</div>--%>
<div id="map" style="margin-left:10px;margin-right: 10px;height:400px;border:#ccc solid 1px;font-size:12px"></div>
<%--<p style="padding-top: 10px;padding-bottom: 10px">--%>
    <%--<a style="width: 85%;align-self: center;line-height: 35px" href="javascript:;" id="confirm_but"--%>
       <%--class="weui-btn weui-btn_plain-primary">确定</a>--%>
<%--</p>--%>
<%--<!--toast-->--%>
<%--<div id="toast" style="display: none;">--%>
    <%--<div class="weui-mask_transparent"></div>--%>
    <%--<div class="weui-toast">--%>
        <%--<i class="weui-icon-success-no-circle weui-icon_toast"></i>--%>
        <%--<p class="weui-toast__content">得到水域地址</p>--%>
    <%--</div>--%>
<%--</div>--%>
</body>
<script src="<%=basePath%>resources/js/inputPrompt.js"></script>
<%--<script src="<%=basePath%>resources/js/confirm_address.js"></script>--%>
<script>
    wx.config({
        debug: false,
        appId: '<%=ret.get("appId")%>',
        timestamp:'<%=ret.get("timestamp")%>',
        nonceStr:'<%=ret.get("nonceStr")%>',
        signature:'<%=ret.get("signature")%>',
        jsApiList : [ 'checkJsApi', 'openLocation', 'getLocation' ]
    });

    wx.error(function(res) {
        alert('<%=ret%>');
        alert("出错了：" + res.errMsg);
    });

    wx.ready(function() {
        wx.getLocation({
            type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
            success : function(res) {
                alert("获取地理位置成功，经纬度为：（" + res.latitude + "，" + res.longitude
                    + "）");
                wx.openLocation( {
                    longitude: res.longitude,
                    latitude: res.latitude
                })
            },
            fail : function(error) {
                AlertUtil.error("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
            }
        });
    });

</script>
</html>