package voss.helpers;

import org.json.JSONObject;

public class ConfigManager {
    private String browserType;
    private String username;
    private String password;
    private String testUrl;
    private String reportPath;
    private String resourcePath;

    public String getBrowserType() {
        return browserType;
    }

    public String getTestUrl() {
        return testUrl;
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getReportPath() { return reportPath; }

    public String getResourcePath() { return resourcePath; }

    public void getConfigManager(JSONObject jsonObject) {
        browserType = jsonObject.getString("browserType");
        username = jsonObject.getString("ultiMeqaUName");
        password = jsonObject.getString("ultimeqaPass");
        testUrl = jsonObject.getString("testUrl");
        reportPath = jsonObject.getString("reportPath");
        resourcePath = jsonObject.getString("resourcePath");
    }
}
