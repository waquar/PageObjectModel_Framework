package pageclasses;

import Basemain_Package.Basemain;
import Basemain_Package.CustomDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchbarPage extends Basemain {
    public SearchbarPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    WebDriver driver;
    public String searchcourse = "xpath:>//input[@id='search-courses']";
    //public String clicksearchicon =  "//button[@id='search-course-button']";

    public ResultsPage courses(String course){
        WebElement coursename = getElement(searchcourse, "search course");
        coursename.clear();
        coursename.sendKeys(course);
        return  new ResultsPage(driver);

         //WebElement submitclick = driver.findElement(By.xpath(clicksearchicon));
         //submitclick.click();
    }

}
