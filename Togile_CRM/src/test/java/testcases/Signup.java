package testcases;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import baseclass.Baseclass;

public class Signup extends Baseclass {

	@Test(testName = "SignUp")
	public static void signuppage() throws InterruptedException {

		Actions actions = new Actions(driver);
		
		//Click SignUp Button
		driver.findElement(By.xpath("//a[text()='Sign Up']")).click();// Click SignUp Button
		Thread.sleep(2000);
		String SignUppage = driver.findElement(By.xpath("//h1[text()='Sign Up']")).getText();
		if(SignUppage.equals("Sign Up")) {
			System.out.println("Sign UP Section Testcase Passed");
		}else {
			System.out.println("Sign UP Section Testcase Failed");
		}
		
		//Enter Full Name
		WebElement fullname = driver.findElement(By.id("fullName"));// Enter FullName of User
		fullname.sendKeys(valuepro.getProperty("Fullname"));
		if(fullname.getDomProperty("value").equals("Fullname")){
			System.out.println("FullName Testcase Passed");
		}else {
			System.out.println("FullName Testcase Failed");
		}
		
		WebElement companyname = driver.findElement(By.id("CompanyName"));// Enter Company Name
		companyname.sendKeys(valuepro.getProperty("CompanyName"));
		if(companyname.getDomProperty("value").equals("ComapanyName")) {
			System.out.println("Company Name Testcase Passed");
		}else {
			System.out.println("Company Name Testcase Failed");
		}

		// Select Industry dropdown
		WebElement industry = driver.findElement(By.id("react-select-2-input"));
		industry.click();
		String Expanded1 = industry.getDomAttribute("aria-expanded");// Dropdown Expand Check
		if (Expanded1 != null && Expanded1.equals("true")) {
			System.out.println("The Industry dropdown is expanded - TestCase Passed");
		} else {
			System.out.println("The Industry dropdown is collapsed - TestCase Failed" + Expanded1);
		}

		industry.sendKeys(valuepro.getProperty("Industry"));
		actions.sendKeys(org.openqa.selenium.Keys.ENTER).perform();// Wait and press ENTER to select the typed value

		WebElement industrydropdownvalue = driver.findElement(By.xpath("//input[@value][@name='industry']"));
		if (industrydropdownvalue.getDomAttribute("value").equalsIgnoreCase(valuepro.getProperty("Industry"))) {
			System.out.println("Industry Dropdown Value Selected Correctly - TestCase Passed");
		} else {
			System.out.println("Industry Dropdown Value Not Selected Correctly - TestCase Failed");
		}

		// Select Country dropdown
		WebElement country = driver.findElement(By.id("react-select-3-input"));
		country.click();
		String Expanded2 = country.getDomAttribute("aria-expanded");// Dropdown Expand Check
		if (Expanded2 != null && Expanded2.equals("true")) {
			System.out.println("The Country dropdown is expanded - TestCase Passed");
		} else {
			System.out.println("The Country dropdown is collapsed - TestCase Failed" + Expanded2);
		}
		country.sendKeys(valuepro.getProperty("Country"));// Enter the Preferred Value
		actions.sendKeys(org.openqa.selenium.Keys.ENTER).perform();// Wait and press ENTER to select the typed value

		WebElement countrydropdownvalue = driver.findElement(By.xpath("//input[@value][@name='country']"));
		if (countrydropdownvalue.getDomAttribute("value").equalsIgnoreCase(valuepro.getProperty("Country"))) {
			System.out.println("Country Dropdown Value Selected Correctly - TestCase Passed");
		} else {
			System.out.println("Country Dropdown Value Not Selected Correctly - TestCase Failed");
		}

		// Select Timezone dropdown
		WebElement timezone = driver.findElement(By.id("react-select-4-input"));
		timezone.click();
		String Expanded3 = timezone.getDomAttribute("aria-expanded");// Dropdown Expand Check
		if (Expanded3 != null && Expanded3.equals("true")) {
			System.out.println("The Timezone dropdown is expanded - TestCase Passed");
		} else {
			System.out.println("The Timezone dropdown is collapsed - TestCase Failed" + Expanded3);
		}
		timezone.sendKeys(valuepro.getProperty("Timezone"));// ENter the preferred Value
		actions.sendKeys(org.openqa.selenium.Keys.ENTER).perform();// Wait and press ENTER to select the typed value

		WebElement timezonedropdownvalue = driver.findElement(By.xpath("//input[@value][@name='timezone']"));
		if (timezonedropdownvalue.getDomAttribute("value").equalsIgnoreCase(valuepro.getProperty("Timezone"))) {
			System.out.println("Timezone Dropdown Value Selected Correctly - TestCase Passed");
		} else {
			System.out.println("Timezone Dropdown Value Not Selected Correctly - TestCase Failed");
		}

		// Select Currency dropdown
		WebElement currency = driver.findElement(By.id("react-select-5-input"));
		currency.click();
		String Expanded4 = currency.getDomAttribute("aria-expanded");// Dropdown Expand Check
		if (Expanded4 != null && Expanded4.equals("true")) {
			System.out.println("The Currency dropdown is expanded - TestCase Passed");
		} else {
			System.out.println("The Currency dropdown is collapsed - TestCase Failed" + Expanded4);
		}
		currency.sendKeys(valuepro.getProperty("Currency"));// ENter the Preferred Value
		actions.sendKeys(org.openqa.selenium.Keys.ENTER).perform();// Wait and press ENTER to select the typed value
		WebElement currencydropdownvalue = driver.findElement(By.xpath("//input[@value][@name='currency']"));
		if (currencydropdownvalue.getDomAttribute("value").equalsIgnoreCase(valuepro.getProperty("Currency"))) {
			System.out.println("Currency Dropdown Value Selected Correctly - TestCase Passed");
		} else {
			System.out.println("Currency Dropdown Value Not Selected Correctly - TestCase Failed");
		}

		// Select Country Code
		WebElement countrycode = driver.findElement(By.xpath("//button[@title]"));
		countrycode.click();
		String Expanded5 = countrycode.getDomAttribute("aria-expanded");// Dropdown Expand Check
		if (Expanded5 != null && Expanded5.equals("true")) {
			System.out.println("The Countrycode dropdown is expanded - TestCase Passed");
		} else {
			System.out.println("The Countrycode dropdown is collapsed - TestCase Failed" + "-" + Expanded5);
		}
		WebElement countrycodevalue = driver
				.findElement(By.xpath("//li[@aria-label='India +91']//span[contains(text(), 'India')]"));// Enter the
																											// Preferred
																											// value
		countrycodevalue.click();

		WebElement currencycodedropdownvalue = driver.findElement(By.xpath("//button[@title]"));
		if (currencycodedropdownvalue.getDomAttribute("title").equalsIgnoreCase(valuepro.getProperty("Countrycode"))) {
			System.out.println("Countrycode Dropdown Value Selected Correctly - TestCase Passed");
		} else {
			System.out.println("Countrycode Dropdown Value Not Selected Correctly - TestCase Failed");
		}
		
		//Enter Phone Number
		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys(valuepro.getProperty("PhoneNumber"));
		
		@Nullable
		String actualphonenumber = driver.findElement(By.xpath("//input[@type='tel']")).getDomProperty("value");
		if(actualphonenumber.length()>=10){
			System.out.println("Phone Number Testcase Passed");
		}else {
			System.out.println("Phone Number Testcase Failed" + actualphonenumber);
		}

		// Enter Email Id
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(valuepro.getProperty("Email"));
		String actualemailvalue = driver.findElement(By.xpath("//input[@id='email']")).getDomProperty("value");
		if (actualemailvalue.equalsIgnoreCase(valuepro.getProperty("Email"))) {
			System.out.println("Email Id Testcase Passed");
		} else {
			System.out.println("Email Id Testcase Failed");
		}

		// Send and Fetch OTP
		String email = "gokul.selvaraj@togile.com";
		String apppassword = "dgH8a0mkmVbV";
		String mailtype = "imap.zoho.in";
		driver.findElement(By.xpath("//button[text()='Send OTP']")).click();
		String otp = Baseclass.otpverify(email,apppassword,mailtype);// Passing argument of mail
		if (otp.length() == 6) {
			System.out.println("OTP Fetch Testcase Passed");
		} else {
			System.out.println("OTP Fetch Testcase Failed");
		}

		// Enter and Confirm OTP
		driver.findElement(By.xpath("//input[@placeholder='Enter your OTP *']")).sendKeys(otp);
		String actualotp = driver.findElement(By.xpath("//input[@placeholder='Enter your OTP *']"))
				.getDomProperty("value");
		if (actualotp.equals(otp)) {
			System.out.println("OTP Value Testcase Passed");
		} else {
			System.out.println("OTP Value Testcase Failed");
		}
		// driver.findElement(By.xpath("//button[text()='Confirm OTP']")).click();

		// Enter the Password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(valuepro.getProperty("Password"));
		String actualpassword = driver.findElement(By.xpath("//input[@name='password']")).getDomProperty("value");
		if (actualpassword.equals(valuepro.getProperty("Password"))) {
			System.out.println("Password Testcase Passed");
		} else {
			System.out.println("Password Testcase Failed");
		}

		// Confirm the Password
		driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys(valuepro.getProperty("Password"));
		String actualconfirmpassword = driver.findElement(By.xpath("//input[@name='password']"))
				.getDomProperty("value");
		if (actualconfirmpassword.equals(valuepro.getProperty("Password"))) {
			System.out.println("Confirm Password Testcase Passed");
		} else {
			System.out.println("Confirm Password Testcase Failed");
		}
		
		//Click SignUp Button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
}
