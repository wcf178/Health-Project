package com.bigdata.service;

import com.bigdata.pojo.OrderSetting;
import com.bigdata.pojo.OrderSettingforWeb;

import java.util.List;

public interface OrderSettingService {
    List<OrderSettingforWeb> getOrdersByDate(String date);
    public void add(List<OrderSetting> list);

    void editNumberByDate(OrderSetting orderSetting);
}

