package com.lab.ws.server;

import com.lab.ws.server.service.LibraryAdminService;
import com.lab.ws.server.service.LibraryClientService;

import javax.xml.ws.Endpoint;

public class LibraryRunner {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:1986/ws/library/client", new LibraryClientService());
        Endpoint.publish("http://localhost:1986/ws/library/admin", new LibraryAdminService());
    }

}
