package com.sk.api.opencv;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class OpenCVResultMatTest {

	@Mocked
	@Injectable
	private OpenCVMat target;

	@Mocked
	@Injectable
	private OpenCVMat template;
	
	@Tested
	private OpenCVResultMat result;

	@Test
	public void assertCols() {
		new Expectations() {
			{
				target.getCols();
				result = 2;
				template.getCols();
				result = 3;
			}
		};
		Assert.assertThat(result.getCols(), new IsEqual<Integer>(0));
	}
	
	@Test
	public void assertRows() {
		new Expectations() {
			{
				target.getRows();
				result = 3;
				template.getRows();
				result = 3;
			}
		};
		Assert.assertThat(result.getRows(), new IsEqual<Integer>(1));
		
	}

}
