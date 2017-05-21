package com.syl.commonactivity.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.syl.commonactivity.MainActivity;
import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseActivity;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * author   Bright
 * date     2017/4/29 14:49
 * desc
 * 常见的隐式意图
 */
public class IntentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_home)
    Button mBtnHome;
    @BindView(R.id.btn_call)
    Button mBtnCall;
    @BindView(R.id.btn_sms)
    Button mBtnSms;
    @BindView(R.id.btn_browser)
    Button mBtnBrowser;
    @BindView(R.id.btn_action)
    Button mBtnAction;
    @BindView(R.id.btn_category)
    Button mBtnCategory;
    @BindView(R.id.tv_action)
    TextView mTvAction;
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.btn_net_img)
    Button mBtnNetImg;
    @BindView(R.id.btn_open_album)
    Button mBtnOpenAlbum;
    @BindView(R.id.btn_camera)
    Button mBtnCamera;
    @BindView(R.id.iv_result)
    ImageView mIvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        ButterKnife.bind(this);

        mBtnBrowser.setOnClickListener(this);
        mBtnCall.setOnClickListener(this);
        mBtnHome.setOnClickListener(this);
        mBtnSms.setOnClickListener(this);
        mBtnAction.setOnClickListener(this);
        mBtnCategory.setOnClickListener(this);
        mBtnNetImg.setOnClickListener(this);
        mBtnOpenAlbum.setOnClickListener(this);
        mBtnCamera.setOnClickListener(this);
        //暂时隐藏
        mBtnAction.setVisibility(View.GONE);
        mBtnCategory.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_home:
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                break;
            case R.id.btn_call:
                intent.setAction(Intent.ACTION_DIAL);//显示拨号面板
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
                break;
            case R.id.btn_sms:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:10086"));
                intent.putExtra("sms_body", "晚上去看电影吧");
                startActivity(intent);
                break;
            case R.id.btn_browser:
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                break;
            case R.id.btn_net_img:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://192.168.31.89:8080/img/mm2.jpg"));
                startActivity(intent);
                break;
            case R.id.btn_camera:
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
                break;
            case R.id.btn_open_album:
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                Intent wrapperIntent = Intent.createChooser(intent, "选择图片");
                startActivityForResult(wrapperIntent, 200);
                break;
            case R.id.btn_action:
                startMusicPlayer();
                break;
            case R.id.btn_category:
                intent.setClass(IntentActivity.this, MainActivity.class);
                Set<String> categories = intent.getCategories();
                Iterator<String> iterator = categories.iterator();
                StringBuffer sb = new StringBuffer();
                while (iterator.hasNext()) {
                    sb.append(iterator.next());
                }
                mTvCategory.setText(sb.toString());
                break;
            default:
                break;
        }
    }

    /**
     * 此方法不能调用系统的音乐播放器
     */
    private void startMusicPlayer() {
        String audioPath = "android.resource://" + getPackageName() + "/" + R.raw.a;
        Intent it = new Intent(Intent.ACTION_VIEW);
        it.setDataAndType(Uri.parse(audioPath), "audio/mp3");
        startActivity(it);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            ContentResolver cr = getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                mIvResult.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
