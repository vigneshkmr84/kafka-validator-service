package org.project.kafka.validatorservice.utils;

import com.google.gson.Gson;
import org.project.kafka.validatorservice.model.Order;

import java.util.Map;

public class JsonParser {

    Gson gson = new Gson();

    public Order parse(String msg) {
        return gson.fromJson(msg, Order.class);
    }


    public String objectToString(Order obj) {
        return gson.toJson(obj);
    }

    public String mapToString(Map<String, String> map) {
        return gson.toJson(map);
    }

}
