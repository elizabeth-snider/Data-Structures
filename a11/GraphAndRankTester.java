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
 */

/*
 * Questions.
 *
 * 1. The assignment presents three ways to rank teams using graphs.
 * The results, especially for the last two methods are reasonable.
 * However if all results from all college football teams are included
 * some unexpected results occur. Explain the unexpected results. You may
 * have to do some research on the various college football divisions to
 * make an informed answer.
 *
 * The unexpected results are caused by all wins that are given equal weight. Some teams
 * are considered much stronger due to more funding and better players, so a good FCS win
 * rate means a lot less than a good FBS win rate.
 *
 * 2. Suggest another way of method of ranking teams using the results
 * from the graph. Thoroughly explain your method. The method can build
 * on one of the three existing algorithms.

 * You can modify the weighted algorithm by taking into account the "better" or "worse"
 * teams after running the original algorithm. From there, we can determine win percentages for
 * each team and  use those to multiply with the weight of their respective edges. This will
 * allow wins against better teams to worth more.
 */

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class GraphAndRankTester {

    /**
     * Runs tests on Graph classes and FootballRanker class.
     * Experiments involve results from college football
     * teams. Central nodes in the graph are compared to
     * human rankings of the teams.
     * @param args None expected.
     */
    public static void main(String[] args)  {
        String[][] edges = new String[][]{
                {"A", "B", "1"},
                {"B", "C", "3"},
                {"B", "D", "12"},
                {"C", "F", "3"},
                {"A", "G", "7"},
                {"D", "F", "4"},
                {"D", "G", "5"},
                {"D", "E", "6"}
        };

        Graph g1 = getGraph(edges, false);
        System.out.println("dijkstra tests");
        //test 1
        g1.dijkstra("A");
        String actual = g1.findPath("D").toString();
        String expect = "[A, B, C, F, D]";
        if(actual.equals(expect)){
            System.out.println("passed test 1");
        } else{
            System.out.println("FAILED test 1");
        }
        //test 2
        g1.dijkstra("A");
        String actual1 = g1.findPath("F").toString();
        String expect1 = "[A, B, C, F]";
        if(actual1.equals(expect1)){
            System.out.println("passed test 2");
        } else{
            System.out.println("FAILED test 2");
        }
        System.out.println();
        System.out.println("findAllPaths tests");;
        //test 3
        g1.findAllPaths(true);
        int actual2 = g1.getDiameter();
        int expect2 = 5;
        if(actual2 == expect2){
            System.out.println("passed test 3");
        } else{
            System.out.println("FAILED test 3");
        }
        //test 4
        double actual3 = g1.costOfLongestShortestPath();
        double expect3 = 17.0;
        if(actual3 == expect3){
            System.out.println("passed test 4");
        } else{
            System.out.println("FAILED test 4");
        }
        System.out.println();
        System.out.println("rootMeanSquareError tests");
        System.out.println("tests are not shown due to private access of method (can be found in " +
                        "the code)");
        System.out.println();
//        //test 5
//        //tests 5- 6 for printRootMeanSquare are commented out
//        //because the method is private
//        String actual4 = "2008ap_poll.txt";
//        String results = "div12008.txt";
//        FootballRanker ranker = new FootballRanker(results, actual4);
//        ArrayList<String> ranks = new ArrayList<String>();
//        TreeSet<AllPathsInfo> paths = new TreeSet<>();
//        ranks.add("Knights");
//        ranks.add("Hornets");
//        ranks.add("Quirrels");
//        ranks.add("Cornifers");
//        ranks.add("Zotes");
//        paths.add(new AllPathsInfo("Knights", 6, 6)); // avg = 1
//        paths.add(new AllPathsInfo("Hornets", 6, 12)); //avg = 2
//        paths.add(new AllPathsInfo("Quirrels", 6, 18)); //avg = 3
//        paths.add(new AllPathsInfo("Cornifers", 6, 24)); //avg = 4
//        paths.add(new AllPathsInfo("Zotes", 6, 30)); //avg = 5
//        double error = ranker.printRootMeanSquareError(ranks, paths, false);
//        if(error == 0.0){
//            System.out.println("passed test 5");
//        } else{
//            System.out.println("FAILED test 5");
//        }
//        //test 6
//        paths.remove(new AllPathsInfo("Cornifers", 6, 24));
//        double error2 = ranker.printRootMeanSquareError(ranks, paths, false);
//        if(error2 == 0.0){
//            System.out.println("passed test 5");
//        } else{
//            System.out.println("FAILED test 5");
//        }
    }

    private static Graph getGraph(String[][] edges, boolean directed) {
        Graph result = new Graph();
        for (String[] edge : edges) {
            result.addEdge(edge[0], edge[1], Double.parseDouble(edge[2]));
            // If edges are for an undirected graph add edge in other direction too.
            if (!directed) {
                result.addEdge(edge[1], edge[0], Double.parseDouble(edge[2]));
            }
        }
        return result;
    }
}
