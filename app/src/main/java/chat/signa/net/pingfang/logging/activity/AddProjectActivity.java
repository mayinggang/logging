package chat.signa.net.pingfang.logging.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chat.signa.net.pingfang.logging.R;
import chat.signa.net.pingfang.logging.net.HttpBaseCallback;
import chat.signa.net.pingfang.logging.net.OkHttpCommonUtil;
import chat.signa.net.pingfang.logging.util.DateTimePickDialogsUtil;
import chat.signa.net.pingfang.logging.util.InfoUtil;

/**
 * 添加项目
 * Created by Administrator on 2016/1/11.
 */
public class AddProjectActivity extends Activity{
    private EditText pro_name,pro_start_time,pro_stop_time,pro_describe,pro_remark;
    private Spinner pro_state;
    String times="";
    private String logName="";
    private List<String> list = new ArrayList<String>();
    private String name,startime,stoptime,status,message,remark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject);
        times= InfoUtil.timeInfo();//获得当前时间
        initView();
        showSpinner1();
    }

    public void initView(){
        pro_name=(EditText)findViewById(R.id.pro_name);
        pro_start_time=(EditText)findViewById(R.id.pro_start_time);
        pro_stop_time=(EditText)findViewById(R.id.pro_stop_time);
        pro_describe=(EditText)findViewById(R.id.pro_describe);
        pro_remark=(EditText)findViewById(R.id.pro_remark);
        pro_state=(Spinner)findViewById(R.id.pro_state);
        pro_start_time.setText(times);
        pro_stop_time.setText(times);
        pro_start_time.setKeyListener(null);
        pro_stop_time.setKeyListener(null);
        pro_name.setText(getString(R.string.yue));
        pro_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prostart = pro_start_time.getText().toString().trim();
                DateTimePickDialogsUtil dateTimePicKDialog = new DateTimePickDialogsUtil(
                        AddProjectActivity.this, prostart);
                dateTimePicKDialog.dateTimePicKDialog(pro_start_time);
            }
        });
        pro_stop_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String proStop = pro_stop_time.getText().toString().trim();
                DateTimePickDialogsUtil dateTimePicKDialog = new DateTimePickDialogsUtil(
                        AddProjectActivity.this, proStop);
                dateTimePicKDialog.dateTimePicKDialog(pro_stop_time);

            }
        });


    }
    //数据提交
    public void update(View view){
        //startime,stoptime,status,message,remark;
        name=pro_name.getText().toString().trim();
        startime=pro_start_time.getText().toString().trim();
        stoptime=pro_stop_time.getText().toString().trim();
        message=pro_describe.getText().toString().trim();
        remark=pro_remark.getText().toString().trim();
        Toast.makeText(getApplicationContext(),"", Toast.LENGTH_SHORT).show();
    }

    public void inittype(){


    }

    public void initUpdate(){
        OkHttpCommonUtil.Param[] params=new OkHttpCommonUtil.Param[]{
                new OkHttpCommonUtil.Param("",name),
                new OkHttpCommonUtil.Param("",startime),
                new OkHttpCommonUtil.Param("",stoptime),
                new OkHttpCommonUtil.Param("",message),
                new OkHttpCommonUtil.Param("",remark)
        };
        OkHttpCommonUtil okhttp=OkHttpCommonUtil.newInstance(getApplicationContext());
        okhttp.postRequest("", params, new HttpBaseCallback() {
            @Override
            public void onFailure(Request request, IOException e) {//数据上传失败
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {//数据上传成功
                String result = response.body().string();
                try {
                    JSONObject json = new JSONObject(result);
                    int status = json.getInt("status");
                    if (status == 1) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), getString(R.string.net_ok), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), getString(R.string.net_ok_error), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void cancel(View view){
        navigateUp();
    }


    public void logUpdate(){
        OkHttpCommonUtil.Param[] params=new OkHttpCommonUtil.Param[]{
          new OkHttpCommonUtil.Param("","")
        };
        OkHttpCommonUtil okhttp=OkHttpCommonUtil.newInstance(getApplicationContext());
        okhttp.postRequest("",params,new HttpBaseCallback(){
            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                super.onResponse(response);
            }
        });
    }
    //选择项目名称
    public void showSpinner1() {
        // 第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list.add(getString(R.string.processed));
        list.add(getString(R.string.off_the_stocks));
        list.add(getString(R.string.pause));

        // 第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        // 第三步：为适配器设置下拉列表下拉时的菜单样式。 simple_spinner_item
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // 第四步：将适配器添加到下拉列表上
        pro_state.setAdapter(adapter);
        // 第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        pro_state
                .setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int position, long arg3) {
						/* 将所选mySpinner 的值带入myTextView 中 */
                        status = adapter.getItem(position);
                        //  Toast.makeText(getApplicationContext(), "您选择的是：" + adapter.getItem(position), Toast.LENGTH_SHORT).show();
						/* 将mySpinner 显示 */
                        arg0.setVisibility(View.VISIBLE);
                    }

                    public void onNothingSelected(AdapterView<?> arg0) {
                        // Toast.makeText(getApplicationContext(), "您选择的是：" + "NONE", Toast.LENGTH_SHORT).show();
                    }
                });
		/* 下拉菜单弹出的内容选项触屏事件处理 */
        pro_state.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
				/* 将mySpinner 隐藏，不隐藏也可以，看自己爱好  */
        // v.setVisibility(View.INVISIBLE);

        return false;
    }
});
		/* 下拉菜单弹出的内容选项焦点改变事件处理   */
        pro_state
        .setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
                    public void onFocusChange(View v, boolean hasFocus) {
                        v.setVisibility(View.VISIBLE);
                    }
                });
    }

    //取消
    public void navigateUp() {
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if(NavUtils.shouldUpRecreateTask(this, upIntent)) {
            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(upIntent)
                    .startActivities();

        } else {
            onBackPressed();
        }
    }


}
