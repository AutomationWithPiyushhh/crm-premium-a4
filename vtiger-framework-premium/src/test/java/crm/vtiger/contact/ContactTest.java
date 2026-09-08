package crm.vtiger.contact;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;

public class ContactTest {

	@Test
	public void createContactTest() throws IOException, ParseException, InterruptedException {

		Reporter.log("========== CREATE CONTACT TEST STARTED ==========", true);

		// get the data json
		Reporter.log("Reading test data from JSON file...", true);

		String browser = FileUtility.getDataFromJSONFile("bro");
		String url = FileUtility.getDataFromJSONFile("url");
		String username = FileUtility.getDataFromJSONFile("un");
		String password = FileUtility.getDataFromJSONFile("pwd");

		Reporter.log("Browser: " + browser, true);
		Reporter.log("URL: " + url, true);
		Reporter.log("Username fetched successfully", true);

		// get data from excel
		Reporter.log("Reading test data from Excel file...", true);

		String lastName = FileUtility.getDataFromEXCELFile("contact", 3, 0);
		String orgName = FileUtility.getDataFromEXCELFile("org", 6, 0);

		Reporter.log("Last Name: " + lastName, true);
		Reporter.log("Organization Name: " + orgName, true);

		// open browser
		Reporter.log("Launching browser...", true);

		WebDriver driver = null;

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
			Reporter.log("Chrome browser launched", true);

		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
			Reporter.log("Edge browser launched", true);

		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
			Reporter.log("Firefox browser launched", true);

		} else {
			driver = new ChromeDriver();
			Reporter.log("Invalid browser. Chrome launched as default", true);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		Reporter.log("Browser maximized", true);
		Reporter.log("Implicit wait configured for 15 seconds", true);

		// login
		Reporter.log("Navigating to application URL...", true);
		driver.get(url);

		Reporter.log("Entering username...", true);
		WebElement un = driver.findElement(By.name("user_name"));
		un.sendKeys(username);

		Reporter.log("Entering password...", true);
		WebElement pwd = driver.findElement(By.name("user_password"));
		pwd.sendKeys(password);

		Reporter.log("Clicking Login button...", true);
		WebElement loginButton = driver.findElement(By.id("submitButton"));
		loginButton.click();

		Reporter.log("Login completed successfully", true);

		// create contact
		Reporter.log("Navigating to Contacts module...", true);
		driver.findElement(By.linkText("Contacts")).click();

		Reporter.log("Clicking Create Contact button...", true);
		driver.findElement(By.cssSelector("img[title='Create Contact...']")).click();

		Reporter.log("Create Contact page opened", true);

		// fill the form
		Reporter.log("Entering Last Name...", true);
		WebElement lastNameField = driver.findElement(By.name("lastname"));
		lastNameField.sendKeys(lastName);

		Reporter.log("Last Name entered successfully", true);

		// step 1> get the home address
		Reporter.log("Capturing parent window ID...", true);
		String PID = driver.getWindowHandle();

		Reporter.log("Parent window ID captured", true);

		// step 2> perform the task which will open new window/s or tab/s
		Reporter.log("Opening Organization lookup window...", true);
		driver.findElement(By.xpath("//img[contains(@onclick, 'Popup&popuptype')]")).click();

		// Step 3> once all the windows are opened, get their addresses
		Reporter.log("Fetching all window handles...", true);
		Set<String> CIDs = driver.getWindowHandles();

		Reporter.log("Total windows available: " + CIDs.size(), true);

		// step 4> go through each and every window
		Reporter.log("Searching for Accounts window...", true);

		for (String i : CIDs) {

			driver.switchTo().window(i);

			Reporter.log("Current window URL: " + driver.getCurrentUrl(), true);

			// step 5> give condition for your desired web-page and break the loop
			if (driver.getCurrentUrl().contains("Accounts")) {
				Reporter.log("Accounts window found", true);
				break;
			}
		}

		// step 6> perform the task on your desired web-page
		Reporter.log("Searching Organization: " + orgName, true);

		driver.findElement(By.id("search_txt")).sendKeys(orgName + Keys.ENTER);

		Thread.sleep(2000);

		Reporter.log("Organization search completed", true);

		driver.findElement(By.partialLinkText(orgName)).click();

		Reporter.log("Organization selected successfully", true);

		// step 7> come back to home
		Reporter.log("Switching back to parent window...", true);
		driver.switchTo().window(PID);

		Reporter.log("Returned to Contact creation page", true);

		Thread.sleep(3000);

		Reporter.log("Entering Birthday...", true);
		driver.findElement(By.name("birthday")).sendKeys("2026-08-03");

		Reporter.log("Birthday entered successfully", true);

		// save
		Reporter.log("Saving Contact...", true);
		driver.findElement(By.className("save")).click();

		Reporter.log("Contact saved successfully", true);

		// verification
		Reporter.log("Starting Contact verification...", true);

		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		String actOrgName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a")).getText();

		Reporter.log("Expected Last Name: " + lastName, true);
		Reporter.log("Actual Last Name: " + actLastName, true);

		Reporter.log("Expected Organization Name: " + orgName, true);
		Reporter.log("Actual Organization Name: " + actOrgName, true);

//		if (actLastName.equals(lastName) && actOrgName.equals(orgName)) {
//
//			Reporter.log("Contact created successfullyyyy !!!", true);
//
//		} else {
//
//			Reporter.log("Could not create contact", true);
//		}
		boolean status = actOrgName.contains(orgName) && actLastName.contains(lastName);

		Assert.assertTrue(status);

		// logout
		Reporter.log("Preparing to logout...", true);
		WebElement profile = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

		// hover on profile icon
		WebDriverUtility wdUtil = new WebDriverUtility(driver);

		Reporter.log("Hovering over profile icon...", true);
		wdUtil.hover(profile);

		// click on sign out link
		Reporter.log("Clicking Sign Out...", true);
		driver.findElement(By.linkText("Sign Out")).click();
		Reporter.log("Logout completed successfully", true);

		// close the browser
		Reporter.log("Closing browser...", true);
		driver.quit();

		Reporter.log("========== CREATE CONTACT TEST COMPLETED ==========", true);
	}
}