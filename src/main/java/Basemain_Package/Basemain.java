package Basemain_Package;

import org.openqa.selenium.WebDriver;

public class Basemain extends  CustomDriver {

    public Basemain(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    WebDriver driver;

    public boolean checktitle(String expectedtitle){
        return  driver.getTitle().equalsIgnoreCase(expectedtitle);
    }
}
