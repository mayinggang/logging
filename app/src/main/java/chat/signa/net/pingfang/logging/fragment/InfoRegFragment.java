package chat.signa.net.pingfang.logging.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chat.signa.net.pingfang.logging.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class InfoRegFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onClick(View view) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_inforeg,container,false);
//        Resources resources = getContext().getResources();
//        Drawable btnDrawable = resources.getDrawable(R.drawable.bg_cheer);
//        view.setBackgroundDrawable(btnDrawable);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
