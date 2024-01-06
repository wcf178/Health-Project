package com.bigdata.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.bigdata.dao.CheckItemDao;
import com.bigdata.pojo.CheckItem;
import com.bigdata.entity.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        this.checkItemDao.add(checkItem);

    }
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = this.checkItemDao.selectByCondition(queryString);
//        System.out.println(page.getPageSize());
//        System.out.println(page.getResult().size());
//        System.out.println(page.getTotal());
//        System.out.println(queryString);
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
//        System.out.println(pageResult.getRows());
        return pageResult;
    }



    @Override
    public void delete(Integer id){
        //查询当前检查项是否和检查组关联
        System.out.println("实现类层已被调用");
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查项被引用，不能删除");
        }
        checkItemDao.deleteById(id);
//        System.out.println("实现类层已被调用");
    }
    @Override
    public CheckItem findByID(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }
}
