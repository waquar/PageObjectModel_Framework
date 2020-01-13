package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    public  LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver driver;
    public String login_link = "//a[@href= '/sign_in']";
    public String username = "//input[@id='user_email']";
    public String password = "//input[@id='user_password']";
    public String submitlogin = "//input[@name='commit']";

public void log_in(){
    driver.findElement(By.xpath(login_link)).click();
}

public void signin_testdata(String passemail, String passkey){
    WebElement email = driver.findElement(By.xpath(username));
    email.clear();
    email.sendKeys(passemail);

    WebElement email_password = driver.findElement(By.xpath(password));
    email_password.clear();
    email_password.sendKeys(passkey);

    WebElement clicksubmit = driver.findElement(By.xpath(submitlogin));
    clicksubmit.click();
}

}