package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends pageObjects.BasePage {

    private By productItem = By.cssSelector("tr.cart_item");
    private By quantityField = By.cssSelector("input.qty");
    private By updateCartButton = By.cssSelector("[name=update_cart]");
    private By totalPrice = By.cssSelector("[data-title=Total]");
    private By productName = By.xpath("//td[contains(@class, 'product-name')]/a");
    private By productQuantity = By.xpath("//td[contains(@class, 'quantity')]/div/input");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage go() {
        driver.get(baseURL + "/cart/");
        return this;
    }

    public int getNumberOfProducts() {
        return driver.findElements(productItem).size();
    }

    public CartPage changeQuantity(int quantity) {
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(String.valueOf(quantity));
        driver.findElement(updateCartButton).click();

        waitForLoadingIconDisappear();
        return this;
    }

    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }

    public String getProductTitle() {
        return driver.findElement(productName).getText();
    }

    public String getProductQuantity() {
        return driver.findElement(productQuantity).getAttribute("value");
    }
}
