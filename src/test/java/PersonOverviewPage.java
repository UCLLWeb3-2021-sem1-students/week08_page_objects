import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class PersonOverviewPage extends Page {

    public PersonOverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"?command=Overview");
    }

    public boolean containsUserWithEmail(String email) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(email)) {
                found=true;
            }
        }
        return found;
    }
}
