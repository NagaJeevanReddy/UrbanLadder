package pagesPack;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

import basePack.PageBase;
import utilsPack.ReadExcelDataFile;

public class StudyChairsPage extends PageBase {
	
	
	
	ReadExcelDataFile ob = new ReadExcelDataFile(
			System.getProperty("user.dir") + "\\src\\main\\resources\\objectRepository\\UrbanLadder.xlsx");

	public StudyChairsPage(WebDriver driver, ExtentTest logger,Properties prop) {
		super(driver, logger, prop);
	}

	@FindBy(xpath = "//div[@class='product-info-block']/descendant::a[@class='product-title-block']")
	public List<WebElement> chairName;
	@FindBy(xpath = "//div[@class='product-info-block']/descendant::div[@class='price-number']/span")
	public List<WebElement> chairPrice;
	@FindBy(xpath = "//div[@class='product-info-block']/descendant::div[@class='price-text']")
	public List<WebElement> chairDetails;

	public void topThreeChairs() {
		try {
			System.out.println("*****************************************");

			waitForCondition(5, chairName);
			ob.setCellData("StudyChairs", 1, 1,"Name");
			ob.setCellData("StudyChairs", 2, 1,"Price");
			ob.setCellData("StudyChairs", 3, 1,"Emi details");
			for (int i = 0; i < 3; i++) {
				
				  ob.setCellData("StudyChairs", 1, i+3,chairName.get(i).getAttribute("title"));
				  ob.setCellData("StudyChairs", 2, i+3,chairPrice.get(i).getText());
				  ob.setCellData("StudyChairs", 3, i+3,chairDetails.get(i).getText());
				 
			}
			
		} catch (Exception e) {
			reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	

	@FindBy(xpath = "//li[@class='topnav_item studyunit']")
	public WebElement study;
	@FindBy(xpath = "//*[@id='topnav_wrapper']/ul/li[6]/div/div/ul/li[3]/ul/li[2]/a")
	public WebElement bookShelves;

	public void moveMouseHover() {
		try {
			
		mouseHover(study);
		
		} catch (Exception e) {
			reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	
	public BookShelvesPage clickOnOpenBooksShelves() {

		try {

			bookShelves.click();

		} catch (Exception e) {
			reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}
		BookShelvesPage bookshelvespage = new BookShelvesPage(driver, logger,prop);
		PageFactory.initElements(driver, bookshelvespage);
		return bookshelvespage;
	}
	
}


