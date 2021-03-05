package com;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class ComposeAndSendEmail {

	
	WebDriver driver;
	SoftAssert sAssert=null;
    By compose = By.xpath("//div[contains(text(),'Compose')]");
    By to = By.xpath("//textarea[@name='to']");
    By sub =By.xpath("//input[@name='subjectbox']");   
    By gmailmsg =By.xpath("//div[@role='textbox']");    
    By attachfileButton =By.xpath("//div[@command='Files']");   
    By sendButton =By.xpath("(//div[contains(text(),'Send')])[2]");   
    By GmailButton =By.xpath("(//a[@role='button'])[4]");   
    By Signoutbutton =By.xpath("//a[contains(text(),'Sign out')]");    
    By GmailLabel=By.xpath("(//a[@title='Gmail'])[2]");
    public ComposeAndSendEmail(WebDriver driver){
        this.driver = driver;
        sAssert=new SoftAssert();
    }
    public void clickCompose(){
       driver.findElement(compose).click();
    }
    public void setSentTo(String sentTo){
        driver.findElement(to).sendKeys(sentTo);
    }
    public void setSubject(String subject){
        driver.findElement(sub).sendKeys(subject);
    }
    
    public void sendButton(String msg){
       driver.findElement(gmailmsg).sendKeys(msg);
   }
    
    public void clickAttachfileButton(){
        driver.findElement(attachfileButton).click();
   }
    
    public void clickSendButton(){
       driver.findElement(sendButton).click();
   }
    
    public void clickGmailButton(){
        driver.findElement(GmailButton).click();
  }
    
    public void clickSignoutbutton(){
        driver.findElement(Signoutbutton).click();
  }
    
    public void setEmailMsg(String msg){
    	driver.findElement(gmailmsg).sendKeys(msg);
   }
    
    public void validateGmailPage(){   	
        sAssert.assertEquals(driver.findElement(GmailLabel).getAttribute("title"),"Gmail");
        }
    public void ComposeGmail(String sentTo,String subject,String msg){
        this.validateGmailPage();
    	this.clickCompose();   	
        this.setSentTo(sentTo);
        this.setSubject(subject);
        this.setEmailMsg(msg);                      
    }
    
    public void sendEmail() throws AWTException, InterruptedException{
    	clickAttachfileButton();
    	Thread.sleep(5000);
    	String currentDirectory=System.getProperty("user.dir")+"\\demofile.pdf";
    	StringSelection ss = new StringSelection(currentDirectory);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	    Robot robot = new Robot();
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);
	     robot.keyPress(KeyEvent.VK_CONTROL);
	     robot.keyPress(KeyEvent.VK_V);
	     robot.keyRelease(KeyEvent.VK_V);
	     robot.keyRelease(KeyEvent.VK_CONTROL);
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);
	     Thread.sleep(5000);
	     clickSendButton();
	     Thread.sleep(5000);
	     clickGmailButton();
	     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	     clickSignoutbutton();
	 }
    
    public void executeAssert(){
    	sAssert.assertAll();
    }
}
