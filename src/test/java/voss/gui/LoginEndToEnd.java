package voss.gui;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import voss.helpers.CommonMethods;

import java.util.List;

public class LoginEndToEnd extends CommonMethods {

    @BeforeMethod(alwaysRun = true)
    public void initializeTest() {
        initTest("loginEndToEnd");
    }

    @Test
    public void executeTest() {
        try {
            proceedTest = navigateToHomePage();
            Assert.assertTrue(proceedTest);

            actions.maximizeBrowser();
            actions.takeScreenshot("navigateToHome", conf.getReportPath());
            logSuccessExtentStep("maximize browser");

            proceedTest = proceedIfStepPassed(proceedTest, actions.clickLinkByText(extentTest, "Login automation"));

            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextById(extentTest, "user[email]", conf.getUsername()));

            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextById(extentTest, "user[password]", conf.getPassword()));

            proceedTest = proceedIfStepPassed(proceedTest, actions.clickButtonByValue(extentTest, "Sign in"));
            Assert.assertTrue(proceedTest);
            actions.takeScreenshot("signIn", conf.getReportPath());
            logSuccessExtentStep("sign in");

            if(actions.elementIDPresentOnPage("rc-imageselect")) {
                List<String> captchaList = actions.getCaptchaEmbeddedText("rc-image-tile-");
                System.out.println("captchaList === > " + captchaList.toString());
            }

            proceedTest = proceedIfStepPassed(proceedTest, actions.clickByHrefText(extentTest, "Phumlani R"));
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickByHrefText(extentTest, "Sign Out"));

        } catch (Exception e) {
            System.err.println("Something went wrong while executing test - " + e.getMessage());
        }
    }

    @AfterTest
    private void tearDown() {
        actions.shutDownDriver();
        reports.flush();
    }
}
