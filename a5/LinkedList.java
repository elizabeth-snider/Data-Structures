
/*
 * Student information for assignment:
 * On my honor, Elizabeth, this programming assignment is my own work
 * and I have not provided this code to any other student.
 * UTEID: eys275
 * email address: elizabethsnider2011@gmail.com
 * TA name: Henry Liu
 * Number of slip days I am using: 0
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements IList<E> {
    // CS314 students. Add you instance variables here.
    // You decide what instance variables to use.
    // Must adhere to assignment requirements.
    // No ArrayLists or Java LinkedLists.
	private DoubleListNode<E> head;
	private DoubleListNode<E> end;
	private int size;
	public static final int INVALID = -1;
	
    // CS314 students, add constructors here:
	//iterator class
	private class LLIterator implements Iterator<E>{
		private DoubleListNode<E> nextNode;
		private boolean canRemove;
		private int index;
		
		//instantiates both the head node and index for removing nodes
		public LLIterator() {
			nextNode = head;
			index = INVALID;
		}
		
		//returns boolean for if their exists a next node
		public boolean hasNext() {
			return nextNode!= null;
		}
		
		//iterates to the next node, returns the data of the next node
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			E nextData = nextNode.getData();
			nextNode = nextNode.getNext();
			canRemove = true;
			index++;
			
			return nextData;
		}
		
		//removes node based on iterator index
		//pre: must be able to remove (have next node)
		public void remove() {
			if(!canRemove) {
				throw new IllegalStateException("Cannot be removed.");
			}
	
			canRemove = false;	
			LinkedList.this.remove(index);
			index--;

		}
	}
	
    // CS314 students, add methods here:
	
	/**
	    * return an Iterator for this list.
	    * <br>pre: none
	    * <br>post: return an Iterator object for this List
	    */
	public Iterator<E> iterator(){
		return new LLIterator();
	}
	
	/**
     * Add an item to the end of this list.
     * <br>pre: item != null
     * <br>post: size() = old size() + 1, get(size() - 1) = item
     * @param item the data to be added to the end of this list,
     * item != null
     */
	public void add(E item) {
		if(item == null) {
			throw new IllegalArgumentException("item cannot be null.");
		}
		
		DoubleListNode<E> node = new DoubleListNode<E>(end, item, null);
		
		if(size == 0) {
			head = node;
		} else {
			end.setNext(node);
		}
		
		end = node;
		size++;
	}
	
	/**
     * Insert an item at a specified position in the list.
     * <br>pre: 0 <= pos <= size(), item != null
     * <br>post: size() = old size() + 1, get(pos) = item,
     * all elements in the list with a positon >= pos have a
     * position = old position + 1
     * @param pos the position to insert the data at in the list
     * @param item the data to add to the list, item != null
    */
	public void insert(int pos, E item) {
		if(item == null || pos < 0 || size < pos) {
			throw new IllegalArgumentException("item cannot be null, position must be "
					+ "greater than 0, and size must be greater than or equal to pos.");
		}
		
		if(pos == 0) {
			addFirst(item);
		} else if (pos == size) {
			add(item);
		} else {
			DoubleListNode<E> before = getNode(pos - 1);
			DoubleListNode<E> node = new DoubleListNode<E>(before, item, before.getNext());
			before.setNext(node);
			size++;
		}
	}
	
	//helper method that returns node at passed position
	//@param passed position
	private DoubleListNode<E> getNode(int pos){
		if(pos == size - 1) {
			return end;
		} 
		
		DoubleListNode<E> temp = head;
		for(int i = 0; i < pos; i++) {
			temp = temp.getNext();
		}
		
		return temp;
	}
	
	/**
     * Change the data at the specified position in the list.
     * the old data at that position is returned.
     * <br>pre: 0 <= pos < size(), item != null
     * <br>post: get(pos) = item, return the
     * old get(pos)
     * @param pos the position in the list to overwrite
     * @param item the new item that will overwrite the old item,
     * item != null
     * @return the old data at the specified position
     */
	public E set(int pos, E item) {
		if(item == null || pos < 0 || pos >= size) {
			throw new IllegalArgumentException("item cannot be null, pos must be greater than "
					+ "zero, and pos must be less than size.");
		}
		
		DoubleListNode<E> temp = getNode(pos);
		E result = temp.getData();
		temp.setData(item);
		
		return result;
	}
	
	/**
     * Get an element from the list.
     * <br>pre: 0 <= pos < size()
     * <br>post: return the item at pos
     * @param pos specifies which element to get
     * @return the element at the specified position in the list
     */
	public E get(int pos) {
		if(pos < 0 || size <= pos) {
			throw new IllegalArgumentException("pos must be greater than 0 and size must "
					+ "be greater than pos.");
		}
	
		return getNode(pos).getData();
	}
	
	 /**
     * Remove an element in the list based on position.
     * <br>pre: 0 <= pos < size()
     * <br>post: size() = old size() - 1, all elements of
     * list with a position > pos have a position = old position - 1
     * @param pos the position of the element to remove from the list
     * @return the data at position pos
     */
	public E remove(int pos) {
		if(pos < 0 || pos >= size()) {
			throw new IllegalArgumentException("pos cannot be less than zero or greater than"
					+ "or equal to size");
		}
		
		if(pos == 0) {
			return removeFirst();
		}
		
		DoubleListNode<E> before = getNode(pos - 1);
		E removed = before.getNext().getData();
		before.setNext(before.getNext().getNext());
		
		if(pos == size - 1) {
			end = before;
		}
		
		size--;
		return removed;
	}
	
	/**
     * Remove the first occurrence of obj in this list.
     * Return <tt>true</tt> if this list changed
     * as a result of this call, <tt>false</tt> otherwise.
     * <br>pre: obj != null
     * <br>post: if obj is in this list the first occurrence
     * has been removed and size() = old size() - 1.
     * If obj is not present the list is not altered in any way.
     * @param obj The item to remove from this list. obj != null
     * @return Return <tt>true</tt> if this list changed
     * as a result of this call, <tt>false</tt> otherwise.
     */
	public boolean remove(E obj) {
		if(obj == null) {
			throw new IllegalArgumentException("obj cannot be null.");
		}
		
		int position = indexOf(obj);
		
		if(position != INVALID) {
			remove(position);
			return true;
		}
		
		return false;
	}
	
	/**
     * Return a sublist of elements in this list
     * from <tt>start</tt> inclusive to <tt>stop</tt> exclusive.
     * This list is not changed as a result of this call.
     * <br>pre: <tt>0 <= start <= size(), start <= stop <= size()</tt>
     * <br>post: return a list whose size is stop - start
     * and contains the elements at positions start through stop - 1
     * in this list.
     * @param start index of the first element of the sublist.
     * @param stop stop - 1 is the index of the last element
     * of the sublist.
     * @return a list with <tt>stop - start</tt> elements,
     * The elements are from positions <tt>start</tt> inclusive to
     * <tt>stop</tt> exclusive in this list.
     * If start == stop an empty list is returned.
     */
	public IList<E> getSubList(int start, int stop){
		if(start < 0 || start > size() || stop < start || stop > size()) {
			throw new IllegalArgumentException("start cannot be less than zero or greater than size,"
					+ "and stop cannot be less than start or greater than size.");
		}
		
		DoubleListNode<E> temp = getNode(start);
		LinkedList<E> result = new LinkedList<E>();
		
		for(int i = start; i < stop; i++) {
			result.add(temp.getData());
			temp = temp.getNext();
		}
		
		return result;
	}
	
	/**
     * Return the size of this list.
     * In other words the number of elements in this list.
     * <br>pre: none
     * <br>post: return the number of items in this list
     * @return the number of items in this list
     */
	public int size() {
		return size;
	}
	
	/**
     * Find the position of an element in the list.
     * <br>pre: item != null
     * <br>post: return the index of the first element equal to item
     * or -1 if item is not present
     * @param item the element to search for in the list. item != null
     * @return return the index of the first element equal to item
     * or a -1 if item is not present
     */
	public int indexOf(E item) {
		if(item == null) {
			throw new IllegalArgumentException("item cannot be null.");
		}
		
		DoubleListNode<E> temp = head;
		
		for(int i = 0; i < size; i++) {
			if(temp.getData().equals(item)) {
				return i;
			}
			
			temp = temp.getNext();
		}
		
		return INVALID;
	}

	/**
     * find the position of an element in the list starting
     * at a specified position.
     * <br>pre: 0 <= pos < size(), item != null
     * <br>post: return the index of the first element equal
     * to item starting at pos
     * or -1 if item is not present from position pos onward
     * @param item the element to search for in the list. Item != null
     * @param pos the position in the list to start searching from
     * @return starting from the specified position
     * return the index of the first element equal to item
     * or a -1 if item is not present between pos
     * and the end of the list
     */
	public int indexOf(E item, int pos) {
		if(item == null || pos < 0 || pos >= size()) {
			throw new IllegalArgumentException("item cannot be null, pos cannot be less than zero"
					+ " or greater than or equal to size.");
		}
		
		DoubleListNode<E> temp = getNode(pos);
		
		for(int i = pos; i < size; i++) {
			if(temp.getData().equals(item)) {
				return i;
			} else {
				temp = temp.getNext();
			}
		}
		
		return INVALID;
	}
	
	/**
     * return the list to an empty state.
     * <br>pre: none
     * <br>post: size() = 0
     */
	public void makeEmpty() {
		head = null;
		end = null;
		size = 0;
	}
	
	/**
     * Remove all elements in this list from <tt>start</tt>
     * inclusive to <tt>stop</tt> exclusive.
     * <br>pre: <tt>0 <= start <= size(), start <= stop <= size()</tt>
     * <br>post: <tt>size() = old size() - (stop - start)</tt>
     * @param start position at beginning of range of elements
     * to be removed
     * @param stop stop - 1 is the position at the end
     * of the range of elements to be removed
     */
	public void removeRange(int start, int stop) {
		if(start < 0 || start > size() || stop < start || stop > size()) {
			throw new IllegalArgumentException("start cannot be less than zero or greater than size"
					+ "and stop cannot be less than start or greater than size");
		}
		
		DoubleListNode<E> startNode = getNode(start);
		DoubleListNode<E> stopNode = getNode(stop);
		
		//specific scenarios for removing a range
		//changes size accordingly
		if(start == 0 && stop == size) {
			makeEmpty();
		} else if(stop == size) {
			end = startNode.getPrev();
			end.setNext(null);
			size -= stop - start;
		} else if(start == 0) {
			head = stopNode;
			head.setPrev(null);
			size -= stop - start;
		} else {
			startNode.getPrev().setNext(stopNode);
			stopNode.setPrev(startNode.getPrev());
			size -= stop - start;
		}
	}
	
	/**
     * Return a String version of this list enclosed in
     * square brackets, []. Elements are in
     * are in order based on position in the
     * list with the first element
     * first. Adjacent elements are separated by comma's
     * @return a String representation of this IList
     */
	public String toString() {
		if(size == 0) {
			return "[]";
		}
		
		StringBuilder string = new StringBuilder();
		string.append("[");
		string.append(head.getData());
		DoubleListNode<E> temp = head.getNext();
		
		while(temp != null) {
			string.append(", ");
			string.append(temp.getData());
			temp = temp.getNext();
		}
		
		string.append("]");
		return string.toString();
	}
	
	/**
     * Determine if this IList is equal to other. Two
     * ILists are equal if they contain the same elements
     * in the same order.
     * @return true if this IList is equal to other, false otherwise
     */
	public boolean equals(Object other) {
		if(!(other instanceof IList)) {
			return false;
		}
		
		boolean result = false;
		//cast other Object to IList<E> type
		IList<E> otherList = (IList<E>) other;
		
		if(this.size() == otherList.size()) {
			Iterator<E> thisIt = this.iterator();
			Iterator<E> otherIt = otherList.iterator();
			result = true;
			
			//makes sure both lists have the same elements/ order of elements
			while(thisIt.hasNext() && result) {
				if(!thisIt.next().equals(otherIt.next())) {
					result = false;
				}
			}
		}
		
		return result;
	}
    /**
     * add item to the front of the list. <br>
     * pre: item != null <br>
     * post: size() = old size() + 1, get(0) = item
     *
     * @param item the data to add to the front of this list
     */
    public void addFirst(E item) {
    	if(item == null) {
    		throw new IllegalArgumentException("item cannot be null.");
    	}
    	
    	DoubleListNode<E> node = new DoubleListNode<E>(null, item, head);
    	head = node;
    	size++;
    	
    	if(size == 1) {
    		end = node;
    	}
    	
    }

    /**
     * add item to the end of the list. <br>
     * pre: item != null <br>
     * post: size() = old size() + 1, get(size() -1) = item
     *
     * @param item the data to add to the end of this list
     */
    public void addLast(E item) {
    	if(item == null) {
    		throw new IllegalArgumentException("item cannot be null.");
    	}
    	
    	add(item); 	
    }

    /**
     * remove and return the first element of this list. <br>
     * pre: size() > 0 <br>
     * post: size() = old size() - 1
     *
     * @return the old first element of this list
     */
    public E removeFirst() {
    	if(size() <= 0) {
    		throw new IllegalArgumentException("size cannot be zero");
    	}
    	
        E old = head.getData();
        head = head.getNext();
        size--;
        
        if(size == 0) {
        	end = null;
        }
        
        return old;
    }

    /**
     * remove and return the last element of this list. <br>
     * pre: size() > 0 <br>
     * post: size() = old size() - 1
     *
     * @return the old last element of this list
     */
    public E removeLast() {
    	if(size() <= 0) {
    		throw new IllegalArgumentException("size cannot be less than or equal to zero.");
    	}
    	
        E result = end.getData();
        
        if(size() > 1) {
        	end = end.getPrev();
        	end.setNext(null);
        } else {
        	head = null;
        	end = null;
        }
        
        size--;
        
        return result;
    }

}
