package pagesPack;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import basePack.PageBase;
import utilsPack.ReadExcelDataFile;

public class BookShelvesPage extends PageBase {

	ArrayList<ArrayList<String>> mainList = new ArrayList<ArrayList<String>>();
	ArrayList<String> List1 = new ArrayList<String>();
	ArrayList<String> List2 = new ArrayList<String>();
	ArrayList<String> List3 = new ArrayList<String>();
	ArrayList<String> List4 = new ArrayList<String>();
	ReadExcelDataFile ob = new ReadExcelDataFile(
			System.getProperty("user.dir") + "\\src\\main\\resources\\objectRepository\\UrbanLadder.xlsx");
	
	public BookShelvesPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	@FindBy(xpath = "//div[@class='product-title product-title-sofa-mattresses']/span")
	public List<WebElement> itemName;
	@FindBy(xpath = "//div[@class='price-number']/span")
	public List<WebElement> itemPrice;
	@FindBy(xpath = "//div[@class='price-text']")
	public List<WebElement> emiDetails;
	@FindBy(xpath = "//div[@class='product-title product-title-sofa-mattresses']/div[@class='small']")
	public List<WebElement> itemDetails;

	public void bookshelves() {
		try {
			ob.setCellData("BookShelves", 1, 1,"Name");
			ob.setCellData("BookShelves", 2, 1,"Price");
			ob.setCellData("BookShelves", 3, 1,"EmiDetails");
			ob.setCellData("BookShelves", 4, 1,"ItemDetails");
			
			
			int n = itemName.size();
		
			String priceString;
			
			for (int i = 0; i < n; i++) {

				
				
				priceString = itemPrice.get(i).getText().replaceAll("[^0-9]", "");
				priceString.trim();
				if(priceString=="") {
					break;
				}
				int price=Integer.parseInt(priceString);
				
				if(price<15000) {
					
			
				System.out.println(itemName.get(i).getText());
				System.out.println(itemPrice.get(i).getText());
				System.out.println(emiDetails.get(i).getText());
				System.out.println(itemDetails.get(i).getText());
				List1.add(itemName.get(i).getText());
				List2.add(itemPrice.get(i).getText());
				List3.add(emiDetails.get(i).getText());
				List4.add(itemDetails.get(i).getText());
				
				
				}
			}
			mainList.add(List1);
			mainList.add(List2);
			mainList.add(List3);
			mainList.add(List4);
			logger.log(Status.PASS, "Able to get the product details in array list");
		} catch (Exception e) {
			reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	public void updateExcel() {
		
		for(int i=0;i<List1.size();i++) {
			
		ob.setCellData("BookShelves", 1, i+3, mainList.get(0).get(i));
		ob.setCellData("BookShelves", 2, i+3, mainList.get(1).get(i));
		ob.setCellData("BookShelves", 3, i+3, mainList.get(2).get(i));
		ob.setCellData("BookShelves", 4, i+3, mainList.get(3).get(i));
		}
	}
	@FindBy(xpath="//div[@class='noUi-handle noUi-handle-upper']")
	public WebElement slider;
	@FindBy(xpath="//li[@data-group='price']")
	public WebElement priceele;
	public void bookshelf() {
	Actions act=new Actions(driver);
	act.moveToElement(priceele).build().perform();
	act.dragAndDropBy(slider, 40, 40).release().build().perform();;
	}

}
