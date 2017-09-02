package com.example.lidotk.menu.Bean;



import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by lidotk on 2017/4/17.
 */
public class Food extends BmobObject {

    private String Price;//单价
    private String Name;//货物名称
    private BmobFile Picture;//货物图片
    private String Type;//货物类型
    private String Belong;//店名
    private Integer number=0;


    public String getObjectId(){
        return super.getObjectId();
    }


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public BmobFile getPicture() {
        return Picture;
    }

    public void setPicture(BmobFile picture) {
        this.Picture = picture;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public int getNumber() {
        return  number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBelong() {
        return Belong;
    }

    public void setBelong(String belong) {
        Belong = belong;
    }
}
