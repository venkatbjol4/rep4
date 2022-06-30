package ddfDemos;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DDFEbayProdCatSearchTest {
	@Test(dataProvider = "ebayData")
	public void catSearch(String ddValue,String txtValue) 
	{
		WebDriverManager.chromedriver().browserVersion("102").setup();
		WebDriver  driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://ebay.com");

		WebElement dd=driver.findElement(By.id("gh-cat"));
		Select s=new Select(dd);
		s.selectByVisibleText(ddValue);
		
		driver.findElement(By.id("gh-ac")).sendKeys(txtValue);
		driver.findElement(By.id("gh-btn")).click();

		Reporter.log("'" + txtValue + "' Product Search in '" + ddValue + "' Category Test is Successful...",true);
	}

	@DataProvider
	public Object[][] ebayData() throws Exception
	{
		Object[][] myData=null;

		XSSFWorkbook wb=new XSSFWorkbook(".\\src\\test\\resources\\DDFEbayTestData.xlsx");
		XSSFSheet ws= wb.getSheet("Sheet1");
		int rows=ws.getPhysicalNumberOfRows();

		myData=new Object[rows][2];
		for(int i=0;i<rows;i++)
		{
			myData[i][0]=ws.getRow(i).getCell(0).getStringCellValue();
			myData[i][1]=ws.getRow(i).getCell(1).getStringCellValue();
		}  

		return myData;
	}
}













