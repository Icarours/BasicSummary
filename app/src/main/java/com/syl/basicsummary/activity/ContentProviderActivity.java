package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.TextView;

import com.syl.basicsummary.R;

public class ContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        TextView textView = new TextView(this);
        textView.setText(this.getClass().getSimpleName());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
    }
}
