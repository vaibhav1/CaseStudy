package com.trivago.casestudy.app;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.trivago.casestudy.model.HotelData;
import com.trivago.casestudy.model.Semantics;
import com.trivago.casestudy.util.ApplicationUtils;
import com.trivago.casestudy.util.Constants;

public class HotelComparator {
	
	
	/**
	 * This is the method containing core business logic. This reads all the reviews files to process.
	 * It first filters reviews based on given topic. Then for each review, it extracts the sentence of the review containing the topic.
	 * Finally, it processes the sentence to analyze the author's sentiment by scoring the review on the basis of the available semantics.
	 * It maintains all the necessary information like positive score, negative score, total review score etc related to each hotel 
	 * and returns the list of these objects for each hotel to generate the output.
	 * 
	 * @param hotelReviews - List of path to all the reviews files available in the database.
	 * @param topic - topic to analyze the review sentiments on.
	 * @return list of HotelData
	 * 
	 */
	public List<HotelData> compareHotels(List<String> hotelReviews, String topic) {
		
		List<HotelData> allHotelData = new ArrayList<HotelData>();
		
		JSONParser parser = new JSONParser();
		
		for(String path: hotelReviews){
			
			HotelData hotelData = new HotelData();
			
			JSONObject hotelDetails = null;
			
			try {
				hotelDetails = (JSONObject) parser.parse(new FileReader(path));
			} catch (IOException | ParseException e) {
				System.out.println(Constants.ERROR+e.getMessage());
				e.printStackTrace();
			}
			
			JSONObject hotelInfo = (JSONObject) hotelDetails.get(Constants.HOTELINFO);
			String hotelName = (String) hotelInfo.get(Constants.NAME);
			hotelData.setHotelName(hotelName);
			
			
			JSONArray reviews = (JSONArray) hotelDetails.get(Constants.REVIEWS);
			hotelData.setTotalAvailableReviews(reviews.size());
			
			List<String> contents = ApplicationUtils.getReviewsWithTopic(reviews, topic);
			hotelData.setTotalTopicReviews(contents.size());
			 
			Semantics semantics = null;
			
			try {
				semantics = Semantics.getInstance(Constants.SEMANTICS_FILEPATH);
			} catch (IOException | ParseException e) {
				System.out.println(Constants.ERROR+e.getMessage());
				e.printStackTrace();
			}
			    	
			int totalReviewsScore = 0;
			int totalPositiveScore = 0;
			int totalNegativeScore = 0;
			
			for(String review: contents){
				
				String [] sentences = review.split(Constants.SENTENCE_DELIMETER);
				String sentenceWithTopic="";
				for(int i=0;i<sentences.length;i++){
					if(sentences[i].contains(topic)){
						sentenceWithTopic = sentences[i];
						break;
					}
				}
				
				long sentimentValue = 0;
				double intensifierValue = 0;
				long positiveScore = 0;
				long negativeScore = 0;
				
				for (String word : sentenceWithTopic.split(Constants.SPACE)){
					
					Map<String,Long> positives = semantics.getPositives();
					Map<String,Long> negatives = semantics.getNegatives();
					Map<String,Double> intensifiers = semantics.getIntensifiers();
					
				    
					if(positives.get(word)!=null){
				    	sentimentValue =  positives.get(word);
				    	positiveScore = sentimentValue;
				    } else if(negatives.get(word)!=null){
				    	sentimentValue = negatives.get(word);
				    	negativeScore = sentimentValue;
				    } else if(intensifiers.get(word)!=null){
				    	intensifierValue = intensifiers.get(word);
				    }
				}
				
				long totalScore = sentimentValue;
				if(intensifierValue!=0){
					totalScore*=intensifierValue;
					positiveScore*=intensifierValue;
					negativeScore*=intensifierValue;
				}
				
				totalReviewsScore+=totalScore;
				totalPositiveScore+=positiveScore;
				totalNegativeScore+=negativeScore;
				
			}
			  
			hotelData.setTotalReviewScore(totalReviewsScore);
			hotelData.setTotalPositiveScore(totalPositiveScore);
			hotelData.setTotalNegativeScore(totalNegativeScore);
			
			allHotelData.add(hotelData);
		}
		
		return allHotelData;
	}
	
}
