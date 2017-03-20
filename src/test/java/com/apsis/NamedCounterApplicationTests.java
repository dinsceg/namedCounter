package com.apsis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.apsis.domain.NamedCounterResponse;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NamedCounterApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testCreateAddCounter() {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("name", "test");
		NamedCounterResponse counterResponse = this.restTemplate.getForObject("/counter/{name}", NamedCounterResponse.class, uriVariables);
		Assert.assertEquals("0", counterResponse.getStatusCode());
	}

	@Test
	public void getAllCounter() {
		NamedCounterResponse counterResponse = this.restTemplate.getForObject("/counter/all", NamedCounterResponse.class);
		Assert.assertEquals("0", counterResponse.getStatusCode());
	}
	
	@Test
	public void testGetCurrentValue() {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("name", "test");
		NamedCounterResponse counterResponse = this.restTemplate.getForObject("/counter/current/{name}", NamedCounterResponse.class, uriVariables);
		Assert.assertEquals("0", counterResponse.getStatusCode());
	}
	
	@Test
	public void testErrorGetCurrentValue() {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("name", "test1");
		NamedCounterResponse counterResponse = this.restTemplate.getForObject("/counter/current/{name}", NamedCounterResponse.class, uriVariables);
		Assert.assertEquals("-4", counterResponse.getErrorCode());
		Assert.assertNotNull(counterResponse.getErrorDescription());
	}

}
