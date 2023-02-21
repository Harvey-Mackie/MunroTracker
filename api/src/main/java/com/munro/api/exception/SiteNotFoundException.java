package com.munro.api.exception;

public class SiteNotFoundException extends RuntimeException{
    public SiteNotFoundException(String siteName) {
        super("Site not found: " + siteName);
    }
}
