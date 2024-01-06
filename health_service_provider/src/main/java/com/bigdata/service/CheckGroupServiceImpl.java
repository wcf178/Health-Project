package com.bigdata.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.dao.CheckGroupDao;
import com.bigdata.entity.PageResult;
import com.bigdata.pojo.CheckGroup;
import com.bigdata.pojo.CheckItem;
import com.bigdata.pojo.GroupToItem;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService{
    @Autowired
    private CheckGroupDao checkGroupDao;


    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = this.checkGroupDao.selectByCondition(queryString);
//        System.out.println(page.getPageSize());
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public List<CheckItem> findAll() {
        return checkGroupDao.findAll();
    }

    @Override
    public void add(CheckGroup checkGroup) {
        System.out.println(checkGroup.getCode());
        this.checkGroupDao.add(checkGroup);

    }

    @Override
    public void additems(GroupToItem groupToItem) {
        System.out.println(groupToItem.getCode());
        groupToItem.setCheckGroupId(this.checkGroupDao.getGroupIdByCode(groupToItem.getCode()));
        for (Integer itemid:
             groupToItem.getCheckItemId()) {
            this.checkGroupDao.add_m(groupToItem.getCheckGroupId(),itemid);
        }
    }

    @Override
    public void delete(Integer id) {
        checkGroupDao.deleteRelation();
        checkGroupDao.deleteRelation2();
        checkGroupDao.deleteGroupById(id);
        checkGroupDao.deleteMidById(id);
        checkGroupDao.recoverRelation();
        checkGroupDao.recoverRelation2();
    }

    @Override
    public CheckGroup findGroupByID(Integer id) {
        CheckGroup checkGroup=checkGroupDao.getGroupById(id);
        checkGroup.setItemIds(checkGroupDao.getIdsById(id));
        System.out.println(checkGroupDao.getIdsById(id));

        return checkGroup;
    }



    @Override
    public void edit(CheckGroup checkGroup) {
        System.out.println("检查组code"+checkGroup.getCode());
        System.out.println("检查组ID"+checkGroup.getId());
        System.out.println(checkGroup.getCheckItems());
        checkGroup.printIds();
        checkGroupDao.editGroup(checkGroup);
        checkGroupDao.deleteRelation();
        checkGroupDao.deleteMidById(checkGroup.getId());
        for(int itemId: checkGroup.getItemIds()) {
            checkGroupDao.add_m(checkGroup.getId(),itemId );
        }

        checkGroupDao.recoverRelation();

    }
}
