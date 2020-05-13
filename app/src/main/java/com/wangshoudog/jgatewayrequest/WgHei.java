package com.wangshoudog.jgatewayrequest;

import android.util.Log;

import com.wangshoudog.jgatewayrequest.annotation.Reference;
import com.wangshoudog.jgatewayrequest.annotation.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 接口调用实现
 */
public class WgHei {

    private static volatile WgHei WG_HEI;
    private WgRequest request = new WgRequest();

    private WgHei() {

    }

    public static WgHei getInstance() {
        if (WG_HEI == null) {
            synchronized (WgHei.class) {
                WG_HEI = new WgHei();
            }
        }

        return WG_HEI;
    }

    /**
     * 接口信息缓存
     */
    private Map<Class<?>,Object> serviceContainer = new HashMap<>();

    /**
     *  初始化方法，获取相关服务信息，注册
     * @param object
     */
    public void inject(Object object) {
        Field[] fields = getFields(object);
        for (Field field : fields) {
            if (field.isAnnotationPresent(Service.class)) {
                Class<?> clazz = field.getType();
                if (clazz.getAnnotation(Reference.class) == null) {
                    Log.e("WG_HEI","当前接口地址未配置信息");
                    continue;
                }

                // 获取接口实现信息
                Object objectImpl = serviceContainer.get(clazz);
                if (objectImpl == null) {
                    objectImpl = createService(clazz);
                    serviceContainer.put(clazz,objectImpl);
                }

                // 使用动态代理生成实现类，进行替换接口
                try {
                    field.setAccessible(true);
                    field.set(object,objectImpl);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T createService(final Class<?> clazz) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Reference reference = clazz.getAnnotation(Reference.class);
                // 获取服务名
                String service = reference.service();
                // 获取别名
                String alias = reference.alias();
                // 获取方法名
                String methodsName= method.getName();
                // 真正执行请求
                return request.wlWgRequest(getUrlName(service,alias,methodsName),args);
            }
        });
    }

    /**
     * 拼接请求路径
     * @param serviceName 服务名
     * @param aliasName 别名
     * @param methodName 方法名
     * @return 拼接后
     */
    private String getUrlName(String serviceName, String aliasName, String methodName) {
        return serviceName + "/" + aliasName + "/" +methodName;
    }
    /**
     * 获取类中声明对象
     * @param object
     * @return
     */
    private Field[] getFields(Object object) {
        Set<Field> fieldSet = new HashSet<>();
        // 获取当前类所有声明对象
        fieldSet.addAll(Arrays.asList(object.getClass().getDeclaredFields()));
        // 获取父类公共声明对象
        fieldSet.addAll(Arrays.asList(object.getClass().getFields()));
        // 获取数据对象
        Field[] objects = (Field[]) fieldSet.toArray();
        return objects;
    }
}
