package org.anirudha.webservices.rest.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvocationDemo {

	public static void main(String[] args) {
		InvocationDemo demo = new InvocationDemo();
		Invocation invocation = demo.prepareRequestForMessagesByYear(2016);
		Response response = invocation.invoke();
		System.out.println(response.getStatus());
	}
	
	private Invocation prepareRequestForMessagesByYear(int year){
		return ClientBuilder
				.newClient()
				.target("http://localhost:8080/advanced-jax-rs/webapi/")
				.path("messages")
				.queryParam("year", year)
				.request(MediaType.APPLICATION_JSON)
				.buildGet();
	}

}
