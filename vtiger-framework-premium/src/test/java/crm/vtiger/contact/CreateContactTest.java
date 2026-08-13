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

import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;

public class CreateContactTest {
	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

//		get the data json	
		String browser = FileUtility.getDataFromJSONFile("bro");
		String url = FileUtility.getDataFromJSONFile("url");
		String username = FileUtility.getDataFromJSONFile("un");
		String password = FileUtility.getDataFromJSONFile("pwd");
		
//		get data from excel
		String lastName = FileUtility.getDataFromEXCELFile("contact", 3, 0);
		String orgName = FileUtility.getDataFromEXCELFile("org", 6, 0);
		
//		open browser
		WebDriver driver = null;

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

//		login	
		driver.get(url);

		WebElement un = driver.findElement(By.name("user_name"));
		un.sendKeys(username);

		WebElement pwd = driver.findElement(By.name("user_password"));
		pwd.sendKeys(password);

		WebElement loginButton = driver.findElement(By.id("submitButton"));
		loginButton.click();

//		create contact
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.cssSelector("img[title='Create Contact...']")).click();

//		fill the form
		WebElement lastNameField = driver.findElement(By.name("lastname"));

		lastNameField.sendKeys(lastName);

//		step 1> get the home address 
		String PID = driver.getWindowHandle();

//		step 2> perform the task which will open new window/s or tab/s
		driver.findElement(By.xpath("//img[contains(@onclick, 'Popup&popuptype')]")).click();

//		Step 3> once all the windows are opened, get their addresses
		Set<String> CIDs = driver.getWindowHandles();

//		step 4> go through each and every window
		for (String i : CIDs) {
			driver.switchTo().window(i);

//		step 5> give condition for your desired web-page and break the loop
			if (driver.getCurrentUrl().contains("Accounts")) {
				break;
			}
		}
//		step 6> perform the task on your desired web-page
		driver.findElement(By.id("search_txt")).sendKeys(orgName + Keys.ENTER);

		Thread.sleep(2000);
		driver.findElement(By.partialLinkText(orgName)).click();

//		step 7> come back to home
		driver.switchTo().window(PID);

		Thread.sleep(3000);
		driver.findElement(By.name("birthday")).sendKeys("2026-08-03");
		
//		save
		driver.findElement(By.className("save")).click();

//		verification
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		String actOrgName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a")).getText();

		if (actLastName.equals(lastName) && actOrgName.equals(orgName)) {
			System.out.println("contact created successfullyyyy !!!");
		} else {
			System.out.println("Could not create contact");
		}

//		logout
		WebElement profile = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

//		hover on profile icon
//		Actions act = new Actions(driver);
//		act.moveToElement(profile).build().perform();

		WebDriverUtility wdUtil = new WebDriverUtility(driver);
		wdUtil.hover(profile);
		
//		click on sign out link
		driver.findElement(By.linkText("Sign Out")).click();

//		close the browser
		Thread.sleep(3000);
		driver.quit();
	}

}
