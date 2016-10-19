function pageOnLoad(){
    var oTable = $('#baseCommunityBuilding-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide" : true,
            "sServerMethod" : "POST",
            "sAjaxSource" : getValue('#contextPath')+"/communityBuilding/search",
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
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "楼栋编号","mData" : "id"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "楼栋名称","mData" : "name"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "所属小区","mData" : "communityName"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "楼栋标注","mData" : "remark"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "创建人","mData" : "createUser"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "创建时间","mData" : "createDt",
                    "fnRender":function(obj){
                        return new Date(obj.aData.createDt).Format("yyyy-MM-dd hh:mm:ss");
                }}
            ],
            "bFilter": false,
            "iDisplayLength": 20,
            "aLengthMenu": [10, 20, 50],
            "fnServerParams" : function(aoData) {
                aoData.push({"name": "name",  "value": getValue('#baseCommunityBuilding_search_BuildingName')});
                aoData.push({"name": "communityName",  "value": getValue('#baseCommunityBuilding_search_CommunityName')});
            },
            "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
                var id = aData["id"];
                $(nRow).dblclick(function() {
                    updateBaseCommunityBuilding(id);
                });

                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/>" +
                "<span class='lbl'></span></label>");

                //$('td:eq(6)', nRow).html("<label><input type='button' class='ace' onclick='openDeliveryCommunityUnit("+id+")' value=查看单元 />" +
                //"<span class='lbl'></span></label>");
            }
        });
}

function baseCommunityBuildingSearch(){
    pageOnLoad();
}

function baseCommunityBuildingClear(){
    $("#baseCommunityBuilding_search_CommunityName").val("");
    $("#baseCommunityBuilding_search_BuildingName").val("");
}

function cityAddInfo(city,area,name){
    //$("#deliveryCommunity_dialog_city").val(city);
    //$("#deliveryCommunity_dialog_area").val(area);
    //$("#deliveryCommunity_dialog_section").val(name);
}

$('#addBuildingMtBtn').click(function(){
    $.communityDialog.open(showCommunityBuildingDialog);
});

$('#modifyMtBtn').click(function(){
    var communityBuildingIdArray = new Array();
    $("#baseCommunityBuilding-table").find("input[type='checkbox']").each(function(index,element){
        if(element.checked){
            communityBuildingIdArray.push(element.value);
        }
    });
    if(communityBuildingIdArray.length ==0){
        alert("请选择要编辑的楼栋!");
        return;
    }
    if(communityBuildingIdArray.length>1){
        alert("请选择一个编辑的楼栋!");
        return;
    }
    var communityBuildingId =communityBuildingIdArray[0];

    updateBaseCommunityBuilding(communityBuildingId);
});

$('#deleteMtBtn').click(function(){
    var communityBuildingIdArray = new Array();
    $("#baseCommunityBuilding-table").find("input[type='checkbox']").each(function(index,element){
        if(element.checked){
            communityBuildingIdArray.push(element.value);
        }
    });
    if(communityBuildingIdArray.length ==0){
        alert("请选择要删除的楼栋!");
        return;
    }


    if(confirm("删除选中楼栋？")){
        var communityBuildingIdStr ='';
        $.ajax({
            url: getValue('#contextPath') + "/communityBuilding/delete?idListStr="+communityBuildingIdArray,
            type: "get",
            async: false,
            dataType:'json',
            success: function (data, textStatus, jqXHR) {
                if(data !='SUCCESS'){
                    alert("删除楼栋失败!")
                }
                pageOnLoad();
            },
            error:function(data, textStatus, jqXHR){
                alert("服务器出现异常，请联系管理员！")
            }
        });
    }
});

