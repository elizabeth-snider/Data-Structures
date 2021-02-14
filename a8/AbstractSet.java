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

/**
 * Students are to complete this class. Students should implement as many
 * methods as they can using the Iterator from the iterator method and the other
 * methods.
 */
public abstract class AbstractSet<E> implements ISet<E> {

	/*
	 * NO INSTANCE VARIABLES ALLOWED.
	 *
	 * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED. (In other words
	 * the data types UnsortedSet and SortedSet will not appear any where in
	 * this class.)
	 *
	 * NO DIRECT REFERENCES to ArrayList or other Java Collections.
	 *
	 * NO METHODS ADDED other than those in ISet and Object.
	 */

	/**
	 * A union operation. Add all items of otherSet that are not already present
	 * in this set to this set.
	 *
	 * @param otherSet != null
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise.
	 */
	public boolean addAll(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet cannot be null");
		}

		boolean result = false;

		for (E value : otherSet) {
			if (!this.contains(value)) {
				this.add(value);
				result = true;
			}
		}

		return result;
	}

	/**
	 * Make this set empty. <br>
	 * pre: none <br>
	 * post: size() = 0
	 */
	public void clear() {
		Iterator<E> it = this.iterator();

		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	/**
	 * Determine if item is in this set. <br>
	 * pre: item != null
	 *
	 * @param item element whose presence is being tested. Item may not equal
	 *             null.
	 * @return true if this set contains the specified item, false otherwise.
	 */
	public boolean contains(E item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot be null");
		}

		for (E value : this) {
			if (value.equals(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine if all of the elements of otherSet are in this set. <br>
	 * pre: otherSet != null
	 *
	 * @param otherSet != null
	 * @return true if this set contains all of the elements in otherSet, false
	 *         otherwise.
	 */
	public boolean containsAll(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet cannot be null");
		}

		for (E value : otherSet) {
			if (!this.contains(value)) {
				return false;
			}
		}
		return true;
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
	public boolean equals(Object other) {
		if (other instanceof ISet<?>) {
			Iterator<E> itOther = ((ISet<E>) other).iterator();
			Iterator<E> itThis = this.iterator();
			boolean seen = false;

			//returns early if both sets are empty
			if (this.size() == 0 && !itOther.hasNext()) {
				return true;
			}

			while (itOther.hasNext()) {
				E temp = itOther.next();
				for (E value : this) {
					if (value.equals(temp)) {
						seen = true;
					}
				}
				if (!seen) {
					return false;
				}
			}
			return seen;
		}
		return false;
	}

	/**
	 * Remove the specified item from this set if it is present. pre: item !=
	 * null
	 *
	 * @param item the item to remove from the set. item may not equal null.
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise
	 */
	public boolean remove(E item) {
		if (item == null) {
			throw new IllegalArgumentException("item cannot be null");
		}

		Iterator<E> it = this.iterator();

		while (it.hasNext()) {
			if (it.next().equals(item)) {
				it.remove();
				return true;
			}
		}

		return false;
	}

	/**
	 * Return the number of elements of this set. pre: none
	 *
	 * @return the number of items in this set
	 */
	public int size() {
		int result = 0;

		for (E value : this) {
			result++;
		}

		return result;
	}

	/**
	 * create a new set that is the intersection of this set and otherSet.
	 * <br>pre: otherSet != null<br>
	 * <br>post: returns a set that is the intersection of this set
	 * and otherSet.
	 * Neither this set or otherSet are altered as a result of this operation.
	 * <br> pre: otherSet != null
	 * @param otherSet != null
	 * @return a set that is the intersection of this set and otherSet
	 */
	public ISet<E> intersection(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet cannot be null");
		}

		ISet<E> result = this.difference(this);
		for (E value : this) {
			if (otherSet.contains(value)) {
				result.add(value);
			}
		}
		return result;
	}

	/**
	 * Return a String version of this set. Format is (e1, e2, ... en)
	 *
	 * @return A String version of this set.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		String seperator = ", ";
		result.append("(");

		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			result.append(it.next());
			result.append(seperator);
		}
		// get rid of extra separator
		if (this.size() > 0)
			result.setLength(result.length() - seperator.length());

		result.append(")");
		return result.toString();
	}
}
