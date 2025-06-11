package testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import baseclass.Baseclass;

public class Company extends Baseclass {

	@Test(priority = 0)
	public static void login() throws IOException, InterruptedException {
		Landing_page.logo();
		Landing_page.company();
	}

	@Test(priority = 1)
	public void smartview() throws InterruptedException {
		WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.navigate().refresh();
		System.out.println("🧪" + "SmartView Testcases");
		WebElement smartviewmore = driver.findElement(By.xpath("//div[@class='plus-more-btn']"));
		if (smartviewmore.isDisplayed() && smartviewmore.isEnabled()) {
			smartviewmore.click();
			System.out.println("Smart View Button is Working." + "-" + "Testcase Passed");
		} else {
			System.out.println("Smart View Button is not working." + "-" + "Testcase Failed"
					+ smartviewmore.isDisplayed() + "-" + smartviewmore.isEnabled());
		}

		WebElement smartviewsearchbar = driver.findElement(By.xpath("//input[@placeholder='search view']"));
		if (smartviewsearchbar.isDisplayed() && smartviewsearchbar.isEnabled()) {
			System.out.println("SmartView Search Bar is working." + "-" + "Testcase Passed");
		} else {
			System.out.println("SmartView Search Bar is working." + "-" + "Testcase Failed"
					+ smartviewsearchbar.isDisplayed() + "-" + smartviewsearchbar.isEnabled());
		}
		smartviewsearchbar.sendKeys("All");
		Thread.sleep(1000); // Or use WebDriverWait if it's dynamically filtered

		// 3. Get all items under the search
		List<WebElement> options = driver.findElements(By.cssSelector(".popover_tab__lIjsm"));

		boolean hasMatchingOption = false;
		boolean testPassed = true;

		// Checking the result of search and cross-checking
		for (WebElement option : options) {
			String labelText = option.getText().trim().toLowerCase();
			boolean isVisible = option.isDisplayed();

			if (labelText.contains("all")) {
				hasMatchingOption = true;
				if (!isVisible) {
					System.out.println("Match found but not visible: " + labelText);
					testPassed = false;
				} else {
					System.out.println("Match and visible: " + labelText);
				}
			} else {
				if (isVisible) {
					System.out.println("Non-match visible: " + labelText);
					testPassed = false;
				} else {
					System.out.println("Non-match hidden: " + labelText);
				}
			}
		}

		// Result of the testcase
		if (!hasMatchingOption) {
			System.out.println("No matching option found for '" + "all" + "'");
		} else if (!testPassed) {
			System.out.println("TestCase Failed: Incorrect filtering behavior");
		} else {
			System.out.println("Test Passed: Correct filtering behavior");
		}

		// Click checkbox and Save Button
		WebElement Checkbox = driver.findElement(By.xpath("(//div[@class='popover_tabs__sJB61']//input)[2]"));
		Thread.sleep(1000);
		Checkbox.click();
		String expectedsmartviewtext = Checkbox.getText();
		driver.findElement(By.xpath("//button[text()='Save']")).click();

		Thread.sleep(1000);
		WebElement Actualsmartview = driver.findElement(By.xpath("//div[@class='entity-table-view_tabCell__Z7fpu ']"));
		String actualsmartviewtext = Actualsmartview.getText();

		if (expectedsmartviewtext.equals(actualsmartviewtext)) {
			System.out.println("SmartView Selected and Save correctly." + "-" + "Testcase Passed");
		} else {
			System.out.println("SmartView Selected and Save incorrectly." + "-" + "Testcase Failed" + "-"
					+ expectedsmartviewtext + "-" + actualsmartviewtext);
		}
		if (Actualsmartview.isDisplayed() && Actualsmartview.isEnabled()) {
			System.out.println("Selected SmartView Working." + "-" + "Testcase Passed");
		} else {
			System.out.println("Selected SmartView not Working." + "-" + "Testcase Failed" + "-"
					+ Actualsmartview.isDisplayed() + "-" + Actualsmartview.isEnabled());
		}

		// Buttons in smartview
		try {

			smartviewmore.click();
			// Create a list of buttons and their labels
			List<String> buttonLabels = Arrays.asList("Reset to default", "Save", "Apply");

			// Loop through each button and check its visibility and clickability
			boolean allButtonsWorking = true; // Assume all buttons are working initially

			for (String label : buttonLabels) {
				WebElement button = driver.findElement(By.xpath("//button[text()='" + label + "']"));

				if (button.isDisplayed() && button.isEnabled()) {
					System.out.println(label + " button is working.");
				} else {
					allButtonsWorking = false;
					System.out.println(label + " button is not working.");
				}
			}

			// Final check to see if all buttons are working
			if (allButtonsWorking) {
				waiting.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Reset to default']")))
						.click();
				System.out.println("All buttons are working./nTestcase Passed");
			} else {
				System.out.println("One or more buttons are not working.Testcase Failed");
			}
		} catch (Exception e) {
			System.out.println("Smartview Button Testcase Failed: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("✅" + "SmartView Testcases Executed");
		System.out.println();
	}

	@Test(priority = 2)
	public void searchbar() throws InterruptedException {

		driver.navigate().refresh();
		System.out.println("🧪" + "SearchBar Testcases");

		WebElement searchbar = driver.findElement(By.xpath("(//input[@class='deBounceSearch form-control'])[2]"));

		if (searchbar.isDisplayed() && searchbar.isEnabled()) {
			System.out.println("SearchBar is Editable and Visible.\tTestCase Passed");
		} else {
			System.out.println("SearchBar is Not Editable or Visible.\tTestcase Failed");
		}

		if ((searchbar.getDomProperty("placeholder")).equals("Search name, phone, email...")) {
			System.out.println("Placeholder Verified.\tTestCase Passed");
		} else {
			System.out.println(searchbar.getDomProperty("placeholder"));
			System.out.println("Placeholder Not Correct.\tTestCase Failed");
		}

		Thread.sleep(1000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(searchbar));
		searchbar.sendKeys("Anu");

		Thread.sleep(2000);

		List<WebElement> listedFirstnames = driver.findElements(By.xpath("//div[@class='f-center-h100 ']//a"));
		List<WebElement> listedEmails = driver.findElements(
				By.xpath("//div[@class='table_body-cell__skB24 ts-table-body-cell']//div[contains(text(),'@')]"));

		boolean hasMatchingOption = false;
		boolean testPassed = true;

		// Assuming names and emails are aligned by index (i.e., listedFirstnames.get(i)
		// matches listedEmails.get(i))
		int count = Math.min(listedFirstnames.size(), listedEmails.size());
		String searchTerm = "anu";

		for (int i = 0; i < count; i++) {
			WebElement nameElement = listedFirstnames.get(i);
			WebElement emailElement = listedEmails.get(i);

			String nameText = nameElement.getText().trim().toLowerCase();
			String emailText = emailElement.getText().trim().toLowerCase();

			boolean nameVisible = nameElement.isDisplayed();
			boolean emailVisible = emailElement.isDisplayed();

			boolean nameMatch = nameText.contains(searchTerm);
			boolean emailMatch = emailText.contains(searchTerm);

			boolean isRowMatch = (nameMatch && nameVisible) || (emailMatch && emailVisible);

			if (nameMatch || emailMatch) {
				hasMatchingOption = true;
			}

			if (isRowMatch) {
				System.out.println("Match and visible - Name: '" + nameText + "', Email: '" + emailText + "'");
			} else {
				System.out.println("No match or not visible - Name: '" + nameText + "', Email: '" + emailText + "'");
				testPassed = false;
			}
		}

		// --- Final Result ---
		if (!hasMatchingOption) {
			System.out.println("No matching option found for '" + searchTerm + "'");
		} else if (!testPassed) {
			System.out.println("TestCase Failed: Incorrect filtering behavior");
		} else {
			System.out.println("Search Bar TestCase Passed: Correct filtering behavior");
		}

		WebElement Clearbutton = driver.findElement(By.xpath("//div[@class='deBounceSearchCross']"));

		// String searchbarvalue = searchbar.getText();
		// WebElement Clearbutton1 =
		// driver.findElement(By.xpath("//div[@class='deBounceSearchCross']"));
		if (Clearbutton.isDisplayed() && Clearbutton.isEnabled()) {
			System.out.println("SearchBar Clear Button Testcase Passed");
			Clearbutton.click();
		} else {
			System.out.println("SearchBar Clear Button Testcase Failed");
		}
		System.out.println("✅" + "Search Bar Testcases Executed");
		System.out.println();
	}

	@Test(priority = 3)
	public void columnselector() throws InterruptedException {

		driver.navigate().refresh();
		System.out.println("🧪" + "Column Selector Testcases");
		WebElement columnselectorbutton = driver.findElement(By.xpath("//div[@class='global-column-selector-icon']"));

		Thread.sleep(1000);
		if (columnselectorbutton.isDisplayed() && columnselectorbutton.isEnabled()) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(columnselectorbutton)).click();
			// columnselectorbutton.click();
			System.out.println("Column Selector Button is Working.- TestCase Passed");
		} else {
			System.out.println("Column Selector Button is not Working.- Testcase Failed");
		}

		// Search Bar Function check
		WebElement columnselectorsearchbar = driver.findElement(By.xpath("//input[@placeholder='search view']"));
		if (columnselectorsearchbar.isDisplayed() & columnselectorsearchbar.isEnabled()) {
			System.out.println("Column Selector Search Bar is working. - Testcase Passed");
		} else {
			System.out.println("Column Selector Search Bar is not working. - Testcase Failed");
		}
		columnselectorsearchbar.sendKeys("Sta");

		Thread.sleep(2000); // Wait until result loads

		// 3. Get all items under the search
		List<WebElement> options = driver.findElements(By.xpath("//div[@class='styles_field__EwgI9']"));

		boolean hasMatchingOption = false;
		boolean testPassed = true;

		// Checking the result of search and cross-checking
		for (WebElement option : options) {
			String labelText = option.getText().trim().toLowerCase();
			boolean isVisible = option.isDisplayed();

			if (labelText.contains("sta")) {
				hasMatchingOption = true;
				if (!isVisible) {
					System.out.println("Match found but not visible: " + labelText);
					testPassed = false;
				}
			}
		}

		// Result of the testcase
		if (!hasMatchingOption) {
			System.out.println("No matching option found for '" + "sta" + "'");
		} else if (!testPassed) {
			System.out.println("TestCase Failed: Incorrect filtering behavior");
		} else {
			System.out.println("Test Passed: Correct filtering behavior");
		}

		columnselectorsearchbar.clear();

		Thread.sleep(2000);
		// Check checkbox
		List<WebElement> fields = driver.findElements(By.cssSelector("div.styles_field__EwgI9"));
		for (WebElement field : fields) {
			String label = field.getText().trim();
			WebElement checkbox = field.findElement(By.tagName("input"));
			if (label.equalsIgnoreCase("state")) {
				if (!checkbox.isSelected() && checkbox.isEnabled()) {
					checkbox.click();
					System.out.println("Selected column: " + "state");
				} else {
					System.out.println("Column already selected: " + "state");
				}
				break;
			}
		}

		driver.findElement(By.xpath("//button[text()='Save']")).click();

		Thread.sleep(1000);

		if (driver.findElement(By.xpath("(//div[@class='f-center-h100 c-pointer'])[7]")).getText()
				.equalsIgnoreCase("state")) {
			System.out.println("Column Selector Working. - TestCase Passed");
		} else {
			System.out.println("Column Selector Not Working.- Testcase Failed");
		}

		columnselectorbutton.click();
		WebElement Resettodefaultbutton = driver.findElement(By.xpath("//button[text()='Reset to default']"));

		if (Resettodefaultbutton.isDisplayed() && Resettodefaultbutton.isEnabled()) {
			System.out.println("Reset to Default is Working. - Testcase Passed");
			Thread.sleep(1000);
			Resettodefaultbutton.click();
		} else {
			System.out.println("Reset to Default is Not Working. - Testcase Failed");
		}
		System.out.println("✅" + "Column Selector Testcases Executed");
		System.out.println();
	}

