import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Navigation {
	ScriptExecutor scriptExe=new ScriptExecutor();
	WebDriver driver=DriverManager.getDriver();

	Actions action=new Actions(driver);
	@Test
	public void start() {
		
;		driver.get("https://www.fitpeo.com");
		driver.navigate().to("https://www.fitpeo.com/revenue-calculator");
		driver.manage().window().maximize();
		
	}
	@Test(dependsOnMethods="start")
	public void getCalc() throws InterruptedException{
		Thread.sleep(3000);
		//js.executeScript("window.scrollBy(0,2000)");
		Thread.sleep(3000);
		
		
		By sliderBy=By.xpath("//span[contains(@class,'MuiSlider-thumb')]//input");
		WebElement slider= DriverManager.waitForElement(sliderBy);
		scriptExe.scroll(slider);
		Thread.sleep(2000);
		int width=slider.getSize().getWidth();
		int min=Integer.parseInt(slider.getAttribute("min"));
		int max=Integer.parseInt(slider.getAttribute("max"));
		int range=max-min;
		int offset=(int)((820-min)*width/(double)range);
		System.out.print(slider.getLocation());
		
		 
		 action.clickAndHold(slider).moveByOffset(93, 0).release().perform();
		 Thread.sleep(2000);
		 scriptExe.transform(slider);
		 Thread.sleep(2000);
		 
		/*	action.dragAndDropBy(slider,1, 0).perform();
			action.dragAndDropBy(slider,-5, 0).perform();
			action.dragAndDropBy(slider,6, 0).perform();
			
			*/
		 Thread.sleep(2000);
		 By inputValBy=By.xpath("//div[contains(@class,'MuiInputBase-root')] //input");
		 WebElement inputVal=DriverManager.waitForElement(inputValBy);
		 inputVal.click();
		 inputVal.sendKeys(Keys.BACK_SPACE);
		 inputVal.sendKeys(Keys.BACK_SPACE);
		 inputVal.sendKeys(Keys.BACK_SPACE);
		 inputVal.sendKeys("560");
		 Thread.sleep(4000);
		 Assert.assertEquals(slider.getAttribute("value"), "560");
		 
		 inputVal.sendKeys(Keys.BACK_SPACE);
		 inputVal.sendKeys(Keys.BACK_SPACE);
		 inputVal.sendKeys(Keys.BACK_SPACE);
		 inputVal.sendKeys("820");
		 
		/* By itr=By.cssSelector(".MuiInputBase-root");
		DriverManager.waitForElement(itr).findElement(By.xpath("//input").sendKeys("560");
		*/
		 scriptExe.scroll(inputVal);
		
		 
		 
		
		   
	}
	
	@Test(dependsOnMethods="getCalc")
	public void listCheck() throws Exception {
		By listDrop=By.cssSelector("body > div.MuiBox-root.css-3f59le > "
				+ "div.MuiBox-root.css-rfiegf >"
				+ " div.MuiBox-root.css-1p19z09");
		WebElement listS=DriverManager.driver.findElement(listDrop);
		List<WebElement> list=listS.findElements(By.xpath("./*"));
		for(WebElement x :list)
		{
			scriptExe.scroll(x);
			String name=x.findElement(By.cssSelector(".MuiTypography-root.MuiTypography-b"
					+ "ody1.inter.css-1s3unkt")).getText();
			try {
				if(name.equals("CPT-99091")
						||name.equals("CPT-99453")||name.equals("CPT-99454")||
						name.equals("CPT-99474"))
				{
					x.findElement(By.tagName("input")).click();
				}
			}
			catch(Exception e)
			{
				continue;
			}
		}
		By header=By.cssSelector("header+div>header");
		WebElement checkHeader=DriverManager.waitForElement(header);
		WebElement checkValue=checkHeader.findElement(By.xpath(".//p[text()='Total Recurring Reimbursement for all Patients Per Month:']"));
		Assert.assertEquals(checkValue.findElement(By.tagName("p")).getText(), "110700");
	}

}
