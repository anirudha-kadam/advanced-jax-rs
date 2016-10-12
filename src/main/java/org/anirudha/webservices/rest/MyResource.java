package org.anirudha.webservices.rest;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@Path("/date/{dateString}")
@Path("/test")
public class MyResource {

	/*@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getRequestedDate(@PathParam("dateString") MyDate myDate){
		return myDate.toString();
	}*/
	
	
	/**
	 * converts date to plain text using custom messagebodywriter
	 * @return
	 */
	@GET
	@Produces(value={MediaType.TEXT_PLAIN, "text/shortdate"})
	public Date getRequestedDate(){
		return Calendar.getInstance().getTime();
	}
}
