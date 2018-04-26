package com.coinmarket.response;

import java.util.ArrayList;

import org.bson.Document;

import com.coinmarket.modal.C;

public class Message {
	private String status;
	private Profile profile;
	private ArrayList<String> favourites;
	
	public Message() {
		
	}
	
	public Message(String status) {
		this.status = status;
		this.profile = null;
	}
	
	public  Message(ArrayList<String> favourites) {
		this.favourites = favourites;
	}
	
	public Message (Document p) {
		this("true",new Profile(
							  p.get(C.MKEY.ID).toString(),
							  p.getString(C.MKEY.EMAIL),
							  p.getString(C.MKEY.NAME))
				);
		
	}
	
	public Message(String status, Profile profile) {
		this.status = status;
		this.profile = profile;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public static Message error() {
		return new Message("error");
	}
	public static Message positive() {
		return new Message("true");
	}
	public static Message negative() {
		return new Message("false");
	}

	public ArrayList<String> getFavourites() {
		return favourites;
	}

	public void setFavourites(ArrayList<String> favourites) {
		this.favourites = favourites;
	}
}
