package basePack;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pagesPack.LandingPage;
import utilsPack.GettingDate;

public class PageBase extends BaseClass {
	
//	public ExtentTest logger;
	

	public PageBase(WebDriver driver, ExtentTest logger) {
		this.driver=driver;
		this.logger=logger;
	}

	//It opens the website
	public LandingPage openWebsite(String WebsiteURL) {
		driver.get(WebsiteURL);
		logger.log(Status.INFO, "Opening website");
		
		LandingPage landingpage = new LandingPage(driver, logger);
		PageFactory.initElements(driver, landingpage);
		return landingpage;
	}
	
	public WebDriverWait waitForCondition(int i) {
		WebDriverWait wait=new WebDriverWait(driver, i*1000);
		return wait;
	}

	public void enterText(WebElement ele,String txt) {
		ele.sendKeys(txt);
	}
	
	public void waitForCondition(int i,String xpath) {
		WebDriverWait wait=new WebDriverWait(driver, i*1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}
	
	public void waitForCondition(int i,List<WebElement> elements) {
		WebDriverWait wait=new WebDriverWait(driver, i*1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void sleep(int i) {
		try {
			Thread.sleep(i*1000);
		} catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public void mouseHover(WebElement locator) {
		Actions action=new Actions(driver);
		action.moveToElement(locator).build().perform();
	}
	/****************** Get Page Title ***********************/
	public void getTitle(String expectedTitle) {
		try {
			//Assert.assertEquals(driver.getTitle(), expectedTitle);
			logger.log(Status.PASS,"Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
	/****************** Reporting Functions ***********************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShot();
		//Assert.fail(reportString);
	}
	/****************** Capture Screen Shot ***********************/
	public void takeScreenShot() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + GettingDate.getdate() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/ScreenShots/" +  GettingDate.getdate()  + ".png");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
