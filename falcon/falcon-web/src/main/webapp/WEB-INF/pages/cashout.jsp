<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="hero" uri="http://www.xiaoquwuyou.com/zhiyi/tags" %>
<html>
<head>
    <title>提现信息</title>

</head>
<body>
<div id="page-content" class="page-content">
    <div class="page-header">
        <h1>
            提现查询
         <span class="pull-right">
             <hero:button permission='${permissionList}' id="cash_fail_Btn" className="btn btn-info btn-sm"
                          authorize="cash:fail"/>
             <hero:button permission='${permissionList}' id="cash_success_Btn" className="btn btn-success btn-sm"
                          authorize="cash:success"/>
         </span>
        </h1>
    </div>
    <%--搜索栏--%>
    <form id="account-search-form">
        <div class="row" style="height: 48px">
            <div class="col-sm-2">
                <label class="col-sm-6 control-label no-padding-right">开始时间：</label>
                <%--<input class="col-sm-6" id="cash-search-startDate" name="startDate" type="text" maxlength="20"/>--%>
                <input type="text" class="col-sm-6 no-padding-right no-padding-left" id="cash-search-startDate"
                       name="startDate"
                       onclick="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
                       class="form-control"/>

                <%--<input type="text" style="width: 100px; height:28px;" name="startDate" id="cash-search-startDate" class="form-control" />--%>
            </div>

            <div class="col-sm-2">
                <label class="col-sm-6 control-label no-padding-right">截止时间：</label>
                <%--<input class="col-sm-6" id="cash-search-endDate" name="endDate" type="text" maxlength="20"/>--%>
                <input type="text" class="col-sm-6 no-padding-right no-padding-left" id="cash-search-endDate"
                       name="endDate"
                       onclick="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
                       class="form-control"/>
                <%--<input type="text" style="width: 100px; height:28px;" name="endDate" id="cash-search-endDate" class="form-control" />--%>
            </div>

            <div class="col-sm-2">
                <label class="col-sm-6 control-label no-padding-right">姓名：</label>
                <input class="col-sm-6" id="cash-search-name" name="name" type="text" maxlength="20">
            </div>

            <div class="col-sm-3">
                <label class="col-sm-4 control-label padding-right">状态：</label>
                <select class="col-sm-8 " id="cash-search-dealStatus" name="dealStatus">
                    <option value="">-请选择-</option>
                    <option value="0">已申请</option>
                    <option value="1">成功</option>
                    <option value="2">失败</option>
                </select>
                <%--<input class="col-sm-6" id="cash-search-status" name="dealStatus" type="text" maxlength="20">--%>
            </div>

            <div class="col-sm-3" style="width: 80px;">
                <button id="account-search-button" style="width: 80px" class="btn btn-warning btn-sm"
                        type="button" onclick="cashSearch()">
                    <i class="icon-edit"></i>查询
                </button>
            </div>

            <div class="col-sm-3" style="width: 80px;margin-left: 10px">
                <button id="account-search-clear-button" style="width: 80px;" class="btn btn-warning btn-sm"
                        type="button" onclick="cashClear(this)">
                    <i class="icon-edit"></i>清空
                </button>
            </div>
        </div>
    </form>
    <%--搜索结果--%>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <div class="dataTables_wrapper">
                    <table id="cashOut-table" class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="cashOut-table_info"
                           style="width: 100%;font-size: 12px">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%--弹窗--%>
<div id="cashOut_dialog" class="hide">
    <table id="cashOut_table" class="table table-striped table-bordered table-hover dataTable"
           width="100%" aria-describedby="cashOut_tables_info"
           style="width: 100%; font-size: 12px;">

    </table>
</div>
</div>
</body>
</html>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/cashout.js"></script>
