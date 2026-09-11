package learning_advance_reports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SauceDemo {
	@Test
	public void login() {

//		report configuration
		ExtentSparkReporter spark = new ExtentSparkReporter("./reports/rep1.html");

		spark.config().setDocumentTitle("SauceDemo report");
		spark.config().setReportName("first report");
		spark.config().setTheme(Theme.STANDARD);

		ExtentReports report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("key", "value");

		ExtentTest test = report.createTest("login");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.quit();

		test.log(Status.PASS, "it is passed");
		test.log(Status.FAIL, "it is failed");
		test.log(Status.SKIP, "it is skipped");
		test.log(Status.WARNING, "it is warning");
		test.log(Status.INFO, "it is information");

//		report backup
		report.flush();
	}
}
