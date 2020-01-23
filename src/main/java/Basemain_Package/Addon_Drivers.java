package Basemain_Package;


import Utilities.Util;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
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

    /**
     * Selects a check box irrespective of its state, using locator
     *
     * @param locator
     * @param info
     * @return
     */
    public void Check(String locator, String info) {
        WebElement element = getElement(locator, info);
        Check(element, info);
    }

    /**
     * UnSelects a check box irrespective of its state
     *
     * @param element
     * @param info
     * @return
     */
    public void UnCheck(WebElement element, String info) {
        if (isSelected(element, info)) {
            elementClick(element, info);
            System.out.println("Element :: " + info + " is unchecked");
        } else
            System.out.println("Element :: " + info + " is already unchecked");
    }

    /**
     * UnSelects a check box irrespective of its state, using locator
     *
     * @param locator
     * @param info
     * @return
     */
    public void UnCheck(String locator, String info) {
        WebElement element = getElement(locator, info);
        UnCheck(element, info);
    }

    /**
     * @param element
     * @param info
     * @return
     */
    public Boolean Submit(WebElement element, String info) {
        if (element != null) {
            element.submit();
            System.out.println("Element :: " + info + " is submitted");
            return true;
        } else
            return false;
    }

    /**
     * @param locator
     * @param attribute
     * @return
     */
    public String getElementAttributeValue(String locator, String attribute) {
        WebElement element = getElement(locator, "info");
        return element.getAttribute(attribute);
    }

    /**
     * @param element
     * @param attribute
     * @return
     */
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
    } public boolean isElementPresent(String locator, String info) {
        List<WebElement> elementList = getElementList(locator, info);
        int size = elementList.size();
        if (size > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Click element with information about element and
     * time to wait in seconds after click
     *
     * @param element - WebElement to perform Click operation
     * @param info    - information about element
     */
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

    /**
     * Click element with no time to wait after click
     *
     * @param element - WebElement to perform Click operation
     * @param info    - information about element
     */
    public void elementClick(WebElement element, String info) {
        elementClick(element, info, 0);
    }

    /**
     * Click element with locator
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info
     * @param timeToWait
     * @return
     */
    public void elementClick(String locator, String info, long timeToWait) {
        WebElement element = this.getElement(locator, info);
        elementClick(element, info, timeToWait);
    }

    /**
     * Click element with locator and no time to wait
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element
     * @return
     */
    public void elementClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        elementClick(element, info, 0);
    }
    /***
     * Wait for element to be clickable
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param timeout - Duration to try before timeout
     */
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

    /**
     *
     */
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

    /**
     * Mouse Hovers to an element
     *
     * @param locator
     */
    public void mouseHover(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        //Util.sleep(5000);
    }

    /**
     * @param element
     * @param optionToSelect
     */
    public void selectOption(WebElement element, String optionToSelect) {
        Select sel = new Select(element);
        sel.selectByVisibleText(optionToSelect);
        System.out.println("Selected option : " + optionToSelect);
    }

    /**
     * Selects a given option in list box
     *
     * @param locator
     * @param optionToSelect
     */
    public void selectOption(String locator, String optionToSelect, String info) {
        WebElement element = getElement(locator, info);
        this.selectOption(element, optionToSelect);
    }

    /**
     * get Selected drop down value
     *
     * @param element
     * @return
     */
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

    /***
     * Send Keys to element with locator
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param data - Data to send
     * @param info - Information about element
     * @param clear - True if you want to clear the field before sending data
     */
    public void sendData(String locator, String data, String info, Boolean clear) {
        WebElement element = this.getElement(locator, info);
        sendData(element, data, info, clear);
    }

    /***
     * Send Keys to element with clear
     * @param element - WebElement to send data
     * @param data - Data to send
     * @param info - Information about element
     */
    public void sendData(WebElement element, String data, String info) {
        sendData(element, data, info, true);
    }

    /***
     * Send Keys to element with locator and clear
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param data - Data to send
     * @param info - Information about element
     */
    public void sendData(String locator, String data, String info) {
        WebElement element = getElement(locator, info);
        sendData(element, data, info, true);
    }

    /**
     * Get text of a web element
     *
     * @param element - WebElement to perform click action
     * @param info    - Information about element
     */
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

    /**
     * Get text of a web element with locator
     * @param locator
     * @param info
     * @return
     */
    public String getText(String locator, String info) {
        WebElement element = this.getElement(locator, info);
        return this.getText(element, info);
    }

    /**
     * Check if element is enabled
     * @param element
     * @param info
     * @return
     */
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

    /***
     * Check if element is enabled with locator
     * @param locator
     * @param info
     * @return
     */
    public Boolean isEnabled(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isEnabled(element, info);
    }

    /**
     * Check if element is displayed
     * @param element
     * @param info
     * @return
     */
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

    /***
     * Check if element is displayed with locator
     * @param locator
     * @param info
     * @return
     */
    public Boolean isDisplayed(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isDisplayed(element, info);
    }

    /**
     * @param element
     * @param info
     * @return
     */
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

    /**
     * @param locator
     * @param info
     * @return
     */
    public Boolean isSelected(String locator, String info) {
        WebElement element = getElement(locator, info);
        return isSelected(element, info);
    }

    /**
     * Selects a check box irrespective of its state
     *
     * @param element
     * @param info
     */






}
