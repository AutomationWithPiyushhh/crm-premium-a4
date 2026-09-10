package crm.vtiger.opp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base_utility.BaseClass;

public class OppTest extends BaseClass {
	
	@Test
	public void createOppTest() throws InterruptedException {

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


	}

}