	@Test(priority = 4)
	public void refreshbutton() {

		driver.navigate().refresh();
		System.out.println("🧪" + "Refresh Button Testcases");

		WebElement refresh = driver.findElement(By.xpath("//div[@class='global-refresh']"));

		if (refresh.isDisplayed() && refresh.isEnabled()) {
			System.out.println("Refresh Button is Working. - Testcase Passed");
		} else {
			System.out.println("Refresh Button is not Working. - Testcase Failed");
		}
		System.out.println("✅" + "Refresh Button Testcases Executed");
		System.out.println();
	}

	@Test(priority = 5)
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

	@Test(priority = 6)
	public void sorting() throws InterruptedException {
		driver.navigate().refresh();
		System.out.println("🧪" + "Sorting Testcases");

		List<WebElement> allHeaders = driver
				.findElements(By.xpath("//div[contains(@class,'f-center-h100 c-pointer')]"));

		for (int i = 0; i < allHeaders.size(); i++) {
			WebElement header = allHeaders.get(i);
			String headerName = header.getText();

			System.out.println("Testing Sorting for Column: " + headerName);

			if (headerName.equalsIgnoreCase("Action")) {
				continue;
			}
			Thread.sleep(1000);
			// Click header to sort
			header.click();
			Thread.sleep(3000); // Small wait for sorting to happen

			// Get all column cell values (dynamic XPath)
			int j;
			if (i == 0) {
				j = i + 2;
			} else {
				j = i + 3;
			}

			List<WebElement> columnCells = driver.findElements(By.xpath("(//div[contains(@class,'ts-table-body-cell')]["
					+ j + "]/div/div/div | //div[contains(@class,'ts-table-body-cell')][" + j + "]//a)"));

			Leads.sorting(columnCells, headerName);
		}
		System.out.println("✅" + "Sorting Testcases Executed");
		System.out.println();
	}

