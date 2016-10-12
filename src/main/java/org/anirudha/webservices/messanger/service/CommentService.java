package org.anirudha.webservices.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.anirudha.webservices.messanger.database.DatabaseClass;
import org.anirudha.webservices.messanger.model.Comment;
import org.anirudha.webservices.messanger.model.ErrorMessage;
import org.anirudha.webservices.messanger.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	

	public List<Comment> getAllComments(Long messageId) {
		return new ArrayList<Comment>(messages.get(messageId).getComments().values());
	}

	public Comment getComment(Long messageId, Long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("not found", 404, "documentation URI");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();        
		Message message = messages.get(messageId);
		if(message == null){
			throw new WebApplicationException(response);
		}
		Comment comment = message.getComments().get(commentId);
		if(comment == null){
			throw new NotFoundException(response);
		}
		return comment;
	}


	public Comment addComment(Long messageId, Comment newComment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		newComment.setId(comments.size() + 1);
		comments.put(newComment.getId(), newComment);
		return newComment;
	}

	public Comment updateComment(Long messageId, Comment updatedComment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (updatedComment.getId() <= 0) {
			return null;
		}
		comments.put(updatedComment.getId(), updatedComment);
		return updatedComment;
	}

	public Comment removeComment(Long messageId, Long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
