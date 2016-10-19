$(function(){
    $("#deliveryCommunity_dialog_section,#deliveryCommunity_dialog_area").click(function () {
        $.getScript(getValue('#contextPath') + "/js/common/adminSectionDialog.js", function () {
            $.adminSectionDialog.open(cityAddInfo);
        });
    });
    $("#deliveryCommunity_dialog_delivery_section_name").click(function () {
        $.getScript(getValue('#contextPath') + "/js/common/sectionDialog.js", function () {
            $.sectionDialog.open(sectionAddInfo);
        });
    });
});

function cityAddInfo(city,area,name){
    $("#deliveryCommunity_dialog_city").val(city);
    $("#deliveryCommunity_dialog_area").val(area);
    $("#deliveryCommunity_dialog_section").val(name);
}

function sectionAddInfo(sectionId,sectionName){
    $("#deliveryCommunity_dialog_delivery_section_id").val(sectionId);
    $("#deliveryCommunity_dialog_delivery_section_name").val(sectionName);
}

function pageOnLoad(){
    var oTable = $('#communityList-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide" : true,
            "sServerMethod" : "POST",
            "sAjaxSource" : getValue('#contextPath')+"/deliveryCommunity/search",
            "bProcessing" : true,
            "aaSorting": [[1,'desc']],
            "oLanguage": {
                "sProcessing": "正在获取数据，请稍候..."
            },
            "aoColumns" : [
                { "sWidth": "4%",
                    "bSortable": false,
                    "sTitle": '<input type="checkbox" class="ace" onclick="headBoxOnclick(this)"  /><span class="lbl"></span>',
                    "mData": "id"
                },
                {"sWidth" : "10%","bSortable" : false,"sTitle" : "小区编号","mData" : "communityCode"},
                {"sWidth" : "12%","bSortable" : false,"sTitle" : "小区名称","mData" : "communityName"},
                {"sWidth" : "8%","bSortable" : false,"sTitle" : "所属城市","mData" : "city"},
                {"sWidth" : "8%","bSortable" : false,"sTitle" : "行政区域","mData" : "area"},
                {"sWidth" : "8%","bSortable" : false,"sTitle" : "行政板块","mData" : "section"},
                {"sWidth" : "10%","bSortable" : false,"sTitle" : "户数","mData" : "households"},
                {"sWidth" : "10%","bSortable" : false,"sTitle" : "年限","mData" : "modifyYears"},
                {"sWidth" : "10%","bSortable" : false,"sTitle" : "建筑类型","mData" : "buildType","fnCreatedCell": function (nTd, sData, oData) {
                    var flag= oData.buildType;
                    var flagText = "";
                    switch(flag){
                        case 'COMMUNITY_HOUSE': flagText = "小区住宅";
                            break;
                        case 'NON_COMMUNITY_HOUSE': flagText = "非小区住宅";
                            break;
                        case 'BUSINESS': flagText = "商业";
                            break;
                        case 'SCHOOL': flagText = "学校";
                            break;
                    }
                    $(nTd).text(flagText);
                }},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "用途类型","mData" : "funType"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "是否电梯","mData" : "communityType","fnCreatedCell": function (nTd, sData, oData) {
                    var flag= oData.elevatorFlag;
                    var flagText = "";
                    switch(flag){
                        case 'UNKNOWN': flagText = "未知";
                            break;
                        case 'ELEVATOR': flagText = "有电梯";
                            break;
                        case 'NO_ELEVATOR': flagText = "无电梯";
                            break;
                        default : flagText = "未知";
                            break;
                    }
                    $(nTd).text(flagText);
                }}
            ],
            "bFilter": false,
            "iDisplayLength": 20,
            "aLengthMenu": [10, 20, 50],
            "fnServerParams" : function(aoData) {
                aoData.push({"name": "communityCode",  "value": getValue('#community-search-code')});
                aoData.push({"name": "communityName",  "value": getValue('#community-search-name')});
                aoData.push({"name": "pinyinCode",  "value": getValue('#community-search-pinyin')});
                aoData.push({"name": "city",  "value": getValue('#community-search-city')});
                aoData.push({"name": "area",  "value": getValue('#community-search-area')});
                aoData.push({"name": "section",  "value": getValue('#community-search-section')});
                aoData.push({"name": "address",  "value": getValue('#community-search-address')});
                aoData.push({"name": "buildType",  "value": getValue('#community-search-buildType')});
                aoData.push({"name": "funType",  "value": getValue('#community-search-funType')});
            },
            "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
                var id = aData["id"];
                $(nRow).dblclick(function() {
                    updateDeliveryCommunity(id);
                });

                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/>" +
                "<span class='lbl'></span></label>");
            }
        });
}

function deliveryCommunitySearch(){
    pageOnLoad();
}

function deliveryCommunityClear(){
    $("#community-search-form").find("input").each(function(index,element){
        if(element.type !="checkbox"){
            $(this).val("");
        }

    });
    $("#community-search-form").find("select").each(function(index,element){
        $(this).val("");
    });
    $("#community-search-form").find("checkbox").each(function(index,element){
        $(this).val("checked",false);
    });

}

