package com.ttianjun.cxf;

import javax.jws.WebService;

/**
 * @user keeley
 * @date 16/3/1
 */
@WebService
public interface HelloService {
    String say(String name);
}