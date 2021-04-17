package common;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;

import pages.AndroidScreenObject;
import pages.IOSScreenObject;

public class BaseFunctions extends BaseClass {

	AndroidScreenObject adb = new AndroidScreenObject();
	IOSScreenObject idb = new IOSScreenObject();

	Logger log = Logger.getLogger(BaseFunctions.class);

	String legalMessage = "We take your privacy seriously. Our privacy policy is available for your review";
	String onboardingmessage = "Continue to the app and discover the new features";
	String garminMergetitle = "Merge with Garmin Connect";
	String privacyText = "Garmin takes your privacy seriously. The Garmin privacy policy is available for your review. Privacy Policy";
	String introductoryParagraph1 = "Tacx is now part of the Garmin family. This means we can offer you one platform to access and view all your indoor and outdoor cycling activities.";
	String introductoryParagraph2 = "Sign in with your Garmin account or create one on the fly and your activities will be automatically synced with Garmin Connect.";

	public void titleCheck(String title) throws IOException {
		if (elementAvailable(adb.androidtitle, "xpath") > 0) {
			displayStatus(adb.androidtitle, "xpath");
		}
		log.info("app is Launched Successfully: title is displayed");
		String titleApp = elementText(adb.androidtitle, "xpath");

		log.info("Title of APP is displayed as :" + titleApp);
		Assert.assertEquals(titleApp, title);

		log.info("Title displayed as expected");

	}

	public void dotsOptionValidation() throws IOException {
		if (elementAvailable(adb.moreOption, "xpath") > 0) {
			displayStatus(adb.moreOption, "xpath");
		}

		log.info("3 Dot Option is displayed");
		selectElement(adb.moreOption, "xpath");
		log.info("User is able to click the 3 dots Option");
		displayStatus(adb.settingICON, "id");
		log.info("User is displayed setting Icon");
		selectElement(adb.settingICON, "id");
		log.info("Setting button is clickable : No action in app further");
	}

	public void bodyValidation(String bodyMessageText) throws IOException {
		if (elementAvailable(adb.bodyText, "xpath") > 0) {
			displayStatus(adb.bodyText, "xpath");
		}
		log.info("app is Launched Successfully: Body Content is displayed");
		String bodyApp = elementText(adb.bodyText, "xpath");
		log.info("Text in APP is displayed as :" + bodyApp);
		Assert.assertEquals(bodyApp, bodyMessageText);
		log.info("Body Text is displayed as expected");
	}

	public void mailValidationCheck(String mailtext) throws IOException {
		if (elementAvailable(adb.mailIcon, "id") > 0) {
			displayStatus(adb.mailIcon, "id");
		}
		log.info("Mail ICON image is displayed in app");
		selectElement(adb.mailIcon, "id");
		log.info("User is able to click the Mail Image");
		displayStatus(adb.mailText, "id");
		String MailMessage = elementText(adb.mailText, "id");
		log.info("Message displayed on selecting Mail Icon:" + MailMessage);
		Assert.assertEquals(MailMessage, mailtext);
		log.info("Mail detailed message  displayed as expected");

	}

	public void firstTextValidation(String firsttext) throws IOException {
		Assert.assertTrue(displayStatus(idb.firstLabel, "id"));
		log.info("App is launched Successfully");
		String text = elementAttributeText(idb.firstLabel, "id", "value");
		log.info("Text displayed in app on launching:" + text);
		Assert.assertEquals(text, firsttext);

	}

	public void buttonExistanceValidation(String buttonT) throws IOException {
		Assert.assertTrue(displayStatus(idb.Button, "id"));
		log.info("Button exist in app");
		String Btext = elementAttributeText(idb.Button, "id", "name");
		log.info("Button UI Text in app :" + Btext);
		Assert.assertEquals(Btext, buttonT);

	}

	public void buttonfunction() throws IOException {

		Assert.assertTrue(displayStatus(idb.Button, "id"));
		String text = elementAttributeText(idb.firstLabel, "id", "value");
		log.info("Current Label text: " + text);
		for (int i = 0; i < 5; i++) {
			int n = i + 1;
			selectElement(idb.Button, "id");
			String textNew = elementAttributeText(idb.firstLabel, "id", "value");
			log.info("Label Text after" + n + " Select of Button" + textNew);
			Assert.assertNotEquals(textNew, text);
			log.info("Label text differ on selecting Button : PASSED");
			text = textNew;
			n = 0;

		}

		log.info("User is displayed with different Label value on each button press");

	}

	public void currencyfunction() throws IOException {
		Assert.assertTrue(displayStatus(idb.Button, "id"));
		log.info("Button exist in app");
		selectElement(idb.Button, "id");
		String Btext = elementAttributeText(idb.firstLabel, "id", "value");
		log.info("Currency value displayed : " + Btext);
		if (Btext.contains("€")) {
			log.info("Its euro currency displayed as Symbol :" + "€");
		} else {
			log.info("Other currency Symbol");
		}
	}

}
