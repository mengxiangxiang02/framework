package com.hession.hessianclient.RestController;

import com.hession.hessionapi.HelloWorldService;
import com.hession.hessionapi.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/11 14:20
 * @Description:
 */
@RestController
public class TestController {
    @Qualifier("helloClient")
    @Autowired
    HelloWorldService helloWorldService;
    @RequestMapping("/test")
    public String test(){
        long start=System.currentTimeMillis();
        Person person=new Person();
        person.setName("xx");
        person.setAge(10);
        String hello = helloWorldService.sayHello(person);
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        return hello;
    }
}
