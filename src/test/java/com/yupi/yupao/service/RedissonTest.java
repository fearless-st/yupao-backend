package com.yupi.yupao.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedissonTest {
    @Resource
    private RedissonClient redissonClient;

    @Test
    void test(){
        //List。数据存在本地JVM内存中
        ArrayList<Object> list = new ArrayList<>();
        list.add("yupi");
        System.out.println("list: "+list.get(0));
//        list.remove(0);

        //数据存在redis的内存中
        RList<Object> rList = redissonClient.getList("test-list");
//        rList.add("yupi");
        System.out.println("rList: "+rList.get(0));

        rList.remove(0);
    }

    @Test
    void testWatchDog(){
        RLock lock = redissonClient.getLock("yupao:precachejob:docache:lock");
        try{
            //只有一个线程能获取到锁
            if (lock.tryLock(0,-1, TimeUnit.MILLISECONDS)){
                Thread.sleep(300000);
                System.out.println("getLock: "+Thread.currentThread().getId());
            }
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }finally {
            //只能释放自己的锁
            if (lock.isHeldByCurrentThread()){
                System.out.println("unlock: "+Thread.currentThread().getId());
                lock.unlock();
            }
        }
    }

}
