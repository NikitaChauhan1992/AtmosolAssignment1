package DamcoAssignment.DamcoAssignment.com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EmailTask {
	public WebDriver driver = null;
	public static final org.apache.log4j.Logger logger = LogManager.getLogger(EmailTask.class.getName());

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
		driver.get("https://mail.aol.com/");
		logger.info("Open the URL in the browser ");
	}

	@Test(priority = 1)
	public void openMail() {
		WebElement LoginButton = driver.findElement(By.xpath("//a[@class=\"login\"]/span"));
		logger.info("Intializing the xpath for LoginButton");
		LoginButton.click();
		logger.info("Click on the LoginButton");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"login-username\"]")));
		logger.info("Using Explicit wait for 10 Seconds to show up the element in the browser");
		WebElement userName = driver.findElement(By.xpath("//input[@id=\"login-username\"]"));
		userName.sendKeys("jatin1594");
		logger.info("Using send Keys data passed to the input field");
		WebElement nextButton = driver.findElement(By.xpath("//input[@id=\"login-signin\"]"));
		nextButton.click();
		logger.info("Click on the nextButton");
		WebElement password = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"login-passwd\"]")));
		logger.info("Using Explicit wait , Intializing the xpath for password");
		password.sendKeys("Password@0110");
		logger.info("Enter the password");
		WebElement nextButtonPwd = driver.findElement(By.xpath("//button[@id=\"login-signin\"]"));
		logger.info("Intializing the xpath for nextButtonPwd");
		nextButtonPwd.click();
		logger.info("Click on the nextButtonPwd");
	}

	@Test(priority = 2)
	public void composeMail() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement mailCompose = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Compose')][@role=\"button\"]")));
		logger.info("Using Explicit wait , Intializing the xpath for mailCompose");
		mailCompose.click();
		logger.info("Click on the mailCompose");
		WebElement mailSubject = driver.findElement(By.xpath("//input[@placeholder=\"Subject\"]"));
		logger.info("Intializing the xpath for mailSubject");
		mailSubject.sendKeys("Damco");
		logger.info("Enter the Subject of the Mail");
		WebElement mailBody = driver.findElement(By.xpath("//div[@aria-label=\"Message body\"]"));
		logger.info("Intializing the xpath for mailBody");
		String data = "• First bullet point\n" + "• Second bullet point\n" + "• Third bullet point";
		mailBody.sendKeys(data);
		logger.info("Through send keys , send the data to mailBody");
		WebElement mailTo = driver.findElement(By.xpath("//input[@id=\"message-to-field\"]"));
		logger.info("Intializing the xpath for mailTo");
		mailTo.sendKeys("jatin1594@aol.com");
		logger.info("Through send keys , send the data to To");
		WebElement mailSent = driver.findElement(By.xpath("//button[@title=\"Send this email\"]"));
		logger.info("Intializing the xpath for mailSent");
		mailSent.click();
		logger.info("Click on the mailSent");

	}

	@Test(priority = 3)
	public void verifyMail() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement mailInbox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Inbox')]")));
		logger.info("Using Explicit wait , Intializing the xpath for mailInbox");
		mailInbox.click();
		logger.info("Click on the mailInbox");
		String expectedSubject = "Damco";
		WebElement email = driver.findElement(By.xpath("//span[@title=\"Damco\"]"));
		logger.info("Intializing the xpath for email");
		String actualSubject = email.getText();
		logger.info("Taking out the text from the email ");
		Assert.assertEquals(actualSubject, expectedSubject, "Email Subject Matched");
		logger.info("Assertion applied to verify the email subject ");
		email.click();
		logger.info("Click on the email");
		WebElement bodyText = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"msg-body P_wpofO mq_AS\"]")));
		logger.info("Using Explicit wait , Intializing the xpath for bodyInbox");
		WebElement firstpoint = driver
				.findElement(By.xpath("//div[@class=\"msg-body P_wpofO mq_AS\"]/div/div/div/div/div/div[1]"));
		logger.info("Intializing the xpath for firstpoint");
		String firstPoint = firstpoint.getText();
		logger.info("Taking out the text from the firstPoint ");
		WebElement secondpoint = driver
				.findElement(By.xpath("//div[@class=\"msg-body P_wpofO mq_AS\"]/div/div/div/div/div/div[2]"));
		logger.info("Intializing the xpath for secondpoint");
		String secondPoint = secondpoint.getText();
		logger.info("Taking out the text from the secondPoint ");
		WebElement thirdpoint = driver
				.findElement(By.xpath("//div[@class=\"msg-body P_wpofO mq_AS\"]/div/div/div/div/div/div[3]"));
		logger.info("Intializing the xpath for thirdpoint");
		String thirdPoint = thirdpoint.getText();
		logger.info("Taking out the text from the thirdPoint ");
		Assert.assertEquals(firstPoint, "• First bullet point", "Body text Matched");
		logger.info("Assertion applied to verify the body text for 1st line ");
		Assert.assertEquals(secondPoint, "• Second bullet point", "Body text Matched");
		logger.info("Assertion applied to verify the body text for 2nd line ");
		Assert.assertEquals(thirdPoint, "• Third bullet point", "Body text Matched");
		logger.info("Assertion applied to verify the body text for 3rd line ");

	}

	@AfterTest
	public void tearDownBrowser() {
		driver.quit();
		logger.info("Close the Browser & Tabs");
	}

}
