package com.example.kotryn;

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

	@Test
	public void contextLoads() {
		System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
		WebDriver driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("http://localhost:8080");

		//driver.findElement(By.name("Begin a new job")).click();
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
	}

}
