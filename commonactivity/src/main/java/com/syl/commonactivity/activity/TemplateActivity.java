package com.syl.commonactivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.syl.commonactivity.R;
import com.syl.commonactivity.adapter.SmsAdapter;
import com.syl.commonactivity.base.BaseActivity;
import com.syl.commonactivity.data.SmsContent;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * author   Bright
 * date     2017/4/25 16:25
 * desc
 * 短信模板
 */
public class TemplateActivity extends BaseActivity {

    @BindView(R.id.lv_template)
    ListView mLvTemplate;
    private SmsAdapter mSmsAdapter;
    private String[] mContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        ButterKnife.bind(this);

        mContents = SmsContent.contents;
        mSmsAdapter = new SmsAdapter(TemplateActivity.this, mContents);
        mLvTemplate.setAdapter(mSmsAdapter);
        mLvTemplate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String content = mContents[position];
                Intent data = new Intent();
                data.putExtra("content", content);
                setResult(12, data);//将intent中的数据带过去,结果码,结果数据
                finish();//结束当前的Activity
            }
        });
    }
}
