package lt.techin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearchPage extends BasePage{

    @FindBy(xpath = "\"//h4/a\"")
    WebElement titleOfItem;
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean searchResults(String name){
        return titleOfItem.getText().contains(name);
    }
}
