function topnavclick(type) {
    if(type.name==="1"){
        window.location.href="toAdmin.do"
    }
    if(type.name==="2"){
        window.location.href="toAdmin_Sample.do"
    }
    if(type.name==="3"){
        window.location.href="toAdmin_Sample_Result.do"
    }
    if(type.name==="4"){
        window.location.href="toAdmin_Project.do"
    }
    if(type.name==="5"){
        window.location.href="toAdmin_News.do"
    }
}

function listSamples(data,pid) {
    $("#"+pid).slideToggle();
    if($(data).hasClass("open")){
        $("#pull_toggle_img"+pid).attr("src","./resources/img/pullDown.png");
        $(data).removeClass("open");
    }else{
        $("#pull_toggle_img"+pid).attr("src","./resources/img/pullUp.png");
        $(data).addClass("open");
    }
}
$(function () {
    $.ajax({
        url:"./allProject",
        type:"get",
        async:false,
        success:function(data) {
            var pros=$.parseJSON(data);
            if(pros.length>0) {
                for(var i=0;i<pros.length;i++) {
                    var projectName = pros[i].name;
                    var pid = pros[i].idProject;
                    $.ajax({
                        url:"./getSamplesByProID",
                        type:"post",
                        async:false,
                        data:{"projectID":pid},
                        success:function (res) {
                            var samples=$.parseJSON(res).sampleList;
                            if(samples.length>0) {
                                var tmp="<li class=''><a class='' onclick='listSamples(this,"+pid+")'>"+projectName+"<label style='float: right'>" +
                                    "<img id='pull_toggle_img"+pid+"' src='./resources/img/pullDown.png' style='width: 15px'/></label></a></li>"+
                                    "<div id='"+pid+"' style='display: none'>";
                                if(i===0) {
                                    tmp = "<li class=''><a class='open' onclick='listSamples(this,"+pid+")'>"+projectName+"<label style='float: right'>" +
                                        "<img id='pull_toggle_img"+pid+"' src='./resources/img/pullUp.png' style='width: 15px'/></label></a></li>"+
                                        "<div id='"+pid+"' style='display: block'>";
                                }
                                for(var j = 0; j < samples.length; j++) {
                                    if(j===0&&i===0) {
                                        tmp+="<li class='active'><a id='first' onclick='sampleClick(this,"+samples[j].sample_id+","+pid+")'>&nbsp;&nbsp;&nbsp;"+samples[j].sample_id+"</a><span class='fa fa-angle-right'></span></li>";
                                    }else{
                                        tmp+="<li class=''><a onclick='sampleClick(this,"+samples[j].sample_id+","+pid+")'>&nbsp;&nbsp;&nbsp;"+samples[j].sample_id+"</a><span class='fa fa-angle-right'></span></li>";
                                    }
                                }
                                tmp+="</div>";
                                // $("#scro4").find("li").remove();
                                $("#scro4").append(tmp);
                                $("#first").click();
                            }
                        }
                    })
                }
            }else {
                $("#content4").hide();
                $("#nothing4").show();
            }

        }
    })

    // $.ajax({
    //     url:"./getSampleList",
    //     type:"post",
    //     async:false,
    //     success:function (data) {
    //         var obj = $.parseJSON(data);
    //         var  sampleList = obj;
    //         if(sampleList.length>0){
    //             sampleScro(sampleList);
    //             setSampleInfo(sampleList[0]);
    //         }
    //         else{
    //             $("#content4").hide();
    //             $("#nothing4").show();
    //
    //         }
    //     }
    // });

    $("#search1").click(function () {
        var id = $("#input1").val();
        $.ajax({
            url: "./getSample",
            type: "post",
            async: false,
            data:{"id":id},
            success: function (data) {
                var obj = $.parseJSON(data);
                if(obj!==null){

                    setSampleInfo(obj);
                    $("#scro4").find("li").each(function() {
                        $(this).removeClass("active");
                    });
                    $("#scro4").find("a").each(function () {
                        if($(this).html().replace("&nbsp;&nbsp;&nbsp;","")===id) {
                            $(this.parentNode).addClass("active");
                            var pid = $(this.parentNode.parentNode).attr("id");
                            var img = document.getElementById("pull_toggle_img"+pid);
                            if(!$(img.parentNode.parentNode).hasClass("open")) {
                                $("#"+pid).slideToggle();
                            }
                            $(img.parentNode.parentNode).addClass("open");
                            $("#pull_toggle_img"+pid).attr("src","./resources/img/pullUp.png");
                        }
                    })
                }
                else {
                    alert("样本编号不存在");
                }
            }
        });

    });
    function  sampleScro(list) {
        $("#scro4").find("li").remove();
        $("#scro4").append("<li class='active'><a onclick='sampleClick(this)'>"+list[0].sampleID+"</a><span class='fa fa-angle-right'></span></li>")
        for(var i =1;i<list.length;i++){
            $("#scro4").append("<li class=''><a onclick='sampleClick(this)'>"+list[i].sampleID+"</a><span class='fa fa-angle-right'></span></li>")
        }
    }

});

