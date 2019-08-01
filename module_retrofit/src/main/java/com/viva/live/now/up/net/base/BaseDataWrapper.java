package com.viva.live.now.up.net.base;

import java.io.Serializable;

/**
 *
 * Created by Liu On 2019/4/13
 * Description:数据包装器，对应http请求响应的第一层数据
 * email: mingming.liu@quvideo.com
 *
 */

public class BaseDataWrapper<T> implements Serializable{

    private T data;
    private boolean result;
    private int status_code;
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isResult() {
        return status_code == 0;
    }


    public int getStateCode() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    public String toString() {
        return "BaseDataWrapper{" +
                "data=" + data +
                ", result=" + result +
                ", errCode=" + status_code +
                ", errMessage='" + message + '\'' +
                '}';
    }
}