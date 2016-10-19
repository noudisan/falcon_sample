var accountInfoTable = accountInfoTable || {}
$(function () {
    accountInfoTable = $('#accountInfo-table').dataTable({
        "bDestroy": true,
        "bServerSide": true,
        "sServerMethod": "POST",
        "sAjaxSource":  getValue('#contextPath')+"/account/search",
        "bProcessing": true,
        "aaSorting": [
            [1, 'asc']
        ],
        "oLanguage": {
            "sProcessing": "正在获取数据，请稍候..."
        },
        "aoColumns": [
            {
                "sWidth": "4%",
                "bSortable": false,
                "iDataSort":"ID",
                "sTitle": '序号',
                "mData": "id"
            },
            {"sWidth": "10%", "bSortable": true, "iDataSort":"ID", "sTitle": "编号", "mData": "id"},
            {"sWidth": "12%", "bSortable": true, "iDataSort":"USER_NAME", "sTitle": "姓名", "mData": "userName"},
            {"sWidth": "9%", "bSortable": true, "iDataSort":"PHONE", "sTitle": "电话", "mData": "phone"},
            {"sWidth": "8%", "bSortable": true, "iDataSort":"ACCOUNT_AMOUNT", "sTitle": "账户余额", "mData": "accountAmount"}
            //{"sWidth": "8%", "bSortable": false, "mData": ""}
        ],
        "bFilter": false,
        "iDisplayLength": 20,
        "aLengthMenu": [10, 20, 50],
        "fnServerParams": function (aoData) {
            aoData.push({"name": "userName", "value": getValue('#account_search_name')});
            aoData.push({"name": "phone", "value": getValue('#account_search_phone')});
        },
        "fnInitComplete": function (oSettings, json) {

        },
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {

            var canEdit = true,
                canLock = true,
                editButton = '',
                lockButton = '';
            /*登陆校验*/
            /* var editAuthentication = $("#edit_userstore_authentication").val(),
             lockAuthentication = $("#lock_userstore_authentication").val(),
             adminAuthentication = $("#admin_authentication").val();
             if (userAuthority.indexOf(editAuthentication) > -1 ||
             userAuthority.indexOf(adminAuthentication) > -1) {
             canEdit = true;
             }

             if (userAuthority.indexOf(lockAuthentication) > -1 ||
             userAuthority.indexOf(adminAuthentication) > -1) {
             canLock = true;
             }*/

        }
    });

});

function accountSearch() {
    accountInfoTable.fnDraw();
}

function accountClear(){
    $("#accunt-search-name").val("");
    $("#account-search-phone").val("");

}

