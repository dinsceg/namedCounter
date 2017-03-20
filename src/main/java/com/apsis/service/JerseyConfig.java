package com.apsis.service;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apsis.api.NamedCounterController;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by dinesh on 19/03/17.
 */

@Component
public class JerseyConfig extends ResourceConfig {
 
  @Autowired
  public JerseyConfig(ObjectMapper objectMapper) {
    register(NamedCounterController.class);
  }

}