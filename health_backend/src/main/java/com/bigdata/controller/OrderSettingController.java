package com.bigdata.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.constant.MessageConstant;
import com.bigdata.entity.Result;
import com.bigdata.pojo.DateT;
import com.bigdata.pojo.OrderSetting;
import com.bigdata.pojo.OrderSettingforWeb;
import com.bigdata.service.OrderSettingService;
import com.bigdata.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RequestMapping("ordersetting")
@RestController
public class OrderSettingController {
    @Reference
    private  OrderSettingService orderSettingService;

    @RequestMapping("/getReservation")
    public Result getReservation(@RequestBody DateT date){
        System.out.println(date.getDate());
        String year_month=date.getDate().substring(0,7);
        List<OrderSettingforWeb> orderSettings ;

        orderSettings = orderSettingService.getOrdersByDate(year_month);


        return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,orderSettings);


    }
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){
        try {
            //读取Excel文件数据
            List<String[]> list = POIUtils.readExcel(excelFile);
            if(list != null && list.size() > 0){
                List<OrderSetting> orderSettingList = new ArrayList<>();
                for (String[] strings : list) {
                    OrderSetting orderSetting =
                            new OrderSetting(new Date(strings[0]), Integer.parseInt(strings[1]));
                    orderSettingList.add(orderSetting);
                }
                orderSettingService.add(orderSettingList);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try{
            orderSettingService.editNumberByDate(orderSetting);
            if(orderSetting.getNumber() > orderSetting.getReservations()){
                return new Result(false,MessageConstant.ORDERSETTING_FAIL);
            }
            //预约设置成功
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //预约设置失败
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }



}
