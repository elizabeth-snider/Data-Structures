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

import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet. Elements are not in any particular order.
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must be
 * used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {
	private ArrayList<E> myCon;

	public UnsortedSet() {
		myCon = new ArrayList<E>();
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

		if (!myCon.contains(item)) {
			myCon.add(item);
			return true;
		}

		return false;
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
	//O(N^2)
	public ISet<E> difference(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet cannot be null");
		}

		UnsortedSet<E> result = new UnsortedSet<E>();

		for (E value : myCon) {
			if (!otherSet.contains(value)) {
				result.add(value);
			}
		}

		return result;
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
	//O(N^2)
	public ISet<E> intersection(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet cannot be null");
		}

		UnsortedSet<E> result = new UnsortedSet<E>();

		for (E value : myCon) {
			if (otherSet.contains(value)) {
				result.add(value);
			}
		}

		return result;
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
	//O(N^2)
	public ISet<E> union(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet cannot be null");
		}

		UnsortedSet<E> result = new UnsortedSet<E>();

		for (E value : myCon) {
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
