package com.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class TestRunner {
    private AppiumDriver<MobileElement> driver;

    @Test
    public void test(){
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            //we use android phone
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            //version of android
            desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
            //name of the device, if it is real device we need to pass UUID parameter
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");

            //either you specify app --> //path/to/app.apk
            //of if app is already installed, you need to specify appActivity and appPackage
            //this info you can find in the internet, at work - ask to developers

            // Set your application's package name.
            desiredCapabilities.setCapability("appPackage", "com.android.calculator2");

            // Set your application's MainActivity i.e. the LAUNCHER activity name.
            desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
            /*
            "http://0.0.0.0:4723/wd/hub" --> address of the appium server. If you have appium server on the same computer
            just use local host
            4723 --> default port number
            we need provide 2 parameters: URL of appium servers and desired capabilities
             */
            driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);


//            desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"\\etsy.apk");
//            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);
//            driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
//            Thread.sleep(3000);
//            WebElement getStarted = driver.findElement(By.xpath("//*[@text='Get Started']"));
//            getStarted.click();

            Thread.sleep(3000);

            //close the app at the end
            driver.closeApp();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}