function pageOnLoad(){
    var oTable = $('#deliveryData-building-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide" : true,
            "sServerMethod" : "POST",
            "sAjaxSource" : getValue('#contextPath')+"/deliverydatabuilding/search",
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
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"DELIVERY_CITY","sTitle" : "城市","mData" : "deliveryCity"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"COMMUNITY_NAME","sTitle" : "小区名称","mData" : "communityName"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"BUILDING_NAME","sTitle" : "楼栋名称","mData" : "name"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"DELIVERY_NUM","sTitle" : "派发数量","mData" : "deliveryNum"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"DELIVERY_STATUS","sTitle" : "派发状态","mData" : "deliveryStatus"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"REMARK","sTitle" : "备注","mData" : "remark"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"DELIVERY_DT","sTitle" : "派发开始时间","mData" : "deliveryDt"}
                 ],
            "bFilter": false,
            "iDisplayLength": 20,
            "aLengthMenu": [10, 20, 50],
            "fnServerParams" : function(aoData) {
                aoData.push({"name": "deliveryNum",  "value": getValue('#building_search_deliveryNum')});
                aoData.push({"name": "deliveryStatus","value":getValue('#building_search_deliveryStatus')});
                aoData.push({"name": "userName","value":getValue('#building_search_userName')});
                aoData.push({"name": "name","value":getValue('#building_search_name')});
                aoData.push({"name": "communityName","value":getValue('#building_search_communityName')});
                aoData.push({"name": "startDateStr","value":getValue('#search_startDate')});
                aoData.push({"name": "endDateStr","value":getValue('#search_endDate')});
                aoData.push({"name": "deliveryCity","value":getValue('#search_deliveryCity')});
            },
            "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
             var id = aData["id"];
                $(nRow).dblclick(function(){
                    updateDeliveryDataBuilding(id);
                });


                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/>" +
                "<span class='lbl'></span></label>");

                var status = aData["deliveryStatus"];


                $('td:eq(4)', nRow).html(getDeliveryStatus(status));


            }
        });
}

function deliveryDataBuildingSearch(){
    pageOnLoad();
}

function deliveryDataBuildingClear(){
    $("#building_search_deliveryNum").val("");
    $("#building_search_deliveryStatus").val("");
    $("#building_search_userName").val("");
    $("#building_search_name").val("");
    $("#building_search_communityName").val("");
    $("#search_startDate").val("");
    $("#search_endDate").val("");
    $("#search_deliveryCity").val("");
}

$('#modifyMtBtn').click(function(){
    cleanForm();
    var deliveryDataIdArray = new Array();
    $("#deliveryData-building-table").find("input[type='checkbox']").each(function(index,element){
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

    updateDeliveryDataBuilding(deliveryDataId);
});




function cleanForm(){
    $("#deliveryData_building_form").find("input").each(function(index,element){
        $(this).val("");
    });
    //$("#delivery_status").hide();
    //$("#deliveryData_building_dialog_status").val("TO_DELIVERY");

}

function updateDeliveryDataBuilding(id){
    $.ajax({
        url: getValue('#contextPath') + "/deliverydatabuilding/get?id="+id,
        type: "get",
        async: false,
        dataType:'json',
        success: function (data, textStatus, jqXHR) {
            if(data){
                $("#deliveryData_building_id").val(data.id);
                $("#deliveryData_building_buildId").val(data.buildId);
                $("#deliveryData_building_communityId").val(data.communityId);
                $("#deliveryData_building_deliveryEmployeeId").val(data.deliveryEmployeeId);
                $("#deliveryData_building_deliveryTaskId").val(data.deliveryTaskId);
                $("#deliveryData_building_beginDt").val(data.beginDt);
                $("#deliveryData_building_endDt").val(data.endDt);
                $("#deliveryData_building_dialog_userName").val(data.userName);
                $("#deliveryData_building_dialog_num").val(data.deliveryNum);
                $("#delivery_status").show();
                $("#deliveryData_building_dialog_status").val(data.deliveryStatus);
                $("#deliveryData_building_dt").val(data.deliveryDt);
                $("#deliveryData_building_remark").val(data.remark);
                $("#deliveryData_building_dialog_name").val(data.name);
                $("#deliveryData_building_dialog_communityName").val(data.communityName);
            }
        },
        error:function(data, textStatus, jqXHR){
            alert("服务器出现异常，请联系管理员！")
        }
    });

    showCommunityUnitDataDialog();
}

function showCommunityUnitDataDialog(){
    var dialog = $("#deliveryData_building_dialog").removeClass('hide').dialog({
        modal: true,
        width: 570,
        height:550,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>楼栋派送数据</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#deliveryData_building_form');
        },
        buttons: [
            //{
            //    text: "保存",
            //    "class": "btn btn-primary btn-xs save-order-dialog",
            //    click: function () {
            //        if (!validateUpdateData()) {
            //            return;
            //        }
            //
            //        var currentDialog = $(this),
            //            addStoreFormOptions = {
            //                beforeSubmit: function(){
            //                    $("button[class*='save-order-dialog']").attr("disabled", true);
            //                },
            //                success: function(data){
            //                    $("button[class*='save-order-dialog']").attr("disabled", false);
            //                    if(data != 'SUCCESS'){
            //                        alert(data);
            //                        return;
            //                    }
            //                    currentDialog.dialog("close");
            //                   deliveryDataBuildingSearch();
            //                },
            //                error:function(ret){
            //                    $("button[class*='save-order-dialog']").attr("disabled", false);
            //                    alert(ret)
            //                }
            //            };
            //        $('#deliveryData_building_form').ajaxSubmit(addStoreFormOptions);
            //
            //    }
            //},
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

function getDeliveryStatus(status){
    switch (status){
        case "TO_DELIVERY":
            return "待派送";
            break;
        case "IN_DELIVERY":
            return "正在派送";
            break;
        case "COMPLETE_DELIVERY":
            return "已完成";
            break;
        case "CANT_DELIVERY":
            return "无法派送";
            break;
        default:
            return "未知";
    }
}
