
var id=-1;

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