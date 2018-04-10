
var id=-1;

function getIndexNews() {
    $.ajax({
        url: "getIndexNews",
        type:"get",
        success:function (data) {
            var newsList = $.parseJSON(data);
            if(newsList.length>0) {
                var tmp='<div class="flexslider"><ul class="slides">';

                // $("#news").empty();
                // $("#carousel-indicators").empty();
                // $("#carousel-inner").empty();
                for(var i=0;i<newsList.length;i++) {
                    var imgSrc = "http://test.ufeng.top/web_upload/"+newsList[i].cover;
                    var title = newsList[i].title;
                    var newsID = newsList[i].id;
                    // tmp+='<li><a href=""><img src="'+imgSrc+'"></a>' +
                    //     '<div style="position:absolute; bottom:0; right:0; width:100%; padding:10px; background:#333; opacity:.8; color:#fff">' +
                    //     '<p>'+title+'</p></div></li>'
                    $("#news").append('<li><a href="http://test.ufeng.top/water/concrete_news?id='+newsID+'"><img src="'+imgSrc+'"></a>' +
                        '<div style="position:absolute; bottom:0; right:0; width:100%; padding:10px; background:#333; opacity:.8; color:#fff">' +
                        '<p>'+title+'</p></div></li>');
                    // if(i==newsList.length-1) {
                    //     $("#news").append('</ul></div>');
                    // }
                    // if(i==0) {
                    //     $("#carousel-indicators").append("<li data-target='#myCarousel3' data-slide-to='"+i+"' class='active'></li>");
                    //     $("#carousel-inner").append("<div class='item active'>" +
                    //         "<img src='"+imgSrc+"' <div class='carousel-caption'>"+title+"</div></div>");
                    // }else {
                    //     $("#carousel-indicators").append("<li data-target='#myCarousel3' data-slide-to='"+i+"'></li>");
                    //     $("#carousel-inner").append("<div class='item'>" +
                    //         "<img src='"+imgSrc+"' <div class='carousel-caption'>"+title+"</div></div>");
                    // }
                }
                // tmp+='</ul></div>';
                // $("#news").append(tmp);
            }else {
                $("#news").empty();
            }

        }
    });
}

function load() {
    var url="./getLatestPro";
    $.ajax({
        type:"Post",
        url:url,
        async:true,
        success:function (data) {
            if(data==null){
                var headline=document.getElementById("proName");
                headline="暂无项目";
            }
            else {
                var objects=data;
                // var objects=JSON.parse(data);
                var headline=document.getElementById("proName");
                var body=document.getElementById("proContent");
                var time=document.getElementById("proDate");

                var name=data.name;
                var content=getText(data.description).substring(0,301)+'...';
                var year=parseInt(data.date['year'])+1900;
                var month=parseInt(data.date['month'])+1;
                var day=data.date['date'];
                var date=year+'-'+month+'-'+day;

                headline.innerHTML=name;
                body.innerHTML=content;
                time.innerHTML=date;
            }
        }
    })
}

function gotoProDetail() {
    if(name==null||name==""){
        window.location.href="./projectIntro/init";
    }
    else {
        window.location.href = "./projectIntro/init?name=" + encodeURI(name) + " ";
    }
}

function getText(str){
    str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
    str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
    str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
    str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
    str=str.replace(/\s/g,''); //将空格去掉
    return str;
}

function chooseActive(elementid){
    $(elementid).addClass("active");
    $('#selectNav').children().click(function () {
        $(this).parent().children().removeClass('active');
        $(this).addClass('active');
    });
}