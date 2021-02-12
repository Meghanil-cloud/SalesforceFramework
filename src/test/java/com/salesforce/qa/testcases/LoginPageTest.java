package com.salesforce.qa.testcases;

import com.salesforce.qa.base.TestBase;
import com.salesforce.qa.pages.HomePage;
import com.salesforce.qa.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.logging.Logger;

public class LoginPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;

    Logger log = Logger.getLogger(getClass().getSimpleName());

    @BeforeTest
    public void settingUpEnvironment() throws Exception {
        String sErrorMessage = "";
        String sClassNameForScreenShot = getClass().getSimpleName();
        driver.get(oCons.getSalesforceURL());
        loginPage = new LoginPage(driver);
        loginPage.checkLoggedIntoSalesforceOrNotElseLogout();
    }
    @AfterMethod
    public void settingReqURL() throws Exception {
        driver.get(oCons.getSalesforceURL());
    }

    @Test(priority = 2)
    public void to_check_Whether_Login_Happening_Or_Not() throws Exception {
        loginPage.loginPageToSalesforce();
    }

    @Test(priority = 1)
    public void To_Check_Error_For_Invalid_Login() throws Exception {
        loginPage.invalidloginToSalesforce();
        SoftAssert sa = new SoftAssert();

        sa.assertAll();
    }
/*
    public LoginPageTest() {
        super();
    }



    @BeforeMethod
    public void setUp()
    {
        intialization();
        loginPage = new LoginPage();

    }

    @Test
    public void loginTest(){
        homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
    }

    @AfterMethod
    public void tearDown() {

    }

 */
}
