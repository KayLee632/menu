package com.example.lidotk.menu.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lidotk.menu.Adapter.newsAdapter;
import com.example.lidotk.menu.Bean.News;
import com.example.lidotk.menu.R;
import com.example.lidotk.menu.eating;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lidotk on 2017/4/17.
 */
public class newstab extends Fragment  {
    private Context mContext;
    private newsAdapter NewsAdapter;
    private  ArrayList<News> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = this.getActivity();
        new Thread(init).start();
    }


    Runnable init = new Runnable() {
        @Override
        public void run() {
            initData();
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", "请求结果");
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            // TODO
            // UI界面的更新等相关操作
            initView();
        }
    };



    public void initData(){
        BmobQuery<News> query = new BmobQuery<News>();
        query.order("-createdAt").addWhereEqualTo("ShopName", eating.choose.getName()).findObjects(new FindListener<News>() {

            @Override
            public void done(List<News> object, BmobException e) {

                if(e==null){
                    if(object.size()!=arrayList.size()){
                        arrayList.clear();
                    arrayList.addAll(object);
                    NewsAdapter.notifyDataSetChanged();
                }

                }else {
                    Log.i("Bmob", "NO"+e.getMessage());
                }
            }

        });


    }

    public void initView(){
        //2.找到控件
        ListView lv_news =  (ListView) getView().findViewById(R.id.lv_news);
        //3.创建一个adapter设置给listview
        NewsAdapter = new newsAdapter(mContext, arrayList);
        lv_news.setAdapter(NewsAdapter);
    }



}
