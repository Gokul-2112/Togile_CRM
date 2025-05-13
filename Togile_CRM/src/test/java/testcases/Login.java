package testcases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import baseclass.Baseclass;

public class Login extends Baseclass {

    @Test(testName = "Login")
    public static void loginpage() throws IOException, InterruptedException {
    	
    	try {
    		// Find and entering credentials for Username and Password using config.Properties
            driver.findElement(By.id("email")).sendKeys(configpro.getProperty("Username"));
            driver.findElement(By.name("password")).sendKeys(configpro.getProperty("Password"));
            
            // Find and click the Login button
            driver.findElement(By.xpath("//button[text()='Sign In']")).click();

            // Take entire screen Screenshot when all value entered and click login button
            Baseclass.screenshot("Login Page\\After Value Enter", null, null);

            // To verify login check the Logo on the Landing page
            WebElement actuallogo = driver.findElement(By.xpath("//a[@class='main-logo']"));
            if(actuallogo.isDisplayed()) {
            	System.out.println("Login Testcase Passed");
            }
            else {
            	System.out.println("Login Testcase Failed");
            }
            
            Thread.sleep(1000);// wait the application for 1 second

            // Take entire screen Screenshot when reached Landing Page
            Login.screenshot("Login Page\\After Logged In", null, null);
            
		} catch (Exception e) {
			 System.out.println("Login Testcase Failed: " + e.getMessage());
			 e.printStackTrace();
		}
        
    }
}
