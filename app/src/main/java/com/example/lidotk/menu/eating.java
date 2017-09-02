package com.example.lidotk.menu;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.lidotk.menu.Adapter.FragmentAdapter;
import com.example.lidotk.menu.Bean.Dinninghall;
import com.example.lidotk.menu.Fragment.tab_01;
import com.example.lidotk.menu.Fragment.tab_02;
import com.example.lidotk.menu.Fragment.tab_03;

import cn.bmob.v3.Bmob;

public class eating extends FragmentActivity {

    /**
     * 顶部三个LinearLayout
     */
    private LinearLayout mTabChat;
    private LinearLayout mTabFound;
    private LinearLayout mTabContact;

    /**
     * 顶部的三个TextView
     */
    private TextView chat;
    private TextView found;
    private TextView contact;
    private View first;
    private View second;
    private View third;

    /**
     * Tab的那个引导线
     */
    private ImageView mTabLine;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;

    private ViewPager mViewPager;
    private FragmentAdapter mAdapter;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    static public Dinninghall choose;

    private Resources res;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,"57fb8cde2840b3ecf4abb6fe4923f00e" );
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_eating);
        res=getResources();

        initView();

        mViewPager=(ViewPager) findViewById(R.id.id_viewpager);

        /**
         * 初始化Adapter
         */
        mAdapter=new FragmentAdapter(getSupportFragmentManager(), fragments);

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new TabOnPageChangeListener());

        initTabLine();
    }

    /**
     * 根据屏幕的宽度，初始化引导线的宽度
     */
    private void initTabLine() {
        mTabLine=(ImageView) findViewById(R.id.id_tab_line);

        //获取屏幕的宽度
        DisplayMetrics outMetrics=new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        screenWidth=outMetrics.widthPixels;

        //获取控件的LayoutParams参数(注意：一定要用父控件的LayoutParams写LinearLayout.LayoutParams)
        LinearLayout.LayoutParams lp=(android.widget.LinearLayout.LayoutParams) mTabLine.getLayoutParams();
        lp.width=screenWidth/3;//设置该控件的layoutParams参数
        mTabLine.setLayoutParams(lp);//将修改好的layoutParams设置为该控件的layoutParams
    }

    /**
     * 初始化控件，初始化Fragment
     */
    private void initView() {
        chat=(TextView) findViewById(R.id.id_chat);
        found=(TextView) findViewById(R.id.id_found);
        contact=(TextView) findViewById(R.id.id_contact);
        first =  findViewById(R.id.first_line);
        second =  findViewById(R.id.second_line);
        third =  findViewById(R.id.third_line);

        chat.setOnClickListener(new TabOnClickListener(0));
        found.setOnClickListener(new TabOnClickListener(1));
        contact.setOnClickListener(new TabOnClickListener(2));

        fragments.add(new tab_01());
        fragments.add(new tab_02());
        fragments.add(new tab_03());

        mTabChat=(LinearLayout) findViewById(R.id.id_tab1_chat);
        mTabFound=(LinearLayout) findViewById(R.id.id_tab2_found);
        mTabContact=(LinearLayout) findViewById(R.id.id_tab3_contact);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        chat.setTextColor(res.getColor(R.color.gray));
        found.setTextColor(res.getColor(R.color.gray));
        contact.setTextColor(res.getColor(R.color.gray));
        first.setVisibility(View.INVISIBLE);
        second.setVisibility(View.INVISIBLE);
        third.setVisibility(View.INVISIBLE);
    }

    /**
     * 功能：点击主页TAB事件
     */
    public class TabOnClickListener implements View.OnClickListener{
        private int index=0;

        public TabOnClickListener(int i){
            index=i;
        }

        public void onClick(View v) {
            mViewPager.setCurrentItem(index);//选择某一页
        }

    }

    /**
     * 功能：Fragment页面改变事件
     */
    public class TabOnPageChangeListener implements ViewPager.OnPageChangeListener {

        //当滑动状态改变时调用
        public void onPageScrollStateChanged(int state) {

        }

        //当前页面被滑动时调用
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
            LinearLayout.LayoutParams lp=(android.widget.LinearLayout.LayoutParams) mTabLine.getLayoutParams();
            //返回组件距离左侧组件的距离
            lp.leftMargin= (int) ((positionOffset+position)*screenWidth/3);
            mTabLine.setLayoutParams(lp);
        }

        //当新的页面被选中时调用
        public void onPageSelected(int position) {
            //重置所有TextView的字体颜色
            resetTextView();
            switch (position) {
                case 0:
                    chat.setTextColor(res.getColor(R.color.white));
                    first.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    found.setTextColor(res.getColor(R.color.white));
                    second.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    contact.setTextColor(res.getColor(R.color.white));
                    third.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

}

