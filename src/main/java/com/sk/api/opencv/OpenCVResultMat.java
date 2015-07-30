package com.sk.api.opencv;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class OpenCVResultMat {

	private OpenCVMat target;

	private OpenCVMat template;
	
	private Mat mat;

	public OpenCVResultMat(OpenCVMat target, OpenCVMat template) {
		this.target = target;
		this.template = template;
	}
	
	public int getCols() {
		return target.getCols() - template.getCols() + 1;
	}

	public int getRows() {
		return target.getRows() - template.getRows() + 1;
	}
	
	public OpenCVMat getTarget() {
		return target;
	}
	
	public OpenCVMat getTemplate() {
		return template;
	}
	
	public Mat getMat() {
		if (mat == null) {
			mat = new Mat(getCols(), getRows(), CvType.CV_32FC1);
		}
		return mat;
	}

}
