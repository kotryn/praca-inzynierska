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
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
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

	/*@Test
	public void beginNewJob() {
	goToStartPage();

		System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
		WebDriver driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("http://localhost:8080");

		driver.findElement(By.xpath("//button[contains(text(),'Begin a new job')]")).click();

		By click = By.xpath("//button[contains(text(),'Connect')]");

		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(click));

		//driver.findElement(By.xpath("//button[contains(text(),'Connect')]")).click();
		element.click();

		By input = By.name("startDate");
		WebElement element2 = wait.until(ExpectedConditions.presenceOfElementLocated(input));

		element2.sendKeys("02","08","2010");
		driver.findElement(By.name("endDate")).sendKeys("02","08","2018");
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
	}*/

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

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	private static void goToStartPage() {
		if(!driver.findElements(By.xpath("//button[@class='btn dark-blue home']")).isEmpty()) {
			driver.findElement(By.xpath("//button[@class='btn dark-blue home']")).click();
		}
	}

	private WebElement waitUntilPageIsLoadByElement(By element){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}

	private WebElement waitUntilPageIsLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='container-fluid']")));
	}
}
