package voss.actions;

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import voss.helpers.ConfigManager;
import voss.helpers.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PageActions {
    private WebDriver driver;

    public void PageActions() {

    }

    public void startDriver(ConfigManager conf) {
        try {
            switch (conf.getBrowserType()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(setChromeOptions());
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.get(conf.getTestUrl());
                    break;
                default:
                    System.err.println("Failed to start driver, unknown driver type");

            }
            this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.err.println("Failed to start driver - " + e.getMessage());
        }
    }

    private ChromeOptions setChromeOptions() {
        ChromeOptions chromeOpts = new ChromeOptions();
        chromeOpts.setCapability("browserName", "chrome");
        chromeOpts.addArguments("--disable-notifications");
        chromeOpts.addArguments("enable-automation");
        chromeOpts.addArguments("--no-sandbox");
        chromeOpts.addArguments("--disable-infobars");
        chromeOpts.addArguments("--disable-dev-shm-usage");
        chromeOpts.addArguments("--disable-browser-side-navigation");
        chromeOpts.addArguments("--disable-gpu");
        return chromeOpts;
    }

    public boolean navigateToURL(String location, String expectedTitle) {
        driver.get(location);
        if (expectedTitle.equals(getPageTitle())) {
            System.out.printf("Successfully navigated to URL : " + location);
            return true;
        }
        return false;
    }

    public void maximizeBrowser() {
        driver.manage().window().maximize();
        System.out.println("Successfully maximized window");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean clickLinkByText(ExtentTest extentTest, String linkText) {
        try {
            WebElement element = driver.findElement(By.xpath("//a[normalize-space()='" + linkText + "'][1]"));
            element.click();
            System.out.println("Successfully clicked element by link text - " + linkText);
            extentTest.pass("click link by text - " + linkText);
            return true;
        } catch (Exception e) {
            System.err.println("Could not click link by text - " + e.getMessage());
        }

        extentTest.fail("click link by text - " + linkText);
        return false;
    }

    public boolean enterTextById(ExtentTest extentTest, String elemId, String inputText) {
        try {
            WebElement element = driver.findElement(By.xpath("//input[@id='" + elemId + "']"));
            element.clear();
            element.sendKeys(inputText);
            System.out.println("Successfully entered text - " + inputText + " into text box by ID - " + elemId);
            extentTest.pass("enter text - " + inputText + " by id - " + elemId);
            return true;
        } catch (Exception e) {
            System.err.println("Could not enter text by ID - " + e.getMessage());
        }
        extentTest.fail("enter text - " + inputText + " by id - " + elemId);
        return false;
    }

    public boolean enterTextByName(ExtentTest extentTest, String elemName, String inputText) {
        try {
            WebElement element = driver.findElement(By.xpath("//input[@name='" + elemName + "']"));
            element.clear();
            element.sendKeys(inputText);
            System.out.println("Successfully entered text - " + inputText + " into text box by name - " + elemName);
            extentTest.pass("enter text - " + inputText + " by name - " + elemName);
            return true;
        } catch (Exception e) {
            System.err.println("Could not enter text by name - " + e.getMessage());
        }
        extentTest.fail("enter text - " + inputText + " by name - " + elemName);
        return false;
    }

    public boolean enterTextAreaById(ExtentTest extentTest, String elemId, String inputText) {
        try {
            WebElement element = driver.findElement(By.xpath("//textarea[@id='" + elemId + "']"));
            element.clear();
            element.sendKeys(inputText);
            System.out.println("Successfully entered text - " + inputText + " into text area by ID - " + elemId);
            extentTest.pass("enter text - " + inputText + " into text area with id - " + elemId);
            return true;
        } catch (Exception e) {
            System.err.println("Could not enter into text area by ID - " + e.getMessage());
        }
        return false;
    }

    public boolean clickButtonByValue(ExtentTest extentTest, String buttonValue) {
        try {
            WebElement element = driver.findElement(By.xpath("//input[@type='submit' and @value='" + buttonValue + "']"));
            element.click();
            System.out.println("Successfully clicked button by value : " + buttonValue);
            extentTest.pass("click button by value - " + buttonValue);
            return true;
        } catch (Exception e) {
            System.err.println("Could not click button by value - " + e.getMessage());
        }

        extentTest.fail("click button by value - " + buttonValue);
        return false;
    }

    public boolean clickButtonByNameAndIndex(ExtentTest extentTest, String buttonName, int position) {
        try {

            WebElement element = driver.findElement(By.xpath("(//button[@type='submit' and @name='" + buttonName + "'])[" + position + "]"));
            element.click();
            System.out.println("Successfully clicked button by value : " + buttonName);
            extentTest.pass("click button by name - " + buttonName + " at position - " + position);
            return true;
        } catch (Exception e) {
            System.err.println("Could not click button by value - " + e.getMessage());
        }
        extentTest.fail("click button by name - " + buttonName + " at position - " + position);
        return false;
    }

    public boolean clickByHrefText(ExtentTest extentTest, String hrefText) {
        try {
            WebElement element = driver.findElement(By.xpath("//a[normalize-space()='" + hrefText + "'][1]"));
            element.click();
            System.out.println("Successfully clicked href by text - " + hrefText);
            extentTest.pass("click by href text - " + hrefText);
            return true;
        } catch (Exception e) {
            System.err.println("Could not click link by H3 text - " + e.getMessage());
        }
        extentTest.fail("click by href text - " + hrefText);
        return  false;
    }

    public boolean selectPackageByCategory(String category) {
        try {
            WebElement element = driver.findElement(By.xpath("//h4[normalize-space()='" + category + "']/parent::div/following-sibling::div/a[normalize-space()='Purchase']"));
            element.click();
            System.out.println("Successfully selected package by category - " + category);
            return true;
        } catch (Exception e) {
            System.err.println("Could not select package by category - " + e.getMessage());
        }
        return  false;
    }


    public String getSpanText(String spanClass) {
        try {
            return driver.findElement(By.xpath("//span[@class='" + spanClass + "']")).getText();
        } catch (Exception e) {
            System.err.println("Could not get span text - " + e.getMessage());
        }
        return null;
    }

    public List<String> getCaptchaEmbeddedText(String captchaClass) {
        try {
            List<WebElement> captchaList = driver.findElements(By.xpath("//img[contains(@class,'" + captchaClass + "')]"));

            File captchaImg;
            String imgName;
            String imgText;
            ITesseract image = new Tesseract();
            List<String> captchaTextList = new ArrayList<>();
            for (WebElement element : captchaList) {
                captchaImg = element.getScreenshotAs(OutputType.FILE);
                imgName = "src/images/" + Utils.getTodayDateWithTime("yyyyMMdd HH:mm:ss") + "_captcha.png";

                FileHandler.copy(captchaImg, new File(imgName));
                imgText = image.doOCR(new File(imgName));
                captchaTextList.add(imgText);
            }
            return captchaTextList;
        } catch (Exception e) {
            System.err.println("Could not get captcha embedded text - " + e.getMessage());
        }
        return null;
    }

    public boolean elementIDPresentOnPage(String elementId) {
        WebElement element = null;
        try {
            element = driver.findElement(By.xpath("//*[@id='" + elementId + "']"));
        } catch (Exception e) {
            System.err.println("Could not locate element by id " + elementId);
        }
        return element!=null;
    }

    public void takeScreenshot(String testStep, String filePath) {
        try {
            TakesScreenshot screenshot = ((TakesScreenshot)driver);
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            String newPath = System.getProperty("user.dir") + "/" + filePath + "/" + testStep +"_" + Utils.getTodayDateWithTime("yyyyMMdd") + ".png";
            File destFile = new File(newPath);
            FileUtils.copyFile(srcFile, destFile);
        } catch (Exception e) {
            System.err.println("Could not take screenshot - " + e.getMessage());
        }
    }

    public void shutDownDriver() {
        try {
            driver.quit();
            System.out.println("Successfully shut down driver");
        } catch (Exception e) {
            System.err.println("Could not shut down driver - " + e.getMessage());
        }
    }
}