package pageObjects;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MyAccountPage extends BasePage {

    public final StoreHeaderComponent storeHeader;
    private static final By userNameInput = By.id("username");
    private static final By userPasswordInput = By.id("password");
    private static final By loginButton = By.cssSelector("button[name='login']");
    private static final By navMenuItems = By.cssSelector(".woocommerce-MyAccount-navigation ul li");

    public MyAccountPage(WebDriver driver) {
        super(driver);
        storeHeader = new StoreHeaderComponent(driver);
    }


    public MyAccountPage go() {
        driver.get(baseURL + "/my-account/");
        return this;
    }

    public MyAccountPage logInToAdminAccount(final String username, final String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(userNameInput)).sendKeys(username);
        driver.findElement(userPasswordInput).sendKeys(password);
        driver.findElement(loginButton).click();
        return this;
    }

    public void validateMessageAfterLogIn(String userName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".woocommerce-MyAccount-content p:first-of-type")));
        String actual = normalizeText(messageText.getText());
        String expected = "Hello " + userName + " (not " + userName + "? Log out)";
        expected = normalizeText(expected);
        Assertions.assertEquals(expected, actual, "The welcome message is not correct. Actual: '" + actual + "'");
    }

    public void validateMessageAfterWrongLogin(String userName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".woocommerce-error")));
        String actual = normalizeText(messageText.getText());
        Assertions.assertTrue(actual.contains(userName) && actual.contains("not registered"),
                "Expected error message to mention that '" + userName + "' is not registered. Actual: '" + actual + "'");
    }

    public void validateMenuOptionsVisible(String... expectedNames) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        final List<WebElement> menuOptions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(navMenuItems));

        if (menuOptions.size() != expectedNames.length) {
            throw new AssertionError(
                    "Invalid number of menu options." +
                            "\nExpected: " + expectedNames.length +
                            "\nActual: " + menuOptions.size()
            );
        }
        List<String> actualTexts = new ArrayList<>();
        for (WebElement option : menuOptions) {
            actualTexts.add(normalizeText(option.getText()));
        }
        for (int i = 0; i < expectedNames.length; i++) {
            String expected = normalizeText(expectedNames[i]);
            String actual = actualTexts.get(i);
            if (!actual.equals(expected)) {
                throw new AssertionError(
                        "Invalid menu name at position " + i +
                                "\nExpected: " + expected +
                                "\nActual: " + actual
                );
            }
        }
    }

    private String normalizeText(String text) {
        if (text == null) return "";
        return text.replaceAll("\\s+", " ").trim();
    }
}
