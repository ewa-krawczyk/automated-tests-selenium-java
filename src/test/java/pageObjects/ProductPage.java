package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends pageObjects.BasePage {

    private By addToCart = By.cssSelector("[name=add-to-cart]");

    private By goToCart = By.cssSelector(".woocommerce-message>.button");

    private By addToWishList = By.cssSelector("a.add_to_wishlist");

    public final pageObjects.StoreHeaderComponent storeHeader;

    public ProductPage(WebDriver driver) {
        super(driver);
        storeHeader = new pageObjects.StoreHeaderComponent(driver);
    }

    public ProductPage go(String productSlug) {
        driver.get(baseURL + "/product/" + productSlug);
        return this;
    }

    public ProductPage addToCart() {
        driver.findElement(addToCart).click();
        return this;
    }

    public pageObjects.CartPage goToCart() {
        driver.findElement(goToCart).click();
        return new pageObjects.CartPage(driver);
    }

    public ProductPage addToWishList() {

        driver.findElement(addToWishList).click();
        waitForLoadingIconDisappear();
        return this;
    }
}
