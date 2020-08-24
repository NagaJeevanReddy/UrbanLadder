package testPack;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
	public void test0() {
		//logger = report.createTest("browser");

		openBrowser(ob.getCellData("Input", 0, 1));

	}

	@Test(priority = 0, groups = { "Smoke" })
	public void test1() {

		logger = report.createTest("opening website");

		PageBase pagebase = new PageBase(driver, logger);
		PageFactory.initElements(driver, pagebase);

		landingpage = pagebase.openWebsite(ob.getCellData("Input", 1, 1));
		ExpectedTitle = ob.getCellData("VerifyingPageTitles", 0, 0);
		pagebase.getTitle(ExpectedTitle);
	}

	@Test(priority = 1, groups = { "Smoke", "Regression" })
	public void Test2() {
		logger = report.createTest("search for study chair");
		PageBase pagebase = new PageBase(driver, logger);
		PageFactory.initElements(driver, pagebase);
		ExpectedTitle = ob.getCellData("VerifyingPageTitles", 0, 1);
		pagebase.getTitle(ExpectedTitle);

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
		PageBase pagebase = new PageBase(driver, logger);
		PageFactory.initElements(driver, pagebase);
		ExpectedTitle = ob.getCellData("VerifyingPageTitles", 0, 2);
		pagebase.getTitle(ExpectedTitle);

		studychairspage.topThreeChairs();
		studychairspage.moveMouseHover();
		bookshelvespage = studychairspage.clickOnOpenBooksShelves();
		logger.log(Status.INFO, "got top three chair details");
		pagebase.takeScreenShot();
	}

	@Test(priority = 3, groups = { "Smoke", "Regression" })
	public void Test4() {
		logger = report.createTest("opening book shelves page");
		PageBase pagebase = new PageBase(driver, logger);
		PageFactory.initElements(driver, pagebase);

		ExpectedTitle = ob.getCellData("VerifyingPageTitles", 0, 3);
		pagebase.getTitle(ExpectedTitle);
		bookshelvespage.bookshelf();
		//bookshelvespage.bookshelves();
		//bookshelvespage.updateExcel();
		try {
			Thread.sleep(5000);
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
