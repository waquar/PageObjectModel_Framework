package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchbarPage {
    public SearchbarPage(WebDriver driver){
        this.driver = driver;
    }

    WebDriver driver;
    public String searchcourse = "//input[@id='search-courses']";
    //public String clicksearchicon =  "//button[@id='search-course-button']";

    public ResultsPage courses(String course){
        WebElement coursename = driver.findElement(By.xpath(searchcourse));
        coursename.clear();
        coursename.sendKeys(course);
        return  new ResultsPage(driver);

         //WebElement submitclick = driver.findElement(By.xpath(clicksearchicon));
         //submitclick.click();
    }

}
