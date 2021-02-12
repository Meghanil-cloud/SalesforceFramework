package com.salesforce.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;

    public TestBase() {

        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("/Users/Admin/IdeaProjects/SalesforceFramework/src/main/java/com/salesforce/qa/config/config.properties");
            prop.load(ip);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void intialization() {

        String browserName = prop.getProperty("browser");

        if(browserName.equals("chrome")) {
            //System.setProperty("webdriver.chrome.driver", "/Users/Admin/Downloads/chromedriver_win32");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Documents\\Megha Java Classes\\Software\\chromedriver_win32 (1)\\chromedriver.exe");
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));
    }
}
