jQuery.adminSectionDialog = {
    open: function (callListener) {
        var dialog = $("#ext-admin-section-dialog").removeClass('hide').dialog({
            modal: true,
            width: 900,
            height: 680,
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
                    text: "确认",
                    "class": "btn btn-primary btn-xs",
                    click: function () {
                        var selectRadio = $("#admin-sectionList-table input[type='radio']:checked");
                        callListener(selectRadio.attr("cityVal"),selectRadio.attr("areaVal"),selectRadio.attr("nameVal"));
                        $(this).dialog("close");
                    }
                }
            ]
        });
    },
    loadData: function () {
        var oTable = $('#admin-sectionList-table').dataTable(
            {
                "bDestroy": true,
                "bServerSide": true,
                "sServerMethod": "POST",
                "sAjaxSource": getValue('#contextPath') + "/deliveryCommunity/searchAdminSection",
                "bProcessing": true,
                "bSort": true,
                "aaSorting": [
                    [1, 'asc']
                ],
                "oLanguage": {
                    "sProcessing": "正在获取数据，请稍候..."
                },
                "aoColumns": [
                    {"sWidth": "1%", "bVisible": true, "bSortable": false, "iDataSort": "SECTION_CODE", "sTitle": "选择", "mData": "sectionCode"},
                    {"sWidth": "20%", "bSortable": true, "iDataSort": "CITY", "sTitle": "所属城市", "mData": "city"},
                    {"sWidth": "20%", "bSortable": true, "iDataSort": "AREA", "sTitle": "所属区域", "mData": "area"},
                    {"sWidth": "20%", "bSortable": true, "iDataSort": "SECTION_NAME", "sTitle": "板块名称", "mData": "sectionName"}
                ],
                "bFilter": false,
                "iDisplayLength": 10,
                "aLengthMenu": [10, 15, 20],
                "fnServerParams": function (aoData) {
                    aoData.push({"name": "city", "value": $('#admin-section-search-city').val()});
                    aoData.push({"name": "area", "value": $('#admin-section-search-area').val()});
                    aoData.push({"name": "name", "value": $('#admin-section-search-name').val()});
                },
                "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                    var name = aData["sectionName"];
                    var city = aData["city"];
                    var area = aData["area"];
                    $('td:eq(0)', nRow).html("<label><input type='radio' name='codeRadio' class='ace'  nameVal=\"" + name + "\" " +
                    "cityVal=\"" + city + "\"  areaVal=\"" + area + "\"/><span class='lbl'></span></label>");
                }
            });


        $("#admin-section-search-query-button").click(function () {
            oTable.fnDraw();
        });


        $("#admin-section-search-clear-button").click(function () {
            $("#admin-section-search-form")[0].reset();
        });
    }
};

$(function () {
    $.adminSectionDialog.loadData();
});







