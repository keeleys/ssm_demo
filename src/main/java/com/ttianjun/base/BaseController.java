package com.ttianjun.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @description 
 * @author Joseph_Mok
 * @date 2016年1月21日下午2:43:25
 */
public class BaseController{
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    public String renderTemp(String path){
        return "/template/"+path;
    }
}