	@Test(priority = 7)
	public void draganddrop() {
		driver.navigate().refresh();

		System.out.println("🧪" + "Drag and Drop Testcases");
		Actions action = new Actions(driver);

		WebElement fromelement = driver.findElement(By.xpath("(//div[@class='f-center-h100 c-pointer'])[2]"));
		String fromelementvalue = fromelement.getText();
		WebElement from = driver.findElement(By.xpath("(//div[@class='table_dragger__EwCXJ'])[4]"));
		WebElement to = driver.findElement(By.xpath("(//div[@class='table_dragger__EwCXJ'])[7]"));
		action.dragAndDrop(from, to).perform();

		driver.navigate().refresh();

		WebElement toelement = driver.findElement(By.xpath("(//div[@class='f-center-h100 c-pointer'])[5]"));

		if (toelement.getText().equalsIgnoreCase(fromelementvalue)) {
			System.out.println("Drag and Drop action Performed. - Testcase Passed");
			action.dragAndDrop(driver.findElement(By.xpath("(//div[@class='table_dragger__EwCXJ'])[7]")),
					driver.findElement(By.xpath("(//div[@class='table_dragger__EwCXJ'])[4]"))).perform();
		} else {
			System.out.println(
					"Drag and Drop action Not Performed. - Testcase Failed. " + toelement.getText() + fromelementvalue);
		}
		System.out.println("✅" + "Drag and Drop Testcases Executed");
		System.out.println();
	}

