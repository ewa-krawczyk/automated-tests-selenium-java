package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreHeaderComponent extends pageObjects.BasePage {

    private By goToWishListFromHeader = By.cssSelector("#menu-item-88 a");

    protected StoreHeaderComponent(WebDriver driver) {
        super(driver);
    }

    public pageObjects.WishListPage goToWishList() {
        driver.findElement(goToWishListFromHeader).click();
        return new pageObjects.WishListPage(driver);
    }
}
