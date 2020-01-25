package Basemain_Package;


import Utilities.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Addon_Drivers extends  CustomDriver {
    public WebDriver driver;
    public JavascriptExecutor js;

    public Addon_Drivers() {
    }

    public void Refreshpage(){
        driver.navigate().refresh();
        System.out.println("Page refreshed!!");
    }
    public String printthetitle(){
        String title  = driver.getTitle();
        System.out.println("This is the title of page : " + title);
        return  title;
    }
    public void browserback(){
        driver.navigate().back();
        System.out.println("Success! One Step Back.");
    }
    public void browserforward(){
        driver.navigate().forward();
        System.out.println("Success! One Step Ahead.");
    }
    public void DoubleClick(WebElement element, String info) {
        Actions action = new Actions(driver);
        action.doubleClick(element);
        System.out.println("Double Clicked on :: " + info);
        action.perform();
    }

    public void rightClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        System.out.println("Double Clicked on :: " + info);
    }

    public void selectItemRightClick(String elementLocator, String itemLocator) {
        WebElement element = getElement(elementLocator, "info");
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        WebElement itemElement = getElement(itemLocator, "info");
        elementClick(itemElement, "Selected Item");
    }

    public void keyPress(Keys key, String info) {
        Actions action = new Actions(driver);
        action.keyDown(key).build().perform();
        System.out.println("Key Pressed :: " + info);
    }
    public void clickWhenReady(By locator, int timeout) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            WebElement element = null;
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver, 15);
            element = wait.until(
                    ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println("Element clicked on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
    }
    public void Check(WebElement element, String info) {
        if (!isSelected(element, info)) {
            elementClick(element, info);
            System.out.println("Element :: " + info + " is checked");
        } else
            System.out.println("Element :: " + info + " is already checked");
    }

    public void Check(String locator, String info) {
        WebElement element = getElement(locator, info);
        Check(element, info);
    }

    public void UnCheck(WebElement element, String info) {
        if (isSelected(element, info)) {
            elementClick(element, info);
            System.out.println("Element :: " + info + " is unchecked");
        } else
            System.out.println("Element :: " + info + " is already unchecked");
    }


    public void UnCheck(String locator, String info) {
        WebElement element = getElement(locator, info);
        UnCheck(element, info);
    }

    public Boolean Submit(WebElement element, String info) {
        if (element != null) {
            element.submit();
            System.out.println("Element :: " + info + " is submitted");
            return true;
        } else
            return false;
    }

    public String getElementAttributeValue(String locator, String attribute) {
        WebElement element = getElement(locator, "info");
        return element.getAttribute(attribute);
    }

    public String getElementAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }
    public boolean isOptionExists(WebElement element, String optionToVerify) {
        Select sel = new Select(element);
        boolean exists = false;
        List<WebElement> optList = sel.getOptions();
        for (int i = 0; i < optList.size(); i++) {
            String text = getText(optList.get(i), "Option Text");
            if (text.matches(optionToVerify)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Selected Option : " + optionToVerify + " exist");
        } else {
            System.out.println("Selected Option : " + optionToVerify + " does not exist");
        }
        return exists;
    }
    public boolean isElementPresent(String locator, String info) {
        List<WebElement> elementList = getElementList(locator, info);
        int size = elementList.size();
        if (size > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void elementClick(WebElement element, String info, long timeToWait) {
        try {
            element.click();
            if (timeToWait == 0) {
                System.out.println("Clicked On :: " + info);
            } else {
                Util.sleep(timeToWait, "Clicked on :: " + info);
            }
        } catch (Exception e) {
            System.out.println("Cannot click on :: " + info);
            takeScreenshot("Click ERROR", "");
        }
    }

    public void elementClick(WebElement element, String info) {
        elementClick(element, info, 0);
    }

    public void elementClick(String locator, String info, long timeToWait) {
        WebElement element = this.getElement(locator, info);
        elementClick(element, info, timeToWait);
    }

    public void elementClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        elementClick(element, info, 0);
    }

    public WebElement waitForElementToBeClickable(String locator, int timeout) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver, 15);
            element = wait.until(
                    ExpectedConditions.elementToBeClickable(byType));
            System.out.println("Element is clickable on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return element;
    }

    public boolean waitForLoading(String locator, long timeout) {
        By byType = getByType(locator);
        boolean elementInvisible = false;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            elementInvisible = wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(byType));
            System.out.println("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return elementInvisible;
    }

    public void mouseHover(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        //Util.sleep(5000);
    }

    public void selectOption(WebElement element, String optionToSelect) {
        Select sel = new Select(element);
        sel.selectByVisibleText(optionToSelect);
        System.out.println("Selected option : " + optionToSelect);
    }

    public void selectOption(String locator, String optionToSelect, String info) {
        WebElement element = getElement(locator, info);
        this.selectOption(element, optionToSelect);
    }

    public String getSelectDropDownValue(WebElement element) {
        Select sel = new Select(element);
        return sel.getFirstSelectedOption().getText();
    }

    public void sendData(WebElement element, String data, String info, Boolean clear) {
        try {
            if (clear) {
                element.clear();
            }
            //Util.sleep(1000, "Waiting Before Entering Data");
            element.sendKeys(data);
            System.out.println("Send Keys on element :: "
                    + info + " with data :: " + data);
        } catch (Exception e) {
            System.out.println("Cannot send keys on element :: "
                    + info + " with data :: " + data);
        }
    }

    public void sendData(String locator, String data, String info, Boolean clear) {
        WebElement element = this.getElement(locator, info);
        sendData(element, data, info, clear);
    }

    public void sendData(WebElement element, String data, String info) {
        sendData(element, data, info, true);
    }

    public void sendData(String locator, String data, String info) {
        WebElement element = getElement(locator, info);
        sendData(element, data, info, true);
    }

    public String getText(WebElement element, String info) {
        System.out.println("Getting Text on element :: " + info);
        String text = null;
        text = element.getText();
        if (text.length() == 0) {
            text = element.getAttribute("innerText");
        }
        if  (!text.isEmpty ()){
            System.out.println(" The text is : " + text);
        } else {
            text = "NOT_FOUND";
        }
        return text.trim();
    }

    public String getText(String locator, String info) {
        WebElement element = this.getElement(locator, info);
        return this.getText(element, info);
    }

    public Boolean isEnabled(WebElement element, String info) {
        Boolean enabled = false;
        if (element != null) {
            enabled = element.isEnabled();
            if (enabled)
                System.out.println("Element :: " + info + " is Enabled");
            else
                System.out.println("Element :: " + info + " is Disabled");
        }
        return enabled;
    }

    public Boolean isEnabled(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isEnabled(element, info);
    }

    public Boolean isDisplayed(WebElement element, String info) {
        Boolean displayed = false;
        if (element != null) {
            displayed = element.isDisplayed();
            if (displayed)
                System.out.println("Element :: " + info + " is displayed");
            else
                System.out.println("Element :: " + info + " is NOT displayed");
        }
        return displayed;
    }

    public Boolean isDisplayed(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isDisplayed(element, info);
    }

    public Boolean isSelected(WebElement element, String info) {
        Boolean selected = false;
        if (element != null) {
            selected = element.isSelected();
            if (selected)
                System.out.println("Element :: " + info + " is selected");
            else
                System.out.println("Element :: " + info + " is already selected");
        }
        return selected;
    }

    public Boolean isSelected(String locator, String info) {
        WebElement element = getElement(locator, info);
        return isSelected(element, info);
    }






}
