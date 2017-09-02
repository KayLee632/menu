package com.example.lidotk.menu.Bean;

import java.util.Date;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by lidotk on 2017/4/17.
 */
public class Advice extends BmobObject{
    private Float Star ;
    private String Describe;
    private String phone;
    private String Shopname;
    private Dinninghall Belong;
    private BmobFile Pic;

    public String getTime(){
        return super.getCreatedAt().toString();
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BmobFile getPic() {
        return Pic;
    }

    public void setPic(BmobFile pic) {
        this.Pic = pic;
    }

    public String getShopname() {
        return Shopname;
    }

    public void setShopname(String shopname) {
        Shopname = shopname;
    }

    public Float getStar() {
        return Star;
    }

    public void setStar(Float star) {
        Star = star;
    }

    public Dinninghall getBelong() {
        return Belong;
    }

    public void setBelong(Dinninghall choose) {
        Belong = choose;
    }
}
