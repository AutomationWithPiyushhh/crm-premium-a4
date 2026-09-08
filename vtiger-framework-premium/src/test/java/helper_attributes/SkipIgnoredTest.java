package helper_attributes;

import org.junit.Assert;
import org.testng.annotations.Test;

public class SkipIgnoredTest {
	@Test(invocationCount = 1)
	public void createOrgTest() {
		System.out.println("org created...");

	}

	@Test(invocationCount = 0 ,dependsOnMethods = "createOrgTest")
	public void modifyOrgTest() {
		System.out.println("org modified...");
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "modifyOrgTest", enabled = false , alwaysRun = true)
	public void deleteOrgTest() {
		System.out.println("org deleted...");
	}
}
