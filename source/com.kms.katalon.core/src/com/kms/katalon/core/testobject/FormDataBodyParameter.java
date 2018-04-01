package com.kms.katalon.core.testobject;

public class FormDataBodyParameter {
public static final String PARAM_TYPE_TEXT = "Text";
    
    public static final String PARAM_TYPE_FILE = "File";

    private String name;
    
    private String value;
    
    private String type = PARAM_TYPE_TEXT;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
