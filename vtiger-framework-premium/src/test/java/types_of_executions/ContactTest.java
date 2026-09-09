package types_of_executions;

import org.testng.annotations.Test;

public class ContactTest {

	@Test(groups = "r")
	public void createContact() throws InterruptedException {
		System.out.println("regression");
	}

	@Test(groups = "s")
	public void modifyContact() throws InterruptedException {
		System.out.println("smoke");
	}

	@Test(groups = "s")
	public void deleteContact() throws InterruptedException {
		System.out.println("smoke");
	}
}
