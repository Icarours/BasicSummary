package com.syl.commonactivity.component;

import com.syl.commonactivity.activity.TestDagger2Activity;

/**
 * Created by Bright on 2017/4/29.
 *
 * @Describe
 * @Called
 */
//@Component(modules = TestDagger2ActivityModule.class)
public interface TestDagger2ActivityComponent {
    void inject(TestDagger2Activity activity);
}
