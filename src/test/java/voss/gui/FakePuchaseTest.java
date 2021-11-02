package voss.gui;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import voss.helpers.CommonMethods;

public class FakePuchaseTest extends CommonMethods {
    @BeforeMethod(alwaysRun = true)
    public void initializeTest() {
        initTest();
    }

    @Test
    public void executeTest() {
        proceedTest = navigateToHomePage();
        proceedTest = proceedIfStepPassed(proceedTest, actions.clickLinkByText("Fake Pricing Page"));
        proceedTest = proceedIfStepPassed(proceedTest, actions.selectPackageByCategory("Basic"));
        Assert.assertEquals(proceedTest, true);
        actions.takeScreenshot("basicPackage", conf.getImagePath());
    }
}
