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

public class Leads extends Baseclass {

	@Test(priority = 0)
	public static void login() throws IOException, InterruptedException {
		Landing_page.logo();
		Landing_page.leads();
	}

	// @Test(testName = "Smartview")
	public void smartview() throws InterruptedException {

		System.out.println("🧪" + "SmartView Testcases");
		WebElement smartviewmore = driver.findElement(By.xpath("//div[@class='plus-more-btn']"));
		if (smartviewmore.isDisplayed() & smartviewmore.isEnabled()) {
			smartviewmore.click();
			System.out.println("Smart View Button is Working./nTestcase Passed");
		} else {
			System.out.println("Smart View Button is not working./nTestcase Failed");
		}

		WebElement smartviewsearchbar = driver.findElement(By.xpath("//input[@placeholder='search view']"));
		if (smartviewsearchbar.isDisplayed() & smartviewsearchbar.isEnabled()) {
			System.out.println("SmartView Search Bar is working./nTestcase Passed");
		} else {
			System.out.println("SmartView Search Bar is working./nTestcase Failed");
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
		WebElement Checkbox = driver
				.findElement(By.xpath("(//div[@class='popover_tabs__sJB61']//input[@type='checkbox'])[2]"));
		Checkbox.click();
		String expectedsmartviewtext = Checkbox.getText();
		driver.findElement(By.xpath("//button[text()='Save']")).click();

		Thread.sleep(1000);
		WebElement Actualsmartview = driver.findElement(By.xpath("//div[@class='entity-table-view_tabCell__Z7fpu ']"));
		String actualsmartviewtext = Actualsmartview.getText();

		if (expectedsmartviewtext.equals(actualsmartviewtext)) {
			System.out.println("SmartView Selected and Save correctly./nTestcase Passed");
		} else {
			System.out.println("SmartView Selected and Save incorrectly./nTestcase Failed");
		}
		if (Actualsmartview.isDisplayed() && Actualsmartview.isEnabled()) {
			System.out.println("Selected SmartView Working./nTestcase Passed");
		} else {
			System.out.println("Selected SmartView not Working./nTestcase Failed");
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
				System.out.println("All buttons are working./nTestcase Passed");
			} else {
				System.out.println("One or more buttons are not working./nTestcase Failed");
			}
		} catch (Exception e) {
			System.out.println("Smartview Button Testcase Failed: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("✅" + "SmartView Testcases Executed");
	}

	// @Test(priority = 1)
	public void searchbar() throws InterruptedException {

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

		searchbar.click();
		searchbar.sendKeys("Gokul");

		Thread.sleep(1000);

		List<WebElement> listedFirstnames = driver.findElements(By.xpath("//div[@class='f-center-h100 ']//a"));
		List<WebElement> listedEmails = driver.findElements(
				By.xpath("//div[@class='table_body-cell__skB24 ts-table-body-cell']//div[contains(text(),'@')]"));

		boolean hasMatchingOption = false;
		boolean testPassed = true;

		// Assuming names and emails are aligned by index (i.e., listedFirstnames.get(i)
		// matches listedEmails.get(i))
		int count = Math.min(listedFirstnames.size(), listedEmails.size());
		String searchTerm = "gokul";

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

		Clearbutton.click();

		String searchbarvalue = searchbar.getText();

		if (Clearbutton.isDisplayed() && Clearbutton.isEnabled() && searchbarvalue == null) {
			System.out.println("SearchBar Clear Button Testcase Passed");
		} else {
			System.out.println("SearchBar Clear Button Testcase Failed");
		}

		System.out.println("✅" + "Search Bar Testcases Executed");

	}

	public void columnselector() throws InterruptedException {

		System.out.println("🧪" + "Column Selector Testcases");
		WebElement columnselectorbutton = driver.findElement(By.xpath("//div[@class='global-column-selector-icon']"));

		if (columnselectorbutton.isDisplayed() && columnselectorbutton.isEnabled()) {
			columnselectorbutton.click();
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

		if (driver.findElement(By.xpath("(//div[@class='f-center-h100 c-pointer'])[12]")).getText()
				.equalsIgnoreCase("state")) {
			System.out.println("Column Selector Woking. - TestCase Passed");
		} else {
			System.out.println("Column Selector Not Working.- Testcase Failed");
		}

		WebElement Resettodefaultbutton = driver.findElement(By.xpath("//button[text()='Save']"));

		if (Resettodefaultbutton.isDisplayed() && Resettodefaultbutton.isEnabled()) {
			System.out.println("Reset to Default is Working. - Testcase Passed");
		} else {
			System.out.println("Reset to Default is Not Working. - Testcase Failed");
		}
		System.out.println("✅" + "Column Selector Testcases Executed");
	}

	public void refreshbutton() {

		System.out.println("🧪" + "Refresh Button Testcases");

		WebElement refresh = driver.findElement(By.xpath("//div[@class='global-refresh']"));

		if (refresh.isDisplayed() && refresh.isEnabled()) {
			System.out.println("Refresh Button is Working. - Testcase Passed");
		} else {
			System.out.println("Refresh Button is not Working. - Testcase Failed");
		}
		System.out.println("✅" + "Refresh Button Testcases Executed");
	}

	public void addlead() throws InterruptedException {

		System.out.println("🧪" + "Add Leads Testcases");

		Actions actions = new Actions(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement addleadsbutton = driver.findElement(By.xpath("//button[@class='blue-btn btn btn-secondary']"));

		if (addleadsbutton.isDisplayed() && addleadsbutton.isEnabled()
				&& (addleadsbutton.getText()).equals("Add Leads")) {
			System.out.println("Add Leads Button is working.- Testcase Passed");
		} else {
			System.out.println("Add Leads Button is not Working. - Testcase Failed");
		}

		addleadsbutton.click();
		if (driver.findElement(By.xpath("//div[@class='customcss-drawer-header']")).isDisplayed()) {
			System.out.println("Add Lead Drawer Opened.- Testcase Passed");
		} else {
			System.out.println("Add Lead Drawer not Opened.- Testcase Failed");
		}

		// FirstName
		WebElement firstname = driver.findElement(By.xpath("//input[@placeholder='Enter First Name']"));
		if (firstname.isDisplayed() && firstname.isEnabled()) {
			System.out.println("First Name Field is Visible and Editable.- Testcase Passed");
		} else {
			System.out.println("First Name Field is not Visible and Editable.- Testcase Failed");
		}
		firstname.sendKeys(valuepro.getProperty("Firstname"));

		if (firstname.getDomProperty("value").equals(valuepro.getProperty("Firstname"))) {
			System.out.println("FirstName Field Value Entered Correctly.- Testcase Passed");
		} else {
			System.out.println("FirstName Field Value Entered Not Correctly.- Testcase Failed");
		}

		// Email
		WebElement emailtextbox = driver.findElement(By.xpath("//input[@placeholder='Enter Email']"));
		if (emailtextbox.isDisplayed() && emailtextbox.isEnabled()) {
			System.out.println("Email Field is Visible and Editable.- Testcase Passed");
		} else {
			System.out.println("Email Field is not Visible and Editable.- Testcase Failed");
		}
		emailtextbox.sendKeys(valuepro.getProperty("Email_Id"));
		if (emailtextbox.getDomProperty("value").equals(valuepro.getProperty("Email_Id"))) {
			System.out.println("Email Field Value Entered Correctly.- Testcase Passed");
		} else {
			System.out.println("Email Field Value Entered Not Correctly.- Testcase Failed");
		}

		WebElement addemailbutton = driver.findElement(By.xpath("(//div[@style='cursor: pointer;'])[2]"));
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

		countrycodetextbox.sendKeys(valuepro.getProperty("Country_Code"));// Enter the Preferred Value
		actions.sendKeys(Keys.ENTER).perform();// Wait and press ENTER to select the typed value

		if ((driver.findElement(By.xpath("//div[@class=' css-hlgwow'][1]")).getText())
				.equals(valuepro.getProperty("Country_Code"))) {
			System.out.println("CountryCode Field Value Entered Correctly.- Testcase Passed");
		} else {
			System.out.println("CountryCode Field Value Entered Not Correctly.- Testcase Failed"
					+ countrycodetextbox.getDomProperty("value"));
		}

		// Phone Number
		WebElement phonenumbertextbox = driver.findElement(By.xpath("//input[@placeholder='Enter Phone Number']"));
		if (phonenumbertextbox.isDisplayed() && phonenumbertextbox.isEnabled()) {
			System.out.println("Phone Number Field is Visible and Editable.- Testcase Passed");
		} else {
			System.out.println("Phone Number Field is not Visible and Editable.- Testcase Failed");
		}
		phonenumbertextbox.sendKeys(valuepro.getProperty("Phone_Number"));
		if (phonenumbertextbox.getDomProperty("value").equals(valuepro.getProperty("Phone_Number"))) {
			System.out.println("Phone Number Field Value Entered Correctly.- Testcase Passed");
		} else {
			System.out.println("Phone Number Field Value Entered Not Correctly.- Testcase Failed");
		}
		WebElement addphonebutton = driver.findElement(By.xpath("(//div[@style='cursor: pointer;'])[3]"));
		if (addphonebutton.isDisplayed() && addphonebutton.isEnabled()) {
			System.out.println("Add-Phone Button is working.- Testcase Passed");
		} else {
			System.out.println("Add-Phone Button is not working.- Testcase Failed");
		}

		// Select Pipeline dropdown
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=' css-19bb58m'])[2]//input")));
		WebElement pipelinedropdown = driver.findElement(By.xpath("(//div[@class=' css-19bb58m'])[2]//input"));
		pipelinedropdown.click();
		String Expanded2 = pipelinedropdown.getDomAttribute("aria-expanded");// Dropdown Expand Check
		if (pipelinedropdown.isDisplayed() && pipelinedropdown.isEnabled()) {
			System.out.println("Pipeline dropdown is Visible and Editable.- Testcase Passed");
		} else {
			System.out.println("Pipeline dropdown is not Visible and Editable.- Testcase Failed");
		}
		if (Expanded2 != null && Expanded2.equals("true")) {
			System.out.println("The Pipeline dropdown is expanded - TestCase Passed");
		} else {
			System.out.println("The Pipeline dropdown is collapsed - TestCase Failed" + Expanded2);
		}
		pipelinedropdown.sendKeys(valuepro.getProperty("Pipeline"));// Enter the Preferred Value

		actions.sendKeys(Keys.ENTER).perform();// Wait and press ENTER to select the typed value

		if ((driver.findElement(By.xpath("(//div[@class=' css-hlgwow'])[2]")).getText())
				.equalsIgnoreCase(valuepro.getProperty("Pipeline"))) {
			System.out.println("Pipeline Dropdown Value Selected Correctly - TestCase Passed");
		} else {
			System.out.println("Pipeline Dropdown Value Not Selected Correctly - TestCase Failed"
					+ pipelinedropdown.getDomAttribute("value"));
		}

		// Select PipelineStage dropdown
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=' css-19bb58m'])[3]//input")));
		WebElement pipelinestagedropdown = driver.findElement(By.xpath("(//div[@class=' css-19bb58m'])[3]//input"));
		pipelinestagedropdown.click();
		String Expanded3 = pipelinestagedropdown.getDomAttribute("aria-expanded");// Dropdown Expand Check
		if (pipelinestagedropdown.isDisplayed() && pipelinestagedropdown.isEnabled()) {
			System.out.println("PipelineStage dropdown is Visible and Editable.- Testcase Passed");
		} else {
			System.out.println("PipelineStage dropdown is not Visible and Editable.- Testcase Failed");
		}
		if (Expanded3 != null && Expanded3.equals("true")) {
			System.out.println("The PipelineStage dropdown is expanded - TestCase Passed");
		} else {
			System.out.println("The PipelineStage dropdown is collapsed - TestCase Failed" + Expanded3);
		}
		pipelinestagedropdown.sendKeys(valuepro.getProperty("Pipeline_Stage"));// Enter the Preferred Value
		actions.sendKeys(Keys.ENTER).perform();// Wait and press ENTER to select the typed value

		if ((driver.findElement(By.xpath("(//div[@class=' css-hlgwow'])[3]")).getText())
				.equalsIgnoreCase(valuepro.getProperty("Pipeline_Stage"))) {
			System.out.println("PipelineStage Dropdown Value Selected Correctly - TestCase Passed");
		} else {
			System.out.println("PipelineStage Dropdown Value Not Selected Correctly - TestCase Failed"
					+ pipelinestagedropdown.getDomAttribute("value"));
		}

		// Select Owner dropdown
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=' css-19bb58m'])[4]//input")));
		WebElement Ownerdropdown = driver.findElement(By.xpath("(//div[@class=' css-19bb58m'])[4]//input"));
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
		Ownerdropdown.sendKeys(valuepro.getProperty("Owner"));// Enter the Preferred Value
		actions.sendKeys(Keys.ENTER).perform();// Wait and press ENTER to select the typed value

		if (Ownerdropdown.getDomAttribute("value").equalsIgnoreCase(valuepro.getProperty("Owner"))) {
			System.out.println("Owner Dropdown Value Selected Correctly - TestCase Passed");
		} else {
			System.out.println("Owner Dropdown Value Not Selected Correctly - TestCase Failed"
					+ Ownerdropdown.getDomAttribute("value"));
		}

		// Select Source dropdown
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div/div[3]/div[1]/div[2]/div/div[8]/div/div/div[1]/div[2]")));
		WebElement sourcedropdown = driver
				.findElement(By.xpath("/html/body/div/div[3]/div[1]/div[2]/div/div[8]/div/div/div[1]/div[2]/input"));
		sourcedropdown.click();
		String Expanded5 = sourcedropdown.getDomAttribute("aria-expanded");// Dropdown Expand Check
		if (sourcedropdown.isDisplayed() && sourcedropdown.isEnabled()) {
			System.out.println("Source dropdown is Visible and Editable.- Testcase Passed");
		} else {
			System.out.println("Source dropdown is not Visible and Editable.- Testcase Failed");
		}
		if (Expanded5 != null && Expanded5.equals("true")) {
			System.out.println("The Source dropdown is expanded - TestCase Passed");
		} else {
			System.out.println("The Source dropdown is collapsed - TestCase Failed" + Expanded5);
		}
		sourcedropdown.sendKeys(valuepro.getProperty("Source"));// Enter the Preferred Value
		actions.sendKeys(Keys.ENTER).perform();// Wait and press ENTER to select the typed value

		if ((driver.findElement(By.xpath("(//div[@class=' css-hlgwow'])[5]")).getText())
				.equalsIgnoreCase(valuepro.getProperty("Source"))) {
			System.out.println("Source Dropdown Value Selected Correctly - TestCase Passed");
		} else {
			System.out.println("Source Dropdown Value Not Selected Correctly - TestCase Failed"
					+ sourcedropdown.getDomAttribute("value"));
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
		WebElement addedlead = driver.findElement(By.xpath("(//div[@class='f-center-h100 '])[1]"));
		if (addedlead.getText().contains(valuepro.getProperty("Firstname"))) {
			System.out.println("Lead Created Successfully. - Testcase Passed");
		} else {
			System.out.println("Lead Not Created. - Testcase Failed" + addedlead.getText());
		}
		// Leads.validation("Enter First Name", "Required Field");
		System.out.println("✅" + "Add Leads Testcases Executed");

	}

	// Sorting
	public void sorting() throws InterruptedException {
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

			System.out.println("✅" + "Sorting Testcases Executed");
		}
	}

	public void draganddrop() {
		Actions action = new Actions(driver);

		WebElement from = driver.findElement(By.xpath("(//div[@class='table_dragger__EwCXJ'])[4]"));
		WebElement to = driver.findElement(By.xpath("(//div[@class='table_dragger__EwCXJ'])[7]"));
		action.dragAndDrop(from, to).perform();

		driver.navigate().refresh();

		WebElement toelement = driver.findElement(By.xpath("(//div[@class='f-center-h100 c-pointer'])[5]"));

		if (toelement.getText().equalsIgnoreCase("Last Name")) {
			System.out.println("Drag and Drop action Performed. - Testcase Passed");
		} else {
			System.out.println("Drag and Drop action Not Performed. - Testcase Failed" + toelement.getText());
		}
	}

	@Test(priority = 1)
	public void bulkactions() throws InterruptedException {
		WebDriverWait waittime = new WebDriverWait(driver, Duration.ofSeconds(10));

		waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk actions']")));

		WebElement bulkactionbutton = driver.findElement(By.xpath("//button[text()='Bulk actions']"));

		bulkactionbutton.click();

////////////////////////// Update Button  //////////////////////////////////
		WebElement updatefieldbutton = driver.findElement(By.xpath("//button[text()='Update field']"));
		if (updatefieldbutton.isDisplayed() && updatefieldbutton.isEnabled()) {
			System.out.println("Update_Field button is working. - Testcase Passed");
		} else {
			System.out.println("Update_Field button is not working. - Testcase Failed" + "-"
					+ updatefieldbutton.isDisplayed() + "-" + updatefieldbutton.isEnabled());
		}

		updatefieldbutton.click();
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]")));
		WebElement updateerrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (updateerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("Update field Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("Update field Error Message not displayed correctly. - Testcase Failed");
		}

		driver.findElement(By.id("check-all")).click();
		updatefieldbutton.click();

		if ((driver.findElement(By.xpath("//div[@class='drawer_title__Jj9gY']")).isDisplayed())) {
			driver.findElement(By.className("css-13cymwt-control")).click();

			// Wait for and select the option
			WebElement option = waittime.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=' css-19bb58m']//input")));
			option.sendKeys("First Name");
			option.sendKeys(Keys.ENTER);
			if ((driver.findElement(By.xpath("//div[@class='update-field-drawer_updateFieldBox__IHlXH']")).getText())
					.equalsIgnoreCase("First Name")) {
				System.out.println("Dropdown in the Update field is working. - TestCase Passed");
			} else {
				System.out.println("Dropdown in the Update field is not working. - TestCase Failed");
			}

		} else {
			System.out.println("Update is not Opened. - Testcase Failed.");
		}

		WebElement clearbutton = driver.findElement(By.xpath("//div[@class='btn-close']"));
		if (clearbutton.isDisplayed() && clearbutton.isEnabled()) {
			clearbutton.click();
			System.out.println("Clear Button is working. - Testcase Passed");
		} else {
			System.out.println("Clear Button is not working. - Testcase Passed");
		}

		WebElement verifycheckbox = driver.findElement(By.xpath("//div[@class='drawer_confirmBox__i63bR']//input"));
		WebElement cancelbutton = driver.findElement(By.xpath("//button[@class='light-btn btn btn-secondary']"));
		WebElement updatebutton = driver.findElement(By.xpath("//button[text()='Update']"));

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
		driver.findElement(By.id("check-all")).click();
		System.out.println();

