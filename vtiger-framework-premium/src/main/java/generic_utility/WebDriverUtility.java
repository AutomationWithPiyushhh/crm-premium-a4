package generic_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebDriverUtility {
	Actions act;
	WebDriver driver;

	public WebDriverUtility(WebDriver driver) {
		act = new Actions(driver);
		this.driver = driver;
	}

	public void hover(WebElement element) {
		act.moveToElement(element).build().perform();
	}

	public void rightClick(WebElement element) {
		act.contextClick(element).build().perform();
	}

	public void clickAndHold(WebElement element) {
		act.clickAndHold(element).build().perform();
	}
	
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
}