	@Test(priority = 8)
	public void bulkactions() throws InterruptedException {
		driver.navigate().refresh();

		System.out.println("🧪" + "Bulk Action Testcases");
		WebDriverWait waittime = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions action = new Actions(driver);

////////////////////////// Update Button  //////////////////////////////////
		System.out.println("🧪" + "Update Button Testcases");
		// Wait for Bulk action button
		waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk actions']")));
		WebElement bulkactionbutton = driver.findElement(By.xpath("//button[text()='Bulk actions']"));
		bulkactionbutton.click();// Click the bulk action button

		// Find and test the Update button
		WebElement updatefieldbutton = driver.findElement(By.xpath("//button[text()='Update field']"));
		if (updatefieldbutton.isDisplayed() && updatefieldbutton.isEnabled()) {
			System.out.println("Update_Field button is working. - Testcase Passed");
		} else {
			System.out.println("Update_Field button is not working. - Testcase Failed" + "-"
					+ updatefieldbutton.isDisplayed() + "-" + updatefieldbutton.isEnabled());
		}

		updatefieldbutton.click();// Click the Update button

		// Wait for the error message and test the error message
		By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]");
		WebElement updateerrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (updateerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("Update field Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("Update field Error Message not displayed correctly. - Testcase Failed");
		}

		// Select any lead
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
		updatefieldbutton.click();// Click the update field button

		// Entering the field on dropdown
		if ((driver.findElement(By.xpath("//div[@class='drawer_title__Jj9gY']")).isDisplayed())) {
			driver.findElement(By.className("css-13cymwt-control")).click();

			// Wait for and select the option
			WebElement option = waittime.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=' css-19bb58m']//input")));
			option.sendKeys("Name");
			option.sendKeys(Keys.ENTER);
			waittime.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='update-field-drawer_updateFieldBox__IHlXH']")));
			if ((driver.findElement(By.xpath("//div[@class='update-field-drawer_updateFieldBox__IHlXH']")).getText())
					.equalsIgnoreCase("Name")) {
				System.out.println("Dropdown in the Update field is working. - TestCase Passed");
			} else {
				System.out.println("Dropdown in the Update field is not working. - TestCase Failed");
			}

		} else {
			System.out.println("Update is not Opened. - Testcase Failed.");
		}

		Thread.sleep(2000);
		// Find and test the clear button of selected field
		WebElement clearbutton = waittime
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='btn-close']")));
		if (clearbutton.isDisplayed() && clearbutton.isEnabled()) {
			clearbutton.click();
			System.out.println("Clear Button is working. - Testcase Passed");
		} else {
			System.out.println("Clear Button is not working. - Testcase Passed");
		}

		// Find and test the element
		WebElement verifycheckbox = driver.findElement(By.xpath("//div[@class='drawer_confirmBox__i63bR']//input"));// Checkbox
		WebElement cancelbutton = driver.findElement(By.xpath("//button[@class='light-btn btn btn-secondary']"));// Cancel
		WebElement updatebutton = driver.findElement(By.xpath("//button[text()='Update']"));// Update

		if (verifycheckbox.isDisplayed() && verifycheckbox.isEnabled()) {
			verifycheckbox.click();
			System.out.println("Verify Checkbox is working. - Testcase Passed");
			if (updatebutton.isDisplayed() && updatebutton.isEnabled()) {
				System.out.println("Update Button is working. - Testcase Passed.");
			} else {
				System.out.println("Update Button is not working. - Testcase Failed");
			}
			if (cancelbutton.isDisplayed() && cancelbutton.isEnabled()) {
				waittime.until(ExpectedConditions.visibilityOfAllElements(cancelbutton));
				Thread.sleep(2000);
				cancelbutton.click();
				System.out.println("Cancel Button is working. - Testcase Passed.");
			} else {
				System.out.println("Cancel Button is not working. - Testcase Failed");
			}
		} else {
			System.out.println("Verify Checkbox is not working. - Testcase Failed");
		}

		driver.navigate().refresh();
		System.out.println("✅" + "Update Button Testcases Executed");
		System.out.println();

