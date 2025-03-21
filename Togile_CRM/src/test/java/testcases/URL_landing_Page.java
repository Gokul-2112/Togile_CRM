package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import baseclass.Baseclass;

public class URL_landing_Page extends Baseclass {

	SoftAssert softassertion = new SoftAssert();// Used to show what type of error

	@Test(priority = 0,testName = "Title")
	public void title() {
		// Verifing the Title
		String title = driver.getTitle();
		softassertion.assertEquals(title, "CM", "Failure: Title not same as requirement.");
	}

	@Test(priority = 1)
	public void URL() {
		// Verifying the Launched URL
		String URL = driver.getCurrentUrl();
		softassertion.assertEquals(URL, configpro.getProperty("URL"), "Failure: URL not same as requirement.");
	}

	@Test(priority = 2)
	public void Logo() throws IOException {
		// Verifying th Logo
		WebElement Logo = driver.findElement(By.xpath("//img[@alt='Logo']"));
		softassertion.assertTrue(Logo.isDisplayed(), "Failure: Logo is not displayed.");
		Baseclass.waitingtime("//img[@alt='Logo']", "visible");
		Baseclass.screenshot(null, "URL_Landing_Page\\Logo", Logo);
	}

	@Test(priority = 3)
	public void dontHaveAccountMessagetext() {
		// Verifying the Text(Don't have an account)
		String dontHaveAccountMessage_text = driver.findElement(By.xpath("(//p)[2]")).getText();
		softassertion.assertEquals(dontHaveAccountMessage_text, "Don't have an account? Sign Up",
				"Failure: Don'tHaveAccountMessage_text is not displayed.");
	}

	@Test(priority = 4)
	public void signUp() {
		// Verifying the Sign Up Link
		WebElement signup = driver.findElement(By.xpath("(//a[@class='page_resetLink__c7AP3'])[1]"));
		softassertion.assertTrue(signup.isEnabled(), "Failure: SignUp is not Clickable.");

	}

	@Test(priority = 5)
	public void usernametextbox() {
		// Verifying the Username textbox
		WebElement un_testbox = driver.findElement(By.id("email"));
		softassertion.assertTrue(un_testbox.isDisplayed() & un_testbox.isEnabled(),
				"Failure: Username textbox is not enabled.");

	}

	@Test(priority = 6)
	public void passwordtextbox() {
		// Verifying the Password Textbox
		WebElement password_testbox = driver.findElement(By.name("password"));
		softassertion.assertTrue(password_testbox.isEnabled() & password_testbox.isDisplayed(),
				"Failure: Password textbox is not enabled.");

	}

	@Test(priority = 7)
	public void loginbutton() {
		// verifying the Login Button
		WebElement loginbutton = driver.findElement(By.xpath("//button[text()='Sign In']"));
		softassertion.assertTrue(loginbutton.isDisplayed() && loginbutton.isEnabled(),
				"Failure: Login Button is not Clickable.");

	}

	@Test(priority = 8)
	public void eyebutton() {
		// Verifying the Pass hide/view button
		WebElement eyebutton = driver.findElement(By.xpath("(//a[@class='page_resetLink__c7AP3'])[2]"));
		softassertion.assertTrue(eyebutton.isEnabled(), "Failure: Hide/Show Password button is not enabled.");

	}

	@Test(priority = 9)
	public void forgetpasswordlink() {
		// Verifying the Forget Password Link
		WebElement forgetpassword = driver.findElement(By.xpath("(//a[@class='page_resetLink__c7AP3'])[2]"));
		softassertion.assertTrue(forgetpassword.isEnabled(), "Failure: Forgetpassword link is not enabled.");

	}

	@Test(priority = 10)
	public void footertext() {
		// verfying the Footer text
		WebElement copyrightText = driver.findElement(By.xpath(
				"//div[@class='page_sectionFooter__zSkLy']//p[contains(text(), 'Copyright © 2024 Togile, Inc. All Rights Reserved.')]"));
		softassertion.assertTrue(copyrightText.isDisplayed(), "Failure: Copyright text is not displayed.");

		WebElement madeWithText = driver
				.findElement(By.xpath("//div[@class='page_sectionFooter__zSkLy']//p[contains(text(), 'Made with ')]"));
		softassertion.assertTrue(madeWithText.isDisplayed(), "Failure: 'Made with' text is not displayed.");

		// Locate the <svg> element
		WebElement svgElement = driver
				.findElement(By.cssSelector("body > div > div.page_sectionFooter__zSkLy > p:nth-child(2) > svg"));
		softassertion.assertTrue(svgElement.isDisplayed(), "Failure: SVG element is not displayed.");

		// Verifying the Hyperlink
		WebElement link = driver
				.findElement(By.xpath("//div[@class='page_sectionFooter__zSkLy']//a[@href='https://techisor.com/']"));
		softassertion.assertTrue(link.isDisplayed(), "Failure: Hyperlink to 'https://techisor.com/' is not displayed.");
		
	}
	@Test(priority = 11)
	public void error() {
		softassertion.assertAll();
	}

}
