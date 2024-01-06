package com.bigdata.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.utils.RedisUtils;
import com.bigdata.dao.RedisTestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;

@Transactional
@Service(interfaceClass = RedisTest.class)
public class RedisTestImpl implements RedisTest{
    private Jedis jedis = RedisUtils.getJedis();
    @Autowired
    private RedisTestDao mysqlTestDao;

    @Override
    public List<String> getProvinceList() {
        System.out.println("\n传到这里了...\n");
        List<String> list = jedis.lrange("province",0,-1);
        if(list.size()==0){
            List<String> sqlist = mysqlTestDao.getProvinceName();
            System.out.println(sqlist+"\nsss\n");
            for(String s:sqlist){
                jedis.rpush("province",s);
            }
            list = jedis.lrange("province",0,-1);
            return list;
        }else return list;
    }
}
