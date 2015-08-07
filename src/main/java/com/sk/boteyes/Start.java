package com.sk.boteyes;

import com.sk.api.opencv.OpenCVAdapter;
import com.sk.api.opencv.OpenCVConfig;
import com.sk.api.opencv.OpenCVMat;
import com.sk.api.opencv.OpenCVResultMat;
import org.opencv.imgproc.Imgproc;
import org.sk.common.adapter.Adapter;

import java.io.File;

public class Start {

    private final OpenCVAdapter adapter;

    private final Adapter adapter;

    
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
