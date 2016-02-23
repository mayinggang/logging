package chat.signa.net.pingfang.logging.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import chat.signa.net.pingfang.logging.R;
import chat.signa.net.pingfang.logging.entity.LogInfo;

/**
 * Created by Administrator on 2016/1/28.
 */
public class LogAdapter extends BaseAdapter {


    private List<LogInfo> lt;
    private Context context;

    public LogAdapter(List<LogInfo> lt, Context context) {
        this.lt = lt;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lt.size();
    }

    @Override
    public Object getItem(int i) {
        return lt.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    static class ViewHolder{
        private static TextView id,project,start,stop,message,remark;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        LogInfo log=lt.get(i);
        if(view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.item_layout,null);
            holder.id=(TextView)view.findViewById(R.id.logid);
            holder.project=(TextView)view.findViewById(R.id.logproject);
            holder.start=(TextView)view.findViewById(R.id.startime);
            holder.stop=(TextView)view.findViewById(R.id.stoptime);
            holder.message=(TextView)view.findViewById(R.id.logmessage);
            holder.remark=(TextView)view.findViewById(R.id.logremark);
            view.setTag(holder);
        }else{
            holder=(ViewHolder)view.getTag();
        }
        holder.id.setText(log.getId());
        holder.project.setText(log.getProject());
        holder.start.setText(log.getStartTime());
        holder.stop.setText(log.getStopTime());
        holder.message.setText(log.getLogMessage());
        holder.remark.setText(log.getRemark());
        return view;
    }
}
