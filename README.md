# Case Study

## Documentation

This tool compares Hotel reviews on a given topic and scores on author sentiments whether the hotel has positive reviews or negative reviews.

The tool receives as input:
* 	A list of reviews for each hotel.This is received in the form of multiple JSON file. These json files are stored in the **resources** folder of the application. Each having reviews for a specific hotel. You can add more review files in the folder and add the path to the list. We pass a list of input file path to the **HotelComparator.java** file
* The topic we are interested in. This is also passed along with reviews

The tool provides as output:
* A json file named **output.json** containing useful information about the processed reviews. 
* The file contains in JSON format, the processed topic and array of hotels with *{totalAvailableReviews, totalReviewsForTopic, totalReviewScore, totalPositiveScore, totalNegativeScore}*. This way we can determine which hotel scored the based on a given topic.  


The application is modularize into number of classes having specific methods and business logic. The details about classes are as below:

### Trivago.java

This is the main class to run the tool. In main method, create a list of reviews file path, provide a topic and pass these as parameters to the **HotelComparator.java**

### HotelComparator.java

* This class contains the core business logic. This is where we do all the review scoring and processing. 
* It first filters reviews based on given topic. 
* Then for each review, it extracts the sentence of the review containing the topic.
* Finally, it processes the sentence to analyze the author's sentiment by scoring the review on the basis of the available semantics.
* It maintains all the necessary information like positive score, negative score, total review score etc related to each hotel 
* and returns the list of these objects for each hotel to generate the output.


#### HotelData.java

This class contains all the useful information about the hotel scoring. This data object is used to write output to JSON format in a file.

### Semantics.java

This is a singleton class which reads the semantics provided like positive words, negative words and intensifiers. There words have certain values which are used to calculate the review score. Then reviews are analyzed as positive or negative, which ever score is higher. More semantics could be added to *semantics.json* file with values and it will also be processed.

### ApplicationUtils.java

Contains some utility methods to be used throughout the application.

### Constants.java

Contains application constants.


## More information about the tool

* The tool does not consider multiple words as same topic. This is the assumption. Only single word topics are processed.
* The tool uses **json-simple-1.1.1** external library to parse json input file and generate json output file.

### Explanation on why the problem is solved this way

* Understood that it is a natural language processing problem so thought of building sentiment analyzer.
* The idea was to first find the reviews containing the specific topic and not work on all the available reviews.
* Semantics like positive and negative words were provided with values, so built up a map with words as key and given values as their value.This was done to have a faster lookup for semantics
* For each review, it is observed usually that there would be only a single sentence mentioning the specific topic, so extract that particular sentence by splitting the review into multiple sentences using regex __"\\."__ 
* Then, the idea was to process each word of that sentence and analyze the sentiment by looking up to the semantics maps available and find its value
* While processing each sentence, kept on calculating the negative score and positive score as a review could have either sentiments.
* In this way, we would be easily able to process the user sentiments and make necessary changes to our product.
* I asked about the output format but decided to go with json format so that the output could be used in other tools and services. Also, with JSON format, it would be easy to process the output like displaying over a webpage or in a datatable and could also be provided as a service.
* This tool could further be optimized by ignoring helping verbs in the review sentence thus working on selective words and analyzing sentiments.
* More semantics could be added as and when required to the semantics.json file and that will also be processed.
* Was not able to complete the project in python as I have only basic knowledge so had to complete it in Java.
* Honestly, it took me 8 hours to finish up the task till here.
