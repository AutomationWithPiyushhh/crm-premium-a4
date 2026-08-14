package object_repo_sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformationPage {

	public CheckoutInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// =====================================================
	// CHECKOUT INFORMATION PAGE ELEMENTS
	// =====================================================

	@FindBy(id = "first-name")
	private WebElement firstName;

	@FindBy(id = "last-name")
	private WebElement lastName;

	@FindBy(id = "postal-code")
	private WebElement postalCode;

	@FindBy(id = "continue")
	private WebElement continueButton;

	// =====================================================
	// GETTERS
	// =====================================================

	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getPostalCode() {
		return postalCode;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}
}