function updateBaseCommunityBuilding(_communityBuildingId){
    cleanForm();

    $.ajax({
        url: getValue('#contextPath') + "/communityBuilding/get?communityBuildingId="+_communityBuildingId,
        type: "get",
        async: false,
        dataType:'json',
        success: function (data, textStatus, jqXHR) {
            if(data){
                $("#deliveryCommunityBuilding_dialog_id").val(data.id);
                $("#deliveryCommunityBuilding_dialog_name").val(data.name);
                $("#deliveryCommunityBuilding_dialog_latitude").val(data.latitude);
                $("#deliveryCommunityBuilding_dialog_longitude").val(data.longitude);
                $("#deliveryCommunityBuilding_dialog_remark").val(data.remark);
                $("#deliveryCommunityBuilding_dialog_commuityId").val(data.communityId);
                $("#deliveryCommunityBuilding_dialog_communityName").val(data.communityName);

            }
        },
        error:function(data, textStatus, jqXHR){
            alert("服务器出现异常，请联系管理员！")
        }
    });

    var dialog = $("#deliveryCommunityBuilding_dialog").removeClass('hide').dialog({
        modal: true,
        width: 570,
        height:650,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>修改小区楼栋</h4></div>",
        title_html: true,
        close: function (event, ui) {
            cleanForm();
        },
        buttons: [
            {
                text: "保存",
                "class": "btn btn-primary btn-xs save-order-dialog",
                click: function () {

                    var currentDialog = $(this),
                        communityBuildingFormOptions = {
                            beforeSubmit: function(){
                                $("button[class*='save-order-dialog']").attr("disabled", true);
                            },
                            success: function(data){
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                if(data != 'SUCCESS'){
                                    alert(data);
                                    return;
                                }
                                dialog.dialog("close");
                                pageOnLoad();
                            },
                            error:function(ret){
                                $("button[class*='save-order-dialog']").attr("disabled", false);
                                alert("服务器出现异常，请联系管理员！")
                            }
                        };
                    $('#deliveryCommunityBuilding_dialog_form').ajaxSubmit(communityBuildingFormOptions);

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


function cleanForm(){
    $("#deliveryCommunityBuilding_dialog_form").find("input").each(function(index,element){
        $(this).val("");
    });
}

function showCommunityBuildingDialog(_communityId,_city,_communityName,_longitude,_latitude){
    setValue("#communityBuilding_dialog_city", _city);
    setValue("#communityBuilding_dialog_community", _communityName);

    setValue("#community-lnglat-select-longitude", _longitude);
    setValue("#community-lnglat-select-latitude", _latitude);

    var dialog = $("#add_communityBuilding_dialog").removeClass('hide').dialog({
        modal: true,
        width: 970,
        height: 610,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>编辑小区楼栋</h4></div>",
        title_html: true,
        open: function (event, ui) {
            setTimeout(function () {
                //延迟渲染，等待html页面加载完成再调用，否则会找不到.方法
                var w = window.frames["map-community-building-lnglat-container"].window;
                w.searchPoint(_longitude, _latitude, _city, _communityName);

                addBuildingPointList(w,_communityId);
            }, 1500);
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
                    var w = window.frames["map-community-building-lnglat-container"].window;
                    if (w.getPointMap() && w.getPointMap().keys &&  w.getPointMap().keys.length > 0) {
                        var points = w.getPointMap().keys;
                        $.ajax({
                            url: getValue('#contextPath') + "/communityBuilding/saveBuilding",
                            type: "post",
                            async: false,
                            traditional: true,
                            dataType: 'json',
                            data: {
                                'communityId': _communityId,
                                'communityName': _communityName,
                                'pointList': points
                            },
                            success: function (data, textStatus, jqXHR) {
                                if (data == 'SUCCESS') {
                                    $("#add_communityBuilding_dialog").dialog("close");
                                } else {
                                    alert("服务器出现异常，请联系管理员！");
                                }
                            },
                            error: function (data, textStatus, jqXHR) {
                                alert("服务器出现异常，请联系管理员！")
                            }
                        });

                    }else{
                        $("#add_communityBuilding_dialog").dialog("close");
                    }
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

function addBuildingPointList(_frame,_communityId){
    $.ajax({
        url: getValue('#contextPath') + "/communityBuilding/buildingList",
        type: "get",
        async: false,
        dataType:'json',
        data:{
            'communityId':_communityId
        },
        success: function (data, textStatus, jqXHR) {
            if(data){
               for(var index in data){
                   var buildingDto = data[index];
                   var _longitude =buildingDto.longitude;
                   var _latitude =buildingDto.latitude;
                   var _name =buildingDto.name;

                   _frame.addPoint(_longitude, _latitude, _name);
               }
            }
        },
        error:function(data, textStatus, jqXHR){
            alert("服务器出现异常，请联系管理员！")
        }
    });
}

function searchCommunityLngLat() {
    var city = getValue('#community-lnglat-select-city');
    var address = getValue('#communityBuilding_dialog_community');
    var longitude = "";
    var latitude = "";
    var w = window.frames["map-community-building-lnglat-container"].window;
    w.searchPoint(longitude, latitude, city, address);
}