/////////////// Add Tags Button  ///////////////////////////////////
		System.out.println("🧪" + "Add Tags Button Testcases");
		// Wait the bulk action button
		waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk actions']")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Bulk actions']")).click();// Click the bulk action button

		// Find and test the Tag button
		WebElement addtagsbutton = driver.findElement(By.xpath("//button[text()='Add tags']"));// locator
		if (addtagsbutton.isDisplayed() && addtagsbutton.isEnabled()) {
			System.out.println("Add_Tags button is working. - Testcase Passed");
		} else {
			System.out.println("Add_Tags button is not working. - Testcase Failed" + "-" + addtagsbutton.isDisplayed()
					+ "-" + addtagsbutton.isEnabled());
		}
		addtagsbutton.click();// Click the Tag Button

		// Wait for error messga and test it
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='d-flex align-content-center align-items-center px-3 gap-2 py-2']")));
		WebElement addtagerrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (addtagerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("Add tags Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("Add tags Error Message not displayed correctly. - Testcase Failed");
		}

		// Select any Lead
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
		Thread.sleep(2000);
		WebElement addtagbutton = waittime
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add tags']")));// Wait
																												// for
																												// the
																												// Tag
																												// button
		addtagbutton.click();// Click the Tag button
		Thread.sleep(2000);
		WebElement addtaginputdropdown = driver.findElement(By.xpath("//div[@class=' css-19bb58m']//input"));// Check
																												// the
																												// dropdwn
																												// of
																												// Tag
		addtaginputdropdown.sendKeys("New Company");// Enter the Value in dropdown
		Thread.sleep(2000);
		addtaginputdropdown.sendKeys(Keys.ENTER);// Select the value

		// Find and test the other element
		WebElement addtagupdatebutton = driver.findElement(By.xpath("//button[@class='blue-btn btn btn-secondary']"));// Update
																														// button
		WebElement addtagcancelbutton = driver
				.findElement(By.xpath("//button[@class='light-btn border-radius-4px btn btn-secondary']"));// Cancel
																											// button

		if ((addtagupdatebutton.isDisplayed() && addtagcancelbutton.isEnabled())
				&& (addtagcancelbutton.isDisplayed() && addtagcancelbutton.isEnabled())) {
			System.out.println("Add_Tag Update and Cancel button is working. - Testcase Passed");
		} else {
			System.out.println("Add_Tag Update and Cancel button is not working. - Testcase Failed");
		}
		Thread.sleep(2000);
		addtagupdatebutton.click();// Click the update button

		// Click the Bulk acion button
		driver.findElement(By.xpath("//button[text()='Bulk actions']")).click();
		Thread.sleep(2000);

		// Select any Lead
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@class='fill-svg-btn min-btn-width btn btn-secondary']"))).click();

		WebElement addtagbutton2 = driver.findElement(By.xpath("//div[text()='Add Tags']"));// check the Add Tag button
																							// on dropdown
		WebElement removetagbutton = driver.findElement(By.xpath("//div[text()='Remove Tags']"));// check the Remove Tag
																									// button on
																									// dropdown

		waittime.until(ExpectedConditions.visibilityOf(addtagbutton2));// Wait for the Add Tag button on dropdown

		if ((addtagbutton2.isDisplayed() && addtagbutton2.isEnabled())
				&& (removetagbutton.isDisplayed() && removetagbutton.isEnabled())) {
			System.out.println("Add_Tag moreoption button is working. - Testcase Passed");
		} else {
			System.out.println("Add_Tag moreoption button is not working. - Testcase Failed");
		}
		driver.navigate().refresh();
		System.out.println("✅" + "Add Tags Button Testcases Executed");
		System.out.println();

