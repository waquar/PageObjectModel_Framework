package Basetest_Package;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pageclasses.*;

import java.util.concurrent.TimeUnit;

public class BaseClass {
        public WebDriver driver;
        protected String baseurl = "https://learn.letskodeit.com/";
        protected LoginPage login;
        protected NavigationBar navbar;
        protected SearchbarPage searchbar;
        protected ResultsPage resultpage;
        protected Category_Filter catfilter;

        @BeforeClass
        public void base_setUp(){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
            driver.get(baseurl);
            navbar = new NavigationBar(driver);
            login = navbar.log_in();
        }

        @AfterClass
        public void base_tearDown(){
            driver.quit();
        }

}

