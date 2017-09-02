package com.example.lidotk.menu.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.lidotk.menu.Adapter.adviceAdapter;
import com.example.lidotk.menu.AddActivity;
import com.example.lidotk.menu.Bean.Advice;
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
public class advicetab extends Fragment  {
    private Context mContext;

    private List<Advice> allAdvice = new ArrayList<>();

    private adviceAdapter AdviceAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.advice_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = this.getActivity();
        initDate();
        initview();

        //2.找到控件
        Button add = (Button) getView().findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);

            }
        });
    }

    public void initDate() {
        //1.获取新闻数据用list封装


        BmobQuery<Advice> query1 = new BmobQuery<Advice>();
        query1.order("-createdAt").addWhereEqualTo("Shopname", eating.choose.getName()).findObjects(new FindListener<Advice>() {

            @Override
            public void done(List<Advice> object, BmobException e) {

                if (e == null) {
                    if(object.size()!=allAdvice.size()) {
                        allAdvice.clear();
                        allAdvice.addAll(object);
                        AdviceAdapter.notifyDataSetChanged();
                    }

                    Log.i("Advice", "done " + allAdvice.size());
                } else {
                    Log.i("Advice", "NO");
                }
            }

        });
        Log.i("", "initDate: "+allAdvice.size());
    }



    public void initview(){
        ListView lv_advice =(ListView) getView().findViewById(R.id.list_advice);

        //3.创建一个adapter设置给listview
         AdviceAdapter = new adviceAdapter(mContext, allAdvice);
        AdviceAdapter.notifyDataSetChanged();
        //添加评论

        lv_advice.setAdapter(AdviceAdapter);
    }

}
