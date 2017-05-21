package com.syl.commonactivity.bean;

/**
 * Created by Bright on 2017/4/24.
 *
 * @Describe
 * @Called
 */

public class Weather {

    /**
     * fengxiang : 无持续风向
     * fengli : 微风级
     * high : 高温 24℃
     * type : 小雨
     * low : 低温 21℃
     * date : 24日星期一
     */

    private String fengxiang;
    private String fengli;
    private String high;
    private String type;
    private String low;
    private String date;

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "fengxiang='" + fengxiang + '\'' +
                ", fengli='" + fengli + '\'' +
                ", high='" + high + '\'' +
                ", type='" + type + '\'' +
                ", low='" + low + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
