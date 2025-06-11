package tests;

import helpers.BrowserFactory;
import helpers.ConfigurationReader;
import helpers.NoSuchBrowserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v128.browser.Browser;

public class BaseTest {
    protected WebDriver driver;
    protected Browser browser;
    private static ConfigurationReader configuration;
    @BeforeAll
    public static void loadConfiguration() {
        configuration = new ConfigurationReader();
    }
    @BeforeEach
    public void setup() {
        BrowserFactory browserFactory = new BrowserFactory();
        try {
            driver = browserFactory.createInstance(configuration);
        } catch (NoSuchBrowserException e) {
            throw new RuntimeException(e);
        }
        driver.manage().window().maximize();
    }
    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
