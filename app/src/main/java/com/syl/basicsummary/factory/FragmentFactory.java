package com.syl.basicsummary.factory;

import android.support.v4.app.Fragment;

import com.syl.basicsummary.fragment.BookFragment;
import com.syl.basicsummary.fragment.GameFragment;
import com.syl.basicsummary.fragment.HomeFragment;
import com.syl.basicsummary.fragment.MovieFragment;
import com.syl.basicsummary.fragment.MusicFragment;

/**
 * Created by j3767 on 2017/2/8.
 *
 * @Describe
 * @Called
 */

public class FragmentFactory {
    /*    <string-array name="main_titles">
        <item>首页</item>
        <item>图书</item>
        <item>游戏</item>
        <item>电影</item>
        <item>音乐</item>
        </string-array>    */
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_BOOK = 1;
    public static final int FRAGMENT_GAME = 2;
    public static final int FRAGMENT_MOVIE = 3;
    public static final int FRAGMENT_MUSIC = 4;

    public static Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case FRAGMENT_HOME:
                fragment = new HomeFragment();
                break;
            case FRAGMENT_BOOK:
                fragment = new BookFragment();
                break;
            case FRAGMENT_GAME:
                fragment = new GameFragment();
                break;
            case FRAGMENT_MOVIE:
                fragment = new MovieFragment();
                break;
            case FRAGMENT_MUSIC:
                fragment = new MusicFragment();
                break;
            default:
                break;
        }
        return fragment;
    }
}
