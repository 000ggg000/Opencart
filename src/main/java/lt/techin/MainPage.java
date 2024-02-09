package lt.techin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class MainPage extends BasePage {

@FindBy(xpath = "//a[text()='QA']")
    WebElement buttonQASelect;




    public MainPage(WebDriver driver) {

        super(driver);
    }

    public void waiting() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

public void selectQAButton(){
        buttonQASelect.click();
}

}
