package org.anirudha.webservices.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.anirudha.webservices.messanger.model.Message;

public class RestAPIClient {

	public static void main(String[] args) {

		/*
		 * Client client = ClientBuilder.newClient(); Response response =
		 * client.target(
		 * "http://localhost:8080/advanced-jax-rs/webapi/messages/1").request().
		 * get(); Message message = response.readEntity(Message.class);
		 * System.out.println(message.getMessage());
		 */

		/*
		 * Client client = ClientBuilder.newClient(); WebTarget target =
		 * client.target(
		 * "http://localhost:8080/advanced-jax-rs/webapi/messages/1"); Builder
		 * builder = target.request(); Response response = builder.get();
		 * Message message = response.readEntity(Message.class);
		 * System.out.println(message.getMessage());
		 */

		/*Client client = ClientBuilder.newClient();
		Message message = client.target("http://localhost:8080/advanced-jax-rs/webapi/messages/1")
							.request(MediaType.APPLICATION_JSON)
							.get(Message.class);

		System.out.println(message.getMessage());*/
		
		
		/*Client client = ClientBuilder.newClient();
		String message = client.target("http://localhost:8080/advanced-jax-rs/webapi/messages/1")
							.request(MediaType.APPLICATION_JSON)
							.get(String.class);

		System.out.println(message);*/
		
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/advanced-jax-rs/webapi/");
		WebTarget messagesTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messagesTarget.path("{messageId}");
		
		Message message1 = singleMessageTarget
						 	.resolveTemplate("messageId", 1)
							.request(MediaType.APPLICATION_JSON)
							.get(Message.class);

		System.out.println(message1.getMessage());
		
		Message message2 = singleMessageTarget
			 	.resolveTemplate("messageId", 2)
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);

		System.out.println(message2.getMessage());
		
		
		
		//post method usage
		Message newMessage = new Message(4, "my new message from jax-rs rest client", "Anirudha");
		
		Response postResponse = messagesTarget
								.request()
								.post(Entity.json(newMessage));
		
		if(postResponse.getStatus() != 201){
			System.out.println("Error occured while creating a resource");
		}
		
		Message createdMessage = postResponse.readEntity(Message.class);
		
		System.out.println(createdMessage.getId() +" "+ createdMessage.getMessage());
	}

}
