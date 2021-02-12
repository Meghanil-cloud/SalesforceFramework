package com.salesforce.qa.pages;

import com.salesforce.qa.base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class HomePage extends TestBase {

    Logger log = Logger.getLogger(getClass().getSimpleName());

    public HomePage(WebDriver driver) {
        TestBase.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@title='Home Tab']")
    WebElement button_Home;


}
