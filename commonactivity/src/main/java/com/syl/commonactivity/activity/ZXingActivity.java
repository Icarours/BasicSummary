package com.syl.commonactivity.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseActivity;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author   Bright
 * date     2017/4/29 15:34
 * desc
 * 二维码,使用精简后的二维码库
 *
 * android5.0后,需要添加动态权限
 */
public class ZXingActivity extends BaseActivity implements View.OnClickListener {

    private static final int ZXINGACTIVITY_SCANCODE = 1;
    private static final int MY_PERMISSION_REQUEST_CAMERA = 2;
    @BindView(R.id.btn_scan)
    Button mBtnScan;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.btn_make)
    Button mBtnMake;
    @BindView(R.id.et_input)
    EditText mEtInput;
    @BindView(R.id.cb_logo)
    CheckBox mCbLogo;
    @BindView(R.id.iv_code)
    ImageView mIvCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        ButterKnife.bind(this);

        mBtnScan.setOnClickListener(this);
        mBtnMake.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_make:
                makeCode();
                break;
            case R.id.btn_scan:
                //判断是否添加了动态权限
                if (ContextCompat.checkSelfPermission(ZXingActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSION_REQUEST_CAMERA);
                }
                scanCode();
                break;
            default:
                break;
        }
    }

    /**
     * 扫描二维码
     */
    private void scanCode() {
        Intent intent = new Intent(ZXingActivity.this, CaptureActivity.class);
        startActivityForResult(intent, ZXINGACTIVITY_SCANCODE);
    }

    /**
     * 生成二维码
     */
    private void makeCode() {
        String input = mEtInput.getText().toString();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "生成二维码的内容不能为空..", Toast.LENGTH_SHORT).show();
            return;
        } else {//如果mCbLogo被选中,生成带logo的二维码
            Bitmap bitmap = EncodingUtils.createQRCode(input, 500, 500, mCbLogo.isChecked() ?
                    BitmapFactory.decodeResource(getResources(), R.mipmap.m1) :
                    EncodingUtils.createQRCode(input, 500, 500, null));
            mIvCode.setImageBitmap(bitmap);
        }
    }

    /**
     * 处理跳转返回带回来的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ZXINGACTIVITY_SCANCODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            mTvResult.setText(result);
        }
    }

    /**
     * 处理获取动态权限后的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSION_REQUEST_CAMERA) {//如果请求码正确
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//如果获取动态权限
                scanCode();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
//            return;//已经到方法末尾了,不需要再return
        }
    }
}