/////////////// Add Tags Button  ///////////////////////////////////
		waittime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Bulk actions']")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Bulk actions']")).click();
		WebElement addtagsbutton = driver.findElement(By.xpath("//button[text()='Add tags']"));
		if (addtagsbutton.isDisplayed() && addtagsbutton.isEnabled()) {
			System.out.println("Add_Tags button is working. - Testcase Passed");
		} else {
			System.out.println("Add_Tags button is not working. - Testcase Failed" + "-" + addtagsbutton.isDisplayed()
					+ "-" + addtagsbutton.isEnabled());
		}
		addtagsbutton.click();
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='d-flex align-content-center align-items-center px-3 gap-2 py-2']")));
		WebElement addtagerrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (addtagerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("Add tags Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("Add tags Error Message not displayed correctly. - Testcase Failed");
		}
		driver.findElement(By.id("check-6811e5aef54c18dca27b368d")).click();
		Thread.sleep(2000);
		WebElement addtagbutton = waittime
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add tags']")));
		addtagbutton.click();
		Thread.sleep(2000);
		WebElement addtaginputdropdown = driver.findElement(By.xpath("//div[@class=' css-19bb58m']//input"));
		addtaginputdropdown.sendKeys("Fresh Lead");
		Thread.sleep(2000);
		addtaginputdropdown.sendKeys(Keys.ENTER);

		WebElement addtagupdatebutton = driver.findElement(By.xpath("//button[@class='blue-btn btn btn-secondary']"));
		WebElement addtagcancelbutton = driver
				.findElement(By.xpath("//button[@class='light-btn border-radius-4px btn btn-secondary']"));

		if ((addtagupdatebutton.isDisplayed() && addtagcancelbutton.isEnabled())
				&& (addtagcancelbutton.isDisplayed() && addtagcancelbutton.isEnabled())) {
			System.out.println("Add_Tag Update and Cancel button is working. - Testcase Passed");
		} else {
			System.out.println("Add_Tag Update and Cancel button is not working. - Testcase Failed");
		}
		Thread.sleep(2000);
		addtagupdatebutton.click();

		driver.findElement(By.xpath("//button[text()='Bulk actions']")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("check-6811e5aef54c18dca27b368d")).click();
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@class='fill-svg-btn min-btn-width btn btn-secondary']"))).click();

		WebElement addtagbutton2 = driver.findElement(By.xpath("//div[text()='Add Tags']"));
		WebElement removetagbutton = driver.findElement(By.xpath("//div[text()='Remove Tags']"));

		waittime.until(ExpectedConditions.visibilityOf(addtagbutton2));

		if ((addtagbutton2.isDisplayed() && addtagbutton2.isEnabled())
				&& (removetagbutton.isDisplayed() && removetagbutton.isEnabled())) {
			System.out.println("Add_Tag moreoption button is working. - Testcase Passed");
		} else {
			System.out.println("Add_Tag moreoption button is not working. - Testcase Failed");
		}

		System.out.println();

