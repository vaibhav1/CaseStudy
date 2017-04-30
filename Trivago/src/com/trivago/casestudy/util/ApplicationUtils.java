/**
 * 
 */
package com.trivago.casestudy.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.trivago.casestudy.model.HotelData;

/**
 * Class containing utility method of the application
 * @author vshukla
 *
 */
public class ApplicationUtils {
	
	/**
	 * The method receives a JSONArray of reviews of a particular hotel and 
	 * returns a filtered list of review containing the specified topic
	 * 
	 * @param reviews - JSONArray of reviews for a particular hotel
	 * @param topic - a topic we need to analyze
	 * @return - list of reviews containing the specified topic
	 * 
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static List<String> getReviewsWithTopic(JSONArray reviews, String topic)
	{
		
		List<String> contents = new ArrayList<String>();
		for (Object o : reviews) {
			JSONObject review = (JSONObject) o;

			String content = (String) review.get(Constants.CONTENT);
			if(content.contains(topic)){
				contents.add(content);
			}
		}
		return contents;
	}

	/**
	 * Creates a JSON file inside output folder of the application by processing the HotelData list.
	 * 
	 * @param data - list containing Hotel comparison data 
	 * @return output file path
	 */
	@SuppressWarnings("unchecked")
	public static String generateOutput(List<HotelData> data, String topic) {
		
		String outputFilePath = "";
		File outputFile = null;
		if(data!=null){
			outputFile = new File(Constants.OUTPUT_FILE_PATH);
			if(!outputFile.exists()){
				try {
					outputFile.createNewFile();
				} catch (IOException e) {
					System.out.println(Constants.ERROR+e.getMessage());
					e.printStackTrace();
					return outputFilePath;
				}
			}
			JSONObject output = new JSONObject();
			output.put(Constants.TOPIC, topic);
			
			
			JSONArray hotels = new JSONArray();
			for(HotelData hotel: data){
				JSONObject obj = populateHotelDataJSONObject(hotel);
				hotels.add(obj);
			}
			
			output.put(Constants.HOTELINFO, hotels);
			
			try (FileWriter file = new FileWriter(outputFile)) {

	            file.write(output.toJSONString());
	            file.flush();

	        } catch (IOException e) {
	        	System.out.println(Constants.ERROR+e.getMessage());
	        	e.printStackTrace();
	        	return outputFilePath;
	        }
		}
		outputFilePath = outputFile.getPath();
		return outputFilePath;
	}

	/**
	 * Method populates a JSONObject with useful hotel data to add it to output file
	 * 
	 * @param hotel - HotelData containing useful information
	 */
	@SuppressWarnings("unchecked")
	private static JSONObject populateHotelDataJSONObject(HotelData hotel) {
		JSONObject obj = new JSONObject();
		obj.put(Constants.NAME, hotel.getHotelName());
		obj.put(Constants.TOTAL_AVAILABLE_REVIEWS, hotel.getTotalAvailableReviews());
		obj.put(Constants.TOTAL_TOPIC_REVIEWS, hotel.getTotalTopicReviews());
		obj.put(Constants.TOTAL_REVIEW_SCORE, hotel.getTotalReviewScore());
		obj.put(Constants.TOTAL_POSITIVE_SCORE, hotel.getTotalPositiveScore());
		obj.put(Constants.TOTAL_NEGATIVE_SCORE, hotel.getTotalNegativeScore());
		return obj;
	}


}
