package basePack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilsPack.ExtentReporterManager;



public class BaseClass {

	public WebDriver driver;
	public ExtentReports report = ExtentReporterManager.getReportInstance();
	public ExtentTest logger;
	

	// invoke the browser and properties file
	public void openBrowser(String BrowserName) {
		try {
			
			

			if (BrowserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\chromedriver.exe");
				ChromeOptions qq = new ChromeOptions();
				qq.addArguments("--disable-notifications");

				driver = new ChromeDriver(qq);
			} else if (BrowserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (BrowserName.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} else if (BrowserName.equalsIgnoreCase("opera")) {
				System.setProperty("webdriver.opera.driver",
						System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\operadriver.exe");
				ChromeOptions qq = new ChromeOptions();
				System.setProperty(OperaDriverService.OPERA_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
				qq.addArguments("--disable-notifications");

				driver = new OperaDriver(qq);
			} else {
				System.out.println("Please enter from the following: chrome,ie,firefox");
				Assert.fail("Invalid input");
			}
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			//driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			driver.manage().window().maximize();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Driver not found");
			e.printStackTrace();
		}

	}
	
	/*
	 * @AfterMethod public void flushReports() { report.flush(); driver.close(); }
	 */

}
