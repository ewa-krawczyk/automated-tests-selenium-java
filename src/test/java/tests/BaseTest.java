package tests;

import helpers.BrowserFactory;
import helpers.ConfigurationReader;
import helpers.NoSuchBrowserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected static final String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
    protected static final String historyOfAstronomySlug = "/history-of-astronomy-by-george-forbes/";

    protected WebDriver driver;
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
