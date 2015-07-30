package com.sk.api;

import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sk.api.opencv.OpenCVAdapter;
import com.sk.api.opencv.OpenCVConfig;

@RunWith(JMockit.class)
public class OpenCVAdapterTest {

	@Tested
	private OpenCVAdapter adapter;
	
	@Injectable
	@Mocked
	private OpenCVConfig config;

	@Test
	public void assertLibraryLoads() {
		Assert.assertTrue(adapter.load());
	}

	@Test
	public void assertLibraryDoesntLoad() {
		new MockUp<System>() {
			@Mock
			public void loadLibrary(String library) {
				throw new UnsatisfiedLinkError(
						"Error forced with Static Mock of System load library method.");
			}

		};
		Assert.assertFalse(adapter.load());
	}
}
