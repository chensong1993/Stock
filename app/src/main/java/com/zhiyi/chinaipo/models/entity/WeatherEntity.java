package com.zhiyi.chinaipo.models.entity;

/**
 * @author chensong
 * @date 2018/9/18 16:26
 */
public class WeatherEntity<T> {
    private String ERRORCODE;

    private T RESULT;

    public void setERRORCODE(String ERRORCODE) {
        this.ERRORCODE = ERRORCODE;
    }

    public String getERRORCODE() {
        return this.ERRORCODE;
    }

    public void setRESULT(T RESULT) {
        this.RESULT = RESULT;
    }

    public T getRESULT() {
        return this.RESULT;
    }



}
