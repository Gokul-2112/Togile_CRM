package baseclass;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class elementfunction extends Baseclass {

    public static void txtbox(String locator, String waitvalue, String placeholder, String value, String incorrectvalue) throws InterruptedException {
        Baseclass.waitingtime(locator, waitvalue);
        WebElement txtbox = driver.findElement(By.xpath(locator));
        //txtbox display && enabled
        Assert.assertTrue(txtbox.isDisplayed(), "Textbox is not displayed!");
        Assert.assertTrue(txtbox.isEnabled(), "Textbox is not enabled!");

        //verify the placeholder
        String placeholdervalue = txtbox.getDomAttribute("placeholder");
        if (placeholdervalue.equals(placeholder)) {
            System.out.println("Placeholder matches");
        } else {
            System.out.println("Placeholder doesn't match");
        }

        //verify the value entered.
        txtbox.clear();
        txtbox.sendKeys(value);
        String enteredvalue = txtbox.getDomAttribute("value");
        if (enteredvalue.equals(value)) {
            System.out.println("The value is correct!");
        } else {
            System.out.println("The value is incorrect!");
        }

        //verify txt clearing
        txtbox.clear();
        if (enteredvalue.equals(value)) {
            System.out.println("The value is  Clear");
        } else {
            System.out.println("The value is Unclear!");
        }

        //invalid value
        txtbox.sendKeys(incorrectvalue);
        String enteredincorrectvalue = txtbox.getDomAttribute("value");
        if (enteredincorrectvalue.equals(incorrectvalue)) {
            System.out.println("The value is  accept!");
        } else {
            System.out.println("The value doesn't accepted!");
        }

        //copy-paste function
        txtbox.clear();
        txtbox.sendKeys(value);
        txtbox.sendKeys(Keys.CONTROL, "a");
        txtbox.sendKeys(Keys.CONTROL, "x");
        txtbox.sendKeys(Keys.CONTROL, "v");
        if (enteredvalue.equals(value)) {
            System.out.println("The Copy/Paste is Working");
        } else {
            System.out.println("The Copy/Paste doesn't working!");
        }
    }

    public static void dropdown(String locator, String dropdownlocator, String value, String invalidvalue) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Dropdown click to open
        WebElement dropdownInput = driver.findElement(By.xpath(locator));
        dropdownInput.click();
        List<WebElement> dropdownOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(dropdownlocator)));
        //check whether dropdown is expand or collapse

        String isExpanded = dropdownInput.getDomProperty("aria-expanded");

        if (isExpanded != null && isExpanded.equals("true")) {
            System.out.println("The dropdown is expanded.");
        } else {
            System.out.println("The dropdown is collapsed." + isExpanded);
        }

        // Wait and locate all dropdown options
        List<WebElement> dropdownOption = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(dropdownlocator)));

        // Print all optionsX
        for (WebElement option : dropdownOption) {
            System.out.print(option.getText() + "--,,--");
        }

        // Select a specific option
        for (WebElement option : dropdownOptions) {
            if (option.getText().equals(value)) {
                option.click();
                break;
            }
        }

        //Get the selected option
        String selectedoption = driver.findElement(By.xpath("(//div[@class=' css-1dimb5e-singleValue'])[1]")).getText();
        if (selectedoption == null ? value == null : selectedoption.equals(value)) {
            System.out.println("Selected a correct value--" + selectedoption);
        } else {
            System.out.println("Value is incorrect");
        }

        //Check for invalid value
        if (invalidvalue != null) {
            Thread.sleep(1000);
            Actions actions = new Actions(driver);
            actions.moveToElement(dropdownInput).click().sendKeys(invalidvalue).perform();
            Thread.sleep(1000);
            List<WebElement> invalidvaluedropdownOptions = driver.findElements(By.xpath("//div[contains(@class, 'css-b62m3t-container')]//div[@role='option']"));
            Thread.sleep(1000);
            if (invalidvaluedropdownOptions.isEmpty()) {
                System.out.println("There is no option for the value: " + invalidvalue);
            } else {
                System.out.println("Invalid format: Options exist for the invalid value");
            }
        } else {
            System.out.println("Value Selected");
        }
    }
}
