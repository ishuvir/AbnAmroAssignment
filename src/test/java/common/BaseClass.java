package common;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.qameta.allure.Attachment;

public class BaseClass extends CommonUtils {

	public WebDriverWait wait;
	AppiumDriverLocalService server = null;

	static String scrShotDir = "screenshots";
	File scrFile;
	static File scrShotDirPath = new java.io.File("./" + scrShotDir + "//");
	String destFile;

	/**
	 * This method is used to start the appium CLI server
	 * 
	 * @throws Exception
	 * @throws Exception
	 */

	@BeforeSuite
	public void beforeSuite() throws Exception, Exception {
		CommonUtils.capability();

		ThreadContext.put("ROUTINGKEY", "ServerLogs");
		server = getAppiumService();
		if (!checkIfAppiumServerIsRunnning(APPIUM_SERVER_PORT)) {
			server.start();
			server.clearOutPutStreams();
			System.out.println("Apium Server Started");

		} else {
			System.out.println("Appium server is already running");
		}
		CommonUtils.getDriver();

	}

	public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
		boolean isAppiumServerRunning = false;
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (IOException e) {
			System.out.println("1");
			isAppiumServerRunning = true;
		} finally {
			socket = null;
		}
		return isAppiumServerRunning;
	}

	/**
	 * This method is used to stop the running Appium CLI server
	 */

	@AfterSuite
	public void stopAppiumServer() {

		server.stop();
		System.out.println("Appium server stopped");
	}

	public AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();
	}

	public AppiumDriverLocalService getAppiumService() {
		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH", Path + System.getenv("PATH"));
		environment.put("ANDROID_HOME", AndroidHomepath);
		environment.put("JAVA_HOME", JavaHomePath);

		return AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
						.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")).usingPort(4723)
						.withArgument(GeneralServerFlag.SESSION_OVERRIDE).withEnvironment(environment)
						.withLogFile(new File("ServerLogs/server.log")));
	}

	/**
	 * @beforeTest method instantiate the driver & Capabilities of mobile devices
	 *             commented this method as using appium CLI tool for running the
	 *             tests
	 * @throws IOException
	 */
//
//	@BeforeTest
//	public void setup() throws IOException {
//
//		CommonUtils.capability();
//		CommonUtils.getDriver();
//
//	}

	/**
	 * Method used to put wait for specific element with specified locator
	 * 
	 * @param duration
	 * @param locator
	 * @param locatorType
	 * @throws IOException
	 */

	public void waitForElement(long duration, String locator, String locatorType) throws IOException {

		wait = new WebDriverWait(driver, duration);
		if (locatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));

		} else if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		}

	}

	/**
	 * Method used to search / locate the element using locator
	 * 
	 * @param locator
	 * @param locatorType
	 * @return
	 * @throws IOException
	 */
	public MobileElement searchElement(String locator, String locatorType) throws IOException {
		MobileElement ele = null;

		if (locatorType.equalsIgnoreCase("id")) {
			ele = driver.findElement(By.id(locator));

		} else if (locatorType.equalsIgnoreCase("xpath")) {
			ele = driver.findElement(By.xpath(locator));
		} else {
			ele = driver.findElementByAccessibilityId(locator);
		}

		return ele;

	}

	/**
	 * Method used to capture the Screenshot on script failure Screenshot added in
	 * Screenshot folder
	 * 
	 * @param driver
	 * @throws IOException
	 */
	@Attachment
	public void getscreenshot(AppiumDriver<MobileElement> driver) throws IOException {
		SimpleDateFormat dateF = new SimpleDateFormat("dd mm yyyy hh mm ss");
		Date date = new Date();
		String fileName = dateF.format(date);
		File failScreen = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(failScreen, new File(System.getProperty("user.dir") + "//ScreenShot//" + fileName + ".png"));
		System.out.println("ScreenShot Captured");
	}

	/*
	 * This method is used to capture screenshot for allure reporting Allure support
	 * Output as Byte Method can be Commented during local run
	 */

