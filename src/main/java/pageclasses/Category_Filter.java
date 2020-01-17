package pageclasses;

import Basemain.Basemain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Category_Filter extends Basemain {
    public Category_Filter(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    public WebDriver driver;
    private String Category_Dropdown_xpath = "(//div[contains(@class, 'course-filter')])[1]//button";
    private String Category_Option_xpath = "//a[@href='/courses/category/%s']";

    //for clicking on category dropdown
    public void clicking_on_categorydropdown(){
        WebElement dropdown = driver.findElement(By.xpath(Category_Dropdown_xpath));
        dropdown.click();
    }

    // for explicitly wait to appear the drop down
    public WebElement categoryDropdown(String category_name){
        clicking_on_categorydropdown();
        WebDriverWait wait = new WebDriverWait(driver, 4);
        WebElement Category_Options =  wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath(String.format(Category_Option_xpath, category_name))));
        return  Category_Options;
    }

    public ResultsPage selectdropdown(String category_name){
        WebElement Category_Options = categoryDropdown(Category_Dropdown_xpath);
        Category_Options.click();
        return  new ResultsPage(driver);
    }
    public int findcourses_count(String cateogary_name){

       /*
       WebElement dropdown = driver.findElement(By.className("dropdown-menu"));
        List<WebElement> menuItems = dropdown.findElements(By.tagName("a"));
        for(WebElement menuItem: menuItems){
            if(menuItem.getText().startsWith(cateogary_name)){
                String [] arraytemp = menuItem.getText().split("\\(");
                String coursecount = arraytemp[1].split("\\)")[0];
                int final_coursecount = Integer.parseInt(coursecount);

                break;
            }
        }
        */

        WebElement Category_count = categoryDropdown(Category_Option_xpath);
        String category_text = Category_count.getText();                                // getting integer value of options using split
        String [] arraytemp = category_text.split("\\(");
        String coursecount = arraytemp[1].split("\\)")[0];
        int final_coursecount = Integer.parseInt(coursecount);
        clicking_on_categorydropdown();                                                           //closing the opened dropdown
        return  final_coursecount;
    }

}
