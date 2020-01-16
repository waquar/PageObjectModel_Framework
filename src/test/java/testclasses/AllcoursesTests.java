package testclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageclasses.*;
import java.util.concurrent.TimeUnit;

public class AllcoursesTests {
    WebDriver driver;
    String baseurl = "https://learn.letskodeit.com/";
    LoginPage login;
    NavigationBar navbar;
    SearchbarPage searchbar;
    ResultsPage resultpage;
    Category_Filter catfilter;

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

    @Test
    public  void searchCourse(){
        navbar = login.signin_testdata("test@email.com", "abcabc");
        navbar.mycourses();
        searchbar = new SearchbarPage(driver);
        resultpage  = searchbar.courses("restapi");
        boolean searchresult = resultpage.verifySearchitems();
        Assert.assertTrue(searchresult);
    }

    @Test
    public void filterthecategory(){
        navbar.allcourses();
        catfilter = new Category_Filter(driver);
        resultpage =  catfilter.selectdropdown("Software IT");
        int count = catfilter.findcourses_count("Software IT");
        boolean filterresult = resultpage.verifyFilterCourseCount(count);
        Assert.assertTrue(filterresult);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}