//////////////////// BulkEmail Button  //////////////////////////////////
		
		System.out.println("🧪" + "Bulk Email button Testcases");
		// Wait the bulk action button
		waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk actions']")));
		driver.findElement(By.xpath("//button[text()='Bulk actions']")).click();// Click the bulk action button
		Thread.sleep(1000);

		// Find the locator of Bulk email and click the bulk email button
		WebElement bulkemailbutton = driver.findElement(By.xpath("//button[text()='Bulk email']"));
		if (bulkemailbutton.isDisplayed() && bulkemailbutton.isEnabled()) {
			System.out.println("Bulk_Email button is working. - Testcase Passed");
		} else {
			System.out.println("Bulk_Email button is not working. - Testcase Failed" + "-"
					+ bulkemailbutton.isDisplayed() + "-" + bulkemailbutton.isEnabled());
		}
		// Wait for the Bulk email button and check the error message
		waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk email']")));
		driver.findElement(By.xpath("//button[text()='Bulk email']")).click();
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]")));// Waiting
																												// for
																												// the
																												// error
																												// message
		WebElement bulkemailerrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (bulkemailerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("Bulk email Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("Bulk email Error Message not displayed correctly. - Testcase Failed");
		}
		// Select any Lead
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
		waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk email']")));// wait
																													// the
																													// Bulk
																													// Email
																													// buton
		driver.findElement(By.xpath("//button[text()='Bulk email']")).click();// wait the Bulk Email buton

		// Check the Email drawer is opened
		String bulkemailbox = driver.findElement(By.xpath("//div[@class='drawer_title__Jj9gY']")).getText();
		if (bulkemailbox.equalsIgnoreCase("Send Email")) {
			System.out.println("Bulk Email drawer opened. -Testcase Passed");
		} else {
			System.out.println("Bulk Email drawer not opened. -Testcase Failed");
		}
		// Check the Send Button
		WebElement sendbutton = waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		if (sendbutton.isDisplayed() && sendbutton.isEnabled()) {
			System.out.println("Send Button is working.-Testcase Passed");
		} else {
			System.out.println("Send Button is not working.-Testcase Failed  " + sendbutton.isDisplayed() + "-"
					+ sendbutton.isEnabled());
		}

		// Find the tomail element and enter the email id.
		WebElement tomail = driver.findElement(By.xpath("(//div[@class=' css-19bb58m']//input)[1]"));
		tomail.sendKeys(valuepro.getProperty("Email-Id_contact"));
		tomail.sendKeys(Keys.ENTER);
		
		// Find the ccmail element and enter the email id.
		WebElement ccmail = driver.findElement(By.xpath("(//div[@class=' css-19bb58m']//input)[2]"));
		ccmail.sendKeys(valuepro.getProperty("ccmail-Id-contact"));
		ccmail.sendKeys(Keys.ENTER);

		// Select the template
		Thread.sleep(4000);
		WebElement templateInput = waittime.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=' css-19bb58m']//input)[3]")));
		templateInput.click();
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		Thread.sleep(4000);

		// Wait for the selected value to be visible
		String selectedtemplateText = "";
		try {
		    WebElement selectedTemplate = waittime.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//div[contains(@class,'-singleValue')]")));
		    selectedtemplateText = selectedTemplate.getText().trim();
		    System.out.println("Selected Text: " + selectedtemplateText);
		} catch (Exception e) {
		    System.out.println("No selected template element found.");
		}

		if (selectedtemplateText.equalsIgnoreCase("Select Template") || selectedtemplateText.isEmpty()) {
			List<String> contentbuttonLabels = Arrays.asList("ql-bold", "ql-italic", "ql-underline", "ql-strike", "ql-blockquote","ql-link");

			boolean allcontentbuttonLabels=true;
			
			for(String buttonlabel:contentbuttonLabels) {
				WebElement contentbutton = waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='"+buttonlabel+"']")));
				if(contentbutton.isDisplayed()&&contentbutton.isEnabled()) {
					System.out.println(buttonlabel+" Button is working.");
				}else {
					allcontentbuttonLabels=false;
					System.out.println(buttonlabel+"Button is not working");
				}	
			}
			if(allcontentbuttonLabels) {
				System.out.println("All Content Buttons are working. Testcase Passed");
			}else {
				System.out.println("Some Content Button are bot working. Testcase Passed");
			}
		    System.out.println("No template selected, entering content...");
		    WebElement content = driver.findElement(By.xpath("//div[@class='ql-container ql-snow']//p"));
		    content.sendKeys(valuepro.getProperty("Content-contact"));
		} else {
		    System.out.println("Template selected: " + selectedtemplateText);
		}
		Thread.sleep(1000);
		if(!selectedtemplateText.isEmpty()) {
			System.out.println("Template Selected But Subject not Populated. TestCase Failed");
			WebElement subject = driver.findElement(By.name("subject"));
			subject.sendKeys(valuepro.getProperty("Subject-contact"));
			Thread.sleep(1000);
			if(subject.getDomProperty("value").equalsIgnoreCase(valuepro.getProperty("Subject-Lead")) && !subject.getDomProperty("value").equals(null)){
				System.out.println("Subject has been entered. Testcase Passed"+subject.getDomProperty("value"));
			}else {
				System.out.println("Subject has not been Entered.  Testcase Failed");
			}
			
		}else {
			System.out.println("Subject is Entered. TestCase Passed");
		}
		
		sendbutton.click();// Click the send button.
		Thread.sleep(2000);
		driver.navigate().refresh();
		System.out.println("✅" + "Bulk Email Testcases Executed");
		System.out.println();

