package lt.techin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class QAPage extends BasePage {

    @FindBy(css = ".fa-table-list")
    WebElement showItemsAsListButton;

    @FindBy(css = "div#product-list")
    WebElement buttonShowList;

    @FindBy(xpath = "//h4/a")
    List<WebElement> namesOfItems;

    @FindBy(css = "h2")
    WebElement titleQA;


    public QAPage(WebDriver driver) {
        super(driver);
    }

    public String titleQaDisplayed() {
        return titleQA.getText();
    }

    public void clickShowItemsInList() {
        showItemsAsListButton.click();
    }

    public boolean isButtonListDisplayed() {
        return buttonShowList.isDisplayed();
    }

    public boolean namesOfItemsDisplayed(String name) {
        return namesOfItems.stream().map(n -> n.getText().equals(name)).findAny().isPresent();
    }

    public String namesOfItemsDisplayedName(String name) {
        WebElement nameItem = driver.findElement(By.xpath("//h4/a[text()='" + name + "']"));
        return nameItem.getText();

    }

    public void clickTheItemDescription(String name) {
        WebElement nameItem = driver.findElement(By.xpath("//h4/a[text()='" + name + "']"));
        nameItem.click();
    }
}
