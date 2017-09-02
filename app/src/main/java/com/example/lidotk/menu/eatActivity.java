package com.example.lidotk.menu;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lidotk.menu.Fragment.advicetab;
import com.example.lidotk.menu.Fragment.foodtab;
import com.example.lidotk.menu.Fragment.newstab;
import com.example.lidotk.menu.View.starbar;
import com.example.lidotk.menu.assistant.GetImageByUrl;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class eatActivity extends AppCompatActivity  {
    /**
     * 后退键
     */
    private Button back;

    private RadioGroup discount_layout;
    /**
     * 团优惠
     */
    private RadioButton group_rb;
    /**
     * 优惠活动
     */
    private RadioButton preferential_rb;
    /**
     * 优惠券
     */
    private RadioButton coupon_rb;
    /**
     * 下划线标记
     */
    private View group_line, preferential_line, coupon_line;
    /**
     * 菜单
     */
    private foodtab foodtab;
    /**
     * 选座位
     */
    private advicetab advicetab;
    /**
     * 评论
     */
    private newstab newstab;
    /**
     * 店铺图标
     */
    private ImageView icon;
    /**
     * 店铺名称
     */
    private TextView name;
    /**
     * 店铺位置
     */
    private TextView Info;

    private starbar star;

    private ViewPager pager;

    private ArrayList<Fragment> fragments;

    /**
     * 标题名集合
     */
    private RadioButton[] titleText = null;

    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);
        res = getResources();
        initView();
    }

    private void initView() {
        back = (Button) findViewById(R.id.back_key);
        icon = (ImageView) findViewById(R.id.choose_item_img_icon);
        name = (TextView) findViewById(R.id.choose_item_title);
        Info = (TextView) findViewById(R.id.choose_item_des);
        star = (starbar) findViewById(R.id.choose_item_star);
        pager = (ViewPager) findViewById(R.id.pager);
        discount_layout = (RadioGroup) findViewById(R.id.discount_layout);
        group_rb = (RadioButton) findViewById(R.id.group_rb);
        preferential_rb = (RadioButton) findViewById(R.id.preferential_rb);
        coupon_rb = (RadioButton) findViewById(R.id.coupon_rb);
        group_line = findViewById(R.id.group_line);
        preferential_line = findViewById(R.id.preferential_line);
        coupon_line = findViewById(R.id.coupon_line);

        titleText = new RadioButton[]{group_rb, preferential_rb, coupon_rb};
        discount_layout.setOnCheckedChangeListener(listener);
        back.setOnClickListener(Listener);

        GetImageByUrl getImageByUrl = new GetImageByUrl();
        getImageByUrl.setImage(icon, eating.choose.getPic().getUrl());

        name.setText(eating.choose.getName());
        Info.setText(eating.choose.getInfo());
        star.setStarMark(eating.choose.getRank());

        fragments = new ArrayList<Fragment>();
        foodtab = new foodtab();
        advicetab = new advicetab();
        newstab = new newstab();

        fragments.add(foodtab);
        fragments.add(advicetab);
        fragments.add(newstab);

        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(fragmentPagerAdapter);
        fragmentPagerAdapter.setFragments(fragments);
        pager.setOnPageChangeListener(new MyOnPageChangeListener());
        // 第一次启动时选中第0个tab
        pager.setCurrentItem(0);
        pager.setOffscreenPageLimit(2);
    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.group_rb:
                    pager.setCurrentItem(0);
                    break;

                case R.id.preferential_rb:
                    pager.setCurrentItem(1);
                    break;

                case R.id.coupon_rb:
                    pager.setCurrentItem(2);
                    break;
            }
        }
    };

    private  Button.OnClickListener Listener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(eatActivity.this, eating.class);
            startActivity(myIntent);
            finish();
        }
    };



    /**
     * 切换更换下划线状态
     *
     * @param position
     */
    private void setVisible(int position) {
        switch (position) {
            case 0:
                group_line.setVisibility(View.VISIBLE);
                preferential_line.setVisibility(View.INVISIBLE);
                coupon_line.setVisibility(View.INVISIBLE);
                group_rb.setTextColor(res.getColor(R.color.blue_font));
                preferential_rb.setTextColor(res.getColor(R.color.black));
                coupon_rb.setTextColor(res.getColor(R.color.black));
                break;
            case 1:
                group_line.setVisibility(View.INVISIBLE);
                preferential_line.setVisibility(View.VISIBLE);
                coupon_line.setVisibility(View.INVISIBLE);
                group_rb.setTextColor(res.getColor(R.color.black));
                preferential_rb.setTextColor(res.getColor(R.color.blue_font));
                coupon_rb.setTextColor(res.getColor(R.color.black));
                break;
            case 2:
                group_line.setVisibility(View.INVISIBLE);
                preferential_line.setVisibility(View.INVISIBLE);
                coupon_line.setVisibility(View.VISIBLE);
                group_rb.setTextColor(res.getColor(R.color.black));
                preferential_rb.setTextColor(res.getColor(R.color.black));
                coupon_rb.setTextColor(res.getColor(R.color.blue_font));
                break;
        }
    }

    /**
     * 设置选中图标的文字颜色与
     * 下划线可见
     *
     * @param index
     */
    private void chingeIndexView(int index) {
        for (int i = 0; i < titleText.length; i++) {
            titleText[i].setChecked(false);
        }
        if (index < titleText.length) {
            titleText[index].setChecked(true);
        }

    }


    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> fragments;
        private FragmentManager fm;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fm = fm;
            this.fragments = fragments;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }


        public void setFragments(ArrayList<Fragment> fragments) {
            if (this.fragments != null) {
                FragmentTransaction ft = fm.beginTransaction();
                for (Fragment f : this.fragments) {
                    ft.remove(f);
                }
                ft.commit();
                ft = null;
                fm.executePendingTransactions();
            }
            this.fragments = fragments;
            notifyDataSetChanged();
        }


        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            Object obj = super.instantiateItem(container, position);
            return obj;
        }

    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            chingeIndexView(position);
            setVisible(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
        }
    }
}

