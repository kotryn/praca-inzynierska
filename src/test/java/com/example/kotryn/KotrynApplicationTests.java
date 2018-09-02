package com.example.kotryn;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class KotrynApplicationTests {

	private static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("http://localhost:8080");
	}

	@Test
	public void createAndDeleteJob() {
		goToStartPage();
		driver.findElement(By.xpath("//button[contains(text(),'Begin a new job')]")).click();
		WebElement element = waitUntilPageIsLoadByElement(By.xpath("//h3[contains(text(),'New job id:')]"));
		String text = element.getAttribute("innerHTML").substring(12);
		driver.findElement(By.xpath("//button[@class='btn dark-blue home']")).click();
		waitUntilPageIsLoadByElement(By.xpath("//button[contains(text(),'Delete job')]")).click();
		waitUntilPageIsLoadByElement(By.name("id")).sendKeys(text);
		driver.findElement(By.xpath("//button[contains(text(),'Delete')]")).click();
		waitUntilPageIsLoad();
		driver.findElement(By.xpath("//h2[contains(text(),'Robust portfolio creator')]"));
	}

	@Test
	public void deleteNotExistJob() {
		goToStartPage();
		driver.findElement(By.xpath("//button[contains(text(),'Delete job')]")).click();
		waitUntilPageIsLoadByElement(By.name("id")).sendKeys("-1");
		driver.findElement(By.xpath("//button[contains(text(),'Delete')]")).click();
		waitUntilPageIsLoad();
		driver.findElement(By.xpath("//div[contains(text(),'not exist')]"));
	}

	@Test
	public void connectToJob() {
		goToStartPage();
		driver.findElement(By.xpath("//button[contains(text(),'Begin a new job')]")).click();
		WebElement element = waitUntilPageIsLoadByElement(By.xpath("//h3[contains(text(),'New job id:')]"));
		String text = element.getAttribute("innerHTML").substring(12);
		driver.findElement(By.xpath("//button[@class='btn dark-blue home']")).click();
		waitUntilPageIsLoadByElement(By.xpath("//button[contains(text(),'Connect to a job')]")).click();
		waitUntilPageIsLoadByElement(By.name("id")).sendKeys(text);
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
		waitUntilPageIsLoad();
		driver.findElement(By.xpath("//h3[contains(text(),'Supply the period of analysis')]"));
	}

	@Test
	public void connectToNotExistJob() {
		goToStartPage();
		waitUntilPageIsLoadByElement(By.xpath("//button[contains(text(),'Connect to a job')]")).click();
		waitUntilPageIsLoadByElement(By.name("id")).sendKeys("-1");
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
		waitUntilPageIsLoad();
		driver.findElement(By.xpath("//div[contains(text(),'not exist')]"));
	}

	@Test
	public void checkAllStates() {
		goToStartPage();
		driver.findElement(By.xpath("//button[contains(text(),'Begin a new job')]")).click();
		waitUntilPageIsLoadByElement(By.xpath("//h3[contains(text(),'New job id:')]"));
		driver.findElement(By.xpath("//button[contains(text(),'Connect')]")).click();
		WebElement element = waitUntilPageIsLoadByElement(By.name("startDate"));
		element.sendKeys("02","08","2010");
		driver.findElement(By.name("endDate")).sendKeys("02","08","2018");
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		waitUntilPageIsLoadByElement(By.xpath("//button[contains(text(),'Next')]")).click();

		waitUntilPageIsLoadByElement(By.xpath("//h3[contains(text(),'Searching for stocks in progress')]"));
		refreshPage(By.xpath("//h3[contains(text(),'Searching for stocks in progress')]"));

		driver.findElement(By.xpath("//div[contains(text(),'Services')]")).click();
		driver.findElement(By.xpath("//div[contains(text(),'Discount')]")).click();

		driver.findElement(By.name("[TGT] Target Corp.")).click();
		driver.findElement(By.name("[WMT] Wal-Mart Stores Inc.")).click();
		driver.findElement(By.name("[COST] Costco Wholesale Corporation")).click();
		driver.findElement(By.name("[BIG] Big Lots Inc.")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();

		waitUntilPageIsLoadByElement(By.xpath("//div[contains(text(),'In-sample and out-of-sample periods')]"));
		driver.findElement(By.name("startInSampleDate")).sendKeys("02","08","2000");
		driver.findElement(By.name("endInSampleDate")).sendKeys("02","08","2018");
		driver.findElement(By.name("startOutOfSampleDate")).sendKeys("02","08","2005");
		driver.findElement(By.name("endOutOfSampleDate")).sendKeys("02","08","2008");
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();

		waitUntilPageIsLoadByElement(By.xpath("//h3[contains(text(),'Calculating Sample Count in progress')]"));
		refreshPage(By.xpath("//h3[contains(text(),'Calculating Sample Count in progress')]"));

		driver.findElement(By.xpath("//div[contains(text(),'Calculating sample count completed successfully')]"));
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
		waitUntilPageIsLoadByElement(By.xpath("//div[contains(text(),'Estimating worst case distributions setup')]"));
		driver.findElement(By.name("windowSize")).sendKeys("12");
		driver.findElement(By.name("growthRate")).sendKeys("5");
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();

		waitUntilPageIsLoadByElement(By.xpath("//h3[contains(text(),'Estimating worst case distributions in progress')]"));
		refreshPage(By.xpath("//h3[contains(text(),'Estimating worst case distributions in progress')]"));

		driver.findElement(By.xpath("//h2[contains(text(),'Estimating worst case distributions completed successfully')]"));
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();

		waitUntilPageIsLoadByElement(By.xpath("//h2[contains(text(),'Estimating growth stocks setup')]"));
		driver.findElement(By.name("maxNumberSector")).sendKeys("120");
		driver.findElement(By.name("maxNumberIndustry")).sendKeys("50");
		driver.findElement(By.name("maxCoefficient")).sendKeys("12");
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();

		waitUntilPageIsLoadByElement(By.xpath("//h3[contains(text(),'Estimating growth stocks in progress')]"));
		refreshPage(By.xpath("//h3[contains(text(),'Estimating growth stocks in progress')]"));
		waitUntilPageIsLoadByElement(By.xpath("//div[contains(text(),'Estimating growth stocks completed successful')]"));
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();

		waitUntilPageIsLoadByElement(By.xpath("//div[contains(text(),'Estimating worst case copula setup')]"));
		driver.findElement(By.name("copulaWindowSize")).sendKeys("10");
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();

		waitUntilPageIsLoadByElement(By.xpath("//h3[contains(text(),'Estimating worst case copula in progress')]"));
		refreshPage(By.xpath("//h3[contains(text(),'Estimating worst case copula in progress')]"));
		waitUntilPageIsLoadByElement(By.xpath("//div[contains(text(),'Estimating worst case copula completed successful')]"));
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();

		waitUntilPageIsLoadByElement(By.xpath("//div[contains(text(),'Portfolio optimisation setup')]"));
		driver.findElement(By.name("numberOfSamples")).sendKeys("10");
		driver.findElement(By.name("yearRateOfReturn")).sendKeys("5");
		driver.findElement(By.name("toleranceLevel")).sendKeys("2");
		driver.findElement(By.name("maxShare")).sendKeys("50");
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();

		waitUntilPageIsLoadByElement(By.xpath("//h3[contains(text(),'Portfolio optimization in progress')]"));
		refreshPage(By.xpath("//h3[contains(text(),'Portfolio optimization in progress')]"));
		waitUntilPageIsLoadByElement(By.xpath("//div[contains(text(),'Portfolio optimization completed successful')]"));
		driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();

		waitUntilPageIsLoadByElement(By.xpath("//h3[contains(text(),'Producing out-of sample portfolio graph in progress')]"));
		refreshPage(By.xpath("//h3[contains(text(),'Producing out-of sample portfolio graph in progress')]"));
		waitUntilPageIsLoadByElement(By.xpath("//div[contains(text(),'Producing out-of sample portfolio graph completed successful')]"));
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	private static void goToStartPage() {
		if(!driver.findElements(By.xpath("//button[@class='btn dark-blue home']")).isEmpty()) {
			driver.findElement(By.xpath("//button[@class='btn dark-blue home']")).click();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='container-fluid']")));
		}
	}

	private WebElement waitUntilPageIsLoadByElement(By element){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}

	private void waitUntilPageIsLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='container-fluid']")));
	}

	private void refreshPage(By element){
		while(!driver.findElements(element).isEmpty()){
			waitUntilPageIsLoadByElement(By.xpath("//button[contains(text(),'Refresh')]")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}
	}
}