//////////////////// BulkEmail Button  //////////////////////////////////
		WebElement bulkemailbutton = driver.findElement(By.xpath("//button[text()='Bulk email']"));
		if (bulkemailbutton.isDisplayed() && bulkemailbutton.isEnabled()) {
			System.out.println("Bulk_Email button is working. - Testcase Passed");
		} else {
			System.out.println("Bulk_Email button is not working. - Testcase Failed" + "-"
					+ bulkemailbutton.isDisplayed() + "-" + bulkemailbutton.isEnabled());
		}
		bulkemailbutton.click();
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]")));
		WebElement bulkemailerrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (bulkemailerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("Bulk email Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("Bulk email Error Message not displayed correctly. - Testcase Failed");
		}
		System.out.println();

		// SMS Button
		WebElement smsbutton = driver.findElement(By.xpath("//button[text()='SMS']"));
		if (smsbutton.isDisplayed() && smsbutton.isEnabled()) {
			System.out.println("SMS button is working. - Testcase Passed");
		} else {
			System.out.println("SMS button is not working. - Testcase Failed" + "-" + smsbutton.isDisplayed() + "-"
					+ smsbutton.isEnabled());
		}
		smsbutton.click();
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]")));
		WebElement smserrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (smserrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("SMS Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("SMS Error Message not displayed correctly. - Testcase Failed");
		}
		System.out.println();

		// Assign Button
		WebElement assignbutton = driver.findElement(By.xpath("//button[text()='Assign to']"));
		if (assignbutton.isDisplayed() && assignbutton.isEnabled()) {
			System.out.println("Assign button is working. - Testcase Passed");
		} else {
			System.out.println("Assign button is not working. - Testcase Failed" + "-" + assignbutton.isDisplayed()
					+ "-" + assignbutton.isEnabled());
		}
		assignbutton.click();
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]")));
		WebElement assignerrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (assignerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("Assign to Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("Assign to Error Message not displayed correctly. - Testcase Failed");
		}
		System.out.println();

		// Merge Button
		WebElement mergebutton = driver.findElement(By.xpath("//button[text()='Merge']"));
		if (mergebutton.isDisplayed() && mergebutton.isEnabled()) {
			System.out.println("Merge button is working. - Testcase Passed");
		} else {
			System.out.println("Merge button is not working. - Testcase Failed" + "-" + mergebutton.isDisplayed() + "-"
					+ mergebutton.isEnabled());
		}
		mergebutton.click();
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]")));
		WebElement mergeerrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (mergeerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("Merge Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("Merge Error Message not displayed correctly. - Testcase Failed");
		}
		System.out.println();

		// Delete Button
		WebElement deletebutton = driver.findElement(By.xpath("//button[text()='Delete']"));
		if (deletebutton.isDisplayed() && deletebutton.isEnabled()) {
			System.out.println("Delete button is working. - Testcase Passed");
		} else {
			System.out.println("Delete button is not working. - Testcase Failed" + "-" + deletebutton.isDisplayed()
					+ "-" + deletebutton.isEnabled());
		}
		deletebutton.click();
		waittime.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]")));
		WebElement deleteerrormesssage = driver.findElement(
				By.xpath("//div[@class=\"d-flex align-content-center align-items-center px-3 gap-2 py-2\"]"));
		if (deleteerrormesssage.getText().equalsIgnoreCase("0 leads is selected")) {
			System.out.println("Delete Error Message displayed correctly. - Testcase Passed");
		} else {
			System.out.println("Delete Error Message not displayed correctly. - Testcase Failed");
		}
		System.out.println();

		// Cancel Bulk Selection Button
		WebElement cancelbulkselectionbutton = driver.findElement(By.xpath("//button[text()='Cancel Bulk Selection']"));
		if (cancelbulkselectionbutton.isDisplayed() && cancelbulkselectionbutton.isEnabled()) {
			System.out.println("Cancel Bulk Selection button is working. - Testcase Passed");
		} else {
			System.out.println("Cancel Bulk Selection button is not working. - Testcase Failed" + "-"
					+ cancelbulkselectionbutton.isDisplayed() + "-" + cancelbulkselectionbutton.isEnabled());
		}
		System.out.println();

		// Overall checkbox
		WebElement overallcheckbox = driver.findElement(By.id("selectAll"));
		WebElement updatefield = driver.findElement(By.xpath("//button[text()='Update field']"));
		if (overallcheckbox.isDisplayed() && overallcheckbox.isEnabled()) {
			overallcheckbox.click();
			if (updatefield.isDisplayed() && updatefield.isEnabled()) {
				System.out.println("Update_Field button is working on bulkaction. - Testcase Passed");
			} else {
				System.out.println("Update_Field button is not working on bulkaction. - Testcase Failed" + "-"
						+ updatefield.isDisplayed() + "-" + updatefield.isEnabled());
			}
			System.out.println("Overall Checkbox is working. - Testcase Passed");
		} else {
			System.out.println("Overall Checkbox is not working. - Testcase Failed");
		}
		System.out.println();

	}
}
