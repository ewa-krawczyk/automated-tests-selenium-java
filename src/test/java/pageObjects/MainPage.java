package pageObjects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends pageObjects.BasePage {
    public final pageObjects.StoreHeaderComponent storeHeader;
    private By searchFieldInputLocator = By.xpath("//*[contains(@id, 'wc-block-search__input')]");
    private By searchButtonLocator = By.xpath("//button[contains(@class, 'wc-block-product-search__button')]");

    public MainPage(WebDriver driver) {
        super(driver);
        storeHeader = new pageObjects.StoreHeaderComponent(driver);
    }

    public MainPage go() {
        driver.get(baseURL);
        return this;
    }

    public void validateMessageOnEmptyCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".wc-block-mini-cart__empty-cart-wrapper p strong")));
        messageText.getText();
        Assertions.assertEquals("Your cart is currently empty!", messageText.getText(), "The message is not correct");
    }

    public void searchProduct(final String searchProductName) {
        WebElement searchField = driver.findElement(searchFieldInputLocator);
        searchField.sendKeys(searchProductName);
        WebElement searchButton = driver.findElement(searchButtonLocator);
        searchButton.click();
    }


}
