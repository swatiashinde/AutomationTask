package com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class LoginPage {

	WebDriver driver;
	SoftAssert sAssert=null;
	
	By userName = By.xpath("//input[@type='email']");
	By nextButton = By.xpath("(//button[@type='button'])[2]");
    By nextUser = By.xpath("(//button[@type='button'])[3]");
    By password =By.xpath("//input[@name='password']");
    By signIn=By.xpath("//span[contains(text(),'Sign in')]");
    By wel=By.xpath("//span[contains(text(),'Welcome')]");

   public LoginPage(WebDriver driver){
        this.driver = driver;
        sAssert=new SoftAssert();
    }

	public void setUserName(String strUserName){
        driver.findElement(userName).sendKeys(strUserName);
    }

    public void setPassword(String strPassword){
    	driver.findElement(password).sendKeys(strPassword);
    }
    public void clickNext(){
    	driver.findElement(nextButton).click();
    }

    public void clickNextUser(){
        driver.findElement(nextUser).click();
    }
    public void validatesignInPage(){       
        sAssert.assertEquals(driver.findElement(signIn).getText(),"Sign in");
    }
    
    public void validateWelcomePage(){   	
    sAssert.assertEquals(driver.findElement(wel).getAttribute("innerHTML"),"Welcome");
    }
    
    public void loginToGmail(String strUserName,String strPasword) throws InterruptedException{
    	this.validatesignInPage();
        this.setUserName(strUserName);         
        this.clickNextUser();       
        this.validateWelcomePage();
        this.setPassword(strPasword);
        Thread.sleep(1000);
        this.clickNext();        
    }
    
    public void executeAssert(){
    	sAssert.assertAll();
    }
}
