package com.lab.ws.server;

import javax.xml.ws.Endpoint;

public class HelloServerRunner {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:1986/wss/hello", new HelloWebServiceImpl());
    }
}
