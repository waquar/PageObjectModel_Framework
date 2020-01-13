package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Allcourses {

    public Allcourses(WebDriver driver){
        this.driver = driver;

    }
    WebDriver driver;
    public String url = "https://learn.letskodeit.com/courses";
    public String Allcourses_link = "//a[@href='/courses']";

    public void open(){
        driver.findElement(By.xpath(Allcourses_link)).click();
    }
    public boolean openedurl(){
        return  url.equalsIgnoreCase(driver.getCurrentUrl());
    }


}
