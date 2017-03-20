package com.apsis.service;

/**
 * Created by dinesh on 19/03/17.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apsis.domain.Counter;
import com.apsis.domain.NamedCounterResponse;
import com.apsis.exception.CounterNotFoundException;
import com.apsis.util.Constants;

@Service
public class NamedCounterService {

	private NamedCounterResponse counterResponse;

	private static HashMap<String,Integer> counterMap = new HashMap<String,Integer>();
	
	@Autowired
	public NamedCounterService(NamedCounterResponse counterResponse){
		this.counterResponse = counterResponse;
	}

	public NamedCounterResponse getCounterValue(String counterName) throws Exception{
		List<Counter> counterList = new ArrayList<>();
		if(counterMap.get(counterName) != null){
			Counter counter = new Counter(counterName,counterMap.get(counterName));
			counterList.add(counter);
			counterResponse.setCounters(counterList);
			counterResponse.setStatusCode(Constants.SUCCESS_CODE);
		}else{
			throw new CounterNotFoundException(Constants.NO_COUNTER_FOUND + counterName);
		}
		return counterResponse;
	}

	public NamedCounterResponse createOrAddCounter(String counterName) throws Exception{
		List<Counter> counterList = new ArrayList<>();
		if(counterMap.containsKey(counterName)){
			counterMap.put(counterName, counterMap.get(counterName)+Constants.INC_VALUE);
		}else{
			counterMap.put(counterName, Constants.INITIAL_VALUE);
		}
		Counter counter = new Counter(counterName,counterMap.get(counterName));
		counterList.add(counter);
		counterResponse.setCounters(counterList);
		counterResponse.setStatusCode(Constants.SUCCESS_CODE);
		return counterResponse;
	}

	public NamedCounterResponse getAllCounter() throws Exception{
		List<Counter> counterList = new ArrayList<>();
		counterMap.forEach((key,value)->{
			Counter counter = new Counter(key,value);
			counterList.add(counter);
		});
		counterResponse.setCounters(counterList);
		counterResponse.setStatusCode(Constants.SUCCESS_CODE);
		return counterResponse;
	}
}
