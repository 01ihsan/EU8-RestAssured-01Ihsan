package com.cydeo.Utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {

    public Map<String, Object> getNewSpartan() {
        Map<String, Object> newSpartan = new LinkedHashMap<>();
        newSpartan.put("gender","Female");
        newSpartan.put("name","John Doe");
        newSpartan.put("phone",1234567890);
        return newSpartan;
    }
}
