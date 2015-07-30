package com.sk.api.appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sk.api.Point;
import com.sk.api.Rect;
import com.sk.api.opencv.OpenCVAdapter;
import com.sk.api.opencv.OpenCVConfig;
import com.sk.api.opencv.OpenCVMat;
import com.sk.api.opencv.OpenCVResultMat;

public class Start {

    private static OpenCVAdapter adapter;
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

    private static void click(String templateFilePath) throws InterruptedException {

        File templateFile = new File(templateFilePath);
        if (!templateFile.exists()) {
            throw new IllegalArgumentException("Template File not found");
        }
        OpenCVMat template = new OpenCVMat(templateFile.getAbsolutePath());

        Rect rect = search(template);

        Point center = rect.getCenter();

        driver.tap(1, (int) center.x, (int) center.y, 500);

    }
    
    public static Rect search(OpenCVMat template) {
        Rect rect = Rect.NOT_FOUNT;
        while (rect == Rect.NOT_FOUNT) {
            File file = driver.getScreenshotAs(OutputType.FILE);
            OpenCVMat target = new OpenCVMat(file.getAbsolutePath());
            OpenCVResultMat result = new OpenCVResultMat(target, template);
            OpenCVConfig config = new OpenCVConfig(Imgproc.TM_CCOEFF_NORMED, result);
            rect = adapter.matchTemplate(config);            
        }
        return rect;
    }
}
