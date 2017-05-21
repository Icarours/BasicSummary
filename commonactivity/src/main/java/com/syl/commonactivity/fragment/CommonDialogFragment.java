package com.syl.commonactivity.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bright on 2017/4/23.
 *
 * @Describe 常见的对话框
 * @Called
 */
public class CommonDialogFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;
    @BindView(R.id.btn3)
    Button mBtn3;
    @BindView(R.id.btn4)
    Button mBtn4;
    @BindView(R.id.btn5)
    Button mBtn5;
    @BindView(R.id.tv_checked)
    TextView mTvChecked;
    @BindView(R.id.btn6)
    Button mBtn6;
    @BindView(R.id.btn7)
    Button mBtn7;
    private View mRootView;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getActivity(), R.layout.fragment_dialog, null);
        return mRootView;
    }

    @Override
    public void initView() {
        ButterKnife.bind(getActivity(), mRootView);
    }

    @Override
    public void initListener() {
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
        mBtn5.setOnClickListener(this);
        mBtn6.setOnClickListener(this);
        mBtn7.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    /**
     * ButterKnife.bind(this, rootView);
     * 这句代码一定要放到onCreateView()方法中
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                btn1Dialog();
                break;
            case R.id.btn2:
                btn2Dialog();
                break;
            case R.id.btn3:
                btn3Dialog();
                break;
            case R.id.btn4:
                btn4Dialog();
                break;
            case R.id.btn5:
                btn5Dialog();
                break;
            case R.id.btn6:
                btn6Dialog();
                break;
            case R.id.btn7:
                btn7Dialog();
                break;
            default:
                break;
        }
    }


    /**
     * 普通对话框
     */
    private void btn1Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("今天天气好吗?");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "今天天气很好...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "今天天气很不好,一直下雨...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    /**
     * 单选对话框
     */
    private void btn2Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] gender = {"male", "female"};
        builder.setTitle("你的性别是...");
        //第二个参数是默认值,如果指定的index超出gender的范围,就默认没有选中任何值
        builder.setSingleChoiceItems(gender, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "你选中的性别是.." + gender[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    /**
     * 多选对话框+普通对话框
     */
    private void btn3Dialog() {
        final List<String> list = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("你喜欢哪些运动...");
        final String[] items = {"篮球", "羽毛球", "乒乓球", "足球", "火球",};
        final boolean[] checkedItems = {true, false, true, false, true};
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;//修改选中状态
                for (int i = 0; i < checkedItems.length; i++) {
                    System.out.println("checkedItems[" + i + "]=" + checkedItems[i]);
                    if (checkedItems[i]) {//如果被选中
                        if (list.contains(items[i])) {//而且list集合中没有改元素
                            continue;
                        } else {
                            list.add(items[i]);
                        }
                    }
                }
                System.out.println(list.toString());
                mTvChecked.setText(list.toString());
                System.out.println("--------------------");
            }
        });
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "ok.......", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    /**
     * 进度对话框(带进度条)
     */
    private void btn4Dialog() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("loading..........");
        progressDialog.show();
        //阻塞线程的,耗时操作需要开启一个新的线程
        new Thread() {
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(2000);
                progressDialog.dismiss();
            }
        }.start();
        //放在外面太快,根本看不到现象更新的操作在新线程中跟主线程不相干
        //progressDialog.dismiss();
    }

    /**
     * 进度对话框(不带进度条)
     */
    private void btn5Dialog() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("正在加载....请售后");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.show();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    SystemClock.sleep(30);
                    progressDialog.setProgress(i);
                }
                progressDialog.dismiss();
            }
        }.start();
        //放在外面太快,根本看不到现象更新的操作在新线程中跟主线程不相干
        //progressDialog.dismiss();
    }

    /**
     * 时间对话框
     */
    private void btn6Dialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String str = year + "-" + (month + 1) + "-" + dayOfMonth;
                mTvChecked.setText(str);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     * 日起对话框
     */
    private void btn7Dialog() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str = hourOfDay + ":" + minute;
                mTvChecked.setText(str);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }
}
