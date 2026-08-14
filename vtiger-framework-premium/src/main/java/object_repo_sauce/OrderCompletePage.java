package object_repo_sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCompletePage {

	public OrderCompletePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// =====================================================
	// ORDER COMPLETE PAGE ELEMENTS
	// =====================================================

	@FindBy(className = "complete-header")
	private WebElement successMessage;

	// =====================================================
	// GETTERS
	// =====================================================

	public WebElement getSuccessMessage() {
		return successMessage;
	}
}