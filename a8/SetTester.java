/*  Student information for assignment:
 *
 *  On our honor, Elizabeth and John Henry,
 *  this programming assignment is our own work
 *  and we have not provided this code to any other student.
 *
 *  Number of slip days used: 1
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: eys275
 *  email address: elizabethsnider2011@gmail.com
 *  TA name: Henry Liu
 *
 *  Student 2
 *  UTEID: jec4968
 *  email address: johnhenrycruz@utexas.edu
 */

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

/*
 * CS 314 Students, put your results to the experiments and answers to questions
 * here: 
 * 
 * |  Text         | SortedSet | UnsortedSet | HashSet | TreeSet | Total Words | Distinct Words |
 * |---------      |-----------|-------------|---------|---------|-------------|----------------|
 * | Maupassant    | 70.14     | 17.44       | 0.36    | 0.49    | 494615      | 41248          |
 * | Frankenstein  | 3.67      | 1.32        | 0.08    | 0.09    | 78189       | 12207          |
 * | Don Quixote   | 38.01     | 12.44       | 0.29    | 0.37    | 430268      | 33464          |
 * | Metamorphosis | 0.47      | 0.47        | 0.03    | 0.04    | 25186       | 4596           |
 *  
 * What do you think the order (Big O) of the two processText methods are for each kind of Set? 
 * Assume N = total number of words in a file and M = number of distinct words in the file. 
 * M = the size of the set when finished.
 * SortedSet: O(M) * N
 * UnsortedSet: O(M) * N
 * HashSet: O(1) * N
 * TreeSet: O(log M) * N
 * 
 * What are the orders (Big O) of your add methods? 
 * O(N) in both UnsortedSet and SortedSet
 * 
 * What do you think the Big O of the HashSet and TreeSet add methods are?
 * HashSet: O(1) / TreeSet: O(log N)
 * 
 * What are the differences between HashSet and TreeSet when printing out the contents of the Set?
 * HashSet prints the contents in an unsorted order, whereas TreeSet prints the methods in random order
 * 
 * CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class:
 * It's unwise to implement all 3 because one can be used to create the two other.
 * Difference can be used the add to the original set to create the union of two sets
 * Difference can be used to find the intersection by being the part of the set that 
 * is not the difference.
 */

public class SetTester {

