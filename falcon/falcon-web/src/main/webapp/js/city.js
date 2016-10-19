function pageOnLoad(){
    var oTable = $('#baseCity-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide" : true,
            "sServerMethod" : "POST",
            "sAjaxSource" : getValue('#contextPath')+"/basecity/search",
            "bProcessing" : true,
            "aaSorting": [[1,'asc']],
            "oLanguage": {
                "sProcessing": "正在获取数据，请稍候..."
            },
            "aoColumns" : [
                { "sWidth": "4%",
                    "bSortable": false,
                    "sTitle": '<input type="checkbox" class="ace" onclick="headBoxOnclick(this)"  /><span class="lbl"></span>',
                    "mData": "id"
                },
                {"sWidth" : "20%","bSortable" : true,"sTitle" : "省份","mData" : "province"},
                {"sWidth" : "20%","bSortable" : true,"sTitle" : "城市名称","mData" : "cityName"},
                {"sWidth" : "20%","bSortable" : true,"sTitle" : "城市地址","mData" : "cityAbbr"},
                {"sWidth" : "20%","bSortable" : true,"sTitle" : "状态","mData" : "isLocked"}
            ],
            "bFilter": false,
            "iDisplayLength": 20,
            "aLengthMenu": [10, 20, 50],
            "fnServerParams" : function(aoData) {
                aoData.push({"name": "cityName",  "value": getValue('#baseCity_search_Name')});
                aoData.push({"name": "isLocked",  "value": getValue('#baseCity_search_isLocked')});
            },
            "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
                var id = aData["id"];
                $(nRow).dblclick(function() {
                    updateBaseCity(id);
                });


                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/>" +
                "<span class='lbl'></span></label>");

                var status = aData["isLocked"];

                $('td:eq(4)', nRow).html(getBaseCityStatus(status));

            }
        });
}

function baseCitySearch(){
    pageOnLoad();
}

function baseCityClear(){
    $("#baseCity_search_Name").val("");
    $("#baseCity_search_isLocked").val("");
}

$('#addMtBtn').click(function(){
    cleanForm()
    showCityDialog();
});

$('#modifyMtBtn').click(function(){
    cleanForm();
    var cityIdArray = new Array();
    $("#baseCity-table").find("input[type='checkbox']").each(function(index,element){
        if(element.checked){
            cityIdArray.push(element.value);
        }
    });
    if(cityIdArray.length ==0){
        alert("请选择要编辑的城市!");
        return;
    }
    if(cityIdArray.length>1){
        alert("请选择一个编辑的城市!");
        return;
    }
    var cityId =cityIdArray[0];

    updateBaseCity(cityId);
});




function updateBaseCity(cityId){
    $.ajax({
        url: getValue('#contextPath') + "/basecity/get?cityId="+cityId,
        type: "get",
        async: false,
        dataType:'json',
        success: function (data, textStatus, jqXHR) {
            if(data){
                $("#base-city_id").val(data.id);
                $("#base_city_dialog_province").val(data.province);
                $("#base_city_dialog_cityName").val(data.cityName);
                $("#base_city_dialog_cityAbbr").val(data.cityAbbr);
                $("#city_status").show();
                $("#base_city_dialog_isLocked").val(data.isLocked);
            }
        },
        error:function(data, textStatus, jqXHR){
            alert("服务器出现异常，请联系管理员！")
        }
    });

    showCityDialog();
}


function cleanForm(){
    $("#base_city_form").find("input").each(function(index,element){
        $(this).val("");
    });
    $("#city_status").hide();
    $("#base_city_dialog_isLocked").val("UNLOCK");

}

function showCityDialog(){
    var dialog = $("#base_city_dialog").removeClass('hide').dialog({
        modal: true,
        width: 570,
        height:550,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>城市管理</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#base_city_form');
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
                                baseCitySearch();
                            },
                            error:function(ret){
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                alert(ret)
                            }
                        };
                    $('#base_city_form').ajaxSubmit(addStoreFormOptions);

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
    var flag = true;
    if (!myvalidate('#base_city_dialog_province')){
        flag = false;
    }
    if (!myvalidate('#base_city_dialog_cityName')){
        flag = false;
    }
    if (!myvalidate('#base_city_dialog_cityAbbr')){
        flag = false;
    }

    return flag;
}

function getBaseCityStatus(status){
        switch(status)
        {
            case "LOCK":
                return "锁定";
                break;
            case "UNLOCK":
                return "未锁定";
                break;
            default://当所有情况都不匹配时，将执行default语句后的
                return "未知";
        }
    }