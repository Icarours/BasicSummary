package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.TextView;

import com.syl.basicsummary.R;
import com.syl.basicsummary.utils.UIUtils;

public class BroadcastReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        TextView textView = new TextView(UIUtils.getContext());
        textView.setText(BroadcastReceiverActivity.class.getSimpleName());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
    }

}
