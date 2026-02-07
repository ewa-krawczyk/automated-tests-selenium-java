package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pageObjects.MyAccountPage;

public class MyAccountTests extends BaseTest {

    private static final String userName = "admin";
    private static final String userPassword = "admin";
    private MyAccountPage myAccountPage;

    @BeforeEach
    public void setupMyAccountPage() {
        myAccountPage = new MyAccountPage(driver);
        myAccountPage.go();
    }

    @Test
    public void logInToMyAccountWithValidCredentials() {
        myAccountPage.logInToAdminAccount(userName, userPassword);
        myAccountPage.validateMessageAfterLogIn(userName);
    }

    @Test
    public void logInToMyAccountWithInvalidCredentials() {
        String userName = "wrongUserName";
        String invalidPassword = "wrongPassword";

        myAccountPage.logInToAdminAccount(userName, invalidPassword);
        myAccountPage.validateMessageAfterWrongLogin(userName);
    }

    @Test
    public void checkMenuOptionsVisible() {
        myAccountPage.logInToAdminAccount(userName, userPassword);

        myAccountPage.validateMenuOptionsVisible("Dashboard", "Orders", "Downloads", "Addresses", "Payment methods",
                "Account details", "Logout");
    }
}
