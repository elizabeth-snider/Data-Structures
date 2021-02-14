import java.util.ArrayList;
import java.util.Scanner;

public class NameRecord implements Comparable<NameRecord>{
	
	String name = "";
	ArrayList<Integer> records = new ArrayList<Integer>();
	int base = 0;
	int decades = 0;
	public static final int MULTIPLIER = 10;
	public static final int TOP = 1000;
	
	//creates NameRecord object based on passed parameters
	//@param Scanner for passed line of data, base year, and 
	//number of decades of data
	public NameRecord(Scanner line, int baseYear, int numDecades) {
		name = line.next();
		decades = numDecades;
		base = baseYear;
		records = new ArrayList<Integer>(decades);
		
		while(line.hasNextInt()) {
			if(line.hasNextInt()) {
				records.add(line.nextInt());
			}
		}	
	}
	
	//returns name of current line
	public String getName() {
		return name;
	}
	
	//returns base year of current data file
	public int getBase() {
		return base;
	}
	
	//returns rank of given index
	//@param passes rank index
	public int getRank(int rank) {
		return records.get(rank);
	}
	
	//returns decade of highest rank in given line
	public int bestDecade() {
		int best = 0;
		
		for(int length = 0; length < records.size(); length++) {
			if(records.get(length) >= best) {
				best = records.get(length);
			}
		}
		
		return base + (best * MULTIPLIER);
	}
	
	//returns number of decades in line of data that are ranked above 1000
	public int numDecades() {
		int num = 0;
		
		for(int length = 0; length < records.size(); length++) {
			if(records.get(length) <= TOP && records.get(length) != 0){
				num++;
			}
		}
		
		return num;
	}
	
	//returns true if every decade in line of data is ranked above 1000
	public boolean top() {
		int num = numDecades();
		
		if(num == decades) {
			return true;
		}
		
		return false;
	}
	
	//returns true if only one decade in line of data is ranked above 1000
	public boolean once() {
		int count = numDecades();
		
		if(count == 1) {
			return true;
		}
		
		return false;
	}
	
	//returns true if each decade increasingly becomes more popular
	public boolean morePop() {
		for(int length = decades - 1; length > 0; length--) {
			if(records.get(length) >= records.get(length - 1) || records.get(length) == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	//returns true if each decade decreases in popularity
	public boolean lessPop() {
		boolean less = true;
		
		for(int length = 1; length < records.size(); length++) {
			int temp = records.get(length - 1);
			if((records.get(length) <= temp && records.get(length) != 0) || 
					(temp == 0)) {
				less = false;
				length = records.size();
			}
		}
		
		return less;
	}
	
	//returns String version of NameRecord data
	public String toString() {
		String line = name + "\n";
		
		for(int length = 0; length < records.size(); length++) {
			line += (base + length * MULTIPLIER) +  ": " + records.get(length) + "\n";
		}
		
		return line;
	}
	
	//method implemented from the Comparable interface, used to compare NameRecord objects
	//based on the Strings that represent their names
	//@param passes NameRecord object
	public int compareTo(NameRecord records) {
		return this.name.compareTo(records.name);
	}
	
	//helper method that returns true if every decade in a line of data is ranked
	public boolean inEvery() {
		int count = 0;
		
		for(int length = 0; length < records.size(); length++) {
			if(records.get(length) <= 1000 && records.get(length) != 0) {
				count++;
			}
		}
		
		if(count == records.size()) {
			return true;
		} 
		
		return false;
	}
}
