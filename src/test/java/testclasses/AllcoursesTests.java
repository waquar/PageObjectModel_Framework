package testclasses;

import Basetest.BaseClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageclasses.Category_Filter;
import pageclasses.SearchbarPage;

public class AllcoursesTests extends BaseClass {

    @BeforeClass
    public void setUp(){
        navbar = login.signin_testdata("test@email.com", "abcabc");
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

