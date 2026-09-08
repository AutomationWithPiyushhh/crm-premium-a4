package crm.vtiger.document;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class DocTest {
	
	@Test
	public void createDocTest() throws InterruptedException {

//		open browser
		WebDriver driver = null;

		String browser = "chrome";

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
		driver.get("http://localhost:8888");

		WebElement un = driver.findElement(By.name("user_name"));
		un.sendKeys("admin");

		WebElement pwd = driver.findElement(By.name("user_password"));
		pwd.sendKeys("password");

		WebElement loginButton = driver.findElement(By.id("submitButton"));
		loginButton.click();

//		create organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.cssSelector("img[title='Create Organization...']")).click();

//		fill the form
		String orgName = "qsp" + System.currentTimeMillis() / 1000;
		WebElement orgField = driver.findElement(By.name("accountname"));

		orgField.sendKeys(orgName);

//		save
		driver.findElement(By.className("save")).click();

//		verification
		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();

		if (actOrgName.equals(orgName)) {
			System.out.println("org created successfullyyyy !!!");
		} else {
			System.out.println("Could not create organization");
		}

//		logout
		WebElement profile = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

//		hover on profile icon
		Actions act = new Actions(driver);
		act.moveToElement(profile).build().perform();

//		click on sign out link
		driver.findElement(By.linkText("Sign Out")).click();

//		close the browser
		Thread.sleep(3000);
		driver.quit();
	}

}
