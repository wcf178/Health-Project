package com.bigdata.dao;

import com.bigdata.pojo.CheckGroup;
import com.bigdata.pojo.CheckItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {
    Page<CheckGroup> selectByCondition(String queryString);

    List<CheckItem> findAll();

    void add(CheckGroup checkGroup);

    void add_m(@Param("groupid") Integer groupid, @Param("itemid") Integer itemid);

    int getGroupIdByCode(@Param("code") String Code);

    void deleteGroupById(@Param("id") Integer id);

    void deleteMidById(@Param("id") Integer id);

    void deleteRelation();
    void deleteRelation2();

    void recoverRelation();
    void recoverRelation2();

    CheckGroup getGroupById(@Param("id") Integer id);

    int[] getIdsById(@Param("id")Integer id);

    void editGroup(CheckGroup checkGroup);

    void editMid(CheckGroup checkGroup);

    List<CheckGroup> findCheckGroupById(Integer id);
}
