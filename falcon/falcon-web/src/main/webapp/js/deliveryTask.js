$(function(){
    $("#delivery_task_dialog_section_sectionNameStr").click(function () {
       $.getScript(getValue('#contextPath') + "/js/common/sectionDialog.js", function () {
            $.sectionDialog.open(sectionCallBack,"CHECKBOX");
       });

    });

    $("#delivery_task_dialog_section_employeeNameStr").click(function () {
        $.getScript(getValue('#contextPath') + "/js/common/employeeDialog.js", function () {
            $.employeeDialog.open(employeeCallBack,"CHECKBOX");
        });

    });
});

function sectionCallBack(idStr,nameStr){
    $("#delivery_task_dialog_section_sectionIdStr").val(idStr);
    $("#delivery_task_dialog_section_sectionNameStr").val(nameStr);
}

function employeeCallBack(idStr,nameStr){
    $("#delivery_task_dialog_section_employeeIdStr").val(idStr);
    $("#delivery_task_dialog_section_employeeNameStr").val(nameStr);
}

function pageOnLoad(){
    var oTable = $('#deliveryTask-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide" : true,
            "sServerMethod" : "POST",
            "sAjaxSource" : getValue('#contextPath')+"/deliverytask/search",
            "bProcessing" : true,
            "aaSorting": [[0,'desc']],
            "oLanguage": {
                "sProcessing": "正在获取数据，请稍候..."
            },
            "aoColumns" : [
                { "sWidth": "1%",
                    "bSortable": true,
                    "sTitle": '<input type="checkbox" class="ace" onclick="headBoxOnclick(this)"  /><span class="lbl"></span>',
                    "mData": "id","iDataSort": "ID"
                },
                {"sWidth" : "8%","bSortable" : true,"iDataSort": "CODE","sTitle" : "任务编号","mData" : "code"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort": "REGION","sTitle" : "作业区域","mData" : "region"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort": "SEND_COUNT","sTitle" : "派发数量","mData" : "sendCount"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort": "MASS_ADDRESS","sTitle" : "集结地址","mData" : "massAddress"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort": "MASS_TIME","sTitle" : "集结时间","mData" : "massTime"},
                {"sWidth" : "6%","bSortable" : true,"iDataSort": "LEADER","sTitle" : "领队","mData" : "leader"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort": "LEADER_PHONE_NUM","sTitle" : "领队手机","mData" : "leaderPhoneNum"},
                {"sWidth" : "6%","bSortable" : true,"iDataSort": "DRIVER","sTitle" : "司机","mData":"driver"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort": "DRIVER_PHONE_NUM","sTitle" : "司机手机","mData":"driverPhoneNum"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort": "TASK_DESC","sTitle" : "任务描述","mData":"taskDesc"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort": "STATUS","sTitle" : "任务状态","mData":"status"}
            ],
            "bFilter": false,
            "iDisplayLength": 20,
            "aLengthMenu": [10, 20, 50],
            "fnServerParams" : function(aoData) {
                aoData.push({"name": "sendCount",  "value": getValue('#deliveryTask_search_count')});
                aoData.push({"name": "status",  "value": getValue('#deliveryTask_search_status')});
                aoData.push({"name": "massAddress",  "value": getValue('#deliveryTask_search_massAddress')});
                aoData.push({"name": "leader",  "value": getValue('#deliveryTask_search_leader')});
                aoData.push({"name": "driver",  "value": getValue('#deliveryTask_search_driver')});
                aoData.push({"name": "isSampling",  "value": getValue('#deliveryTask_search_isSampling')});
                aoData.push({"name": "region",  "value": getValue('#deliveryTask_search_region')});
            },
            "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
                var id = aData["id"];
                $(nRow).dblclick(function() {
                    updateDeliveryTask(id);
                });

                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/>" +
                "<span class='lbl'></span></label>");

                var status = aData["status"];
                $('td:eq(11)', nRow).html(getTaskStatus(status));

            }
        });
}

/**
 * 查询
 */
function deliveryTaskSearch(){
    pageOnLoad();
}
/**
 * 清空
 */
function deliveryTaskClear(){
   $("#task-search-form").find("input,select").each(function(index,element){
       $(this).val("");
   });
}
/**
 * 新增任务
 */
$('#addMtBtn').click(function(){
    cleanDialogForm();
    showDeliveryTaskDialog();
});

/**
 * 删除任务
 */
