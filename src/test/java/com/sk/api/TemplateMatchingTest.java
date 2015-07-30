package com.sk.api;

import static org.junit.Assert.*;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.hamcrest.core.IsSame;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class TemplateMatchingTest {

	@Tested
	private TemplateMatching templateMatching;

	private String image = "image.png";
	private String template = "template.png";

	@Test
	public void assertNoMatches() {
		Assert.assertThat(templateMatching.search(image, template),
				new IsSame<Integer>(0));
	}

	@Test
	@Ignore
	public void assertOneMatch() {
		Assert.assertThat(templateMatching.search(image, template),
				new IsSame<Integer>(1));
	}

}