//////////////////// SMS Button////////////////////////////////////////
		System.out.println("🧪" + "SMS Testcases");
		// wait the Bulk action button
		waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk actions']")));
		driver.findElement(By.xpath("//button[text()='Bulk actions']")).click();// click the bulk action button
		Thread.sleep(1000);
		// Find the SMS button and check
		WebElement smsbutton = waittime
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='SMS']")));
		if (smsbutton.isDisplayed() && smsbutton.isEnabled()) {
			System.out.println("SMS button is working. - Testcase Passed");
		} else {
			System.out.println("SMS button is not working. - Testcase Failed" + "-" + smsbutton.isDisplayed() + "-"
					+ smsbutton.isEnabled());
		}
		smsbutton.click();// Click the sms button
		// Wait for the error message and check
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]")));
		WebElement smserrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (smserrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("SMS Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("SMS Error Message not displayed correctly. - Testcase Failed");
		}
		// Select any lead
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
		smsbutton.click();// Click the sms button

		WebElement smsdropdown = waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div[@class='select template__input-container css-19bb58m']//input)[1]")));// Sms Dropdown
																										// button
		WebElement smssendbutton = waittime
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Send']")));// Sms Send
																											// button
		WebElement smscancelbutton = waittime
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Cancel']")));// Cancel
																												// Button
		if (smsdropdown.isDisplayed() && smsdropdown.isEnabled()) {
			System.out.println("Sms Dropdown is working.-Testcase Passed");
		} else {
			System.out.println("Sms Dropdown is not working.-Testcase Failed");
		}
		if ((smssendbutton.isDisplayed() && smssendbutton.isEnabled())
				&& (smscancelbutton.isDisplayed() && smscancelbutton.isEnabled())) {
			System.out.println("Sms Cancel and Send Button is working.-Testcase Passed");
		} else {
			System.out.println("Sms Cancel and Send Button is not working.-Testcase Failed"
					+ ((smssendbutton.isDisplayed() && smssendbutton.isEnabled())
							&& (smscancelbutton.isDisplayed() && smscancelbutton.isEnabled())));
		}
		Thread.sleep(1000);
		driver.navigate().refresh();
		System.out.println("✅" + "SMS Testcases Executed");
		System.out.println();

///////////////////////// Assign Button///////////////////////////////
		System.out.println("🧪" + "Assign button Testcases");
		//// wait the Bulk action button
		waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk actions']")));
		driver.findElement(By.xpath("//button[text()='Bulk actions']")).click();// Click the Bulk action button
		Thread.sleep(1000);
		// Find the Assign to button
		WebElement assignbutton = driver.findElement(By.xpath("//button[text()='Assign to']"));
		if (assignbutton.isDisplayed() && assignbutton.isEnabled()) {
			System.out.println("Assign button is working. - Testcase Passed");
		} else {
			System.out.println("Assign button is not working. - Testcase Failed" + "-" + assignbutton.isDisplayed()
					+ "-" + assignbutton.isEnabled());
		}
		assignbutton.click();// Click the Assign Button
		// Check the error message
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]")));
		WebElement assignerrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (assignerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("Assign to Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("Assign to Error Message not displayed correctly. - Testcase Failed");
		}
		// Select any Lead
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();

		assignbutton.click();// Click the assign button

		WebElement assigndropdown = waittime
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=' css-19bb58m']//input")));// Assign
																														// Dropdown
		WebElement assignupdatebutton = waittime
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Update']")));// Update
																												// button
		WebElement assigncancelbutton = waittime
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Cancel']")));// Cancel
																												// button
		if (assigndropdown.isDisplayed() && assigndropdown.isEnabled()) {
			System.out.println("Assign Dropdown is working.-Testcase Passed");
		} else {
			System.out.println("Assign Dropdown is not working.-Testcase Failed");
		}
		if ((assignupdatebutton.isDisplayed() && assignupdatebutton.isEnabled())
				&& (assigncancelbutton.isDisplayed() && assigncancelbutton.isEnabled())) {
			System.out.println("Assign Cancel and Update Button is working.-Testcase Passed");
		} else {
			System.out.println("Assign Cancel and Update Button is not working.-Testcase Failed" + "-"
					+ ((assignupdatebutton.isDisplayed() && assignupdatebutton.isEnabled())
							&& (assigncancelbutton.isDisplayed() && assigncancelbutton.isEnabled())));
		}

		Thread.sleep(1000);
		// Select the value from the dropdown
		WebElement dropdown = driver.findElement(By.className("css-19bb58m"));
		dropdown.click();// Click the dropdown
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement optionselect = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'-option') and text()='" + valuepro.getProperty("Owner_Name") + "']")));// Select
																														// specific
																														// value
		optionselect.click();// Click the value
		Thread.sleep(2000);
		assignupdatebutton.click();// Click the Update button
		Thread.sleep(2000);
		// Check the owner is updated of the lead
		WebElement ownerdata = driver.findElement(By.xpath("(//div[@class='f-center-h100'])[3]"));// Find the element of
																									// ownerof that lead
		if (ownerdata.getText().equalsIgnoreCase(valuepro.getProperty("Owner_Name"))) {
			System.out.println("Assign_to is working.- Testcase Passed");
		} else {
			System.out.println("Assign_to is not working.- Testcase Failed");
		}
		Thread.sleep(4000);
		driver.navigate().refresh();
		System.out.println("✅" + "Assign button Testcases Executed");
		System.out.println();

