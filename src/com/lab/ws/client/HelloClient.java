package com.lab.ws.client;

import com.lab.ws.HelloWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class HelloClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:1986/wss/hello?wsdl");
        QName qName = new QName("http://server.ws.lab.com/", "HelloWebServiceImplService");

        Service service = Service.create(url, qName);
        HelloWebService helloWebService = service.getPort(HelloWebService.class);

        System.out.println(helloWebService.sayHi("me"));
    }
}
