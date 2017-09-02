package com.example.lidotk.menu.Bean;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by lidotk on 2017/4/17.
 */
public class Dinninghall extends BmobObject {
    private BmobFile Pic;
    private String Name;
    private String Position;
    private String Info;
    private List<String> FoodType;
    private Float Rank=0f;


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }


    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public Float getRank() {
        return Rank;
    }

    public void setRank(Float Rank) {
        this.Rank = Rank;
    }

    public BmobFile getPic() {
        return Pic;
    }

    public void setPic(BmobFile Pic) {
        this.Pic = Pic;
    }

    public List<String> getFoodType() {
        return FoodType;
    }

    public void setFoodType(List<String> foodType) {
        FoodType = foodType;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }
}
