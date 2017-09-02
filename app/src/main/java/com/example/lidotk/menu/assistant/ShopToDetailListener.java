package com.example.lidotk.menu.assistant;


import com.example.lidotk.menu.Bean.Food;

/**
 * Created by 曹博 on 2016/6/7.
 * 购物车添加接口回调
 */
public interface ShopToDetailListener {
    /**
     * Type表示添加或减少
     * @param product
     * @param type
     */
    void onUpdateDetailList(Food product, String type);

    void onRemovePriduct(Food product);
}
