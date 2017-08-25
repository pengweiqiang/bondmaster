package com.huake.bondmaster.model.bean;

/**
 * @author will on 2017/8/24 17:46
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class ResultBean {
    private String success;//1成功 2 失败
    private String message;//结果信息描述  如：该手机号已经注册过；验证码不正确；注册成功等等


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
