jQuery.sectionDialog = {
    selectType:'RADIO',
    selectSection:new Object(),
    open: function (callListener,_selectType) {
        if(_selectType){
            this.selectType = _selectType;
        }
        var dialog = $("#ext-section-dialog").removeClass('hide').dialog({
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
                        if ($.sectionDialog.selectType == 'CHECKBOX') {
                            var idStr = '', valueStr = '', sectionObj = $.sectionDialog.selectSection;
                            if (sectionObj) {
                                for (var index in sectionObj) {
                                    if (sectionObj[index]) {
                                        idStr += index + ",";
                                        valueStr += sectionObj[index] + ",";
                                    }
                                }
                                if(idStr.length > 0){
                                    idStr =idStr.substr(0,idStr.length-1);
                                }
                                if(valueStr.length > 0){
                                    valueStr =valueStr.substr(0,valueStr.length-1);
                                }
                            }
                            callListener(idStr, valueStr);
                            $(this).dialog("close");
                        } else {
                            var selectRadio = $("#sectionList-table input[type='radio']:checked");
                            callListener(selectRadio.attr("IdVal"), selectRadio.attr("nameVal"));
                            $(this).dialog("close");
                        }
                    }
                }
            ]
        });
    },
    loadData: function () {
        var oTable = $('#sectionList-table').dataTable(
            {
                "bDestroy": true,
                "bServerSide": true,
                "sServerMethod": "GET",
                "sAjaxSource": getValue('#contextPath') + "/deliverySection/search",
                "bProcessing": true,
                "bSort": true,
                "aaSorting": [
                    [1, 'asc']
                ],
                "oLanguage": {
                    "sProcessing": "正在获取数据，请稍候..."
                },
                "aoColumns": [
                    {"sWidth": "4%", "bVisible": true, "bSortable": false, "iDataSort": "ID", "sTitle": "选择", "mData": "id"},
                    {"sWidth": "48%", "bSortable": true, "iDataSort": "CITY", "sTitle": "所属城市", "mData": "city"},
                    {"sWidth": "48%", "bSortable": true, "iDataSort": "SECTION_NAME", "sTitle": "板块名称", "mData": "sectionName"}
                ],
                "bFilter": false,
                "iDisplayLength": 10,
                "aLengthMenu": [10, 15, 20],
                "fnServerParams": function (aoData) {
                    aoData.push({"name": "sectionName", "value": $('#section-search-name').val()});
                    aoData.push({"name": "cityName", "value": $('#section-search-city').val()});
                },
                "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                    var id = aData["id"];
                    var sectionName = aData["sectionName"];

                    if($.sectionDialog.selectType == 'CHECKBOX'){
                        $('td:eq(0)', nRow).html("<label><input type='checkbox' id='section_checkBoxId_"+id+"' " +
                        " onclick='$.sectionDialog.changeSelectSection("+id+")' name='checkbox' class='ace'  " +
                        " nameVal=\"" + sectionName + "\" IdVal=\"" + id + "\"  /><span class='lbl'></span></label>");
                    }else{
                        $('td:eq(0)', nRow).html("<label><input type='radio' name='codeRadio' class='ace' " +
                        " nameVal=\"" + sectionName + "\"  IdVal=\"" + id + "\"  /><span class='lbl'></span></label>");
                    }

                },
                "fnDrawCallback":function(){
                    var sectionObj = $.sectionDialog.selectSection;
                    if (sectionObj) {
                        for (var index in sectionObj) {
                            if (sectionObj[index]) {
                                $("#section_checkBoxId_"+index).prop("checked",true)
                            }
                        }
                    }
                }

            });


        $("#section-search-query-button").click(function () {
            oTable.fnDraw();
        });


        $("#section-search-clear-button").click(function () {
            $("#section-search-form")[0].reset();
        });
    },
    changeSelectSection : function(id){
        var checkboxId="#section_checkBoxId_"+id;
        var sectionId= $(checkboxId).attr("IdVal");
        var sectionName =$(checkboxId).attr("nameVal");

        if($(checkboxId).prop("checked")){
            this.selectSection[sectionId] =sectionName;
        }else{
            this.selectSection[sectionId] =null;
        }
    }

};

$(function () {
    $.sectionDialog.loadData();
});







