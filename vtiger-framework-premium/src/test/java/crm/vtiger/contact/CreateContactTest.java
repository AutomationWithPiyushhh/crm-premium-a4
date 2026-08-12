package crm.vtiger.contact;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactTest {
	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

//		get the data json
		FileReader fr = new FileReader("./src/test/resources/commondata.json");

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fr);

		JSONObject jObj = (JSONObject) obj;
		String browser = jObj.get("bro").toString();
		String url = jObj.get("url").toString();
		String username = jObj.get("un").toString();
		String password = jObj.get("pwd").toString();		
		
		
//		get data from excel
		FileInputStream fis = new FileInputStream("./src/test/resources/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		Sheet sh1 = wb.getSheet("contact");
		Row row1 = sh1.getRow(3);
		Cell cell1 = row1.getCell(0);
		String lastName = cell1.getStringCellValue();
		
		Sheet sh2 = wb.getSheet("org");
		Row row2 = sh2.getRow(6);
		Cell cell2 = row2.getCell(0);
		String orgName = cell2.getStringCellValue();
		
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
		driver.findElement(By.linkText(orgName)).click();

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
		Actions act = new Actions(driver);
		act.moveToElement(profile).build().perform();

//		click on sign out link
		driver.findElement(By.linkText("Sign Out")).click();

//		close the browser
		Thread.sleep(3000);
		driver.quit();
	}

}
