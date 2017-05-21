package com.syl.commonactivity.moduel;

import com.syl.commonactivity.bean.Student;

/**
 * Created by Bright on 2017/4/29.
 *
 * @Describe
 * @Called
 */
//@Module
public class TestDagger2ActivityModule {
   // @Provides
    Student provideStudent() {
        return new Student(1, "张三", "男");
    }
}
