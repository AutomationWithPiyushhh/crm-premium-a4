package base_utility;

import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import generic_utility.FileUtility;

public class BaseClass {

	public WebDriver driver;

	@BeforeClass
	public void openBro() throws IOException, ParseException {

//		open browser

		String browser = FileUtility.getDataFromJSONFile("bro");

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

	}

	@BeforeMethod
	public void login() throws IOException, ParseException {
//		get the data json	
		String url = FileUtility.getDataFromJSONFile("url");
		String username = FileUtility.getDataFromJSONFile("un");
		String password = FileUtility.getDataFromJSONFile("pwd");

//		login	
		driver.get(url);

		WebElement un = driver.findElement(By.name("user_name"));
		un.sendKeys(username);

		WebElement pwd = driver.findElement(By.name("user_password"));
		pwd.sendKeys(password);

		WebElement loginButton = driver.findElement(By.id("submitButton"));
		loginButton.click();
	}

	@AfterMethod
	public void logout() {

//		logout
		WebElement profile = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

//	hover on profile icon
		Actions act = new Actions(driver);
		act.moveToElement(profile).build().perform();

//	click on sign out link
		driver.findElement(By.linkText("Sign Out")).click();
	}

	@AfterClass
	public void closeBro() throws InterruptedException {
//		close the browser
		Thread.sleep(3000);
		driver.quit();
	}
}
