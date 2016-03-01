package com.ttianjun.cxf;

/**
 * @user keeley
 * @date 16/3/1
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public  String say(String name) {
        return "123"+name;
    }
}
