var communityNearbyMap = new BMap.Map('communitySection_map');
var locationPoint = null;
communityNearbyMap.centerAndZoom("上海");//默认是上海
initForbid();
initMapMenuContext();
/**
 *  初始化需要禁止的操作
 */
function initForbid() {
    if (communityNearbyMap == undefined || communityNearbyMap == null) {
        alert("地图没有初始化不能够初始化操作!");
        return;
    }
    communityNearbyMap.enableScrollWheelZoom();
    communityNearbyMap.disableDoubleClickZoom();
}
/**
 * 初始化需要右键绑定的操作
 */
function initMapMenuContext() {
    if (communityNearbyMap == undefined || communityNearbyMap == null) {
        alert("地图没有初始化不能够初始化右键菜单!");
    }
}

/**
 * 定位
 */
function locationPosition() {
    var cityName = $("select[name='city']").find("option:selected").val();
    if (cityName == null) {
        alert("请选择城市!");
        return;
    }
    communityNearbyMap.clearOverlays();
    var address = $("#communityNearby-search-name").val();
    var realAddress = cityName + address;
    // 创建地址解析器实例
    var myGeo = new BMap.Geocoder();
    // 将地址解析结果显示在地图上,并调整地图视野
    myGeo.getPoint(realAddress, function (point) {
        if (point) {
            locationPoint = point;
            var marker = new BMap.Marker(point);  // 创建中心点标注
            var label = new BMap.Label(realAddress, {offset: new BMap.Size(20, -10)}); //创建标签
            marker.setLabel(label);
            communityNearbyMap.centerAndZoom(point, 16);
            communityNearbyMap.addOverlay(marker);
        } else {
            alert("您选择地址没有解析到结果!");
        }
    }, cityName);
}

/**
 * 查询附近内容
 */
function search() {
    var keyword = $("#communityNearby-keyword-name").val();
    var area = $("#communityNearby-area-name").val();
    if (keyword == null || keyword == '') {
        alert("请输入关键字!");
        return;
    }
    if (area == null || area == '') {
        alert("请输入范围值!");
        return;
    }
    communityNearbyMap.centerAndZoom(locationPoint, 16);
    var local = new BMap.LocalSearch(communityNearbyMap, {
        renderOptions: {map: communityNearbyMap}
    });
    local.searchNearby(keyword, locationPoint, area);
}

/**
 * 导出小区附近的值
 */
function exportCommunityNearby() {
    if (locationPoint == null) {
        alert("请输入定位的点!");
        return;
    }
    var keyword = $("#communityNearby-keyword-name").val();
    var area = $("#communityNearby-area-name").val();
    var pageNum = $("#communityNearby-pageNum-name").val();
    if (keyword == null || keyword == '') {
        alert("请输入关键字!");
        return;
    }
    if (area == null || area == '') {
        alert("请输入范围值!");
        return;
    }
    if (pageNum == null || pageNum == '') {
        alert("页数必填!");
        return;
    }
    var reg = /^\d+$/;
    if (!pageNum.match(reg)) {
        alert("页数必须是数字");
    }

    var geo = locationPoint.lat + "," + locationPoint.lng;
    window.location.href = contextPath + "/communityNearby/export?geo=" + geo + "&keyword=" + keyword + "&areaRadius=" + area + "&pageNum=" + pageNum;
}

/**
 * 清除值
 */
function clearSearchCondition() {
    $("#communityNearby-search-city option").eq(0).attr('selected', 'true');
    $("#communityNearby-area-name").val("");
    $("#communityNearby-search-name").val("")
    $("#communityNearby-keyword-name").val("");
}