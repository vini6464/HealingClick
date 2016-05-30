package com.vinod.service;

import java.util.ArrayList;
import java.util.List;

import com.vinod.dao.ChatDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Chat;

import com.vinod.model.Login;
import com.vinod.model.Message;

import com.vinod.model.User;

public class ChatService {
ChatDao dao = new ChatDao();

public int getChatID(Login login, long otherId, int type) throws DaoException {
	 int chatId = dao.getChatID(login,otherId,type);
	return chatId;
}

public List<Message> getMessagesByChatId(int chatId) throws DaoException {
	List<Message> messages = new ArrayList<Message>();
	messages = dao.getMessagesByChatId(chatId);
	return messages;
}

public int createConversation(Login login, long otherId, int type) throws DaoException {
	int i = dao.createConversation(login,otherId,type);
	return i;
}

public void saveMessage(Login login, int chatId, String message) throws DaoException {
	dao.saveMessage(login,chatId,message);
	
}

public void getActivePatients(Login login, List<User> users) throws DaoException {
	dao.getActivePatients(login,users);
	
}

public void getActivePharmacies(Login login, List<User> users) throws DaoException {
	dao.getActivePharmacies(login,users);
	
}

public void getActiveDoctors(Login login, List<User> users) throws DaoException {
	dao.getActiveDoctors(login , users);
	
}

public List<Chat> getNewMessages(Login login) throws DaoException {
	 List<Chat> chats = new ArrayList<Chat>();
 	chats = dao.getNewMessages(login);
	return chats;
}

public void setRead(int chatId, Login login) throws DaoException {
	dao.setRead(chatId,login);
	
}

public List<Chat> getLatestChat(Login login) throws DaoException {
	List<Chat> chats = dao.getLatestChat(login);
	return chats;
}

public void getPatients(Login login, List<User> users) throws DaoException {
	dao.getPatients(login,users);
	
}

public void getPharmacies(Login login, List<User> users) throws DaoException {
	dao.getPharmacies(login,users);
	
}

public void getDoctors(Login login, List<User> users) throws DaoException {
	dao.getDoctors(login , users);
	
}

public Chat getChatById(int chatId) throws DaoException {
	Chat chat = dao.getChatById(chatId);
	return chat;
}

public void getConnectedPatients(Login login, List<User> users) throws DaoException {
	dao.getConnectedPatients(login,users);
	
}

public void getConnectedPharmacies(Login login, List<User> users) throws DaoException {
	dao.getConnectedPharmacies(login,users);
	
}

public void getConnectedDoctors(Login login, List<User> users) throws DaoException {
	dao.getConnectedDoctors(login , users);
	
}
}
