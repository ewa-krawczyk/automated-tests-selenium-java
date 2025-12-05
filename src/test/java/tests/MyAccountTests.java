package tests;

import org.junit.jupiter.api.Test;

import pageObjects.MyAccountPage;

public class MyAccountTests extends BaseTest {

    @Test
    public void logInToAdminAccount() {
        String userName = "admin";
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.go();
        myAccountPage.logInToAdminAccount(userName, "admin");

        myAccountPage.validateMessageAfterLogIn(userName);
    }
}
