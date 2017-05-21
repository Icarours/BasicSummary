package com.syl.demo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button    demo_btn_play1;
    private Button    demo_btn_play2;
    private ImageView demo_iv_content;
    private static final int SYS_CAMERAR_IMAGE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();

    }

    //http://blog.csdn.net/csqingchen/article/details/45502813
    private Uri mContentUri;
    private String mFilePath;
    private void initView() {
        demo_iv_content = (ImageView) findViewById(R.id.demo_iv_content);

        demo_btn_play1 = (Button) findViewById(R.id.demo_btn_play1);
        demo_btn_play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filePath = getExternalFilesDir("avatar").getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";

                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.Images.Media.DATA, filePath);

                contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                mContentUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mContentUri);
                intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, 0);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, 2);
            }
        });

        demo_btn_play2 = (Button) findViewById(R.id.demo_btn_play2);
        demo_btn_play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, System.currentTimeMillis() + ".jpg");
                contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                mContentUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mContentUri);
                intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, 0);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, 2);*/
                mFilePath = getExternalFilesDir("avatar").getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
                Uri contentUri = Uri.fromFile(new File(mFilePath));

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, 0);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, SYS_CAMERAR_IMAGE);
            }
        });

    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {

                String[] Columns = {
                        MediaStore.Images.Media._ID,
                        MediaStore.Images.Media.ORIENTATION,
                        MediaStore.Images.Media.DATA
                };

                Cursor cursor = getContentResolver().query(mContentUri, Columns, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                String id = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                int orientation = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION));
                String dataPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                demo_btn_play1.setText(id);
                demo_btn_play2.setText(orientation+"");
                demo_iv_content.setImageBitmap(BitmapFactory.decodeFile(dataPath));
            }
        }
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SYS_CAMERAR_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                System.out.println(mFilePath);
                System.out.println(data);
            }
        }
    }
}