package pageObjects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void validateProductListsIsEmpty() {
        List<WebElement> messages = driver.findElements(
                By.xpath("//*[contains(@class, 'woocommerce-no-products-found')]"));
        Assertions.assertFalse(messages.isEmpty(), "Expected 'no products found' message, but none was displayed.");

        String messageText = messages.get(0).getText();
        Assertions.assertEquals("No products were found matching your selection.", messageText,
                "The message text is not correct.");
    }


    public void validatePriceSorting(String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement sortDropdown = driver.findElement(By.name("orderby"));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(optionText);

        wait.until(ExpectedConditions.stalenessOf(sortDropdown));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.products li.product")));

        List<Double> actualPrices = getPricesFromUI();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);

        if (optionText.toLowerCase().contains("low to high")) {
            Collections.sort(expectedPrices);
            Assertions.assertEquals(expectedPrices, actualPrices, "BŁĄD: Ceny nie są ułożone od najniższej!");
        }
        else if (optionText.toLowerCase().contains("high to low")) {
            Collections.sort(expectedPrices, Collections.reverseOrder());
            Assertions.assertEquals(expectedPrices, actualPrices, "BŁĄD: Ceny nie są ułożone od najwyższej!");
        }
    }

    private List<Double> getPricesFromUI() {
        List<WebElement> productCards = driver.findElements(By.cssSelector("ul.products li.product"));
        List<Double> prices = new ArrayList<>();

        for (WebElement card : productCards) {
            WebElement priceElement;

            List<WebElement> salePrice = card.findElements(By.cssSelector("ins .woocommerce-Price-amount bdi"));

            if (!salePrice.isEmpty()) {
                priceElement = salePrice.get(0);
            } else {
                priceElement = card.findElement(By.cssSelector(".woocommerce-Price-amount bdi")); // Bierzemy zwykłą
            }
            String cleanPrice = priceElement.getText().replaceAll("[^\\d,.]", "").replace(",", ".");
            prices.add(Double.valueOf(cleanPrice));
        }
        return prices;
    }
}
