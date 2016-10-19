var platformTable = {};
$(function () {
	platformTable = $('#platformList-table').dataTable(
        {
            "bDestroy": true,
            "bServerSide": true,
            "sServerMethod": "POST",
            "sAjaxSource": contextPath + "/platform/search",
            "bProcessing": true,
            "bSort": true,
            "aaSorting": [
                [1, 'desc']
            ],
            "oLanguage": {
                "sProcessing": "正在获取数据，请稍候..."
            },
            "aoColumns": [
                {"sWidth": "3%", "bSortable": false, "sTitle": '<input type="checkbox" class="ace" onclick="checkAllItem(this)"/><span class="lbl"></span>', "mData": "id"},
                {"sWidth": "5%",  "bSortable": true, "iDataSort": "ID","sTitle": "平台ID", "mData": "id"},
                {"sWidth": "10%",  "bSortable": true, "iDataSort": "NAME","sTitle": "平台名称", "mData": "name"},
                {"sWidth": "15%",  "bSortable": false, "iDataSort": "URL","sTitle": "平台URL", "mData": "url"},
                {"sWidth": "20%",  "bSortable": false, "iDataSort": "SECRET_KEY","sTitle": "平台密钥", "mData": "secretKey"},
                {"sWidth": "5%",  "bSortable": true, "iDataSort": "CREATE_USER","sTitle": "创建人", "mData": "createUser"},
                {"sWidth": "15%",  "bSortable": true, "iDataSort": "CREATE_DT","sTitle": "创建时间", "mData": "createDtString"},
                {"sWidth": "5%",  "bSortable": true, "iDataSort": "MODIFY_USER","sTitle": "修改人", "mData": "modifyUser"},
                {"sWidth": "15%",  "bSortable": true, "iDataSort": "MODIFY_DT","sTitle": "修时间", "mData": "modifyDtString"}
            ],
            "bFilter": false,
            "iDisplayLength": 15,
            "aLengthMenu": [15, 30, 45],
            "fnServerParams": function (aoData) {
               aoData.push({"name": "name", "value": elementVal('platform-search-name')});
               aoData.push({"name": "url", "value": elementVal('platform-search-url')});
               aoData.push({"name": "startDate", "value": elementVal('platform-search-startDate')});
               aoData.push({"name": "endDate", "value": elementVal('platform-search-endDate')});
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                   var id = aData["id"];
                   $('td:eq(0)', nRow).html("<label><input type='checkbox' class='ace' value=\"" + id + "\"" + "/><span class='lbl'></span></label>");
            }
        });

    $("#platform-search-query-button").click(function () {
    	platformTable.fnDraw();
    });

    $("#platform-search-clear-button").click(function () {
        $("#platform-search-form")[0].reset();
    });
    

    if( $("#platform-add-btn")){
    	$("#platform-add-btn").click(function () {
        	var that = $(this);
            var elementsId = new Array('platform-add-name', 'platform-add-url');
            $.validatePlugin.bind(elementsId, true);
            $.validatePlugin.clear(elementsId);
            $("#platform-add-form")[0].reset();
            var dialog = $("#platform-add-dialog").removeClass('hide').dialog({
                modal: true,
                width: 400,
                title_html: true,
                buttons: [
                    {
                        text: "取消",
                        "class": "btn btn-xs",
                        click: function () {
                            $(this).dialog("close");
                        }
                    },
                    {
                        text: "保存",
                        "class": "btn btn-primary btn-xs",
                        click: function () {
                            if (!$.validatePlugin.validate()) {
                                return;
                            }
                            $.ajax({
                                url:that.attr("path"),
                                type: "post",
                                data: {
                                    "name": $("#platform-add-name").val(),
                                    "url": $("#platform-add-url").val()
                                },
                                success: function (data, textStatus, jqXHR) {
                                    if (data.status == 'SUCCESS') {
                                        dialog.dialog("close");
                                        platformTable.fnDraw();
                                    }
                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    alert("服务器异常，请联系管理员！", "错误");
                                }
                            })
                        }
                    }
                ]
            });
        });
    }
    
    
    
    $("#platform-edit-btn").click(function () {
        var items = checkedItems();
        var that = $(this);
        if (items) {
            $.ajax({
                url: contextPath + "/platform/get?id=" + items[0].value,
                success: function (data, textStatus, jqXHR) {
                    var elementsId = new Array('platform-update-id', 'platform-update-name','platform-update-secretKey');
                    var values = {
                        "platform-update-id": data.id,
                        "platform-update-name": data.name,
                        "platform-update-url": data.url,
                        "platform-update-secretKey": data.secretKey
                    };
                    $.validatePlugin.rest(values);
                    $.validatePlugin.bind(elementsId, true);
                    var dialog = $("#platform-update-dialog").removeClass('hide').dialog({
                        modal: true,
                        width: 400,
                        title_html: true,
                        buttons: [
                            {
                                text: "取消",
                                "class": "btn btn-xs",
                                click: function () {
                                    $(this).dialog("close");
                                }
                            },
                            {
                                text: "修改",
                                "class": "btn btn-primary btn-xs",
                                click: function () {
                                    if (!$.validatePlugin.validate()) {
                                        return;
                                    }
                                    $.confirmDialog("确认修改？", function () {
                                        $.ajax({
                                        	url:that.attr("path"),
                                            type: "post",
                                            data: {
                                                "id": $("#platform-update-id").val(),
                                                "name": $("#platform-update-name").val(),
                                                "url": $("#platform-update-url").val(),
                                                "secretKey": $("#platform-update-secretKey").val()
                                            },
                                            success: function (data, textStatus, jqXHR) {
                                                if (data.status == 'SUCCESS') {
                                                    dialog.dialog("close");
                                                    $('#platformList-ftable_wrapper .active').trigger("click");
                                                }
                                            },
                                            error: function (jqXHR, textStatus, errorThrown) {
                                                alert("服务器异常，请联系管理员！", "错误");
                                            }
                                        })

                                    });
                                }
                            }
                        ]
                    });

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("服务器异常，请联系管理员！", "错误");
                }
            })
        }
    })
    

});

function checkAllItem(id) {
    var checked = $(id).is(':checked');
    $('label input:checkbox').each(function () {
        $(this).prop("checked", checked);
    });
}

function checkedItems() {
    var checkItems = $("label input[type='checkbox']:checked");
    var selectedLength = checkItems.length;
    if (selectedLength <= 0) {
        $.warnDialog("请至少选择一个平台");
        return false;
    }
    if (selectedLength > 1) {
        $.warnDialog("只能编辑一个平台");
        return false;
    }
    return checkItems;
}
