package org.anirudha.webservices.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.anirudha.webservices.messanger.model.Message;

public class GenericDemo {

	public static void main(String[] args) {
		
		List<Message> list = ClientBuilder
							.newClient()
							.target("http://localhost:8080/advanced-jax-rs/webapi/")
							.path("messages")
							.queryParam("year", 2016)
							.request(MediaType.APPLICATION_JSON)
							.get(new GenericType<List<Message>>(){});
		
		System.out.println(list);

	}

}
