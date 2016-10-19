<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<div id="page-content" class="page-content">
    <div class="page-header">
        <h1>
           平台资源管理
           <span class="pull-right">
           	<hero:button permission='${permissionList}'   id="resources-add-btn"  className="btn btn-sm" authorize="resources:add" />
           	<hero:button permission='${permissionList}'   id="resources-edit-btn"  className="btn btn-primary btn-sm" authorize="resources:update" />
           	<hero:button permission='${permissionList}'   id="resources-del-btn"  className="btn btn-pink btn-sm" authorize="resources:deleteResources" />
            </span>
        </h1>
    </div>
    <form id="resources-search-form">
        <div class="row" style="height:40px;">
            <div class="col-sm-2">
                <label class="col-sm-4 control-label  no-padding-right">资源名称:</label>
                <input id="resources-search-name" name="name" class="col-sm-8" type="text"/>
            </div>
            <div class="col-sm-2">
                <label class="col-sm-4 control-label  no-padding-right">资源描述:</label>
                <input id="resources-search-describer" name="describer" class="col-sm-8" type="text"/>
            </div>
            <div class="col-sm-2">
                <label class="col-sm-4 control-label   no-padding-left" for="resources-search-platformId">所属平台:</label>
                <div class="col-xs-12 col-sm-8">
	                <select style="line-height:41px;" class="col-sm-12 no-padding-right no-padding-left" name="platformId" id="resources-search-platformId">
	               	 <option value="">请选择</option>
		               <c:forEach items="${platforms}" var="platform">
		               	<option value="${platform.id}">${platform.name}</option>
		               </c:forEach>
	               </select>
               </div>
       	 </div>
        </div>
         <div class="row" style="height:40px;">
            <div class="col-sm-2">
                <label class="col-sm-4 control-label  no-padding-right">开始日期:</label>
                <input class="col-sm-8" type="text" id="resources-search-startDate" name="startDate"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="col-sm-2">
                <label class="col-sm-4 control-label  no-padding-right">结束时间:</label>
                <input class="col-sm-8" type="text" id="resources-search-endDate" name="endDate"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </div>
            <div class="col-sm-2" style="width:80px;">
                <button id="resources-search-query-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                <i class="icon-search"></i>查询
                </button>
            </div>
            <div class="col-sm-2" style="width:80px;margin-left: 10px;">
                <button id="resources-search-clear-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                <i class="icon-undo"></i>清空
                </button>
            </div>
        </div>
    </form>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <div id="resourcesList-ftable_wrapper" class="dataTables_wrapper">
                    <table id="resourcesList-table" class="table table-striped table-bordered table-hover dataTable"
                           width="100%" aria-describedby="resourcesList-table_info" style="width: 100%;">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- add -->
<div id="resources-add-dialog" class="hide">
    <form action="${pageContext.request.contextPath}/resources/add" id="resources-add-form"
          class="form-horizontal" method="POST" style="min-height: 34px; max-height: none; height: auto;">
          <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-add-platformId">所属平台*:</label>
            <div class="col-xs-12 col-sm-6">
                <select style="line-height:41px;" class="col-sm-5 no-padding-right no-padding-left" name="platformId" id="resources-add-platformId">
               	 <option value="">请选择</option>
	               <c:forEach items="${platformList}" var="platform">
	               	<option value="${platform.id}">${platform.name}</option>
	               </c:forEach>
               </select>
            </div>
       </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-add-parentId">父资源*:</label>
            <div class="col-xs-12 col-sm-6">
              <select style="line-height:41px;" class="col-sm-6 no-padding-right no-padding-left" name="parentId" id="resources-add-parentId">
              	   <option value="0">-请选择-</option>
              </select>
            </div>
       </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-add-name">资源名称*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="name" id="resources-add-name"/>
            </div>
        </div>
       <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-add-linkUrl">资源链接:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="linkUrl" id="resources-add-linkUrl"/>
            </div>
       </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-add-authorize">授权标识:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="authorize" id="resources-add-authorize"/>
            </div>
        </div>
       <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-add-orders">资源次序*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="orders" id="resources-add-orders"/>
            </div>
       </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-add-describer">资源描述:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="describer" id="resources-add-describer"/>
            </div>
        </div>
    </form>
</div>

<!-- update -->
<div id="resources-update-dialog" class="hide">
    <form action="${pageContext.request.contextPath}/resources/update" id="resources-update-form"
          class="form-horizontal" method="POST" style="min-height: 34px; max-height: none; height: auto;">
          <input type="hidden" name="id" id="resources-update-id"/>
          <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-update-platformId">所属平台*:</label>
            <div class="col-xs-12 col-sm-6">
                <select style="line-height:41px;" class="col-sm-6 no-padding-right no-padding-left" name="platformId" id="resources-update-platformId" disabled>
                	<option value="">请选择</option>
	               <c:forEach items="${platformList}" var="platform">
	               	<option value="${platform.id}">${platform.name}</option>
	               </c:forEach>
               </select>
            </div>
       </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-update-parentId">父资源*:</label>
            <div class="col-xs-12 col-sm-6">
              <select style="line-height:41px;" class="col-sm-6 no-padding-right no-padding-left" name="parentId" id="resources-update-parentId">
              	     <option value="0">-请选择-</option>
              </select>
            </div>
       </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-update-name">资源名称*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="name" id="resources-update-name"/>
            </div>
        </div>
       <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-update-linkUrl">资源链接:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="linkUrl" id="resources-update-linkUrl"/>
            </div>
       </div>
       <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-update-authorize">授权标识:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="authorize" id="resources-update-authorize"/>
            </div>
       </div>       
       <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-update-orders">资源次序*:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="orders" id="resources-update-orders"/>
            </div>
       </div>
        <div class="form-group">
            <label class="col-sm-4 control-label col-xs-12  no-padding-left" for="resources-update-describer">资源描述:</label>
            <div class="col-xs-12 col-sm-6">
                <input type="text" name="describer" id="resources-update-describer"/>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="${contextPath}/js/resources/resources.js"></script>

