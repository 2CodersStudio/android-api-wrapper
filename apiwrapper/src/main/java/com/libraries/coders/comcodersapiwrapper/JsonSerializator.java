package com.libraries.coders.comcodersapiwrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by alvaro on 12/2/18.
 */

public class JsonSerializator {
    private static JsonSerializator instance = null;
    private ObjectMapper mapper = null;

    protected JsonSerializator() {
        mapper = new ObjectMapper();
    }

    public static JsonSerializator getInstance() {
        if (instance == null) {
            instance = new JsonSerializator();
        }
        return instance;
    }

    public String toJson(Object item) throws JsonProcessingException {
        return mapper.writeValueAsString(item);
    }

    public Object toObject(String item, Class<?> to) throws IOException {
        return mapper.readValue(item, to);
    }
}

