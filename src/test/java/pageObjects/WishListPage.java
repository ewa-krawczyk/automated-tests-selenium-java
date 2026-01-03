package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class WishListPage extends pageObjects.BasePage {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private final By productItems = By.cssSelector(".wishlist-items-wrapper tr td.product-remove");
    String productItemsTable = "//*[contains(@class, 'wishlist-items-wrapper')]";

    protected WishListPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfProducts() {
        return driver.findElements(productItems).size();
    }


    public WishListPage removeAddedProductFromWishList(String productName) {
        WebElement table = driver.findElement(By.xpath(productItemsTable));
        WebElement row = table.findElements(By.xpath("tr"))
                .stream()
                .filter(tr ->
                        tr.findElement(By.xpath("td[3]/a"))
                                .getText()
                                .contains(productName)
                )
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Nie znaleziono produktu o nazwie: " + productName
                ));
        row.findElement(By.xpath(".//td[1]"))
                .click();
        wait.until(ExpectedConditions.stalenessOf(row));

        return new WishListPage(this.driver);
    }
}
