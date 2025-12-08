package tests;

import org.junit.jupiter.api.Test;

import pageObjects.MyAccountPage;

public class MyAccountTests extends BaseTest {

    private static final String userName = "admin";
    private static final String userPassword = "admin";

    @Test
    public void logInToMyAccountWithValidCredentials() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.go();
        myAccountPage.logInToAdminAccount(userName, userPassword);

        myAccountPage.validateMessageAfterLogIn(userName);
    }

    @Test
    public void logInToMyAccountWithInvalidCredentials() {
        String userName = "wrongUserName";
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.go();
        myAccountPage.logInToAdminAccount(userName, "wrongPassword");

        myAccountPage.validateMessageAfterWrongLogin(userName);
    }

    @Test
    public void checkMenuOptionsVisible() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.go();
        myAccountPage.logInToAdminAccount(userName, userPassword);

        myAccountPage.validateMenuOptionsVisible("Dashboard", "Orders", "Downloads", "Addresses", "Payment methods",
                "Account details", "Logout");
    }
}
