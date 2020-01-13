package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar {

    public NavigationBar(WebDriver driver){
        this.driver = driver;

    }
    WebDriver driver;
    public String url = "https://learn.letskodeit.com/courses";
    public String Allcourses_link = "//a[@href='/courses']";
    public String Mycourses_link = "//a[@href='/courses/enrolled']";


    public void allcourses(){
        driver.findElement(By.xpath(Allcourses_link)).click();
    }
    public void mycourses(){
        driver.findElement(By.xpath(Mycourses_link)).click();
    }



    //public boolean openedurl(){
     //   return  url.equalsIgnoreCase(driver.getCurrentUrl());
    //}


}
