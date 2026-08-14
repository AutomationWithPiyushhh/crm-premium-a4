package pom_extra;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import object_repo_sauce.CartPage;
import object_repo_sauce.CheckoutInformationPage;
import object_repo_sauce.CheckoutOverviewPage;
import object_repo_sauce.LoginPage;
import object_repo_sauce.OrderCompletePage;
import object_repo_sauce.ProductsPage;

public class SauceDemoTest {

	public static void main(String[] args) {

		// =====================================================
		// 1. LAUNCH BROWSER
		// =====================================================

		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// =====================================================
		// 2. OPEN SAUCEDEMO
		// =====================================================

		driver.get("https://www.saucedemo.com/");

		// =====================================================
		// 3. CREATE PAGE OBJECTS
		// =====================================================

		LoginPage loginPage = new LoginPage(driver);

		ProductsPage productsPage = new ProductsPage(driver);

		CartPage cartPage = new CartPage(driver);

		CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);

		CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);

		OrderCompletePage orderCompletePage = new OrderCompletePage(driver);

		// =====================================================
		// 4. LOGIN
		// =====================================================

		WebElement username = loginPage.getUsername();
		username.sendKeys("standard_user");

		WebElement password = loginPage.getPassword();
		password.sendKeys("secret_sauce");

		WebElement loginButton = loginPage.getLoginButton();
		loginButton.click();

		// =====================================================
		// 5. VERIFY LOGIN
		// =====================================================

		WebElement pageTitle = productsPage.getPageTitle();

		String title = pageTitle.getText();

		if (title.equals("Products")) {

			System.out.println("Login Successful");

		} else {

			System.out.println("Login Failed");
		}

		// =====================================================
		// 6. ADD PRODUCT TO CART
		// =====================================================

		WebElement sauceLabsBackpack = productsPage.getSauceLabsBackpack();

		sauceLabsBackpack.click();

		System.out.println("Product added to cart");

		// =====================================================
		// 7. VERIFY CART COUNT
		// =====================================================

		WebElement cartBadge = productsPage.getCartBadge();

		String cartCount = cartBadge.getText();

		if (cartCount.equals("1")) {

			System.out.println("Cart contains 1 product");

		} else {

			System.out.println("Cart verification failed");
		}

		// =====================================================
		// 8. OPEN CART
		// =====================================================

		WebElement cartIcon = productsPage.getCartIcon();

		cartIcon.click();

		// =====================================================
		// 9. VERIFY PRODUCT IN CART
		// =====================================================

		WebElement productName = cartPage.getProductName();

		String product = productName.getText();

		System.out.println("Product in cart: " + product);

		if (product.equals("Sauce Labs Backpack")) {

			System.out.println("Product verification successful");

		} else {

			System.out.println("Wrong product in cart");
		}

		// =====================================================
		// 10. VERIFY QUANTITY
		// =====================================================

		WebElement quantityElement = cartPage.getQuantity();

		String quantity = quantityElement.getText();

		if (quantity.equals("1")) {

			System.out.println("Quantity verified: 1");

		} else {

			System.out.println("Quantity verification failed");
		}

		// =====================================================
		// 11. CLICK CHECKOUT
		// =====================================================

		WebElement checkoutButton = cartPage.getCheckoutButton();

		checkoutButton.click();

		// =====================================================
		// 12. ENTER CUSTOMER DETAILS
		// =====================================================

		WebElement firstName = checkoutInformationPage.getFirstName();
		firstName.sendKeys("Piyush");

		WebElement lastName = checkoutInformationPage.getLastName();
		lastName.sendKeys("Baldaniya");

		WebElement postalCode = checkoutInformationPage.getPostalCode();
		postalCode.sendKeys("201301");

		// =====================================================
		// 13. CLICK CONTINUE
		// =====================================================

		WebElement continueButton = checkoutInformationPage.getContinueButton();

		continueButton.click();

		// =====================================================
		// 14. VERIFY CHECKOUT OVERVIEW
		// =====================================================

		WebElement checkoutTitle = checkoutOverviewPage.getPageTitle();

		String checkoutTitleText = checkoutTitle.getText();

		if (checkoutTitleText.equals("Checkout: Overview")) {

			System.out.println("Checkout overview displayed");

		} else {

			System.out.println("Checkout overview verification failed");
		}

		// =====================================================
		// 15. VERIFY PRODUCT ON OVERVIEW
		// =====================================================

		WebElement overviewProduct = checkoutOverviewPage.getProductName();

		String overviewProductName = overviewProduct.getText();

		if (overviewProductName.equals("Sauce Labs Backpack")) {

			System.out.println("Product verified on overview page");

		} else {

			System.out.println("Product verification failed on overview page");
		}

		// =====================================================
		// 16. VERIFY ITEM TOTAL
		// =====================================================

		WebElement itemTotal = checkoutOverviewPage.getItemTotal();

		String total = itemTotal.getText();

		System.out.println("Item Total: " + total);

		// =====================================================
		// 17. PLACE ORDER
		// =====================================================

		WebElement finishButton = checkoutOverviewPage.getFinishButton();

		finishButton.click();

		// =====================================================
		// 18. VERIFY ORDER SUCCESS
		// =====================================================

		WebElement successMessage = orderCompletePage.getSuccessMessage();

		String message = successMessage.getText();

		if (message.equals("Thank you for your order!")) {

			System.out.println("--------------------------------------");
			System.out.println("E2E TEST PASSED");
			System.out.println("Order Successfully Placed");
			System.out.println("--------------------------------------");

		} else {

			System.out.println("E2E TEST FAILED");
		}

		// =====================================================
		// 19. CLOSE BROWSER
		// =====================================================

		driver.quit();
	}
}