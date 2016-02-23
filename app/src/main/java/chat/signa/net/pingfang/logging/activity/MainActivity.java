package chat.signa.net.pingfang.logging.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import chat.signa.net.pingfang.logging.R;
import chat.signa.net.pingfang.logging.net.HttpBaseCallback;
import chat.signa.net.pingfang.logging.net.OkHttpCommonUtil;
import chat.signa.net.pingfang.logging.net.ResultCallback;
import chat.signa.net.pingfang.logging.util.InfoUtil;


public class MainActivity extends Activity implements View.OnClickListener{
    private EditText et_login_no,et_login_pwd;
    private String loginName,loginPwd;
    private TextView register,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    public void initView(){
        et_login_no=(EditText)findViewById(R.id.et_login_no);
        et_login_pwd=(EditText)findViewById(R.id.et_login_pwd);
        register=(TextView)findViewById(R.id.register);
        register.setOnClickListener(this);
        login=(TextView)findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.register:

                break;
            case R.id.login:
                login();
                break;
        }
    }
    //??????????
    public void workType(){
        OkHttpCommonUtil.Param[] param=new OkHttpCommonUtil.Param[]{
            new OkHttpCommonUtil.Param("","")
        };
        OkHttpCommonUtil okhttp=OkHttpCommonUtil.newInstance(getApplicationContext());
        okhttp.postRequest("",param,new HttpBaseCallback(){
            @Override
            public void onFailure(Request request, IOException e) {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(getApplicationContext(),"?????????????",Toast.LENGTH_SHORT).show();
                   }
               });
            }

            @Override
            public void onResponse(Response response) throws IOException {
               String result=response.body().string();
                try {
                    JSONObject JSON=new JSONObject(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //??????????
    public String netUrl="http://121.40.100.61:8017/Document/%E9%A9%AC%E8%8B%B1%E5%88%9A-%E5%B7%A5%E4%BD%9C%E6%97%A5%E5%BF%9720160116-164248.xls";
    private String rootPath = "";
    public void NetLogin(){

      boolean isok= InfoUtil.isSDCardMounted();
        if (isok){
            rootPath=InfoUtil.getSDCardBaseDir();
        }

        //http://121.40.100.61:8017/Document/%E9%A9%AC%E8%8B%B1%E5%88%9A-%E5%B7%A5%E4%BD%9C%E6%97%A5%E5%BF%9720160116-164248.xls
        OkHttpCommonUtil.Param[] params=new OkHttpCommonUtil.Param[]{
                new OkHttpCommonUtil.Param("","")
        };
        OkHttpCommonUtil okhttp=OkHttpCommonUtil.newInstance(getApplicationContext());

        okhttp.downloadFileAsync(netUrl, rootPath, new ResultCallback() {

            @Override
            public void onError(Request request, Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"??????????",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Object response) {
                if (!response.equals("")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"??????????",Toast.LENGTH_SHORT).show();
                        }
                    });
            }
            }
        });

//        okhttp.postRequest(netUrl, params, new HttpBaseCallback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                super.onFailure(request, e);
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                String result = response.body().string();
//                try {
//                    JSONObject json = new JSONObject(result);
//                    int id = json.getInt("id");
//                    if (id == 1) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(getApplicationContext(), "?????????", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    } else {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(getApplicationContext(), "?????????", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
    }

    //???
    public void login(){
        loginName=et_login_no.getText().toString().trim();
        loginPwd=et_login_pwd.getText().toString().trim();
//isPhoneNumberValid   loginName
        if (loginName.equals("")){
            Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.username_null),Toast.LENGTH_SHORT).show();
        }else if (loginPwd.equals("")){
            Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.pwd_null),Toast.LENGTH_SHORT).show();
        }else if (loginPwd.length()<6){
            Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.newpwd_length),Toast.LENGTH_SHORT).show();
        }else{
            //???????????????
            //initNet()
            if(loginName.equals("15817419383")&&loginPwd.equals("123456")){
                //??????
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        }

    }
    public void initNet(){
        OkHttpCommonUtil.Param[] params = new OkHttpCommonUtil.Param[]{
                new OkHttpCommonUtil.Param("loginName",loginName),
                new OkHttpCommonUtil.Param("loginPwd",loginPwd)
        };
        OkHttpCommonUtil okHttp = OkHttpCommonUtil.newInstance(getApplicationContext());
        okHttp.postRequest("",params,new HttpBaseCallback(){
            //?????????????
            @Override
            public void onFailure(Request request, IOException e) {
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.net_ok_error),Toast.LENGTH_SHORT).show();
                  }
              });
            }
            //????????????



            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();//?????????????
                try {
                    JSONObject json=new JSONObject(result);
                    int status=json.getInt("status");
                    String mess=json.getString("message");
                    if (status==1){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.login_ok),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.login_no),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
