package com.bbkmobile.iqoo.common.json;  

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
  
public class ObjectMappingCustomer extends ObjectMapper{
    public ObjectMappingCustomer()  
    {  
        super();  
        this.getSerializationConfig().disable(
                Feature.WRITE_NULL_PROPERTIES);
    }  
}
