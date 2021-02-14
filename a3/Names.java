/*  Student information for assignment:
*
*  On my honor, Elizabeth, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: eys275
*  email address: elizabethsnider2011@gmail.com
*  Number of slip days I am using: 0
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
* A collection of NameRecords. 
* Stores NameRecord objects and provides methods to select
* NameRecords based on various criteria.
*/
public class Names {
	
	public int baseYear = 0;
	public int numDecades = 0;
	private ArrayList<NameRecord> records = new ArrayList<NameRecord>();
    
    /**
     * Construct a new Names object based on the data source the Scanner 
     * sc is connected to. Assume the first two lines in the 
     * data source are the base year and number of decades to use.
     * Any lines without the correct number of decades are discarded 
     * and are not part of the resulting Names object. 
     * Any names with ranks of all 0 are discarded and not
     * part of the resulting Names object.
     * @param sc Is connected to a data file with baby names 
     * and positioned at the start of the data source.
     */
    public Names(Scanner sc) {
    	baseYear = sc.nextInt();
    	numDecades = sc.nextInt();
    	sc.nextLine();
    	String line = "";
    	
    	//passes a scanner of each line of the file to create a new NameRecord object
    	//only adds NameRecord to the records ArrayList if it contains the correct 
    	//number of decades and does not contain all zeros
    	while(sc.hasNextLine()) {
    		if(sc.hasNextLine()) {
    			line = sc.nextLine();
    			Scanner scan = new Scanner(line);
    			NameRecord data = new NameRecord(scan, baseYear, numDecades);
    			
    			Scanner scanLine = new Scanner(line);
    			scanLine.next();
    			int count = 0;
    			int zeros = 0;
    				
    			while(scanLine.hasNextInt()) {
    				if(scanLine.hasNextInt()) { 
    					count++;
    					if(scanLine.nextInt() == 0) {
    						zeros++;
    					}
    				} 
    			}
    			
    			if(count == numDecades && zeros != numDecades) {
    				records.add(data);
    			}
    		}
    	}
    		
    	Collections.sort(records);
        
    }

   /**
    * Returns an ArrayList of NameRecord objects that contain a 
    * given substring, ignoring case.  The names must be in sorted order based 
    * on name.
    * @param partialName != null, partialName.length() > 0
    * @return an ArrayList of NameRecords whose names contains
    * partialName. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   public ArrayList<NameRecord> getMatches(String partialName) {
	   if(partialName == null || partialName.length() <= 0) {
		   throw new IllegalArgumentException("partialName cannot be null or have a length of 0");
	   }
	   
       String lower = partialName.toLowerCase();
       ArrayList<NameRecord> names = new ArrayList<NameRecord>();
       
       //return names that contain the passed partialName by going through
       //the ArrayList, and converting both to lower case
       for(int length = 0; length < records.size(); length++) {
    	   NameRecord record = records.get(length);
    	   String name = record.getName();
    	   
    	   if(name.toLowerCase().contains(lower)) {
    		   names.add(record);
    	   }
       }
       
       Collections.sort(names);
       return names;
   }

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the
    * top 1000 or better for every decade. The Strings  must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   
   public ArrayList<String> rankedEveryDecade() {
       ArrayList<String> names = new ArrayList<String>();
       
       //calls on a method in NameRecord.java to check if some names
       //are ranked above 1000 in every decade
       for(int length = 0; length < records.size(); length++) {
    	   if(records.get(length).inEvery()) {
    		   names.add(records.get(length).getName());
    	   }
       }
       
       return names;
   }
   

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the 
    * top 1000 or better in exactly one decade. The Strings must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in exactly one decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   public ArrayList<String> rankedOnlyOneDecade() {
       ArrayList<String> names = new ArrayList<String>();
       
       //calls on a method in NameRecord.java to check for names
       //that are ranked in only one decade
       for(int length = 0; length < records.size(); length++) {
    	   if(records.get(length).once()) {
    		   names.add(records.get(length).getName());
    	   }
       }
       
       return names;
   }

   /**
    * Returns an ArrayList of Strings of names that have been getting more
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting more popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   public ArrayList<String> alwaysMorePopular() {
	   ArrayList<String> names = new ArrayList<String>();
       
	   //calls on a method in NameRecord.java to check for names 
	   //that increase in popularity each decade
       for(int length = 0; length < records.size(); length++) {
    	   if(records.get(length).morePop()) {
    		   names.add(records.get(length).getName());
    	   }
       }
       
       return names;
   }

   /**
    * Returns an ArrayList of Strings of names that have been getting less
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting less popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   public ArrayList<String> alwaysLessPopular() {
	   ArrayList<String> names = new ArrayList<String>();
       
	   //calls on a method in NameRecord.java to check for names 
	   //that decrease in popularity each decade
       for(int length = 0; length < records.size(); length++) {
    	   if(records.get(length).lessPop()) {
    		   names.add(records.get(length).getName());
    	   }
       }
       
       return names;
   }
   
   /**
    * Return the NameRecord in this Names object that matches the given String.
    * <br>
    * <tt>pre: name != null</tt>
    * @param name The name to search for.
    * @return The name record with the given name or null if no NameRecord in this Names
    * object contains the given name.
    */
   public NameRecord getName(String name) {
       if(name == null) {
           throw new IllegalArgumentException("The parameter name cannot be null");
       }
        
       //returns data on the passed name String
       for(int length = 0; length < records.size(); length++) {
    	   if(records.get(length).getName().equalsIgnoreCase(name)) {
    		   return records.get(length);
    	   }
       }
       
       return null;
   }
   
   /*
    * Returns the ArrayList of Strings of names that have permutations/
    * are permutations of one another. This means they contain the same letters,
    * but rearranged. The list is in sorted ascending order and returns an
    * empty list if no NameRecords meet the criteria.
    */
   public ArrayList<String> getPerms() {
	   ArrayList<String> names = new ArrayList<String>();
	   
	   for(int length = 0; length < records.size(); length++) {
		   String name = records.get(length).getName();
		   if(hasPerm(name)) {
			   names.add(name);
		   }
	   }
	   
	   return names;
   }
   
   //helper method for getPerms that converts each name into a char array
   //and sorts them, allowing them to be compared for equality.
   //returns true if a permutation is found.
   //@param passes the String name being compared
   public boolean hasPerm(String name) {
	   for(int length = 0; length < records.size(); length++) {
		   char[] original = name.toCharArray();
		   char[] other = records.get(length).getName().toCharArray();
		   Arrays.sort(original);
		   Arrays.sort(other);
		   if(original.equals(other)){
			   return true;
		   }
	   }
	   
	   return false;
   }
   
}
