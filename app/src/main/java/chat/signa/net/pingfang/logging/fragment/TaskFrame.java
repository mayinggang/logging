package chat.signa.net.pingfang.logging.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chat.signa.net.pingfang.logging.R;
import chat.signa.net.pingfang.logging.activity.LogAddActivity;

/**
 * Created by Administrator on 2016/1/7.
 */
public class TaskFrame extends Fragment implements View.OnClickListener{
    private TextView tv_task_item_log,tv_task_item_show,tv_task_item_show_mon,tv_task_item_show_yue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_task,container,false);
        tv_task_item_log=(TextView)view.findViewById(R.id.tv_task_item_log);
        tv_task_item_show=(TextView)view.findViewById(R.id.tv_task_item_show);
        tv_task_item_show_mon=(TextView)view.findViewById(R.id.tv_task_item_show_mon);
        tv_task_item_show_yue=(TextView)view.findViewById(R.id.tv_task_item_show_yue);
        tv_task_item_log.setOnClickListener(this);
        tv_task_item_show.setOnClickListener(this);
        tv_task_item_show_mon.setOnClickListener(this);
        tv_task_item_show_yue.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.tv_task_item_log://添加日志
                Intent intent=new Intent(getContext(), LogAddActivity.class);
                startActivity(intent);
                break;
            case  R.id.tv_task_item_show://查看日志一周内

                break;
            case R.id.tv_task_item_show_mon://查看日志一月内

                break;
            case R.id.tv_task_item_show_yue://查看日志一年内

                break;
        }

    }
}
