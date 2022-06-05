package test;

import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Amazon {

	private static WebDriver driver;
	private static String expectedProduct;
	@BeforeTest
	public static void launchWebdriver() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\91700\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		String url="https://www.amazon.in ";
		driver.get(url);
		driver.manage().window().maximize();
	}

	@Test(priority=1)
	public static void logIn() {

		WebElement signIn=driver.findElement(By.xpath("//a[@data-nav-role='signin' and contains(@href,'signin')][1]"));
		signIn.click();

		WebElement typeEmail=driver.findElement(By.xpath("//input[@type='email']"));
		typeEmail.sendKeys("7009164133",Keys.ENTER);

		WebElement typePassword=driver.findElement(By.xpath("//input[@type='password']"));
		typePassword.sendKeys("amazonkapass",Keys.ENTER);
	}

	@Test(priority=2)
	public static void selectDropDown()
	{
		Select dropBox=new Select(driver.findElement(By.xpath("//select[@id='searchDropdownBox']")));

		dropBox.selectByVisibleText("Electronics");
	}
	@Test(priority=3)
	public static void searchIphone12()
	{

		WebElement searchBox=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchBox.sendKeys("Iphone 12",Keys.ENTER);
	}
	@Test(priority=4)
	public static void selectFirstResult()
	{

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");

		WebElement firstResult=driver.findElement(By.xpath("//div[@data-component-type=\"s-search-result\"][1]//a[1]"));
		firstResult.click();
	}

	@Test(priority=5)
	public static void addToCart() throws InterruptedException
	{	
		String currWindow = driver.getWindowHandle();
		Set<String> allWindows=driver.getWindowHandles();
		Iterator<String> i = allWindows.iterator();
		while(i.hasNext()) {
			String wind = i.next();
			if(wind!=currWindow)
			{
				driver.switchTo().window(wind);

			}
		}

		expectedProduct=driver.findElement(By.xpath("//h1[@id='title' and @class='a-size-large a-spacing-none']//span")).getText();
		System.out.print(expectedProduct);
		Thread.sleep(2000);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,250)", "");

		WebElement addTocart=driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		addTocart.click();
	}
	@Test(priority=6)
	public static void goTocart() throws InterruptedException
	{

		Thread.sleep(2000);
		WebElement cartButton=driver.findElement(By.xpath("//span[contains(@id,'view-cart-button')]//input[@type='submit']"));

		Thread.sleep(2000);

		cartButton.click();

		Thread.sleep(2000);
	}
	@Test(priority=7)
	public static void checkCartdetails()
	{
		String actualProduct=driver.findElement(By.xpath("//a[contains(@class,'product-link')]//span[contains(@class,'product-title')][1]")).getText();
		System.out.print(actualProduct);
		Assert.assertEquals(actualProduct, expectedProduct,"Does Not Maatch"); 
	}

	@AfterTest
	public static void closeDriver()
	{
		driver.quit();
	}
}

