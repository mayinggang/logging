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
import chat.signa.net.pingfang.logging.util.DateTimePickDialogUtil;
import chat.signa.net.pingfang.logging.util.InfoUtil;

/**
 * 添加工作日志
 * Created by Administrator on 2016/1/8.
 */
public class LogAddActivity extends Activity {
    public static final String TAG = LogAddActivity.class.getSimpleName();
    private Spinner log_name,log_type;//项目名称，工作类型
    private EditText log_starttime,log_stoptime;//开始时间，结束时间
    private EditText log_describe,log_remark;//工作描述，工作备注
    private List<String> list = new ArrayList<String>();
    private List<String> lists = new ArrayList<String>();
    private String logName,logType,logStartTime,logStopTime,logDescribe,logRemark;
    String times="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logadd);


        times= InfoUtil.timeInfo();

        initView();
        showSpinner1();
        showSpinner2();
    }
    //初始化控件
    public void initView(){
        log_name=(Spinner)findViewById(R.id.log_name);
        log_type=(Spinner)findViewById(R.id.log_type);
        log_starttime=(EditText)findViewById(R.id.log_starttime);
        log_stoptime=(EditText)findViewById(R.id.log_stoptime);
        log_starttime.setText(times);
        log_stoptime.setText(times);
        log_starttime.setKeyListener(null);
        log_stoptime.setKeyListener(null);
        /**
         *  long day = InfoUtil.calculateTime(dateTime,InfoUtil.timeInfo());
         System.out.print("day:"+day);
         if (day >= 1) {
         inputDate.setText(initDateTime);
         Toast.makeText(activity,"选择日期不能大于当前日期",Toast.LENGTH_SHORT).show();
         } else {
         inputDate.setText(dateTime);
         }

         System.out.println("..........." + day + "天");
         */
        log_starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strTime = log_starttime.getText().toString().trim();
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        LogAddActivity.this, strTime);
                dateTimePicKDialog.dateTimePicKDialog(log_starttime);


            }
        });
        log_stoptime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strTime = log_stoptime.getText().toString().trim();
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        LogAddActivity.this, strTime);
                dateTimePicKDialog.dateTimePicKDialog(log_stoptime);
            }
        });


        log_describe=(EditText)findViewById(R.id.log_describe);
        log_remark=(EditText)findViewById(R.id.log_remark);
    }
    //选择工作类型
    public void showSpinner2() {
        // 第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        lists.add("北京人");
        lists.add("上海人");
        lists.add("深圳人");
        lists.add("南京人");
        lists.add("重庆人");
        // 第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lists);
        // 第三步：为适配器设置下拉列表下拉时的菜单样式。 simple_spinner_item
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // 第四步：将适配器添加到下拉列表上
        log_type.setAdapter(adapter);
        // 第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        log_type
                .setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int position, long arg3) {
						/* 将所选mySpinner 的值带入myTextView 中 */
                        logType = adapter.getItem(position);
                        Toast.makeText(getApplicationContext(), "您选择的是：" + adapter.getItem(position), Toast.LENGTH_SHORT).show();

						/* 将mySpinner 显示 */
                        arg0.setVisibility(View.VISIBLE);

                    }

                    public void onNothingSelected(AdapterView<?> arg0) {
                        Toast.makeText(getApplicationContext(), "您选择的是：" + "NONE", Toast.LENGTH_SHORT).show();
                    }
                });
		/* 下拉菜单弹出的内容选项触屏事件处理 */
        log_type.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
				/* 将mySpinner 隐藏，不隐藏也可以，看自己爱好 */
                // v.setVisibility(View.INVISIBLE);

                return false;
            }
        });
		/* 下拉菜单弹出的内容选项焦点改变事件处理 */
        log_type
                .setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
                    public void onFocusChange(View v, boolean hasFocus) {
                        v.setVisibility(View.VISIBLE);

                    }
                });
    }
    //选择项目名称
    public void showSpinner1() {
        // 第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list.add("北京");
        list.add("上海");
        list.add("深圳");
        list.add("南京");
        list.add("重庆");
        // 第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        // 第三步：为适配器设置下拉列表下拉时的菜单样式。 simple_spinner_item
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // 第四步：将适配器添加到下拉列表上
        log_name.setAdapter(adapter);
        // 第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        log_name
                .setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int position, long arg3) {
						/* 将所选mySpinner 的值带入myTextView 中 */
                        logName=adapter.getItem(position);
                        Toast.makeText(getApplicationContext(),"您选择的是：" + adapter.getItem(position),Toast.LENGTH_SHORT).show();
						/* 将mySpinner 显示 */
                        arg0.setVisibility(View.VISIBLE);

                    }

                    public void onNothingSelected(AdapterView<?> arg0) {
                        Toast.makeText(getApplicationContext(),"您选择的是：" + "NONE",Toast.LENGTH_SHORT).show();
                    }
                });
		/* 下拉菜单弹出的内容选项触屏事件处理 */
        log_name.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
				/* 将mySpinner 隐藏，不隐藏也可以，看自己爱好 */
                // v.setVisibility(View.INVISIBLE);
                return false;
            }
        });
		/* 下拉菜单弹出的内容选项焦点改变事件处理 */
        log_name
                .setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
                    public void onFocusChange(View v, boolean hasFocus) {
                        v.setVisibility(View.VISIBLE);

                    }
                });
    }





    public void update(View view){

        logPost();
    }

    public void cancel(View view){
        navigateUp();
    }



    //数据提交  logName,logType,logStartTime,logStopTime,logDescribe,logRemark;
    public void logPost(){
        logStartTime=log_starttime.getText().toString().trim();
        logStopTime=log_stoptime.getText().toString().trim();
        logDescribe=log_describe.getText().toString().trim();
        logRemark=log_remark.getText().toString().trim();
        long day=InfoUtil.calculateTime(logStartTime,logStopTime);
        if(logName.equals("")){
            Toast.makeText(getApplicationContext(),"项目名称不能为空",Toast.LENGTH_SHORT).show();
        }else if(logType.equals("")){
            Toast.makeText(getApplicationContext(),"工作类型不能为空",Toast.LENGTH_SHORT).show();
        }else if(logStartTime.equals("")){
            Toast.makeText(getApplicationContext(),"工作开始时间不能为空",Toast.LENGTH_SHORT).show();
        }else if(logStopTime.equals("")){
            Toast.makeText(getApplicationContext(),"工作结束时间不能为空",Toast.LENGTH_SHORT).show();
        }else if(day!=0){
            Toast.makeText(getApplicationContext(),"每次只能添加一天的工作日志",Toast.LENGTH_SHORT).show();
        }else if(logDescribe.equals("")){
            Toast.makeText(getApplicationContext(),"工作描述不能为空",Toast.LENGTH_SHORT).show();
        }else if(logDescribe.length()<20){
            Toast.makeText(getApplicationContext(),"工作描述的字数不能少于20字",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"数据验证通过："+logName+logType+logStartTime+"\t"+logStopTime+logDescribe+logRemark,Toast.LENGTH_SHORT).show();
        }
    }

    public void messUpdate(){
        OkHttpCommonUtil.Param[] params=new OkHttpCommonUtil.Param[]{
                new OkHttpCommonUtil.Param("",logName),
                new OkHttpCommonUtil.Param("",logType),
                new OkHttpCommonUtil.Param("",logStartTime),
                new OkHttpCommonUtil.Param("",logStopTime),
                new OkHttpCommonUtil.Param("",logDescribe),
                new OkHttpCommonUtil.Param("",logDescribe)
        };
        OkHttpCommonUtil okhttp=OkHttpCommonUtil.newInstance(getApplicationContext());
        okhttp.postRequest("",params,new HttpBaseCallback(){
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),getString(R.string.net_error),Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(Response response) throws IOException {
               String result=response.body().string();
                try {
                    JSONObject json=new JSONObject(result);
                    int status=json.getInt("status");
                    if (status==1){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),getString(R.string.net_ok),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),getString(R.string.net_ok_error),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
