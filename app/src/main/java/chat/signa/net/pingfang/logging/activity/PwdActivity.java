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

/**
 * Created by Administrator on 2016/1/11.
 */
public class PwdActivity extends Activity {
    private EditText upwd_name,upwd_pwd,upwd_newpwd,upwd_newpwds;
    private String name,pwd,newPwd,newPwds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);
        initView();
    }
    public void initView(){
        upwd_name=(EditText)findViewById(R.id.upwd_name);
        upwd_pwd=(EditText)findViewById(R.id.upwd_pwd);
        upwd_newpwd=(EditText)findViewById(R.id.upwd_newpwd);
        upwd_newpwds=(EditText)findViewById(R.id.upwd_newpwds);
    }

    public void cancel(View v){
        navigateUp();
    }

    public void updatepwd(View view){
       // name, pwd,newPwd,newPwds
        name=upwd_name.getText().toString().trim();
        pwd=upwd_pwd.getText().toString().trim();
        newPwd=upwd_newpwd.getText().toString().trim();
        newPwds= upwd_newpwds.getText().toString().trim();
        if (name.equals("")){
            Toast.makeText(getApplicationContext(),getString(R.string.username_null),Toast.LENGTH_SHORT).show();
        }else if(pwd.equals("")){
            Toast.makeText(getApplicationContext(),getString(R.string.pwd_null),Toast.LENGTH_SHORT).show();
        }else if(newPwd.equals("")){
            Toast.makeText(getApplicationContext(),getString(R.string.newpwd_null),Toast.LENGTH_SHORT).show();
        }else if (newPwds.equals("")){
            Toast.makeText(getApplicationContext(),getString(R.string.newpwds_null),Toast.LENGTH_SHORT).show();
        }else if(newPwd.length()<6){
            Toast.makeText(getApplicationContext(),getString(R.string.newpwd_length),Toast.LENGTH_SHORT).show();
        }else if(newPwd.length()>15){
            Toast.makeText(getApplicationContext(),getString(R.string.newpwd_lengths),Toast.LENGTH_SHORT).show();
        }else if(newPwd.equals(newPwds)){
            //数据上传

            OkHttpCommonUtil.Param[] params=new OkHttpCommonUtil.Param[]{
                    new OkHttpCommonUtil.Param("",name),
                    new OkHttpCommonUtil.Param("",pwd),
                    new OkHttpCommonUtil.Param("",newPwd),
                    new OkHttpCommonUtil.Param("",newPwds),
            };


            OkHttpCommonUtil okhttp=OkHttpCommonUtil.newInstance(getApplicationContext());
            okhttp.postRequest("",params,new HttpBaseCallback(){
                @Override
                public void onFailure(Request request, IOException e) {
                    super.onFailure(request, e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String result=response.body().string();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),getString(R.string.pwds_null),Toast.LENGTH_SHORT).show();
        }



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
