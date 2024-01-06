package com.bigdata.dao;

import com.bigdata.pojo.CheckGroup;
import com.bigdata.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SetMealDao {
    Page<Setmeal> selectByCondition(String queryString);

    List<CheckGroup> findAllGroups();

    void add(Setmeal setmeal);

    void setSetMealAndCheckGroup(Map<String, Object> map);

    List<Integer> findCheckGroupIdsBySetMealId(Integer id);

    void edit(Setmeal setmeal);

    void deleteAssociation(Integer id);

    void deleteRelation();

    void deleteSetMealById(Integer id);

    void recoverRelation();

    List<Setmeal> findAll();

    Setmeal findById(int id);
}
