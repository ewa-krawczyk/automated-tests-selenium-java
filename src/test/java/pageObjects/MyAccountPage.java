package pageObjects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage extends BasePage {

    public final pageObjects.StoreHeaderComponent storeHeader;
    private By userNameInputLocator = By.xpath("//input[contains(@id, 'username')]");
    private By passwordInputLocator = By.xpath("//input[contains(@id, 'password')]");
    private By logInButtonLocator = By.xpath("//button[contains(@name, 'login')]");

    public MyAccountPage(WebDriver driver) {
        super(driver);
        storeHeader = new pageObjects.StoreHeaderComponent(driver);
    }

    public MyAccountPage go() {
        driver.get(baseURL + "/my-account/");
        return this;
    }

    public MyAccountPage logInToAdminAccount(final String userName, final String password) {
        driver.findElement(userNameInputLocator).sendKeys(userName);
        driver.findElement(passwordInputLocator).sendKeys(password);
        driver.findElement(logInButtonLocator).click();
        return this;
    }

    public void validateMessageAfterLogIn(String userName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".woocommerce-MyAccount-content p:first-of-type")));
        messageText.getText();
        Assertions.assertEquals("Hello " + userName + " (not " + userName + "? Log out)", messageText.getText(), "The message is not correct");
    }
}
