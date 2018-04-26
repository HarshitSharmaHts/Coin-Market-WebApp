package com.coinmarket.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.coinmarket.modal.C;
import com.coinmarket.response.Message;

public class UserService {
	
	MMongo mongo = new MMongo();
	
	public Message signup(String name, String email, String password) {
		try {
			
			Iterator<Document> iterator = mongo.find(new Document(C.MKEY.EMAIL,email)).iterator();
			if(iterator.hasNext())
				return Message.negative();
			else {
				Document eDocument = new Document(C.MKEY.NAME,name)
										 .append(C.MKEY.EMAIL, email)
										 .append(C.MKEY.PASSWORD, password);
				mongo.insert(eDocument);
				return Message.positive();
			}
			
		} catch (Exception ex) {
			
			return new Message(ex.getMessage());
			
		}
	}
	
	public Message login (String email, String password) {
		try {
		
			Iterator<Document> iterator = mongo.find(
					new Document(C.MKEY.EMAIL,email)
					.append(C.MKEY.PASSWORD,password)
					).iterator();
			
			if(iterator.hasNext()) {
				return new Message(iterator.next());
			} else {
				return Message.negative();
			}
		
		} catch (Exception e) {
			
			return new Message(e.getMessage());
			
		}
	}
	
	public ArrayList<String> favourites(String email) {
		try {
			
			Iterator<Document> iterator = mongo.find(new Document(C.MKEY.EMAIL,email)).iterator();
			if(iterator.hasNext()) {
				ArrayList<String> arrayList = (ArrayList<String>)iterator.next().get(C.MKEY.FAVOURITE);
				return arrayList;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
