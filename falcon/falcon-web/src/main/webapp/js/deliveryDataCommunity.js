function pageOnLoad(){
    var oTable = $('#deliveryData-community-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide" : true,
            "sServerMethod" : "POST",
            "sAjaxSource" : getValue('#contextPath')+"/deliverydatacommunity/search",
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
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"DELIVERY_NUM","sTitle" : "派发数量","mData" : "deliveryNum"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "小区名称","mData" : "communityName"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"CITY","sTitle" : "所在城市","mData" : "city"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"DELIVERY_STATUS","sTitle" : "派发状态","mData" : "deliveryStatus"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"REMARK","sTitle" : "备注","mData" : "remark"},
                {"sWidth" : "10%","bSortable" : true,"iDataSort":"DELIVERY_DT","sTitle" : "派发开始时间","mData" : "deliveryDt"}
                ],
            "bFilter": false,
            "iDisplayLength": 20,
            "aLengthMenu": [10, 20, 50],
            "fnServerParams" : function(aoData) {
                aoData.push({"name": "deliveryNum",  "value": getValue('#community_search_deliveryNum')});
                aoData.push({"name": "deliveryStatus","value":getValue('#community_search_deliveryStatus')});
                aoData.push({"name": "communityName","value":getValue('#community_search_communityName')});
                aoData.push({"name": "city","value":getValue('#community_search_city')});
                aoData.push({"name": "startDateStr","value":getValue('#search_startDate')});
                aoData.push({"name": "endDateStr","value":getValue('#search_endDate')});
                aoData.push({"name": "deliveryCity","value":getValue('#search_deliveryCity')});

            },
            "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
             var id = aData["id"];
                $(nRow).dblclick(function(){
                    updateDeliveryDataCommunity(id);
                });

                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/>" +
                "<span class='lbl'></span></label>");

                var status = aData["deliveryStatus"];
                $('td:eq(4)', nRow).html(getDeliveryStatus(status));
            }
        });
}

function deliveryDataCommunitySearch(){
    pageOnLoad();
}

function deliveryDataCommunityClear(){
    $("#community_search_deliveryNum").val("");
    $("#community_search_deliveryStatus").val("");
    $("#community_search_communityName").val("");
    $("#community_search_city").val("");
    $("#search_startDate").val("");
    $("#search_endDate").val("");
    $("#search_deliveryCity").val("");
}


$('#modifyMtBtn').click(function(){
    cleanForm();
    var deliveryDataIdArray = new Array();
    $("#deliveryData-community-table").find("input[type='checkbox']").each(function(index,element){
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

    updateDeliveryDataCommunity(deliveryDataId);
});




function cleanForm(){
    $("#deliveryData_community_form").find("input").each(function(index,element){
        $(this).val("");
    });
    $("#delivery_status").hide();
    $("#deliveryData_community_dialog_status").val("TO_DELIVERY");

}

function updateDeliveryDataCommunity(id){
    $.ajax({
        url: getValue('#contextPath') + "/deliverydatacommunity/get?id="+id,
        type: "get",
        async: false,
        dataType:'json',
        success: function (data, textStatus, jqXHR) {
            if(data){
                $("#deliveryData_community_id").val(data.id);
                $("#deliveryData_community_sectionId").val(data.sectionId);
                $("#deliveryData_community_communityId").val(data.communityId);
                $("#deliveryData_community_taskId").val(data.deliveryTaskId);
                $("#deliveryData_community_beginDt").val(data.beginDt);
                $("#deliveryData_community_dialog_communityName").val(data.communityName);
                $("#deliveryData_community_dialog_city").val(data.city);

                $("#delivery_status").show();
                $("#deliveryData_community_dialog_status").val(data.deliveryStatus);
                $("#deliveryData_community_dialog_num").val(data.deliveryNum);
                $("#deliveryData_community_deliveryDt").val(data.deliveryDt);
                $("#deliveryData_community_remark").val(data.remark);
            }
        },
        error:function(data, textStatus, jqXHR){
            alert("服务器出现异常，请联系管理员！")
        }
    });

    showCommunityDataDialog();
}

function showCommunityDataDialog(){
    var dialog = $("#deliveryData_community_dialog").removeClass('hide').dialog({
        modal: true,
        width: 570,
        height:550,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>小区派送数据</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#deliveryData_community_form');
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
            //                   deliveryDataCommunitySearch();
            //                },
            //                error:function(ret){
            //                    $("button[class*='save-order-dialog']").attr("disabled", false);
            //                    alert(ret)
            //                }
            //            };
            //        $('#deliveryData_community_form').ajaxSubmit(addStoreFormOptions);
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
