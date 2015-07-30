package com.sk.api;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

public class RectTest {

	@Test
	public void assertWidthCalculation() {
		double width = new Rect(100, 100, 300, 200).getWidth();
		Assert.assertThat(width, new IsEqual<Double>(200d));
	}

	@Test
	public void assertHeightCalculation() {
		double height = new Rect(100, 100, 300, 200).getHeight();
		Assert.assertThat(height, new IsEqual<Double>(100d));
	}

}