//
//	@Attachment
//	public byte[] getscreenshot(AppiumDriver<MobileElement> driver) {
//		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//	}

	public String takeScreenShot() {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		new File(scrShotDir).mkdirs(); // Create folder under project with name
		// "screenshots" if doesn't exist
		destFile = dateFormat.format(new Date()) + ".png"; // Set file name
		// using current
		// date time.
		try {
			FileUtils.copyFile(scrFile, new File(scrShotDir + "/" + destFile)); // Copy
			// paste
			// file
			// at
			// destination
			// folder
			// location
		} catch (IOException e) {
			System.out.println("Image not transfered to screenshot folder");
			e.printStackTrace();
		}
		return destFile;
	}

	/**
	 * This method is used to verify that element is displayed or not
	 *
	 * @param locator
	 * @param locatorType
	 * @return
	 * @throws IOException
	 */
	public boolean displayStatus(String locator, String locatorType) throws IOException {

		try {
			waitForElement(20, locator, locatorType);
			searchElement(locator, locatorType).isDisplayed();
			return true;
		}

		catch (Exception e) {
			getscreenshot(driver);
			System.out.println("Screenshot is captured on element not displayed:Element is not displayed");
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			Assert.fail();
		}
		return false;

	}

	/**
	 * This method is used to verify the displayed status with duration to wait for
	 * element
	 * 
	 * @param locator
	 * @param locatorType
	 * @param duration
	 * @return
	 * @throws IOException
	 */

	public boolean displayStatus(String locator, String locatorType, long duration) throws IOException {

		try {
			waitForElement(duration, locator, locatorType);
			searchElement(locator, locatorType).isDisplayed();
			return true;
		}

		catch (Exception e) {
			getscreenshot(driver);
			System.out.println("Screenshot is captured on element not displayed:Element is not displayed");
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			Assert.fail();
		}
		return false;

	}

	/*
	 * This method is used to verify the Elements available or existence Can be used
	 * to check for more then one element
	 * 
	 */

	public int elementAvailable(String locator, String locatorType) throws IOException {
		int eleSize = 0;

		if (locatorType.equalsIgnoreCase("id")) {

			eleSize = driver.findElementsById(locator).size();

		} else {
			eleSize = driver.findElementsByXPath(locator).size();
		}

		return eleSize;

	}

	/**
	 * This method is used to select the particular element
	 *
	 * 
	 * @param locator
	 * @param locatorType
	 * @throws IOException
	 */
	public void selectElement(String locator, String locatorType) throws IOException {

		try {
			waitForElement(10, locator, locatorType);
			searchElement(locator, locatorType).click();
		} catch (Exception e) {
			getscreenshot(driver);
			System.out.println("Screenshot is captured on failure :Unable to select element ");
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			Assert.fail();
		}
	}

	/**
	 * This method is used to capture the text of element
	 * 
	 * @param locator
	 * @param locatorType
	 * @return
	 * @throws IOException
	 */
	public String elementText(String locator, String locatorType) throws IOException {
		String locatorText = null;
		try {
			waitForElement(20, locator, locatorType);
			locatorText = searchElement(locator, locatorType).getText();
			// System.out.println("Field text is : "+locatorText);
			return locatorText;
		} catch (Exception e) {
			getscreenshot(driver);
			System.out.println("Screenshot is captured on failure :Unable to get elementText ");
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			Assert.fail();
		}
		return locatorText;
	}

	/*
	 * This method is used to capture the text of particular attribute of element
	 * 
	 * 
	 */
	public String elementAttributeText(String locator, String locatorType, String attributeValue) throws IOException {
		String locatorAText = null;
		try {
			waitForElement(20, locator, locatorType);
			locatorAText = searchElement(locator, locatorType).getAttribute(attributeValue);
			return locatorAText;
		} catch (Exception e) {
			getscreenshot(driver);
			System.out.println("Screenshot is captured on failure :Unable to get elementText ");
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			Assert.fail();
		}
		return locatorAText;
	}

	/**
	 * This element is used to make the script wait for some duration
	 * 
	 * @param duration
	 */
	public void scriptwait(long duration) {
		driver.manage().timeouts().implicitlyWait(duration, TimeUnit.SECONDS);

	}

	/**
	 * After test stops the driver and end the script exexution
	 * 
	 * @throws IOException
	 */
	@AfterTest
	public void TearDown() throws IOException {
		CommonUtils.StopDriver();
	}

}
