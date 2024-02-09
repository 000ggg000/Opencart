package lt.techin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ItemPage extends BasePage{

    @FindBy(css = "h1")
    WebElement nameItem;

    @FindBy(css = "#input-quantity")
    WebElement inputQuantity;

    @FindBy(id = "button-cart")
    WebElement addToCartButton;

    @FindBy(css = ".alert-dismissible")
    WebElement infoMessage;

    @FindBy(xpath = "//button[@class='btn btn-lg btn-inverse btn-block dropdown-toggle']")
    WebElement cartButton;

    @FindBy(css = ".text-start > a")
    WebElement nameOfItemInCart;

    @FindBy(css = "tr > td:nth-of-type(3)")
    WebElement qtyinCart;

    @FindBy(css = "tr > td:nth-of-type(4)")
    WebElement priceInCart;

    @FindBy(css = ".price-new")
    WebElement priceOfItem;


    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public String getNameItemText(){
       return nameItem.getText();
    }

    public void addQty(int index){
        inputQuantity.clear();
        inputQuantity.sendKeys(String.valueOf(index));
    }

        public int inputWithRandomNumber(){
        return parseInt(inputQuantity.getText());
    }

    public String checkTheQtyOfItemAdded() {
       return inputQuantity.getAttribute("value");
    }

    public void clickTheAddToCartButton(){
        addToCartButton.click();
    }

    public String cartButtonText() {
        return cartButton.getText();
    }

    public int cartButtonTextQtyValue() {
        int number = parseInt(cartButton.getText().split(" ")[0]);
        return number;
    }



    public void cartButtonView(){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> cartButton.isDisplayed());
        cartButton.click();
    }

    public int qtyInCartNumber(){
        return parseInt(qtyinCart.getText().replaceAll("x", "").trim());
    }

    public double priceInTheCartPage(){
        return parseDouble(priceInCart.getText().replaceAll("\\$", ""));
    }

    public double priceOfTheItem(){
        return parseDouble(priceOfItem.getText().replaceAll("\\$", ""));
    }

    public String messageText() {
        return infoMessage.getText();
    }

    public String nameOfItemInCartText() {
        return nameOfItemInCart.getText();
    }
}
