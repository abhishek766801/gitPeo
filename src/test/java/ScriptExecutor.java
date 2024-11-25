import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScriptExecutor {
	WebDriver driver=DriverManager.getDriver();
	JavascriptExecutor js=(JavascriptExecutor)driver;
	void scroll(WebElement ele)
	{
		js.executeScript( "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);" + "var elementTop = arguments[0].getBoundingClientRect().top;" 
	+ "window.scrollBy(0, elementTop - (viewPortHeight / 2));", ele );
	}
	
	void SetValue(WebElement ele,String val)
	{
		js.executeScript("arguments[0].value="+val+";", ele);
	}
	void transform(WebElement ele)
	{
		js.executeScript("arguments[0].style.transform='translateX(10px)';", ele);
	}
	

}
