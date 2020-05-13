package com.wangshoudog.jgatewayrequest.network;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import rx.Subscriber;

/**
 * 请求回调类
 *
 */
public abstract class WgSubscriber<T> extends Subscriber<T> {

    // 上下文引用
    protected Context context;
    protected View view;

    public WgSubscriber(Context context) {
        this.context = context;
    }

    public WgSubscriber(View view) {
        this.view = view;
        context = view.getContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        showDialog();
        setViewDisable();
    }

    @Override
    public void onCompleted() {
        dismissDialog();
    }

    @Override
    public void onError(Throwable e) {
        dismissDialog();
        setViewEnable();
        if (context != null) {
            Toast.makeText(context, "网络异常，请检查网络配置", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNext(T t) {
        setViewEnable();
        onSuccess(t);
    }

    public abstract void onSuccess(T t);

    /**
     * 显示Dialog
     */
    private void showDialog() {
        if (context instanceof Activity) {
            //正在加载
        }
    }

    /**
     * 关闭Dialog
     */
    private void dismissDialog() {
        if (context instanceof Activity) {
            //关闭
        }
        // 置空对context的引用
        context = null;
    }

    /**
     * 关闭控件使能
     */
    private void setViewDisable() {
        if (view != null && view.isEnabled()) {
            view.setEnabled(false);
        }
    }

    /**
     * 开启控件使能
     */
    protected void setViewEnable() {
        if (view != null && !view.isEnabled()) {
            view.setEnabled(true);
        }
    }
}

