package com.wangshoudog.jgatewayrequest.network;

import com.wangshoudog.jgatewayrequest.bean.WlWgResponseBean;

import java.util.Map;


import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 网关请求API
 */
public interface WGApi {

    /**
     * 物流网关调用
     * @param url 路径
     * @param params 参数
     * @param headMap 基础信息
     * @return
     */
    @POST
    Observable<WlWgResponseBean> post2WLWGJson(
            @Url String url,
            @Body Object[] params,
            @HeaderMap Map<String, String> headMap);
}
