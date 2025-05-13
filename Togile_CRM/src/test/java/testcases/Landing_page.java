package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import baseclass.Baseclass;

public class Landing_page extends Baseclass {
	
	static SoftAssert softassertion = new SoftAssert();

    @Test(priority = 0)
    public static void logo() throws IOException, InterruptedException {

        Login.loginpage();
        Landing_page.waitingtime("//a[@class='main-logo']", "visible");
        // Find the Logo using locator
        WebElement daslogo = driver.findElement(By.xpath("//a[@class='main-logo']"));
        //Checking the Logo is set
        softassertion.assertTrue(daslogo.isDisplayed() && daslogo.isEnabled(), "Failure: The Logo is not Working");
        Thread.sleep(1000);
        //Taking the screenshot of Logo
        Landing_page.screenshot(null, "Landing Page\\logo", daslogo);
    }

    @Test(priority = 1)
    public void dashboard() throws IOException {
        Landing_page.waitingtime("//a[text()='Dashboard']", null);
        driver.findElement(By.xpath("//a[text()='Dashboard']")).click();
        WebElement welcometext = driver.findElement(By.xpath("//div//h1[@class='style_WelcomeHeading__Rv_d7']"));
        String actualtext = welcometext.getText();
        String expectedtext = "Welcome Back, "+configpro.getProperty("Accountname");
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The dashboard welcome text not same.");
        Landing_page.waitingtime("//div[@class='style_headerContainer__19qXm']", null);
        Landing_page.screenshot("//Landing Page//Default Dashboard", null, null);
    }

    @Test(priority = 2)
    public static void leads() throws IOException {
        Landing_page.waitingtime("//a[text()='Leads']", null);
        driver.findElement(By.xpath("//a[text()='Leads']")).click();
        Landing_page.waitingtime("//div[text()='Leads']", null);
        WebElement addleadsbutton = driver.findElement(By.xpath("//button[@class='blue-btn btn btn-secondary']"));
        String actualtext = addleadsbutton.getText();
        String expectedtext ="Add Leads";
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The Leads section is not opened");
        Landing_page.screenshot("//Landing Page//Leads", null, null);
    }
    @Test(priority = 3)
    public void deals() throws IOException, InterruptedException {
        Landing_page.waitingtime("//a[text()='Deals']", null);
        driver.findElement(By.xpath("//a[text()='Deals']")).click();
		Landing_page.waitingtime("//div[text()='Deals']", null);
        WebElement adddealbutton = driver.findElement(By.xpath("//button[@class='blue-btn btn btn-secondary']/div"));
        String actualtext = adddealbutton.getText();
        String expectedtext ="Add Deals";
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The Deals section is not opened");
        Landing_page.screenshot("//Landing Page//Deals", null, null);
    }
    @Test(priority = 5)
    public void contact() throws IOException {
        Landing_page.waitingtime("//a[text()='Contacts']", null);
        driver.findElement(By.xpath("//a[text()='Contacts']")).click();
        Baseclass.waitingtime("//div[text()='Contacts']", null);
        WebElement addcontactbutton = driver.findElement(By.xpath("//button[@class='blue-btn btn btn-secondary']/div"));
        String actualtext = addcontactbutton.getText();
        String expectedtext ="Add Contacts";
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The Contact section is not opened");
        Landing_page.screenshot("//Landing Page//Contact", null, null);
    }

    @Test(priority = 6)
    public void company() throws IOException {
        Landing_page.waitingtime("//a[text()='Companies']", null);
        driver.findElement(By.xpath("//a[text()='Companies']")).click();
        Baseclass.waitingtime("//div[text()='Companies']", null);
        WebElement addcompanybutton = driver.findElement(By.xpath("//button[@class='blue-btn btn btn-secondary']"));
        String actualtext = addcompanybutton.getText();
        String expectedtext ="Add Companies";
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The Company section is not opened");
        Landing_page.screenshot("//Landing Page//Company", null, null);
    }

    @Test(priority = 7)
    public void report() throws IOException {
        Landing_page.waitingtime("//a[text()='Report']", null);
        driver.findElement(By.xpath("//a[text()='Report']")).click();
        WebElement createreportbutton = driver.findElement(By.xpath("//div[text()='Create Report']"));
        String actualtext = createreportbutton.getText();
        String expectedtext ="Create Report";
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The Report section is not opened");
        Landing_page.screenshot("//Landing Page//Report", null, null);
    }

