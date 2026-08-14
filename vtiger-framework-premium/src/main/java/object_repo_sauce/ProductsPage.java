package object_repo_sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

	public ProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// =====================================================
	// PRODUCTS PAGE ELEMENTS
	// =====================================================

	@FindBy(className = "title")
	private WebElement pageTitle;

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement sauceLabsBackpack;

	@FindBy(className = "shopping_cart_badge")
	private WebElement cartBadge;

	@FindBy(className = "shopping_cart_link")
	private WebElement cartIcon;

	// =====================================================
	// GETTERS
	// =====================================================

	public WebElement getPageTitle() {
		return pageTitle;
	}

	public WebElement getSauceLabsBackpack() {
		return sauceLabsBackpack;
	}

	public WebElement getCartBadge() {
		return cartBadge;
	}

	public WebElement getCartIcon() {
		return cartIcon;
	}
}