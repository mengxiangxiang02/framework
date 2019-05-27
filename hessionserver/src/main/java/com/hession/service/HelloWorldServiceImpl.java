package com.hession.service;

import com.hession.hessionapi.HelloWorldService;
import com.hession.hessionapi.bean.Person;
import org.springframework.stereotype.Service;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/11 14:00
 * @Description:hessionservice实现类
 */
@Service("HelloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService
{
    @Override
    public String sayHello(Person person) {
        return "Hello World! " + person;
    }
}
