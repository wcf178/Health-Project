package com.bigdata.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.constant.MessageConstant;
import com.bigdata.entity.PageResult;
import com.bigdata.entity.QueryPageBean;
import com.bigdata.entity.Result;
import com.bigdata.pojo.CheckItem;
import com.bigdata.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RequestMapping("/checkitem")
@RestController
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @RequestMapping("/add")
    public Result addItem(@RequestBody CheckItem checkItem) {
        System.out.println(checkItem);
        try{
            checkItemService.add(checkItem);
        }catch (Exception e){
            return new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);

    }
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
//        System.out.println(pageResult.getRows());
        return pageResult;
    }
    @RequestMapping("/delete")
    public Result delete(Integer id){

        try{
            checkItemService.delete(id);
        }
        catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }
        catch (Exception e){
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        Result result =new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
//        System.out.println("控制类层已被调用"+result.isFlag());
//        Result result = new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        return result;

    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            CheckItem checkItem=checkItemService.findByID(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkItem);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);

        }
    }
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try{
            checkItemService.edit(checkItem);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);

        }
        return  new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }


}
