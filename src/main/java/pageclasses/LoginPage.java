package pageclasses;

import Basemain_Package.Basemain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Basemain {

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    public WebDriver driver;
    public String username = "//input[@id='user_email']";
    public String password = "//input[@id='user_password']";
    public String submitlogin = "//input[@name='commit']";

    //returning object of navigation bar since it is a result
//of  action of login page, that is pageobject theory.

    //yet to implement custom driver here
    public NavigationBar signin_testdata(String passemail, String passkey) {
        WebElement email = driver.findElement(By.xpath(username));
        email.clear();
        email.sendKeys(passemail);

        WebElement email_password = driver.findElement(By.xpath(password));
        email_password.clear();
        email_password.sendKeys(passkey);

        WebElement clicksubmit = driver.findElement(By.xpath(submitlogin));
        clicksubmit.click();
        return new NavigationBar(driver);
    }

}