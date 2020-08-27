package testPack;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import basePack.BaseClass;
import basePack.PageBase;
import pagesPack.BookShelvesPage;
import pagesPack.LandingPage;
import pagesPack.StudyChairsPage;
import utilsPack.ReadExcelDataFile;

public class UrbanLadderTestClass extends BaseClass {

	String ExpectedTitle = null;
	ReadExcelDataFile ob = new ReadExcelDataFile(
			System.getProperty("user.dir") + "\\src\\main\\resources\\objectRepository\\UrbanLadder.xlsx");
	LandingPage landingpage;
	StudyChairsPage studychairspage;
	BookShelvesPage bookshelvespage;

	@BeforeTest(groups = { "Smoke" })
	@Parameters("browser")
	public void test0(String browser) {
		//logger = report.createTest("browser");

		openBrowser(browser);

	}

	@Test(priority = 0, groups = { "Smoke" })

	public void test1() {

		logger = report.createTest("opening website");

		PageBase pagebase = new PageBase(driver, logger, prop);
		PageFactory.initElements(driver, pagebase);

		landingpage = pagebase.openWebsite("url");
		ExpectedTitle = ob.getCellData("VerifyingPageTitles", 0, 0);
		ExpectedTitle=prop.getProperty("landingpage");
		pagebase.getTitle(ExpectedTitle);
	}

	@Test(priority = 1, groups = { "Smoke", "Regression" })
	public void Test2() {
		logger = report.createTest("search for study chair");
		PageBase pagebase = new PageBase(driver, logger, prop);
		PageFactory.initElements(driver, pagebase);
		

		logger.log(Status.INFO, "closing login pop-up");
		landingpage.closeLoginPopUp();
		logger.log(Status.INFO, "login popup closed");
		logger.log(Status.INFO, "entering the product name in search box");
		landingpage.enterItemInSearchBox();
		logger.log(Status.INFO, "Entered product name in search box");
		logger.log(Status.INFO, "clicking on the search button");
		studychairspage = landingpage.clickSearchButton();
		logger.log(Status.INFO, "successfully clicked on the search button");
		logger.log(Status.INFO, "study chairs page opened");
		pagebase.takeScreenShot();

	}

	@Test(priority = 2, groups = { "Smoke", "Regression" })
	public void Test3() {
		logger = report.createTest("getting top three chair details");
		PageBase pagebase = new PageBase(driver, logger, prop);
		PageFactory.initElements(driver, pagebase);
		
		studychairspage.topThreeChairs();
		studychairspage.moveMouseHover();
		bookshelvespage = studychairspage.clickOnOpenBooksShelves();
		//bookshelvespage=studychairspage.enterTxtOnSearchBox();
		logger.log(Status.INFO, "got top three chair details");
		pagebase.takeScreenShot();
	}

	@Test(priority = 3, groups = { "Smoke", "Regression" })
	public void Test4() {
		logger = report.createTest("opening book shelves page");
		PageBase pagebase = new PageBase(driver, logger, prop);
		PageFactory.initElements(driver, pagebase);

		ExpectedTitle = prop.getProperty("bookshelvespage");
		pagebase.getTitle(ExpectedTitle);
		//bookshelvespage.bookshelf();
		bookshelvespage.bookshelves();
		bookshelvespage.updateExcel();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.log(Status.INFO, "got top book shelves details");
	}

	@AfterTest(groups = { "Smoke" })
	public void closeBrowser() {
		report.flush();
		driver.quit();
	}
}
