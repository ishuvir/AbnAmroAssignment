/*
 * Author: Ishuvir Singh
 * Email id : ishuvirs@gmail.com
 * Script scenario: IOS Automation
 * 
 */

package IOSTest;

import java.io.IOException;

import org.testng.annotations.Test;

import common.BaseClass;
import common.BaseFunctions;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pages.IOSScreenObject;

public class iOSTestValidations extends BaseClass {
	IOSScreenObject adb = new IOSScreenObject();
	BaseFunctions bfun = new BaseFunctions();

	String firsttext = "Hello";
	String buttonText = "Button";

	@Step("User will validate the first title of app")
	@Description("Test case to validate the first text displayed in app")
	@Test(enabled = true, priority = 0)
	public void mainBodyValidation() throws IOException {
		bfun.firstTextValidation(firsttext);

	}

	@Step("User will validate button in app")
	@Description("Test case to validate the UI existance of Button in app")
	@Test(enabled = true, priority = 1)
	public void ButtonUIValidation() throws IOException {
		bfun.buttonExistanceValidation(buttonText);

	}

	@Step("User will validate button functionality  in app")
	@Description("Test case to validate the functionality of button in app")
	@Test(enabled = true, priority = 2)
	public void ButtonFunctionalValidation() throws IOException {
		bfun.buttonfunction();

	}

	@Step("User will validate currency in app")
	@Description("Test case to validate the type of currency in app")
	@Test(enabled = true, priority = 3)
	public void labelCurrencyValidation() throws IOException {
		bfun.currencyfunction();

	}
}