//$('#modifyMtBtn').click(function(){
//    var taskIdArray = new Array();
//    $("#deliveryTask-table").find("input[type='checkbox']").each(function(index,element){
//        if(element.checked){
//            taskIdArray.push(element.value);
//        }
//    });
//    if(taskIdArray.length ==0){
//        alert("请选择要删除的任务!");
//        return;
//    }
//    if(taskIdArray.length>1){
//        alert("请选择一个要删除的任务!");
//        return;
//    }
//    var taskId =taskIdArray[0];
//
//    if(confirm("确认删除任务？")){
//        $.ajax({
//            url: getValue('#contextPath') + "/deliverytask/" + taskId,
//            method: "delete",
//            success: function (data, textStatus, jqXHR) {
//                if (data != "SUCCESS") {
//                    alert("服务器异常，删除数据失败,请重新尝试！");
//                }
//                window.location.reload();
//            },
//            error: function (jqXHR, textStatus, errorThrown) {
//                alert("服务器异常，请联系管理员！");
//            }
//        });
//    }
//});
/**
 * 修改任务
 */
$('#modifyMtBtn').click(function(){
    var taskIdArray = new Array();
    $("#deliveryTask-table").find("input[type='checkbox']").each(function(index,element){
        if(element.checked){
            taskIdArray.push(element.value);
        }
    });
    if(taskIdArray.length ==0){
        alert("请选择要编辑的任务!");
        return;
    }
    if(taskIdArray.length>1){
        alert("请选择一个编辑的任务!");
        return;
    }
    var taskId =taskIdArray[0];

    updateDeliveryTask(taskId);
});


function updateDeliveryTask(taskId){
    cleanDialogForm();
    $.ajax({
        url: getValue('#contextPath') + "/deliverytask/get?taskId="+taskId,
        type: "get",
        async: false,
        dataType:'json',
        success: function (data, textStatus, jqXHR) {
            if(data){
                $("#delivery_task_dialog").find("input,select").each(function(index,element){
                    var name =this.name;
                    if(name && data[name]){
                        $(this).val(data[name]);
                    }
                });
                $("#delivery_task_dialog_taskDesc").val(data.taskDesc);
                $("#delivery_task_status_row").show();
                $("#delivery_task_check_status").val(data.status);
            }
        },
        error:function(data, textStatus, jqXHR){
            alert("服务器出现异常，请联系管理员！")
        }
    });

    showDeliveryTaskDialog();
}

/**
 * 清空任务输入值
 */
function cleanDialogForm(){
    $("#delivery_task_form").find("input,select").each(function(index,element){
        $(this).val("");
    });
    $("#delivery_task_dialog_taskDesc").val("");
    $("#delivery_task_status_row").hide()
}

function showDeliveryTaskDialog(){
    var dialog = $("#delivery_task_dialog").removeClass('hide').dialog({
        modal: true,
        width: 690,
        height:650,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>任务管理</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#delivery_task_form');
        },
        buttons: [
            {
                text: "保存",
                "class": "btn btn-primary btn-xs save-order-dialog",
                click: function () {
                    if (!validateUpdate()) {
                        return;
                    }

                    var currentDialog = $(this),
                        addStoreFormOptions = {
                            beforeSubmit: function(){
                                $("button[class*='save-order-dialog']").attr("disabled", true);
                            },
                            success: function(data){
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                if(data != 'SUCCESS'){
                                    alert(data);
                                    return;
                                }
                                currentDialog.dialog("close");
                                deliveryTaskSearch();
                            },
                            error:function(ret){
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                alert("保存任务出错，请重新登录")
                            }
                        };
                    $('#delivery_task_form').ajaxSubmit(addStoreFormOptions);

                }
            },
            {
                text: "取消",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });

}

function validateUpdate(){
    if($("#delivery_task_check_status").val() == 'TASKEND'){
        alert("已完成的任务不允许修改!")
        return false;
    }
    var flag = true;

    if (!myvalidate('#delivery_task_dialog_region')){
        flag = false;
    }
    if (!myvalidate('#delivery_task_dialog_sendCount')){
        flag = false;
    }
    if (!myvalidate('#delivery_task_dialog_massTime')){
        flag = false;
    }
    if (!myvalidate('#delivery_task_dialog_massAddress')){
        flag = false;
    }
    if (!myvalidate('#delivery_task_dialog_leader')){
        flag = false;
    }
    if (!myvalidate('#delivery_task_dialog_leaderPhoneNum')){
        flag = false;
    }
    if (!myvalidate('#delivery_task_dialog_driver')){
        flag = false;
    }
    if (!myvalidate('#delivery_task_dialog_driverPhoneNum')){
        flag = false;
    }
    if (!myvalidate('#delivery_task_dialog_startTime')){
        flag = false;
    }
    return flag;
}

function getTaskStatus(status){
        switch(status)
        {
            case "TASKTOBEGIN":
                return "任务即将开始";
                break;
            case "TASKBEGIN":
                return "任务开始";
                break;
            case "TASKEND":
                return "任务结束";
                break;
            default://当所有情况都不匹配时，将执行default语句后的
                return "未知";
        }
    }

function getTaskSampling(status){
    switch (status)
    {
        case "SAMPLING":
            return "已抽检";
            break;
        case "NOSAMPLING":
            return "未抽检";
            break;
        default://当所有情况都不匹配时，将执行default语句后的
            return "未知";
    }
}