# AbnAmroAssignment
Mobile Automation Assignment
Author: Ishuvir Singh
Email: ishuvirs@gmail.com
Assignment: IOS & Android both assignment are completed in one framework of appium 
Assignment solution : Doc file is attached with details explanation for framework, usage, tools,how to execute


Below are steps for Setting up the pre-requisite for Running appium scripts 


APPIUM SETUP for Windows & MAC OS
 
Setting Up Your Testing Environment
Appium is platform independent so executing Appium scripts is largely consistent across all the major platforms (Linux, Mac, and Windows). In this section, we will discuss how can you setup Appium and it’s dependencies on different platforms. Refer to the section relevant to your environment.
 
Installation on Windows
Software required:
1.	Java
2.	Android SDK (Android Studio)
3.	Node.js
4.	Appium Desktop Server
5.	Install the JDK software and set JAVA_HOME 
6.	Please install Maven  https://maven.apache.org/download.cgi
 
1) Install the JDK software
1.	Install the Java Development Kit Software from:
http://java.sun.com/javase/downloads/index.jsp
2. Select the appropriate JDK software and click Download.
1.	SetJAVA_HOME:
•	Right click My Computer and select Properties.
•	On the Advanced tab, select Environment Variables, and then edit JAVA_HOME to point to where the JDK software is located, like: C:\Program Files\Java\jdk1.6.0_02.
•	You can check it by typing $ java -version command at the command prompt


 
2) Install Android Studio & Android SDK
1.	Install Android Studio from:
https://developer.android.com/studio/index.html
2.	Download and Install Android Studio
3.	Open Android Studio and then download the needed Android SDK files from Tools > Android > SDK Manager
4.	Set ANDROID_HOME
•	Right click My Computer and select Properties.
•	On the Advanced tab, select Environment Variables, and then add ANDROID_HOME to point to where the Android SDK files is located, like: D : \Android\sdk\
▪ Verify it on the Command prompt using $ echo %ANDROID_HOME% command. Output must display the SDK path.

 
3) Installation of Node.js
1.	Install Node.js from:https://nodejs.org/en/download/
2.	You can verify installation by entering $npm -v command at the command prompt and it will display the version.

 
4) Installation of Appium desktop server. 
 
https://github.com/appium/appium-desktop/releases/tag/v1.20.2 
1.	Go to the Appium github project and Download the relevant Appium Desktop .exe file.
2.	Install it and Open Appium.exe file and start the server

 
3. A Terminal should appear saying ‘The server is running’

 
 
Installation on MAC
 
Software required:
1.	Java
2.	Android SDK (Android Studio)
3.	Node.js
4.	XCode
5.	Appium Desktop Server
 
1) Install the JDK software
1.	Install the Java Development Kit Software from:
http://java.sun.com/javase/downloads/index.jsp
1.	Select the appropriate JDK software and click Download.
2.	SetJAVA_HOME in bash profile
 
2)Install Android Studio & Android SDK
1.	Install Android Studio from:
https://developer.android.com/studio/index.html
2.	Download and Install Android Studio
3.	Open Android Studio and then download the needed Android SDK files from Tools > Android > SDK Manager
 
3)Set JAVA_HOME & ANDROID_HOME
1.	We need to store Environment variables in .bash_profile file so open Terminal and enter this command to open the bash_profile:
$ vi ~/.bash_profile
2. Now to you need to go into insert mode by pressing the `i` key from the keyboard, and write the following text at the end of the file.

 
3. Press ESC key followed by : wq which will save the .bash_profile file.
4. You can check that & are properly set by executing commands &
$ANDROID_HOME Respectively.

 
3 Installation of Node.js
1.	Install Node.js from:https://nodejs.org/en/download/
2.	You can verify installation by entering $npm -v command at the command prompt and it will display the version.

 
4 Installation of Appium desktop server
1.	Go to the Appium github project and Download the relevant Appium Desktop .exe file.
2.	Install it and Open Appium.exe file and start the server

 
3. A Terminal should appear saying 
‘The server is running’

 
4) XCode with Appium libraries setup
1.	Install XCode: https://developer.apple.com/download/
2.Install Xcode Command line tools:
▪ Execute below command on Terminal:
$ xcode-select --install
 
3.Install Brew(If it’s not installed already):
▪ Execute below command on Terminal:
$ /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/ins
tall/master/install)"
 
4.Install libimobiledevice:
▪ Execute below command on Terminal:
$ brew install libimobiledevice --HEAD
 
5. Install ios-deploy:
▪ Execute below command on Terminal:
$ npm install -g ios-deploy
 
