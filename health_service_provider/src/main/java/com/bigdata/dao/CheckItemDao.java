package com.bigdata.dao;

import com.bigdata.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface CheckItemDao {



    public void add(CheckItem checkItem);

    Page<CheckItem> selectByCondition(String queryString);

    public long findCountByCheckItemId(Integer id);
    public void deleteById(Integer id);

    public CheckItem findById(Integer id);

    public void edit(CheckItem checkItem);

    List<CheckItem> findCheckItemById(Integer checkgroupId);
}
