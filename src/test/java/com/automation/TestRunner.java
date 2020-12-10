package com.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestRunner {
    private AppiumDriver<MobileElement> driver;

    @Test
    public void calculatorTest(){
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

            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
            /*
            "http://localhost:4723/wd/hub" --> address of the appium server. If you have appium server on the same computer
            just use local host
            4723 --> default port number
            we need provide 2 parameters: URL of appium servers and desired capabilities
             */
            driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);

            MobileElement digit2 = driver.findElement(By.id("com.android.calculator2:id/digit_2"));
            MobileElement digit4 = driver.findElement(By.id("com.android.calculator2:id/digit_4"));
            MobileElement digit5 = driver.findElement(By.id("com.android.calculator2:id/digit_5"));

            //MobileBy child class of By
            MobileElement plus = driver.findElement(MobileBy.AccessibilityId("plus"));
            MobileElement multiply = driver.findElement(MobileBy.AccessibilityId("multiply"));
            MobileElement equals = driver.findElement(MobileBy.AccessibilityId("equals"));
            MobileElement minus = driver.findElementByAccessibilityId("minus");
            MobileElement delete = driver.findElement(MobileBy.AccessibilityId("delete"));

            MobileElement result = driver.findElement(By.id("com.android.calculator2:id/result"));

            //Test 2 + 2 returning 4

            digit2.click();
            plus.click();
            digit2.click();
            equals.click();
            String resultText = result.getText();
            Assert.assertEquals(resultText,"4");
            Thread.sleep(1000);

            // 4 * 5 returning 20
            digit4.click();
            multiply.click();
            digit5.click();
            equals.click();
            resultText = result.getText();
            Assert.assertEquals(resultText,"20");
            Thread.sleep(1000);

            // 63 - 7 returning 56
            getDigit(6).click();
            getDigit(3).click();
            minus.click();
            getDigit(7).click();
            equals.click();
            resultText = result.getText();
            Assert.assertEquals(resultText,"56");

            Thread.sleep(3000);

            //close the app at the end
            driver.closeApp();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void etsyTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        //desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"\\etsy.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "https://cybertek-appium.s3.amazonaws.com/etsy.apk");
        //https://cybertek-appium.s3.amazonaws.com/etsy.apk
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);

        driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);

        MobileElement you = driver.findElementByAccessibilityId("You tab, 4 of 5");
        you.click();
        Thread.sleep(3000);
        MobileElement settings = driver.findElement(By.xpath("//*[@text='Settings']"));
        settings.click();
        Thread.sleep(3000);
        MobileElement checkBox = driver.findElement(By.id("com.etsy.android:id/settings_checkbox"));
        checkBox.click();
        Thread.sleep(3000);

        //verify after click the box it is not selected
        Assert.assertFalse(driver.findElement(By.id("com.etsy.android:id/settings_checkbox")).isSelected());

        driver.closeApp();
    }



    @Test
    public void realPhoneTest() throws MalformedURLException, InterruptedException {
        /*
        5200435bb4125541 get this number on terminal by sending "adb devices"
        On your Android phone
        Settings --> About phone --> Software Information --> Build number --> tab several time
        Then
        Developer options --> USB debugging enable
         */

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "10.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "5200435bb4125541");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");

        desiredCapabilities.setCapability(MobileCapabilityType.APP, "https://cybertek-appium.s3.amazonaws.com/etsy.apk");
        //https://cybertek-appium.s3.amazonaws.com/etsy.apk
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);

        driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);

        MobileElement you = driver.findElementByAccessibilityId("You tab, 4 of 5");
        you.click();
        Thread.sleep(3000);
        MobileElement settings = driver.findElement(By.xpath("//*[@text='Settings']"));
        settings.click();
        Thread.sleep(3000);
        MobileElement checkBox = driver.findElement(By.id("com.etsy.android:id/settings_checkbox"));
        checkBox.click();
        Thread.sleep(3000);

        //verify after click the box it is not selected
        Assert.assertFalse(driver.findElement(By.id("com.etsy.android:id/settings_checkbox")).isSelected());

        driver.closeApp();
    }

    @Test
    public void ynamdar() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"\\Ynamdar.apk");

        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);

        driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);

        Thread.sleep(10000);
        driver.closeApp();
    }

    //Crete a method that is returning mobile element of the digit that you pass as a parameter
    public  MobileElement getDigit(int digit){
        return driver.findElement(By.id("com.android.calculator2:id/digit_"+ digit));
    }

    @Test
    public void sauceLabs() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability("appiumVersion", "1.18.1");
        desiredCapabilities.setCapability("deviceName","Google Pixel 3a XL GoogleAPI Emulator");
        desiredCapabilities.setCapability("deviceOrientation", "portrait");
        desiredCapabilities.setCapability("browserName", "");
        desiredCapabilities.setCapability("platformVersion","11.0");
        desiredCapabilities.setCapability("platformName","Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "https://cybertek-appium.s3.amazonaws.com/etsy.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);

        driver = new AppiumDriver<>(new URL("https://udurdiyev:56a4c3a2-52a1-4ae2-bdbb-2ffbaa8cafea@ondemand.us-west-1.saucelabs.com:443/wd/hub"), desiredCapabilities);

        MobileElement you = driver.findElementByAccessibilityId("You tab, 4 of 5");
        you.click();
        Thread.sleep(1000);
        MobileElement settings = driver.findElement(By.xpath("//*[@text='Settings']"));
        settings.click();
        Thread.sleep(1000);
        MobileElement checkBox = driver.findElement(By.id("com.etsy.android:id/settings_checkbox"));
        checkBox.click();
        Thread.sleep(1000);

        //verify after click the box it is not selected
        Assert.assertFalse(driver.findElement(By.id("com.etsy.android:id/settings_checkbox")).isSelected());

        driver.quit();
    }
}