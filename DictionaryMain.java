import java.io.*;
import java.util.*;

/**
*Class that initialises a Dictionary, fills it with words from a text file
*and allows user to choose in the terminal which operations to carry out.
*
*@author Lara Okafor
*@version 01.10.2017
*/
class DictionaryMain {
    public static void main(String[] args) throws FileNotFoundException {
		Scanner a = new Scanner(new File("dictionary.txt"));
		Dictionary d = new Dictionary();

		while(a.hasNextLine()) {
		    d.insert(a.nextLine());
		}

		a.close();

		while(true) {
			System.out.print("\nPlease enter number:\n1. To search for word.\n2. To print first " 
				             + "and last word in Dictionary.\n3. To print tree depth."
				              + "\n4. To print amount of nodes at each depth and average" 
				              + " depth of nodes.\n\nEnter 'q' to quit.\n");
		    
		    Scanner b = new Scanner(System.in);
		    String word = b.nextLine();

		    if(word.equals("q")) {
				b.close();
				break;
		    }
		    else if(word.equals("1")) {
		    	System.out.print("\nEnter word to be looked up (enter q to quit): ");
		    	word = b.nextLine();
		    	System.out.println("");
		    	d.search(word);
		    }
		    else if(word.equals("2")) {
				System.out.println("\n" + d.firstLast());
		    }
		    else if(word.equals("3")) {
				System.out.println("\nTree depth: " + d.findTreeDepth() + "\n");
		    }
		    else if(word.equals("4")) {
				d.depthArray();
		    }
		    else {
		    	System.out.println("Option not recognised, please enter a valid option.");
		    }
		}
    }
}
