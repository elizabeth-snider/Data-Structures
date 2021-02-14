/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor, Elizabeth, this programming assignment is my own work 
 * and I have not provided this code
 * to any other student. 
 * UTEID: eys275
 * email address: elizabethsnider2011@gmail.com
 * Number of slip days I am using: 0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NameSurfer {

    // CS314 students, explain your menu option 7 here:
	/* My menu 7 option displays the names that have permutations
	 * (or are permutations of one another), meaning they contain the same
	 * letters but rearranged. The method converts each name String into char
	 * arrays, sorts them, and then checks for ones that are equal.
	*/
    // CS314 students, Explain your interesting search / trend here:
	/* During the 1900s, girl names that began with "Ad" were either popular only
	 * during the first half of the century or only the second half of the century.
	 * Most were not ranked during their unpopular half of the century (contained
	 * all zeros)
	 */
    // CS314 students, add test code for NameRecord class here:
	/*public static void test() {
		String data = "Jake 262 312 323 479 484 630 751 453 225 117 97";
		NameRecord record = new NameRecord(new Scanner(data), 1900, 11);
		int expectBestDec = 10;
		int actualBestDec = record.bestDecade();
		//tests for increasing in popularity
		boolean expectedBoo = false;
		boolean actualBoo = record.morePop();
		if (expectBestDec == actualBestDec && expectedBoo == actualBoo)
			System.out.println("passed the test!");
		System.out.println("failed the test");
			
	}*/

    // main method. Driver for the whole program
    public static void main(String[] args) {
        // delete the following line in the final version of your program.
        final String NAME_FILE = "names.txt";
        Scanner fileScanner = getFileScannerForNames(NAME_FILE);
        Names namesDatabase = new Names(fileScanner);
        fileScanner.close();
        runOptions(namesDatabase);
    }

    // pre: namesDatabase != null
    // ask user for options to perform on the given Names object.
    // Creates a Scanner connected to System.in.
    private static void runOptions(Names namesDatabase) {
        Scanner keyboard = new Scanner(System.in);
        MenuChoices[] menuChoices = MenuChoices.values();
        MenuChoices menuChoice;
        do {
            showMenu();
            int userChoice = getChoice(keyboard) - 1;
            menuChoice = menuChoices[userChoice];
            if(menuChoice == MenuChoices.SEARCH) {
                search(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.ONE_NAME) {
                oneName(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.APPEAR_ONCE) {
                appearOnce(namesDatabase);
            } else if (menuChoice == MenuChoices.APPEAR_ALWAYS) {
                appearAlways(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_MORE) {
                alwaysMore(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_LESS) {
                alwaysLess(namesDatabase);
            } else if (menuChoice == MenuChoices.STUDENT_SEARCH) {
                permutations(namesDatabase);
            }
        } while(menuChoice != MenuChoices.QUIT);
        keyboard.close();
    }

    // pre: fileName != null
    // create a Scanner and return connected to a File with the given name.
    private static Scanner getFileScannerForNames(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Problem reading the data file. Returning null for Scanner"
                    + "object. Problems likely to occur." + e);
        }
        return sc;
    }

    // method that shows names that have appeared in ever decade
    // pre: n != null
    // post: print out names that have appeared in ever decade
    private static void appearAlways(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }
        
        ArrayList<String> names = namesDatabase.rankedEveryDecade();
        
        System.out.println(names.size() + " names appear in every decade. The names are: ");
		for(int length = 0; length < names.size(); length++) {
			System.out.println(names.get(length));
		}	
    }

    // method that shows names that have appeared in only one decade
    // pre: n != null
    // post: print out names that have appeared in only one decade
    private static void appearOnce(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }
        
        ArrayList<String> names = namesDatabase.rankedOnlyOneDecade();
        
        System.out.println(names.size() + " names appear in exactly one decade. The names are: ");
		for(int length = 0; length < names.size(); length++) {
			System.out.println(names.get(length));
		}
        
    }
    
    // method that shows names that have gotten more popular 
    // in each successive decade
    // pre: n != null
    // post: print out names that have gotten more popular in each decade
    private static void alwaysMore(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }
        
        ArrayList<String> names = namesDatabase.alwaysMorePopular();
        
        System.out.println(names.size() + " names are more popular in every decade.");
		for(int length = 0; length < names.size(); length++) {
			System.out.println(names.get(length));
		} 
    }
    
    // method that shows names that have gotten less popular 
    // in each successive decade
    // pre: n != null
    // post: print out names that have gotten less popular in each decade
    private static void alwaysLess(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }
        
        ArrayList<String> names = namesDatabase.alwaysLessPopular();
        
        System.out.println(names.size() + " names are less popular in eveyr decade.");
		for(int length = 0; length < names.size(); length++) {
			System.out.println(names.get(length));
		}       
    }

    // method that shows data for one name, or states that name has never been
    // ranked
    // pre: n != null, keyboard != null and is connected to System.in
    // post: print out the data for n or a message that n has never been in the
    // top 1000 for any decade
    private static void oneName(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        
        System.out.print("Enter a name: ");
        String input = keyboard.next();
        keyboard.nextLine();
        System.out.println();

        if(namesDatabase.getName(input) == null) {
        	System.out.println(input + " does not appear in any decade.");
        } else {
        	System.out.println(namesDatabase.getName(input).toString());
        }
    }

    // method that shows all names that contain a substring from the user
    // and the decade they were most popular in
    // pre: n != null, keyboard != null and is connected to System.in
    // post: show the correct data
    private static void search(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        
        System.out.print("Enter a partial name: ");
        String input = keyboard.next();
        keyboard.hasNextLine();
        System.out.println();
        
        int count = namesDatabase.getMatches(input).size();
        System.out.println("There are " + count + " matches for " + input + ".\n");
        if(count > 0) {
        	System.out.println("The matches with their highest ranking decade are: ");
        	for(int length = 0; length < count; length++) {
        		System.out.println(namesDatabase.getMatches(input).get(length).getName() +
        				" " + namesDatabase.getMatches(input).get(length).bestDecade());
        	}
        }
        
    }
    
    //method that displays count and names of NameRecord objects that have permutations
    //pre: namesDatabase != null
    private static void permutations(Names namesDatabase) {
    	if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }
        
        ArrayList<String> names = namesDatabase.getPerms();
        System.out.println(names.size() + " names have permutations.");
		for(int length = 0; length < names.size(); length++) {
			System.out.println(names.get(length));
		} 
    }

    // get choice from the user
    // keyboard != null and is connected to System.in
    // return an int that is >= SEARCH and <= QUIT
    private static int getChoice(Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (keyboard == null) {
            throw new IllegalArgumentException("The parameter keyboard cannot be null");
        }
        int choice = getInt(keyboard, "Enter choice: ");
        keyboard.nextLine();
        // add one due to zero based indexing of enums, but 1 based indexing of menu
        final int MAX_CHOICE = MenuChoices.QUIT.ordinal() + 1;
        while (choice < 1  || choice > MAX_CHOICE) {
            System.out.println();
            System.out.println(choice + " is not a valid choice");
            choice = getInt(keyboard, "Enter choice: ");
            keyboard.nextLine();
        }
        return choice;
    }

    // ensure an int is entered from the keyboard
    // pre: s != null and is connected to System.in
    private static int getInt(Scanner s, String prompt) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (s == null) {
            throw new IllegalArgumentException("The parameter s cannot be null");
        }
        System.out.print(prompt);
        while (!s.hasNextInt()) {
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }

    // show the user the menu
    private static void showMenu() {
        System.out.println();
        System.out.println("Options:");
        System.out.println("Enter 1 to search for names.");
        System.out.println("Enter 2 to display data for one name.");
        System.out.println("Enter 3 to display all names that appear in only "
                + "one decade.");
        System.out.println("Enter 4 to display all names that appear in all "
                + "decades.");
        System.out.println("Enter 5 to display all names that are more popular "
                + "in every decade.");
        System.out.println("Enter 6 to display all names that are less popular "
                + "in every decade.");
        System.out.println("Enter 7 to display all names that have permutations.");
        System.out.println("Enter 8 to quit.");
        System.out.println();
    }

}
