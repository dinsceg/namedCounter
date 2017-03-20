package com.apsis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apsis.api.NamedCounterController;
import com.apsis.domain.Counter;
import com.apsis.domain.NamedCounterResponse;
import com.apsis.service.NamedCounterService;

@RunWith(SpringJUnit4ClassRunner.class)
public class NamedCounterApplicationMockTests {
	
	@Mock
	private NamedCounterService service;
	
	@InjectMocks
	private NamedCounterController controller;
	
	@Test
	public void testMockCounterValue() throws Exception {
		NamedCounterResponse response = new NamedCounterResponse();
		Counter counter = new Counter("test",1);
		List<Counter> counterList = new ArrayList<>();
		counterList.add(counter);
		response.setCounters(counterList);
		response.setStatusCode("0");
		Mockito.when(service.getCounterValue("test")).thenReturn(response);
		NamedCounterResponse res =  controller.getCurrentValue("test");
		Assert.assertEquals("0", res.getStatusCode());
	}

	@Test
	public void testMockCreateAddCounter() throws Exception {
		NamedCounterResponse response = new NamedCounterResponse();
		Counter counter = new Counter("test",1);
		List<Counter> counterList = new ArrayList<>();
		counterList.add(counter);
		response.setCounters(counterList);
		response.setStatusCode("0");
		Mockito.when(service.createOrAddCounter("test")).thenReturn(response);
		NamedCounterResponse res =  controller.createAddCounter("test");
		Assert.assertEquals("0", res.getStatusCode());
	}
	
	@Test
	public void testMockAllCounter() throws Exception {
		NamedCounterResponse response = new NamedCounterResponse();
		Counter counter = new Counter("test",1);
		Counter secondCounter = new Counter("test1",2);
		List<Counter> counterList = new ArrayList<>();
		counterList.add(counter);
		counterList.add(secondCounter);
		response.setCounters(counterList);
		response.setStatusCode("0");
		Mockito.when(service.getAllCounter()).thenReturn(response);
		NamedCounterResponse res =  controller.getAllCounter();
		Assert.assertEquals("0", res.getStatusCode());
	}
	
	@Test
	public void testMockInvalidCounterValue() throws Exception {
		NamedCounterResponse response = new NamedCounterResponse();
		Counter counter = new Counter("test",1);
		List<Counter> counterList = new ArrayList<>();
		counterList.add(counter);
		response.setCounters(counterList);
		response.setStatusCode("0");
		Mockito.when(service.createOrAddCounter("test")).thenReturn(response);
		NamedCounterResponse res =  controller.getCurrentValue("test1");
		Assert.assertEquals(null, res);
	}

}
