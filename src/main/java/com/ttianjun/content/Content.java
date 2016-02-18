package com.ttianjun.content;

import com.ttianjun.common.kit.Prop;
import com.ttianjun.common.kit.PropKit;

import java.util.HashMap;

/**
 * @user keeley
 * @date 16/2/1
 */
public class Content {
    public static final Prop prop =PropKit.use("conf.txt");


    public static final String COOKIE2 = prop.get("cookie2");



}
