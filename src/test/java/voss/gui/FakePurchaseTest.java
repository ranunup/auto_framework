package voss.gui;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import voss.helpers.CommonMethods;

public class FakePurchaseTest extends CommonMethods {
    @BeforeMethod(alwaysRun = true)
    public void initializeTest() {
        initTest("fakePurchaseTest");
    }

    @Test
    public void executeTest() {
        proceedTest = navigateToHomePage();
        actions.maximizeBrowser();
        proceedTest = proceedIfStepPassed(proceedTest, actions.clickLinkByText(extentTest, "Fake Pricing Page"));
        actions.takeScreenshot("fakePricingPage", conf.getReportPath());

        proceedTest = proceedIfStepPassed(proceedTest, actions.selectPackageByCategory("Basic"));
        Assert.assertTrue(proceedTest);
        actions.takeScreenshot("basicPackage", conf.getReportPath());
    }

    @AfterTest
    private void tearDown() {
        actions.shutDownDriver();
        reports.flush();
    }
}
