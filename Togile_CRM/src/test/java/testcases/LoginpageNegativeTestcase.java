package testcases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import baseclass.Baseclass;

public class LoginpageNegativeTestcase extends Baseclass {
	@Test(priority = 0)
	public static void login() throws IOException, InterruptedException {
		Landing_page.logo();
		Landing_page.company();
	}
	@Test(priority = 1)
	public void delete() throws InterruptedException {
		WebDriverWait waittime = new WebDriverWait(driver, Duration.ofSeconds(20));
	System.out.println("🧪" + "Delete Testcases");
	// wait the Bulk action button
	waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk actions']")));
	driver.findElement(By.xpath("//button[text()='Bulk actions']")).click();// Click the Bulk action button
	Thread.sleep(1000);
	// Find the Delete buton
	WebElement deletebutton = driver.findElement(By.xpath("//button[text()='Delete']"));
	if (deletebutton.isDisplayed() && deletebutton.isEnabled()) {
		System.out.println("Delete button is working. - Testcase Passed");
	} else {
		System.out.println("Delete button is not working. - Testcase Failed" + "-" + deletebutton.isDisplayed()
				+ "-" + deletebutton.isEnabled());
	}
	deletebutton.click();// Click the delete button
	// Wait for the error message
	waittime.until(ExpectedConditions.visibilityOfElementLocated(
			By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]")));
	// Find the locator of error message and check
	WebElement deleteerrormesssage = driver.findElement(
			By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
	if (deleteerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
		System.out.println("Delete Error Message displayed correctly. - Testcase Passed");
	} else {
		System.out.println("Delete Error Message not displayed correctly. - Testcase Failed");
	}

	// Get the data of lead which is going to be delete
	WebElement deletedbeforedata = driver
			.findElement(By.xpath("(//div[@class='f-center-h100'])[4]"));
	String deletedbeforevalue = deletedbeforedata.getText();// Get the value of that Lead-Firstname

	driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();// Select the lead
	deletebutton.click();// Click the delete button

	Thread.sleep(2000);
	// Find and check the checkbox of delete
	WebElement deletecheckbox = waittime.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pb-4 modal-body']//input")));
	if (deletecheckbox.isDisplayed() && deletecheckbox.isEnabled()) {
		System.out.println("Delete Checkbox is working. - Testcase Passed");
		deletecheckbox.click();
	} else {
		System.out.println("Delete Checkbox is not working. - Testcase Failed");
	}

	WebElement deletecancelbutton = driver.findElement(By.xpath("//button[text()='Cancel']"));// Cancel button
	WebElement deleteconfirmbutton = driver.findElement(By.xpath("(//button[text()='Delete'])[2]"));// Delete button

	if ((deleteconfirmbutton.isDisplayed() && deleteconfirmbutton.isEnabled())
			&& (deletecancelbutton.isDisplayed() && deletecancelbutton.isEnabled())) {
		System.out.println("Delete - Confirm and Cancel button are working.- Testcase Passed");
		deleteconfirmbutton.click();
	} else {
		System.out.println("Delete - Confirm and Cancel button are not working.- Testcase Failed"
				+ "Delete Button - " + (deleteconfirmbutton.isDisplayed() && deleteconfirmbutton.isEnabled())
				+ "Cancel button - " + (deletecancelbutton.isDisplayed() && deletecancelbutton.isEnabled()));
	}
	Thread.sleep(2000);
	WebElement deletedafterdata = driver
			.findElement(By.xpath("(//div[@class='f-center-h100'])[4]"));
	String deletedaftervalue = deletedafterdata.getText();// Get the value of that lead after delete function happened
	// Compare both value and check
	if (deletedbeforevalue.equalsIgnoreCase(deletedaftervalue)) {
		System.out.println(
				"Delete function is not working. - Testcase Failed" + deletedbeforevalue+"---" + deletedaftervalue);
	} else {
		System.out.println("Delete function is  working. - Testcase Passed");
	}
	driver.navigate().refresh();
	System.out.println("✅" + "Delete Testcases Executed");
	System.out.println();

	}
	@Test(priority = 3)
	public void addcompany() throws InterruptedException {
		driver.navigate().refresh();

		System.out.println("🧪" + "Add Contact Testcases");

		Actions actions = new Actions(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement addcompanybutton = driver.findElement(By.xpath("//button[@class='blue-btn btn btn-secondary']"));

		if (addcompanybutton.isDisplayed() && addcompanybutton.isEnabled()
				&& (addcompanybutton.getText()).equals("Add Companies")) {
			System.out.println("Add Company Button is working.- Testcase Passed");
		} else {
			System.out.println("Add Company Button is not Working. - Testcase Failed");
		}

		wait.until(ExpectedConditions.elementToBeClickable(addcompanybutton));
		addcompanybutton.click();
		if (driver.findElement(By.xpath("//div[@class='customcss-drawer-header']")).isDisplayed()) {
			System.out.println("Add Company Drawer Opened.- Testcase Passed");
		} else {
			System.out.println("Add Company Drawer not Opened.- Testcase Failed");
		}
		
		// FirstName
		WebElement name = driver.findElement(By.xpath("//input[@placeholder='Enter Name']"));
		if (name.isDisplayed() && name.isEnabled()) {
			System.out.println("Name Field is Visible and Editable.- Testcase Passed");
		} else {
			System.out.println("Name Field is not Visible and Editable.- Testcase Failed");
		}
		name.sendKeys(valuepro.getProperty("Name_Company"));

		if (name.getDomProperty("value").equals(valuepro.getProperty("Name_Company"))) {
			System.out.println("Name Field Value Entered Correctly.- Testcase Passed");
		} else {
			System.out.println("Name Field Value Entered Not Correctly.- Testcase Failed");
		}
		// Leads.validation("Enter First Name", "Required Field");

		// Email
		WebElement emailtextbox = driver.findElement(By.xpath("(//input[@class='form-control' and @type='text'])[3]"));
		if (emailtextbox.isDisplayed() && emailtextbox.isEnabled()) {
			System.out.println("Email Field is Visible and Editable.- Testcase Passed");
		} else {
			System.out.println("Email Field is not Visible and Editable.- Testcase Failed");
		}
		emailtextbox.sendKeys(valuepro.getProperty("Email_Id_Company"));
		if (emailtextbox.getDomProperty("value").equals(valuepro.getProperty("Email_Id_Company"))) {
			System.out.println("Email Field Value Entered Correctly.- Testcase Passed");
		} else {
			System.out.println("Email Field Value Entered Not Correctly.- Testcase Failed");
		}

		WebElement addemailbutton = driver.findElement(By.xpath("(//div[@style='cursor: pointer;'])[3]"));
		if (addemailbutton.isDisplayed() && addemailbutton.isEnabled()) {
			System.out.println("Add-Email Button is working.- Testcase Passed");
		} else {
			System.out.println("Add-Email Button is not working.- Testcase Failed");
		}

		// Country Code

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=' css-19bb58m'])[1]//input")));
		WebElement countrycodetextbox = driver.findElement(By.xpath("(//div[@class=' css-19bb58m'])[1]//input"));
		if (countrycodetextbox.isDisplayed() && countrycodetextbox.isEnabled()) {
			System.out.println("CountryCode Field is Visible and Editable.- Testcase Passed");
		} else {
			System.out.println("CountryCode Field is not Visible and Editable.- Testcase Failed");
		}

		countrycodetextbox.sendKeys(valuepro.getProperty("Country_Code_Company"));// Enter the Preferred Value
		actions.sendKeys(Keys.ENTER).perform();// Wait and press ENTER to select the typed value
		if ((driver.findElement(By.xpath("//div[contains(@class, 'singleValue') or contains(@id, 'placeholder')]")).getText())
				.equals(valuepro.getProperty("Country_Code_Company"))) {
			System.out.println("CountryCode Field Value Entered Correctly.- Testcase Passed");
		} else {
			System.out.println("CountryCode Field Value Entered Not Correctly.- Testcase Failed"
					+ (driver.findElement(By.xpath("//div[contains(@class, 'singleValue') or contains(@id, 'placeholder')]")).getText()));
		}

		// Phone Number
		WebElement phonenumbertextbox = driver
				.findElement(By.xpath("(//input[@class='form-control' and @type='text'])[2]"));
		if (phonenumbertextbox.isDisplayed() && phonenumbertextbox.isEnabled()) {
			System.out.println("Phone Number Field is Visible and Editable.- Testcase Passed");
		} else {
			System.out.println("Phone Number Field is not Visible and Editable.- Testcase Failed");
		}
		phonenumbertextbox.sendKeys(valuepro.getProperty("Phone_Number_Company"));
		if (phonenumbertextbox.getDomProperty("value").equals(valuepro.getProperty("Phone_Number_Company"))) {
			System.out.println("Phone Number Field Value Entered Correctly.- Testcase Passed");
		} else {
			System.out.println("Phone Number Field Value Entered Not Correctly.- Testcase Failed");
		}
		WebElement addphonebutton = driver.findElement(By.xpath("(//div[@style='cursor: pointer;'])[2]"));
		if (addphonebutton.isDisplayed() && addphonebutton.isEnabled()) {
			System.out.println("Add-Phone Button is working.- Testcase Passed");
		} else {
			System.out.println("Add-Phone Button is not working.- Testcase Failed");
		}

		// Select Owner dropdown
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=' css-19bb58m'])[2]//input")));
		WebElement Ownerdropdown = driver.findElement(By.xpath("(//div[@class=' css-19bb58m'])[2]//input"));
		Ownerdropdown.click();
		String Expanded4 = Ownerdropdown.getDomAttribute("aria-expanded");// Dropdown Expand Check
		if (Ownerdropdown.isDisplayed() && Ownerdropdown.isEnabled()) {
			System.out.println("Owner dropdown is Visible and Editable.- Testcase Passed");
		} else {
			System.out.println("Owner dropdown is not Visible and Editable.- Testcase Failed");
		}
		if (Expanded4 != null && Expanded4.equals("true")) {
			System.out.println("The Owner dropdown is expanded - TestCase Passed");
		} else {
			System.out.println("The Owner dropdown is collapsed - TestCase Failed" + Expanded4);
		}
		Ownerdropdown.sendKeys(valuepro.getProperty("Owner_Company"));// Enter the Preferred Value
		actions.sendKeys(Keys.ENTER).perform();// Wait and press ENTER to select the typed value

