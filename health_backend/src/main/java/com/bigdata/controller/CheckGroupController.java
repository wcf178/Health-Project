package com.bigdata.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.constant.MessageConstant;
import com.bigdata.entity.PageResult;
import com.bigdata.entity.QueryPageBean;
import com.bigdata.entity.Result;
import com.bigdata.pojo.CheckGroup;
import com.bigdata.pojo.CheckItem;
import com.bigdata.pojo.GroupToItem;
import com.bigdata.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static java.lang.Thread.sleep;

@RequestMapping("/checkgroup")
@RestController
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkGroupService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
//        System.out.println(pageResult.getRows());
        return pageResult;
    }

    //    查询所有的检查项目
    @RequestMapping("/findAllItems")
    public Result findAllItems() {
        List<CheckItem> checkItemList = checkGroupService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemList);
    }


    @RequestMapping("/add")
    public Result addGroup(@RequestBody CheckGroup checkGroup) {
        System.out.println(checkGroup.getCode());

        try {
            checkGroupService.add(checkGroup);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);

    }

    @RequestMapping("/additems")
    public void additems(@RequestBody GroupToItem groupToItem) throws InterruptedException {
        sleep(1000);
        System.out.println(groupToItem.getCode());
        System.out.println(groupToItem.getCheckItemId());
        groupToItem.printIds();
        checkGroupService.additems(groupToItem);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            checkGroupService.delete(id);
        } catch (RuntimeException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        Result result = new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
//        System.out.println("控制类层已被调用"+result.isFlag());
//        Result result = new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        return result;
    }

    @RequestMapping("/findGroupById")
    public Result findGroupById(Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findGroupByID(id);
            checkGroup.toString();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);

        }


    }

    /*
    @RequestMapping("/findItemsById")
    public Result findItemsById(Integer id) {
        try {
            int[] itemIds = checkGroupService.findItemsByID(id);
            System.out.println(itemIds);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, itemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);

        }

    }
    */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup){
        System.out.println("检查组code"+checkGroup.getCode());
        System.out.println("检查组ID"+checkGroup.getId());
        System.out.println(checkGroup.getCheckItems());
        try{
            checkGroupService.edit(checkGroup);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);

        }
        return  new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);

    }
}
