package com.bigdata.service;

import com.bigdata.entity.PageResult;
import com.bigdata.pojo.CheckGroup;
import com.bigdata.pojo.Setmeal;

import java.util.List;

public interface SetMealService {
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    List<CheckGroup> findAllGroups();

    void add(Setmeal setmeal, Integer[] ids) throws InterruptedException;

    List<Integer> findCheckGroupIdsBySetMealId(Integer id);

    void edit(Setmeal setmeal, Integer[] id);

    void deleteById(Integer id);

    List<Setmeal> findAll();

    Setmeal findById(int id);
}
