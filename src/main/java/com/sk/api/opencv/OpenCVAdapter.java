package com.sk.api.opencv;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.sk.common.Rect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenCVAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCVAdapter.class);

    private boolean loaded = false;

    public boolean load() {
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            loaded = true;
        } catch (UnsatisfiedLinkError er) {
            LOGGER.error("Error Loading OpenCV native library.", er);
            loaded = false;
        }
        return loaded;
    }

    public Rect matchTemplate(OpenCVConfig config) {
        OpenCVResultMat result = config.getResult();
        OpenCVMat template = result.getTemplate();
        OpenCVMat target = result.getTarget();
//        Mat gtarget = new Mat(), gtemplate = new Mat();
        
        Mat targetMat = target.getMat();
        Mat templateMat = template.getMat();
        if (targetMat.empty() || templateMat.empty()) {
            return Rect.NOT_FOUNT;
        }
        
        
        //Imgproc.cvtColor(targetMat, gtarget, Imgproc.COLOR_BGR2GRAY);
        //Imgproc.cvtColor(templateMat, gtemplate, Imgproc.COLOR_BGR2GRAY);
        
        Mat des = null;
        
        fastMatchTemplate(targetMat, templateMat, des, 2);
        
        //Highgui.imwrite("/Users/mario.canovas/Documents/workspace/frameworks/appium-stuff/BotEyes/src/main/resources/images/gtarget.png",
        //  gtarget);
        //        
        //Highgui.imwrite("/Users/mario.canovas/Documents/workspace/frameworks/appium-stuff/BotEyes/src/main/resources/images/gtemplate.png",
        //  gtemplate);
        
        Imgproc.matchTemplate(targetMat, templateMat, result.getMat(), config.getMatchMethod());
        //Imgproc.threshold(result.getMat(), result.getMat(), 0.55, 1., Imgproc.THRESH_TOZERO);

        //Core.normalize(result.getMat(), result.getMat(), 0, 1, Core.NORM_MINMAX, -1, new Mat());

        Rect bounds = Rect.NOT_FOUNT;
//        while (bounds == Rect.NOT_FOUNT) {
            MinMaxLocResult mmr = Core.minMaxLoc(result.getMat());
            System.out.println(mmr.maxVal);
            if (mmr.maxVal >= 0.9) {
                Point matchLoc;
                if (config.getMatchMethod() == Imgproc.TM_SQDIFF || config.getMatchMethod() == Imgproc.TM_SQDIFF_NORMED) {
                    matchLoc = mmr.minLoc;
                } else {
                    matchLoc = mmr.maxLoc;
                }

                Point point = new Point(matchLoc.x + template.getCols(), matchLoc.y + template.getRows());

//                Core.rectangle(target.getMat(), matchLoc, new Point(matchLoc.x + template.getCols(), matchLoc.y
//                        + template.getRows()), new Scalar(0, 255, 0));
//
//                Highgui.imwrite("/Users/mario.canovas/Documents/workspace/frameworks/appium-stuff/BotEyes/src/main/resources/images/found.png",
//                        target.getMat());
                bounds = new Rect(matchLoc.x, matchLoc.y, point.x, point.y);
            }
//        }

        return bounds;

    }

    private void fastMatchTemplate(Mat gtarget, Mat gtemplate, Mat des, int i) {
//        Imgproc.blur(gtarget, gtarget, gtarget.size());
//        Imgproc.Canny(gtarget, gtarget, 0.5, 0.9);
//        Imgproc.cornerHarris(gtarget, gtarget, 1, 1, 1);

        
//        Imgproc.blur(gtemplate, gtemplate, gtemplate.size());
//        Imgproc.Canny(gtemplate, gtemplate, 0.5, 0.9);
//        Imgproc.cornerHarris(gtemplate, gtemplate, 1, 1, 1);
        
        Imgproc.equalizeHist(gtarget, gtarget);
        Imgproc.equalizeHist(gtemplate, gtemplate);
        
        Highgui.imwrite("/Users/mario.canovas/Documents/workspace/frameworks/appium-stuff/BotEyes/src/main/resources/images/gtarget.png",
          gtarget);
        Highgui.imwrite("/Users/mario.canovas/Documents/workspace/frameworks/appium-stuff/BotEyes/src/main/resources/images/gtemplate.png",
          gtemplate);

    }

    private void drawRectangle(Mat img, Point matchLoc, Mat template) {
        Core.rectangle(img, matchLoc, new Point(matchLoc.x + template.cols(), matchLoc.y + template.rows()),
                new Scalar(0, 255, 0));
    }

}
