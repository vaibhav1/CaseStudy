/**
 * 
 */
package com.trivago.casestudy.model;

/**
 * Class containing useful statistics related to hotel. 
 * The HotelData object is used to generate the output having useful hotel details.
 * 
 * @author vshukla
 *
 */
public class HotelData {
	
	/**
	 * Hotel name
	 */
	private String hotelName;
	
	
	/**
	 * total available reviews for a hotel
	 */
	private int totalAvailableReviews;
	
	
	/**
	 * total reviews containing the given topic
	 */
	private int totalTopicReviews;
	
	
	/**
	 * total reviews score based on semantics values
	 */
	private int totalReviewScore;
	
	
	/**
	 * total positive score for a topic based on semantics values
	 */
	private int totalPositiveScore;
	
	
	
	/*
	 * total negative score for a topic based on semantic values
	 */
	private int totalNegativeScore;
	
	

	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * @param hotelName the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	/**
	 * @return the totalAvailableReviews
	 */
	public int getTotalAvailableReviews() {
		return totalAvailableReviews;
	}

	/**
	 * @param totalAvailableReviews the totalAvailableReviews to set
	 */
	public void setTotalAvailableReviews(int totalAvailableReviews) {
		this.totalAvailableReviews = totalAvailableReviews;
	}

	/**
	 * @return the totalTopicReviews
	 */
	public int getTotalTopicReviews() {
		return totalTopicReviews;
	}

	/**
	 * @param totalTopicReviews the totalTopicReviews to set
	 */
	public void setTotalTopicReviews(int totalTopicReviews) {
		this.totalTopicReviews = totalTopicReviews;
	}

	/**
	 * @return the totalReviewScore
	 */
	public int getTotalReviewScore() {
		return totalReviewScore;
	}

	/**
	 * @param totalReviewScore the totalReviewScore to set
	 */
	public void setTotalReviewScore(int totalReviewScore) {
		this.totalReviewScore = totalReviewScore;
	}

	/**
	 * @return the totalPositiveScore
	 */
	public int getTotalPositiveScore() {
		return totalPositiveScore;
	}

	/**
	 * @param totalPositiveScore the totalPositiveScore to set
	 */
	public void setTotalPositiveScore(int totalPositiveScore) {
		this.totalPositiveScore = totalPositiveScore;
	}

	/**
	 * @return the totalNegativeScore
	 */
	public int getTotalNegativeScore() {
		return totalNegativeScore;
	}

	/**
	 * @param totalNegativeScore the totalNegativeScore to set
	 */
	public void setTotalNegativeScore(int totalNegativeScore) {
		this.totalNegativeScore = totalNegativeScore;
	}

}
