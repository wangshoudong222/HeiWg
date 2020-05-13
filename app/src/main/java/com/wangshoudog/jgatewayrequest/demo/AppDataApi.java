package com.wangshoudog.jgatewayrequest.demo;

import com.wangshoudog.jgatewayrequest.annotation.Reference;
import com.wangshoudog.jgatewayrequest.bean.BusinessBean;

import rx.Observable;

@Reference(service = "com.xx.api",alias = "online",version = "01")
public interface AppDataApi {

    Observable<BusinessBean> getData();

}
