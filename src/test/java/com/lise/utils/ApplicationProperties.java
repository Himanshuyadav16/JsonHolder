package com.lise.utils;

import java.util.Properties;

public enum ApplicationProperties {
    INSTANCE;
    public Properties properties;

    ApplicationProperties(){
        try{
            properties=new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public String getUrl(){
        return properties.getProperty("url");
    }
}