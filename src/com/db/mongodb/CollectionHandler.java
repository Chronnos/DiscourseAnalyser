package com.db.mongodb;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

/*
 * Class to perform queries and other necessary functions for app 
 */
public class CollectionHandler {
	public MongoDBConnection mConnection = null;
	public String mCollectionName = null;
	public String mDBName = null;

	public CollectionHandler(String collection, String db) {
		mCollectionName = collection;
		mDBName = db;

		mConnection.dbConnect(mDBName);
		mConnection.setCollection(mCollectionName);
	}

	public MongoCollection<Document> getCollection() {
		return mConnection.getCollection();
	}
	
	public String getFisrtSpeech() {
		return mConnection.getCollection().find().first().get("speech").toString();
	}	
}
