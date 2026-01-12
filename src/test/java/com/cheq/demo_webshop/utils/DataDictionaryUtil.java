package com.cheq.demo_webshop.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for loading, caching, and retrieving test data from JSON files.
 * Uses an in-memory cache to avoid repeated file reads during execution.
 */
public class DataDictionaryUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Map<String, JsonNode> dataCache = new HashMap<>();

    /**
     * Loads a JSON file from the given path and stores its parsed content in cache.
     * If the file is already cached, it will not be reloaded.
     *
     * @param filePath the relative path of the JSON file
     * @throws IOException if the file cannot be read or parsed
     */
    public static void loadJsonFile(String filePath) throws IOException {
        if (!dataCache.containsKey(filePath)) {
            File file = new File(System.getProperty("user.dir") + filePath);
            JsonNode rootNode = objectMapper.readTree(file);
            dataCache.put(filePath, rootNode);
        }
    }

    /**
     * Retrieves a specific data group node from a cached JSON file.
     * Automatically loads the file if it has not been cached yet.
     *
     * @param filePath the relative path of the JSON file
     * @param dataGroup the top-level key to retrieve from the JSON
     * @return the JsonNode representing the data group, or null if not found
     * @throws IOException if the file cannot be loaded
     */
    public static JsonNode getDataNode(String filePath, String dataGroup) throws IOException {

        JsonNode rootNode = dataCache.get(filePath);
        if (rootNode == null) {
            loadJsonFile(filePath);
            rootNode = dataCache.get(filePath);
        }

        JsonNode dataGroupNode = rootNode.path(dataGroup);
        if (dataGroupNode.isMissingNode()) {
            return null;
        }

        return dataGroupNode;
    }

    /**
     * Clears all cached JSON data.
     * Intended to prevent stale data between test scenarios.
     */
    public static void clearDataCache() {
        dataCache.clear();
    }
}