package com.example.lidotk.menu.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.lidotk.menu.Adapter.DinninghallItem;
import com.example.lidotk.menu.Bean.Advice;
import com.example.lidotk.menu.Bean.Dinninghall;
import com.example.lidotk.menu.R;
import com.example.lidotk.menu.eatActivity;
import com.example.lidotk.menu.eating;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lidotk on 2017/4/17.
 */
public class tab_02 extends Fragment implements OnItemClickListener {
    private Context mContext;

    private static ArrayList<Dinninghall> allDinningHall2 = new ArrayList<>();

    private DinninghallItem dinninghallAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.tab_02, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
        mContext = this.getActivity();
        new Thread(initDate).start();

    }

    Runnable initDate = new Runnable() {
        @Override
        public void run() {
            BmobQuery<Dinninghall> query2 = new BmobQuery<Dinninghall>();
            query2.addWhereEqualTo("Position","西").findObjects(new FindListener<Dinninghall>() {

                @Override
                public void done(List<Dinninghall> object, BmobException e) {

                    if(e==null){
                        if(object.size()!=allDinningHall2.size()){
                            allDinningHall2.clear();
                            allDinningHall2.addAll(object);
                            dinninghallAdapter.notifyDataSetChanged();
                        }
                    }else {
                        Log.i("Bmob", "NO");
                    }
                }

            });
            for (final Dinninghall d:allDinningHall2){
                BmobQuery<Advice> query21 = new BmobQuery<Advice>();
                query21.addWhereEqualTo("Belong",new BmobPointer(d));
                query21.findObjects(new FindListener<Advice>() {
                    @Override
                    public void done(List<Advice> list, BmobException e) {
                        if(e==null){
                            Float all=0f;
                            for (Advice a:list
                                    ) {
                                all +=a.getStar();
                            }
                            d.setRank(all/list.size());
                        }else {
                            Log.i("Bmob", "NO");
                        }
                    }
                });
            }
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

    public void initView(){
        //2.找到控件
        ListView halls = (ListView) getView().findViewById(R.id.tab2_list);
        //3.创建一个adapter设置给listview
        dinninghallAdapter = new DinninghallItem(mContext, allDinningHall2);
        halls.setAdapter(dinninghallAdapter);
        //4.设置listview条目的点击事件
        halls.setOnItemClickListener(this);
    }



    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id){
        Dinninghall bean = (Dinninghall) parent.getItemAtPosition(position);
        eating.choose = bean;
        Intent intent = new Intent(getActivity(), eatActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
