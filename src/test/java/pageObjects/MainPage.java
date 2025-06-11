package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}
