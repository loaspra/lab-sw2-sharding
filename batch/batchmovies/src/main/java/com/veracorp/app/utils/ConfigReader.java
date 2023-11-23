package com.veracorp.app.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConfigReader {
    private String configFilePath;

    public ConfigReader(String configFilePath) {
        this.configFilePath = configFilePath;
    }


    public String[] getUris() throws Exception {
        String configContent = new String(Files.readAllBytes(Paths.get(configFilePath)));

        JSONObject configJson = new JSONObject(configContent);

        JSONArray uriArray = configJson.getJSONArray("uris");

        String[] uris = new String[uriArray.length()];
        for (int i = 0; i < uriArray.length(); i++) {
            uris[i] = uriArray.getString(i);
        }

        return uris;
    }
}
