function pageOnLoad(){
    var oTable = $('#deliveryData-unit-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide" : true,
            "sServerMethod" : "POST",
            "sAjaxSource" : getValue('#contextPath')+"/deliverydatacommunityunit/search",
            "bProcessing" : true,
            "aaSorting": [[0,'asc']],
            "oLanguage": {
                "sProcessing": "正在获取数据，请稍候..."
            },
            "aoColumns" : [
                { "sWidth": "4%",
                    "bSortable": false,
                    "iDataSort":"ID",
                    "sTitle": '<input type="checkbox" class="ace" onclick="headBoxOnclick(this)"  /><span class="lbl"></span>',
                    "mData": "id"
                },
                {"sWidth" : "9%","bSortable" : true,"iDataSort":"DELIVERY_CITY","sTitle" : "城市","mData" : "deliveryCity"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort":"COMMUNITY_NAME","sTitle" : "小区名称","mData" : "communityName"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort":"BUILDING_NAME","sTitle" : "楼栋名称","mData" : "buildingName"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort":"COMMUNITY_UNIT_NAME","sTitle" : "单元名称","mData" : "communityUnitName"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort":"DELIVERY_EMPLOYEE_NAME","sTitle" : "派发人员","mData" : "userName"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort":"DELIVERY_NUM","sTitle" : "派发数量","mData" : "deliveryNum"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort":"DELIVERY_TYPE","sTitle" : "派发方式","mData" : "deliveryType"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort":"DELIVERY_RESULT","sTitle" : "派发结果","mData" : "deliveryResult"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort":"SETTLESTATUS","sTitle" : "结算状态","mData" : "settleStatus"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort":"TASK_SAMPLING","sTitle" : "抽查状态","mData" : "taskSampling"},
                {"sWidth" : "9%","bSortable" : true,"iDataSort":"DELIVERY_DT","sTitle" : "派发开始时间","mData" : "deliveryDt"}

                 ],
            "bFilter": false,
            "iDisplayLength": 20,
            "aLengthMenu": [10, 20, 50],
            "fnServerParams" : function(aoData) {
                aoData.push({"name": "deliveryNum",  "value": getValue('#communityUnit_search_deliveryNum')});
                aoData.push({"name": "deliveryType","value":getValue('#communityUnit_search_deliveryType')});
                aoData.push({"name": "deliveryResult","value":getValue('#communityUnit_search_deliveryResult')});
                aoData.push({"name": "buildingName","value":getValue('#communityUnit_search_buildingName')});
                aoData.push({"name": "communityUnitName","value":getValue('#communityUnit_search_communityUnitName')});
                aoData.push({"name": "communityName","value":getValue('#communityUnit_search_communityName')});
                aoData.push({"name": "userName","value":getValue('#communityUnit_search_userName')});
                aoData.push({"name": "settleStatus","value":getValue('#communityUnit_search_settleStatus')});
                aoData.push({"name": "taskSampling","value":getValue('#communityUnit_search_taskSampling')});
                aoData.push({"name": "startDateStr","value":getValue('#search_startDate')});
                aoData.push({"name": "endDateStr","value":getValue('#search_endDate')});
                aoData.push({"name": "deliveryCity","value":getValue('#search_deliveryCity')});
            },
            "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
             var id = aData["id"];
                $(nRow).dblclick(function(){
                    updateDeliveryDataCommunityUnit(id);
                });


                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/>" +
                "<span class='lbl'></span></label>");

                var type = aData["deliveryType"];
                var result = aData["deliveryResult"];
                var settleStatus = aData["settleStatus"]
                var taskSampling = aData["taskSampling"]

                $('td:eq(6)', nRow).html(getDeliveryType(type));
                $('td:eq(7)', nRow).html(getDeliveryResult(result));
                $('td:eq(8)', nRow).html(getSettleResult(settleStatus));
                $('td:eq(9)', nRow).html(getTaskSampling(taskSampling));

            }
        });
}

function deliveryDataUnitSearch(){
    pageOnLoad();
}

function deliveryDataUnitClear(){
    $("#communityUnit_search_buildingName").val("");
    $("#communityUnit_search_communityUnitName").val("");
    $("#communityUnit_search_communityName").val("");
    $("#communityUnit_search_deliveryNum").val("");
    $("#communityUnit_search_deliveryType").val("");
    $("#communityUnit_search_deliveryResult").val("");
    $("#communityUnit_search_userName").val("");
    $("#communityUnit_search_settleStatus").val("");
    $("#communityUnit_search_taskSampling").val("");
    $("#search_startDate").val("");
    $("#search_endDate").val("");
    $("#search_deliveryCity").val("");
}

$('#taskSamping').click(function() {
    var deliveryDataIdArray = new Array();
    $("#deliveryData-unit-table").find("input[type='checkbox']").each(function (index, element) {
        if (element.checked) {
            deliveryDataIdArray.push(element.value);
        }
    });
    if (deliveryDataIdArray.length == 0) {
        alert("请选择要抽检的数据!");
        return;
    }

    $.ajax({
        url: getValue('#contextPath') + "/deliverydatacommunityunit/sampling?idListStr=" + deliveryDataIdArray,
        type: "get",
        async: false,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            if (data != 'SUCCESS') {
                alert("操作失败，请更新后重试")
            } else {
                pageOnLoad();
            }
        },
        error: function (data, textStatus, jqXHR) {
            alert("服务器出现异常，请联系管理员！")
        }
    });
});



