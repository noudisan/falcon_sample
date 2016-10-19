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
      单元管理
     <span class="pull-right">
              <hero:button permission='${permissionList}' id="modifyMtBtn" className="btn btn-success btn-sm"
                           authorize="baseCommunityUnit:update"/>
              <hero:button permission="${permissionList}" id="deleteMtBtn" className="btn btn-pink btn-sm"
                           authorize="baseCommunityUnit:delete"/>
     </span>
    </h1>
  </div>
  <form id="CommunityUnit-search-form">
      <div class="row" style="height:40px;">
          <div class="col-sm-3">
              <label class="col-sm-6 ">小区名称:</label>
              <input id="baseCommunityUnit_search_communityName" class="col-sm-6" type="text"/>
          </div>
          <div class="col-sm-3">
              <label class="col-sm-6 ">楼栋名称:</label>
              <input id="baseCommunityUnit_search_buildingName" class="col-sm-6" type="text"/>
          </div>
          <div class="col-sm-3">
              <label class="col-sm-6 ">单元名称:</label>
              <input id="baseCommunityUnit_search_UnitName" class="col-sm-6" type="text"/>
          </div>
          <div class="col-sm-3" style="width:80px;">
              <button style="width:80px;" class="btn btn-warning btn-sm" type="button"
                      onclick="baseCommunityUnitSearch()">
                  <i class="icon-edit"></i>查询
              </button>
          </div>
          <div class="col-sm-3" style="width:80px;margin-left: 10px;">
              <button style="width:80px;" class="btn btn-warning btn-sm" type="button"
                      onclick="baseCommunityUnitClear()">
                  <i class="icon-edit"></i>清空
              </button>
          </div>
      </div>
  </form>

  <div class="row">
    <div class="col-xs-12">
      <div class="table-responsive">
        <div id="baseCommunityUnit-ftable_wrapper" class="dataTables_wrapper">
          <table id="baseCommunityUnit-table"
                 class="table table-striped table-bordered table-hover dataTable"
                 width="100%" aria-describedby="baseCommunityUnit-table_info" style="width: 100%;">
          </table>
        </div>
      </div>
    </div>
  </div>
</div>


<!--修改单元-->
<div id="deliveryCommunityUnit_dialog" class="hide">
    <form class="form-horizontal"  style="min-height: 34px; max-height: none; height: auto;"
          action="${pageContext.request.contextPath}/communityUnit/updateUnit" id="deliveryCommunityUnit_dialog_form">
        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityUnit_dialog_id">单元编号*:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="id" id="deliveryCommunityUnit_dialog_id" readonly />
                </div>
            </div>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityUnit_dialog_name">单元名称:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="name" id="deliveryCommunityUnit_dialog_name" maxlength="30" />
                </div>
            </div>
        </div>

        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityUnit_dialog_buildingName">楼栋名称:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="buildingName" id="deliveryCommunityUnit_dialog_buildingName" readonly />
                </div>
            </div>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityUnit_dialog_communityName">小区名称:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="communityName" id="deliveryCommunityUnit_dialog_communityName" readonly />
                </div>
            </div>
        </div>
        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityUnit_dialog_floorNum">层数:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="buildingNum" id="deliveryCommunityUnit_dialog_floorNum"  />
                </div>
            </div>
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityUnit_dialog_households">每层户数:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="households" id="deliveryCommunityUnit_dialog_households"  />
                </div>
            </div>
        </div>
        <div class="row" style="height:40px;margin-top:10px;">
            <div class="col-sm-6">
                <label class="col-sm-4 control-label col-xs-12  no-padding-right" for="deliveryCommunityUnit_dialog_allNum">总户数:</label>
                <div class="col-xs-12 col-sm-6">
                    <input type="text" name="allNum" id="deliveryCommunityUnit_dialog_allNum"  />
                </div>
            </div>
        </div>

    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/deliveryCommunityUnit.js"></script>

