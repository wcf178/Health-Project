package com.bigdata.service;

import com.bigdata.entity.PageResult;
import com.bigdata.pojo.CheckGroup;
import com.bigdata.pojo.CheckItem;
import com.bigdata.pojo.GroupToItem;

import java.util.List;

public interface CheckGroupService {

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    List<CheckItem> findAll();

    void add(CheckGroup checkGroup);

    void additems(GroupToItem groupToItem);

    void delete(Integer id);

    CheckGroup findGroupByID(Integer id);



    void edit(CheckGroup checkGroup);

}
