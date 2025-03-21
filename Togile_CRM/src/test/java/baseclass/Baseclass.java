package baseclass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Baseclass {

    public static WebDriver driver;// Declare WebDriver for browser control
    public static Properties configpro;// Declare Properties for configuration settings
    public static Properties valuepro;// Declare Properties for value settings
    public static FileReader configfile;// Declare FileReader for reading configuration files
    public static FileReader valuefile;// Declare FileReader for reading value files

    @BeforeClass
    public void setup() throws IOException, InterruptedException {
        if (driver == null) {

            // Initializing the properties and load the config.properties
            configfile = new FileReader(
                    "C:\\Users\\Admin\\eclipse-workspace\\Togile_CRM\\src\\test\\resources\\config.properties");
            configpro = new Properties();
            configpro.load(configfile);

            // Initializing the properties and load the value.properties
            valuefile = new FileReader(
                    "C:\\Users\\Admin\\eclipse-workspace\\Togile_CRM\\src\\test\\resources\\value.properties");
            valuepro = new Properties();
            valuepro.load(valuefile);
        }
        // Control the Cross-browser based on config.properties
        if (configpro.getProperty("Browser").equalsIgnoreCase("chrome")) {
            // Set-up the chrome setting
            ChromeOptions chrome = new ChromeOptions();
            Map<String, Object> option = new HashMap<>();// Store and access the notification by the preference
            option.put("profile.default_content_setting_values.notifications", 2); // Block notifications
            chrome.setExperimentalOption("prefs", option); // Set Chrome options with preferences

            // Opening the Chrome-Browser
            driver = new ChromeDriver(chrome);
        } else if (configpro.getProperty("Browser").equalsIgnoreCase("firefox")) {

            // Set-up the firefox setting
            FirefoxOptions firefox = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();// Create a custom Firefox profile and disable notifications
                                                          // in Firefox using profile preferences
            profile.setPreference("permissions.default.desktop-notification", 2); // Block desktop notifications
            firefox.setProfile(profile);

            // Opening the Firefox-Browser
            driver = new FirefoxDriver(firefox);
        } else if (configpro.getProperty("Browser").equalsIgnoreCase("edge")) {
            // Set-up the Edge setting
            EdgeOptions edge = new EdgeOptions();
            Map<String, Object> option = new HashMap<>();// Store and access the notification by the preference
            option.put("profile.default_content_setting_values.notifications", 2); // Block notifications
            edge.setExperimentalOption("prefs", option); // Set Edge options with preferences

            // Opening the Chrome-Browser
            driver = new EdgeDriver(edge);
        }

        // Opening the Url
        driver.get(configpro.getProperty("URL"));
        driver.manage().window().maximize();// Maximize the browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));// set default wait time,when waiting time needed.
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();// Ends the WebDriver session

    }

    public static void screenshot(String shotname, String elementshotname, WebElement element) throws IOException {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String timestamp = formatter.format(date);
        if (shotname == null) {
            File SourceFile = element.getScreenshotAs(OutputType.FILE);
            File desFile = new File(
                    "C:\\Users\\Admin\\eclipse-workspace\\Togile_CRM\\Screenshot\\" + elementshotname + "--"
                            + timestamp
                            + ".png");
            FileUtils.copyFile(SourceFile, desFile);
        } else {
            TakesScreenshot fullshot = (TakesScreenshot) driver;
            File SourceFile = fullshot.getScreenshotAs(OutputType.FILE);
            File desFile = new File(
                    "C:\\Users\\Admin\\eclipse-workspace\\Togile_CRM\\Screenshot\\" + shotname + "--" + timestamp
                            + ".png");
            FileUtils.copyFile(SourceFile, desFile);
        }

    }
    
    public static void waitingtime(String locator, String value) {
        WebDriverWait waittime = new WebDriverWait(driver, Duration.ofSeconds(10));

        if (value != null) {
            waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        } else {
            waittime.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        }
    }
}