$('#modifyMtBtn').click(function(){
    cleanForm();
    var deliveryDataIdArray = new Array();
    $("#deliveryData-unit-table").find("input[type='checkbox']").each(function(index,element){
        if(element.checked){
            deliveryDataIdArray.push(element.value);
        }
    });
    if(deliveryDataIdArray.length == 0){
        alert("请选择要编辑的派发数据!");
        return;
    }
    if(deliveryDataIdArray.length >1){
        alert("请选择一个编辑的派发数据!");
        return;
    }
    var deliveryDataId = deliveryDataIdArray[0];

    updateDeliveryDataCommunityUnit(deliveryDataId);

});

function cleanForm(){
    $("#deliveryData_communityUnit_form").find("input").each(function(index,element){
        $(this).val("");
    });
    $("#delivery_type").hide();
    $("#deliveryData_communityUnit_dialog_type").val("LIFT");
    $("#delivery_result").hide();
    $("#deliveryData_communityUnit_result").val("NOTDELIVERY");
}
function updateDeliveryDataCommunityUnit(id){
    $.ajax({
        url: getValue('#contextPath') + "/deliverydatacommunityunit/get?id="+id,
        type: "get",
        async: false,
        dataType:'json',
        success: function (data, textStatus, jqXHR) {
            if(data){
                $("#deliveryData_communityUnit_id").val(data.id);
                $("#deliveryData_communityUnit_communityUnitId").val(data.communityUnitId);
                $("#deliveryData_communityUnit_communityId").val(data.communityId);
                $("#deliveryData_communityUnit_buildId").val(data.buildId);
                $("#deliveryData_communityUnit_deliveryEmployeeId").val(data.deliveryEmployeeId);
                $("#deliveryData_communityUnit_deliveryTaskId").val(data.deliveryTaskId);
                $("#deliveryData_communityUnit_dialog_userName").val(data.userName);
                $("#deliveryData_communityUnit_dialog_communityName").val(data.communityName);
                $("#deliveryData_communityUnit_dialog_buildingName").val(data.buildingName);
                $("#deliveryData_communityUnit_dialog_communityUnitName").val(data.communityUnitName);
                $("#deliveryData_communityUnit_dialog_num").val(data.deliveryNum);
                $("#delivery_type").show();
                $("#deliveryData_communityUnit_dialog_type").val(data.deliveryType);
                $("#delivery_result").show();
                $("#deliveryData_communityUnit_result").val(data.deliveryResult);
                $("#settle_status").show();
                $("#deliveryData_communityUnit_settleStatus").val(data.settleStatus);
                $("#deliveryData_communityUnit_remark").val(data.remark);
                $("#deliveryData_communityUnit_dt").val(data.deliveryDt);
                
                $("#deliveryData_communityUnit_pictures td.deliveryData_communityUnit_picture").empty();//单页，需要线将原图片位置全部清空
                if( data.pictures || data.pictures.length ){
                	
                	
                	$.each(data.pictures,function(i,p){
                		$("#deliveryData_communityUnit_pictures td.deliveryData_communityUnit_picture:eq("+p.sequence+")").html(
                    			$("<img />").attr({
                   				 "width":"100%"
                   				,"height":"100%"
                   				,"src":data.pictures[i].path
                   			})
                   		);
                	});
                	
                	
                }
                
            }
        },
        error:function(data, textStatus, jqXHR){
            alert("服务器出现异常，请联系管理员！")
        }
    });

    showCommunityUnitDataDialog();
}

function showCommunityUnitDataDialog(){
    var dialog = $("#deliveryData_communityUnit_dialog").removeClass('hide').dialog({
        modal: true,
        width: 1024,
        height:768,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>单元派送数据</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#deliveryData_communityUnit_form');
        },
        buttons: [
            {
                text: "保存",
                "class": "btn btn-primary btn-xs save-order-dialog",
                click: function () {
                    if (!validateUpdateData()) {
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
                                    alert("修改派送数据失败！");
                                    return;
                                }
                                currentDialog.dialog("close");
                               deliveryDataUnitSearch();
                            },
                            error:function(ret){
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                alert(ret)
                            }
                        };
                    $('#deliveryData_communityUnit_form').ajaxSubmit(addStoreFormOptions);

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

function validateUpdateData(){
    var flag = true;
    return flag;
}

function getDeliveryType(status){
    switch (status){
        case "LIFT":
            return "电梯入户";
            break;
        case "STAIRS":
            return "步梯入户";
            break;
        case "INLETTER":
            return "内信箱";
            break;
        case "OUTLETTER":
            return "外信箱";
            break;
        default:
            return "未知";
    }
}

function getDeliveryResult(status){
    switch (status){
        case "NOTDELIVERY":
            return "无法派送";
            break;

        case "CANDELIVERY":
            return "可以派送";
            break;
        default :
            return "未知";

    }
}

function getSettleResult(status){
    switch (status){
        case "UNBALANCED":
            return "未结算";
            break;
        case "SUCCESS":
            return "结算成功";
            break;
        case "FAIL":
            return "结算失败";
            break;
        default :
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

//$(function(){
//    $.ajax({
//        url: getValue('#contextPath') + "/deliverydatacommunityunit/get?id="+id,
//        type: "POST",
//        async: false,
//        dataType:'json',
//        success: function (data, textStatus, jqXHR) {
//            var cityOptions = "<option value=''>--请选择--</option>";
//            for(var city in data){
//                cityOptions.append("<option value=").append("'"+city.cityName+"'").append(" >");
//                cityOptions.append(city.cityName);
//                cityOptions.append("</option>");
//            }
//            $("#select_city").html(cityOptions);
//        },
//        error:function(data, textStatus, jqXHR){
//            alert("服务器出现异常，请联系管理员！")
//        }
//    });
//});