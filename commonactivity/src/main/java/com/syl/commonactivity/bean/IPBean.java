package com.syl.commonactivity.bean;

/**
 * Created by Bright on 2017/5/9.
 *
 * @Describe
 * ip信息
 * @Called
 */

public class IPBean {

    /**
     * code : 0
     * data : {"ip":"210.75.225.254","country":"中国","area":"华北","region":"北京市","city":"北京市","county":"","isp":"电信","country_id":"86","area_id":"100000","region_id":"110000","city_id":"110000","county_id":"-1","isp_id":"100017"}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
