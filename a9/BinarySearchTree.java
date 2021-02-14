/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, Elizabeth, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: eys275
 *  email address: elizabethsnider2011@gmail.com
 *  TA name: Henry Liu
 *  Number of slip days I am using: 2
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Shell for a binary search tree class.
 *
 * @param <E> The data type of the elements of this BinarySearchTree.
 *            Must implement Comparable or inherit from a class that implements
 *            Comparable.
 * @author scottm
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private BSTNode<E> root;
    // CS314 students. Add any other instance variables you want here
    private int size;
    // CS314 students. Add a default constructor here if you feel it is necessary.

    /**
     * Add the specified item to this Binary Search Tree if it is not already present.
     * <br>
     * pre: <tt>value</tt> != null<br>
     * post: Add value to this tree if not already present. Return true if this tree
     * changed as a result of this method call, false otherwise.
     *
     * @param value the value to add to the tree
     * @return false if an item equivalent to value is already present
     * in the tree, return true if value is added to the tree and size() = old size() + 1
     */
    public boolean add(E value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }

        //code from lecture
        int oldSize = size;
        root = addHelp(root, value);
        return size != oldSize;
    }

    //helper method for add that uses recursion
    //@param passes the root node and value to add
    private BSTNode<E> addHelp(BSTNode<E> n, E val) {
        //base case, creates and returns new leaF
        if (n == null) {
            size++;
            return new BSTNode<>(val);
        }

        //recursive case if n is not null
        int dir = val.compareTo(n.data);
        //val belongs in left subtree of n
        if (dir < 0) {
            n.left = addHelp(n.left, val);
            //val belongs in right subtree of n
        } else if (dir > 0) {
            n.right = addHelp(n.right, val);
        }
        return n;
    }

    /**
     * Remove a specified item from this Binary Search Tree if it is present.
     * <br>
     * pre: <tt>value</tt> != null<br>
     * post: Remove value from the tree if present, return true if this tree
     * changed as a result of this method call, false otherwise.
     *
     * @param value the value to remove from the tree if present
     * @return false if value was not present
     * returns true if value was present and size() = old size() - 1
     */
    public boolean remove(E value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }

        int oldSize = size;
        root = removeHelp(root, value);
        return oldSize != size;
    }

    //helper method for remove that uses recursion
    //@param passes the root node and value to remove
    private BSTNode<E> removeHelp(BSTNode<E> n, E val) {
        //if value is not present
        if (n == null) {
            return n;
        } else {
            int dir = val.compareTo(n.data);
            //if value is in the left subtree
            if (dir < 0) {
                n.left = removeHelp(n.left, val);
                //if value is in the right subtree
            } else if (dir > 0) {
                n.right = removeHelp(n.right, val);
                //if found
            } else {
                size--;
                //if n is a leaf
                if (n.left == null && n.right == null) {
                    n = null;
                } else if (n.right == null) {
                    n = n.left;
                } else if (n.left == null) {
                    n = n.right;
                } else {
                    n.data = max(n.left);
                    n.left = removeHelp(n.left, n.data);
                    size++;
                }
            }
        }
        return n;
    }

    //pre: n != null
    private E max(BSTNode<E> n) {
        while (n.right != null) {
            n = n.right;
        }
        return n.data;
    }


    /**
     * Check to see if the specified element is in this Binary Search Tree.
     * <br>
     * pre: <tt>value</tt> != null<br>
     * post: return true if value is present in tree, false otherwise
     *
     * @param value the value to look for in the tree
     * @return true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }

        return contains(value, root);
    }

    //helper method for isPresent that uses recursion
    //@param passes the root node and value to search for
    private boolean contains(E val, BSTNode<E> n) {
        //base case: if n is null then value is not found
        if (n == null) {
            return false;
        }

        //search tree based on if the value is lesser than
        //or greater than the values in the tree
        int dir = val.compareTo(n.getData());
        if (dir == 0) {
            return true;
        } else if (dir < 0) {
            return contains(val, n.getLeft());
        } else {
            return contains(val, n.getRight());
        }

    }

    /**
     * Return how many elements are in this Binary Search Tree.
     * <br>
     * pre: none<br>
     * post: return the number of items in this tree
     *
     * @return the number of items in this Binary Search Tree
     */
    public int size() {
        return size;
    }

    /**
     * return the height of this Binary Search Tree.
     * <br>
     * pre: none<br>
     * post: return the height of this tree.
     * If the tree is empty return -1, otherwise return the
     * height of the tree
     *
     * @return the height of this tree or -1 if the tree is empty
     */
    public int height() {
        //code from lecture
        return htHelp(root);
    }

    private int htHelp(BSTNode<E> n) {
        //returns -1 if tree is empty
        if (n == null) {
            return -1;
        } else {
            return 1 + Math.max(htHelp(n.left), htHelp(n.right));
        }
    }

    /**
     * Return a list of all the elements in this Binary Search Tree.
     * <br>
     * pre: none<br>
     * post: return a List object with all data from the tree in ascending order.
     * If the tree is empty return an empty List
     *
     * @return a List object with all data from the tree in sorted order
     * if the tree is empty return an empty List
     */
    public List<E> getAll() {
        List<E> result = new ArrayList<E>();
        //if tree is empty, return an empty List
        if (root == null) {
            return result;
        }
        return getAllHelp(result, root);
    }

    //helper method for getAll that uses recursion to traverse the BST
    //@param passes the result List and root node
    private List<E> getAllHelp(List<E> result, BSTNode<E> n) {
        //base case: when n is null return the list
        if (n == null) {
            return result;
        } else {
            getAllHelp(result, n.left);
            result.add(n.data);
            getAllHelp(result, n.right);
        }
        return result;
    }


    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     *
     * @return the maximum value in this tree
     */
    public E max() {
        if (size() <= 0) {
            throw new IllegalArgumentException("size must be greater than zero");
        }

        //goes to the rightmost node of the BST
        BSTNode<E> n = root;
        while (n.right != null) {
            n = n.right;
        }
        return n.data;
    }

    /**
     * return the minimum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the smallest value in this Binary Search Tree
     *
     * @return the minimum value in this tree
     */
    public E min() {
        if (size() <= 0) {
            throw new IllegalArgumentException("size must be greater than zero");
        }

        //goes to the leftmost node of the BST
        BSTNode<E> n = root;
        while (n.left != null) {
            n = n.left;
        }
        return n.data;
    }

    /**
     * An add method that implements the add algorithm iteratively
     * instead of recursively.
     * <br>pre: data != null
     * <br>post: if data is not present add it to the tree,
     * otherwise do nothing.
     *
     * @param data the item to be added to this tree
     * @return true if data was not present before this call to add,
     * false otherwise.
     */
    public boolean iterativeAdd(E data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        //if the root is null, make the root the new node
        //and return true
        if (root == null) {
            root = new BSTNode<E>(data);
            size++;
            return true;
        }

        //create current node and pointer node
        BSTNode<E> n = root;
        BSTNode<E> pointer = null;
        //traverse BST
        while (n != null) {
            pointer = n;
            if (data.compareTo(n.data) < 0) {
                n = n.left;
            } else if(data.compareTo(n.data) > 0) {
                n = n.right;
            } else {
                return false;
            }
        }

        //compares data to leaf nodes
        if (data.compareTo(pointer.data) < 0) {
            pointer.left = new BSTNode<E>(data);
        } else {
            pointer.right = new BSTNode<E>(data);
        }
        size++;
        return true;
    }


    /**
     * Return the "kth" element in this Binary Search Tree. If kth = 0 the
     * smallest value (minimum) is returned.
     * If kth = 1 the second smallest value is returned, and so forth.
     * <br>pre: 0 <= kth < size()
     *
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     */
    public E get(int kth) {
        if (kth < 0 || kth >= size()) {
            throw new IllegalArgumentException("kth must be greater than zero and less than size");
        }
        ArrayList<E> result = new ArrayList<E>();
        int count = 0;
        getHelp(kth, root, result, count);
        //returns the kth value in the ArrayList
        return result.get(kth);
    }

    //helper method for get that uses recursion
    //@param passes kth, the root node, and the resulting List
    private void getHelp(int kth, BSTNode<E> n, ArrayList<E> result, int count) {
        //adds values to the ArrayList in ascending order until the kth count
        if (n != null && count <= kth) {
            getHelp(kth, n.left, result, count);
            count++;
            result.add(n.data);
            getHelp(kth, n.right, result, count);
        }
    }


    /**
     * Return a List with all values in this Binary Search Tree
     * that are less than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     *
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than
     * the parameter value. If there are no values in this tree less
     * than value return an empty list. The elements of the list are
     * in ascending order.
     */
    public List<E> getAllLessThan(E value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        List<E> result = new ArrayList<E>();
        return allLessHelp(root, value, result);
    }

    //helper method for getAllLessThan that uses recursion
    //@param passes the root node, value to compare, and result List
    private List<E> allLessHelp(BSTNode<E> n, E val, List<E> result) {
        //base case: return the list when the current node is null
        if (n == null) {
            return result;
        } else {
            allLessHelp(n.left, val, result);
            if (n.data.compareTo(val) < 0) {
                result.add(n.data);
            }
            allLessHelp(n.right, val, result);

        }
        return result;
    }


    /**
     * Return a List with all values in this Binary Search Tree
     * that are greater than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     *
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater
     * than the parameter value. If there are no values in this tree
     * greater than value return an empty list.
     * The elements of the list are in ascending order.
     */
    public List<E> getAllGreaterThan(E value) {
        List<E> result = new ArrayList<E>();
        return allGreaterHelp(root, value, result);
    }

    //helper method for getAllGreaterThan that uses recursion
    //@param passes the root node, value to compare, and result List
    private List<E> allGreaterHelp(BSTNode<E> n, E val, List<E> result) {
        //base case: return List when the current node is null
        if (n == null) {
            return result;
        } else {
            allGreaterHelp(n.left, val, result);
            if (n.data.compareTo(val) > 0) {
                result.add(n.data);
            }
            allGreaterHelp(n.right, val, result);
        }
        return result;
    }


    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>pre: none
     *
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     * the parameter d.
     */
    public int numNodesAtDepth(int d) {
        //if d is 0 and the root isn't null, return 1
        if (d == 0 && root != null) {
            return 1;
            //if depth is invalid or the root is null, return 0
        } else if (d < 0 || root == null) {
            return 0;
        }
        return numHelp(d, root, 0);
    }

    //helper method for numNodesAtDepth that uses recursion
    //@param passes the target depth, root node, and depth count
    private int numHelp(int d, BSTNode<E> n, int depth) {
        //returns 0 if node is null
        if (n == null) {
            return 0;
            //returns 1 if at the target depth
        } else if (depth == d) {
            return 1;
        } else {
            return numHelp(d, n.left, depth + 1) +
                    numHelp(d, n.right, depth + 1);
        }
    }

    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children will not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * <br>pre: none
     */
    public void printTree() {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces) {
        if (n != null) {
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + n.getData());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode() {
            this(null);
        }

        public BSTNode(E initValue) {
            this(null, initValue, null);
        }

        public BSTNode(BSTNode<E> initLeft,
                       E initValue,
                       BSTNode<E> initRight) {
            data = initValue;
            left = initLeft;
            right = initRight;
        }

        public E getData() {
            return data;
        }

        public BSTNode<E> getLeft() {
            return left;
        }

        public BSTNode<E> getRight() {
            return right;
        }

        public void setData(E theNewValue) {
            data = theNewValue;
        }

        public void setLeft(BSTNode<E> theNewLeft) {
            left = theNewLeft;
        }

        public void setRight(BSTNode<E> theNewRight) {
            right = theNewRight;
        }
    }
}
