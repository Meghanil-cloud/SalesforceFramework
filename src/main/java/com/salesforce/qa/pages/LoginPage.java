package com.salesforce.qa.pages;

import com.salesforce.qa.base.TestBase;
import com.salesforce.qa.util.browserutilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class LoginPage extends TestBase {
    browserutilities oBroUti;

    Logger log = Logger.getLogger(getClass().getSimpleName());

    public LoginPage(WebDriver driver) {
        TestBase.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //pagefactory

    @FindBy(name = "username")
    WebElement username;

    @FindBy(name = "pw")
    WebElement password;

    @FindBy(name = "Login")
    WebElement loginbutton;

    @FindBy(xpath = "//a[@title='Home Tab']")
    WebElement homepage;


    //Initializing the page objects
    //public LoginPage() {
      //  PageFactory.initElements(driver, this);
    //}
/*
    public  HomePage login(String un, String pwd) {
            username.sendKeys(un);
            password.sendKeys(pwd);
            loginbutton.click();

          // return new HomePage();
        }
  */
        public boolean LoginPageApp() throws Exception {
        boolean bRes_Flag = false;
        oBroUti.waitForElementVisible(driver,username,5);
        oBroUti.ufSendKeys(username,System.getProperty("td_emailid"));
        oBroUti.ufSendKeys(password,System.getProperty("td_password"));
        oBroUti.ufClick(loginbutton);
        oBroUti.waitForElementVisible(driver,homepage,5);
        if(oBroUti.isDisplayed(homepage))
            bRes_Flag = true;
        return bRes_Flag;
        }

    @FindBy(xpath = "//a[@title='Logout']")
        WebElement logout;

        public boolean LogoutPageApp() throws Exception {
            boolean bRes_Flag = false;
            if(oBroUti.isDisplayed(logout))
                logout.click();
            bRes_Flag = true;
            return bRes_Flag;
        }

        public boolean checkLoggedIntoAppOrNotElseLogin() throws Exception {
             boolean bRes_Flag = false;
             try {
                 oBroUti.waitForElementVisible(driver,homepage,3);
             }catch(Exception ea) {log.error("By passing error when we run on suite case");}
             if(!oBroUti.isDisplayed(homepage))
                 bRes_Flag = LoginPageApp();

             return bRes_Flag;
        }
    public boolean checkLoggedIntoAppOrNotElseLogout() throws Exception {
        boolean bRes_Flag = false;

        if (!oBroUti.isDisplayed(homepage))
            bRes_Flag = LogoutPageApp();

        return bRes_Flag;
    }

    public boolean invalidloginApp() throws Exception {
            boolean bRes_Flag = false;
        
        oBroUti.waitForElementVisible(driver,username,5);
            oBroUti.ufSendKeys(username, System.getProperty("td_invalid_emailid"));
            oBroUti.ufSendKeys(password, System.getProperty("td_invalid_password"));
            oBroUti.ufClick(loginbutton);
            log.info("***************");
            Thread.sleep(20000);
            log.info(driver.switchTo.alert().getText());
            log.info("****************");
            assertEquals(driver.switchTo.alert().getText());

         driver.switchTo().alert().accept();
         bRes_Flag = true;
         return bRes_Flag;

    }
}
