
package Unit4Activities;

/*
 * Activity 2.4.4
 * Loop to iterate through the alphabet (the outer loop)
 * Loop to iterate through all the letters of a phrase (the inner loop)
 * Counter variable to count the number of times a letter is in the phrase
 * Print statement to display the frequencies
 */
public class NestedLoopsAssignments
{
	public static void main(String[] args)
	{
		String letters = "abcdefghijklmnopqrstuvwxyz"; 
		String phrase = "This is a phrase!";

		System.out.println("The following shows the letter frequencies for the phrase");


    /* your code here */
        for (int i = 0; i < letters.length(); i++) {
            int count = 0;
            for (int j = 0; j < phrase.length(); j++) {
                if (letters.substring(i, i+1).equals(phrase.substring(j,j+1))) count++;
            }
            System.out.println("Frequency of " + letters.substring(i, i+1) + ": " + count);
        } 
	}
}
