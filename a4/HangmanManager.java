/*  Student information for assignment:
 *
 *  On my honor, Elizabeth, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Elizabeth Snider
 *  email address: elizabethsnider2011@gmail.com
 *  UTEID: eys275
 *  Section 5 digit ID: 50860
 *  Grader name: Henry Liu
 *  Number of slip days used on this assignment: 1
 */

// add imports as necessary

import java.util.Set;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Random;
import java.util.Collections;
import java.util.HashSet;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {

    // instance vars
	private ArrayList<String> dictionary = new ArrayList<String>();
	private ArrayList<String> current = new ArrayList<String>();
	private ArrayList<Character> chars = new ArrayList<Character>();
	HangmanDifficulty difficulty;
	private int max = 0;
	private boolean debug;
	private int wrongs = 0;
	private int guessed = 0;
	private StringBuilder pattern;
	private final String DASH = "-";

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn) {
    	
    	if(words == null || words.size() <= 0) {
    		throw new IllegalArgumentException("words and the size of words cannot be 0.");
    	}
    	
    	dictionary = new ArrayList<String>(words);   	
    	debug = debugOn;

    }

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * Debugging is off.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {
    	
    	if(words == null || words.size() == 0) {
    		throw new IllegalArgumentException("words and the size of words cannot be 0.");
    	}
    	
    	dictionary = new ArrayList<String>(words);	
    	debug = false;
    }


    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     * @param length The given length to check.
     * @return the number of words in the original Dictionary
     * with the given length
     */
    public int numWords(int length) {
    	int num = 0;
    	
    	for(String word : dictionary) {
    		if(word.length() == length) {
    			num++;
    		}
    	}
    	
        return num;
    }


    /**
     * Get for a new round of Hangman. Think of a round as a
     * complete game of Hangman.
     * @param wordLen the length of the word to pick this time.
     * numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the
     * player loses the round. numGuesses >= 1
     * @param diff The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {
    	
    	if(wordLen <= 0 || numGuesses < 1) {
    		 throw new IllegalArgumentException("Length of word must be greater than 0 and number"
    		 		+ " of guesses must be greater than 1.");
    	}
    	
    	max = numGuesses;
    	difficulty = diff;
    	chars = new ArrayList<Character>();
    	current = new ArrayList<String>();
    	pattern = new StringBuilder();
    	wrongs = 0;
    	guessed = 0;

    	//adds all words that are the same length as wordLen to the current words list
    	for(String word: dictionary) {
    		if(word.length() == wordLen) {
    			current.add(word);
    		}
    	}
    	
    	//creates a String of dashes based on how long wordLen is
    	for(int i = 0; i < wordLen; i++) {
    		pattern.append(DASH);
    	}	
    	
    }


    /**
     * The number of words still possible (live) based on the guesses so far.
     *  Guesses will eliminate possible words.
     * @return the number of words that are still possibilities based on the
     * original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return current.size();
    }


    /**
     * Get the number of wrong guesses the user has left in
     * this round (game) of Hangman.
     * @return the number of wrong guesses the user has left
     * in this round (game) of Hangman.
     */
    public int getGuessesLeft() {
        return max - wrongs;
    }


    /**
     * Return a String that contains the letters the user has guessed
     * so far during this round.
     * The characters in the String are in alphabetical order.
     * The String is in the form [let1, let2, let3, ... letN].
     * For example [a, c, e, s, t, z]
     * @return a String that contains the letters the user
     * has guessed so far during this round.
     */
    public String getGuessesMade() {
        return chars.toString();
    }


    /**
     * Check the status of a character.
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman,
     * false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
    	if(chars.contains(guess)) {
    		return true;
    	}
    	
        return false;
    }


    /**
     * Get the current pattern. The pattern contains '-''s for
     * unrevealed (or guessed)
     * characters and the actual character for "correctly guessed" characters.
     * @return the current pattern.
     */
    public String getPattern() {
        return pattern.toString();
    }


    /**
     * Update the game status (pattern, wrong guesses, word list),
     * based on the give guess.
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     * words in each of the new patterns.
     * The return value is for testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {
    	if(alreadyGuessed(guess)) {
    		throw new IllegalArgumentException("Letter has already been guessed.");
    	}
    	
    	//creates TreeMaps for different families and pattern counts
    	TreeMap<String, ArrayList<String>> families = new TreeMap<String, ArrayList<String>>();
    	TreeMap<String, Integer> patternCount = new TreeMap<String, Integer>();
    	chars.add(guess);
    	Collections.sort(chars);
    	guessed++;
    	
    	Set<String> possible = new HashSet<String>();
    	
    	//adds possible patterns with the guessed char into a set
    	for(String word : current) {
    		String wordPattern = generatePattern(word, guess);
    		possible.add(wordPattern);
    	}
    	
    	//sorts patterns, words, and number of patterns into the families and patternCount TreeMaps
    	transfer(possible, guess, families, patternCount);
    	
    	//based on chosen difficulty, chooses hardest or second hardest pattern
    	StringBuilder updated = new StringBuilder();
    	updated = difficulties(patternCount, updated);
    	
    	//if no more letters are revealed, counts the guess as wrong
    	if(updated.toString().equals(pattern.toString())) {
    		wrongs++;
    	} else {
    		pattern = updated;
    	}
    	
    	//updates list of potential words
    	current = families.get(getPattern());
    	
    	//for debugging
    	int hard = 0;
    	debugHard(hard, patternCount);
    	
    	return patternCount;
    }
    
    //helper method for printing out debugging
    //@param passes int for pick difficulty, 0 for hardest, 1 for second hardest
    public void debugHard(int hard, TreeMap<String, Integer> patternCount) {
    	if(debug) {
    		if(difficulty == HangmanDifficulty.EASY) {
        		if((guessed % 2) == 0) {
        			hard = 1;
        		}
        	} else if(difficulty == HangmanDifficulty.MEDIUM) {
        		if((guessed % 4) == 0) {
        			hard = 1;
        		} 
        	}
        	if(patternCount.size() == 1 && hard == 1) {	
        			System.out.println("\nDEBUGGING: Should pick second hardest "
        					+ "pattern this turn, "
        					+ "but only one pattern available.\n");
        			System.out.println("DEBUGGING: Picking hardest list.");
        			System.out.println("DEBUGGING: New pattern is: " + pattern.toString() + 
        					". New family has " + numWordsCurrent() + " words.\n");	
        	} else if(hard == 1) {
            		System.out.println("\nDEBUGGING: Difficulty second hardest pattern "
            				+ "and list.\n");
            		System.out.println("DEBUGGING: New pattern is: " + pattern.toString() + 
        					". New family has " + numWordsCurrent() + " words.\n");	
        	} 
        	if(hard == 0) {
    			System.out.println("\nDEBUGGING: Picking hardest list.");
    			System.out.println("DEBUGGING: New pattern is: " + pattern.toString() + 
    					". New family has " + numWordsCurrent() + " words.\n");
        	}
    	}

    }
    
    //chooses the hardest pattern based on the chosen difficulty
    //calls on the hardest or secondHardest helper methods
    //@param passes the patternCount TreeMap and updated pattern StringBuilder
    public StringBuilder difficulties(TreeMap<String, Integer> patternCount, 
    		StringBuilder updated) {
    	if(difficulty == HangmanDifficulty.EASY) {
    		if((guessed % 2) == 0) {
    			updated = new StringBuilder(secondHardest(patternCount));
    		} else {
    			updated = new StringBuilder(hardest(patternCount));
    		}
    	} else if(difficulty == HangmanDifficulty.MEDIUM) {
    		if((guessed % 4) == 0) {
    			updated = new StringBuilder(secondHardest(patternCount));
    		} else {
    			updated = new StringBuilder(hardest(patternCount));
    		}
    	} else if(difficulty == HangmanDifficulty.HARD) {
    		updated = new StringBuilder(hardest(patternCount));
    	}
    	
    	return updated;
    }   
    
    //returns the hardest pattern by choosing the one(s) with the most counted
    //if there are more than one, calls on the tie helper method
    //@param passes the patternCount TreeMap
    public String hardest(TreeMap<String, Integer> patternCount) {
    	int max = Collections.max(patternCount.values());
    	ArrayList<String> patterns = new ArrayList<String>();
    	
    	//adds all patterns with the largest count to an ArrayList
    	for(String pattern : patternCount.keySet()) {
    		if(patternCount.get(pattern) == max) {
    			patterns.add(pattern);
    		}
    	}
    	
    	String hardestPattern = patterns.get(0);
    	
    	//calls on the tie helper method to choose the hardest pattern
    	if(patterns.size() > 1) {
    		hardestPattern = tie(patterns);
    	}
    	
    	return hardestPattern;
    }
    
    //uses lexicographical ordering to distinguish hardest pattern
    //@param passes the patterns Arraylist from the hardest method
    public String tie(ArrayList<String> patterns) {
    	int max = 0;
    	String temp = "";
    	
    	for(String pattern : patterns) {
    		int dashes = 0;
    		
    		for(int i = 0; i < pattern.length(); i++) {
    			if(pattern.charAt(i) == DASH.charAt(0)) {
    				dashes++;
    			}
    		}
    		
    		if(dashes > max) {
    			max = dashes;
    			temp = pattern;
    		}
    	}
    	
    	return temp;
    }
    
    //chooses the second hardest pattern by removing the hardest pattern and
    //chooses the new hardest pattern from the new list
    //@param passes the patternCount TreeMap
    public String secondHardest(TreeMap<String, Integer> patternCount) {
    	String hardestPattern = hardest(patternCount);
    	String second = "";
    	TreeMap<String, Integer> secondList;
    	
    	if(patternCount.size() == 1) {
    		second = hardestPattern;
    		
    	} else {
    		secondList = new TreeMap<String, Integer>();
    		secondList.putAll(patternCount);
    		secondList.remove(hardestPattern);
    		second = hardest(secondList);
    	} 	
    	
    	return second;
    }
    
    //returns pattern based on guessed char and previous pattern
    public String generatePattern(String word, char guess) {
    	StringBuilder temp = new StringBuilder();

    	for(int i = 0; i < word.length(); i++) {
    		if(word.charAt(i) == guess) {
    			temp.append(guess);
    		} else {
    			temp.append(pattern.charAt(i));
    		}
    	}

    	return temp.toString();
    }
    
    //puts patterns (keys) and list of words for each (values) into families TreeMap
    //puts patterns (keys) and number of each (values) into patternCount TreeMap
    public void transfer(Set<String> possible, char guess, TreeMap<String, 
    		ArrayList<String>> families,
    		TreeMap<String, Integer> patternCount) {
    	for(String pattern : possible) {
    		ArrayList<String> addWords = new ArrayList<String>();
    		
    		for(String word : current) {
    			String pat = generatePattern(word, guess);
    			
    			if(pat.equals(pattern)) {
    				addWords.add(word);
    			}
    		}
    		
    		families.put(pattern, addWords);
    		patternCount.put(pattern, addWords.size());
    	}
    }

    /**
     * Return the secret word this HangmanManager finally ended up
     * picking for this round.
     * If there are multiple possible words left one is selected at random.
     * <br> pre: numWordsCurrent() > 0
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
        if(numWordsCurrent() == 0) {
        	throw new IllegalArgumentException("numWordsCurrent cannot be 0.");
        }
        
        Random r = new Random();
        int index = 0;
        
        if(current.size() == 1) {
        	return current.get(0);
        } else {
        	index = r.nextInt(current.size());
        }
        
        return current.get(index);
    }
}