		if ((driver.findElement(By.xpath("(//div[contains(@class,'singleValue')])[2]")).getText())
				.equalsIgnoreCase(valuepro.getProperty("Owner_Company"))) {
			System.out.println("Owner Dropdown Value Selected Correctly - TestCase Passed");
		} else {
			System.out.println("Owner Dropdown Value Not Selected Correctly - TestCase Failed"
					+ (driver.findElement(By.xpath("(//div[contains(@class,'singleValue')])[2]")).getText()));
		}

		// Cancel Button
		WebElement cancelbutton = driver.findElement(By.xpath("//button[text()='Cancel']"));
		if (cancelbutton.isDisplayed() && cancelbutton.isEnabled()) {
			System.out.println("Cancel Button is Visible and Clickable.- Testcase Passed");
		} else {
			System.out.println("Cancel Button is Not Visible and Clickable.- Testcase Failed");
		}
		// cancelbutton.click();

		// Closs Button
		WebElement closebutton = driver.findElement(By.xpath("//div[@class='cursor-pointer']"));
		if (closebutton.isDisplayed() && closebutton.isEnabled()) {
			System.out.println("Close Button is Visible and Clickable.- Testcase Passed");
		} else {
			System.out.println("Close Button is Not Visible and Clickable.- Testcase Failed");
		}
		// closebutton.click();

		// Click Save Button
		WebElement savebutton = driver.findElement(By.xpath("//button[@type='submit']"));
		if (savebutton.isDisplayed() && savebutton.isEnabled()) {
			System.out.println("Save Button is Visible and Clickable.- Testcase Passed");
		} else {
			System.out.println("Save Button is Not Visible and Clickable.- Testcase Failed");
		}
		savebutton.click();

		driver.navigate().refresh();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='f-center-h100 '])[1]")));
		WebElement addedcompany = driver.findElement(By.xpath("(//div[@class='f-center-h100 '])[1]"));
		if (addedcompany.getText().contains(valuepro.getProperty("Name_Company"))) {
			System.out.println("Company Created Successfully. - Testcase Passed");
		} else {
			System.out.println("Company Not Created. - Testcase Failed" + addedcompany.getText());
		}
		System.out.println("✅" + "Add Company Testcases Executed");
		System.out.println();
	}

}