package com.example.fourcomp.web;

import org.springframework.web.servlet.handler.AbstractDetectingUrlHandlerMapping;

public class MyHandlerMapping extends AbstractDetectingUrlHandlerMapping {

    @Override
    protected String[] determineUrlsForHandler(String controllerID) {
        String[] mapping = null;
        if (controllerID.equals("helloController")) {
            mapping = new String[2];
            mapping[0] = "/hello";
            mapping[1] = "/bye";

        }
        return mapping;
    }

}