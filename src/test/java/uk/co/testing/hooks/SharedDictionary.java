package uk.co.testing.hooks;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class SharedDictionary {

    private final Map<String,Object> sharedMap = new HashMap<String,Object>();
    private WebDriver driver;

    public void addDict(String key, Object value){
        sharedMap.put(key,value);
    }

    public Object readDict(String key){
        return sharedMap.get(key);
    }

    public boolean containsKey(String key){
        return sharedMap.containsKey(key);
    }
    public WebDriver getDriver() { return driver;}

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }
}