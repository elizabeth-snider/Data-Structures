/* Student information for assignment:
 *
 *  On my honor, Elizabeth, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: eys275
 *  email address: elizabethsnider2011@gmail.com
 *  TA name: Henry Liu
 *  Number of slip days I am using: 1
 */


import java.util.*;

public class AnagramSolver {

    //map with all the words in the dictionary and their respective letter inventories
    private HashMap<String, LetterInventory> words;

    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException("dictionary cannot be null");
        }

        words = new HashMap<String, LetterInventory>();

        //adds all the words and their respective letter inventories
        for (String word : dictionary) {
            words.put(word, new LetterInventory(word));
        }
    }

    /*
     * pre: maxWords >= 0, s != null, s contains at least one
     * English letter.
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        LetterInventory inventory = new LetterInventory(s);

        if (s == null || s.length() < 1 || maxWords < 0) {
            throw new IllegalArgumentException("parameters violate preconditions");
        }

        if (inventory.isEmpty()) {
            throw new IllegalArgumentException("s must contain at least one English letter");
        }

        ArrayList<List<String>> result = new ArrayList<List<String>>();
        ArrayList<String> anagrams = new ArrayList<String>();
        ArrayList<String> possibilities = new ArrayList<String>();
        int index = 0;

        //adds all the possible words based on s's letter inventory
        possibilities = possibleWords(inventory);

        //if max words is zero, sets it to where it does not have limit
        if (maxWords == 0) {
            maxWords = possibilities.size() - 1;
        }

        //call recursive helper method
        recursiveGet(result, possibilities, anagrams, inventory, maxWords, index);

        //sort using nested class comparator
        Collections.sort(result, new compareAnagrams());
        return result;
    }

    //helper method that adds all possible words from the dictionary into an ArrayList
    //@param passes the LetterInventory of the String s and the ArrayList of possible words itself
    private ArrayList<String> possibleWords(LetterInventory inventory) {
        ArrayList<String> possible = new ArrayList<String>();

        for (String word : words.keySet()) {
            //adds the word if it contains the LetterInventory of String s
            if (inventory.subtract(words.get(word)) != null) {
                possible.add(word);
            }
        }

        return possible;
    }

    //recursive helper method to add the list of anagrams to the result
    //@param passes the result, list of possible words, list of anagrams that we are finding,
    //the LetterInventory of String s, the max number of words, and index
    private void recursiveGet(ArrayList<List<String>> result, ArrayList<String> possibilities,
                              ArrayList<String> anagrams, LetterInventory inventory, int maxWords,
                              int index) {
        //base case: if LetterInventory reaches zero, add anagram list to result
        //sort words in list in lexicographical order
        if (inventory.isEmpty()) {
            ArrayList<String> resultAnagrams = new ArrayList<String>(anagrams);
            Collections.sort(resultAnagrams);
            result.add(resultAnagrams);
            //continues until maxWords reaches zero
        } else if (maxWords != 0) {
            for (int i = index; i < possibilities.size(); i++) {
                //subtract letter we are looking at from the current LetterInventory
                LetterInventory newInv = inventory.subtract(words.get(possibilities.get(i)));

                //if the new LetterInventory is not null, runs recursion and backtracking
                if (newInv != null) {
                    String word = possibilities.get(i);
                    anagrams.add(word);
                    recursiveGet(result, possibilities, anagrams, newInv, maxWords - 1, i);
                    anagrams.remove(word);
                }
            }
        }
    }

    //nested class for the Comparator interface
    private static class compareAnagrams implements Comparator<List<String>> {

        //method that compares anagrams and sorts them according to
        //lists with less words first
        public int compare(List<String> anaOne, List<String> anaTwo) {
            if (anaOne.size() != anaTwo.size()) {
                return anaOne.size() - anaTwo.size();
            } else {
                for (int i = 0; i < anaOne.size(); i++) {
                    int result = anaOne.get(i).compareTo(anaTwo.get(i));

                    if (result != 0) {
                        return result;
                    }
                }

                return 0;
            }
        }
    }
}
