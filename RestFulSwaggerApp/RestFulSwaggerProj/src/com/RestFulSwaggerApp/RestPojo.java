package com.RestFulSwaggerApp;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/hello")
@Api(value = "/hello", description = "manage message class")
public class RestPojo
{
    public RestPojo()
    {
        super();
    }
    
    @GET
    @Path("/SayHello")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation( 
    	    value = "Displaying message", 
    	    notes = "Get input name and display", 
    	    response = String.class ,
    	    responseContainer = "List"
    	)
    public String SayHello(@QueryParam("inputName")  String inputName)
    {
      //  return "Hello: "+ inputName;
        return "{'name':'"+ inputName + "', 'Description':'Test on Server'}";
    }
}

