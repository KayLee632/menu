package com.example.lidotk.menu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lidotk.menu.Bean.Dinninghall;
import com.example.lidotk.menu.R;
import com.example.lidotk.menu.View.starbar;
import com.example.lidotk.menu.assistant.GetImageByUrl;

import java.util.ArrayList;



/**
 * Created by lidotk on 2017/4/17.
 */
public class DinninghallItem extends BaseAdapter {
    private ArrayList<Dinninghall> list;
    private Context context;


    //通过构造方法接受要显示的新闻数据集合
    public DinninghallItem(Context context, ArrayList<Dinninghall> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        //1.复用converView优化listview,创建一个view作为getview的返回值用来显示一个条目
        if(convertView != null){
            view = convertView;
        }else {
            //方法一：推荐
            //context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)包一层作为codify的返回值,一般传null
            //view = View.inflate(context, R.layout.dinninghall_item, null);//将一个布局文件转换成一个view对象

            //方法二
            //通过LayoutInflater将布局转换成view对象
            view =  LayoutInflater.from(context).inflate(R.layout.dinninghall_item, null);


        }
        //2.获取view上的子控件对象
        ImageView item_img_icon = (ImageView) view.findViewById(R.id.item_img_icon);
        TextView item_tv_des = (TextView) view.findViewById(R.id.item_des);
        TextView item_tv_title = (TextView) view.findViewById(R.id.item_title);
        starbar  item_star = (starbar) view.findViewById(R.id.starBar);
        //3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
        Dinninghall newsBean = list.get(position);

        //4.将数据设置给这些子控件做显示
        GetImageByUrl getImageByUrl = new GetImageByUrl();
        getImageByUrl.setImage(item_img_icon,newsBean.getPic().getUrl());//设置imageView的图片
        item_tv_title.setText(newsBean.getName());
        item_tv_des.setText(newsBean.getInfo());
        item_star.setStarMark(newsBean.getRank());

        return view;
    }

}
