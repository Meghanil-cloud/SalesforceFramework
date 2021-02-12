package com.salesforce.qa.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import sun.tools.java.Constants;

import java.util.HashMap;
import java.util.logging.Logger;

@Listeners(com.salesforce.listeners.TestListener.class)
public class TestBase {
    public static CommonUtilities oCommUtil = new CommonUtilities();
    public static BrowserUtilities oBroUti = new BrowserUtilities();
    public static Constants oCons = new Constants();
    public static ExtentManager oExt = new ExtentManager();
    Logger log = Logger.getLogger(getClass().getSimpleName());
    public static HashMap<String, String> sMapAuthorizationHeader = new HashMap<String, String>();
    public static WebDriver driver;
    public static String sHost;
    public static String sScreenShotName;
    public static String sClassNameForScreenShot;
    public static String sErrorMessage="";

    @BeforeSuite
    public void TriggerDependencies() throws Exception {

        oCommUtil.deleteScreenShotDirectory();
        oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
        oCommUtil.loadLog4jProperty(System.getProperty("user.dir") + "/src/main/java/properties/log4j.properties");
        oCommUtil.loadPropertyFiles(System.getProperty("user.dir") + "/src/main/java/properties/testdata.properties");
        oCommUtil.deleteCreateScreenShotDirectorySureFireReports();


		if(System.getProperty("Environment") == null || System.getProperty("Environment").isEmpty())
            System.setProperty("Environment", System.getProperty("EnvironmentConf"));

        log.info(System.getProperty("AutomationRunning"));
        log.info(System.getProperty("Environment"));

        if(System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.sAutomationAPI))
        {
            if(System.getProperty("Environment").equalsIgnoreCase("staging"))
                sHost=System.getProperty("StageHost"); //StageHost
            else
                throw new Exception("Environment is not found OR Give the RIGHT Environment");
            System.out.println(sHost);
            //oResUtil.loginToCowrksAndGenerateToken();

        }
        if(System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.sAutomationWeb.toLowerCase()))
        {
            oBroUti.launchBrowser(System.getProperty("Browser"));

        }

    }

    @AfterSuite
    public void ShuttingDownAllDependencies() throws Exception {
        if (System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.sAutomationWeb.toLowerCase())) {
            driver.quit();
        }
    }
}

