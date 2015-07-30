package com.sk.api.opencv;

public final class OpenCVConfig {

	private int matchMethod;

	private OpenCVResultMat result;

	public OpenCVConfig(int matchMethod, OpenCVResultMat result) {
		this.matchMethod = matchMethod;
		this.result = result;
	}

	public int getMatchMethod() {
		return matchMethod;
	}

	public OpenCVResultMat getResult() {
		return result;
	}

}
