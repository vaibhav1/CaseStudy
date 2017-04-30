/**
 * 
 */
package com.trivago.casestudy.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.trivago.casestudy.util.Constants;

/**
 * 
 * Semantics in a singleton class containing all the positive and negative semantics with values and intensifiers.
 * This class reads the semantics and their values specified in the JSON file.
 * 
 * The class is made singleton so as to have a single instance with 
 * global access throughout the application as semantics are to be loaded just once.
 * Maps are used to have a faster look up for values.
 *   
 * @author vshukla
 *
 */
public class Semantics {
	
	/*
	 * single instance
	 */
	private static Semantics semantic;
	
	/*
	 * Map containing positive semantics
	 */
	private Map<String, Long> positives;

	/*
	 * Map containing negative semantics
	 */
	private Map<String, Long> negatives;
	
	/*
	 * Map containing intensifiers
	 */
	private Map<String, Double> intensifiers;
	
	/*
	 * Semantics.json file path
	 */
	private String filePath;
	
	
	/**
	 * Private constructor for Semantics class in order to avoid creating multiple instances.
	 * @param path - semantics.json file path
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	private Semantics(String path) throws FileNotFoundException, IOException, ParseException{
		filePath = path;
		populateSemantics();
	}
	
	/**
	 * Method to return a single instance of the Semantics class after populating all the semantics.
	 * 
	 * @param path - JSON file path
	 * @return single Semantics instance
	 * 
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static Semantics getInstance(String path) throws FileNotFoundException, IOException, ParseException{
		
		if(semantic==null){
			semantic = new Semantics(path);
		}
		return semantic;
	}
	
	
	/**
	 * Method to populate all the semantics
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	private void populateSemantics() throws FileNotFoundException, IOException, ParseException{
		
		File semanticFile = new File(filePath);
		
		JSONParser parser = new JSONParser();
		JSONObject a =   (JSONObject) parser.parse(new FileReader(semanticFile));
		
		JSONArray positiveSemantics = (JSONArray) a.get(Constants.POSITIVE);
		JSONArray negativeSemantics = (JSONArray) a.get(Constants.NEGATIVE);
		JSONArray intensifierSemantics = (JSONArray) a.get(Constants.INTENSIFIER);
		
		positives = readSemantics(positiveSemantics);
		negatives = readSemantics(negativeSemantics);
		intensifiers = readIntensifiers(intensifierSemantics);
		
	}

	
	/**
	 * This method reads a semantics JSON array and returns a map containing semantics and their values.
	 * @param semanticsArray - JSONArray of semantics.json file
	 * @return map contaning semantics and their values to be used to calculate scores
	 */
	private static Map<String, Long> readSemantics(JSONArray semanticsArray) {

		Map<String,Long> semantic = new HashMap<String, Long>();
		for(Object o: semanticsArray){
			JSONObject phrases = (JSONObject) o;
			String phrase = (String) phrases.get(Constants.PHRASE);
			long value =  (long) phrases.get(Constants.VALUE);
			semantic.put(phrase, value);
		}
		return semantic;
	}
	
	/**
	 * Method reads semantics JSON array to populate the intensifiers with the multipliers
	 * @param semanticsArray - JSONArray of semantics.json file
	 * @return map containing intensifiers and their multiplier value
	 */
	private static Map<String, Double> readIntensifiers(JSONArray semanticsArray) {
		
		Map<String,Double> semantic = new HashMap<String, Double>();
		for(Object o: semanticsArray){
			JSONObject phrases = (JSONObject) o;
			String phrase = (String) phrases.get(Constants.PHRASE);
			double value =  ((Number)phrases.get(Constants.MULTIPLIER)).doubleValue();
			semantic.put(phrase, value);
		}
		return semantic;
	}


	/**
	 * @return the positives
	 */
	public Map<String, Long> getPositives() {
		return positives;
	}


	/**
	 * @param positives the positives to set
	 */
	public void setPositives(Map<String, Long> positives) {
		this.positives = positives;
	}


	/**
	 * @return the negatives
	 */
	public Map<String, Long> getNegatives() {
		return negatives;
	}


	/**
	 * @param negatives the negatives to set
	 */
	public void setNegatives(Map<String, Long> negatives) {
		this.negatives = negatives;
	}


	/**
	 * @return the intensifiers
	 */
	public Map<String, Double> getIntensifiers() {
		return intensifiers;
	}


	/**
	 * @param intensifiers the intensifiers to set
	 */
	public void setIntensifiers(Map<String, Double> intensifiers) {
		this.intensifiers = intensifiers;
	}
	
	
}
