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

/*experiments

--insertion for binary search tree--
1000: time: 7.2598E-4s height: 22 size: 1000 min-height: 10
2000: time: 2.9371E-4s height: 24 size: 2000 min-height: 11
4000: time: 8.8944E-4s height: 25 size: 4000 min-height: 12
8000: time: 0.0015628700000000003s height: 29 size: 8000 min-height: 13
16000: time: 0.003801959999999999s height: 31 size: 15999 min-height: 14
32000: time: 0.008382610000000002s height: 35 size: 32000 min-height: 15
64000: time: 0.01659147s height: 37 size: 63999 min-height: 16
128000: time: 0.04950718999999999s height: 39 size: 127997 min-height: 17
256000: time: 0.09773314000000002s height: 42 size: 255992 min-height: 18
512000: time: 0.25892025s height: 46 size: 511972 min-height: 19
1024000: time: 0.70809963s height: 50 size: 1023876 min-height: 20

the minimum possible tree height for n unique values inserted into a tree: log(n)

--insertion for treeset--
1000: time: 0.00120201s
2000: time: 0.00128807s
4000: time: 0.0025723699999999996s
8000: time: 0.00465789s
16000: time: 0.005280169999999999s
32000: time: 0.00882324s
64000: time: 0.02506561s
128000: time: 0.05587175s
256000: time: 0.09944051000000001s
512000: time: 0.28197313s
1024000: time: 0.63334655s

compared to the binary search tree, the average times are very similar

--sorted insertion for binary search tree--
1000: time: 0.00291742s height: 999 size: 1000
2000: time: 0.00508625s height: 1999 size: 2000
4000: time: 0.020820979999999996s height: 3999 size: 4000
8000: time: 0.08986416s height: 7999 size: 8000
16000: time: 0.3586029300000001s height: 15999 size: 16000
32000: time: 1.36269548s height: 31999 size: 32000
64000: time: 6.0537936299999995s height: 63999 size: 64000
predictions:
128000: time: 24s
256000: time: 96s
512000: time: 384s

--sorted insertion for treeset--
1000: time: 0.00103731
2000: time: 3.3324E-4
4000: time: 8.4787E-4
8000: time: 0.00116977
16000: time: 0.00204069
32000: time: 0.00426812
64000: time: 0.009550630000000001

the times for the treeset are significantly lower than for the binary search tree
rather than a binary tree we have a one-sided tree, which will be faster when adding to the end
 */

import java.util.*;

/**
 * Some test cases for CS314 Binary Search Tree assignment.
 *
 */
public class BSTTester {

    /**
     * The main method runs the tests.
     * @param args Not used
     */
    public static void main(String[] args) {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        //experiments();
        //add tests
        t.add(1);
        t.add(2);
        t.add(3);
        showTestResults(t.size() == 3, 1);
        t.add(4);
        t.add(5);
        showTestResults(t.size() == 5, 2);
        //remove tests
        t.remove(5);
        t.remove(4);
        showTestResults(t.size() == 3, 3);
        t.remove(3);
        t.remove(2);
        t.remove(1);
        showTestResults(t.size() == 0, 4);
        //max tests
        t.add(1);
        t.add(2);
        t.add(3);
        t.add(4);
        t.add(5);
        showTestResults(t.max() == 5, 5);
        t.remove(5);
        showTestResults(t.max() == 4, 6);
        //min tests
        showTestResults(t.min() == 1, 7);
        t.add(5);
        showTestResults(t.min() == 1, 8);
        //isPresent tests
        showTestResults(t.isPresent(1), 9);
        showTestResults(t.isPresent(2), 10);
        //size tests
        showTestResults(t.size() == 5, 11);
        t.remove(5);
        showTestResults(t.size() == 4, 12);
        //height tests
        showTestResults(t.height() == 3, 13);
        t.add(5);
        showTestResults(t.height() == 4, 14);
        //getALl tests
        showTestResults(t.getAll().toString().equals("[1, 2, 3, 4, 5]"), 15);
        t.remove(5);
        showTestResults(t.getAll().toString().equals("[1, 2, 3, 4]"), 16);
        //iterativeAdd tests
        t.remove(4);
        t.iterativeAdd(4);
        showTestResults(t.size() == 4, 17);
        t.iterativeAdd(5);
        showTestResults(t.size() == 5, 18);
        //get tests
        showTestResults(t.get(0) == 1, 19);
        showTestResults(t.get(4) == 5, 20);
        //getALlLessThan tests
        showTestResults(t.getAllLessThan(5).toString().equals("[1, 2, 3, 4]"), 21);
        showTestResults(t.getAllLessThan(4).toString().equals("[1, 2, 3]"), 22);
        //getAllGreaterThan tests
        showTestResults(t.getAllGreaterThan(5).toString().equals("[]"), 23);
        showTestResults(t.getAllGreaterThan(0).toString().equals("[1, 2, 3, 4, 5]"), 24);
        //numNodesAtDepth tests
        showTestResults(t.numNodesAtDepth(0) == 1, 25);
        showTestResults(t.numNodesAtDepth(1) == 1, 26);

    }

    private static void experiments(){
        int[] tests = {1000, 2000, 4000, 8000, 16000, 32000, 64000};
        Stopwatch s;
        BinarySearchTree<Integer> t;

        for(int k = 0; k < tests.length; k++) {
            double time = 0;
            int height = 0;
            int size = 0;
            for (int j = 0; j < 10; j++) {
                s = new Stopwatch();
                t = new BinarySearchTree<Integer>();
                s.start();
                for (int i = 0; i < tests[k]; i++) {
                    t.iterativeAdd(i);
                }
                s.stop();
                time += s.time();
            }
            System.out.println(tests[k] + ": " + "time: " + time/10);
        }
    }

    private static void showTestResults(boolean passed, int testNum) {
        if (passed) {
            System.out.println("Test " + testNum + " passed.");
        } else {
            System.out.println("TEST " + testNum + " FAILED.");
        }
    }
}
