package com.bigdata.dao;

import com.bigdata.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderSettingDao {

    List<OrderSetting> getOrders(@Param("date") String date);

    public long findCountByOrderDate(Date orderDate);
    public void add(OrderSetting orderSetting);
    public void editNumberByOrderDate(OrderSetting orderSetting);
}
