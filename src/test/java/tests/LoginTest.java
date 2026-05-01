package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class LoginTest extends BaseTest{

    @Test(dataProvider = "LoginData",  dataProviderClass = DataProviders.class, groups = {"Sanity", "Master"})
    public void loginTest(String email, String password, String dataValuation) {
        logger.info("---Login Test started---");
        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Click on Account dropdown");
            homePage.clickAccountDropdown();
            logger.info("Click on Login Link");
            homePage.clickLoginButton();

            LoginPage loginPage = new LoginPage(driver);
            // String email = prop.getProperty("email");
            // String password = prop.getProperty("password");
            logger.info("Login with email and password");
            loginPage.login(email,password);

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean isMyAccountPage = myAccountPage.isOnAccountPage();

            if (dataValuation.equalsIgnoreCase("valid")) {
                Assert.assertTrue(isMyAccountPage, "Expected successful login and My Account page for valid credentials.");
                myAccountPage.clickLogoutButton();
            } else {
                if(isMyAccountPage){
                    myAccountPage.clickLogoutButton();
                }
                Assert.assertFalse(isMyAccountPage, "Expected login to fail for invalid credentials; My Account page should not appear.");
            }
        } catch (Exception ex) {
            logger.error("Login test failed with unexpected error", ex);
            Assert.fail("Unexpected error during login test: " + ex.getMessage(), ex);
        }
        logger.info("---Login Test finished---");
    }
}
