package com.coinmarket.service;

import org.bson.Document;

import com.coinmarket.modal.C;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MMongo {

	private MongoClient mClient;
	private MongoDatabase mDatabase;
	private MongoCollection<Document> mCollection;
	
	public MMongo() {
		mClient = new MongoClient(C.MONGO.HOST,C.MONGO.PORT);
		mDatabase = mClient.getDatabase(C.MONGO.DATABASE_NAME);
		mCollection = mDatabase.getCollection(C.MONGO.COLLECTION_NAME);
	}

	public MongoClient getmClient() {
		return mClient;
	}

	public MongoDatabase getmDatabase() {
		return mDatabase;
	}

	public MongoCollection<Document> getmCollection() {
		return mCollection;
	}
	
	public FindIterable<Document> find(Document query) {
		return mCollection.find(query).projection(new Document("password",0));
	}
	
	public void insert(Document e) {
		mCollection.insertOne(e);
	}
}
