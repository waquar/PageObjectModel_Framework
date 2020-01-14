package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Category_Filter {
    public Category_Filter(WebDriver driver){
        this.driver = driver;
    }
    public WebDriver driver;
    private String Category_Dropdown = "(//div[contains(@class, 'course-filter')])[1]//button";
    private String Category_Option = "//a[@href='/courses/category/Software IT']";

    public ResultsPage selectdropdown(String category_name){
        WebElement dropdown = driver.findElement(By.xpath(Category_Dropdown));
        dropdown.click();
        //Explicitlywait
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(Category_Option, category_name)))).click();
        return  new ResultsPage(driver);

    }
    public int findcourses_count(String cateogary_name){
        WebElement dropdown = driver.findElement(By.xpath(Category_Dropdown));
        dropdown.click();
        WebDriverWait wait = new WebDriverWait(driver, 4);
        WebElement category_Option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format(Category_Option, cateogary_name))));

        // getting integer value of options using split
        String category_text = category_Option.getText();
        String [] arraytemp = category_text.split("\\(");
        String coursecount = arraytemp[1].split("\\)")[0];

        int final_coursecount = Integer.parseInt(coursecount);
        //closing dropdown
        dropdown.click();
        return  final_coursecount;

    }



}
