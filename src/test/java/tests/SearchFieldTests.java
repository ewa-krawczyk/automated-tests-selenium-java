package tests;

import org.junit.jupiter.api.Test;
import pageObjects.MainPage;
import pageObjects.ProductPage;


public class SearchFieldTests extends tests.BaseTest {

    @Test
    public void shouldDisplayCorrectProductWhenSearchingByTitle() {
        MainPage mainPage = new MainPage(driver);
        mainPage.go();
        mainPage.searchProduct("How to Do Chemical Tricks by A. Anderson");

        ProductPage productPage = new ProductPage(driver);
        productPage.validateTitleProductIsCorrect("How to Do Chemical Tricks by A. Anderson");
    }
}
