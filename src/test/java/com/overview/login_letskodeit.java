package com.overview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class login_letskodeit {

    WebDriver driver;
    @BeforeTest
    public void beforetest(){
        //System.setProperty("webdriver.chrome.driver","/users/waquaralam/Automation_Addons/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }
    @Test
    public void login() throws InterruptedException {
        driver.get("https://learn.letskodeit.com/");
        Thread.sleep(10000);
        Assert.assertEquals(driver.getTitle(),"Home | Let's Kode It");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("user_email")).sendKeys("test@email.com");
        driver.findElement(By.id("user_password")).sendKeys("abcabc");
        driver.findElement(By.name("commit")).click();
        Thread.sleep(4000);
    }
    @Test
    public void loginfail() throws InterruptedException {
        driver.get("https://learn.letskodeit.com/");
        Thread.sleep(10000);
        Assert.assertEquals(driver.getTitle(),"Home | Let's Kode It");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("user_email")).sendKeys("test@wrongemail.com");
        driver.findElement(By.id("user_password")).sendKeys("abcabc");
        driver.findElement(By.name("commit")).click();
        Thread.sleep(4000);
    }
    @AfterTest
    public void aftertest(){
        WebElement icon = driver.findElement(By.className("gravatar"));
        if (icon != null) {
            driver.findElement(By.className("gravatar")).click();
            driver.findElement(By.linkText("Log Out"));
            driver.quit();
        }
        else{
            System.out.println("log in not successful");
            driver.quit();
        }
    }

}
