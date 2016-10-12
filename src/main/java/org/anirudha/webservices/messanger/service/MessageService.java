package org.anirudha.webservices.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.anirudha.webservices.messanger.database.DatabaseClass;
import org.anirudha.webservices.messanger.exception.DataNotFoundException;
import org.anirudha.webservices.messanger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService(){
		messages.put(1L, new Message(1L, "hello Pune", "anirudha"));
		messages.put(2L, new Message(2L, "hello Binghamton", "vinay"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(Long id){
		Message message = messages.get(id);
		if(message == null){
			throw new DataNotFoundException("Message with id "+id+" not found");
		}
		return message;
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		List<Message> list = new ArrayList<Message>(messages.values());
		if(start + size > list.size())
			return new ArrayList<Message>();
		return list.subList(start, start + size);
	}
	
	public Message addMeesage(Message newMessage){
		newMessage.setId(messages.size() + 1);
		messages.put(newMessage.getId(), newMessage);
		return newMessage;
	}
	
	public Message updateMessage(Message updatedMessage){
		if(updatedMessage.getId() <= 0){
			return null;
		}
		messages.put(updatedMessage.getId(), updatedMessage);
		return updatedMessage;
	}
	
	public Message removeMessage(Long id){
		return messages.remove(id);
	}
}

