package net.pureessence.example;


import org.apache.commons.logging.Log;

import java.util.ArrayList;
import java.util.List;

public class TestLogger implements Log {
    private List<String> infoMessages = new ArrayList<String>();
    public boolean isDebugEnabled() {
        return false;  
    }

    public boolean isErrorEnabled() {
        return false;  
    }

    public boolean isFatalEnabled() {
        return false;  
    }

    public boolean isInfoEnabled() {
        return false;  
    }

    public boolean isTraceEnabled() {
        return false;  
    }

    public boolean isWarnEnabled() {
        return false;  
    }

    public void trace(Object o) {
        
    }

    public void trace(Object o, Throwable throwable) {
        
    }

    public void debug(Object o) {
        
    }

    public void debug(Object o, Throwable throwable) {
        
    }

    public void info(Object o) {
        if(o instanceof String) {
            infoMessages.add((String)o);
        }
    }

    public void info(Object o, Throwable throwable) {
        if(o instanceof String) {
            infoMessages.add((String)o);
        }
    }

    public void warn(Object o) {
        
    }

    public void warn(Object o, Throwable throwable) {
        
    }

    public void error(Object o) {
        
    }

    public void error(Object o, Throwable throwable) {
        
    }

    public void fatal(Object o) {
        
    }

    public void fatal(Object o, Throwable throwable) {
        
    }

    public List<String> getInfoMessages() {
        return infoMessages;
    }
}
