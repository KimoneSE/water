/**
 * Created by lenovo on 2017/7/18.
 */

$("#upload_but").click(function () {
    var sampleID=$("#sampleID").val();
    alert($("#sample_time").val());
    $.ajax({
        url:"/upload/j"+sampleID+"/confirm",
        type:'get',
        async:false,
        data:{"sample_time":$("#sample_time").val(),"sample_volume":$("#sample_volume").val(),
            "sample_number":$("#sample_number").val(),"sample_remark":$("#sample_remark").val()},
        success:function (data) {
            if(data){
                // toast
                var $toast = $('#toast');
                if ($toast.css('display') != 'none') return;
                $toast.fadeIn(100);
                setTimeout(function () {
                    $toast.fadeOut(100);
                }, 2000);
            }
        }
    });
});