package com.automation.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class JSONDataReader {
    public static List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //JSON TO STRING //commons.io dependency
        String jsonContent= FileUtils.readFileToString(new File(filePath),"UTF-8");

        //json string to hash map //jackson databind dependency
        ObjectMapper mapper=new ObjectMapper();

        //the list contains 2 hash maps means 2 data sets
        return mapper.readValue(jsonContent, new TypeReference<>() {
        });
    }
}
