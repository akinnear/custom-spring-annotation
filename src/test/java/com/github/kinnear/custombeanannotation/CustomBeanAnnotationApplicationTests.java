package com.github.kinnear.custombeanannotation;

import com.github.kinnear.custombeanannotation.job.MyCustomJob;
import com.github.kinnear.custombeanannotation.runner.JobRunner;
import com.github.kinnear.custombeanannotation.runner.SimpleJobRunner;
import com.github.kinnear.custombeanannotation.runner.SimpleListJobRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomBeanAnnotationApplicationTests {
	@Autowired
	@Qualifier("myCustomJob.JobRunner")
	SimpleJobRunner<MyCustomJob, String, String> myCustomJobJobRunner;

	@Autowired
	@Qualifier("myCustomListJob.JobRunner")
	SimpleListJobRunner<MyCustomJob, String, String> myCustomJobListJobRunner;

	@Test
	public void simpleJobRunnerWorks() {
		String result = myCustomJobJobRunner.run("12345");
		assertEquals("54321", result);
	}

	@Test
	public void simpleListJobRunnerWorks() {
		List<String> inputs = Arrays.asList("12345", "ABCDEF", null);
		List<String> results = myCustomJobListJobRunner.run(inputs);
		assertEquals(3, inputs.size());
		assertEquals("54321", results.get(0));
		assertEquals("FEDCBA", results.get(1));
		assertNull(results.get(2));
	}

}

