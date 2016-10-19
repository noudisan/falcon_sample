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
      楼栋管理
        <span class="pull-right">
            <hero:button permission='${permissionList}' id="addBuildingMtBtn" className="btn btn-info btn-sm"
                        authorize="baseBuilding:add"/>
            <hero:button permission='${permissionList}' id="modifyMtBtn" className="btn btn-success btn-sm"
                        authorize="baseBuilding:update"/>
            <hero:button permission="${permissionList}" id="deleteMtBtn" className="btn btn-pink btn-sm"
                         authorize="baseBuilding:delete"/>
      </span>
    </h1>
  </div>
  <form id="CommunityBuilding-search-form">
    <div class="row" style="height:40px;">
    <div class="col-sm-3">
        <label class="col-sm-6 ">小区名称:</label>
        <input id="baseCommunityBuilding_search_CommunityName"  class="col-sm-6" type="text"/>
    </div>
      <div class="col-sm-3">
        <label class="col-sm-6 ">楼栋名称:</label>
        <input id="baseCommunityBuilding_search_BuildingName"  class="col-sm-6" type="text"/>
      </div>
      <div class="col-sm-3" style="width:80px;">
        <button  style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="baseCommunityBuildingSearch()">
          <i class="icon-edit"></i>查询
        </button>
      </div>
      <div class="col-sm-3" style="width:80px;margin-left: 10px;">
        <button  style="width:80px;" class="btn btn-warning btn-sm" type="button" onclick="baseCommunityBuildingClear()">
          <i class="icon-edit"></i>清空
        </button>
      </div>
    </div>
  </form>

  <div class="row">
    <div class="col-xs-12">
      <div class="table-responsive">
        <div id="baseCommunityBuilding-ftable_wrapper" class="dataTables_wrapper">
          <table id="baseCommunityBuilding-table"
                 class="table table-striped table-bordered table-hover dataTable"
                 width="100%" aria-describedby="baseCommunityBuilding-table_info" style="width: 100%;">
          </table>
        </div>
      </div>
    </div>
  </div>
</div>


<%@ include file="common/communityDialog.jsp"%>

<div id="add_communityBuilding_dialog" class="hide no-padding">
  <div style="margin-top: 10px;margin-left: 10px">
    <%--城市:<input type="text" id="communityBuilding_dialog_city"/>--%>
    <%--小区:<input type="text" id="communityBuilding_dialog_community"/>--%>
    <input type="hidden" id="community-lnglat-select-longitude"/>
    <input type="hidden" id="community-lnglat-select-latitude"/>
    <%--<a href="#" style="margin-left: 15px;" height="40" class="btn btn-info btn-sm" onclick="searchCommunityLngLat()">查找</a>--%>
      <label style="color: #d3d3d3;margin-left: 20px">请右键新增或删除新增楼栋</label>
  </div>
  <iframe id="map-community-building-lnglat-container" name="map-community-building-lnglat-container"
          src="${pageContext.request.contextPath}/assets/html/MapCommunityBuildingPoint.html" scrolling="no"
          frameborder="0" style="width: 100%; height: 450px;margin-top: 10px;"></iframe>
</div>


<!--修改楼栋-->
<div id="deliveryCommunityBuilding_dialog" class="hide">
    <form class="form-horizontal"  style="min-height: 34px; max-height: none; height: auto;"
          action="${pageContext.request.contextPath}/communityBuilding/updateBuilding" id="deliveryCommunityBuilding_dialog_form">
        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityBuilding_dialog_id">楼栋编号*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="id" id="deliveryCommunityBuilding_dialog_id" readonly />
                </div>
            </div>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityBuilding_dialog_name">楼栋名称:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="name" id="deliveryCommunityBuilding_dialog_name" maxlength="30" />
                </div>
            </div>
        </div>
        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityBuilding_dialog_longitude">精度*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="longitude" id="deliveryCommunityBuilding_dialog_longitude" readonly />
                </div>
            </div>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityBuilding_dialog_latitude">维度:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="latitude" id="deliveryCommunityBuilding_dialog_latitude" readonly />
                </div>
            </div>
        </div>
        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityBuilding_dialog_communityName">所属小区:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="communityName" id="deliveryCommunityBuilding_dialog_communityName" readonly />
                    <input type="hidden" name="communityId" id="deliveryCommunityBuilding_dialog_communityId"  />
                </div>
            </div>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityBuilding_dialog_remark">备注:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="remark" id="deliveryCommunityBuilding_dialog_remark"  maxlength="100"/>
                </div>
            </div>
        </div>

    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/deliveryCommunityBuilding.js"></script>

