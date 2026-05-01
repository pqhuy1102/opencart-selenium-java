package tests;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class AccountRegisterTest extends BaseTest {

    @Test(groups={"Regression", "Master"})
    void registerAccount() throws InterruptedException, IOException {
        logger.info("--- Start Register Account test ---");
        test = extent.createTest("Register Account Test");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickAccountDropdown();
            logger.info("Click on My Account dropdown");
            Thread.sleep(1000);

            homePage.clickRegisterButton();
            logger.info("Click on Register link");
            Thread.sleep(1000);

            AccountRegisterPage accountRegisterPage = new AccountRegisterPage(driver);
            String firstName = "Huy";
            String lastName = "Pham";
            Random  random = new Random();
            String email = firstName + lastName + random.nextInt(999) + "@gmail.com";
            String password = "123456";
            accountRegisterPage.registerAccount(firstName, lastName, email, password);
            Thread.sleep(3000);

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            String confirmMessage = myAccountPage.getAccountCreatedMessage();
            String expectedMessage = "Your Account Has Been Created!";
            Thread.sleep(2000);


            Assert.assertEquals(confirmMessage, expectedMessage);
            test.pass("Account Created Successfully");
        }
        catch (Throwable t) {
            logger.error("--- Error in Register Account test ---");
            logger.error("Error message: {}", t.getMessage());
            logger.debug("Debug...");
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("./src//test//java//reports//screenshot-debug-1.png"));
            // In URL hiện tại
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page title: " + driver.getTitle());
            Assert.fail("Error" + t.getMessage());
            test.fail("Error in Register Account test");
        }



        logger.info("--- End Register Account test ---");


    }
}
