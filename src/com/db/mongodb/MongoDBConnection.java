package com.db.mongodb;

/*
 * IMPORTS
 */
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

/*
 * CODE
 */

/*
 * Handles the connection with MongoDB by Compose
 * This class was created based on instructions at 
 * <b>http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/</b>
 * 
 */
public class MongoDBConnection {

	/*
	 * CONSTANTS AND DEFINITIONS
	 */

	// Server and port info
	private final String server = "candidate.57.mongolayer.com";
	private final int port = 10008;

	// Instances of MongoDB
	private MongoClient mMongoClient = null;
	private MongoDatabase db = null;
	private MongoCollection<Document> mCollection = null;

	// Credentials
	private final String user = "marcos.v.campelo@gmail.com";
	private final String passwd = "mark1990";
	
	/*
	 * Connect with DB on server using user credentials
	 * 
	 * @param dbName: DB name to connect to
	 * 
	 * @type dbName: String
	 * 
	 * @return: true if successfully connected or false otherwise
	 * 
	 * @rtype: boolean
	 * 
	 */
	public boolean dbConnect(String dbName) {

		// MongoClient was not initialized: initialize it using server, port and
		// credentials
		if (mMongoClient == null) {
			try {
				// Create credential to Compose
				MongoClientURI uri = new MongoClientURI("mongodb://" + user + ":" + passwd + "@" + server + ":" + port);
				mMongoClient = new MongoClient(uri);
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				return false;
			}
		}

		// Get user selected database
		db = mMongoClient.getDatabase(dbName);

		// Return true if successfully connected and false if not
		return db != null;
	}

	/*
	 * Set the current handled collection on DB
	 * 
	 * @param collection: name of the collection to use
	 * 
	 * @type collection: String
	 * 
	 * @return: true if the collection was successfully set or false otherwise
	 * 
	 * @rtype: boolean
	 * 
	 */
	public boolean setCollection(String collection) {

		// Client or DB weren't initialized: set nothing
		if (mMongoClient == null || db == null)
			return false;

		// Get collection from DB
		mCollection = db.getCollection(collection);

		// Return true for collection existent and set or false otherwise
		return mCollection != null;
	}

	public MongoCollection<Document> getCollection() {

		// Client or DB weren't initialized: set nothing
		if (mMongoClient == null || db == null)
			return null;

		// Get collection
		return mCollection;
	}
	
	public MongoDatabase getDB() {

		// Client wasn't initialized: return nothing
		if (mMongoClient == null)
			return null;

		// Get DB
		return db;
	}
		
	public String getHost() {
		return mMongoClient.getAddress().getHost() + ":" + mMongoClient.getAddress().getPort();
	}
}
