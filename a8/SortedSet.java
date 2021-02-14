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

import java.util.IllformedLocaleException;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 * 
 * The data type for E must be a type that implements Comparable.
 * 
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must be
 * used as the internal storage container. For methods involving two set, if
 * that method can be done more efficiently if the other set is also a SortedSet
 * do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

	private ArrayList<E> myCon;

	/**
	 * create an empty SortedSet
	 */
	public SortedSet() {
		myCon = new ArrayList<E>();
	}

	/**
	 * create a SortedSet out of an unsorted set. <br>
	 * 
	 * @param other != null
	 */
	//O(NlogN)
	public SortedSet(ISet<E> other) {
		if (other == null) {
			throw new IllegalArgumentException("other cannot be null");
		}

		myCon = new ArrayList<E>();

		for (E value : other) {
			myCon.add(value);
		}

		//calls helper method to merge sort this and other
		//O(NlogN)
		mergeSort();
		SortedSet<E> sorted = new SortedSet<E>();

		for (E value : myCon) {
			sorted.add(value);
		}
	}

	//helper method for the SortedSet constructor to merge sort
	//this and other
	private void mergeSort() {
		ArrayList<E> temp = new ArrayList<E>();
		for (int index = 0; index < myCon.size(); index++) {
			temp.add(null);
		}
		//calls another helper method to do the sorting and recursion
		sort(temp, 0, myCon.size() - 1);
	}

	//helper method for mergesort
	private void sort(ArrayList<E> temp, int low, int high) {
		if (low < high) {
			int center = (low + high) / 2;
			//recursion to keep splitting the objects
			sort(temp, low, center);
			sort(temp, center + 1, high);
			//another helper method
			merge(temp, low, center + 1, high);
		}

	}

	//another helper method for mergesort that actually merges the data
	//into one ArrayList
	//@param passes the temp ArrayList, left position, right position, and right
	//end of the data
	private void merge(ArrayList<E> temp, int leftPos, int rightPos,
			int rightEnd) {
		int leftEnd = rightPos - 1;
		int tempPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		while (leftPos <= leftEnd && rightPos <= rightEnd) {
			if (myCon.get(leftPos).compareTo(myCon.get(rightPos)) <= 0) {
				temp.set(tempPos, myCon.get(leftPos));
				leftPos++;
			} else {
				temp.set(tempPos, myCon.get(rightPos));
				rightPos++;
			}
			tempPos++;
		}

		while (leftPos <= leftEnd) {
			temp.set(tempPos, myCon.get(leftPos));
			tempPos++;
			leftPos++;
		}

		while (rightPos <= rightEnd) {
			temp.set(tempPos, myCon.get(rightPos));
			tempPos++;
			rightPos++;
		}

		for (int i = 0; i < numElements; i++, rightEnd--) {
			myCon.set(rightEnd, temp.get(rightEnd));
		}
	}

	/**
	 * Return the smallest element in this SortedSet. <br>
	 * pre: size() != 0
	 * 
	 * @return the smallest element in this SortedSet.
	 */
	public E min() {
		if (size() == 0) {
			throw new IllegalArgumentException("size cannot be zero");
		}

		return myCon.get(0);
	}

	/**
	 * Return the largest element in this SortedSet. <br>
	 * pre: size() != 0
	 * 
	 * @return the largest element in this SortedSet.
	 */
	public E max() {
		if (size() == 0) {
			throw new IllegalArgumentException("size cannot be zero");
		}

		return myCon.get(size() - 1);
	}

	/**
	 * Add an item to this set. <br>
	 * item != null
	 * 
	 * @param item the item to be added to this set. item may not equal null.
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise.
	 */
	//O(N)
	public boolean add(E item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot be null");
		}

		//adds item to end if ArrayList is empty, compares item to single element if
		//the list only has one element, or adds item to its designated spot respectively
		//only adds if item does not already exist
		if (myCon.size() == 0) {
			myCon.add(item);
			return true;
		} else if (myCon.size() == 1 && !myCon.contains(item)) {
			if (myCon.get(0).compareTo(item) > 0) {
				myCon.add(0, item);

			} else {
				myCon.add(item);
			}
			return true;
		} else if (!myCon.contains(item)) {
			for (int index = 0; index < myCon.size(); index++) {
				if (item.compareTo(myCon.get(index)) < 0) {
					myCon.add(index, item);
					return true;
				}
			}
			myCon.add(item);
			return true;
		}
		return false;
	}

	/**
	 * A union operation. Add all items of otherSet that are not already present
	 * in this set to this set.
	 * 
	 * @param otherSet != null
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise.
	 */
	//O(N)
	public boolean addAll(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet cannot be null");
		}

		//if otherSet is an instance of SortedSet, uses an algorithm similar to merge
		//in mergesort to add elements to their respective spots
		//otherwise calls the addAll() in AbstractSet
		if (otherSet instanceof SortedSet<?>) {
			SortedSet<E> other = (SortedSet<E>) otherSet;
			ArrayList<E> otherCon = other.myCon;
			int thisStart = 0;
			int otherStart = 0;
			int oldSize = this.size();

			if (other.size() == 0) {
				return false;
			} else if(this.containsAll(other)) {
				return false;
			}

			ArrayList<E> temp = new ArrayList<E>();
			//calls helper method to merge both sets
			addMerge(temp, otherCon, thisStart, otherStart);

			this.myCon = temp;
			return this.size() != oldSize;

		} else {
			return super.addAll(otherSet);
		}
	}

	//helper method that merges the two sets into a temporary ArrayList
	//@param passes the temp ArrayList, other ArrayList, and this and other
	// starting positions
	private void addMerge(ArrayList<E> temp, ArrayList<E> other, int thisStart,
						  int otherStart){
		while (thisStart < this.size()
				&& otherStart < other.size()) {
			if (this.myCon.get(thisStart)
					.compareTo(other.get(otherStart)) < 0) {
				temp.add(this.myCon.get(thisStart));
				thisStart++;
			} else if (this.myCon.get(thisStart)
					.compareTo(other.get(otherStart)) > 0) {
				temp.add(other.get(otherStart));
				otherStart++;
			} else {
				temp.add(this.myCon.get(thisStart));
				thisStart++;
				otherStart++;
			}
		}

		//adds remaining elements if one list is larger
		while (thisStart < this.size()) {
			temp.add(this.myCon.get(thisStart));
			thisStart++;
		}

		while (otherStart < other.size()) {
			temp.add(other.get(otherStart));
			otherStart++;
		}
	}

	/**
	 * Make this set empty. <br>
	 * pre: none <br>
	 * post: size() = 0
	 */
	//O(N)
	public void clear() {
		myCon.clear();
	}

	/**
	 * Determine if item is in this set. <br>
	 * pre: item != null
	 * 
	 * @param item element whose presence is being tested. Item may not equal
	 *             null.
	 * @return true if this set contains the specified item, false otherwise.
	 */
	//O(logN)
	public boolean contains(E item) {
		if (item == null) {
			throw new IllformedLocaleException("item cannot be null");
		}
		//calls binary search helper method to locate item
		return binarySearch(0, myCon.size() - 1, item);
	}

	//helper method to locate item using binary search
	//@param passes the integers start and end and the item to look for
	private boolean binarySearch(int start, int end, E item) {
		int middle = (start + end) / 2;

		if (end < start) {
			return false;
		}

		if (item == myCon.get(middle)) {
			return true;
		} else if (item.compareTo(myCon.get(middle)) < 0) {
			//recursively calls binarySearch again to look at new portion of set
			return binarySearch(start, middle - 1, item);
		} else {
			return binarySearch(middle + 1, end, item);
		}
	}

	/**
	 * Determine if all of the elements of otherSet are in this set. <br>
	 * pre: otherSet != null
	 * 
	 * @param otherSet != null
	 * @return true if this set contains all of the elements in otherSet, false
	 *         otherwise.
	 */
	//O(N)
	public boolean containsAll(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllformedLocaleException("item cannot be null");
		}

		//if otherSet is an instance of SortedSet, checks if the intersection of
		//otherSet and this is equal to the size of otherSet
		//otherwise calls the containsAll() in AbstractSet
		if (otherSet instanceof SortedSet<?>) {
			SortedSet<E> inter = (SortedSet<E>) intersection(otherSet);
			return (inter.size() == otherSet.size());
		} else {
			return super.containsAll(otherSet);
		}
	}

	/**
	 * Create a new set that is the difference of this set and otherSet. Return
	 * an ISet of elements that are in this Set but not in otherSet. Also called
	 * the relative complement. <br>
	 * Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] then
	 * A.difference(B) would return an ISet with elements [X, Y] while
	 * B.difference(A) would return an ISet with elements [W]. <br>
	 * pre: otherSet != null <br>
	 * post: returns a set that is the difference of this set and otherSet.
	 * Neither this set or otherSet are altered as a result of this operation.
	 * <br>
	 * pre: otherSet != null
	 * 
	 * @param otherSet != null
	 * @return a set that is the difference of this set and otherSet
	 */
	//O(N)
	public ISet<E> difference(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet cannot be null");
		}

		//if otherSet is an instance of SortedSet, iterates through ArrayLists and
		//adds elements that are present in this but not otherSet to the result
		//otherwise, uses contains to check for each element
		if (otherSet instanceof SortedSet<?>) {
			SortedSet<E> other = (SortedSet<E>) otherSet;
			ArrayList<E> otherCon = other.myCon;
			SortedSet<E> result = new SortedSet<E>();
			int thisIndex = 0;
			int otherIndex = 0;

			//returns early if both sets are empty or other is empty
			if(this.size() == 0 && other.size() == 0){
				return result;
			} else if(other.size() == 0){
				return this;
			}

			//calls helper method to iterate through the ArrayLists
			getDiff(otherCon, result, thisIndex, otherIndex);
			return result;
		} else {
			SortedSet<E> result = new SortedSet<E>();
			for (E value : this) {
				if (!otherSet.contains(value)) {
					result.add(value);
				}
			}
			return result;
		}
	}

	//helper method that iterates through the ArrayLists and add elements accordingly
	//@param passes the other ArrayList, result SortedSet, this index, and other index
	private void getDiff(ArrayList<E> other, SortedSet<E> result, int thisIndex,
						 int otherIndex){
		while ((thisIndex <= (this.size() - 1))
				&& (otherIndex <= (other.size() - 1))) {
			if (this.myCon.get(thisIndex)
					.compareTo(other.get(otherIndex)) < 0) {
				result.add(this.myCon.get(thisIndex));
				thisIndex++;
			} else if (this.myCon.get(thisIndex)
					.compareTo(other.get(otherIndex)) > 0) {
				otherIndex++;
			} else {
				thisIndex++;
				otherIndex++;
			}
		}

		if(this.size() > other.size()) {
			while (thisIndex <= this.size() - 1) {
				result.add(this.myCon.get(thisIndex));
				thisIndex++;
			}
		}
	}

	/**
	 * Determine if this set is equal to other. Two sets are equal if they have
	 * exactly the same elements. The order of the elements does not matter.
	 * <br>
	 * pre: none
	 * 
	 * @param other the object to compare to this set
	 * @return true if other is a Set and has the same elements as this set
	 */
	//O(N)
	public boolean equals(Object other) {
		if (!(other instanceof ISet<?>)) {
			return false;
		}

		//if other is an instance of SortedSet, iterates through both objects
		//to check if any elements are different
		//otherwise, uses containsAll()
		if (other instanceof SortedSet<?>) {
			ISet<?> otherSet = (ISet<?>) other;
			Iterator<E> itThis = this.iterator();
			Iterator<?> itOther = otherSet.iterator();

			//returns early if both sets are empty or not the same size
			if (this.size() == 0 && otherSet.size() == 0) {
				return true;
			} else if (this.size() != otherSet.size()) {
				return false;
			}

			while (itThis.hasNext() && itOther.hasNext()) {
				if (!itThis.next().equals((itOther.next()))) {
					return false;
				}
			}
			return true;
		} else {
			UnsortedSet<E> otherSet = (UnsortedSet<E>) other;
			if (this.size() == 0 && otherSet.size() == 0) {
				return true;
			} else {
				return containsAll(otherSet);
			}
		}
	}

	/**
	 * create a new set that is the intersection of this set and otherSet. <br>
	 * pre: otherSet != null<br>
	 * <br>
	 * post: returns a set that is the intersection of this set and otherSet.
	 * Neither this set or otherSet are altered as a result of this operation.
	 * <br>
	 * pre: otherSet != null
	 * 
	 * @param otherSet != null
	 * @return a set that is the intersection of this set and otherSet
	 */
	//O(N)
	public ISet<E> intersection(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet cannot be null");
		}

		//if otherSet is an instance of SortedSet, iterates through both sets and
		//adds common elements to the result
		//otherwise, calls on the intersection() in AbstractSet
		if (otherSet instanceof SortedSet<?>) {
			SortedSet<E> other = (SortedSet<E>) otherSet;
			ArrayList<E> otherCon = other.myCon;
			SortedSet<E> result = new SortedSet<E>();
			int thisIndex = 0;
			int otherIndex = 0;

			//calls on helper method to iterate through both sets
			getInter(otherCon, thisIndex, otherIndex, result);
			return result;
		} else {
			return super.intersection(otherSet);
		}
	}

	//helper method that get common elements from both sets
	//@param passes the other ArrayList, result SortedSet, this index, and other index
	private void getInter(ArrayList<E> other, int thisIndex, int otherIndex,
						  SortedSet<E> result){
		while ((thisIndex <= this.size() - 1)
				&& (otherIndex <= other.size() - 1)) {
			if (this.myCon.get(thisIndex)
					.compareTo(other.get(otherIndex)) < 0) {
				thisIndex++;
			} else if (this.myCon.get(thisIndex)
					.compareTo(other.get(otherIndex)) > 0) {
				otherIndex++;
			} else {
				result.myCon.add(this.myCon.get(thisIndex));
				thisIndex++;
				otherIndex++;
			}
		}
	}

	/**
	 * Return an Iterator object for the elements of this set. pre: none
	 * 
	 * @return an Iterator object for the elements of this set
	 */
	//O(1)
	public Iterator<E> iterator() {
		return myCon.iterator();
	}

	/**
	 * Remove the specified item from this set if it is present. pre: item !=
	 * null
	 * 
	 * @param item the item to remove from the set. item may not equal null.
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise
	 */
	//O(N)
	public boolean remove(E item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot be null");
		}

		//calls on the binary search method to locate element
		if (binarySearch(0, myCon.size() - 1, item)) {
			int index = myCon.indexOf(item);
			myCon.remove(index);
			return true;
		}

		return false;
	}

	/**
	 * Return the number of elements of this set. pre: none
	 * 
	 * @return the number of items in this set
	 */
	//O(1)
	public int size() {
		return myCon.size();
	}

	/**
	 * Create a new set that is the union of this set and otherSet. <br>
	 * pre: otherSet != null <br>
	 * post: returns a set that is the union of this set and otherSet. Neither
	 * this set or otherSet are altered as a result of this operation. <br>
	 * pre: otherSet != null
	 * 
	 * @param otherSet != null
	 * @return a set that is the union of this set and otherSet
	 */
	//O(N)
	public ISet<E> union(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet cannot be null");
		}

		//if otherSet is an instance of SortedSet, uses addAll()
		//otherwise, uses for loops to add each element
		if (otherSet instanceof SortedSet<?>) {
			SortedSet<E> other = (SortedSet<E>) otherSet;
			SortedSet<E> result = new SortedSet<>();

			if(other.size() == 0){
				return this;
			}

			result.addAll(this);
			result.addAll(other);
			return result;
		} else {
			//is this too inefficient
			SortedSet<E> result = new SortedSet<E>();
			for (E value : this) {
				result.add(value);
			}

			for (E value : otherSet) {
				if (!result.contains(value)) {
					result.add(value);
				}
			}
			return result;
		}
	}

}
