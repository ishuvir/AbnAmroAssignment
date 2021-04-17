/*
 * Author: Ishuvir Singh
 * Email id : ishuvirs@gmail.com
 * Script scenario: Android Automation
 * 
 */

package AndroidTest;

import java.io.IOException;

import org.testng.annotations.Test;

import common.BaseClass;
import common.BaseFunctions;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pages.AndroidScreenObject;

public class AndroidTestValidations extends BaseClass {
	AndroidScreenObject adb = new AndroidScreenObject();
	BaseFunctions bfun = new BaseFunctions();

	String titleMessage = "ReferenceAndroid";
	String bodyMessage = "Hello World!";
	String mailMessage = "Replace with your own action";

	@Step("User will validate the title of app")
	@Description("Test case to validate the title of app")
	@Test(enabled = true, priority = 0)
	public void titleValidation() throws IOException {
		bfun.titleCheck(titleMessage);

	}

	@Step("User will validate Other Options in app")
	@Description("Test case to validate  Other 3 dots Options in app ")
	@Test(enabled = true, priority = 1)
	public void optionValidations() throws IOException {
		bfun.dotsOptionValidation();

	}

	@Step("User will validate BodyText in app")
	@Description("Test case to validate content in app ")
	@Test(enabled = true, priority = 2)
	public void contentalidations() throws IOException {
		bfun.bodyValidation(bodyMessage);

	}

	@Step("User will validate MailOption in app")
	@Description("Test case to validate MailOption content in app ")
	@Test(enabled = true, priority = 3)
	public void mailValidations() throws IOException {
		bfun.mailValidationCheck(mailMessage);

	}

}