6. Install carthage:
▪ Execute below command on Terminal:
$ brew install carthage
 
NOTE: Now you are set to run your iOS Appium Script on Simulator
 
7. WebDriverAgentRunner setup(Setting up iOS real devices tests with XCUITest)
This is the most crucial and important step of Appium setup for iOS.
If you don’t follow these steps correctly then you might not be able to run the Appium Automation scripts on your real iOS devices.
 
There are 2 ways to configure project in Appium:
1.	 Basic (automatic) configuration
2.	Basic (manual) configuration
 
1)Automatic configuration
The easiest way to get up-and-running with Appium's XCUITest support on iOS real devices is to use the automatic configuration strategy. There are two ways to do this:
1.	Use the xcodeOrgId and xcodeSigningId desired capabilities:
 
1{"xcodeOrgId": "<Team ID>",
2
3"xcodeSigningId": "iPhone Developer"
4
5 }
 
In Java, the code will look like:
 
1desiredCapabilities.setCapability("xcodeOrgId", <<Team ID>>); 
2desiredCapabilities.setCapability("xcodeSigningId", "iPhone Developer");
3
 
2) Create a .xcconfig file somewhere on your file system and add the following to it
 
1 DEVELOPMENT_TEAM = <Team ID> 
2 CODE_SIGN_IDENTITY = iPhone Developer
 
After this you need to set the desired capabilities and set the path to .xcconfig file:
 
1 desiredCapabilities.setCapability("xcodeConfigFile", "path/to/.xcconfig")
 
In either case, the Team ID is a unique 10-character string generated by Apple that is assigned to your team. You can find your Team ID using your developer account. Sign in to www.developer.apple.com/account , and click Membership in the sidebar. Your Team ID appears in the Membership Information section under the team name. You can also find your team ID listed under the "Organizational Unit" field in your iPhone Developer certificate in your keychain.
 
 
NOTE: These are mutually exclusive strategies; use either the xcodeConfigFile capability or the combination of xcodeOrgId and xcodeSigningId. For more details you can visit this link.
 
2) Manual configuration
There are many cases in which the basic automatic configuration is not enough. This usually has to do with code signing and the configuration of the project to be able to be run on the real device under test. Often this happens when the development account being used is a "Free" one, in which case it is not possible to create a wildcard provisioning profile, and will often not create one for the default application bundle.
Please follow these steps to Manually configure the WebDriverAgent XCode project.
•	Move to the: WebDriverAgent.xcodeproj(Make sure you have installed Appium Desktop application properly):
1$ cd /Applications/Appium.app/Contents/Resources/app/node_modules/ appium/node_modules/appium-xcuitest-driver/WebDriverAgent
 
•	Execute the Scripts/bootstrap.sh script using:
1$ sh Scripts/bootstrap.sh
 
•	Open the WebDriverAgent.xcodeproj project in Xcode.

 
•	Select WebDriverAgentRunner under TARGETS.
•	Now when you move to WebDriverAgentRunner, you would face an
error that Xcode failed to create provisioning profile:
•	
The easiest way to resolve that is 1) Select WebDriverAgentLib under TARGETS, 2) select Automatically manage signing, 3) select valid Team and most important 4) change the Bundle Identifier and put the Bundle Identifier of your existing valid XCode project the purpose here to put something that Xcode will accept.

•	Also ensure that you should have installed the valid Provisioning profile(Of course compatible with entered Certificate and Bundle Identifier). Now move to WebDriverAgentRunner again and 1) Select valid Provisioning Profile under Signing (Debug) and 2) Select valid Provisioning Profile under Signing (Release).

 
•	Now to be on safer side repeat above step same for UnitTests, IntegrationTests_1, IntegrationTests_2, IntegrationTests_3 and IntegrationApp
•	Connect valid iPhone device to your Mac machine(Please ensure device is included in selected provisioning profile).
•	Select WebDriverAgentRunner under TARGETS and click on Test button to execute build on your connected device.
•	
You can observe that when you click on Test/Run button the WebDriverAgent application will be installed to iOS device and it will open and give you the black screen for a moment and automatically closed. That means Success. Now you can able to Run Appium script on this device.(In fact it applies to all the valid devices registered under selected provisioning profile).
NOTE: Please install Appium-Doctor(Node Utility) using npm, It will diagnose and fix common Node, iOS and Android configuration issues before starting Appium.
 
1$ npm install -g appium-doctor //then
2$ appium-doctor
3//it will give checklist of which things are okay and
4which are not

 


