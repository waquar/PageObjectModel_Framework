package testclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageclasses.NavigationBar;
import pageclasses.LoginPage;
import pageclasses.ResultsPage;
import pageclasses.SearchbarPage;

import java.util.concurrent.TimeUnit;

public class AllcoursesTests {
    WebDriver driver;
    String baseurl = "https://learn.letskodeit.com/";

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.get(baseurl);
    }

    @Test
    public  void searchCourse(){
        LoginPage login = new LoginPage(driver);
        login.log_in();
        NavigationBar navbar = login.signin_testdata("test@email.com", "abcabc");
        navbar.mycourses();

        SearchbarPage searchbar = new SearchbarPage(driver);
        ResultsPage resultpage  = searchbar.courses("restapi");

        boolean searchresult = resultpage.verifySearchitems();
        Assert.assertTrue(searchresult);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}

