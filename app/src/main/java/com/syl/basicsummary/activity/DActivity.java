package com.syl.basicsummary.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.syl.basicsummary.R;
import com.syl.basicsummary.base.BaseActivity;

/**
 * author   j3767
 * date     2017/3/5 16:18
 * desc
 * 常用对话框的使用步骤:
 * 1.创建Dialog对象
 * 2.设置相应的参数,标题,提示信息,按钮
 * 3.显示dialog
 * 常用的对话框
 * 1 普通Dialog
 * 2 列表Dialog
 * 3 单选Dialog
 * 4 多选Dialog
 * 5 等待Dialog
 * 6 进度条Dialog
 * 7 编辑Dialog
 * 8 自定义Dialog
 */
public class DActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private String[] mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //已经继承了BaseActivity以及其相关布局,就要注释掉setContentView(R.layout.dialog_customize),否则,系统会优先使用本身的布局
//        setContentView(R.layout.dialog_customize);
        mTitles = new String[]{"普通Dialog", "列表Dialog", "单选Dialog", "多选Dialog",
                "等待Dialog", "进度条Dialog", "编辑Dialog", "自定义Dialog",};
        mListView.setAdapter(new ArrayAdapter<>(DActivity.this, R.layout.support_simple_spinner_dropdown_item, mTitles));
        mListView.setOnItemClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0://普通Dialog
                showNormalDialog();
                break;
            case 1://列表Dialog
                showListDialog();
                break;
            case 2://单选Dialog
                showSingleChoiceDialog();
                break;
            case 3://多选Dialog
                showMultiChoiceDialog();
                break;
            case 4://等待Dialog(不带进度条的对话框)
                showWaitingDialog();
                break;
            case 5://进度条Dialog
                showProgressDialog();
                break;
            case 6://编辑Dialog
                showEditDialog();
                break;
            case 7://自定义Dialog
                showCustomizeDialog();
                break;
            default:
                break;
        }
    }

    /**
     * 自定义对话框
     */
    private void showCustomizeDialog() {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(this);
        customizeDialog.setTitle("自定义对话框");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_customize, null);
        customizeDialog.setView(view);
        customizeDialog.setPositiveButton("ok...", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DActivity.this, "this is Customize Dialog..", Toast.LENGTH_SHORT).show();
            }
        });

        customizeDialog.show();
    }

    /**
     * 编辑对话框
     */
    private void showEditDialog() {
        final EditText et = new EditText(this);
        final AlertDialog.Builder inputDialog = new AlertDialog.Builder(this);
        inputDialog.setTitle("编辑对话框");
        inputDialog.setView(et);
        inputDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DActivity.this, et.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        inputDialog.show();
    }

    /**
     * 带进度条的对话框
     * 非常常用
     */
    private void showProgressDialog() {
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("加载中.");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
        new Thread() {
            @Override
            public void run() {
                int progress = 0;
                while (progress < MAX_PROGRESS) {
                    SystemClock.sleep(200);
                    progressDialog.setProgress(progress++);
                }
                progressDialog.cancel();
            }
        }.start();
    }

    /**
     * 等待对话框(不带进度条的对话框)
     */
    private void showWaitingDialog() {
        boolean indeterminate = true;
        ProgressDialog.show(this, "加载中..", "正在加载,请稍后...", indeterminate, true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(DActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 多选对话框
     */
    private void showMultiChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择你的爱好..");
        final String[] items = {"看书", "游戏", "娱乐", "听歌"};
        final boolean[] checkedItems = {false, true, true, false};
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(DActivity.this, items[which] + "被点击..." + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < checkedItems.length; i++) {
                    if (true == checkedItems[i]) {
                        //将选中的爱好通过Toast打印出来
                        Toast.makeText(DActivity.this, "你选中了---" + items[i], Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        builder.show();
    }

    /**
     * 单选Dialog
     */
    int checkedItem;

    private void showSingleChoiceDialog() {
        final String[] items = {"条目1", "条目2", "条目3", "条目4",};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //默认选择
        checkedItem = -1;//没有选择
        //第二个参数,默认选择.如果第二个参数是-1,表示任何参数都没有被选中
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedItem = which;
            }
        });
        builder.setPositiveButton("ok..", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (checkedItem != -1) {
                    Toast.makeText(DActivity.this, items[checkedItem] + "被选择了...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }

    /**
     * 列表Dialog,有问题,暂时不处理
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void showListDialog() {
        //TODO
        final String[] items = {"我是1", "我是2", "我是3", "我是4", "我是5",};
        AlertDialog.Builder builder = new AlertDialog.Builder(this) {
            @Override
            public AlertDialog create() {
                items[0] = "我是No.1";
                return super.create();
            }

            @Override
            public AlertDialog show() {
                items[1] = "我是No.2";
                return super.show();
            }
        };
        builder.setTitle("列表Dialog");
        builder.setMessage("this is a list Dialog");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DActivity.this, items[which] + "被点击了..", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    /**
     * 普通Dialog
     */
    private void showNormalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("普通Dialog");
        builder.setMessage("请选择------...");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DActivity.this, "确认按钮被点击了...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DActivity.this, "取消按钮被点击了...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
