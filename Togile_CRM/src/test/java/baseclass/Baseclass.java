package baseclass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

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
                    "C:\\Users\\Admin\\git\\Togile_CRM\\Togile_CRM\\src\\test\\resources\\config.properties");
            configpro = new Properties();
            configpro.load(configfile);

            // Initializing the properties and load the value.properties
            valuefile = new FileReader(
                    "C:\\Users\\Admin\\git\\Togile_CRM\\Togile_CRM\\src\\test\\resources\\value.properties");
            valuepro = new Properties();
            valuepro.load(valuefile);
        }
        // Opening the Chrome-Browser
        // Control the Cross-browser based on config.properties
        if (configpro.getProperty("Browser").equalsIgnoreCase("chrome")) {
            // Set-up the chrome setting
            ChromeOptions chrome = new ChromeOptions();
            Map<String, Object> option = new HashMap<>();// Store and access the notification by the preference
            option.put("profile.default_content_setting_values.notifications", 2); // Block notifications
            chrome.setExperimentalOption("prefs", option); // Set Chrome options with preferences

            driver = new ChromeDriver(chrome);// Opening the Chrome-Browser
        // Opening the Firefox-Browser
        } else if (configpro.getProperty("Browser").equalsIgnoreCase("firefox")) {

            // Set-up the firefox setting
            FirefoxOptions firefox = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();// Create a custom Firefox profile and disable notifications
                                                          // in Firefox using profile preferences
            profile.setPreference("permissions.default.desktop-notification", 2); // Block desktop notifications
            firefox.setProfile(profile);

            driver = new FirefoxDriver(firefox);// Opening the Firefox-Browser
        // Opening the Edge-Browser  
        } else if (configpro.getProperty("Browser").equalsIgnoreCase("edge")) {
            // Set-up the Edge setting
            EdgeOptions edge = new EdgeOptions();
            Map<String, Object> option = new HashMap<>();// Store and access the notification by the preference
            option.put("profile.default_content_setting_values.notifications", 2); // Block notifications
            edge.setExperimentalOption("prefs", option); // Set Edge options with preferences

            driver = new EdgeDriver(edge);// Opening the Edge-Browser
        }

        // Opening the Url
        driver.get(configpro.getProperty("URL"));
        driver.manage().window().maximize();// Maximize the browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));// set default wait time,when waiting time needed.
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);//Waits for 5 seconds before closing the application
        driver.quit();// Ends the WebDriver session

    }

    public static void screenshot(String shotname, String elementshotname, WebElement element) throws IOException {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String timestamp = formatter.format(date);
        if (shotname == null) {
            File SourceFile = element.getScreenshotAs(OutputType.FILE);
            File desFile = new File(
                    "C:\\Users\\Admin\\git\\Togile_CRM\\Togile_CRM\\Screenshot\\" + elementshotname + "--"
                            + timestamp
                            + ".png");
            FileUtils.copyFile(SourceFile, desFile);
        } else {
            TakesScreenshot fullshot = (TakesScreenshot) driver;
            File SourceFile = fullshot.getScreenshotAs(OutputType.FILE);
            File desFile = new File(
                    "C:\\Users\\Admin\\git\\Togile_CRM\\Togile_CRM\\Screenshot\\" + shotname + "--" + timestamp
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
    public static String otpverify(String email, String apppassword, String mailtype) throws InterruptedException {
    	String otp = null;
		
		Thread.sleep(20000);
		
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect(mailtype,email,apppassword);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            Message latestMessage = messages[messages.length - 1];

            // Get the content of the message
            String content = getTextFromMessage(latestMessage);
            //System.out.println("Email Content:\n" + content);

            // Extract OTP using regex (4 to 6 digits)
            Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()) {
                otp = matcher.group();
                System.out.println("OTP is: " + otp);
            } 
            
            
            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
		return otp;
    }

    // Helper method to extract plain text from MimeMessage
    private static String getTextFromMessage(Message message) throws Exception {
        // If the message is plain text
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } 
        // If the message is HTML
        else if (message.isMimeType("text/html")) {
            return message.getContent().toString();
        } 
        // If the message is multipart (can have multiple parts)
        else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return "";
    }

    // Helper method to extract plain text from MimeMultipart (for multipart messages)
    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();

        // Loop through all body parts and check each part
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            System.out.println("Body Part " + (i + 1) + ": " + bodyPart.getContentType());

            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                result.append(bodyPart.getContent());
            }
        }
        return result.toString();
    }
    
    public static void validation(String placeholderText, String expectedErrorMsg) {
        WebElement field = driver.findElement(By.xpath("//input[@placeholder='" + placeholderText + "']"));
        field.clear();
        driver.findElement(By.xpath("//button[text()='Save']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@placeholder='" + placeholderText + "']/following-sibling::div[contains(@class, 'invalid-feedback')]")
        ));

        if (error.isDisplayed() && error.getText().equalsIgnoreCase(expectedErrorMsg)) {
            System.out.println("✅ '" + placeholderText + "' validation working as expected.");
        } else {
            System.out.println("❌ Validation failed for '" + placeholderText + "'.");
        }
    }

    public static void sorting(List<WebElement> columnCells,String headerName) throws InterruptedException {
    	List<String> columnData = new ArrayList<>();
    	for (WebElement cell : columnCells) {
    	    columnData.add(cell.getText().trim());
    	}

    	// Check if it's a numeric column
    	boolean isNumeric = true;
    	for (String item : columnData) {
    		System.out.println(item);
    	    if (!item.matches("\\d{7,}")) {
    	        isNumeric = false;
    	        break;
    	    }
    	}

    	if (isNumeric) {
    		
    		System.out.println("Togile"+isNumeric);
    	    // Convert to integers or doubles and sort
    	    List<Double> actual = columnData.stream()
    	            .map(Double::parseDouble)
    	            .collect(Collectors.toList());

    	    List<Double> expected = new ArrayList<>(actual);
    	    
    	    Collections.sort(expected);
   	    
    	    System.out.println(columnData);
    	    // Compare
    	    if (actual.equals(expected)) {
    	    	System.out.println("PASS: " + headerName + " is sorted correctly.");
    	    } else {
    	    	System.out.println("FAIL: " + headerName + " is not sorted correctly.");
    	    }
    	    System.out.println();
    	} else {
    	    // Sort as strings
    	    List<String> expected = new ArrayList<>(columnData);  
    		System.out.println("Togile"+isNumeric);
    	    if(headerName.equalsIgnoreCase("First Name")) {
      	    	 Collections.sort(expected);
       	    }else {
          	   Collections.sort(expected, Collections.reverseOrder());
       	    }
      	    
       	    System.out.println(columnData);
       	    // Compare
       	    if (columnData.equals(expected)) {
       	    	System.out.println("PASS: " + headerName + " is sorted correctly.");
       	    } else {
       	    	System.out.println("FAIL: " + headerName + " is not sorted correctly.");
       	    }
       	    System.out.println();
    	}
    }
}
