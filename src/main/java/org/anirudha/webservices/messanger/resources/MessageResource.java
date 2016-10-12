package org.anirudha.webservices.messanger.resources;

import java.net.URI;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.anirudha.webservices.messanger.model.Message;
import org.anirudha.webservices.messanger.resources.beans.MessageFilterBean;
import org.anirudha.webservices.messanger.service.MessageService;

@Path("/messages")
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML})
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML})
public class MessageResource {

	private MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
		if (messageFilterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(messageFilterBean.getYear());
		}
		
		if(messageFilterBean.getStart() >= 0 && messageFilterBean.getSize() > 0){
			return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") Long messageId, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComments(uriInfo, message), "comments");
		return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri  = uriInfo.getBaseUriBuilder()
			      .path(MessageResource.class)
			      .path(MessageResource.class, "getCommentResource")
			      .path(CommentResource.class)
			      .resolveTemplate("messageId", message.getId())
			      .build()
			      .toString();
		return uri;
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri  = uriInfo.getBaseUriBuilder()
				      .path(ProfileResource.class)
				      .path(message.getAuthor())
				      .build()
				      .toString();
		return uri;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri  = uriInfo.getBaseUriBuilder()
					  .path(MessageResource.class)
					  .path(String.valueOf(message.getId()))
					  .build()
					  .toString();
		return uri;
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		
		Message insertedMessage = messageService.addMeesage(message);
		String newId = String.valueOf(insertedMessage.getId());
		
		URI uri = uriInfo.getAbsolutePathBuilder()
						 .path(newId)
						 .build();
		
		return Response.created(uri)
					   .entity(insertedMessage)
					   .build();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") Long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId") Long messageId) {
		messageService.removeMessage(messageId);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}

}
