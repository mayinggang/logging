package chat.signa.net.pingfang.logging.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chat.signa.net.pingfang.logging.R;
import chat.signa.net.pingfang.logging.entity.NoticeInfo;
import chat.signa.net.pingfang.logging.net.HttpBaseCallback;
import chat.signa.net.pingfang.logging.net.OkHttpCommonUtil;

/**
 * ֪ͨ公告信息
 * Created by Administrator on 2016/1/8.
 */
public class NoticeInfoFragment extends Fragment implements View.OnClickListener {
    public ListView notice;
   // private String URL="";//��ȡ���ݵ�API��ַ
    private List<NoticeInfo> lt=new ArrayList<NoticeInfo>();
    String str;
    private TextView texts;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.noticeinfo_activity,container,false);
       // netNotice();
        notice=(ListView)view.findViewById(R.id.notice);
        texts=(TextView)view.findViewById(R.id.texts);
        AsynTasks asyn=new AsynTasks();
        asyn.execute();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    class AsynTasks extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            texts.setText(str);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpCommonUtil.Param[] params=new OkHttpCommonUtil.Param[]{
                    new OkHttpCommonUtil.Param("","")
            };
            OkHttpCommonUtil okhttp=OkHttpCommonUtil.newInstance(getContext());
            okhttp.postRequest("",params,new HttpBaseCallback(){

                @Override
                public void onFailure(Request request, IOException e) {
                    super.onFailure(request, e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String result=response.body().string();
                    try {
                        JSONArray jsonArray=new JSONArray(result);
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                            NoticeInfo info=new NoticeInfo();
                            info.setId(jsonObject.getInt("id"));
                            info.setMassage(jsonObject.getString("message"));
                            info.setTime(jsonObject.getString("time"));
                            info.setTitle(jsonObject.getString("title"));
                            lt.add(info);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            });
            return null;
        }
    }

    public void netNotice(){
        OkHttpCommonUtil.Param[] params=new OkHttpCommonUtil.Param[]{
          new OkHttpCommonUtil.Param("","")
        };
        OkHttpCommonUtil okhttp=OkHttpCommonUtil.newInstance(getContext());
        okhttp.postRequest("",params,new HttpBaseCallback(){

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
               String result=response.body().string();
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        NoticeInfo info=new NoticeInfo();
                        info.setId(jsonObject.getInt("id"));
                        info.setMassage(jsonObject.getString("message"));
                        info.setTime(jsonObject.getString("time"));
                        info.setTitle(jsonObject.getString("title"));
                        lt.add(info);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });

    }



    @Override
    public void onClick(View view) {

    }
}
