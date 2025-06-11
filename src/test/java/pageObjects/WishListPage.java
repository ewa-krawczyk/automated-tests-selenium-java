package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishListPage extends pageObjects.BasePage {

    private By productItems = By.cssSelector(".wishlist-items-wrapper tr td.product-remove");

    protected WishListPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfProducts() {
        return driver.findElements(productItems).size();
    }
}
