import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * Class that contains helper methods for the Review Lab
 **/
public class Review {
  
  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
  private static ArrayList<String> posAdjectives = new ArrayList<String>();
  private static ArrayList<String> negAdjectives = new ArrayList<String>();
 
  
  private static final String SPACE = " ";
  
  public static void main(String args[]) {
    System.out.println(sentimentVal("boring"));
    System.out.println(sentimentVal("exciting"));
    System.out.println(sentimentVal("awful"));
    System.out.println(totalSentiment("/workspace/java/ConsumerLab_Code/SimpleReview.txt"));
    System.out.println(starRating("/workspace/java/ConsumerLab_Code/SimpleReview.txt"));
    System.out.println(fakeReview("/workspace/java/ConsumerLab_Code/SimpleReview.txt"));
  }

  static{
    try {
      Scanner input = new Scanner(new File("/workspace/java/ConsumerLab_Code/cleanSentiment.csv"));
      while(input.hasNextLine()){
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0],Double.parseDouble(temp[1]));
        //System.out.println("added "+ temp[0]+", "+temp[1]);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }
  
  
  //read in the positive adjectives in postiveAdjectives.txt
     try {
      Scanner input = new Scanner(new File("/workspace/java/ConsumerLab_Code/positiveAdjectives.txt"));
      while(input.hasNextLine()){
        String temp = input.nextLine().trim();
        System.out.println(temp);
        posAdjectives.add(temp);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }   
 
  //read in the negative adjectives in negativeAdjectives.txt
     try {
      Scanner input = new Scanner(new File("/workspace/java/ConsumerLab_Code/negativeAdjectives.txt"));
      while(input.hasNextLine()){
        negAdjectives.add(input.nextLine().trim());
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing negativeAdjectives.txt");
    }   
  }
  

  public static double totalSentiment( String fileName ) {
    String totalReviewString = textToString(fileName);
    String[] words = new String[50];
    int index = 0;
    double total = 0;
    boolean stop = false;

    while (!stop) {
      words[index] = totalReviewString.substring(0, totalReviewString.indexOf(" "));
      totalReviewString = totalReviewString.substring(totalReviewString.indexOf(" ") + 1);
      index++;
      if (totalReviewString.indexOf(" ") == -1) {
        words[index] = totalReviewString;
        stop = true;
      }
    }
    for (int i = 0; i < words.length; i++) {
      if (words[i] != null) {
        if (words[i].indexOf("!") != -1){
          words[i] = words[i].substring(0, words[i].indexOf("!"));
        } else if (words[i].indexOf(",") != -1) {
          words[i] = words[i].substring(0, words[i].indexOf(","));
        } else if (words[i].indexOf(".") != -1) {
          words[i] = words[i].substring(0, words[i].indexOf("."));
        }
      }
      total += sentimentVal(words[i]);
    }
    return total;
  }

  public static int starRating(String fileName) {
    double sentimentOfReview = totalSentiment(fileName);
    if (sentimentOfReview > 6) return 5;
    else if (sentimentOfReview > 5) return 4;
    else if (sentimentOfReview > 3) return 3;
    else if (sentimentOfReview > 1) return 2;
    else return 1;
  }

  public static String fakeReview(String filename) {
    String totalReviewString = textToString(filename);
    String output = "";
    String temp = "";
    boolean stop = false;
  

    while (!stop) {
      temp = totalReviewString.substring(0, totalReviewString.indexOf(" ") + 1);
      totalReviewString = totalReviewString.substring(totalReviewString.indexOf(" ") + 1);
      if (totalReviewString.indexOf(" ") == -1) {
        temp = totalReviewString;
        stop = true;
      }
      if (temp.substring(0, 1).equals("*")) {
        output += randomAdjective();
        output += getPunctuation(temp);

      } else output += temp;
      
    }
    return output;

  }

  /** 
   * returns a string containing all of the text in fileName (including punctuation), 
   * with words separated by a single space 
   */
  public static String textToString( String fileName )
  {  
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));
      
      //add 'words' in the file to the string, separated by a single space
      while(input.hasNext()){
        temp = temp + input.next() + " ";
      }
      input.close();
      
    }
    catch(Exception e){
      System.out.println("Unable to locate " + fileName);
    }
    //make sure to remove any additional space that may have been added at the end of the string.
    return temp.trim();
  }
  
  /**
   * @returns the sentiment value of word as a number between -1 (very negative) to 1 (very positive sentiment) 
   */
  public static double sentimentVal( String word )
  {
    try
    {
      return sentiment.get(word.toLowerCase());
    }
    catch(Exception e)
    {
      return 0;
    }
  }
  
  /**
   * Returns the ending punctuation of a string, or the empty string if there is none 
   */
  public static String getPunctuation( String word )
  { 
    int index = word.length();
    for(int i=word.length()-1; i >= 0; i--){
      if(!Character.isLetterOrDigit(word.charAt(i))){
        index = i;
      } else break;
    }
    String punc = word.substring(index);
    return punc;
  }

      /**
   * Returns the word after removing any beginning or ending punctuation
   */
  public static String removePunctuation( String word )
  {
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(0)))
    {
      word = word.substring(1);
    }
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(word.length()-1)))
    {
      word = word.substring(0, word.length()-1);
    }
    
    return word;
  }
 
  /** 
   * Randomly picks a positive adjective from the positiveAdjectives.txt file and returns it.
   */
  public static String randomPositiveAdj()
  {
    int index = (int)(Math.random() * posAdjectives.size());
    return posAdjectives.get(index);
  }
  
  /** 
   * Randomly picks a negative adjective from the negativeAdjectives.txt file and returns it.
   */
  public static String randomNegativeAdj()
  {
    int index = (int)(Math.random() * negAdjectives.size());
    return negAdjectives.get(index);
    
  }
  
  /** 
   * Randomly picks a positive or negative adjective and returns it.
   */
  public static String randomAdjective()
  {
    boolean positive = Math.random() < .5;
    if(positive){
      return randomPositiveAdj();
    } else {
      return randomNegativeAdj();
    }
  }
}
