/*  Student information for assignment:
*
*  On my honor, Elizabeth, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  Name: Elizabeth Snider
*  email address: elizabethsnider2011@gmail.com
*  UTEID: eys275
*  TA name: Henry Liu
*  Number of slip days used on this assignment: 0
*/

//Experiment results. CS314 students, place your experiment
//results here:
/*
Adding at end: ArrayList
N = 30000, total time:  0.0299
N = 60000, total time:  0.0584
N = 120000, total time:  0.1265
N = 240000, total time:  0.2215
N = 480000, total time:  0.4747

Adding at end: LinkedList
N = 30000, total time:  0.0257
N = 60000, total time:  0.0503
N = 120000, total time:  0.0979
N = 240000, total time:  0.1992
N = 480000, total time:  0.5336

Adding at front: ArrayList
N = 2000, total time:  0.0239
N = 4000, total time:  0.0506
N = 8000, total time:  0.1917
N = 16000, total time:  0.8673
N = 32000, total time:  3.6152

Adding at front: LinkedList
N = 10000, total time:  0.0069
N = 20000, total time:  0.0158
N = 40000, total time:  0.0248
N = 80000, total time:  0.0481
N = 160000, total time:  0.0955

Removing from front: ArrayList
N = 2000, total time:  0.0136
N = 4000, total time:  0.0454
N = 8000, total time:  0.1732
N = 16000, total time:  0.8317
N = 32000, total time:  3.4237

removing from front: LinkedList
N = 5000, total time:  0.0108
N = 10000, total time:  0.0278
N = 20000, total time:  0.0646
N = 40000, total time:  0.1377
N = 80000, total time:  0.2849

Getting random: ArrayList
N = 10000, total time:  0.0149
N = 20000, total time:  0.0336
N = 40000, total time:  0.0821
N = 80000, total time:  0.1852
N = 160000, total time:  0.4114

Getting random: LinkedList
N = 1000, total time:  0.0662
N = 2000, total time:  0.2721
N = 4000, total time:  1.0951
N = 8000, total time:  4.5002
N = 16000, total time: 18.2498

Getting all using iterator: ArrayList
N = 50000, total time:  0.0116
N = 100000, total time:  0.0196
N = 200000, total time:  0.0376
N = 400000, total time:  0.0784
N = 800000, total time:  0.1445

Getting all using iterator: LinkedList
N = 50000, total time:  0.0170
N = 100000, total time:  0.0323
N = 200000, total time:  0.0644
N = 400000, total time:  0.1258
N = 800000, total time:  0.2513

Getting all using get method: ArrayList
N = 100000, total time:  0.0126
N = 200000, total time:  0.0233
N = 400000, total time:  0.0480
N = 800000, total time:  0.1104
N = 1600000, total time:  0.1730

Getting all using get method: LinkedList
N = 1000, total time:  0.0621
N = 2000, total time:  0.2682
N = 4000, total time:  1.0913
N = 8000, total time:  4.4459
N = 16000, total time: 18.3792

 * Operations that are faster when using LinkedList: 
 * Adding at the front, removing from the front
 * 
 * Operations that are faster when using ArrayLists:
 * Getting random, getting all using iterator, getting all using get method
 * 
 * Operations that are about the same for both:
 * Adding at the end
 * 
 * Big O's:
 * Adding at the front: O(N) for ArrayList (when N is doubled, time is multiplied by around 4)
 * and O(1) for LinkedList (when N is doubled, time is doubled as well)
 * Removing from the front: O(N) for ArrayList (when N is doubled, time is multiplied by around 4)
 * and O(1) for LinkedList (when N is doubled, time is doubled as well)
 * Getting random: O(1) for ArrayList (when N is doubled, time is doubled as well) 
 * and O(N) for LinkedList (when N is doubled, time is multiplied by around 4)
 * Getting all using iterator: O(1) for both ArrayList and LinkedList (when N is doubled, 
 * time is doubled as well)
 * Getting all using get method: O(1) for ArrayList (when N is doubled, time is doubled as well) 
 * and O(N) for LinkedList (when N is doubled, time is multiplied by around 4)
 * Adding at the end: O(1) for both ArrayList and LinkedList (when N is doubled, 
 * time is doubled as well)
 */


