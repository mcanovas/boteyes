package com.sk.api;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class OpenCVTemplateMatching {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		int matchMethod = Imgproc.TM_CCOEFF;

		//Mat img = Highgui
		//		.imread("/Users/mcanovas/Documents/workspace-king/BotEyes/src/test/resources/images/board2.png");
		
		Mat img = Highgui
				.imread("/var/folders/py/36fh19ln5sg9v46qcjm9wpgw0000gn/T/screenshot1022355796233149335.png");
		Mat template = Highgui
				.imread("/Users/mcanovas/Documents/workspace-king/BotEyes/src/test/resources/images/play_button.png");

		int resultCols = img.cols() - template.cols() + 1;
		int resultRows = img.rows() - template.rows() + 1;
		Mat result = new Mat(resultRows, resultCols, CvType.CV_32FC1);

		Imgproc.matchTemplate(img, template, result, matchMethod);
		
		Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

		MinMaxLocResult mmr = Core.minMaxLoc(result, new Mat());
		
		Point matchLoc;
		if (matchMethod == Imgproc.TM_SQDIFF || matchMethod == Imgproc.TM_SQDIFF_NORMED) {
			matchLoc = mmr.minLoc;
		} else {
			matchLoc = mmr.maxLoc;
		}

		Core.rectangle(img, matchLoc, new Point(matchLoc.x + template.cols(),
				matchLoc.y + template.rows()), new Scalar(0, 255, 0));

		Highgui.imwrite(
				"/Users/mcanovas/Documents/workspace-king/BotEyes/src/test/resources/images/found.png",
				img);
	}

}
