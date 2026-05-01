package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passwordInput;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement loginButton;

    @FindBy(xpath = "//h1[normalize-space()='My Account']")
    WebElement myAccountTitle;

    public void setEmailInput(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void setPasswordInput(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }


    public void login(String email, String password) {
        setEmailInput(email);
        setPasswordInput(password);
        loginButton.click();
    }
}
