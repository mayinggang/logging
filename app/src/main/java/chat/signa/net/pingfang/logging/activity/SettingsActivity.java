package chat.signa.net.pingfang.logging.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import chat.signa.net.pingfang.logging.R;
import chat.signa.net.pingfang.logging.net.HttpBaseCallback;
import chat.signa.net.pingfang.logging.net.OkHttpCommonUtil;
import chat.signa.net.pingfang.logging.util.CommonUtil;

/**
 * Created by Administrator on 2016/1/11.
 */
public class SettingsActivity extends Activity {
    private EditText setting_name,setting_call,setting_phone,setting_email;
    private String name,call,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
    }

    public void initView(){
        setting_name=(EditText)findViewById(R.id.setting_name);
        setting_call=(EditText)findViewById(R.id.setting_call);
        setting_phone=(EditText)findViewById(R.id.setting_phone);
        setting_email=(EditText)findViewById(R.id.setting_email);
        setting_name.setKeyListener(null);

    }
    public void cancel(View view){
        navigateUp();
    }

    public void updates(View view){
        name=setting_name.getText().toString().trim();
        call=setting_call.getText().toString().trim();
        phone=setting_phone.getText().toString().trim();
        email=setting_email.getText().toString().trim();

        if(!CommonUtil.isPhone(call)){
            Toast.makeText(getApplicationContext(),getString(R.string.sett_call),Toast.LENGTH_SHORT).show();
        }else if (!CommonUtil.isPhoneNumberValid(phone)){
            Toast.makeText(getApplicationContext(),getString(R.string.sett_phone),Toast.LENGTH_SHORT).show();
        }else if(!CommonUtil.EmailUtil(email)){
            Toast.makeText(getApplicationContext(),getString(R.string.sett_email),Toast.LENGTH_SHORT).show();
        }else{
            OkHttpCommonUtil.Param[] params=new OkHttpCommonUtil.Param[]{
                    new OkHttpCommonUtil.Param("",name),
                    new OkHttpCommonUtil.Param("",call),
                    new OkHttpCommonUtil.Param("",phone),
                    new OkHttpCommonUtil.Param("",email)
            };

            OkHttpCommonUtil okhttp=OkHttpCommonUtil.newInstance(getApplicationContext());
            okhttp.getRequest("" ,params,new HttpBaseCallback(){
                //数据上传成功成功
                @Override
                public void onResponse(Response response) throws IOException {
                    String result=response.body().string();
                }

                //数据上传失败
                @Override
                public void onFailure(Request request, IOException e) {
                    super.onFailure(request, e);
                }
            });



        }


//        OkHttpCommonUtil.Param[] params=new OkHttpCommonUtil.Param[]{
//            new OkHttpCommonUtil.Param("",)
//        };
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
