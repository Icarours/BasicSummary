package com.syl.commonactivity.interface_;

import com.syl.commonactivity.bean.IPBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bright on 2017/5/9.
 *
 * @Describe
 * @Called
 */

public interface IPInterface {
    @GET("/service/getIpInfo.php")
    public Call<IPBean> getIp(@Query("ip") String ip);
    //http://ip.taobao.com/service/getIpInfo.php?ip=8.8.8.8
}
