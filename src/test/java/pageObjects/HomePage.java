package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement registerButton;

    @FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Login']")
    WebElement loginButton;

    @FindBy(xpath = "//li[@class='list-inline-item']//div[@class='dropdown']")
    WebElement accountDropdown;

    public void clickAccountDropdown() {
        accountDropdown.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickRegisterButton() {
        registerButton.click();
    }
}
