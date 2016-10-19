function pageOnLoad() {
    var oTable = $('#delivery-steps-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide": true,
            "sServerMethod": "POST",
            "sAjaxSource": getValue('#contextPath') + "/deliverysteps/search",
            "bProcessing": true,
            "aaSorting": [[0, 'asc']],
            "oLanguage": {
                "sProcessing": "正在获取数据，请稍候..."
            },
            "aoColumns": [

                {"sWidth": "10%", "bSortable": true, "iDataSort": "USER_ID", "sTitle": "员工号", "mData": "userId"},
                {"sWidth": "12%", "bSortable": true, "iDataSort": "TASK_ID", "sTitle": "任务编号", "mData": "taskId"},
                {"sWidth": "9%", "bSortable": true, "iDataSort": "STEPS", "sTitle": "步数", "mData": "steps"},
                {
                    "sWidth": "8%",
                    "bSortable": true,
                    "iDataSort": "START_TIME",
                    "sTitle": "开始时间",
                    "mData": "startTime",
                    "fnRender": function (obj) {
                        return new Date(obj.aData.startTime).Format("yyyy-MM-dd hh:mm:ss")
                    }
                },
                {
                    "sWidth": "8%", "bSortable": true, "iDataSort": "END_TIME", "sTitle": "结束时间", "mData": "endTime",
                    "fnRender": function (obj) {
                        if(obj.aData.endTime){
                            return new Date(obj.aData.endTime).Format("yyyy-MM-dd hh:mm:ss")
                        }else{
                            return "";
                        }
                    }
                },
                {
                    "sWidth": "8%", "bSortable": true, "sTitle": "用时", "mData": "id",
                    "fnRender": function (obj) {
                        if(obj.aData.endTime){
                            return getTime(obj.aData.startTime, obj.aData.endTime);
                        }else{
                            return "";
                        }

                    }
                }
            ],
            "bFilter": false,
            "iDisplayLength": 20,
            "aLengthMenu": [10, 20, 50],
            "fnServerParams": function (aoData) {
                aoData.push({"name": "userId", "value": getValue('#delivery-steps-search-userId')});
                aoData.push({"name": "taskId", "value": getValue('#delivery-steps-search-taskId')});
                aoData.push({"name": "startTime", "value": getValue('#delivery-steps-search-startTime')});
                aoData.push({"name": "endTime", "value": getValue('#delivery-steps-search-endTime')});
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });
}

function headBoxOnclick(dom) {
    var checked = $(dom).is(':checked');
    $('label input:checkbox').each(function () {
        $(this).prop("checked", checked);
    });
}


function deliveryStepsSearch() {
    pageOnLoad();
}

function clearDeliverySteps(dom) {
    $(dom).parents("form").find("input").each(function (index, element) {
        $(this).val("");
    });
    $(dom).parents("form").find("select").each(function (index, element) {
        $(this).val("");
    });
    deliveryStepsSearch();
}

function getTime(startTime, endTime) {
    var startDate = Date.parse(startTime.replace("-", "/").replace("-", "/"));//Fire Fox 支持
    var endDate = Date.parse(endTime.replace("-", "/").replace("-", "/"));//Fire Fox 支持
    var resultMs = endDate - startDate;

    //计算出相差天数
    var days = Math.floor(resultMs / (24 * 3600 * 1000))

    //计算出小时数
    var leave1 = resultMs % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数
    var hours = Math.floor(leave1 / (3600 * 1000))
    //计算相差分钟数
    var leave2 = leave1 % (3600 * 1000)        //计算小时数后剩余的毫秒数
    var minutes = Math.floor(leave2 / (60 * 1000))
    //计算相差秒数
    var leave3 = leave2 % (60 * 1000)      //计算分钟数后剩余的毫秒数
    var seconds = Math.round(leave3 / 1000)
    var result;
    if (hours) {
        result = hours + "小时" + minutes + "分" + seconds + "秒";
    } else {
        result = minutes + "分" + seconds + "秒";
    }

    return result;
}