//////////////////////////// Merge Button////////////////////////////////////
		System.out.println("🧪" + "Merge Button Testcases");
		// wait the Bulk action button
		waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk actions']")));
		driver.findElement(By.xpath("//button[text()='Bulk actions']")).click();// Click the Bulk action buton
		Thread.sleep(1000);
		// Find the merge button
		WebElement mergebutton = driver.findElement(By.xpath("//button[text()='Merge']"));
		if (mergebutton.isDisplayed() && mergebutton.isEnabled()) {
			System.out.println("Merge button is working. - Testcase Passed");
		} else {
			System.out.println("Merge button is not working. - Testcase Failed" + "-" + mergebutton.isDisplayed() + "-"
					+ mergebutton.isEnabled());
		}

		mergebutton.click();// Click the merge button
		// Check the error message
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]")));
		WebElement mergeerrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (mergeerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("Merge Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("Merge Error Message not displayed correctly. - Testcase Failed");
		}
		// Select any Lead
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
		mergebutton.click();// Click the merge button

		WebElement mergedropdown = driver.findElement(By.xpath("//div[@class=' css-19bb58m']//input"));// Dropdown
		WebElement mergecheckbox = driver.findElement(By.xpath("//div[@class='drawer_confirmBox__i63bR']//input"));// checkbox
		WebElement mergeupdatebutton = driver.findElement(By.xpath("(//button[text()='Merge'])[2]"));// Update button
		WebElement mergecancelbutton = driver.findElement(By.xpath("//button[text()='Cancel']"));// Cancel

		if ((mergedropdown.isDisplayed() && mergedropdown.isEnabled())
				&& (mergecheckbox.isDisplayed() && mergecheckbox.isEnabled())) {
			System.out.println("Merge Dropdown and checkbox is working. - Testcase Passed");
			Thread.sleep(1500);
			waittime.until(ExpectedConditions.elementToBeClickable(mergecheckbox)).click();// Wait for the checkbox is
																							// clickable
		} else {
			System.out.println("Merge Dropdown is and checkbox not working. - Testcase Failed" + "Dropdown - "
					+ (mergedropdown.isDisplayed() && mergedropdown.isEnabled()) + "Checkbox - "
					+ (mergecheckbox.isDisplayed() && mergecheckbox.isEnabled()));
		}
		Thread.sleep(1000);
		if ((mergeupdatebutton.isDisplayed() && mergeupdatebutton.isEnabled())
				&& (mergecancelbutton.isDisplayed() && mergecancelbutton.isEnabled())) {
			System.out.println("Merge_Update and Cancel is working. - Testcase Passed");
		} else {
			System.out.println("Merge_Update and Cancel is not working. - Testcase Failed" + "Update Button - "
					+ (mergeupdatebutton.isDisplayed() && mergeupdatebutton.isEnabled()) + "Cancel Button - "
					+ (mergecancelbutton.isDisplayed() && mergecancelbutton.isEnabled()));
		}

		driver.navigate().refresh();
		System.out.println("✅" + "Merge Testcases Executed");
		System.out.println();

///////////////////// Delete Button////////////////////////////////
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

		

		// Cancel Bulk Selection Button
		System.out.println("🧪" + "Cancel bulk selection Testcases");
		// wait the Bulk action button
		waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk actions']")));
		driver.findElement(By.xpath("//button[text()='Bulk actions']")).click();// Click the Bulk action button
		Thread.sleep(1000);
		// Find the Cancelbulkselection button and check
		WebElement cancelbulkselectionbutton = driver.findElement(By.xpath("//button[text()='Cancel Bulk Selection']"));
		if (cancelbulkselectionbutton.isDisplayed() && cancelbulkselectionbutton.isEnabled()) {
			System.out.println("Cancel Bulk Selection button is working. - Testcase Passed");
		} else {
			System.out.println("Cancel Bulk Selection button is not working. - Testcase Failed" + "-"
					+ cancelbulkselectionbutton.isDisplayed() + "-" + cancelbulkselectionbutton.isEnabled());
		}
		System.out.println("✅" + "Cancel bulk selection Testcases Executed");
		System.out.println();

		// Overall checkbox
		System.out.println("🧪" + "Select All Testcases");
		WebElement overallcheckbox = driver.findElement(By.id("selectAll"));// Seleteall checkbox
		WebElement updatefield = driver.findElement(By.xpath("//button[text()='Update field']"));// Update field
		WebElement assigntobutton = driver.findElement(By.xpath("//button[text()='Assign to']"));// Assign to button
		WebElement overalldeletebutton = driver.findElement(By.xpath("//button[text()='Delete']"));// Delete button
		if (overallcheckbox.isDisplayed() && overallcheckbox.isEnabled()) {
			overallcheckbox.click();
			if (updatefield.isDisplayed() && updatefield.isEnabled()) {
				System.out.println("Update_Field button is working on bulkaction. - Testcase Passed");
			} else {
				System.out.println("Update_Field button is not working on bulkaction. - Testcase Failed" + "-"
						+ updatefield.isDisplayed() + "-" + updatefield.isEnabled());
			}
			if (assigntobutton.isDisplayed() && assigntobutton.isEnabled()) {
				System.out.println("Assign_to button is working on bulkaction. - Testcase Passed");
			} else {
				System.out.println("Assign_to button is not working on bulkaction. - Testcase Failed" + "-"
						+ assigntobutton.isDisplayed() + "-" + assigntobutton.isEnabled());
			}
			if (overalldeletebutton.isDisplayed() && overalldeletebutton.isEnabled()) {
				System.out.println("Delete button is working on bulkaction. - Testcase Passed");
			} else {
				System.out.println("Delete button is not working on bulkaction. - Testcase Failed" + "-"
						+ overalldeletebutton.isDisplayed() + "-" + overalldeletebutton.isEnabled());
			}
			System.out.println("Overall Checkbox is working. - Testcase Passed");
		} else {
			System.out.println("Overall Checkbox is not working. - Testcase Failed");
		}
		System.out.println("✅" + "Select All Testcases Executed");
		System.out.println("✅" + "Bulk Action Testcases Executed");
		System.out.println();
	}
}