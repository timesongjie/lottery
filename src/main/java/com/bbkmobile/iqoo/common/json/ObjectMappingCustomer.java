package com.bbkmobile.iqoo.common.json;  

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
  
public class ObjectMappingCustomer extends ObjectMapper{
    public ObjectMappingCustomer()  
    {  
        super();  
        this.getSerializationConfig().disable(
                Feature.WRITE_NULL_PROPERTIES);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.getSerializationConfig().setDateFormat(df );
    }  
}
