package com.bigdata.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.dao.CheckGroupDao;
import com.bigdata.dao.CheckItemDao;
import com.bigdata.dao.SetMealDao;
import com.bigdata.entity.PageResult;
import com.bigdata.pojo.CheckGroup;
import com.bigdata.pojo.CheckItem;
import com.bigdata.pojo.Setmeal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

@Service(interfaceClass = SetMealService.class)
public class SetMealServiceImpl implements SetMealService{
    @Autowired
    private SetMealDao setMealDao;
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = this.setMealDao.selectByCondition(queryString);
//        System.out.println(page.getPageSize());
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public List<CheckGroup> findAllGroups() {
        return setMealDao.findAllGroups();
    }

    @Override
    public void add(Setmeal setmeal, Integer[] ids){
        this.setMealDao.add(setmeal);
        //中间表t_checkgroup_checkitem
        //检查组的id 检查项id(前台页面已经传递过来)
        System.out.println(setmeal.getId());

        System.out.println(setmeal.getId());
        setSetMealAndCheckGroup(setmeal.getId(),ids);
    }

    @Override
    public List<Integer> findCheckGroupIdsBySetMealId(Integer id) {
        return this.setMealDao.findCheckGroupIdsBySetMealId(id);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] id) {
        //更新检查组表t_checkgroup
        this.setMealDao.edit(setmeal);

        //中间表（先删后加）
        this.setMealDao.deleteAssociation(setmeal.getId());
        setSetMealAndCheckGroup(setmeal.getId(),id);
    }

    @Override
    public void deleteById(Integer id) {
        this.setMealDao.deleteRelation();
        this.setMealDao.deleteSetMealById(id);
        this.setMealDao.deleteAssociation(id);
        this.setMealDao.recoverRelation();
    }

    @Override
    public List<Setmeal> findAll() {
        return setMealDao.findAll();
    }

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public Setmeal findById(int id) {
        Setmeal setmeal = setMealDao.findById(id);
        List<CheckGroup> checkGroupList=checkGroupDao.findCheckGroupById(setmeal.getId());
        for(CheckGroup checkGroup:checkGroupList){
            Integer checkgroupId = checkGroup.getId();
            List<CheckItem>  checkItemList=checkItemDao.findCheckItemById(checkgroupId);
            checkGroup.setCheckItems(checkItemList);
        }
        setmeal.setCheckGroups(checkGroupList);
        return setmeal;
    }

    private void setSetMealAndCheckGroup(Integer id, Integer[] ids) {
        //判断检查项是否被选中了
        if(ids!=null&&ids.length>0){
            for(int i=0;i<ids.length;i++){
                Map<String,Object> map=new HashMap();
                map.put("setmeal_id",id);//检查组
                map.put("checkgroup_id",ids[i]);//检查项
                this.setMealDao.setSetMealAndCheckGroup(map);
            }

        }



    }
}
