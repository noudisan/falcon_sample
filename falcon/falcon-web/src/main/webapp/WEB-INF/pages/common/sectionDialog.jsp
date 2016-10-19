<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<div id="ext-section-dialog" class="hide">
    <div id="page-section-content" class="page-content">
        <form id="section-search-form">
            <div class="row" style="height:40px;">
                <div class="col-sm-3">
                    <label class="col-sm-6 control-label  no-padding-right">所属城市:</label>
                    <input id="section-search-city" name="city" class="col-sm-6" type="text"/>
                </div>
                <div class="col-sm-3">
                    <label class="col-sm-6 control-label  no-padding-right">板块:</label>
                    <input id="section-search-name" name="name" class="col-sm-6" type="text"/>
                </div>
                <div class="col-sm-3" style="width:80px;">
                    <button id="section-search-query-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                    <i class="icon-edit"></i>查询
                    </button>
                </div>
                <div class="col-sm-3" style="width:80px;margin-left: 10px;">
                    <button id="section-search-clear-button" style="width:80px;" class="btn btn-warning btn-sm" type="button"/>
                    <i class="icon-undo"></i>清空
                    </button>
                </div>
            </div>
        </form>
        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">
                    <div class="dataTables_wrapper">
                        <table id="sectionList-table" class="table table-striped table-bordered table-hover dataTable"
                               width="100%" aria-describedby="sectionList-table_info" style="width: 100%;">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
