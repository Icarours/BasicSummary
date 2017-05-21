package com.syl.basicsummary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.basicsummary.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author   j3767
 * date     2017/3/4 14:05
 * desc
 * xml文件的存储和解析
 */
public class DataActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.rb_male)
    RadioButton mRbMale;
    @BindView(R.id.rb_female)
    RadioButton mRbFemale;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    @BindView(R.id.btn_query)
    Button mBtnQuery;
    @BindView(R.id.tv_query_result)
    TextView mTvQueryResult;
    @BindView(R.id.rg)
    RadioGroup mRg;
    @BindView(R.id.activity_data)
    LinearLayout mActivityData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);
    }

    /**
     * 保存文件
     * 生成xml文件
     *
     * @param view
     */
    @OnClick(R.id.btn_save)
    public void btn_save(View view) {
        String etName = mEtName.getText().toString();
        String etPwd = mEtPwd.getText().toString();
        if (TextUtils.isEmpty(etName) || TextUtils.isEmpty(etPwd)) {
            Toast.makeText(this, "姓名或者密码不能为空---.", Toast.LENGTH_SHORT).show();
        }
        int radioButtonId = mRg.getCheckedRadioButtonId();
        String gender;
        switch (radioButtonId) {
            case R.id.rb_male:
                gender = "male";
                break;
            case R.id.rb_female:
                gender = "female";
                break;
            default:
                gender = "male";
                break;
        }
        XmlSerializer xmlSerializer = Xml.newSerializer();
        try {
            File file = new File(getFilesDir(), etName + ".xml");
            OutputStream outputStream = new FileOutputStream(file);
            xmlSerializer.setOutput(outputStream, "utf-8");
            xmlSerializer.startDocument("utf-8", true);
            xmlSerializer.startTag(null, "student");

            xmlSerializer.startTag(null, "name");
            xmlSerializer.text(etName);
            xmlSerializer.endTag(null, "name");

            xmlSerializer.startTag(null, "pwd");
            xmlSerializer.text(etPwd);
            xmlSerializer.endTag(null, "pwd");

            xmlSerializer.startTag(null, "gender");
            xmlSerializer.text(gender);
            xmlSerializer.endTag(null, "gender");

            xmlSerializer.endTag(null, "student");
            xmlSerializer.endDocument();

            outputStream.close();
            Toast.makeText(this, "保存成功--", Toast.LENGTH_SHORT).show();
            //同时检查不同的Exception,可以提高处理效率
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "没有发现文件..", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this, "文件保存失败...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 查询,解析xml文件
     *
     * @param view
     */
    @OnClick(R.id.btn_query)
    public void btn_query(View view) {
        //1.获得需要查询的学生的名字
        String name = mEtName.getText().toString();
        try {
            //2.需要查询的文件
            File file = new File(getFilesDir(), name + ".xml");

            //3.解析文件内容
            XmlPullParser pullParser = Xml.newPullParser();
            InputStream inputStream = new FileInputStream(file);
            pullParser.setInput(inputStream, "utf-8");

            int eventType = pullParser.getEventType();
            String result = "";
            while (eventType != pullParser.END_DOCUMENT) {
                if (eventType == pullParser.START_TAG) {
                    if ("name".equals(pullParser.getName())) {
                        //获得name的值
                        String sname = pullParser.nextText();
                        result += sname + ",";
                    } else if ("pwd".equals(pullParser.getName())) {
                        String spwd = pullParser.nextText();
                        result += spwd + ",";
                    } else if ("gender".equals(pullParser.getName())) {
                        String sgender = pullParser.nextText();
                        result += sgender;
                    }
                }
                eventType = pullParser.next();
            }
            mTvQueryResult.setText(result);
            //多分支Exception相对于大范围的Exception可以稍稍提高效率
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
