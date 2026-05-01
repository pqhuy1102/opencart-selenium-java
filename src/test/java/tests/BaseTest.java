package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilities.ExtentReportManager;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public Logger logger;
    public Properties prop;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass(groups={"Regression", "Sanity", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException {
        // loading properties
        FileReader fileReader = new FileReader("./src//test//resources//config.properties");
        prop = new Properties();
        prop.load(fileReader);

        logger = LogManager.getLogger(this.getClass());

        if(prop.getProperty("execution_env").equalsIgnoreCase("remote")){
            DesiredCapabilities caps = new DesiredCapabilities();

            if(os.equalsIgnoreCase("Mac")){
                caps.setPlatform(Platform.MAC);
            } else if(os.equalsIgnoreCase("Windows")) {
                caps.setPlatform(Platform.WINDOWS);
            } else if(os.equalsIgnoreCase("Linux")) {
                caps.setPlatform(Platform.LINUX);
            } else {
                System.out.println("No OS matching!");
                return;
            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    caps.setBrowserName("chrome");
                    break;
                case "firefox":
                    caps.setBrowserName("firefox");
                    break;
                case "safari":
                    caps.setBrowserName("safari");
                    break;
                case "edge":
                    caps.setBrowserName("MicrosoftEdge");
                    break;
               default:
                   caps.setBrowserName("chrome");
                   return;
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        } else {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    System.out.println("Invalid browser");
                    return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://opencart-4103-opencart-1:80");
        driver.manage().window().maximize();

        extent = ExtentReportManager.getInstance();
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown() throws IOException {
        driver.quit();
        extent.flush();
        File report = new File("./src//test//java//reports/index.html");
        Desktop.getDesktop().browse(report.toURI());
    }
}
