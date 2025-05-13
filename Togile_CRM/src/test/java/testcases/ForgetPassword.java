package testcases;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import baseclass.Baseclass;

public class ForgetPassword extends Baseclass{

	@Test(testName = "ForgetPassword")
	public static void forgetpassword() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//Click Forget Password on SignIn page
		driver.findElement(By.xpath("//div[@class='text-end']//a")).click();
		
		//Check ForgetPassword page is opened
		String forgetpasswordsection = driver.findElement(By.xpath("//h1[text()='Forgot your password?']")).getText();
		if(forgetpasswordsection.equals("Forgot your password?")) {
			System.out.println("ForgetPassword section Testcase Passed");
		}else {
			System.out.println("ForgetPassword section Testcase Failed");
		}
		
		//Enter Registered EmailId
		driver.findElement(By.id("exampleEmail")).sendKeys(valuepro.getProperty("EmailId"));
		@Nullable
		String actualregisteredemail = driver.findElement(By.id("exampleEmail")).getDomProperty("value");
		if(actualregisteredemail.equals(valuepro.getProperty("EmailId"))){
			System.out.println("Registered Email Enter Testcase Passed");
		}else {
			System.out.println("Registered Email Enter Testcase Failed");
		}
		
		driver.findElement(By.xpath("//button[text()='Send OTP']")).click();//Click the Send OTP button
		
		//Wait untill Confirm OTP page loads
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Confirm OTP']")));
		
		String Confirmotptext = driver.findElement(By.xpath("//button[text()='Confirm OTP']")).getText();
		if(Confirmotptext.equals("Confirm OTP")) {
			System.out.println("OTP Page Testcase Passed");
		}else {
			System.out.println("OTP Page Testcase Failed");
		}
		
		//Fetch the OTP from mail
		String email = "check.out.the.past@gmail.com";
		String apppassword = "dfoqwkulwyyskpwc";
		String mailtype= "imap.gmail.com";
		
		String otp = Baseclass.otpverify(email, apppassword, mailtype);
		
		//Enter the fetched OTP
		driver.findElement(By.id("otp")).sendKeys(otp);
		@Nullable
		String OTPvalue = driver.findElement(By.id("otp")).getDomProperty("value");
		if(OTPvalue.equals(otp)) {
			System.out.println("OTP value Entered Testcase Passed");
		}else {
			System.out.println("OTP value Entered Testcase Failed");
		}
		
		//Submit the OTP
		driver.findElement(By.xpath("//button[text()='Confirm OTP']")).click();
		
		WebElement Setpassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Set a Password']")));
		
		if(Setpassword.isDisplayed()) {
			System.out.println("Set Password section Testcase Passed");
		}else {
			System.out.println("Set Password section Testcase Failed");
		}
		
		//Enter New Password
		driver.findElement(By.id("password")).sendKeys(valuepro.getProperty("Password"));
		driver.findElement(By.id("confirmPassword")).sendKeys(valuepro.getProperty("Password"));
		
		@Nullable
		String password = driver.findElement(By.id("password")).getDomProperty("value");
		@Nullable
		String confirmpassword = driver.findElement(By.id("confirmPassword")).getDomProperty("value");
		
		if(password.equals(confirmpassword)) {
			System.out.println("Password Testcase Passed");
		}else {
			System.out.println("Password Testcase Failed");
		}
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();//Click the Submit button
		
	}
}
