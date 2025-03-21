package testcases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import baseclass.Baseclass;

public class LoginpageNegativeTestcase extends Baseclass {

	@Test
	public static void value() throws IOException, InterruptedException {
		SoftAssert softassertion = new SoftAssert();

		// Entering Wrong username and paswword
		driver.findElement(By.id("email")).sendKeys("kaaju@techr.com");	
		driver.findElement(By.name("password")).sendKeys("Kaaju@#123");
		//Click sign In button
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//Find the errormessage and get the error text
		String errortypepassword = driver.findElement(By.xpath("/html/body/div/div[3]/div/div/div/div[2]")).getText();
		
		if ((errortypepassword.equals("Email does not exist")) || (errortypepassword.equals("Invalid email"))) {
			
			softassertion.assertTrue(
					errortypepassword.equals("Email does not exist") || errortypepassword.equals("Invalid email"),
					"Failure: Wrong type of MailError." + errortypepassword);
		} else {

			softassertion.assertTrue(errortypepassword.equals(
					"Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, and 1 special character (!@#$%^&*)")
					|| errortypepassword.equals("Password did not match"),
					"Failure: Wrong type of PasswordError." + errortypepassword);
		}
		softassertion.assertAll();
	}
}