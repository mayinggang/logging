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
import chat.signa.net.pingfang.logging.activity.AddProjectActivity;
import chat.signa.net.pingfang.logging.activity.PwdActivity;
import chat.signa.net.pingfang.logging.activity.SettingsActivity;


/**
 * Created by Administrator on 2016/1/7.
 */
public class AccountFragment extends Fragment implements View.OnClickListener{
    private TextView tv_account_item_uploaded,tv_account_item_nearby,tv_account_item_resources,tv_account_item_adstare;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_account,container,false);
        tv_account_item_uploaded=(TextView)view.findViewById(R.id.tv_account_item_uploaded);
        tv_account_item_uploaded.setOnClickListener(this);
        tv_account_item_nearby=(TextView)view.findViewById(R.id.tv_account_item_nearby);
        tv_account_item_nearby.setOnClickListener(this);
        tv_account_item_resources=(TextView)view.findViewById(R.id.tv_account_item_resources);
        tv_account_item_resources.setOnClickListener(this);
        tv_account_item_adstare=(TextView)view.findViewById(R.id.tv_account_item_adstare);
        tv_account_item_adstare.setOnClickListener(this);


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        int viewId=view.getId();
        switch(viewId){
            case R.id.tv_account_item_uploaded://�鿴��Ŀ

                break;
            case R.id.tv_account_item_nearby://�����Ŀ
                Intent intent=new Intent(getContext(), AddProjectActivity.class);
                startActivity(intent);

                break;
            case R.id.tv_account_item_resources:
                Intent intents=new Intent(getContext(), SettingsActivity.class);
                startActivity(intents);
                break;
            case R.id.tv_account_item_adstare:
                Intent intentd=new Intent(getContext(), PwdActivity.class);
                startActivity(intentd);
                break;
        }

    }
}
