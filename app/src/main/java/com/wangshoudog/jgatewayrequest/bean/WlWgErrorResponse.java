package com.wangshoudog.jgatewayrequest.bean;

/**
 *  网关返回错误信息
 */
public class WlWgErrorResponse {
    private Integer code;
    private String zh_desc;
    private String en_desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getZh_desc() {
        return zh_desc;
    }

    public void setZh_desc(String zh_desc) {
        this.zh_desc = zh_desc;
    }

    public String getEn_desc() {
        return en_desc;
    }

    public void setEn_desc(String en_desc) {
        this.en_desc = en_desc;
    }
}