import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class LinkedListTester {

	public static final int INVALID = -1;
	
   public static void main(String[] args) {

       LinkedList<String> list = new LinkedList<>();

       //test 1
       System.out.println("\nTest 1: add()");
       list.add("A");
       if (list.get(0).equals("A")) {
           System.out.println("Passed test 1");
       } else {
           System.out.println("Failed test 1");
       }
       
       //test 2
       System.out.println("\nTest 2: add()");
       list.add("B");
       if (list.get(0).equals("A")) {
           System.out.println("Passed test 2");
       } else {
           System.out.println("Failed test 2");
       }
       
       //test 3
       System.out.println("\nTest 3: add()");
       if (list.get(1).equals("B")) {
           System.out.println("Passed test 3");
       } else {
           System.out.println("Failed test 3");
       }

       //test 4
       System.out.println("\nTest 4: size()");
       if (list.size() == 2) {
           System.out.println("Passed test 4");
       } else {
           System.out.println("Failed test 4");
       }
       
       //test 5
       System.out.println("\nTest 5: size()");
       list.add("C");
       if (list.size() == 3) {
           System.out.println("Passed test 5");
       } else {
           System.out.println("Failed test 5");
       }
       
       //test 6
       System.out.println("\nTest 6: size()");
       list.remove("C");
       if (list.size() == 2) {
           System.out.println("Passed test 6");
       } else {
           System.out.println("Failed test 6");
       }

       //test 7
       System.out.println("\nTest 7: remove(0)");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       String removed = list.remove(0);
       if (removed.equals("A")) {
           System.out.println("Passed test 7");
       } else {
           System.out.println("Failed test 7");
       }
       
       //test 8
       System.out.println("\nTest 8: remove(1)");
       String removed2 = list.remove(0);
       if (removed2.equals("B")) {
           System.out.println("Passed test 8");
       } else {
           System.out.println("Failed test 8");
       }
       
       //test 9
       System.out.println("\nTest 9: remove(2)");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       String removed3 = list.remove(2);
       if (removed3.equals("C")) {
           System.out.println("Passed test 9");
       } else {
           System.out.println("Failed test 9");
       }

       System.out.println("\nTest 10: toString()");
       //test 10
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       if (list.toString().equals("[A, B, C]")) {
           System.out.println("Passed test 10");
       } else {
           System.out.println("Failed test 10");
       }
       
       System.out.println("\nTest 11: toString()");
       //test 11
       list.remove("C");
       if (list.toString().equals("[A, B]")) {
           System.out.println("Passed test 11");
       } else {
           System.out.println("Failed test 11");
       }

       //test 12
       System.out.println("\nTest 12: toString()");
       list.makeEmpty();
       if (list.toString().equals("[]")) {
            System.out.println("Passed test 12");
       } else {
           System.out.println("Failed test 12");
       }

       //test 13
       System.out.println("\nTest 13: makeEmpty()");
       list.makeEmpty();
       if (list.size() == 0) {
           System.out.println("Passed test 13");
       } else {
           System.out.println("Failed test 13");
       }

       //test 14
       System.out.println("\nTest 13: makeEmpty()");
       list.makeEmpty();
       if (list.size() == 0) {
           System.out.println("Passed test 14");
       } else {
           System.out.println("Failed test 14");
       }
       
       //test 15
       System.out.println("\nTest 15: makeEmpty");
       list.add("A");
       list.makeEmpty();
       if (list.size() == 0) {
           System.out.println("Passed test 15");
       } else {
           System.out.println("Failed test 15");
       }

       //test 16
       System.out.println("\nTest 16: insert(pos, item)");
       list = new LinkedList<>();
       list.insert(0, "A");
       if (list.toString().equals("[A]")) {
           System.out.println("Passed test 16");
       } else {
           System.out.println("Failed test 16");
       }
       
       //test 17
       System.out.println("\nTest 17: insert(pos, item)");
       list = new LinkedList<>();
       list.insert(0, "A");
       list.insert(1, "B");
       if (list.toString().equals("[A, B]")) {
           System.out.println("Passed test 17");
       } else {
           System.out.println("Failed test 17");
       }
       
       //test 18
       System.out.println("\nTest 18: insert(pos, item)");
       list = new LinkedList<>();
       list.insert(0, "A");
       list.insert(1,  "C");
       if (list.toString().equals("[A, C]")) {
           System.out.println("Passed test 17");
       } else {
           System.out.println("Failed test 17");
       }

       //test 19
       System.out.println("\nTest 19: addFirst(item)");
       list = new LinkedList<>();
       list.addFirst("A");
       if (list.toString().equals("[A]")) {
           System.out.println("Passed test 19");
       } else {
           System.out.println("Failed test 19");
       }
       
       //test 20
       System.out.println("\nTest 20: addFirst(item)");
       list = new LinkedList<>();
       list.add("A");
       list.addFirst("B");
       if (list.toString().equals("[B, A]")) {
           System.out.println("Passed test 20");
       } else {
           System.out.println("Failed test 20");
       }
       
       //test 21
       System.out.println("\nTest 21: addFirst(item)");
       list = new LinkedList<>();
       list.addFirst("A");
       list.add("B");
       if (list.toString().equals("[A, B]")) {
           System.out.println("Passed test 21");
       } else {
           System.out.println("Failed test 21");
       }

       //test 22
       System.out.println("\nTest 22: removeFirst()");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.removeFirst();
       if (list.toString().equals("[B]")) {
           System.out.println("Passed test 22");
       } else {
           System.out.println("Failed test 22");
       }
       
     //test 23
       System.out.println("\nTest 5: removeFirst()");
       list = new LinkedList<>();
       list.add("A");
       list.removeFirst();
       if (list.toString().equals("[]")) {
           System.out.println("Passed test 23");
       } else {
           System.out.println("Failed test 23");
       }
       
     //test 24
       System.out.println("\nTest 24: removeFirst()");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.remove("B");
       list.removeFirst();
       if (list.toString().equals("[]")) {
           System.out.println("Passed test 24");
       } else {
           System.out.println("Failed test 24");
       }

       //test 25
       list = new LinkedList<>();
       System.out.println("\nTest 25: addLast(item)");
       list = new LinkedList<>();
       list.addLast("A");
       if (list.toString().equals("[A]")) {
           System.out.println("Passed test 25");
       } else {
           System.out.println("Failed test 25");
       }
       
       //test 26
       list = new LinkedList<>();
       System.out.println("\nTest 26: addLast(item)");
       list = new LinkedList<>();
       list.add("B");
       list.addLast("A");
       if (list.toString().equals("[B, A]")) {
           System.out.println("Passed test 26");
       } else {
           System.out.println("Failed test 26");
       }
       
       //test 27
       list = new LinkedList<>();
       System.out.println("\nTest 27: addLast(item)");
       list = new LinkedList<>();
       list.addLast("A");
       list.addLast("B");
       if (list.toString().equals("[A, B]")) {
           System.out.println("Passed test 27");
       } else {
           System.out.println("Failed test 27");
       }

       //test 28
       System.out.println("\nTest 28: removeLast()");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.removeLast();
       if (list.toString().equals("[A, B]")) {
           System.out.println("Passed test 28");
       } else {
           System.out.println("Failed test 28");
       }
       
       //test 29
       System.out.println("\nTest 29: removeLast()");
       list = new LinkedList<>();
       list.add("A");
       list.removeLast();
       if (list.toString().equals("[]")) {
           System.out.println("Passed test 29");
       } else {
           System.out.println("Failed test 29");
       }
       
       //test 30
       System.out.println("\nTest 30: removeLast()");
       list = new LinkedList<>();
       list.add("A");
       list.removeLast();
       if (list.toString().equals("[]")) {
           System.out.println("Passed test 30");
       } else {
           System.out.println("Failed test 30");
       }

       //test 31
       System.out.println("\nTest 31: set(pos, item)");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.set(0,  "D");
       if (list.toString().equals("[D, B, C]")) {
           System.out.println("Passed test 31");
       } else {
           System.out.println("Failed test 31");
       } 
       
       //test 32
       System.out.println("\nTest 32: set(pos, item)");
       list = new LinkedList<>();
       list.add("A");
       list.set(0,  "D");
       if (list.toString().equals("[D]")) {
           System.out.println("Passed test 32");
       } else {
           System.out.println("Failed test 32");
       } 
       
       //test 33
       System.out.println("\nTest 33: set(pos, item)");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.set(0,  "D");
       list.set(1,  "A");
       if (list.toString().equals("[D, A, C]")) {
           System.out.println("Passed test 33");
       } else {
           System.out.println("Failed test 33");
       }

       //test 34
       System.out.println("\nTest 34: remove(obj)");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.add("D");
       list.remove("D");
       if (list.toString().equals("[A, B, C]")) {
           System.out.println("Passed test 34");
       } else {
           System.out.println("Failed test 34");
       }

       //test 35
       System.out.println("\nTest 35: remove(obj)");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.add("D");
       list.remove("B");
       if (list.toString().equals("[A, C, D]")) {
           System.out.println("Passed test 35");
       } else {
           System.out.println("Failed test 35");
       }
       
       //test 36
       System.out.println("\nTest 36: remove(obj)");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.add("D");
       list.remove("D");
       list.remove("A");
       if (list.toString().equals("[B, C]")) {
           System.out.println("Passed test 36");
       } else {
           System.out.println("Failed test 36");
       }

       System.out.println("\ngetSubList() Tests:");
       //test 37
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.add("D");
       IList<String> subList = list.getSubList(0, 3);
       if (subList.toString().equals("[A, B, C]")) {
           System.out.println("Passed test 37");
       } else {
           System.out.println("Failed test 37");
       }

       //test 38
       subList = list.getSubList(0, 1);
       if (subList.toString().equals("[A]")) {
           System.out.println("Passed test 38");
       } else {
           System.out.println("Failed test 38");
       }

       //test 39
       subList = list.getSubList(0, 0);
       if (subList.toString().equals("[]")) {
           System.out.println("Passed test 39");
       } else {
           System.out.println("Failed test 39");
       }
       
       System.out.println("\nindexOf(val) Tests:");
       //test 40
       int index = list.indexOf("A");
       if (index == 0) {
           System.out.println("Passed test 40");
       } else {
           System.out.println("Failed test 40");
       }

       //test 41
       int index2 = list.indexOf("B");
       if (index2 == 1) {
           System.out.println("Passed test 41");
       } else {
           System.out.println("Failed test 41");
       }
       
       //test 42
       int index3 = list.indexOf("C");
       if (index3 == 2) {
           System.out.println("Passed test 42");
       } else {
           System.out.println("Failed test 42");
       }

       System.out.println("\nindexOf(val, pos) Tests:");
       //test 43
       list = new LinkedList<>();
       list.add("A");
       list.add("A");
       list.add("B");
       list.add("B");
       int index4 = list.indexOf("A", 0);
       if (index4 == 0) {
           System.out.println("Passed test 43");
       } else {
           System.out.println("Failed test 43");
       }

       //test 44
       int index5 = list.indexOf("C", 0);
       if (index5 == INVALID) {
           System.out.println("Passed test 44");
       } else {
           System.out.println("Failed test 44");
       }

       //test 45
       int index6 = list.indexOf("B", 3);
       if (index6 == 3) {
           System.out.println("Passed test 45");
       } else {
           System.out.println("Failed test 45");
       }

       System.out.println("\nremoveRange() Tests:");
       //test 46
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.add("D");
       list.removeRange(0, 3);
       if (list.toString().equals("[D]")) {
           System.out.println("Passed test 46");
       } else {
           System.out.println("Failed test 46");
       }
       
       //test 47
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.add("D");
       list.removeRange(0, 1);
       if (list.toString().equals("[B, C, D]")) {
           System.out.println("Passed test 47");
       } else {
           System.out.println("Failed test 47");
       }
       
       //test 48
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.add("D");
       list.removeRange(2, 3);
       if (list.toString().equals("[A, B, D]")) {
           System.out.println("Passed test 48");
       } else {
           System.out.println("Failed test 48");
       }
       
       //test 49
       System.out.println("\nequals() Tests:");
       list = new LinkedList<>();
       LinkedList list2 = new LinkedList<>();
       list.add("A");
       list.add("B");
       list2.add("A");
       list2.add("B");
       if (list.equals(list2)) {
           System.out.println("Passed test 49");
       } else {
           System.out.println("Failed test 49");
       }

       //test 50
       list = new LinkedList<>();
       LinkedList list3 = new LinkedList<>();
       list.add("A");
       list.add("B");
       list3.add("A");
       list3.add("B");
       list3.add("C");
       if (!list.equals(list3)) {
           System.out.println("Passed test 50");
       } else {
           System.out.println("Failed test 50");
       }

       //test 51
       list = new LinkedList<>();
       LinkedList list4 = new LinkedList<>();
       list.add("A");
       list.add("B");
       list4.add("B");
       list4.add("A");
       if (!list.equals(list4)) {
           System.out.println("Passed test 51");
       } else {
           System.out.println("Failed test 51");
       }
       
       System.out.println("\nIterator Tests:");
       System.out.println("\nhasNext() and next() Tests:");

       //test 52
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.add("D");
       subList = list.getSubList(0, list.size());
       Iterator<String> it = subList.iterator();
       if (it.hasNext()) {
           System.out.println("Passed test 52");
       } else {
           System.out.println("Failed test 52");
       }
       
       //test 53
       it.next();
       if(it.hasNext()) {
    	   System.out.println("Passed test 53");
       } else {
           System.out.println("Failed test 53");
       }
     
       //test 54
       it.next();
       it.next();
       if(it.hasNext()) {
    	   System.out.println("Passed test 54");
       } else {
           System.out.println("Failed test 54");
       }

       System.out.println("\nremove() Tests:");
       //test 55
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.add("D");
       Iterator<String> it2 = list.iterator();
       it2.next();
       it2.remove();
       if(list.toString().equals("[B, C, D]")) {
    	   System.out.println("Passed test 55");
       } else {
           System.out.println("Failed test 55");
       }

       //test 56
       it2.next();
       it2.remove();
       if(list.toString().equals("[C, D]")) {
    	   System.out.println("Passed test 56");
       } else {
           System.out.println("Failed test 56");
       }

       //test 57
       it2.next();
       it2.remove();
       if(list.toString().equals("[D]")) {
    	   System.out.println("Passed test 57");
       } else {
           System.out.println("Failed test 57");
       }
       
       //test 58
       System.out.println("\nget(pos) Tests:");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       if(list.get(0).equals("A")) {
    	   System.out.println("Passed test 58");
       } else {
           System.out.println("Failed test 58");
       }
       
       //test 59
       if(list.get(1).equals("B")) {
    	   System.out.println("Passed test 59");
       } else {
           System.out.println("Failed test 59");
       }
       
       //test 60
       if(list.get(2).equals("C")) {
    	   System.out.println("Passed test 60");
       } else {
           System.out.println("Failed test 60");
       }
       

       //test 61
       System.out.println("\niterator() Tests:");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       Iterator<String> iter = list.iterator();
       if(iter instanceof Iterator) {
    	   System.out.println("Passed test 61");
       } else {
           System.out.println("Failed test 61");
       }
       

       //test 62
       IList<String> sub = list.getSubList(0, 2);
       Iterator<String> iter2 = sub.iterator();
       if(iter2 instanceof Iterator) {
    	   System.out.println("Passed test 62");
       } else {
           System.out.println("Failed test 62");
       }
       

       //test 63
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.add("D");
       Iterator<String> it3 = list.iterator();
       if(it3 instanceof Iterator) {
    	   System.out.println("Passed test 63");
       } else {
           System.out.println("Failed test 63");
       }
       
   }

}