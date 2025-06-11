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
}
