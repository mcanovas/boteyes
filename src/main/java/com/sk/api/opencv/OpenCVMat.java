package com.sk.api.opencv;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public final class OpenCVMat {

	private Mat mat;

	public OpenCVMat(String imagePath) {
		mat = Highgui.imread(imagePath, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
	}

	public int getCols() {
		return mat.cols();
	}

	public int getRows() {
		return mat.rows();
	}

	public Mat getMat() {
		return mat;
	}

}
