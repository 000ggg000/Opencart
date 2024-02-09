package lt.techin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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

    @FindBy(xpath = "//button[@aria-label='Add to Cart']")
    List<WebElement> shoppingCartButtons;

    @FindBy(css = "#product-list [class='col mb-3']:nth-of-type(1) .button-group [type='submit']:nth-of-type(1)")
    WebElement firstItemInList;

    @FindBy(css = "#product-list [class='col mb-3']:nth-of-type(2) .button-group [type='submit']:nth-of-type(1)")
    WebElement secondItemInList;

    @FindBy(css = "#product-list [class='col mb-3']:nth-of-type(3) .button-group [type='submit']:nth-of-type(1)")
    WebElement thirdItemInList;
    @FindBy(css = "#product-list [class='col mb-3']:nth-of-type(4) .button-group [type='submit']:nth-of-type(1)")
    WebElement forthItemInList;

    @FindBy(css = ".btn-inverse")
    WebElement cartButton;

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

//    public void addingAllProductsToCart() {
//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//        wait.until(d -> showItemsAsListButton.isDisplayed());
//        shoppingCartButtons.forEach(WebElement::click);
//    }

    public void addingAllProductsToCartPrimitive(){
        firstItemInList.click();
        secondItemInList.click();
        thirdItemInList.click();
        forthItemInList.click();
    }
}
