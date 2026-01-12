package com.cheq.demo_webshop.utils;

import java.io.FileReader;
import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Utility class for reading test data from JSON files.
 * Loads a JSON file once and provides access to values using logical keys.
 */
public class JSONReader {
    private JsonObject jsonObject;

    /**
     * Loads the specified JSON test data file from the test resources directory.
     *
     * @param filename the JSON file name containing test data
     */
    public JSONReader(String filename) {
        try {
            String path = System.getProperty("user.dir") + "/src/test/resources/testdata/" + filename;
            FileReader reader = new FileReader(path);
            jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON test data: " + e.getMessage());
        }
    }

    /**
     * Retrieves a value from the JSON file based on user type and key.
     *
     * @param userType the parent JSON object name
     * @param key the key within the user type object
     * @return the corresponding value as a string
     */
    public String getValue(String userType, String key) {
        return jsonObject.getAsJsonObject(userType).get(key).getAsString();
    }
}