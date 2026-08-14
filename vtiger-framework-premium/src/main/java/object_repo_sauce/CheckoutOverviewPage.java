package object_repo_sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {

	public CheckoutOverviewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// =====================================================
	// CHECKOUT OVERVIEW PAGE ELEMENTS
	// =====================================================

	@FindBy(className = "title")
	private WebElement pageTitle;

	@FindBy(className = "inventory_item_name")
	private WebElement productName;

	@FindBy(className = "summary_subtotal_label")
	private WebElement itemTotal;

	@FindBy(id = "finish")
	private WebElement finishButton;

	// =====================================================
	// GETTERS
	// =====================================================

	public WebElement getPageTitle() {
		return pageTitle;
	}

	public WebElement getProductName() {
		return productName;
	}

	public WebElement getItemTotal() {
		return itemTotal;
	}

	public WebElement getFinishButton() {
		return finishButton;
	}
}