    @Test(priority = 8)
    public void moreOption() throws IOException, InterruptedException {
        Landing_page.waitingtime("//button[@class='header-more-dot btn btn-secondary']", null);
        driver.findElement(By.xpath("//button[@class='header-more-dot btn btn-secondary']")).click();
        Landing_page.waitingtime("(//div//li[@role='menuitem'])[7]", null);
        WebElement threedotconfirm = driver.findElement(By.xpath("(//div//li[@role='menuitem'])[7]"));
        String actualtext = threedotconfirm.getText();
        String expectedtext ="Task";
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The Three-Dot section is not opened");
        Landing_page.screenshot("//Landing Page//ThreeDot", null, null);
        System.out.println("Three-Dot Button is Verified");

        Landing_page.waitingtime("//button[@class='header-more-dot btn btn-secondary']", null);
        Landing_page.waitingtime("//li[text()='Task']", null);
        driver.findElement(By.xpath("//li[text()='Task']")).click();
        Landing_page.waitingtime("//h1[text()='Tasks']", "visible");
        WebElement task = driver.findElement(By.xpath("//h1[text()='Tasks']"));
        String actualtask = task.getText();
        String expectedtask ="Tasks";
        softassertion.assertEquals(actualtask, expectedtask, "Failure: The Task section is not opened");
        Landing_page.screenshot("//Landing Page//Task", null, null);
        System.out.println(" *Taks Section is Verified");

        Landing_page.waitingtime("//button[@class='header-more-dot btn btn-secondary']", null);
        driver.findElement(By.xpath("//button[@class='header-more-dot btn btn-secondary']")).click();
        Landing_page.waitingtime("//li[text()='Call Logs']", null);
        driver.findElement(By.xpath("//li[text()='Call Logs']")).click();
        Landing_page.waitingtime("//h1[text()='Call Logs']", "visible");
        WebElement calllogs = driver.findElement(By.xpath("//h1[text()='Call Logs']"));
        String actualcalllogs = calllogs.getText();
        String expectedcalllog ="Call Logs";
        softassertion.assertEquals(actualcalllogs, expectedcalllog, "Failure: The Call Logs section is not opened");
        Landing_page.screenshot("//Landing Page//Call Log", null, null);
        System.out.println(" *Call Log Section is Verified");

        Landing_page.waitingtime("//button[@class='header-more-dot btn btn-secondary']", null);
        driver.findElement(By.xpath("//button[@class='header-more-dot btn btn-secondary']")).click();
        Landing_page.waitingtime("//li[text()='Meetings']", null);
        driver.findElement(By.xpath("//li[text()='Meetings']")).click();
        Landing_page.waitingtime("//h1[text()='Meetings']", "visible");
        WebElement meeting = driver.findElement(By.xpath("//h1[text()='Meetings']"));
        String actualmeeting = meeting.getText();
        String expectedmeeting ="Meetings";
        softassertion.assertEquals(actualmeeting, expectedmeeting, "Failure: The Meeting section is not opened");
        Landing_page.screenshot("//Landing Page//Meetings", null, null);
        System.out.println(" *Meeting Section is Verified");

        Landing_page.waitingtime("//button[@class='header-more-dot btn btn-secondary']", null);
        driver.findElement(By.xpath("//button[@class='header-more-dot btn btn-secondary']")).click();
        Landing_page.waitingtime("//li[text()='Mail']", null);
        driver.findElement(By.xpath("//li[text()='Mail']")).click();
        Landing_page.waitingtime("//button[@class='blue-btn w-100 btn btn-secondary']", "visible");
        WebElement mail = driver.findElement(By.xpath("//button[text()='New Mail']"));
        String actualmail = mail.getText();
        String expectedmail ="New Mail";
        softassertion.assertEquals(actualmail, expectedmail, "Failure: The Mail section is not opened");
        Landing_page.screenshot("//Landing Page//Mail", null, null);
        System.out.println(" *Mail Section is Verified");
        
        Landing_page.waitingtime("//button[@class='header-more-dot btn btn-secondary']", null);
        driver.findElement(By.xpath("//button[@class='header-more-dot btn btn-secondary']")).click();
        Landing_page.waitingtime("//li[text()='Calendar']", null);
        driver.findElement(By.xpath("//li[text()='Calendar']")).click();
        Landing_page.waitingtime("//button[text()='today']", "visible");
        WebElement calender = driver.findElement(By.xpath("//button[text()='today']"));
        String actualcalender = calender.getText();
        String expectedcalender ="today";
        softassertion.assertEquals(actualcalender, expectedcalender, "Failure: The Calender section is not opened");
        Landing_page.screenshot("//Landing Page//Calender", null, null);
        System.out.println(" *Calender Section is Verified");
    }

