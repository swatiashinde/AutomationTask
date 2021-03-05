package com;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Automation {

	@Test
	public void composeAndSendEmail() throws AWTException, InterruptedException{
		
		
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://accounts.google.com/AccountChooser/identifier?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&flowName=GlifWebSignIn&flowEntry=AccountChooser");
		driver.manage().window().maximize(); 
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("abcdemoautomation@gmail.com");
		driver.findElement(By.xpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc qIypjc TrZEUc lw1w4b']")).click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement ele=  driver.findElement(By.xpath("//input[@name='password']"));	 
		ele.sendKeys("abc@0503");
		Thread.sleep(1000);
		WebElement next= driver.findElement(By.xpath("(//button[@type='button'])[2]"));
		next.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click(); 
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys("abcdemoautomation@gmail.com");
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("automation");
		driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys("automation");
		WebElement ele2= driver.findElement(By.xpath("//div[@command='Files']"));
		ele2.click();
		StringSelection ss = new StringSelection("C:\\Users\\admin\\Desktop\\Testing_Swati_Shinde-1.pdf");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

	     //imitate mouse events like ENTER, CTRL+C, CTRL+V
	     Robot robot = new Robot();
	     robot.delay(250);
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);
	     robot.keyPress(KeyEvent.VK_CONTROL);
	     robot.keyPress(KeyEvent.VK_V);
	     robot.keyRelease(KeyEvent.VK_V);
	     robot.keyRelease(KeyEvent.VK_CONTROL);
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.delay(100);
	     robot.keyRelease(KeyEvent.VK_ENTER);

	     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	     WebElement send= driver.findElement(By.xpath("(//div[contains(text(),'Send')])[2]"));
		 send.click();
		 
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 WebElement gmailbutton=driver.findElement(By.xpath("(//a[@role='button'])[4]"));
		 gmailbutton.click();
		 
		 WebElement signout=driver.findElement(By.xpath("//a[contains(text(),'Sign out')]"));
		 signout.click();
		 driver.close();
		 
		 
		 
	}
}
