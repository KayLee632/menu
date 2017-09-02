package com.example.lidotk.menu.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lidotk.menu.Bean.Advice;
import com.example.lidotk.menu.R;
import com.example.lidotk.menu.View.starbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lidotk on 2017/4/17.
 */
public class adviceAdapter extends BaseAdapter {
    private Context context;
    private List<Advice> adviceList= new ArrayList<>();


    public adviceAdapter(Context context,List<Advice> adviceList) {
//        System.out.println(newsList.get(1));
        this.context=context;
        this.adviceList=adviceList;
    }
    @Override
    public int getCount() {
        return adviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //position代表位置

        //通过View关联自定义Item布局，进行填充

        if(view == null)
        {
            view = view.inflate(context, R.layout.advice_item, null);
        }

        System.out.println(position);
        Advice AdviceItem =adviceList.get(position);

        //获取要显示的组件,注意findViewById的调用对象是上面填充了Item的布局的对象View
        starbar tv_star = (starbar) view.findViewById(R.id.tv_star);
        TextView tv_describe = (TextView)view.findViewById(R.id.tv_describe);
        TextView tv_time =(TextView)view.findViewById(R.id.tv_time);
        TextView tv_phone =(TextView)view.findViewById(R.id.tv_phone);
        ImageView iv_pic=(ImageView)view.findViewById(R.id.tv_pic);

        //组件添加内容
        tv_star.setStarMark(AdviceItem.getStar());
        tv_describe.setText(AdviceItem.getDescribe());
        tv_phone.setText(AdviceItem.getPhone());
        tv_time.setText(AdviceItem.getTime());

        if(AdviceItem.getPic()!=null)
        {
            iv_pic.setVisibility(View.VISIBLE);
        }
        else
        {
            iv_pic.setVisibility(View.GONE);
        }

        return view;
    }
}
