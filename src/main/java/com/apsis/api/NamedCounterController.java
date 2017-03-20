package com.apsis.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apsis.domain.NamedCounterResponse;
import com.apsis.exception.CounterNotFoundException;
import com.apsis.service.NamedCounterService;
import com.apsis.util.Constants;


/**
 * Created by dinesh on 19/03/17.
 */

@Component
@Path("/counter")
public class NamedCounterController {

    private NamedCounterService namedCounterService;
    
	private NamedCounterResponse counterResponse;
	
	@Autowired
	public NamedCounterController(NamedCounterService namedCounterService,NamedCounterResponse counterResponse){
		this.namedCounterService = namedCounterService;
		this.counterResponse = counterResponse;
	}

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public NamedCounterResponse createAddCounter(@PathParam("name") String name) {
    	try {
    		counterResponse =  namedCounterService.createOrAddCounter(name);
		} catch (Exception e) {
			counterResponse = new NamedCounterResponse();
			counterResponse.setErrorCode(Constants.CREATE_ADD_COUNTER_ERROR_CODE);
			counterResponse.setErrorDescription(Constants.CREATE_ADD_COUNTER_ERROR_DESC);
		}
		return counterResponse;
        
    }

    @GET
    @Path("/current/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public NamedCounterResponse getCurrentValue(@PathParam("name") String name) {
        
        try {
    		counterResponse = namedCounterService.getCounterValue(name);
		}catch (CounterNotFoundException e) {
			counterResponse = new NamedCounterResponse();
			counterResponse.setErrorCode(Constants.NO_COUNTER_ERROR_CODE);
			counterResponse.setErrorDescription(e.getMessage());
		} catch (Exception e) {
			counterResponse = new NamedCounterResponse();
			counterResponse.setErrorCode(Constants.CURRENT_COUNTER_ERROR_CODE);
			counterResponse.setErrorDescription(Constants.CURRENT_COUNTER_ERROR_DESC);
		}
		return counterResponse;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public NamedCounterResponse getAllCounter() {
        try {
    		counterResponse = namedCounterService.getAllCounter();
		} catch (Exception e) {
			counterResponse = new NamedCounterResponse();
			counterResponse.setErrorCode(Constants.GET_ALL_COUNTER_ERROR_CODE);
			counterResponse.setErrorDescription(Constants.GET_ALL_COUNTER_ERROR_DESC);
		}
		return counterResponse;
    }
}
