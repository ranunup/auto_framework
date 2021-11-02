package voss.helpers;

import org.json.JSONObject;
import org.json.JSONTokener;
import voss.actions.PageActions;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileReader;

public abstract class CommonMethods {
    protected PageActions actions = new PageActions();
    protected ConfigManager conf = new ConfigManager();
    protected boolean proceedTest = false;

    // does the necessary steps to initialize a test
    protected void initTest() {
        JSONObject jsonObject = readJSONFile("src/main/resources/config.json");
        conf.getConfigManager(jsonObject);
        actions.startDriver(conf.getBrowserType());
    }

    protected boolean navigateToHomePage() {
        return actions.navigateToURL(conf.getTestUrl(), "Automation Practice | Ultimate QA");
    }

    protected JSONObject readJSONFile(String filePath) {
        File file = new File(filePath);
        try (FileReader reader = new FileReader(file.getAbsolutePath())){
            JSONTokener jsonTokener = new JSONTokener(reader);
            return new JSONObject(jsonTokener);
        } catch (Exception e) {
            System.err.println("Could not read JSON file - " + e.getMessage());
        }
        return null;
    }

    protected boolean proceedIfStepPassed(boolean prevStepSuccess, boolean currentStepSuccess) {
        if(prevStepSuccess) {
            return currentStepSuccess;
        }
        return false;
    }

    protected String evaluateStringExpression(String expression) {
        try {
            ScriptEngineManager managaer = new ScriptEngineManager();
            ScriptEngine engine = managaer.getEngineByName("JavaScript");
            return engine.eval(expression).toString();
        } catch (Exception e) {
            System.err.println("Could not evaluate string expression - " + e.getMessage());
        }
        return null;
    }
}
