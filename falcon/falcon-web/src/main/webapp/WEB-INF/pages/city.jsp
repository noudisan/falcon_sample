<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="hero" uri="http://www.xiaoquwuyou.com/zhiyi/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body onload="pageOnLoad()"></body>
<div id="page-content" class="page-content">
  <div class="page-header">
    <h1>
      城市管理
     <span class="pull-right">
         <hero:button permission='${permissionList}' id="addMtBtn" className="btn btn-info btn-sm"
                      authorize="baseCity:addCity"/>
         <hero:button permission='${permissionList}' id="modifyMtBtn" className="btn btn-success btn-sm"
                      authorize="baseCity:updateCity"/>
     </span>
    </h1>
  </div>
  <form id="city-search-form">
    <div class="row" style="height:40px;">
      <div class="col-sm-2">
        <label class="col-sm-6 ">城市:</label>
        <input id="baseCity_search_Name" name="city" class="col-sm-6" type="text"/>
      </div>
      <div class="col-sm-3">
        <label class="col-sm-3">状态:</label>
        <select id="baseCity_search_isLocked" name="isLocked">
          <option value="" selected >-请选择-</option>
          <option value="UNLOCK"  >未锁定</option>
          <option value="LOCK">锁定</option>
        </select>
      </div>
      <div class="col-sm-3" style="width:80px;">
        <button  style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="baseCitySearch()">
          <i class="icon-edit"></i>查询
        </button>
      </div>
      <div class="col-sm-3" style="width:80px;margin-left: 10px;">
        <button  style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="baseCityClear()">
          <i class="icon-edit"></i>清空
        </button>
      </div>
    </div>
  </form>

  <div class="row">
    <div class="col-xs-12">
      <div class="table-responsive">
        <div id="baseCity-ftable_wrapper" class="dataTables_wrapper">
          <table id="baseCity-table"
                 class="table table-striped table-bordered table-hover dataTable"
                 width="100%" aria-describedby="baseCity-table_info" style="width: 100%;">
          </table>
        </div>
      </div>
    </div>
  </div>
</div>



<div id="base_city_dialog" class="hide" >
  <form action="${pageContext.request.contextPath}/basecity/saveOrUpdate" id="base_city_form" class="form-horizontal"
        role="form"  method="POST"  >
    <input type="hidden" id="base-city_id" name="id">
    <div class="form-group">
      <label class="col-xs-2 text-right" for="base_city_dialog_province">省份:</label>
      <div class="col-xs-12 col-sm-3">
        <input type="text" name="province" id="base_city_dialog_province"  />
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 text-right" for="base_city_dialog_cityName">城市:</label>
      <div class="col-xs-12 col-sm-3">
        <input type="text" name="cityName" id="base_city_dialog_cityName"  />
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 text-right" for="base_city_dialog_cityAbbr">城市地址:</label>
      <div class="col-xs-12 col-sm-3">
        <input type="text" name="cityAbbr" id="base_city_dialog_cityAbbr"  />
      </div>
    </div>
    <div class="form-group" id="city_status">
      <label class="col-xs-2 text-right" for="base_city_dialog_isLocked">城市锁定:</label>
      <div class="col-xs-12 col-sm-3">
        <select id="base_city_dialog_isLocked" name="isLocked">
          <option value="UNLOCK" selected >未锁定</option>
          <option value="LOCK">锁定</option>
        </select>
      </div>
    </div>
  </form>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/city.js"></script>
