package Basemain;

import org.openqa.selenium.WebDriver;

public class Basemain {

    public Basemain(WebDriver driver){
        this.driver = driver;
    }
    WebDriver driver;

    public boolean checktitle(String expectedtitle){
        return  driver.getTitle().equalsIgnoreCase(expectedtitle);
    }
}
