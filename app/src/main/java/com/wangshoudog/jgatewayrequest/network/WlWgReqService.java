package com.wangshoudog.jgatewayrequest.network;

import android.support.annotation.Nullable;

import java.util.Map;

/**
 * 物流网关服务
 * @param <T>
 */
public abstract class WlWgReqService<T> {

    // 网关网络请求
    protected WGApi wgApi;
    private static final String normalUrl = "";

    public WlWgReqService() {
        this(normalUrl);
    }

    public WlWgReqService(String baseUrl) {
        wgApi = new NetWork.Builder(baseUrl).build().getApi(WGApi.class);
    }

    public abstract T wlWgReq(String url,Object[] param, @Nullable Map<String, String> headMap);
}
