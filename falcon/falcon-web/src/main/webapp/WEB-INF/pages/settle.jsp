<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="hero" uri="http://www.xiaoquwuyou.com/zhiyi/tags" %>
<html>
<head>
    <title>结算信息</title>

</head>
<div id="page-content" class="page-content">
    <div class="page-header">
        <h1>
            结算信息
          <span class="pull-right">
              <hero:button permission='${permissionList}' id="settle_detail_Btn" className="btn btn-success btn-sm"
                           authorize="settle:detail"/>
              <hero:button permission='${permissionList}' id="settle_addAllowance_Btn" className="btn btn-success btn-sm"
                           authorize="settle:addAllowance"/>
          </span>
        </h1>
    </div>
    <%--搜索栏--%>
    <form id="account-search-form">
        <div class="row" style="height: 48px">
            <div class="col-sm-3">
                <label class="col-sm-4 control-label no-padding-right">派发开始时间：</label>
                <%--<input class="col-sm-6" id="cash-search-startDate" name="startDate" type="text" maxlength="20"/>--%>
                <input type="text" class="col-sm-3 no-padding-right no-padding-left" id="search-startDate"
                       name="startDate"
                       onfocus="WdatePicker({startDate:this.value,dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
                       class="form-control"/>
                <label class="col-sm-1 control-label no-padding-right" style="font-size: 20px;padding-left:12px;">-</label>
                <input type="text" class="col-sm-3 no-padding-right no-padding-left" id="search-endDate"
                       name="endDate"
                       onfocus="WdatePicker({startDate:this.value,dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
                       class="form-control"/>

                <%--<input type="text" style="width: 100px; height:28px;" name="startDate" id="cash-search-startDate" class="form-control" />--%>
            </div>

            <div class="col-sm-2">
                <label class="col-sm-6 control-label no-padding-right">派发城市：</label>
                <%--<input class="col-sm-6" id="cash-search-endDate" name="endDate" type="text" maxlength="20"/>--%>
                <input type="text" class="col-sm-6 no-padding-right no-padding-left" id="search-deliveryCity" name="deliveryCity"
                       class="form-control"/>
                <%--<input type="text" style="width: 100px; height:28px;" name="endDate" id="cash-search-endDate" class="form-control" />--%>
            </div>

            <div class="col-sm-2">
                <label class="col-sm-6 control-label no-padding-right">姓名：</label>
                <input class="col-sm-6" id="settle-search-name" name="name" type="text" maxlength="20">
            </div>


            <div class="col-sm-3" style="width: 80px;">
                <button id="account-search-button" style="width: 80px" class="btn btn-warning btn-sm"
                        type="button" onclick="search()">
                    <i class="icon-edit"></i>查询
                </button>
            </div>

            <div class="col-sm-3" style="width: 80px;margin-left: 10px">
                <button id="account-search-clear-button" style="width: 80px;" class="btn btn-warning btn-sm"
                        type="button" onclick="cleanAll()">
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
                <table id="data-table" class="table table-striped table-bordered table-hover dataTable"
                       width="100%" aria-describedby="settle-table_info"
                       style="width: 100%;font-size: 12px">
                </table>
            </div>
        </div>
    </div>
</div>
</div>

<%--弹窗--%>
<div id="settleDetail_dialog" class="hide" >
    <table id="settleDetail_table" class="table table-striped table-bordered table-hover dataTable"
           width="100%" aria-describedby="settleDetail_tables_info"
            style="width: 100%; font-size: 12px;">

    </table>
</div>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/settle.js"></script>