jQuery.employeeDialog = {
    selectType:'RADIO',
    selectEmployee:new Object(),
    open: function (callListener,_selectType) {
        if(_selectType){
            this.selectType = _selectType;
        }
        var dialog = $("#ext-employee-dialog").removeClass('hide').dialog({
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
                        if ($.employeeDialog.selectType == 'CHECKBOX') {
                            var idStr = '', valueStr = '', employeeObj = $.employeeDialog.selectEmployee;
                            if (employeeObj) {
                                for (var index in employeeObj) {
                                    if (employeeObj[index]) {
                                        idStr += index + ",";
                                        valueStr += employeeObj[index] + ",";
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
                            var selectRadio = $("#employeeList-table input[type='radio']:checked");
                            callListener(selectRadio.attr("IdVal"), selectRadio.attr("nameVal"));
                            $(this).dialog("close");
                        }
                    }
                }
            ]
        });
           	
    },
    loadData: function () {
        var oTable = $('#employeeList-table').dataTable(
            {
                "bDestroy": true,
                "bServerSide": true,
                "sServerMethod": "GET",
                "sAjaxSource": getValue('#contextPath') + "/employee/search",
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
                    {"sWidth": "48%", "bSortable": true, "iDataSort": "USER_NAME", "sTitle": "姓名", "mData": "userName"}
                ],
                "bFilter": false,
                "iDisplayLength": 10,
                "aLengthMenu": [10, 15, 20],
                "fnServerParams": function (aoData) {
                    aoData.push({"name": "userName", "value": $('#employee-search-name').val()});
                    aoData.push({"name": "city", "value": $('#employee-search-city').val()});
                },
                "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                    var id = aData["id"];
                    var employeeName = aData["userName"];

                    if($.employeeDialog.selectType == 'CHECKBOX'){
                        $('td:eq(0)', nRow).html("<label><input type='checkbox' id='employee_checkBoxId_"+id+"' " +
                        " onclick='$.employeeDialog.changeSelectEmployee("+id+")' name='checkbox' class='ace'  " +
                        " nameVal=\"" + employeeName + "\" IdVal=\"" + id + "\"  /><span class='lbl'></span></label>");
                    }else{
                        $('td:eq(0)', nRow).html("<label><input type='radio' name='codeRadio' class='ace' " +
                        " nameVal=\"" + employeeName + "\"  IdVal=\"" + id + "\"  /><span class='lbl'></span></label>");
                    }
                },
                "fnDrawCallback":function(){
                	                	
                    var employeeObj = $.employeeDialog.selectEmployee;
                    if (employeeObj) {
                        for (var index in employeeObj) {
                            $("#employee_checkBoxId_"+index).prop("checked",true);
                         }
                    }
                }

            });


        $("#employee-search-query-button").click(function () {
            oTable.fnDraw();
        });


        $("#employee-search-clear-button").click(function () {
            $("#employee-search-form")[0].reset();
        });
        
        

    	
    	this.readSelectEmployees();
        
    },
    changeSelectEmployee : function(id){
        var checkboxId="#employee_checkBoxId_"+id;
        var employeeId= $(checkboxId).attr("IdVal");
        var employeeName =$(checkboxId).attr("nameVal");

        if($(checkboxId).prop("checked")){
            this.selectEmployee[employeeId] =employeeName;
        }else{
            this.selectEmployee[employeeId] =null;
        }
    }
    ,readSelectEmployees : function(){

        var idsStr = $.trim($("#delivery_task_dialog_section_employeeIdStr").val());
        var namesStr = $.trim($("#delivery_task_dialog_section_employeeNameStr").val());
    	if( idsStr && namesStr ){
        	var ids = idsStr.split(",");
        	var names = namesStr.split(",");
        	$.each(ids,function(i,id){
        		$.employeeDialog.selectEmployee[id]=names[i];
        	});
    	}

    }

};

$(function () {
    $.employeeDialog.loadData();
});







