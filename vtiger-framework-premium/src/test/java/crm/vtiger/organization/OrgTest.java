package crm.vtiger.organization;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base_utility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.JavaUtility;
import org.testng.Assert;

public class OrgTest extends BaseClass {

	@Test
	public void createOrgTest() throws InterruptedException, EncryptedDocumentException, IOException, ParseException {
		// Report configuration
		ExtentSparkReporter spark = new ExtentSparkReporter("./reports/OrgTest.html");
		spark.config().setDocumentTitle("Vtiger Organization Report");
		spark.config().setReportName("Create Organization Test Report");
		spark.config().setTheme(Theme.STANDARD);

		ExtentReports report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Application", "Vtiger CRM");
		report.setSystemInfo("Module", "Organization");
		report.setSystemInfo("Tester", "AutomationWithPiyush");
		report.setSystemInfo("Environment", "Test");

		ExtentTest test = report.createTest("Create Organization Test");

		// Get data from Excel
		String orgName = FileUtility.getDataFromEXCELFile("org", 6, 0) + JavaUtility.generateRandomNumber();

		// Create organization
		driver.findElement(By.linkText("Organizations")).click();
		test.log(Status.PASS, "Organizations link clicked successfully");
		driver.findElement(By.cssSelector("img[title='Create Organization...']")).click();
		test.log(Status.PASS, "Create Organization page opened successfully");

		// Fill the form
		WebElement orgField = driver.findElement(By.name("accountname"));
		orgField.sendKeys(orgName);
		test.log(Status.PASS, "Organization name entered successfully");

		// Save
		driver.findElement(By.className("save")).click();
		test.log(Status.PASS, "Organization saved successfully");

		// Verification
		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		Assert.assertEquals(actOrgName, orgName);
		test.log(Status.PASS, "Organization created and verified successfully");

		// Report backup
		report.flush();
	}
}