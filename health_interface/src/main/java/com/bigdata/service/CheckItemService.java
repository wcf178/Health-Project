package com.bigdata.service;

import com.bigdata.entity.PageResult;
import com.bigdata.pojo.CheckItem;

public interface CheckItemService {
    public void delete(Integer id);

    public void add(CheckItem checkItem);

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    public CheckItem findByID(Integer id);

    public void edit(CheckItem checkItem);
}
