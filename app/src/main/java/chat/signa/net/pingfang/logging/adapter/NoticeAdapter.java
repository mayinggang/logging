package chat.signa.net.pingfang.logging.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import chat.signa.net.pingfang.logging.R;
import chat.signa.net.pingfang.logging.entity.NoticeInfo;

/**
 * ֪ͨ�����listViewʹ�õ�adapter
 * Created by Administrator on 2016/1/8.
 */
public class NoticeAdapter extends BaseAdapter {

    private List<NoticeInfo> lt;
    private Context context;

    public NoticeAdapter(List<NoticeInfo> lt, Context context) {
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
   static class viewHolder{
                public static TextView id,title,mess;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                viewHolder viewholder;
                NoticeInfo info=lt.get(i);
                if(view==null){
                    viewholder=new viewHolder();
                    view= LayoutInflater.from(context).inflate(R.layout.item_layout,null);
                    viewholder.id=(TextView)view.findViewById(R.id.messageid);
                    viewholder.title=(TextView)view.findViewById(R.id.messagetitle);
            viewholder.mess=(TextView)view.findViewById(R.id.messagemsg);
            view.setTag(viewholder);
        }else{
            viewholder=(viewHolder)view.getTag();
        }
        viewholder.id.setText(info.getId());
        viewholder.title.setText(info.getTitle());
        viewholder.mess.setText(info.getMassage());
        return view;
    }
}
