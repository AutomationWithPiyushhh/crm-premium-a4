package object_repo_sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// =====================================================
	// CART PAGE ELEMENTS
	// =====================================================

	@FindBy(className = "inventory_item_name")
	private WebElement productName;

	@FindBy(className = "cart_quantity")
	private WebElement quantity;

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	// =====================================================
	// GETTERS
	// =====================================================

	public WebElement getProductName() {
		return productName;
	}

	public WebElement getQuantity() {
		return quantity;
	}

	public WebElement getCheckoutButton() {
		return checkoutButton;
	}
}