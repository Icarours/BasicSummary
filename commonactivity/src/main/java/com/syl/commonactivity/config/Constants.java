package com.syl.commonactivity.config;

import com.syl.commonactivity.activity.BanzhengActivity;
import com.syl.commonactivity.activity.DetailActivity;
import com.syl.commonactivity.activity.IjkPlayerActivity;
import com.syl.commonactivity.activity.ImageActivity;
import com.syl.commonactivity.activity.IntentActivity;
import com.syl.commonactivity.activity.MeiTuanActivity;
import com.syl.commonactivity.activity.MyReceiverActivity;
import com.syl.commonactivity.activity.NumberActivity;
import com.syl.commonactivity.activity.RefreshActivity;
import com.syl.commonactivity.activity.ServiceActivity;
import com.syl.commonactivity.activity.SmsHelperActivity;
import com.syl.commonactivity.activity.TemplateActivity;
import com.syl.commonactivity.activity.TestDagger2Activity;
import com.syl.commonactivity.activity.VitamioActivity;
import com.syl.commonactivity.activity.WebViewActivity;
import com.syl.commonactivity.activity.ZXingActivity;

/**
 * Created by Bright on 2017/4/19.
 *
 * @Describe 常量数据
 * @Called
 */

public class Constants {
    //http地址一定要写全,不能鞥省略前面的http://,pc浏览器会在前面默认添加,但是移动手机端一定要自己手动添加
    public static final String BASEURL = "http://192.168.31.133:8080/";
    //查询天气http://wthrcdn.etouch.cn/weather_mini?city=深圳
    public static final String WEATHERURL = "http://wthrcdn.etouch.cn/weather_mini?city=";
    //视频播放地址
    public static final String VIDEOURL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    //直播地址
    public static final String VIDEOURL1 = "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8";

    //ip地址查询
    public static final String IPURL = "http://ip.taobao.com/";
    public static String[] titles = {
            "打电话",//0
            "发短信",//1
            "数据存储(存在私有文件)",//2
            "数据存储(存在sp)",//3
            "数据存储(存在公有文件)",//4
            "SeekBar",//5
            "数据存储xml",//6
            "数据存储sqlite",//7
            "常用对话框",//8
            "HttpUrlConnection加载图片",//9
            "HttpUrlConnection查看网络文本",//10
            "HttpUrlConnection天气预报",//11
            "HttpUrlConnection天气预报2",//12
            "图片处理",//13
            "涂鸦",//14
            "播放本地音乐",//15
            "播放网络音乐",//16
            "声音池",//17
            "VideoView视频播放",//18
            "SurfaceView",//19
            "SurfaceView视频播放",//20
            "调用照相机",//21
            "录制视频",//22
            "传感器",//23
            "补间动画",//24
            "属性动画",//25
            "OkHttp网络请求",//26
            "HttpUrlConnection举例",//27
            "本机分辨率",//28
            "Vitamio视频播放器",//29
            "retrofit网络请求",//30
            "Volley网络请求",//31
    };
    public static Class<?>[] activities = {
            DetailActivity.class,//0详情页面,展示Fragment
            IntentActivity.class,//1常见的隐式意图
            SmsHelperActivity.class,//2短信助手
            NumberActivity.class,//联系人界面
            TemplateActivity.class,//模板界面
            MyReceiverActivity.class,//广播
            ServiceActivity.class,//服务
            BanzhengActivity.class,//办证,本地服务
            MeiTuanActivity.class,//8进程间通信,远程服务,美团支付
            ZXingActivity.class,//9二维码demo
            TestDagger2Activity.class,//10Dagger注入
            RefreshActivity.class,//11刷新ListView
            VitamioActivity.class,//12Vitamio
            WebViewActivity.class,//13.WebView播放视频
            IjkPlayerActivity.class,//14.IjkPlayer播放视频
            ImageActivity.class,//15.测试自定义圆形图片
    };

    /**
     * author   Bright
     * date     2017/4/27 20:55
     * desc
     * 图片地址
     */
    public class IMGURL {
        public static final String IMGURL1 = Constants.BASEURL + "img/mm1.jpg";
        public static final String IMGURL2 = "https://onedrive.live.com/?cid=6FEA6981519B365E&id=6FEA6981519B365E%213771&parId=6FEA6981519B365E%21322&o=OneUp";
    }

    /**
     * author   Bright
     * date     2017/4/27 20:55
     * desc
     * 文本地址
     */
    public class TXTURL {
        public static final String TXTURL1 = Constants.BASEURL + "text/ymh.txt";
    }

    /**
     * author   Bright
     * date     2017/4/27 20:56
     * desc
     * 音乐地址/音频地址
     */
    public class MUSIC {
        public static final String MUSIC1 = Constants.BASEURL + "music/qny.mp3";
    }
}
