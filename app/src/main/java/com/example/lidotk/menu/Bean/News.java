package com.example.lidotk.menu.Bean;


import android.graphics.Bitmap;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;


/**
 * Created by lidotk on 2017/4/17.
 */
public class News extends BmobObject{
     private String Name;
    private String Content;
    private String From;
    private String ShopName;
    private BmobFile pic;

    public String getTime(){
        return super.getCreatedAt().toString();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public BmobFile getPic() {
        return pic;
    }

    public void setPic(BmobFile Pic) {
        this.pic = Pic;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }
}
