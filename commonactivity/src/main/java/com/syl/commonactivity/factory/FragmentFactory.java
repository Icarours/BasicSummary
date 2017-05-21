package com.syl.commonactivity.factory;

import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.fragment.CallFragment;
import com.syl.commonactivity.fragment.CameraFragment;
import com.syl.commonactivity.fragment.CaptureVideoFragment;
import com.syl.commonactivity.fragment.CommonDialogFragment;
import com.syl.commonactivity.fragment.DataStorageFragmentPrivate;
import com.syl.commonactivity.fragment.DataStorageFragmentPublic;
import com.syl.commonactivity.fragment.DataStorageFragmentSp;
import com.syl.commonactivity.fragment.DisplayFragment;
import com.syl.commonactivity.fragment.HttpUrlConnImgFragment;
import com.syl.commonactivity.fragment.HttpUrlConnTxtFragment;
import com.syl.commonactivity.fragment.HttpUrlConnectionFragment;
import com.syl.commonactivity.fragment.ImgFragment;
import com.syl.commonactivity.fragment.MusicFragment;
import com.syl.commonactivity.fragment.NetMusicFragment;
import com.syl.commonactivity.fragment.ObjectAnimatorFragment;
import com.syl.commonactivity.fragment.OkHttpFragment;
import com.syl.commonactivity.fragment.RetrofitFragment;
import com.syl.commonactivity.fragment.SVFragment;
import com.syl.commonactivity.fragment.SVVideoFragment;
import com.syl.commonactivity.fragment.SeekBarFragment;
import com.syl.commonactivity.fragment.SensorFragment;
import com.syl.commonactivity.fragment.SmsFragment;
import com.syl.commonactivity.fragment.SoundPoolFragment;
import com.syl.commonactivity.fragment.SqliteFragment;
import com.syl.commonactivity.fragment.TuyaFragment;
import com.syl.commonactivity.fragment.VVFragment;
import com.syl.commonactivity.fragment.ViewAnimationFragment;
import com.syl.commonactivity.fragment.VitamioFragment;
import com.syl.commonactivity.fragment.VolleyFragment;
import com.syl.commonactivity.fragment.WeatherFragment;
import com.syl.commonactivity.fragment.WeatherFragment2;
import com.syl.commonactivity.fragment.XmlFragment;

import java.util.HashMap;

/**
 * Created by Bright on 2017/4/23.
 *
 * @Describe 对Fragment进行缓存控制
 * @Called
 */

public class FragmentFactory {
    private static HashMap<Integer, BaseFragment> mFragmentCache = new HashMap<>();

    public static BaseFragment createFragment(int position) {
        BaseFragment baseFragment = null;
        //如果集合里面有需要的Fragment,优先从Fragment里面取出
        if (mFragmentCache.containsKey(position)) {
            return mFragmentCache.get(position);
        }
        switch (position) {
            case 0:
                baseFragment = new CallFragment();
                break;
            case 1:
                baseFragment = new SmsFragment();
                break;
            case 2:
                baseFragment = new DataStorageFragmentPrivate();
                break;
            case 3:
                baseFragment = new DataStorageFragmentSp();
                break;
            case 4:
                baseFragment = new DataStorageFragmentPublic();
                break;
            case 5:
                baseFragment = new SeekBarFragment();
                break;
            case 6:
                baseFragment = new XmlFragment();
                break;
            case 7:
                baseFragment = new SqliteFragment();
                break;
            case 8:
                baseFragment = new CommonDialogFragment();
                break;
            case 9:
                baseFragment = new HttpUrlConnImgFragment();
                break;
            case 10:
                baseFragment = new HttpUrlConnTxtFragment();
                break;
            case 11:
                baseFragment = new WeatherFragment();
                break;
            case 12:
                baseFragment = new WeatherFragment2();
                break;
            case 13:
                baseFragment = new ImgFragment();
                break;
            case 14:
                baseFragment = new TuyaFragment();
                break;
            case 15:
                baseFragment = new MusicFragment();
                break;
            case 16:
                baseFragment = new NetMusicFragment();
                break;
            case 17:
                baseFragment = new SoundPoolFragment();
                break;
            case 18:
                baseFragment = new VVFragment();
                break;
            case 19:
                baseFragment = new SVFragment();
                break;
            case 20:
                baseFragment = new SVVideoFragment();
                break;
            case 21:
                baseFragment = new CameraFragment();
                break;
            case 22:
                baseFragment = new CaptureVideoFragment();
                break;
            case 23:
                baseFragment = new SensorFragment();
                break;
            case 24:
                baseFragment = new ViewAnimationFragment();
                break;
            case 25:
                baseFragment = new ObjectAnimatorFragment();
                break;
            case 26:
                baseFragment = new OkHttpFragment();
                break;
            case 27:
                baseFragment = new HttpUrlConnectionFragment();
                break;
            case 28:
                baseFragment = new DisplayFragment();
                break;
            case 29:
                baseFragment = new VitamioFragment();
                break;
            case 30:
                baseFragment = new RetrofitFragment();
                break;
            case 31:
                baseFragment = new VolleyFragment();
                break;
            default:
                break;
        }
        //将baseFragment保存到缓存集合中
        mFragmentCache.put(position, baseFragment);
        return baseFragment;
    }
}
