package com.fh.model;

import java.util.List;
import java.util.Set;

public class aaa {

    /*public static void main(String[] args)
    {
        for (int i = 1; i <= 100; i += 1) {
            // 初始化CommentId索引 SortSet
            RedisClient.zadd("topicId", i, "commentId" + i);
            // 初始化Comment数据 Hash
            RedisClient.hset("Comment_Key", "commentId" + i, "comment content "+ i +" .......");
        }
        // 倒序取 从0条开始取 5条 Id 数据
        Set<String> sets = RedisClient.zrevrangebyscore("topicId", "100", "1", 0, 10);
        String[] items = new String[]{};
        System.out.println(sets.toString());
        // 根据id取comment数据
        List<String> list = RedisClient.hmget("Comment_Key", sets.toArray(items));
        for (String str : list) {
            System.out.println(str);
        }

    }*/

}
