package com.buba.gateway.catutils;

import com.dianping.cat.Cat;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Cat上下文信息配置
 */
public class CatContext implements Cat.Context{

    //存储链路监控相关信息
    private Map<String,String> properties = new HashMap<>();

    @Override
    public void addProperty(String s, String s1) {
        properties.put(s,s1);
    }

    @Override
    public String getProperty(String s) {
        return properties.get(s);
    }
}
