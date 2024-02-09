package lt.techin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.Integer.parseInt;

public class ItemPage extends BasePage{

    @FindBy(css = "h1")
    WebElement nameItem;

    @FindBy(css = "#input-quantity")
    WebElement inputQuantity;

    @FindBy(id = "button-cart")
    WebElement addToCartButton;


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
}
