<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="hero" uri="http://www.xiaoquwuyou.com/zhiyi/tags" %>
<html>
<head>
    <title>结算单价信息</title>
</head>
<body>
<div id="page-content" class="page-content">
    <div class="page-header">
        <h1>
            结算单价管理
     <span class="pull-right">
         <hero:button permission='${permissionList}' id="add_settlePrice_Btn" className="btn btn-info btn-sm"
                      authorize="settlePrice:add"/>
         <hero:button permission='${permissionList}' id="update_settlePrice_Btn" className="btn btn-success btn-sm"
                     authorize="settlePrice:update"/>
     </span>
        </h1>
    </div>
    <form id="city-search-form">

        <div class="row" style="height:40px;">
            <%--<div class="col-sm-2">--%>
            <%--<label class="col-sm-6 ">省:</label>--%>
            <%--<input type="text" id="search_province" name="province" maxlength="20" class="col-sm-6" />--%>
            <%--</div>--%>
            <div class="col-sm-2">
                <label class="col-sm-6 ">城市:</label>
                <input type="text" id="search_city" name="city" maxlength="20" class="col-sm-6"/>
            </div>
            <div class="col-sm-3">
                <label class="col-sm-3">投递方式:</label>
                <select id="search_sendStyle" name="sendStyle">
                    <option value="" selected>-请选择-</option>
                    <option value="INLETTER">内信箱</option>
                    <option value="OUTLETTER">外信箱</option>
                    <option value="STAIRS">步梯入户</option>
                    <option value="LIFT">电梯入户</option>
                    <option value="FOOD">饭补</option>
                    <option value="BAD_WEATHER">恶劣天气补贴</option>
                </select>
            </div>
            <div class="col-sm-3" style="width:80px;">
                <button style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="search()">
                    <i class="icon-edit"></i>查询
                </button>
            </div>
            <div class="col-sm-3" style="width:80px;margin-left: 10px;">
                <button style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="cleanAll()">
                    <i class="icon-edit"></i>清空
                </button>
            </div>
        </div>
    </form>

    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <div id="baseCity-ftable_wrapper" class="dataTables_wrapper">
                    <table id="data-table"
                           class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="data-table_info" style="width: 100%;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="base_city_dialog" class="hide">
    <form action="${pageContext.request.contextPath}/settlePrice/saveOrUpdate" id="settle_price_form"
          class="form-horizontal"
          role="form" method="POST">
        <input type="hidden" id="saveOrUpdate_id" name="id">
        <%--<div class="form-group">--%>
        <%--<label class="col-xs-2 text-right" for="saveOrUpdate_province">省份:</label>--%>
        <%--<div class="col-xs-12 col-sm-3">--%>
        <%--<input type="text" name="province" id="saveOrUpdate_province"  maxlength="20" />--%>
        <%--</div>--%>
        <%--</div>--%>
        <div class="form-group">
            <label class="col-xs-2 text-right" for="settle_price_form">城市:</label>
            <div class="col-xs-12 col-sm-3">
                <select id="city" name="city">
                    <option value="">--请选择--</option>
                    <c:forEach var="city" items="${cityList}">
                        <option value="${city}">${city}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right" for="saveOrUpdate_sendStyle">结算方式:</label>

            <div class="col-xs-12 col-sm-3">
                <select id="saveOrUpdate_sendStyle" name="sendStyle">
                    <option value="" selected>-请选择-</option>
                    <option value="INLETTER">内信箱</option>
                    <option value="OUTLETTER">外信箱</option>
                    <option value="STAIRS">步梯入户</option>
                    <option value="LIFT">电梯入户</option>
                    <option value="LIFT">电梯入户</option>
                    <option value="FOOD">饭补</option>
                    <option value="BAD_WEATHER">恶劣天气补贴</option>
                </select>
            </div>
        </div>
        <div class="form-group" id="city_status">
            <label class="col-xs-2 text-right" for="saveOrUpdate_price">单价:</label>

            <div class="">
                <input type="text" name="price" id="saveOrUpdate_price" maxlength="5"/><span>元</span>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/settlePrice.js"></script>
