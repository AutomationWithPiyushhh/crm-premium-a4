package crm.vtiger.organization;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;
import junit.framework.Assert;

public class OrgTest extends BaseClass{

	@Test
	public void createOrgTest() throws InterruptedException, EncryptedDocumentException, IOException, ParseException {

//		get data from excel
		String orgName = FileUtility.getDataFromEXCELFile("org", 6, 0) + JavaUtility.generateRandomNumber();

//		create organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.cssSelector("img[title='Create Organization...']")).click();

//		fill the form
		WebElement orgField = driver.findElement(By.name("accountname"));
		orgField.sendKeys(orgName);

//		save
		driver.findElement(By.className("save")).click();

//		verification
		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		Assert.assertEquals(orgName, actOrgName);

	}

}
