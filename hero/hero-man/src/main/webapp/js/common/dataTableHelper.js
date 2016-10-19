$.dataTableSelectAll = function (o) {
    var checked = $(o).is(':checked');
    var table = $(o).parent().parent().parent().parent();
    return table.find("label input:checkbox").prop("checked", checked);
}

$.dataTableCheckedOneItem = function (tableId, message1, message2) {
    var table = $("#"+tableId);
    var checkItems = table.find("label input[type='checkbox']:checked");
    var selectedLength = checkItems.length;
    if (selectedLength <= 0) {
        $.warnDialog(message1);
        return false;
    }
    if (selectedLength > 1) {
        $.warnDialog(message2);
        return false;
    }
    return checkItems;
}

$.dataTableCheckedItem = function (tableId, message1) {
    var table = $("#"+tableId);
    var checkItems = table.find("label input[type='checkbox']:checked");
    var selectedLength = checkItems.length;
    if (selectedLength <= 0) {
        $.warnDialog(message1);
        return false;
    }
    return checkItems;
}

$.dataTableSelectedItem = function (tableId) {
    var table = $("#"+tableId);
    return table.find("label input[type='checkbox']:checked");
}


$.dataTableAllCheckBox = function (tableId) {
    var table = $("#"+tableId);
    return table.find("label input[type='checkbox']");
}

