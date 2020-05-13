package com.wangshoudog.jgatewayrequest.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口依赖注入声明 用于读取接口具体信息
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Reference {

    /**
     * 接口的服务名
     * @returns
     */
    String service() default "";

    /**
     * 接口别名（分组）
     * @return
     */
    String alias() default "";

    /**
     * 接口版本
     * @return
     */
    String version() default "";

}
