package org.sk.appium;

import org.sk.common.location.Point;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumAdapter {

    private static AndroidDriver<MobileElement> driver;

    public static void main(String[] args) {
        // DesiredCapabilities capabilities = DesiredCapabilities.iphone();
        // capabilities.setCapability(MobileCapabilityType.UDID,
        // "ace977285650c8331011560bb4f5cdbe6d644ec8");
        // capabilities.setCapability(CapabilityType.VERSION, "8.3");
        // capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
        // "iPhone 6 Plus");
        // capabilities.setCapability("bundleId",
        // "com.midasplayer.apps.bubblewitchsaga2");

        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "BubbleWitchSaga2Activity");
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.midasplayer.apps.bubblewitchsaga2");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

            Runtime.getRuntime().addShutdownHook(new Thread() {

                @Override
                public void run() {
                    if (driver != null) {
                        driver.quit();
                    }
                }
            });

//            Thread.sleep(20000);

            System.out.println("Starting");
            adapter = new OpenCVAdapter();
            adapter.load();

            click("/Users/mario.canovas/Documents/workspace/frameworks/appium-stuff/BotEyes/src/main/resources/images/play_button.png");
            click("/Users/mario.canovas/Documents/workspace/frameworks/appium-stuff/BotEyes/src/main/resources/images/close_button.png");

            Thread.sleep(10000);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void click(Point point) throws InterruptedException {
        driver.tap(1, (int) point.x, (int) point.y, 500);
    }
}
