jQuery.communityDialog = {
    callbackMethod:null,
    open: function (_callbackMethod) {
        this.callbackMethod = _callbackMethod;

        var dialog = $("#ext-community-dialog").removeClass('hide').dialog({
            modal: true,
            width: 900,
            height: 680,
            title_html: true,
            buttons: [
                /*{
                    text: "取消",
                    "class": "btn btn-xs",
                    click: function () {
                        $(this).dialog("close");
                    }
                },*/
                {
                    text: "关闭",
                    "class": "btn btn-primary btn-xs",
                    click: function () {
                        //var selectRadio = $("#communityList-table input[type='radio']:checked");
                        //callListener(selectRadio.attr("cityVal"),selectRadio.attr("areaVal"),selectRadio.attr("nameVal"));
                        $(this).dialog("close");
                    }
                }
            ]
        });
    },
    loadData: function () {
        var oTable = $('#communityList-table').dataTable(
            {
                "bDestroy": true,
                "bServerSide": true,
                "sServerMethod": "GET",
                "sAjaxSource": getValue('#contextPath') + "/deliveryCommunity/search",
                "bProcessing": true,
                "bSort": true,
                "aaSorting": [
                    [1, 'asc']
                ],
                "oLanguage": {
                    "sProcessing": "正在获取数据，请稍候..."
                },
                "aoColumns": [
                    {"sWidth": "1%", "bVisible": true, "bSortable": false, "iDataSort": "COMMUNITY_CODE", "sTitle": "选择", "mData": "id"},
                    {"sWidth": "20%", "bSortable": true, "iDataSort": "CITY", "sTitle": "所属城市", "mData": "city"},
                    {"sWidth": "20%", "bSortable": true, "iDataSort": "SECTION", "sTitle": "所属板块", "mData": "section"},
                    {"sWidth": "20%", "bSortable": true, "iDataSort": "COMMUNITY_NAME", "sTitle": "小区名称", "mData": "communityName"},
                    {"sWidth": "20%", "bSortable": false, "iDataSort": "COMMUNITY_NAME", "sTitle": "操作", "mData": "communityName"}
                ],
                "bFilter": false,
                "iDisplayLength": 10,
                "aLengthMenu": [10, 15, 20],
                "fnServerParams": function (aoData) {
                    aoData.push({"name": "communityName", "value": $('#community-search-name').val()});
                    aoData.push({"name": "city", "value": $('#community-search-city').val()});
                    aoData.push({"name": "section", "value": $('#community-search-section').val()});
                },
                "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                    var name = aData["communityName"];
                    var city = aData["city"];
                    var section = aData["section"];
                    var communityId = aData["id"];
                    var longitude = aData["longitude"];
                    var latitude = aData["latitude"];

                    $('td:eq(0)', nRow).html("<label><input type='radio' name='codeRadio' class='ace'  nameVal=\"" + name + "\" " +
                    "cityVal=\"" + city + "\"  sectionVal=\"" + section + "\"/><span class='lbl'></span></label>");

                    $('td:eq(4)', nRow).html("<label><input type='button'  class='ace' value=操作 " +
                    "onclick='$.communityDialog.communityBuilding("+communityId+",\""+city+"\",\""+name+"\","+longitude+","+latitude+")' /><span class='lbl'></span></label>");
                }
            });


        $("#community-search-query-button").click(function () {
            oTable.fnDraw();
        });


        $("#community-search-clear-button").click(function () {
            $("#community-search-form")[0].reset();
        });
    },
    communityBuilding:function(_communityId,_city,_communityName,_longitude,_latitude){
        this.callbackMethod(_communityId,_city,_communityName,_longitude,_latitude);
    }
};

$(function () {
    $.communityDialog.loadData();
});







