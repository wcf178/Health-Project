package com.bigdata.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.dao.OrderSettingDao;
import com.bigdata.pojo.OrderSetting;
import com.bigdata.pojo.OrderSettingforWeb;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService{
    @Autowired
    private OrderSettingDao orderSettingDao;
    private Calendar calendar=Calendar.getInstance();


    @Override
    public List<OrderSettingforWeb> getOrdersByDate(String date) {
        System.out.println("服务实现层");
        List<OrderSetting> orderSettings = orderSettingDao.getOrders(date);
        List<OrderSettingforWeb> orderSettingforWebs = new ArrayList<OrderSettingforWeb>();
        System.out.println(orderSettings.size());
        for(int i=0;i<orderSettings.size();i++){
            calendar.setTime(orderSettings.get(i).getOrderDate());
            OrderSettingforWeb order =new OrderSettingforWeb(calendar.get(Calendar.DAY_OF_MONTH),orderSettings.get(i).getNumber(),orderSettings.get(i).getReservations());
            orderSettingforWebs.add(order);
        }
        return orderSettingforWebs;
    }

    @Override
    public void add(List<OrderSetting> list) {
        if(list != null && list.size() > 0){
            for (OrderSetting orderSetting : list) {
                //检查此数据（日期）是否存在
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if(count > 0){
                    //已经存在，执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else{
                    //不存在，执行添加操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    //根据日期修改可预约人数
    public void editNumberByDate(OrderSetting orderSetting) {

        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count > 0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }

    private String  yearAndMonth(Integer year, Integer month){
        if(month>9) {
            return new String(year + "-" + month);
        }
        else {
            return new String (year+"-0"+month);
        }

    }

//    public static void main(String[] args) {
//        System.out.println(yearAndMonth(2003,7));
//    }

}
