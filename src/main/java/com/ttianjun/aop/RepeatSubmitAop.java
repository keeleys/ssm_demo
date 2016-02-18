package com.ttianjun.aop;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description 
 * @author Joseph_Mok
 * @date 2016年1月20日下午5:20:14
 */

@Aspect
@Component
public class RepeatSubmitAop {

	
	@Pointcut("@annotation(com.ttianjun.annotation.TokenToView)")
    private void tokenToView(){}//定义一个切入点  
      
	@Pointcut("@annotation(com.ttianjun.annotation.CheckRepeat)")
    private void checkRepeat(){}//定义一个切入点  
	
    @Before(value="tokenToView()")  
    public void generateToken(JoinPoint joinPoint) {  
    	HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
    	request.setAttribute("token", UUID.randomUUID());
    	request.getSession().setAttribute("tt", UUID.randomUUID());
    }  
    
    @Before(value="checkRepeat()")  
    public void checkRepeatSubmit(JoinPoint joinPoint) {  
    	HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[1];
		String token = request.getParameter("request");

		if (StringUtils.isBlank(token)){
    		System.out.println("bad request!");
    	} else {
    		if (token.equals(request.getAttribute("token"))) {
    			System.out.println("db op");
    		} else {
    			System.out.println("repeat!");
    		}
    	}
    } 
}
