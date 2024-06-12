package web_testing_app;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import web_testing_app.WebTesting;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.*;

import java.util.concurrent.TimeUnit;
import java.lang.Thread;

/* READ THIS FIRST
 *
 * General instructions are given above each of the 4 test classes. Make sure to use the correct "By"
 * function for selection. You will either submit the line where you added your code fragment or the entire section
 * of code that you write for later problems on this assignment marked by CODE FRAGMENT or CODE.
 *
 */

public class Assignment {
	private static long shortTimeout = 10;
	private static long longTimeout = 90;

	@BeforeClass
	public static void setUp() {
		WebTesting.initSystemProperties();
	}

	@AfterClass
	public static void tearDownAll() {
		if (WebTesting.getDriver() != null) {
			WebTesting.quitDriver();
		}
	}

	@After
	public void tearDown() {
		if (WebTesting.getDriver() != null) {
			WebTesting.quitDriver();
		}
	}



	@Test
	public void testGoogleCalculatorExplicitly() {
		/* Use one */
//		FirefoxDriver driver = (FirefoxDriver) WebTesting.launchDriver("https://www.google.com", "firefox");
		ChromeDriver driver = (ChromeDriver) WebTesting.launchDriver("https://www.google.com", "chrome");

		if (driver == null) {
			fail("Driver not created.");
		}

		/* You can change these timeout values to what you see fit for your machine */
		WebDriverWait shortWait = new WebDriverWait(driver, shortTimeout);

		// #14: TODO (ensure that the search bar is visible before accessing it)
		// HINT: the search bar has attribute name="q"
		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		} catch (TimeoutException ex) {
			fail("Search bar not found.");
		}

		// #15: TODO (enter "calculator" into the search bar then press the "Return" key)
		driver.findElement(By.name("q")).sendKeys("calculator" + Keys.RETURN);

		// #16: TODO (ensure that the Calculator is visible)
		// HINT: the Calculator is tag <div> with class="tyYmIf"
		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tyYmIf")));
		} catch (TimeoutException ex) {
			fail("Calculator not found.");
		}

		// #17: TODO (enter "1" and check whether the value was input into the text field)
		// HINT: the "1" button is tag <div> with attribute jsname="N10B9"
		driver.findElement(By.xpath("//div[@jsname='N10B9']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "1"));
		} catch (TimeoutException ex) {
			fail("\"1\" was not input.");
		}

		// #18: TODO (enter "+" and check whether the value was input into the text field)
		// HINT: the "+" button is tag <div> with attribute jsname="XSr6wc"
		driver.findElement(By.xpath("//div[@jsname='XSr6wc']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "1 +"));
		} catch (TimeoutException ex) {
			fail("\"+\" was not input.");
		}

		// #19: TODO (enter "-" and check whether the value was input correctly into the text field)
		// HINT: the "-" button is tag <div> with attribute jsname="pPHzQc"
		driver.findElement(By.xpath("//div[@jsname='pPHzQc']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "1 -"));
		} catch (TimeoutException ex) {
			fail("\"-\" was not input.");
		}

		// #20: TODO (enter "2" and check whether the value was input into the text field)
		// HINT: the "2" button is tag <div> with attribute jsname="lVjWed"
		driver.findElement(By.xpath("//div[@jsname='lVjWed']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "1 - 2"));
		} catch (TimeoutException ex) {
			fail("\"2\" was not input.");
		}

		// #21: TODO (press "=" and check whether the value was solved correctly)
		// HINT: the "=" button is tag <div> with attribute jsname="Pt8tGc"
		driver.findElement(By.xpath("//div[@jsname='Pt8tGc']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "-1"));
		} catch (TimeoutException ex) {
			fail("\"-1\" was not the solution.");
		}

		// #22: TODO (press "AC" button and check whether the value was cleared)
		// HINT: the "AC" button is tag <div> with attribute jsname="SLn8gc"
		driver.findElement(By.xpath("//div[@jsname='SLn8gc']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
	}
	@Test
	public void testGoogleCalculatorMultiplication() {
		ChromeDriver driver = (ChromeDriver) WebTesting.launchDriver("https://www.google.com", "chrome");

		if (driver == null) {
			fail("Driver not created.");
		}

		WebDriverWait shortWait = new WebDriverWait(driver, shortTimeout);

		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		} catch (TimeoutException ex) {
			fail("Search bar not found.");
		}

		driver.findElement(By.name("q")).sendKeys("calculator" + Keys.RETURN);

		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tyYmIf")));
		} catch (TimeoutException ex) {
			fail("Calculator not found.");
		}

		driver.findElement(By.xpath("//div[@jsname='N10B9']")).click(); // "1"
		driver.findElement(By.xpath("//div[@jsname='YovRWb']")).click(); // "*"
		driver.findElement(By.xpath("//div[@jsname='lVjWed']")).click() ; // "2"
		driver.findElement(By.xpath("//div[@jsname='Pt8tGc']")).click(); // "="

		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "2"));
		} catch (TimeoutException ex) {
			fail("\"2\" was not the solution.");
		}

		driver.findElement(By.xpath("//div[@jsname='SLn8gc']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
	}
	@Test
	public void testGoogleCalculatorDivision() {
		ChromeDriver driver = (ChromeDriver) WebTesting.launchDriver("https://www.google.com", "chrome");

		if (driver == null) {
			fail("Driver not created.");
		}

		WebDriverWait shortWait = new WebDriverWait(driver, shortTimeout);

		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		} catch (TimeoutException ex) {
			fail("Search bar not found.");
		}

		driver.findElement(By.name("q")).sendKeys("calculator" + Keys.RETURN);

		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tyYmIf")));
		} catch (TimeoutException ex) {
			fail("Calculator not found.");
		}

		driver.findElement(By.xpath("//div[@jsname='N10B9']")).click(); // "1"
		driver.findElement(By.xpath("//div[@jsname='lVjWed']")).click(); // "2"
		driver.findElement(By.xpath("//div[@jsname='WxTTNd']")).click(); // "/"
		driver.findElement(By.xpath("//div[@jsname='N10B9']")).click(); // "1"
		driver.findElement(By.xpath("//div[@jsname='Pt8tGc']")).click(); // "="

		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "12"));
		} catch (TimeoutException ex) {
			fail("\"12\" was not the solution.");
		}

		driver.findElement(By.xpath("//div[@jsname='SLn8gc']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
	}
	@Test
	public void testGoogleCaculatorSquareRoot(){
		ChromeDriver driver = (ChromeDriver) WebTesting.launchDriver("https://www.google.com", "chrome");
		if (driver == null) {
			fail("Driver not created.");
		}
		WebDriverWait shortWait = new WebDriverWait(driver, shortTimeout);
		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		} catch (TimeoutException ex) {
			fail("Search bar not found.");
		}
		driver.findElement(By.name("q")).sendKeys("calculator" + Keys.RETURN);

		try {
			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tyYmIf")));
		} catch (TimeoutException ex) {
			fail("Calculator not found.");
		}
		driver.findElement(By.xpath("//div[@jsname='oQcVc']")).click(); // SQRT()
		driver.findElement(By.xpath("//div[@jsname='XoxYJ']")).click();// 9
		driver.findElement(By.xpath("//div[@jsname='Pt8tGc']")).click(); // =
		
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "3"));
		} catch (TimeoutException ex) {
			fail("\"3\" was not the solution.");
		}

		driver.findElement(By.xpath("//div[@jsname='SLn8gc']")).click();
		try {
			shortWait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cwos"), "0"));
		} catch (TimeoutException ex) {
			fail("Output not cleared to \"0\".");
		}
	}

}
