package com.wangshoudog.jgatewayrequest;

import android.text.TextUtils;

import com.wangshoudog.jgatewayrequest.bean.BusinessBean;
import com.wangshoudog.jgatewayrequest.bean.WlWgResponseBean;
import com.wangshoudog.jgatewayrequest.network.WlWgReq;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import rx.functions.Func1;

public class WgRequest implements Func1<WlWgResponseBean,BusinessBean> {

    // 网关请求Helper类
    private WlWgReq<BusinessBean> wlWgReq;

    public WgRequest() {
        // 设置请求地址
        wlWgReq = new WlWgReq<>(WgCon.ON_LINE_WG_URL);
        wlWgReq.setResultFunc(this);
    }

    protected Observable<BusinessBean> wlWgRequest(String url, Object[] param) {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("网关请求参数错误");
        }
        // 调用请求，传入url,param，headMap
        return wlWgReq.wlWgReq(url,param,new HashMap<String, String>());
    }


    @Override
    public BusinessBean call(WlWgResponseBean wlWgResponseBean) {
        // 数据返回，对返回数据进行解析
        if (!TextUtils.isEmpty(wlWgResponseBean.getCode())) {
            return new BusinessBean(wlWgResponseBean);
        } else {
            try {
                return new BusinessBean(wlWgResponseBean.getError_response());
            } catch (Exception e) {
                e.printStackTrace();
                // 访问失败返回给业务层网关错误码和网关错误信息
                return new BusinessBean();
            }
        }
    }
}
