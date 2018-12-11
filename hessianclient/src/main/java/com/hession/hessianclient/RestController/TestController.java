package com.hession.hessianclient.RestController;

import com.hession.demo.Person;
import com.hession.service.HelloWorldService;
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
        Person person=new Person();
        person.setName("xx");
        person.setAge(10);
        return helloWorldService.sayHello(person);

    }
}
