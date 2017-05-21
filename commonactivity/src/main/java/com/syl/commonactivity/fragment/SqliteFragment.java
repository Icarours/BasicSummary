package com.syl.commonactivity.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;
import com.syl.commonactivity.bean.Student;
import com.syl.commonactivity.dao.StudentDao;

import java.util.List;

/**
 * Created by Bright on 2017/4/23.
 *
 * @Describe 使用sqlite保存数据
 * 1.sqlite保存数据
 * 2.使用帧动画设置图片背景
 * @Called
 */

public class SqliteFragment extends BaseFragment implements View.OnClickListener {

    private View mRootView;
    private EditText mEtUser;
    private EditText mEtPassword;
    private ListView mListView;
    private StudentDao mDao;
    private RadioGroup mRgGender;
    private StudentAdapter mAdapter;
    private List<Student> mList;
    private ImageView mIvRocket;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getActivity(), R.layout.fragment_sql, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mEtUser = (EditText) mRootView.findViewById(R.id.et_user);
        mEtPassword = (EditText) mRootView.findViewById(R.id.et_password);
        mListView = (ListView) mRootView.findViewById(R.id.list);

        mIvRocket = (ImageView) mRootView.findViewById(R.id.iv_rocket);
        ivBackgroundAnimation();//给背景设置帧动画

        mDao = new StudentDao(getActivity());

        mRgGender = (RadioGroup) mRootView.findViewById(R.id.rg_gender);
    }

    /**
     * 使用帧动画设置图片背景
     * 帧动画在布局文件中设置
     */
    private void ivBackgroundAnimation() {
        mIvRocket.setBackgroundResource(R.drawable.rocket_anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) mIvRocket.getBackground();
        animationDrawable.start();
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
                queryAll();
                break;
            default:
        }
    }

    /**
     * 查询所有数据
     */
    private void queryAll() {
        refreshData();
        Toast.makeText(getActivity(), "查询完毕...", Toast.LENGTH_SHORT).show();
    }

    /**
     * 刷新修改后的视图,数据集发生改变,及时UI刷新,获得新的数据集
     */
    public void refreshData() {
        //给数据集赋值,selectAll()的返回值就是List所以不用再次new ArrayList
        mList = mDao.selectAll();
        if (mAdapter == null) {
            mAdapter = new StudentAdapter();
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void saveData() {
        String user = mEtUser.getText().toString();
        String password = mEtPassword.getText().toString();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "用户名和密码都不能为空..", Toast.LENGTH_SHORT).show();
            return;
        }
        //错用API,应该用mRgGender.getCheckedRadioButtonId(),却用了mRgGender.getId(),导致没有拿到mRgGender的选中状态
        //获得选中RadioButton的id,
        int id = mRgGender.getCheckedRadioButtonId();
        String gender = getActivity().getResources().getString(R.string.male);
        switch (id) {
            case R.id.rb_male:
                gender = getActivity().getResources().getString(R.string.male);
                break;
            case R.id.rb_female:
                gender = getActivity().getResources().getString(R.string.female);
                break;
            default:
                break;
        }
        System.out.println("user==" + user + "gender==" + gender);
        mDao.insert(user, gender);
        Toast.makeText(getActivity(), "信息保存成功..", Toast.LENGTH_SHORT).show();
    }

    /**
     * author   Bright
     * date     2017/4/23 20:38
     * desc listView中的Adapter
     * 1.提供视图和数据
     * 2.视图和数据的绑定
     * 3.刷新修改后的adapter
     */
    class StudentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(getActivity(), R.layout.list_item_student, null);
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                holder.ivGender = (ImageView) convertView.findViewById(R.id.iv_gender);
                holder.ivDelete = (ImageView) convertView.findViewById(R.id.iv_delete);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvName.setText(mList.get(position).getName());
            String gender = mList.get(position).getGender();
            if ("男".equals(gender)) {
                holder.ivGender.setImageResource(R.mipmap.nan);
            } else if ("女".equals(gender)) {
                holder.ivGender.setImageResource(R.mipmap.nv);
            }
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = mList.get(position).getName();
                    //数据集发生改变
                    mDao.delete(name);
                    Toast.makeText(getActivity(), "删除..." + name, Toast.LENGTH_SHORT).show();
                    //刷新数据
                    refreshData();
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tvName;
            ImageView ivGender;
            ImageView ivDelete;
        }
    }
}
