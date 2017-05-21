package com.syl.commonactivity.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

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

/**
 * Created by Bright on 2017/4/23.
 *
 * @Describe
 * 使用xmlSerializer存储数据xml
 * 使用XmlPullParser解析xml文件
 * @Called
 */
public class XmlFragment extends BaseFragment implements View.OnClickListener {

    private View mRootView;
    private EditText mEtUser;
    private EditText mEtPassword;
    private RadioGroup mRgGender;
    private TextView mTvResult;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getActivity(), R.layout.fragment_xml, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mEtUser = (EditText) mRootView.findViewById(R.id.et_user);
        mEtPassword = (EditText) mRootView.findViewById(R.id.et_password);
        mRgGender = (RadioGroup) mRootView.findViewById(R.id.rg_gender);
        mTvResult = (TextView) mRootView.findViewById(R.id.tv_result);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_save).setOnClickListener(this);
        mRootView.findViewById(R.id.btn_query).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                saveData();
                break;
            case R.id.btn_query:
                queryData();
                break;
            default:
                break;
        }
    }

    /**
     * 查询信息
     * 使用XmlPullParser解析xml文件
     */
    private void queryData() {
        String user = mEtUser.getText().toString();
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(getActivity(), "用户名不能为空...", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(getActivity().getFilesDir(), user + ".xml");
        if (file.exists() && file.length() > 0) {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            try {
                InputStream inputStream = new FileInputStream(file);
                xmlPullParser.setInput(inputStream, "UTF-8");
                int eventType = xmlPullParser.getEventType();
                String result = null;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if ("name".equals(xmlPullParser.getName())) {
                        result = xmlPullParser.nextText()+",";
                    } else if ("password".equals(xmlPullParser.getName())) {
                        result += xmlPullParser.nextText()+",";
                    } else if ("gender".equals(xmlPullParser.getName())) {
                        result += xmlPullParser.nextText()+",";
                    }
                    eventType = xmlPullParser.next();
                }
                mTvResult.setText(result);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "你查询的信息不存在..", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 保存信息
     * 使用XmlSerializer保存信息
     */
    private void saveData() {
        String user = mEtUser.getText().toString();
        String password = mEtPassword.getText().toString();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "用户名和密码都不能为空...", Toast.LENGTH_SHORT).show();
            return;
        }
        int checkedRadioButtonId = mRgGender.getCheckedRadioButtonId();
        String gender = getActivity().getResources().getString(R.string.male);
        switch (checkedRadioButtonId) {
            case R.id.rb_male:
                gender = getActivity().getResources().getString(R.string.male);
                break;
            case R.id.rb_female:
                gender = getActivity().getResources().getString(R.string.female);
                break;
            default:
                break;
        }
        XmlSerializer xmlSerializer = Xml.newSerializer();
        //文件的保存地址  /data/data/包名/files/qiqi.xml
        File file = new File(getActivity().getFilesDir(), user + ".xml");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            xmlSerializer.setOutput(outputStream, "UTF-8");
            xmlSerializer.startDocument("UTF-8", true);

            xmlSerializer.startTag(null, "student");

            xmlSerializer.startTag(null, "name");
            xmlSerializer.text(user);
            xmlSerializer.endTag(null, "name");

            xmlSerializer.startTag(null, "password");
            xmlSerializer.text(password);
            xmlSerializer.endTag(null, "password");

            xmlSerializer.startTag(null, "gender");
            xmlSerializer.text(gender);
            xmlSerializer.endTag(null, "gender");

            xmlSerializer.endTag(null, "student");

            xmlSerializer.endDocument();

            outputStream.close();
            Toast.makeText(getActivity(), "文件保存成功..", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "没有发现文件..", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "文件写入失败...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
