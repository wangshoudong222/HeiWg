package com.wangshoudog.jgatewayrequest.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建网络访问
 *
 */
public class NetWork {

    // 基础URL设置
    private String baseUrl;
    // 默认超时时间
    private long timeout;
    // 拦截器
    private Interceptor interceptor;
    // 是否添加转换器
    private boolean addConverter;

    /**
     * NetWork构建者
     */
    public static class Builder {
        // 基础URL设置
        private String baseUrl;
        // 默认超时时间
        private long timeout = 30;
        // 拦截器
        private Interceptor interceptor;
        // 是否添加转换器
        private boolean addConverter = true;

        /**
         * baseUrl为必填项
         *
         * @param baseUrl 基础Url
         */
        public Builder(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public Builder timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder interceptor(Interceptor interceptor) {
            this.interceptor = interceptor;
            return this;
        }

        public Builder addConverter(boolean addConverter) {
            this.addConverter = addConverter;
            return this;
        }

        public NetWork build() {
            return new NetWork(this);
        }
    }

    /**
     * 构造器
     *
     * @param builder 构造builder
     */
    private NetWork(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.timeout = builder.timeout;
        this.interceptor = builder.interceptor;
        this.addConverter = builder.addConverter;
    }

    /**
     * 根据接口获取对应的网络请求Api
     *
     * @param service 接口类
     * @param <T>     接口类实现
     * @return 接口的动态代理实现类
     */
    public <T> T getApi(final Class<T> service) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        // 超时
        okHttpBuilder.connectTimeout(timeout, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(timeout, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(timeout, TimeUnit.SECONDS);
        // 重试
        okHttpBuilder.retryOnConnectionFailure(false);
        // 是否添加拦截器
        if (interceptor != null) {
            okHttpBuilder.addNetworkInterceptor(interceptor);
        }
        // 请求压缩
        // GzipRequestInterceptor gzipRequestInterceptor = new GzipRequestInterceptor();
        // okHttpBuilder.addInterceptor(gzipRequestInterceptor);

        // Retrofit Builder
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        // 添加结果转换器
        if (addConverter) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));
        }
        return retrofitBuilder.build().create(service);
    }
}
