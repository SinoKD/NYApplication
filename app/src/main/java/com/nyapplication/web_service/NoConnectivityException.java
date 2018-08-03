package com.nyapplication.web_service;

import java.io.IOException;

public class NoConnectivityException extends IOException {
 
    @Override
    public String getMessage() {
        return "No connectivity exception";
    }
 
}