package com.sk.boteyes;

import java.io.File;

import org.opencv.imgproc.Imgproc;
import org.sk.common.Point;
import org.sk.common.Rect;

import com.sk.api.opencv.OpenCVAdapter;
import com.sk.api.opencv.OpenCVConfig;
import com.sk.api.opencv.OpenCVMat;
import com.sk.api.opencv.OpenCVResultMat;

public class Start {

    private static OpenCVAdapter adapter;

    
    public Point extractCenter(String templateFilePath) {
        File templateFile = new File(templateFilePath);
        if (!templateFile.exists()) {
            throw new IllegalArgumentException("Template File not found");
        }
        OpenCVMat template = new OpenCVMat(templateFile.getAbsolutePath());

        Rect rect = search(template);

        return rect.getCenter();
    }
    
    public Rect search(OpenCVMat template) {
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
