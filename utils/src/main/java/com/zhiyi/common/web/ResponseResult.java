package com.zhiyi.common.web;

import java.util.List;

public class ResponseResult<T> {
	public DtPagerResponse<T> getResponse(List<T> data, int count, DtRequest dtReq) {
		DtPagerResponse<T> dpr = new DtPagerResponse<T>();
		dpr.setsEcho(dtReq.getsEcho());
		PagerQueryResult<T> pQR = new PagerQueryResult<T>();
		pQR.setDataList(data);
		pQR.setTotal(count);
		dpr.setupResult(pQR);
		return dpr;
	}

    public static <T> DtPagerResponse<T> value(List<T> results, int totalSize, DtRequest dtReq) {
        DtPagerResponse<T> dpr = new DtPagerResponse<T>();
        dpr.setsEcho(dtReq.getsEcho());
        PagerQueryResult<T> pQR = new PagerQueryResult<T>();
        pQR.setDataList(results);
        pQR.setTotal(totalSize);
        dpr.setupResult(pQR);
        return dpr;
    }
}
