package DamcoAssignment.DamcoAssignment.com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MMTTask {
	public WebDriver driver = null;
	public static final org.apache.log4j.Logger logger = LogManager.getLogger(MMTTask.class.getName());
	
	
	@BeforeTest
	public void setUpl() throws FileNotFoundException, IOException {
		String systemDirectory = System.getProperty("user.dir");
		logger.info("Getting the System Directory");
		Properties props = new Properties();
		logger.info("Setting the System Properties File");
		props.load(new FileInputStream(systemDirectory + "/log4j.properties"));
		PropertyConfigurator.configure(props);
		logger.info("Setting the System Properties File Path");
		System.setProperty("webdriver.chrome.driver", systemDirectory + "/driver/chromedriver");
		logger.info("Setting the System Directory");
		driver = new ChromeDriver();
		logger.info("Initializating the Browser");
		driver.manage().window().maximize();
		logger.info("Maximizing the browser");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("Implicit Wait added for 10 seconds");
		driver.get("https://www.makemytrip.com/");
		logger.info("Open the URL in the browser ");
	}
	
	@Test
	public void flightSearch() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"fromCity\"]")));
		logger.info("Using Explicit wait for 20 Seconds to show up the element in the browser");
		WebElement FromField = driver.findElement(By.xpath("//input[@id=\"fromCity\"]"));
		logger.info("Intializing the xpath for FromField");
		FromField.click();
		logger.info("Click on the FromField");
		FromField.sendKeys("Delhi");
		logger.info("Enter the FromField using sendkeys");
		WebElement ToField = driver.findElement(By.xpath("//input[@id=\"toCity\"]"));
		logger.info("Intializing the xpath for FromField");
		ToField.click();
		logger.info("Click on the ToField");
		ToField.sendKeys("Mum");
		logger.info("Enter the ToField using sendkeys");
		ToField.sendKeys(Keys.ARROW_DOWN);
		logger.info("Through Action Class keys , Selected the option by putting down key from keyboard");
		WebElement ToFieldText = driver.findElement(By.xpath("//p[contains(text(),'Mumbai, India')]"));
		logger.info("Intializing the xpath for ToFieldText");
		ToFieldText.click();
		logger.info("Click on the ToFieldText");
		WebElement DatePickerDeparture = driver.findElement(By.xpath("//div[@aria-label=\"Thu Jun 15 2023\"]"));
		logger.info("Intializing the xpath for DatePickerDeparture");
		DatePickerDeparture.click();
		logger.info("Click on the DatePickerDeparture");
		WebElement SearchButton = driver.findElement(By.xpath("//a[contains(text(),'Search')]"));
		logger.info("Intializing the xpath for SearchButton");
		SearchButton.click();
		logger.info("Click on the SearchButton");
		WebElement commonOverlay = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class=\"commonOverlay \"]/span")));
		logger.info("Using Explicit wait for 10 Seconds to show up the element in the browser");
		commonOverlay.click();
		logger.info("Click on the commonOverlay");
		WebElement SortByDeparture = driver.findElement(By.xpath("//span[contains(text(),'Departure')]"));
		logger.info("Intializing the xpath for SortByDeparture");
		SortByDeparture.click();
		logger.info("Click on the SortByDeparture");
		WebElement FlightElementdiv = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@id=\"listing-id\"]")));
		logger.info("Using Explicit wait for 10 Seconds to show up the FlightElementdiv in the browser");
		List<WebElement> FlightElements = driver.findElements(By.xpath("//p[@class=\"boldFont blackText airlineName\"]"));
		logger.info("Insert List of elements");
		logger.info("To take out the 2nd Lowest Flight Name & fare , Logic works here !");
		if(FlightElements.size() >=10) {
			WebElement secondLowestFlight = FlightElements.get(2);
			WebElement airlineElement = secondLowestFlight.findElement(By.xpath("//p[@class=\"boldFont blackText airlineName\"]"));
			WebElement priceElement = secondLowestFlight.findElement(By.xpath("//div[@class=\"priceSection\"]/div//div/div"));
		
			System.out.println("2nd Lowest Fligt Name:" +airlineElement.getText());
			System.out.println("2nd Lowest Fligt Price :" +priceElement.getText());
		}
		else {
			System.out.println("Not Found");
		}
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
		logger.info("Quit the Browser & tabs");
	}
}
