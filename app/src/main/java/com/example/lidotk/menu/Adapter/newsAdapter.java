package com.example.lidotk.menu.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lidotk.menu.Bean.News;
import com.example.lidotk.menu.R;
import com.example.lidotk.menu.assistant.GetImageByUrl;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by lidotk on 2017/4/17.
 */
public class newsAdapter extends BaseAdapter {
    private Context context;
    private List<News> newsList= new ArrayList<>();

    public newsAdapter(Context context,List<News> newsList) {
        this.context=context;
        this.newsList = newsList;
    }

    public int getCount() {
        return newsList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //position代表位置

        //通过View关联自定义Item布局，进行填充

        if(convertView == null)
        {
            convertView = View.inflate(context, R.layout.news_item, null);
        }

        final News newsItem =newsList.get(position);

        //获取要显示的组件,注意findViewById的调用对象是上面填充了Item的布局的对象View
        TextView tv_name = (TextView)convertView.findViewById(R.id.txt_wb_item_uname);
        TextView tv_content = (TextView)convertView.findViewById(R.id.txt_wb_item_content);
        TextView tv_from =(TextView)convertView.findViewById(R.id.txt_wb_item_from);
        ImageView iv_content_pic=(ImageView)convertView.findViewById(R.id.img_wb_item_content_pic);

        //组件添加内容
        tv_content.setText(newsItem.getContent());
        tv_name.setText(newsItem.getName());
        tv_from.setText(newsItem.getTime());

        if(newsItem.getPic()!=null)
        {
            GetImageByUrl getImageByUrl = new GetImageByUrl();
            getImageByUrl.setImage(iv_content_pic,newsItem.getPic().getUrl());
            iv_content_pic.setVisibility(View.VISIBLE);
        }
        else
        {
            iv_content_pic.setVisibility(View.GONE);
        }

        return convertView;
    }



}
