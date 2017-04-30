package com.trivago.casestudy.test;

import java.util.ArrayList;
import java.util.List;

import com.trivago.casestudy.app.HotelComparator;
import com.trivago.casestudy.model.HotelData;
import com.trivago.casestudy.util.ApplicationUtils;

public class Trivago {

	/**
	 * Main method to test application. We need to specify the topic and pass it to HotelComparator algorithm.
	 * Then it generates the output in JSON format in the output folder of the application directory
	 * @param args
	 */
	public static void main(String[] args) {
		
		HotelComparator hc  = new HotelComparator();

		String topic = "breakfast";
		
		List<String> path = readReviewsInput();
		
		List<HotelData> data = hc.compareHotels(path, topic);
		
		String outputFilePath = ApplicationUtils.generateOutput(data, topic);
		
		if(!outputFilePath.isEmpty()){
			System.out.println("Output file generated at: "+outputFilePath);
		}
		
	}

	/**
	 * Method creates a list of input file paths to feed into the comparator algorithm.
	 * To add a new input file, just add it to the resource folder and add its path to the list.
	 * 
	 * @return list of filePaths.
	 */
	private static List<String> readReviewsInput() {
		List<String> path = new ArrayList<String>();
		path.add("resources/reviews1.json");
		path.add("resources/reviews2.json");
		path.add("resources/reviews3.json");
		path.add("resources/reviews4.json");
		path.add("resources/reviews5.json");
		return path;
	}

	
}