    @Test(priority = 9)
    public void notification() throws IOException {
        Landing_page.waitingtime("//span[@class='MuiBadge-root css-uzopow']", null);
        driver.findElement(By.xpath("//span[@class='MuiBadge-root css-uzopow']")).click();
        Landing_page.waitingtime("//div[text()='All Notification']", "visible");
        WebElement notification = driver.findElement(By.xpath("//div[text()='All Notification']"));
        String actualtext = notification.getText();
        String expectedtext ="All Notification";
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The Notification section is not opened");
        Landing_page.screenshot("//Landing Page//Notification", null, null);
        driver.findElement(By.xpath("//div[@class='cursor-pointer']")).click();
        System.out.println("Notification Tab is Verified");
    }

    @Test(priority = 10)
    public void marketplace() throws IOException {
        Landing_page.waitingtime("(//a[@class='header-icon-hover'])[1]", null);
        driver.findElement(By.xpath("(//a[@class='header-icon-hover'])[1]")).click();
        Landing_page.waitingtime("//h5[text()='Market Place']", "visible");
        WebElement marketplace = driver.findElement(By.xpath("//h5[text()='Market Place']"));
        String actualtext = marketplace.getText();
        String expectedtext ="Market Place";
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The Market Place section is not opened");
        Landing_page.screenshot("//Landing Page//MarketPlace", null, null);
        System.out.println("MarketPlace Section is Verified");
    }

    @Test(priority = 11)
    public void setting() throws IOException {
        Landing_page.waitingtime("(//a[@class='header-icon-hover'])[2]", null);
        driver.findElement(By.xpath("(//a[@class='header-icon-hover'])[2]")).click();
        Landing_page.waitingtime("//span[text()='Organization']", "visible");
        WebElement setting = driver.findElement(By.xpath("//span[text()='Organization']"));
        String actualtext = setting.getText();
        String expectedtext ="Organization";
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The Setting section is not opened");
        Landing_page.screenshot("//Landing Page//Setting", null, null);
        System.out.println("Setting Section is Verified");
    }

    @Test(priority = 12)
    public void support() throws IOException {
        Landing_page.waitingtime("(//div[@class='header-icon-hover'])[2]", null);
        driver.findElement(By.xpath("(//div[@class='header-icon-hover'])[2]")).click();
        Landing_page.waitingtime("//button[text()='Submit']", "visible");
        WebElement support = driver.findElement(By.xpath("//div[@class='customcss-drawer-header']"));
        String actualtext = support.getText();
        String expectedtext =" Support";
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The Support section is not opened");
        Landing_page.screenshot("//Landing Page//Support", null, null);
        driver.findElement(By.xpath("//div[@class='cursor-pointer']")).click();
        System.out.println("Support Tab is Verified");
    }
    @Test(priority = 13)
    public void profile() throws IOException {
        Landing_page.waitingtime("//div[@class='header-profile-section']", null);
        driver.findElement(By.xpath("//div[@class='header-profile-section']")).click();
        Landing_page.waitingtime("//div[@class='role-container']", "visible");
        WebElement profile = driver.findElement(By.xpath("//div[@class='profile-user-name']/p"));
        String actualtext = profile.getText();
        String expectedtext =configpro.getProperty("Accountname");
        softassertion.assertEquals(actualtext, expectedtext, "Failure: The Profile section is not opened");
        Landing_page.screenshot("//Landing Page//Profile", null, null);
        System.out.println("Profile Tab is Verified");
    }
    
    @Test(priority = 14)
    public void error() {
    	softassertion.assertAll();
    }
}
