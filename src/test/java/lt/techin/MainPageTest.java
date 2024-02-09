package lt.techin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest extends BasePageTest {


    @Test
    void clickOnQAButton() {
        MainPage mainPage = new MainPage(driver);
        QAPage qaPage = new QAPage(driver);
        mainPage.selectQAButton();
        assertEquals("QA", qaPage.titleQaDisplayed(), "The page was not redirected to QA");
    }

    @Test
    void ShowItemsAsList() {
        MainPage mainPage = new MainPage(driver);
        QAPage qaPage = new QAPage(driver);
        mainPage.selectQAButton();
        qaPage.clickShowItemsInList();
        assertTrue(qaPage.isButtonListDisplayed(), "The sorting by list is not enabled.");
    }


    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/names.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromFileFindItemName(String name) {
        MainPage mainPage = new MainPage(driver);
        QAPage qaPage = new QAPage(driver);
        mainPage.selectQAButton();
        qaPage.clickShowItemsInList();
        assertTrue(qaPage.namesOfItemsDisplayed(name), "The item was not found");
        assertEquals(name, qaPage.namesOfItemsDisplayedName(name), "" + name + " does not exist in the eshop");

    }

    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/names.csv", numLinesToSkip = 1)
    void testOpenItem(String name) {
        MainPage mainPage = new MainPage(driver);
        QAPage qaPage = new QAPage(driver);
        ItemPage itemPage = new ItemPage(driver);
        mainPage.selectQAButton();
        qaPage.clickShowItemsInList();
        qaPage.clickTheItemDescription(name);
        assertEquals(name, itemPage.getNameItemText(), "No such product found.");

    }

    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/names.csv", numLinesToSkip = 1)
    void changeQuantityOfItems(String name) {
        MainPage mainPage = new MainPage(driver);
        QAPage qaPage = new QAPage(driver);
        ItemPage itemPage = new ItemPage(driver);
        int index = (int) (Math.random() * 20);
        mainPage.selectQAButton();
        qaPage.clickShowItemsInList();
        qaPage.clickTheItemDescription(name);
        itemPage.addQty(index);
        String index2 = String.valueOf(index);
        assertEquals(index2, itemPage.checkTheQtyOfItemAdded(), "The quantity of Item was not added.");
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/names.csv", numLinesToSkip = 1)
    void clickTheButtonAddToCart(String name) {
        MainPage mainPage = new MainPage(driver);
        QAPage qaPage = new QAPage(driver);
        ItemPage itemPage = new ItemPage(driver);
        int index = (int) (Math.random() * 20);
        mainPage.selectQAButton();
        qaPage.clickShowItemsInList();
        qaPage.clickTheItemDescription(name);
        itemPage.addQty(index);
        itemPage.clickTheAddToCartButton();
        String messageText = itemPage.messageText();
        Assertions.assertEquals("Success: You have added " + name + " to your shopping cart!", messageText);
        Assertions.assertEquals(index, itemPage.cartButtonTextQtyValue(), "The Qty amount do not match.");
        itemPage.cartButtonView();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> itemPage.nameOfItemInCart.isDisplayed());
        Assertions.assertEquals(name, itemPage.nameOfItemInCartText(), "The name of the product do not match with the name added.");
        Assertions.assertEquals(index, itemPage.qtyInCartNumber(), "The Qty do not match.");
        System.out.println(itemPage.priceInTheCartPage());
        double calculatedPrice = (double) index * (itemPage.priceOfTheItem());
        Assertions.assertEquals(calculatedPrice, itemPage.priceInTheCartPage(), 0.01, "The prices do not match.");
    }

//    BONUS

    @Test
    void searchForWord() {
        MainPage mainPage = new MainPage(driver);
        SearchPage searchPage = new SearchPage(driver);
        mainPage.selectQAButton();
        mainPage.inputNameInSearchBar("Duck");
        mainPage.clickTheButtonSearch();
        assertTrue(searchPage.searchResults("Duck"), "There is an Item not matching the search");
    }

    @Test
    void addAllProductsToTheCart() {
        MainPage mainPage = new MainPage(driver);
        ItemPage itemPage = new ItemPage(driver);
        QAPage qaPage = new QAPage(driver);
        mainPage.selectQAButton();
        qaPage.addingAllProductsToCartPrimitive();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> qaPage.cartButton.isDisplayed());
        assertEquals("4", itemPage.checkTheQtyOfItemAdded(), "The quantity of Item was not added.");

    }

}