$('#addMtBtn').click(function(){
    cleanForm()
    showDeliveryCommunityDialog();
});

$('#modifyMtBtn').click(function(){
    var communityIdArray = new Array();
    $("#communityList-table").find("input[type='checkbox']").each(function(index,element){
        if(element.checked){
            communityIdArray.push(element.value);
        }
    });
    if(communityIdArray.length ==0){
        alert("请选择要编辑的小区!");
        return;
    }
    if(communityIdArray.length>1){
        alert("请选择一个编辑的小区!");
        return;
    }
    var communityId =communityIdArray[0];

    updateDeliveryCommunity(communityId);
});

function updateDeliveryCommunity(communityId){
    cleanForm();

    $.ajax({
        url: getValue('#contextPath') + "/deliveryCommunity/get?communityId="+communityId,
        type: "get",
        async: false,
        dataType:'json',
        success: function (data, textStatus, jqXHR) {
            if(data){

                $("#deliveryCommunity_dialog").find("input,select").each(function(index,element){
                    var name =this.name;
                    if(name && data[name]){
                        $(this).val(data[name]);
                    }
                });

                var deliverySectionId= data.deliverySectionId;
                if(deliverySectionId){
                    updateDeliverySectionName(deliverySectionId);
                }

            }
        },
        error:function(data, textStatus, jqXHR){
            alert("服务器出现异常，请联系管理员！")
        }
    });

    showDeliveryCommunityDialog();
}

function cleanForm(){
    $("#delivery_community_form").find("input,select").each(function(index,element){
        if(element.type !="checkbox"){
            $(this).val("");
        }else{
            $(this).prop("checked",false);
        }
    });

}

function updateDeliverySectionName(_sectionId){
    $.ajax({
        url: getValue('#contextPath') + "/deliverySection/get?sectionId="+_sectionId,
        type: "get",
        async: false,
        dataType:'json',
        success: function (data, textStatus, jqXHR) {
            if(data && data.code ==1){
                $("#deliveryCommunity_dialog_delivery_section_name").val(data.data.sectionName);
            }
        },
        error:function(data, textStatus, jqXHR){
        }
    });
}

function showDeliveryCommunityDialog(){
    var dialog = $("#deliveryCommunity_dialog").removeClass('hide').dialog({
        modal: true,
        width: 570,
        height:650,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>小区管理</h4></div>",
        title_html: true,
        close: function (event, ui) {
            removeFormInputBorders('#delivery_community_form');
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
                                deliveryCommunitySearch();
                            },
                            error:function(ret){
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                alert("服务器出现异常，请重新登录！")
                            }
                        };
                    $('#delivery_community_form').ajaxSubmit(addStoreFormOptions);

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
    if (!myvalidate('#deliveryCommunity_dialog_name')){
        flag = false;
    }


    return flag;
}


function selectCommunityLngLat() {
    var city = getValue('#deliveryCommunity_dialog_city');
    var address = getValue('#deliveryCommunity_dialog_address');
    var longitude = getValue('#deliveryCommunity_dialog_longitude');
    var latitude = getValue('#deliveryCommunity_dialog_latitude');

    setValue("#community-lnglat-select-city", city);
    setValue("#community-lnglat-select-address", address);
    setValue("#community-lnglat-select-longitude", longitude);
    setValue("#community-lnglat-select-latitude", latitude);

    var dialog = $("#community-lnglat-select-dialog").removeClass('hide').dialog({
        modal: true,
        width: 970,
        height: 610,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>选择小区经纬度</h4></div>",
        title_html: true,
        open: function (event, ui) {
            setTimeout(function () {
                //延迟渲染，等待html页面加载完成再调用，否则会找不到.方法
                var w = window.frames["map-community-lnglat-container"].window;
                w.searchPoint(longitude, latitude, city, address);
            }, 3500);
        },
        close: function (event, ui) {
            clearValue('#community-lnglat-select-city');
            clearValue('#community-lnglat-select-address');
            clearValue('#community-lnglat-select-longitude');
            clearValue('#community-lnglat-select-latitude');
        },
        buttons: [
            {
                text: "保存",
                "class": "btn btn-primary btn-xs",
                click: function () {
                    var w = window.frames["map-community-lnglat-container"].window;
                    var points = w.getPoints();

                    setValue('#deliveryCommunity_dialog_longitude', points.lng);
                    setValue('#deliveryCommunity_dialog_latitude', points.lat);

                    removeBorder('#deliveryCommunity_dialog_longitude');
                    removeBorder('#deliveryCommunity_dialog_latitude');
                    $(this).dialog("close");
                }
            },
            {
                text: "关闭",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
}

function searchCommunityLngLat() {
    var city = getValue('#community-lnglat-select-city');
    var address = getValue('#community-lnglat-select-address');
    var longitude = "";
    var latitude = "";
    var w = window.frames["map-community-lnglat-container"].window;
    w.searchPoint(longitude, latitude, city, address);
}
