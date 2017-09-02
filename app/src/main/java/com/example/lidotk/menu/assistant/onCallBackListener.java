package com.example.lidotk.menu.assistant;

import com.example.lidotk.menu.Bean.Food;

/**
 * Created by lidotk on 2017/4/17.
 * 购物车添加接口回调
 */
public interface onCallBackListener {
    /**
     * Type表示添加或减少
     * @param product
     * @param type
     */
    void updateProduct(Food product, String type);
}
