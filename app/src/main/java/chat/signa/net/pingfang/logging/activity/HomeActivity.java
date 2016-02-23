package chat.signa.net.pingfang.logging.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chat.signa.net.pingfang.logging.R;
import chat.signa.net.pingfang.logging.fragment.AccountFragment;
import chat.signa.net.pingfang.logging.fragment.InfoRegFragment;
import chat.signa.net.pingfang.logging.fragment.NoticeInfoFragment;
import chat.signa.net.pingfang.logging.fragment.TaskFrame;

/**
 * Created by Administrator on 2016/1/7.
 */
public class HomeActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;
    // ???
    private TextView text_seller_description = null;
    private TextView text_common_problem = null;
    private TextView text_purchase_process = null;
    private TextView text_same_shop = null;
    private LinearLayout ll_seller_description = null;
    private LinearLayout ll_common_problem = null;
    private LinearLayout ll_purchase_process = null;
    private LinearLayout ll_same_shop = null;
    private ImageView img_line;


    private int select_color;
    private int unselect_color;

    private int mScreen1_4;


    private Integer viewPagerW = 0;

    private static int width = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initLine();
        initView();
        initFragment();

    }

    private void initLine() {
        img_line = (ImageView) findViewById(R.id.img_line);
        mScreen1_4 = getScreenWidth(this) / 4;
        LayoutParams lp = img_line.getLayoutParams();
        lp.width = mScreen1_4;
        img_line.setLayoutParams(lp);
    }

    private void initView() {
        // ??????
        select_color = getResources().getColor(R.color.text_orange);
        unselect_color = getResources().getColor(R.color.black);

        text_seller_description = (TextView) findViewById(R.id.text_seller_description);
        text_common_problem = (TextView) findViewById(R.id.text_common_problem);
        text_purchase_process = (TextView) findViewById(R.id.text_purchase_process);
        text_same_shop = (TextView) findViewById(R.id.text_same_shop);
        ll_seller_description = (LinearLayout) findViewById(R.id.linear_seller_description);
        ll_common_problem = (LinearLayout) findViewById(R.id.linear_common_problem);
        ll_purchase_process = (LinearLayout) findViewById(R.id.linear_purchase_process);
        ll_same_shop = (LinearLayout) findViewById(R.id.linear_same_shop);

        ll_seller_description.setOnClickListener(new MyOnClickListenser(0));
        ll_common_problem.setOnClickListener(new MyOnClickListenser(1));
        ll_purchase_process.setOnClickListener(new MyOnClickListenser(2));
        ll_same_shop.setOnClickListener(new MyOnClickListenser(3));

        mViewPager = (ViewPager) findViewById(R.id.mViewpager);
        mDatas = new ArrayList<Fragment>();

    }
    public class MyOnClickListenser implements View.OnClickListener {

        private int index = 0;

        public MyOnClickListenser(int i) {
            index = i;
        }
        @Override
        public void onClick(View v) {
//            //改变字体颜色
//            switch (v.getId()) {
//                case R.id.linear_seller_description:
//                    tcolor(0);
//                    break;
//                case R.id.linear_common_problem:
//                    tcolor(1);
//                    break;
//                case R.id.linear_purchase_process:
//                    tcolor(2);
//                    break;
//                case R.id.linear_same_shop:
//                    tcolor(3);
//                    break;
//            }
            mViewPager.setCurrentItem(index);
        }

    }

    private void resetTextColor() {
        text_seller_description.setTextColor(unselect_color);
        text_common_problem.setTextColor(unselect_color);
        text_purchase_process.setTextColor(unselect_color);
        text_same_shop.setTextColor(unselect_color);
    }

    private void initFragment() {
        NoticeInfoFragment mSDF = new NoticeInfoFragment();
        InfoRegFragment mCPF = new InfoRegFragment();
        TaskFrame mPPF = new TaskFrame();
        AccountFragment mSSF = new AccountFragment();
        mDatas.add(mSDF);
        mDatas.add(mCPF);
        mDatas.add(mPPF);
        mDatas.add(mSSF);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mDatas == null ? 0 : mDatas.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mDatas.get(position);
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);

    }

    @Override
    public void onPageSelected(int i) {

    }

    public void tcolor(int i){


        if(i==0){
            text_seller_description.setSelected(true);
        }else{
            text_seller_description.setSelected(false);
        }

        if(i==1){
            text_seller_description.setSelected(true);
        }else{
            text_seller_description.setSelected(false);
        }
        if(i==2){
            text_seller_description.setSelected(true);
        }else{
            text_seller_description.setSelected(false);
        }

        if(i==3){
            text_seller_description.setSelected(true);
        }else{
            text_seller_description.setSelected(false);
        }
    }

      /** 当前视图宽度 **/

      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

          viewPagerW = mViewPager.getWidth() + mViewPager.getPageMargin();
          LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) img_line.getLayoutParams();

          lp.leftMargin = (int) ((int) (mScreen1_4 * position) + (((double) positionOffsetPixels / viewPagerW) * mScreen1_4));

          img_line.setLayoutParams(lp);
         // tcolor(position);
      }



    @Override
    public void onPageScrollStateChanged(int position) {

    }

    public static int getScreenWidth(Context context) {
        if (width == 0) {
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            width = display.getWidth();
        }
        return width;
    }



}
