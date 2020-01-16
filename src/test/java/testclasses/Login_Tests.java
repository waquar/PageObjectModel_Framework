package testclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageclasses.*;

import java.util.concurrent.TimeUnit;

public class Login_Tests {
    WebDriver driver;
    String baseurl = "https://learn.letskodeit.com/";
    LoginPage login;
    NavigationBar navbar;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.get(baseurl);
        navbar = new NavigationBar(driver);
        navbar.log_in();
    }
    @AfterMethod
    public void afterthemethod(){
        if(navbar.checkUserlogin_status()){
            navbar.logout();
            navbar.log_in();
        }
    }

    @Test
    public  void testlogin_positive(){
        navbar = login.signin_testdata("test@email.com", "abcabc");
        boolean result = navbar.checkUserlogin_status();
        Assert.assertTrue(result);
    }

    @Test
    public void testlogin_negative(){
        navbar = login.signin_testdata("fail@email.com", "abcabc");
        boolean result = navbar.checkUserlogin_status();
        Assert.assertFalse(result);

    }

}
