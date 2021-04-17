package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CommonUtils {

	private static String path = System.getProperty("user.dir");
	private static Properties prop = new Properties();
	private static String PropertyFileName = "abn.properties";
	public static int EXPLICIT_WAIT_TIME;
	public static int IMPLICIT_WAIT_TIME;
	public static int DEFAULT_WAIT_TIME;
	public static String APPLICATION_NAME_ANDROID;
	public static String APPLICATION_NAME_IOS;
	public static String APPIUM_PORT;
	public static int APPIUM_SERVER_PORT;
	public static String PLATFORM_NAME;

	private static DesiredCapabilities dc = new DesiredCapabilities();
	private static DesiredCapabilities cap = new DesiredCapabilities();
	private static URL serverUrl;
	public static AppiumDriver<MobileElement> driver;
	public static String DEVICETYPE;
	public static String iphoneSimVersion;
	public static String iphoneSimDevice;
	public static String AndroidVersion;
	public static String AndroidRealDevice;
	public static String AndroidRealUdid;
	public static String iOSBundleID;
	public static String Path;
	public static String JavaHomePath;
	public static String AndroidHomepath;
	public static String IOSUDID;

	public static void loadConfigProp(String propertyFileName) throws IOException {

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/Property/" + PropertyFileName);
		prop.load(fis);

		EXPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("explicit.wait"));
		IMPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("implicit.wait"));
		DEFAULT_WAIT_TIME = Integer.parseInt(prop.getProperty("default.wait"));
		APPLICATION_NAME_ANDROID = path + prop.getProperty("application.path.android");
		APPLICATION_NAME_IOS = path + prop.getProperty("application.path.ios");
		APPIUM_PORT = prop.getProperty("appium.server.port");
		PLATFORM_NAME = prop.getProperty("platform.name");
		DEVICETYPE = prop.getProperty("device.type.test");
		AndroidVersion = prop.getProperty("android.version");
		AndroidRealDevice = prop.getProperty("android.deviceName");
		AndroidRealUdid = prop.getProperty("android.RealUDID");
		iOSBundleID = prop.getProperty("abn.iOS.bundleID");
		APPIUM_SERVER_PORT = Integer.parseInt(prop.getProperty("appium.server.port"));
		iphoneSimDevice = prop.getProperty("ios.sim.device");
		iphoneSimVersion = prop.getProperty("ios.sim.version");

		Path = prop.getProperty("system.path");
		JavaHomePath = prop.getProperty("system.javahome.path");
		AndroidHomepath = prop.getProperty("system.androidhome.path");
		IOSUDID = prop.getProperty("IOS.Real.UDID");
	}

	public static void capability() throws IOException {
		loadConfigProp(PropertyFileName);
		dc = new DesiredCapabilities();
		if (DEVICETYPE.equalsIgnoreCase("iPhone")) {
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, iphoneSimDevice);
			dc.setCapability(MobileCapabilityType.APP, APPLICATION_NAME_IOS);
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, iphoneSimDevice);
			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			dc.setCapability("xcodeOrgId", "P6Z2T27997");
			dc.setCapability("xcodeSigningId", "iPhone Developer");
			dc.setCapability("udid", IOSUDID);

		}

		else if (DEVICETYPE.equalsIgnoreCase("iPhoneSimulator")) {
			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			dc.setCapability("platformName", "iOS");
			dc.setCapability("deviceName", iphoneSimDevice);
			dc.setCapability("platformVersion", iphoneSimVersion);
			dc.setCapability("newCommandTimeout", 1000);
			dc.setCapability("bundleId", iOSBundleID);

		}

		else if (DEVICETYPE.equalsIgnoreCase("AndroidEmulator")) {
			dc.setCapability(CapabilityType.PLATFORM_NAME, "Android");
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, AndroidVersion);
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			dc.setCapability("uiautomator2ServerInstallTimeout", 100000);
			dc.setCapability(MobileCapabilityType.APP, APPLICATION_NAME_ANDROID);

		}

		else {
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, AndroidVersion);
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, AndroidRealDevice);
			dc.setCapability(MobileCapabilityType.UDID, AndroidRealUdid);
			dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
			dc.setCapability(MobileCapabilityType.EVENT_TIMINGS, "true");
			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			dc.setCapability("uiautomator2ServerInstallTimeout", 50000);
			dc.setCapability(MobileCapabilityType.APP, APPLICATION_NAME_ANDROID);

		}

		cap.setCapability("noReset", "false");
	}

	public static AppiumDriver<MobileElement> getDriver() throws IOException {
		serverUrl = new URL("http://localhost:" + APPIUM_PORT + "/wd/hub");

		if (PLATFORM_NAME.equalsIgnoreCase("Android")) {
			driver = new AndroidDriver<MobileElement>(serverUrl, dc);
			driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);

		} else {
			driver = new IOSDriver<MobileElement>(serverUrl, dc);
		}
		return driver;
	}

	public static void StopDriver() {
		driver.quit();
	}
}
