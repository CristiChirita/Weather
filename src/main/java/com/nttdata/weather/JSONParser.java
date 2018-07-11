package com.nttdata.weather;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JSONParser {
    public List<WeatherBean> parseJSON(String in){
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JavaType type = mapper.getTypeFactory().
                    constructCollectionType(List.class, WeatherBean.class);
            List<WeatherBean> bean = mapper.readValue(in, type);
            return bean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
