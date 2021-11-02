package voss.gui;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import voss.helpers.CommonMethods;

public class FillFormsEndToEnd extends CommonMethods {
    @BeforeMethod(alwaysRun = true)
    public void initializeTest() {
        initTest();
    }

    @Test
    public void executeTest() {
        try {
            proceedTest = navigateToHomePage();
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickLinkByText("Fill out forms"));
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextById("et_pb_contact_name_0", "Test Name 1"));
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextAreaById("et_pb_contact_message_0", "Test Message 1"));
            actions.takeScreenshot("fillForm1", conf.getImagePath());
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickButtonByNameAndIndex("et_builder_submit_button", 1));
            Assert.assertEquals(proceedTest, true);
            processForm2();
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickButtonByNameAndIndex("et_builder_submit_button", 1));
            Assert.assertEquals(proceedTest, true);
            actions.takeScreenshot("fillForm2", conf.getImagePath());
        } catch (Exception e) {
            System.err.println("Something went wrong while executing test - " + e.getMessage());
        }
    }

    private void processForm2() {
        try {
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextById("et_pb_contact_name_1", "Test Name 2"));
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextAreaById("et_pb_contact_message_1", "Test Message 2"));

            String captchaCalc = actions.getSpanText("et_pb_contact_captcha_question");
            String captchaResult = evaluateStringExpression(captchaCalc);
            proceedTest = proceedIfStepPassed(proceedTest, actions.enterTextByName("et_pb_contact_captcha_1", captchaResult));
            proceedTest = proceedIfStepPassed(proceedTest, actions.clickButtonByNameAndIndex("et_builder_submit_button", 2));
        } catch (Exception e) {
            System.err.println("Something went wrong while processing form 2 - " + e.getMessage());
        }
    }
}
