package com.example.lidotk.menu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.lidotk.menu.Bean.Advice;
import com.example.lidotk.menu.View.starbar;


import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 
 * @ClassName: AddActivity
 * @Description: TODO
 */
public class AddActivity extends Activity implements OnClickListener {

	EditText  edit_photo, edit_describe;
	starbar edit_star;
	Button btn_back, btn_true;
	starbar.OnStarChangeListener Listenstar;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		initData();
		initViews();
		initListeners();
	}


	public void initViews() {
		// TODO Auto-generated method stub
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_true = (Button) findViewById(R.id.btn_true);
		edit_photo = (EditText) findViewById(R.id.edit_phone);
		edit_describe = (EditText) findViewById(R.id.edit_describe);
		edit_star = (starbar) findViewById(R.id.edit_star);
	}


	public void initListeners() {
		// TODO Auto-generated method stub
		btn_back.setOnClickListener(this);
		btn_true.setOnClickListener(this);
		edit_star.setOnStarChangeListener(Listenstar);
	}


	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btn_true) {
			addByType();
		} else if (v == btn_back) {
			finish();
		}
	}
	Float star = 0f;
	String describe = "";
	String photo="";
	

	private void addByType(){
		star = edit_star.getStarMark();
		describe = edit_describe.getText().toString();
		photo = edit_photo.getText().toString();
		if(star==0f){
			return;
		}
		if(TextUtils.isEmpty(describe)){
			return;
		}
		if(TextUtils.isEmpty(photo)){
			return;
		}
		addAdvice();
	}

	private void addAdvice(){
		Advice lost = new Advice();
		lost.setDescribe(describe);
		lost.setPhone(photo);
		lost.setStar(star);
		lost.setShopname(eating.choose.getName());
		lost.setBelong(eating.choose);
		lost.save(new SaveListener<String>() {

			@Override
			public void done(String objectId, BmobException e) {
				if(e==null){
					Log.i("Advice","创建数据成功：" + objectId);
					finish();
				}else{
					Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
				}
			}
		});
	}
	



}
