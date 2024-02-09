package lt.techin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(qaPage.isButtonListDisplayed());
    }


    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/names.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromFileFindItemName(String name) {
        MainPage mainPage = new MainPage(driver);
        QAPage qaPage = new QAPage(driver);
        mainPage.selectQAButton();
        qaPage.clickShowItemsInList();
        assertTrue(qaPage.namesOfItemsDisplayed(name), "The item was not found");
        assertEquals(name, qaPage.namesOfItemsDisplayedName(name), "The item was not found");

//        assertTrue(createAccount.isAlertWithTextVisible(errorMessage), "Account was not created: " + errorMessage);


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
        int index = (int)(Math.random()*20);
        mainPage.selectQAButton();
        qaPage.clickShowItemsInList();
        qaPage.clickTheItemDescription(name);
        itemPage.addQty(index);
        String index2 = String.valueOf(index);
        assertEquals(index2,itemPage.checkTheQtyOfItemAdded(), "The quantity of Item was not added.");
    }

    
}
