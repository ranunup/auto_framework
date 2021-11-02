package voss.gui;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import voss.helpers.CommonMethods;

import java.util.List;

public class LoginEndToEnd extends CommonMethods {

    @BeforeMethod(alwaysRun = true)
    public void initializeTest() {
        initTest();
    }

    @Test
    public void executeTest() {
        try {
            proceedTest = navigateToHomePage();
            Assert.assertEquals(proceedTest, true);
            actions.maximizeBrowser();
            actions.takeScreenshot("navigateToHome", conf.getImagePath());
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickLinkByText("Login automation"));
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextById("user[email]", conf.getUsername()));
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextById("user[password]", conf.getPassword()));
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickButtonByValue("Sign in"));
            Assert.assertEquals(proceedTest, true);
            actions.takeScreenshot("signIn", conf.getImagePath());
            if(actions.elementIDPresentOnPage("rc-imageselect")) {
                List<String> captchaList = actions.getCaptchaEmbeddedText("rc-image-tile-");
                System.out.println("captchaList === > " + captchaList.toString());
            }
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickByHrefText("Phumlani R"));
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickByHrefText("Sign Out"));

        } catch (Exception e) {
            System.err.println("Something went wrong while executing test - " + e.getMessage());
        }
    }
}
