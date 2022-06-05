package test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver","C:\\Users\\91700\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-web-security"); 
		options.addArguments("--disable-blink-features=AutomationControlled");

		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://makemytrip.com/");
		
		driver.manage().window().maximize();
		
		WebElement loginButton=driver.findElement(By.xpath("//li[@data-cy='account']"));
		loginButton.click();
		
		WebElement userName=driver.findElement(By.xpath("//input[contains(@placeholder,'Enter email')]"));
		userName.sendKeys("gauravsinghmer@gmail.com");
		
		Thread.sleep(2000);
		
		WebElement continueButton=driver.findElement(By.xpath("//button[@data-cy='continueBtn']"));
		continueButton.click();
		
		Thread.sleep(2000);
		
		WebElement passwordInput=driver.findElement(By.xpath("//input[@placeholder='Minimum 6 characters.']"));
		passwordInput.sendKeys("Gauravchi@123");
		
		WebElement passLogin=driver.findElement(By.xpath("//button[@data-cy='login']"));
		passLogin.click();
		
	

	}

}
