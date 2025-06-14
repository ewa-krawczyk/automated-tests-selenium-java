package tests;

import org.junit.jupiter.api.Test;
import pageObjects.MainPage;


public class SearchFieldTests extends tests.BaseTest {

    @Test
    public void shouldDisplayCorrectProductWhenSearchingByTitle() {
        MainPage mainPage = new MainPage(driver);
        mainPage.go();

        mainPage.searchProduct("How to Do Chemical Tricks by A. Anderson");
        mainPage.validateSearchedProductIsCorrect("How to Do Chemical Tricks by A. Anderson");

    }
}
