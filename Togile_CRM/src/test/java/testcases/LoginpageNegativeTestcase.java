package testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import baseclass.Baseclass;

public class LoginpageNegativeTestcase extends Baseclass {

	@Test
	public static void value() throws IOException, InterruptedException {

		WebElement search = driver.findElement(By.id("myInput"));
		search.sendKeys("Billy");
		Thread.sleep(1000); // Or use WebDriverWait if it's dynamically filtered

		// 3. Get all <li> items under the <ul>
		List<WebElement> items = driver.findElements(By.cssSelector("#myUL li"));

		// 4. Check visibility of each item
		for (WebElement item : items) {
		    String text = item.getText();
		    boolean isVisible = item.isDisplayed();

		    if (text.toLowerCase().contains("billy")) {
		        if (isVisible) {
		            System.out.println("✅ Matched and visible: " + text);
		        } else {
		            System.out.println("❌ Matched but not visible: " + text);
		        }
		    } else {
		        if (isVisible) {
		            System.out.println("❌ Unmatched but still visible: " + text);
		        } else {
		            System.out.println("✅ Unmatched and hidden: " + text);
		        }
		    }
		}
	}
}