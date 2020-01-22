package pageclasses;
import Basemain_Package.Basemain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ResultsPage extends Basemain {
    public ResultsPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    WebDriver driver;
    private String url = "?query=";
    private String courses_list = "xpath:>//div[@class='course-listing-title']";

    public boolean isOpen( ){
        return  driver.getCurrentUrl().contains(url);
    }

    public int courseCount( ){
        List <WebElement> count_items = getElementList(courses_list, "items list");
        return  count_items.size();

    }
    public boolean verifySearchitems( ){
        boolean result = false;
        if (courseCount() > 0){
            result = true;
        }
        result = isOpen() & verifySearchitems();
        return  result;
    }

    public boolean verifyFilterCourseCount(int expectedCount) {
        return courseCount() == expectedCount;
    }
}