function  setSampleInfo(temp) {
    $.ajax({
        url: "./getSampleResult",
        type: "post",
        async: false,
        data:{"idSample":temp.sampleID},
        success: function (data) {
            $("#sample-result").html(data);
            $("#reason").val(data);

        }
    });

    var pID = temp.projectID;
    var btn = '<button id="download" class="button btn-info" onclick="download('+pID+')">导出该项目的所有样本信息</button>' +
        '<a id="export" type="hidden" download="" href="" ></a>';
    $("#projectBlock").empty();
    $("#projectBlock").append(btn);
    $("#sample").html(temp.sampleID);
    $(".sample_time").html(timeFormatter1(temp.sampleDate));
    $("#content4").find("span[name='name']").each(function (index) {
        if(index===0)
            $(this).html(temp.apply.name);
        if(index===1)
            $(this).html(temp.apply.idApply);
        if(index===2)
            $(this).html(timeFormatter1(temp.apply.applyDate));
        if(index===3)
            $(this).html(temp.projectName);
        if(index===4)
            $(this).html(temp.volume+"mL");
        if(index===5)
            $(this).html(temp.ammoniaN_c);
        if(index===6)
            $(this).html(temp.phosphate_c);
        if(index===7)
            $(this).html(temp.apply.waterAddress);
        if(index===8)
            $(this).html(temp.remark);
        if(index===9)
            $(this).html(temp.apply.number);
        if(index===10)
            $(this).html(temp.apply.longitude+"°");
        if(index===11)
            $(this).html(temp.apply.latitude+"°");
        if(index===12)
            $(this).html(temp.temperature+"℃");
        if(index===13)
            $(this).html(temp.weather);
        if(index===14){
            if(temp.state===1) {
                $(this).html("处理中");
                $("#confirm").hide();
                $("#result").hide();
            }
            if(temp.state===2) {
                $(this).html("已上传实验结果");
                $("#confirm").hide();
                $("#result").show();
            }
            if(temp.state===0) {
                $(this).html("待收取");
                $("#confirm").show();
                $("#result").hide();
            }
        }


        $("#downlo1").attr("href","/samples/"+temp.sampleID+".txt");
        $("#downlo1").attr("download",temp.sampleID+".txt");

    })
    $("#content4").find("ol").each(function () {
        $(this).empty();
        for(var i=0;i<temp.apply.image.length;i++){
            $(this).append("<li data-target='#myCarousel3' data-slide-to='"+i+"'></li>");
        }
    })
    $("#content4").find(".carousel-inner").each(function () {
        $(this).empty();
        $(this).append("<div class='item active'>"+
            "<img src='http://test.ufeng.top/web_upload/"+temp.apply.image[0]+"'></div>");
        for(var i=1;i<temp.apply.image.length;i++){
            $(this).append("<div class='item'>"+
                "<img src='http://test.ufeng.top/web_upload/"+temp.apply.image[i]+"'></div>");
        }
    })
}

function download(projectID) {
    $.ajax({
        url: "./exportExcel",
        type: "post",
        data:{"projectID":projectID},
        success: function (data) {
            var filename = data;
            $("#export").attr("href","/projectSample/"+projectID+".xlsx");
            $("#export").attr("download",filename+".xlsx");
            document.getElementById("export").click();
        }
    })
}

function  sampleClick(type,id) {
    $.ajax({
        url: "./getSample",
        type: "post",
        async: false,
        data:{"id":id},
        success: function (data) {
            var obj = $.parseJSON(data);
            setSampleInfo(obj)
        }
    });

    $("#scro4").find("li").each(function() {
        $(this).removeClass("active");
    });
    $(type.parentNode).addClass("active");
}

function confirmClik(type) {
        if(confirm("确认更改状态")){
            var id = $("#sample").html();
            $.ajax({
                url: "./sampleState",
                type: "post",
                async: false,
                data:{"idSample":id,"state":1},
                success: function (data) {
                    var bool = $.parseJSON(data);
                    if(bool){
                        alert("已改为处理中");
                        $("#sample-state").html("处理中");
                        $("#confirm").hide();
                    }
                }
            });
        }
        else{
            alert("取消");
        }
}

function modifyClik(type) {
    var id = $("#sample").html();
    $.ajax({
        url: "./modifyResult",
        type: "post",
        data: {"idSample": id, "description": $("#reason").val()},
        success: function (data) {
            if(data) {
                alert("修改成功！");
                $("#myModal").modal("hide");
                $("#sample-result").html($("#reason").val());
            }
        }
    })
}

function timeFormatter1(value) {
    return (1900 + value.year) + "-" + (value.month + 1) + "-" + value.date + " " + value.hours + ":" + value.minutes + ":" + value.seconds;
}