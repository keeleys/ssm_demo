package com.ttianjun.cxf;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @user keeley
 * @date 16/3/1
 */
public class Test {
    public static void SpringClient() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-cxf.xml");
        HelloService helloService = context.getBean("helloWorld", HelloService.class);
        String result = helloService.say("SpringClient");
        System.out.println(result);
    }
    public static void JaxWsClient(String address) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress(address);
        factory.setServiceClass(HelloService.class);
        HelloService helloService = factory.create(HelloService.class);
        String result = helloService.say("JaxWsClient");
        System.out.println(result);
    }
    public static void main(String[]args){
        JaxWsClient("http://localhost:8080/ws/hello");
    }
}
