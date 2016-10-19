<%@ taglib prefix="hero" uri="http://www.xiaoquwuyou.com/zhiyi/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Adminstrator
  Date: 2015-07-07
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>定时任务手工处理页面</title>
</head>
<body>
<table class="table table-striped table-bordered table-hover dataTable"
       width="100%" aria-describedby="data-table_info" style="width: 50%;">
    <tr>
        <td>任务名称</td>
        <td>操作</td>
    </tr>
    <tr>
        <td>结算</td>
        <td><hero:button permission='${permissionList}' id="settle_settleAmount_Btn"
                         className="btn btn-primary btn-sm"
                         authorize="settle:settleAmount"/></td>
    </tr>
</table>

</body>
</html>
<script type="application/javascript">
    $("#settle_settleAmount_Btn").click(function () {
        $.ajax({
            url: getValue('#contextPath') + "/settle/settleAmount?t=" + Math.random(),
            type: "get",
            async: false,
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                alert("执行完成");
            },
            error: function (data, textStatus, jqXHR) {
                alert("服务器出现异常，请联系管理员！")
            }
        });
    });
</script>
