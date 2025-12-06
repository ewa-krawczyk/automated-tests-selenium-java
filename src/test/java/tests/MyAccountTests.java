package tests;

import org.junit.jupiter.api.Test;

import pageObjects.MyAccountPage;

public class MyAccountTests extends BaseTest {

    @Test
    public void logInToMyAccountWithValidCredentials() {
        String userName = "admin";
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.go();
        myAccountPage.logInToAdminAccount(userName, "admin");

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
}
