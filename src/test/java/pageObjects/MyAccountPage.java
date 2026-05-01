package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[normalize-space()='My Account']")
    WebElement myAccountTitle;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement accountCreatedMessage;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logoutButton;

    Actions actions = new Actions(driver);


    public String getAccountCreatedMessage() {
        try {
            return accountCreatedMessage.getText();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Returns whether the My Account heading is visible. Does not throw if login failed or
     * another page is shown — avoids relying on catch blocks in tests for the negative case.
     */
    public boolean isOnAccountPage() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='My Account']")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickLogoutButton() {
        actions.moveToElement(logoutButton).click().perform();
    }
}
