# Togile CRM Automation Suite

This project contains an automation test suite for **Togile CRM**, developed using **Selenium WebDriver**, **Java**, **TestNG**, and **Maven**. It is designed to validate key CRM functionalities including login, CRUD operations, filters, and UI checks.

---

**Tech Stack**

- **Language**: Java  
- **Automation Framework**: Selenium WebDriver  
- **Test Framework**: TestNG  
- **Build Tool**: Maven  
- **Design Pattern**: Page Object Model (POM)  
- **Version Control**: Git  

---

**Project Structure**

Togile_CRM/
├── src/
│ ├── main/
│ │ └── java/
│ └── test/
│ ├── java/
│ │ ├── baseclass/ # Base setup classes and element interactions
│ │ │ ├── Baseclass.java
│ │ │ └── elementfunction.java
│ │ └── testcases/ # Test cases for various modules
│ │ ├── Company.java
│ │ ├── Contact.java
│ │ ├── Deals.java
│ │ ├── Defaultdashboard.java
│ │ ├── ForgetPassword.java
│ │ ├── Landing_page.java
│ │ ├── Leads.java
│ │ ├── Login.java
│ │ ├── LoginpageNegativeTestcase.java
│ │ ├── Organization.java
│ │ ├── Signup.java
│ │ └── URL_landing_Page.java
│ └── resources/
│ ├── config.properties
│ └── value.properties
│
├── testng.xml # TestNG suite configuration
├── pom.xml # Maven build configuration
├── Screenshot 
├── test-output/ # TestNG default output

**Pre-requisites**

- JDK 11 or higher  
- Maven 3.x  
- Chrome browser  
- Git  


**Features Covered**

Login functionality

Forgot Password workflow

Entity creation and update

Filter dropdown testing

Validation of icon actions in tables

Cross-browser compatibility

**Author**
Gokul Selvaraj

**Notes**
Make sure ChromeDriver is compatible with your browser version.

Update test data and credentials in the config.properties file
