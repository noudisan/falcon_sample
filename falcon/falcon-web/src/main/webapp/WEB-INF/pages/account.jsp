<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>账户信息</title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/account.js"></script>
</head>
<body>
<div id="page-content" class="page-content">
  <div class="page-header">
    <h1>
      账户查询
     <span class="pull-right"></span>
    </h1>
  </div>
   <%--搜索栏--%>
  <form id="account-search-form">
    <div class="row" style="height: 48px">
      <div class="col-sm-3">
        <label class="col-sm-6 control-label no-padding-right">姓名：</label>
        <input class="col-sm-6" id="account_search_name" name="name" type="text" maxlength="20"/>
      </div>

      <div class="col-sm-3">
        <label class="col-sm-6 control-label no-padding-right">手机号：</label>
        <input class="col-sm-6" id="account_search_phone" name="phone" type="text" maxlength="20">
      </div>

      <div class="col-sm-3" style="width: 80px;">
        <button id="account-search-button" style="width: 80px"  class="btn btn-warning btn-sm"
                type="button" onclick="accountSearch()">
          <i class="icon-edit"></i>查询
        </button>
      </div>

      <div class="col-sm-3" style="width: 80px;margin-left: 10px">
        <button id="account-search-clear-button" style="width: 80px;" class="btn btn-warning btn-sm"
                type="button" onclick="accountClear(this)">
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
          <table id="accountInfo-table" class="table table-striped table-bordered table-hover dataTable"
                 width="100%" aria-describedby="accountInfo-table_info"
                 style="width: 100%;font-size: 12px">
          </table>
        </div>
      </div>
    </div>
  </div>
</div>

</div>
</body>
</html>
