package PhraseSolver;
import java.util.Scanner;
import java.io.File;
public class Board{

String phrase;
String letter;
//guessedLetters is a string that contains all the guessed letters
String guessedLetters;
boolean bankrupt;
Scanner sc = new Scanner(System.in);

//constructor
public Board() {
  phrase = loadPhrase();
  guessedLetters = ";";
  letter = "";
  bankrupt = false;
}

//accessor that generates a new phrase, essentially resets the board
public void newPhrase() {
  phrase = loadPhrase();
  guessedLetters = ";";
  letter = "";
  bankrupt = false;
}

//plays out round using other methods in the class
public void playRound(Player one, Player two, int num) {
  System.out.println("\n\n------ROUND " + num + "-----");
  boolean guessed = false;
  //repeats until the phrase is guessed
  while (!guessed) {
    //turn returns the letter guessed, which is added to the guessedLetters string
    guessedLetters += turn(one);
    printBoard(one, two);
    //only asks the player to guess the board if their letter guessed was in the phrase and if they are not bankrupt
    if (ifContains(phrase, letter) && !bankrupt) guessed = guess(one);

    //if the first player guesses correctly, then the money is reprinted and the loop ends
    if (guessed) {
      System.out.println("\n\n------CURRENT MONEY-----");
      System.out.println(one.getName() + ": $" + one.getMoney());
      System.out.println(two.getName() + ": $" + two.getMoney());
      break;
    }

    //same process as above for the second player
    guessedLetters += turn(two);
    printBoard(one, two);
    if (ifContains(phrase, letter) && !bankrupt) guessed = guess(two);
    if (guessed) {
      System.out.println("\n\n------CURRENT MONEY-----");
      System.out.println(one.getName() + ": $" + one.getMoney());
      System.out.println(two.getName() + ": $" + two.getMoney());
    }
  }

}

//goes through a turn for a player guessing a letter
public String turn(Player p) {
  Spinner spinner = new Spinner();
  //spins the spinner and checks if the player spins bankrupt
  bankrupt = spinner.spin(p);
  if (bankrupt) {
    System.out.println("\n\n\nSorry, " + p.getName() + ". You spun bankrupt and lost all your money.");
    p.setMoney(0);
    return "";
  } else {
    boolean validLetter = false;
    letter = "";
    //loops until the player inserts a valid letter
    while (!validLetter) {
      System.out.println("\nWhat letter would you like to guess?");
      letter = sc.nextLine().toLowerCase();
      if (guessedLetters.contains(letter)) System.out.println("\nThe letter " + letter + " has already been guessed.");
      else if (letter.length() > 1) System.out.println("\nPlease input only one letter.");
      else if (letter.equals("")) System.out.println("\nPlease input a letter.");
      else validLetter = true;
    }
    System.out.println("\n");
    //checks if the phrase containes the letter
    if (ifContains(phrase, letter)) {
      int count = 0;
      //counts how many of the letter is in the phrase
      for (int i = 0; i < phrase.length(); i++) {
        if (phrase.substring(i, i + 1).equals(letter)) count++;
      }
      System.out.println("The phrase contains " + count + " instance(s) of the letter " + letter + "!"); 
      System.out.println("You've earned $" + count*spinner.getMoney() + "!");
      p.setMoney(p.getMoney() + count*spinner.getMoney());
    }
    else System.out.println("Sorry. The phrase does not contain the letter " + letter + ".");
    return letter;
  }
  
}

//simple method to check if the guessed letter is in the phrase
public boolean ifContains(String p, String l) {
  boolean contain = false;
  if (p.contains(l)) contain = true;
  return contain;
}

//method to see if the player guesses the phrase
public boolean guess(Player p) {
  boolean guessCorrect = false;
  boolean isValidInput = false;
  while (!isValidInput) {
    System.out.println("\n\n\nDo you want to guess the phrase? \n[Y] Yes \n[N] No");
    String answer = sc.nextLine().toUpperCase();
    if (answer.equals("Y")) {
      System.out.println("\nEnter your guess (with no commas, apostrophes, or any other punctuation marks):");
      isValidInput = true;
      if (sc.nextLine().equals(phrase)) {
        System.out.println("That is correct!");
        System.out.println("You earn $2000!");
        p.setMoney(p.getMoney() + 2000);
        guessCorrect = true;
      } else {
        System.out.println("Incorrect.");
      }
    } else if (answer.equals("N")) {
      isValidInput = true;
    } else System.out.println("Invalid answer. Try again.");
  }
  return guessCorrect;
}

//method to print the board
public void printBoard(Player one, Player two) {
  System.out.println("\n\n\n-----CURRENT BOARD-----");
  String output = "";
  boolean inPhrase = false;
  boolean isSpace = false;
  //loops through phrase
  for (int i = 0; i < phrase.length(); i++) {
    inPhrase = false;
    isSpace = false;
    //loops through guessedLetters
    for (int j = 0; j < guessedLetters.length(); j++){
      //checks if any of the guessedLetters matches the letter of the phrase
      if (phrase.substring(i, i + 1).equals(guessedLetters.substring(j, j + 1))) inPhrase = true;
      // if not, checks if the phrase is a space
      else if (phrase.substring(i, i + 1).equals(" ")) isSpace = true;
    }
    //prints the actual letter if the letter is in the guessedLetters string
    //prints space if the letter is a space
    //prints an underscore if the letter has not been guessed
    if (inPhrase) output += phrase.substring(i, i + 1);
    else if (isSpace) output += " ";
    else output += "_";
  }
  System.out.println(output);
  //prints current money
  System.out.println("\n\n------CURRENT MONEY-----");
  System.out.println(one.getName() + ": $" + one.getMoney());
  System.out.println(two.getName() + ": $" + two.getMoney());
}

  //Returns a random phrase from the text document
private String loadPhrase()
  {
    String tempPhrase = "";
    
    int numOfLines = 0;
    tempPhrase = "how are you";
    
    try 
    {
      
      Scanner sc = new Scanner(new File(/*Replace with the path*/"/workspace/java/PhraseSolver/phrases.txt"));
      while (sc.hasNextLine())
      {
        tempPhrase = sc.nextLine().trim();
        numOfLines++;
      }
    } catch(Exception e) { System.out.println("Error reading or parsing phrases.txt"); }
    
		int randomInt = (int) ((Math.random() * numOfLines) + 1);
    
    try 
    {
      int count = 0;
      Scanner sc = new Scanner(new File(/*Replace with the path*/"/workspace/java/PhraseSolver/phrases.txt"));
      while (sc.hasNextLine())
      {
        count++;
        String temp = sc.nextLine().trim();
        if (count == randomInt)
        {
          tempPhrase = temp;
        }
      }
    } catch (Exception e) { System.out.println("Error reading or parsing phrases.txt"); }
    return tempPhrase;
  }
}