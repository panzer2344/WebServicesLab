package com.lab.ws.server;

import com.lab.ws.HelloWebService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.lab.ws.HelloWebService")
public class HelloWebServiceImpl implements HelloWebService {
    @Override
    public String sayHi(String to) {
        return "Hello, " + to;
    }
}
