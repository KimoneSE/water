/**
 * Created by zhanglei on 2017/7/20.
 */
function on_unchecked_click() {
    $("#unchecked_tab").addClass("weui-bar__item_on");
    $("#checked_tab").removeClass("weui-bar__item_on");
    $("#sampling_tab").removeClass("weui-bar__item_on");
    $("#unchecked_tab_panel").show();
    $("#checked_tab_panel").hide();
    $("#sampling_tab_panel").hide();
}
function on_checked_click() {
    $("#unchecked_tab").removeClass("weui-bar__item_on");
    $("#checked_tab").addClass("weui-bar__item_on");
    $("#sampling_tab").removeClass("weui-bar__item_on");
    $("#unchecked_tab_panel").hide();
    $("#checked_tab_panel").show();
    $("#sampling_tab_panel").hide();
}
function on_sample_click() {
    $("#unchecked_tab").removeClass("weui-bar__item_on");
    $("#checked_tab").removeClass("weui-bar__item_on");
    $("#sampling_tab").addClass("weui-bar__item_on");
    $("#unchecked_tab_panel").hide();
    $("#checked_tab_panel").hide();
    $("#sampling_tab_panel").show();
}

function personal_info() {
    window.location.href='../j'+userID+'?next=default';
}

function confirmDelete() {
    $.ajax({
        url: "history/deleteUnChecked",
        type: 'get',
        async: false,
        data: {"index": deleteIndex},
        success: function (data) {
            if (data) {
                $("#dialog").hide();
                // toast
                var $toast = $('#toast');
                if ($toast.css('display') !== 'none') return;
                $toast.fadeIn(100);
                setTimeout(function () {
                    $toast.fadeOut(100);
                    window.location.reload();
                }, 2000);
            }
        }
    });
}

function onUpload(index,result) {
    $.ajax({
        url: "history/jumpToUpload",
        type: 'get',
        async: false,
        data: {"index": index},
        success: function (uploadID) {
            window.location.href=basePath+"upload/j"+uploadID+"?sampleID="+result;
        }
    });
}

var userID = $("#userID").val();
function onConcreteApply(applyID) {
    window.location.href = "history/apply?applyID=" + applyID;
}

function onConcreteSample(sampleID) {
    window.location.href = "history/sample?sampleID=" + sampleID;
}

function onAddApply() {
    window.location.href=basePath+'init/j'+userID;
}

function noItemTip(container) {
    container.append("<div class='weui-loadmore weui-loadmore_line'>"+
        "<span class='weui-loadmore__tips'>暂无数据</span>"+
        "</div>");
}