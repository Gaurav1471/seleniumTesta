package test;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BajajFinservHealth {

	private static WebDriver driver;
	private static String expectedTime;
	
	@BeforeTest
	public static void launchWebdriver() {
		System.out.println(System.getProperty("user.dir"));
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		String url="https://www.google.com/";
		driver.get(url);
		driver.manage().window().maximize();

	}
	@Test(priority =1)
	public static void navigateToBajajWebPage() {
		WebElement searchBox=driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
		searchBox.sendKeys("Bajaj Finserv health",Keys.ENTER);
		driver.findElement(By.xpath("//h3[text()='Bajaj Finserv Health - Your Personalized Healthcare Platform']")).click();
	}

	@Test(priority =2)
	public static void searchDoctorAndSelectSlot() throws InterruptedException {

		driver.findElement(By.xpath("//input[@placeholder='Search Symptoms, Doctors, Specialists, Clinics']")).sendKeys("Doctor",Keys.ENTER);
		Thread.sleep(1000); 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000); 
		WebElement appointmentTime =driver.findElement(By.xpath("//button[text()='Book Appointment'][1]"));
		appointmentTime.click();
		
	}

	@Test(priority =3)
	public static void checkAppointmentDetails() throws InterruptedException {
		Thread.sleep(2000);
		expectedTime = driver.findElement(By.xpath("//div[@class='slotTimeContainer']//span[@class='MuiButton-label']")).getText();
		driver.findElement(By.xpath("//div[@class='slotTimeContainer']//span[@class='MuiButton-label']")).click();
		Thread.sleep(2000);
		String actualTime=driver.findElement(By.xpath("//div[@data-testid='appointment-time']")).getText();
		Assert.assertEquals(actualTime, expectedTime,"");
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
