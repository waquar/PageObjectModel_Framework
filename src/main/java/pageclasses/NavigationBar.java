package pageclasses;

import Basemain_Package.Basemain;
import Utilities.Util;
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
    public String Allcourses_link = "xpath:>//a[@href='/courses']";
    public String Mycourses_link = "xpath:>//a[@href='/courses/enrolled']";
    public String user_icon = "class:>gravatar";
    public String login_link = "xpath:>//a[@href= '/sign_in']";
    public String logout_link = "linktext:>Log Out";

    public void allcourses(){
        getElement(Allcourses_link, "allcourses").click();
    }
    public void mycourses(){
        getElement(Mycourses_link, "Mycourses").click();
    }

    public LoginPage log_in() {
        getElement(login_link, "login");
        return  new LoginPage(driver);
    }

    public boolean checkUserlogin_status() {
        WebElement icon = null;
        try{
            icon = getElement(user_icon, "icon image");
            return  true;
        }catch(Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    public boolean verifytheheader(){
        WebElement link = getElement(Mycourses_link, "my courses");
        String application_text = link.getText();
        return Util.verifyTextMatch(application_text, "My Courses");

    }

    public  void logout(){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(
                getElement(user_icon,"image icon")));   //it will click on icon
        logout.click();
        getElement(logout_link,"logout").click();
        driver.quit();
    }
    //public boolean openedurl(){
     //   return  url.equalsIgnoreCase(driver.getCurrentUrl());
    //}


}
