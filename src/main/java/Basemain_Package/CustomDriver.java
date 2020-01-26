package Basemain_Package;

import Utilities.Util;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomDriver {
    public WebDriver driver;
    public JavascriptExecutor js;

    public CustomDriver() {
    }

    public CustomDriver(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor)driver;
    }
    public void javascriptClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        try {
            js.executeScript("arguments[0].click();", element);
            System.out.println("Clicked on :: " + info);
        } catch (Exception e) {
            System.out.println("Cannot click on :: " + info);
        }
    }

   ///will return By type object
    public By getByType(String locator){
        By by = null;
        String locator_type = locator.split(":>")[0];
        locator = locator_type.split(":>")[1];

        try{
            if(locator_type.contains("id")){
                by = By.id(locator);
            }
            else if(locator_type.contains("name")){
                by = By.name(locator);
            }
            else if(locator_type.contains("class")){
                by = By.className(locator);
            }
            else if(locator_type.contains("tag")){
                by = By.tagName(locator);
            }
            else if(locator_type.contains("link")){
                by = By.linkText(locator);
            }
            else if(locator_type.contains("partiallink")){
                by = By.partialLinkText(locator);
            }
            else if(locator_type.contains("css")){
                by = By.cssSelector(locator);
            }
            else if(locator_type.contains("xpath")){
                by = By.xpath(locator);
            }else{
                System.out.println("Given locator is not valid : > " + locator);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("By type not found with this locator : > " + locator);
        }
        return  by;

    }
    public WebElement getElement(String locator, String info){
        //setting element null at first call
        WebElement element = null;
        By bytheType = getByType(locator);
        try {
            element  =driver.findElement(bytheType);
        }catch (Exception e){
            System.out.println("Element not able to find with this locator : > " + locator);
        }
        return  element;
    }

    public List<WebElement> getElementList(String locator, String info) {
        List<WebElement> elementList = new ArrayList<WebElement>();
        By byType = getByType(locator);
        try {
            elementList = driver.findElements(byType);
            System.out.println("Element List found with: " + locator);
        } catch (Exception e) {
            System.out.println("Element List not found with: " + locator);
            e.printStackTrace();
        }
        return elementList;
    }

    public WebElement waitForElement(String locator, int timeout) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(byType));
            System.out.println("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return element;
    }


    public String takeScreenshot(String methodName, String browserName) {
        String fileName = Util.getScreenshotName(methodName, browserName);
        String screenshotDir = System.getProperty("user.dir") + "//" + "test-output/screenshots";
        new File(screenshotDir).mkdirs();
        String path = screenshotDir + "//" + fileName;

        try {
            File screenshot = ((TakesScreenshot)driver).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
            System.out.println("Screen Shot Was Stored at: "+ path);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return path;
    }

}
