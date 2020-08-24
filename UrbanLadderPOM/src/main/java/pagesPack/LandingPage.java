package pagesPack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import basePack.PageBase;

public class LandingPage extends PageBase {

	public LandingPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	@FindBy(xpath = "//div[@class='popup-text text-center vert large-6 columns']/a[1]")
	public WebElement closeLoginPopUp;
	
	
	@FindBy(xpath = "//span[@class='twitter-typeahead']/child::input[@id='search']")
	public WebElement searchBox;

	@FindBy(xpath = "//span[@class='search-icon icofont-search']/parent::button")
	public WebElement searchButton;

	public void closeLoginPopUp() {
		try {
		waitForCondition(6).until(ExpectedConditions.elementToBeClickable(closeLoginPopUp));
		closeLoginPopUp.click();
		} catch (Exception e) {
			reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	public void enterItemInSearchBox() {
		try {
			sleep(5);
			waitForCondition(5, "//span[@class='twitter-typeahead']/child::input[@id='search']");
			enterText(searchBox, "Study chairs");
		} catch (Exception e) {
			reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	public StudyChairsPage clickSearchButton() {

		try {
			waitForCondition(5).until(ExpectedConditions.elementToBeClickable(searchButton));
			searchButton.click();
			
		} catch (Exception e) {
			reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}

		StudyChairsPage scp = new StudyChairsPage(driver, logger);
		PageFactory.initElements(driver, scp);
		return scp;
	}

}
