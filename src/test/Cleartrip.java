package test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Test
public class Cleartrip {
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static final String email = "tanvimer1031@gmail.com";
	private static final String password = "Gauravchi@123";

	
	
	@BeforeTest
	public static void launchWebDriver() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		String url="https://www.cleartrip.com/flights";
		driver.get(url);
		driver.manage().window().maximize();

	}
	@Test(priority=1)
	public static void loginCleartrip() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		if(driver.findElements( By.xpath("//div[text()='Log in or Sign up']")).size() == 0) {
			driver.findElement(By.xpath("//p[text()='Log in']")).click();
			driver.findElement(By.xpath("//button[text()='Log in / Sign up']")).click();
		}
        driver.findElement(By.xpath("//span[text() = 'Continue with Email']")).click();
        
        String emailInput = "//input[@data-testid='email']";
        wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailInput)));
        
        driver.findElement(By.xpath(emailInput)).sendKeys(email);
        driver.findElement(By.xpath("//input[@data-testid='password']")).sendKeys(password);        
		driver.findElement(By.xpath("//span[text()='Log in']")).click();

		
	}
	@Test(priority = 2)
	public static void selectRoundTrip() {
		//Stale Element Reference 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement roundTripRadio = driver.findElement(By.xpath("//p[text()='Round trip']"));
	    roundTripRadio.click();
	}
	@Test(priority=3)
	public static void searchFlight()
	{
		List<WebElement> fromTo = driver.findElements(By.xpath("//input[@placeholder='Any worldwide city or airport']"));
		
		fromTo.get(0).sendKeys("Chandigarh");
		driver.findElement(By.xpath("//ul//p[text()='Chandigarh, IN - Chandigarh (IXC)']")).click();
		
		fromTo.get(1).sendKeys("Pune");
		driver.findElement(By.xpath("//ul//p[text()='Pune, IN - Lohegaon (PNQ)']")).click();
	
		driver.findElement(By.xpath("//button[text()='Search flights']")).click();
	}
	
	@Test(priority=4)
	public static void selectDepartureDate() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='masterTupple']")));
		String depMonth = date("MMMM",1);
		String depDate = date("d",1);
		String depYear = date("YYYY",1);

		List<WebElement> dates = driver.findElements(By.xpath("//div[contains(text(),',') and contains(text(),'"+date("d",0)+"')]/ancestor::button"));
		dates.get(0).click();
		driver.findElement(By.xpath("//div[text()='"+depMonth+" "+depYear+"']/ancestor::div[@class='DayPicker-Month']//div[text()='"+depDate+"']")).click();
		
		driver.findElement(By.xpath("//div[text()='"+date("MMMM",2)+" "+date("YYYY",2)+"']/ancestor::div[@class='DayPicker-Month']//div[text()='"+date("d",2)+"']")).click();
		
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		
	}
	public static String date(String format, int addDays) {  
	    SimpleDateFormat formatter = new SimpleDateFormat(format);  
	    Date date = new Date();  
	    Calendar cl = Calendar.getInstance();
	    cl.setTime(date);
	    cl.add(Calendar.DATE, addDays);
	    return formatter.format(cl.getTime());  
	}  
	
	

}
