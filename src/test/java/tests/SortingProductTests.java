package tests;

import org.junit.jupiter.api.Test;
import pageObjects.MainPage;

public class SortingProductTests extends  BaseTest {


    @Test
    public void sortingByPriceLowToHighTest() {
        MainPage mainPage = new MainPage(this.driver).go();
        mainPage.validatePriceSorting("Sort by price: low to high");
    }
    @Test
    public void sortingByPriceHighToLowTest() {
        MainPage mainPage = new MainPage(this.driver).go();
        mainPage.validatePriceSorting("Sort by price: high to low");
    }
}
