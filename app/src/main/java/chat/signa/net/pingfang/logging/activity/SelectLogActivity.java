package chat.signa.net.pingfang.logging.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import chat.signa.net.pingfang.logging.R;

/**
 * Created by Administrator on 2016/1/28.
 * »’÷æ≤È—Ø
 */
public class SelectLogActivity extends Activity {


    private ListView loglist;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_selectlog);
        loglist=(ListView)findViewById(R.id.loglist);
    }
}
