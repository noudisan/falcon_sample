function pageOnLoad(){
    var oTable = $('#baseCommunityUnit-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide" : true,
            "sServerMethod" : "POST",
            "sAjaxSource" : getValue('#contextPath')+"/communityunit/search",
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
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "单元编号","mData" : "id"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "单元名称","mData" : "name"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "层数","mData" : "floorNum"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "每层户数","mData" : "households"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "总户数","mData" : "allNum"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "楼栋名称","mData" : "buildingName"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "小区名称","mData" : "communityName"},
                {"sWidth" : "10%","bSortable" : true,"sTitle" : "创建时间","mData" : "createDt",
                    "fnRender":function(obj){
                        return new Date(obj.aData.createDt).Format("yyyy-MM-dd hh:mm:ss");
                }}
            ],
            "bFilter": false,
            "iDisplayLength": 20,
            "aLengthMenu": [10, 20, 50],
            "fnServerParams" : function(aoData) {
                aoData.push({"name": "name",  "value": getValue('#baseCommunityUnit_search_UnitName')});
                aoData.push({"name": "buildingName",  "value": getValue('#baseCommunityUnit_search_buildingName')});
                aoData.push({"name": "communityName",  "value": getValue('#baseCommunityUnit_search_communityName')});
            },
            "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
                var id = aData["id"];
                $(nRow).dblclick(function() {
                    updateBaseCommunityUnit(id);
                });

                $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/>" +
                "<span class='lbl'></span></label>");
            }
        });
}

function baseCommunityUnitSearch(){
    pageOnLoad();
}

function baseCommunityUnitClear(){
    $("#baseCommunityUnit_search_buildingName").val("");
    $("#baseCommunityUnit_search_UnitName").val("");
    $("#baseCommunityUnit_search_communityName").val("");
}

function editBaseCommunityUnitDialog(){
    var communityUnitIdArray = new Array();
    $("#baseCommunityUnit-table").find("input[type='checkbox']").each(function(index,element){
        if(element.checked){
            communityUnitIdArray.push(element.value);
        }
    });
    if(communityUnitIdArray.length ==0){
        alert("请选择要编辑的单元!");
        return;
    }
    if(communityUnitIdArray.length>1){
        alert("请选择一个编辑的单元!");
        return;
    }
    var communityUnitId =communityUnitIdArray[0];

    updateBaseCommunityUnit(communityUnitId);
}


function deleteBaseCommunityUnitDialog(){
    var communityUnitIdArray = new Array();
    $("#baseCommunityUnit-table").find("input[type='checkbox']").each(function(index,element){
        if(element.checked){
            communityUnitIdArray.push(element.value);
        }
    });
    if(communityUnitIdArray.length ==0){
        alert("请选择要删除的单元!");
        return;
    }

    if(confirm("删除选中单元？")){
        var communityUnitIdStr ='';
        $.ajax({
            url: getValue('#contextPath') + "/communityunit/delete?idListStr="+communityUnitIdArray,
            type: "get",
            async: false,
            dataType:'json',
            success: function (data, textStatus, jqXHR) {
                if(data !='SUCCESS'){
                    alert(data)
                }
                pageOnLoad();
            },
            error:function(data, textStatus, jqXHR){
                alert("服务器出现异常，请联系管理员！")
            }
        });
    }

}

function updateBaseCommunityUnit(_communityUnitId){
    cleanForm();

    $.ajax({
        url: getValue('#contextPath') + "/communityunit/get?communityUnitId="+_communityUnitId,
        type: "get",
        async: false,
        dataType:'json',
        success: function (data, textStatus, jqXHR) {
            if(data){
                $("#deliveryCommunityUnit_dialog").find("input,select").each(function(index,element){
                    var name =this.name;
                    if(name && data[name]){
                        $(this).val(data[name]);
                    }
                });

            }
        },
        error:function(data, textStatus, jqXHR){
            alert("服务器出现异常，请联系管理员！")
        }
    });

    var dialog = $("#deliveryCommunityUnit_dialog").removeClass('hide').dialog({
        modal: true,
        width: 570,
        height:650,
        title: "<div class=\"widget-header widget-header-small\"><h4 class=\"smaller\"><i class=\"icon-ok\"></i>修改小区单元</h4></div>",
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
                        communityUnitFormOptions = {
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
                    $('#deliveryCommunityUnit_dialog_form').ajaxSubmit(communityUnitFormOptions);

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
    $("#deliveryCommunityUnit_dialog_form").find("input").each(function(index,element){
        $(this).val("");
    });
}

