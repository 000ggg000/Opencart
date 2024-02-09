package lt.techin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class MainPage extends BasePage {

@FindBy(xpath = "//a[text()='QA']")
    WebElement buttonQASelect;

@FindBy(xpath = "//input[@name='search']")
WebElement inputSearch;

@FindBy(css = ".fa-magnifying-glass")
WebElement searchButton;




    public MainPage(WebDriver driver) {

        super(driver);
    }

    public void waiting() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

public void selectQAButton(){
        buttonQASelect.click();
}

public void inputNameInSearchBar(String word){
        inputSearch.sendKeys(word);
}

public void clickTheButtonSearch(){
        searchButton.click();
}

}
