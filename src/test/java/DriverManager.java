import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverManager {
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	 Actions action = new Actions(driver);
	// Actions actionChain = new ActionChain(driver);
	
	public static WebDriver getDriver() {
		if(driver==null) {
			driver=new ChromeDriver();

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		}
		return driver;
		
	}
	public static void quitDriver() {
		if(driver!=null)
		{
			driver.quit();
			driver=null;
		}
		
	}
	public static WebElement waitForElement(By locator)
	{
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
}
