package com.almod.flow.soap;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService(serviceName = "Hello")
public class Hello {

    @WebMethod(operationName = "hello")
    public String hello(String name) {
        return "Hello " + name;
    }
}