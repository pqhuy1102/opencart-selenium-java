package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountRegisterPage extends BasePage {
    WebDriverWait wait;

    public AccountRegisterPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement agreePolicyButton;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    WebElement registerButton;

    public void setFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickAgreePolicyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(agreePolicyButton));
        new Actions(driver).moveToElement(agreePolicyButton).click().perform();
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        new Actions(driver).moveToElement(registerButton).click().perform();
    }

    public void registerAccount(String firstName,
                                String lastName,
                                String email,
                                String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        clickAgreePolicyButton();
        clickRegisterButton();
    }
}
