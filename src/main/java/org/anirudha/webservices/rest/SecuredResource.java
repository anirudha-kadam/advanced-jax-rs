package org.anirudha.webservices.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/secured")
public class SecuredResource {

	@GET
	@Path("/messages")
	@Produces(MediaType.TEXT_PLAIN)
	public String securedMethod(){
		return "This is authorized access to API";
	}
}
