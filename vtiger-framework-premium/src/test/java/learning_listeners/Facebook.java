package learning_listeners;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Facebook {
	ExtentReports report;

	@BeforeSuite
	public void reportConfig() {

//		report configuration
		ExtentSparkReporter spark = new ExtentSparkReporter("./reports/rep5.html");

		spark.config().setDocumentTitle("SauceDemo report");
		spark.config().setReportName("first report");
		spark.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("key", "value");

	}

	@Test
	public void signup() {

		ExtentTest test = report.createTest("signup");

		System.out.println("signup");

		test.log(Status.PASS, "passed");

	}

	@Test
	public void login() {
		ExtentTest test = report.createTest("login");

		System.out.println("login");

		test.log(Status.PASS, "passed");

	}

	@Test
	public void logout() {

		ExtentTest test = report.createTest("logout");

		System.out.println("logout");

		test.log(Status.PASS, "this is passed");
	}

	@AfterSuite
	public void repBackup() {
		report.flush();
	}
}
