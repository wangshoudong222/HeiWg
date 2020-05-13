package com.wangshoudog.jgatewayrequest.network;

import android.support.annotation.Nullable;

import com.wangshoudog.jgatewayrequest.bean.WlWgResponseBean;

import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * 物流网关请求
 * @param <T>
 */
public class WlWgReq<T> extends WlWgReqService<Observable<T>> {

    // 请求解析方法
    protected Func1<WlWgResponseBean, T> resultFunc;

    public WlWgReq() {
        super();
    }

    public WlWgReq(String url) {
        super(url);
    }

    /**
     * 设置业务逻辑解析类
     *
     * @param resultFunc 业务逻辑解析类
     */
    public void setResultFunc(Func1<WlWgResponseBean, T> resultFunc) {
        this.resultFunc = resultFunc;
    }

    @Override
    public Observable<T> wlWgReq(String method, Object[] params, @Nullable Map<String, String> headMap) {
        if (wgApi == null) {
            throw new IllegalStateException("未初始化Api");
        }
        // 执行请求
        return wgApi.post2WLWGJson(method,params,headMap)
                // 请求返回统一网关处理
                .map(resultFunc)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}


