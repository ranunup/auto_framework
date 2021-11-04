package voss.gui;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import voss.helpers.CommonMethods;

public class FillFormsTest extends CommonMethods {
    @BeforeMethod(alwaysRun = true)
    public void initializeTest() {
        initTest("fillFormsTest");
    }

    @Test
    public void executeTest() {
        try {
            proceedTest = navigateToHomePage();
            actions.maximizeBrowser();
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickLinkByText(extentTest, "Fill out forms"));
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextById(extentTest, "et_pb_contact_name_0", "Test Name 1"));
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextAreaById(extentTest, "et_pb_contact_message_0", "Test Message 1"));

            proceedTest = proceedIfStepPassed(proceedTest, actions.clickButtonByNameAndIndex(extentTest, "et_builder_submit_button", 1));
            Assert.assertTrue(proceedTest);
            actions.takeScreenshot("fillForm1", conf.getReportPath());

            processForm2();
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickButtonByNameAndIndex(extentTest, "et_builder_submit_button", 1));
            Assert.assertTrue(proceedTest);
            actions.takeScreenshot("fillForm2", conf.getReportPath());
        } catch (Exception e) {
            System.err.println("Something went wrong while executing test - " + e.getMessage());
        }
    }

    private void processForm2() {
        try {
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextById(extentTest, "et_pb_contact_name_1", "Test Name 2"));
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextAreaById(extentTest, "et_pb_contact_message_1", "Test Message 2"));

            String captchaCalc = actions.getSpanText("et_pb_contact_captcha_question");
            String captchaResult = evaluateStringExpression(captchaCalc);
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextByName(extentTest, "et_pb_contact_captcha_1", captchaResult));
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickButtonByNameAndIndex(extentTest, "et_builder_submit_button", 2));
        } catch (Exception e) {
            System.err.println("Something went wrong while processing form 2 - " + e.getMessage());
        }
    }

    @AfterTest
    private void tearDown() {
        actions.shutDownDriver();
        reports.flush();
    }
}
