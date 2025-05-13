package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import baseclass.Baseclass;
import baseclass.elementfunction;

public class Organization extends Baseclass {

    @Test
    public void launch() throws IOException, InterruptedException {
        Login.loginpage();
        Baseclass.waitingtime("(//a[@class='header-icon-hover'])[2]", null);
        driver.findElement(By.xpath("(//a[@class='header-icon-hover'])[2]")).click();
        System.out.println("Setting Section is Opened");
        Baseclass.waitingtime("//button[text()='Org Details']", "visible");
        //Landing_page.screenshot("//Setting//Organization//Org-Details", null, null);
        System.out.println("Org Details is Visible");
    }

    @Test(priority = 1)
    public void editpage() throws InterruptedException {
        Baseclass.waitingtime("//button[text()='Edit']", null);
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
        Thread.sleep(2000);
         //Company Name 
         System.out.println("testbox checking");
         elementfunction.txtbox("//input[@id='CompanyName']", null, "Enter Company Name", "Techisor", "@#$%^&*123abc");
         System.out.println("testbox verified correctly");
         
         //Select Industry 
         Thread.sleep(2000);
         elementfunction.dropdown("//input[@id='react-select-6-input']", "//div[contains(@class, 'css-b62m3t-container')]//div[@role='option']", "Automotive", "Life Science");
         System.out.println("Corrected");
         
        //Country Code
        Baseclass.waitingtime("//div[@class='react-international-phone-country-selector-button__button-content']", null);
        elementfunction.dropdown("//div[@class='react-international-phone-country-selector-button__button-content']", "//li[contains(@class, 'react-international-phone-country-selector-dropdown__list-item')]/span[@class='react-international-phone-country-selector-dropdown__list-item-country-name']", "Afghanistan", null);
        
        //Mobile Number entering
        System.out.println("PHONE");
        WebElement phonenumbertxtbox = driver.findElement(By.name("phoneNumber"));
        System.out.println("Located");
        Thread.sleep(2000);
        System.out.println(phonenumbertxtbox.getText());
        Thread.sleep(2000);
        phonenumbertxtbox.sendKeys("9878998765");
        Thread.sleep(2000);
        System.out.println("ENTERED");

        //Phone Number entering
        driver.findElement(By.id("phone")).sendKeys("9876788675");
        System.out.println("Number Enterted");

        WebElement description = driver.findElement(By.id("description"));
        description.clear();
        description.sendKeys("This is the Techisor's Togile-CRM");
        System.out.println("Description entered");

        WebElement domaintxtbox = driver.findElement(By.id("domain"));
        domaintxtbox.clear();
        domaintxtbox.sendKeys("Customer Relationship management");

        WebElement URLtxtbox = driver.findElement(By.id("websiteURL"));
        URLtxtbox.clear();
        URLtxtbox.sendKeys("https://test.togile.com");
        System.out.println("Website Entered");

        WebElement addresstxtbox = driver.findElement(By.id("address"));
        addresstxtbox.clear();
        addresstxtbox.sendKeys("Hsr Layout,Bangalore");
        System.out.println("Address Entered");

        WebElement statetxtbox = driver.findElement(By.id("state"));
        statetxtbox.clear();
        statetxtbox.sendKeys("Karnataka");
        System.out.println("State Entered");

        WebElement zipcodetxtbox = driver.findElement(By.name("zipcode"));
        zipcodetxtbox.clear();
        zipcodetxtbox.sendKeys("5006701");
        System.out.println("Zipcode Entered");
    }
}
