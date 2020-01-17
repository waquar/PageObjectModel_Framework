package pageclasses;

import Basemain.Basemain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationBar extends Basemain {

    public NavigationBar(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    WebDriver driver;
    public String url = "https://learn.letskodeit.com/courses";
    public String Allcourses_link = "//a[@href='/courses']";
    public String Mycourses_link = "//a[@href='/courses/enrolled']";
    public String user_icon = "gravatar";
    public String login_link = "//a[@href= '/sign_in']";

    public void allcourses(){
        driver.findElement(By.xpath(Allcourses_link)).click();
    }
    public void mycourses(){
        driver.findElement(By.xpath(Mycourses_link)).click();
    }

    public LoginPage log_in() {
        driver.findElement(By.xpath(login_link)).click();
        return  new LoginPage(driver);
    }

    public boolean checkUserlogin_status() {
        WebElement icon = null;
        try{
            icon = driver.findElement(By.className(user_icon));
            return  true;
        }catch(Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    public  void logout(){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.className(user_icon))));
        logout.click();
        driver.findElement(By.linkText("Log Out"));
        driver.quit();
    }
    //public boolean openedurl(){
     //   return  url.equalsIgnoreCase(driver.getCurrentUrl());
    //}


}