    public static void main(String[] args) {
    	
    	UnsortedSet<Integer> s1 = new UnsortedSet<>();
    	UnsortedSet<Integer> s2 = new UnsortedSet<>();
    	String expString = "";
    	int expInt = 0;

    	// UnsortedSet Tests
    	// test 1 UnsortedSet add
        showTestResults(s1.add(9), 1, "add method UnsorteSet");
    	
    	// test 2 UnsortedSet addAll
        s2.add(2);
        s1.addAll(s2);
        expString = "(9, 2)";
        showTestResults(s1.toString().toString().equals(expString), 2, "addAll UnsortedSet");
        
    	// test 3 UnsortedSet size 
        expInt = 2;
        showTestResults(s1.size() == expInt, 3, "size UnsortedSet");
        
    	// test 4 UnsortedSet contain
        showTestResults(s1.contains(9), 4, "contains UnsortedSet");

    	// test 5 UnsortedSet containsAll
        showTestResults(s1.containsAll(s2), 5, "containsAll UnsortedSet");
        
    	// test 6 UnsortedSet remove
        s1.remove(9);
        expString = "(2)";
        showTestResults(s1.equals(s2), 6, "remove UnsortedSet");

    	// test 7 UnsortedSet equals
        showTestResults(s1.equals(s2), 7, "equals UnsortedSet");
        
    	// test 8 UnsortedSet iterator
        Iterator<Integer> s1It= s1.iterator();
        int next = s1It.next();
        showTestResults(next == 2, 8, "iterator UnsortedSet");
        
    	// test 9 UnsortedSet clear
        s1.clear();
        showTestResults(s1.size() == 0, 9, "clear UnsortedSet");
        
    	// test 10 UnsortedSet difference
        s1.add(9);
        s1.add(2);
        ISet<Integer> s3 = s1.difference(s2);
        expString = "(9)";
        showTestResults(s3.toString().equals(expString), 10, "difference UnsortedSet");   
    	
    	// test 11 UnsortedSet intersection
        s3 = s1.intersection(s2);
        expString = "(2)";
        showTestResults(s3.toString().equals(expString), 11, "intersection UnsortedSet");
    	
    	// test 12 UnsortedSet union
        s3 = s1.union(s2);
        expString = "(9, 2)";
        showTestResults(s3.toString().equals(expString), 12, "union UnsortedSet");
        
        SortedSet<Integer> s4 = new SortedSet<>();
    	SortedSet<Integer> s5 = new SortedSet<>();
    	expString = "";
    	
        // SortedSet Tests
    	// test 1 SortedSet add
        showTestResults(s4.add(9), 1, "add method SortedSet");
    	
    	// test 2 SortedSet addAll
        s5.add(2);
        s4.addAll(s5);
        expString = "(2, 9)";
        showTestResults(s4.toString().toString().equals(expString), 2, "addAll SortedSet");
        
    	// test 3 SortedSet size 
        expInt = 2;
        showTestResults(s4.size() == expInt, 3, "size SortedSet");
        
    	// test 4 SortedSet contain
        showTestResults(s4.contains(9), 4, "contains SortedSet");

    	// test 5 SortedSet containsAll
        showTestResults(s4.containsAll(s5), 5, "containsAll SortedSet");
        
    	// test 6 SortedSet remove
        s4.remove(9);
        expString = "(2)";
        showTestResults(s4.equals(s5), 6, "remove SortedSet");

    	// test 7 SortedSet equals
        showTestResults(s4.equals(s5), 7, "equals SortedSet");
        
    	// test 8 SortedSet iterator
        Iterator<Integer> s4It= s4.iterator();
        next = s4It.next();
        showTestResults(next == 2, 8, "iterator SortedSet");
        
    	// test 9 SortedSet clear
        s4.clear();
        showTestResults(s4.size() == 0, 9, "clear SortedSet");
        
    	// test 10 SortedSet difference
        s4.add(9);
        s4.add(2);
        ISet<Integer> s6 = s4.difference(s5);
        expString = "(9)";
        showTestResults(s6.toString().equals(expString), 10, "difference SortedSet");   
    	
    	// test 11 SortedSet intersection
        s6 = s4.intersection(s5);
        expString = "(2)";
        showTestResults(s6.toString().equals(expString), 11, "intersection SortedSet");
    	
    	// test 12 SortedSet union
        s6 = s4.union(s5);
        expString = "(2, 9)";
        showTestResults(s6.toString().equals(expString), 12, "union SortedSet");

        // CS314 Students. Uncomment this section when ready to
        // run your experiments
//         try {
//         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//         }
//         catch(Exception e) {
//         System.out.println("Unable to change look and feel");
//         }
//         Scanner sc = new Scanner(System.in);
//         String response = "";
//         do {
//         largeTest();
//         System.out.print("Another file? Enter y to do another file: ");
//         response = sc.next();
//         } while( response != null && response.length() > 0
//         && response.substring(0,1).equalsIgnoreCase("y") );

    }

    // print out results of test
    private static void showTestResults(boolean passed, int testNumber, String testDescription) {
        if (passed) {
            System.out.print("Passed test ");
        } else {
            System.out.print("Failed test ");
        }
        System.out.println(testNumber + ": " + testDescription);
    }

    /*
     * Method asks user for file and compares run times to add words from file
     * to various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest() {
        System.out.println();
        System.out
                .println("Opening Window to select file. You may have to minimize other windows.");
        String text = convertFileToString();
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text, keyboard);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets(new HashSet<String>(), text, keyboard);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets(new TreeSet<String>(), text, keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for CS314
     * sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for Java
     * Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text,
            Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
            int setSize, Scanner keyboard) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString());
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);

        System.out.print("Enter y to see the contents of this set: ");
        String response = keyboard.next();

        if (response != null && response.length() > 0
                && response.substring(0, 1).equalsIgnoreCase("y")) {
            for (Object o : set)
                System.out.println(o);
        }
        System.out.println();
    }

    /*
     * Ask user to pick a file via a file choosing window and convert that file
     * to a String. Since we are evalutatin the file with many sets convert to
     * string once instead of reading through file multiple times.
     */
    private static String convertFileToString() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        // read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            try {
                Scanner s = new Scanner(new FileReader(source));

                while (s.hasNextLine()) {
                    text.append(s.nextLine());
                    text.append(" ");
                }

                s.close();
            } catch (IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            }
        }

        return text.toString();
    }
}
