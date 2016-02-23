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
 * ��ӹ�����־
 * Created by Administrator on 2016/1/8.
 */
public class LogAddActivity extends Activity {
    public static final String TAG = LogAddActivity.class.getSimpleName();
    private Spinner log_name,log_type;//��Ŀ���ƣ���������
    private EditText log_starttime,log_stoptime;//��ʼʱ�䣬����ʱ��
    private EditText log_describe,log_remark;//����������������ע
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
    //��ʼ���ؼ�
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
         Toast.makeText(activity,"ѡ�����ڲ��ܴ��ڵ�ǰ����",Toast.LENGTH_SHORT).show();
         } else {
         inputDate.setText(dateTime);
         }

         System.out.println("..........." + day + "��");
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
    //ѡ��������
    public void showSpinner2() {
        // ��һ�������һ�������б����list��������ӵ�����������б�Ĳ˵���
        lists.add("������");
        lists.add("�Ϻ���");
        lists.add("������");
        lists.add("�Ͼ���");
        lists.add("������");
        // �ڶ�����Ϊ�����б���һ����������������õ���ǰ�涨���list��
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lists);
        // ��������Ϊ���������������б�����ʱ�Ĳ˵���ʽ�� simple_spinner_item
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // ���Ĳ�������������ӵ������б���
        log_type.setAdapter(adapter);
        // ���岽��Ϊ�����б����ø����¼�����Ӧ���������Ӧ�˵���ѡ��
        log_type
                .setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int position, long arg3) {
						/* ����ѡmySpinner ��ֵ����myTextView �� */
                        logType = adapter.getItem(position);
                        Toast.makeText(getApplicationContext(), "��ѡ����ǣ�" + adapter.getItem(position), Toast.LENGTH_SHORT).show();

						/* ��mySpinner ��ʾ */
                        arg0.setVisibility(View.VISIBLE);

                    }

                    public void onNothingSelected(AdapterView<?> arg0) {
                        Toast.makeText(getApplicationContext(), "��ѡ����ǣ�" + "NONE", Toast.LENGTH_SHORT).show();
                    }
                });
		/* �����˵�����������ѡ����¼����� */
        log_type.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
				/* ��mySpinner ���أ�������Ҳ���ԣ����Լ����� */
                // v.setVisibility(View.INVISIBLE);

                return false;
            }
        });
		/* �����˵�����������ѡ���ı��¼����� */
        log_type
                .setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
                    public void onFocusChange(View v, boolean hasFocus) {
                        v.setVisibility(View.VISIBLE);

                    }
                });
    }
    //ѡ����Ŀ����
    public void showSpinner1() {
        // ��һ�������һ�������б����list��������ӵ�����������б�Ĳ˵���
        list.add("����");
        list.add("�Ϻ�");
        list.add("����");
        list.add("�Ͼ�");
        list.add("����");
        // �ڶ�����Ϊ�����б���һ����������������õ���ǰ�涨���list��
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        // ��������Ϊ���������������б�����ʱ�Ĳ˵���ʽ�� simple_spinner_item
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // ���Ĳ�������������ӵ������б���
        log_name.setAdapter(adapter);
        // ���岽��Ϊ�����б����ø����¼�����Ӧ���������Ӧ�˵���ѡ��
        log_name
                .setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int position, long arg3) {
						/* ����ѡmySpinner ��ֵ����myTextView �� */
                        logName=adapter.getItem(position);
                        Toast.makeText(getApplicationContext(),"��ѡ����ǣ�" + adapter.getItem(position),Toast.LENGTH_SHORT).show();
						/* ��mySpinner ��ʾ */
                        arg0.setVisibility(View.VISIBLE);

                    }

                    public void onNothingSelected(AdapterView<?> arg0) {
                        Toast.makeText(getApplicationContext(),"��ѡ����ǣ�" + "NONE",Toast.LENGTH_SHORT).show();
                    }
                });
		/* �����˵�����������ѡ����¼����� */
        log_name.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
				/* ��mySpinner ���أ�������Ҳ���ԣ����Լ����� */
                // v.setVisibility(View.INVISIBLE);
                return false;
            }
        });
		/* �����˵�����������ѡ���ı��¼����� */
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



    //�����ύ  logName,logType,logStartTime,logStopTime,logDescribe,logRemark;
    public void logPost(){
        logStartTime=log_starttime.getText().toString().trim();
        logStopTime=log_stoptime.getText().toString().trim();
        logDescribe=log_describe.getText().toString().trim();
        logRemark=log_remark.getText().toString().trim();
        long day=InfoUtil.calculateTime(logStartTime,logStopTime);
        if(logName.equals("")){
            Toast.makeText(getApplicationContext(),"��Ŀ���Ʋ���Ϊ��",Toast.LENGTH_SHORT).show();
        }else if(logType.equals("")){
            Toast.makeText(getApplicationContext(),"�������Ͳ���Ϊ��",Toast.LENGTH_SHORT).show();
        }else if(logStartTime.equals("")){
            Toast.makeText(getApplicationContext(),"������ʼʱ�䲻��Ϊ��",Toast.LENGTH_SHORT).show();
        }else if(logStopTime.equals("")){
            Toast.makeText(getApplicationContext(),"��������ʱ�䲻��Ϊ��",Toast.LENGTH_SHORT).show();
        }else if(day!=0){
            Toast.makeText(getApplicationContext(),"ÿ��ֻ�����һ��Ĺ�����־",Toast.LENGTH_SHORT).show();
        }else if(logDescribe.equals("")){
            Toast.makeText(getApplicationContext(),"������������Ϊ��",Toast.LENGTH_SHORT).show();
        }else if(logDescribe.length()<20){
            Toast.makeText(getApplicationContext(),"����������������������20��",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"������֤ͨ����"+logName+logType+logStartTime+"\t"+logStopTime+logDescribe+logRemark,Toast.LENGTH_SHORT).show();
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


    //ȡ��
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
