package dictionary_problem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class DictionaryShortest {

    //HashSet to store all the words from the dictionary.
    private static HashSet<String> words = null;
    private static int iterationNumber = 0;

    public static void main(String[] args) {
        //Test cases
        System.out.println(toWordLadder("fish", "mast"));
        System.out.println(toWordLadder("code", "data"));
        System.out.println(toWordLadder("clock", "pluck"));
        System.out.println(toWordLadder("daze", "rise"));
        System.out.println(toWordLadder("phone", "phase"));
    }

    public static String toWordLadder(String start, String end) {
        //Store dictionary words in the set if not stored before
        if (words == null) {
            words = new HashSet<>();
            Scanner in = null;
            try {
                in = new Scanner(new FileInputStream("src/dictionary_problem/words.txt"));
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
            while (in.hasNextLine()) {
                String s = (in.nextLine()).toUpperCase();
                words.add(s);
            }
            in.close();
        }
        //Program only works for uppercase words
        start = start.toUpperCase();
        end = end.toUpperCase();
        //ArrayList to store the valid word ladder
        ArrayList<String> wordladder = wordLadder(start, end);
        if (wordladder == null)
            return "Word Ladder does not exist.";
        else {
            //Print word ladder
            String s = "";
            for (int i = 0; i < wordladder.size() - 1; i++)
                s = s + wordladder.get(i).toUpperCase() + ", ";
            return (s + wordladder.get(wordladder.size() - 1)).toUpperCase();
        }
    }

    //Method to deal with special cases where word ladder does not exist
    public static ArrayList<String> wordLadder(String start, String end) {
        if (start.length() != end.length()) {
            System.out.print("Words have different lengths. ");
            return null;
        }
        if (start.equals(end)) {
            System.out.print("Words are equal. ");
            return null;
        }
        if (!words.contains(start) || !words.contains(end)) {
            System.out.print("Invalid English words. ");
            return null;
        }
        //Word ladder formation begins here
        ArrayList<ArrayList<String>> ladderlist = new ArrayList<>();
        ArrayList<String> wordladder = new ArrayList<>();
        wordladder.add(start);
        while (wordLadderHelper(start, end, wordladder, ladderlist, 0)) {
            ladderlist.add(wordladder);
            wordladder = new ArrayList<>();
            wordladder.add(start);
        }
        Collections.sort(ladderlist, Comparator.comparing(ArrayList::size));
        if (ladderlist.size() != 0)
            return ladderlist.get(0);
        else
            return null;
    }

    private static boolean wordLadderHelper(String start, String end, ArrayList<String> wordladder, ArrayList<ArrayList<String>> ladderlist, int index) {
        //If start and end words have become equal, return true
        if (start.equalsIgnoreCase(end))
            return true;
        //Loop from the zeroth index if index goes out of bounds
        if (index == start.length())
            return wordLadderHelper(start, end, wordladder, ladderlist, 0);
        //Proceed to the next character if characters found are equal and not the last characters
        if (start.charAt(index) == end.charAt(index) && iterationNumber == 0) {
            //Since start and end are not equal, and all other characters have been permuted, this ladder has reached a dead end
            if (index == start.length() - 1)
                return false;
            else
                return wordLadderHelper(start, end, wordladder, ladderlist, index + 1);
        }
        //Check if a direct match is possible by replacing a character from 'end'
        String newStart = "";
        if (iterationNumber == 0) {
            newStart = start.substring(0, index) + end.charAt(index) + start.substring(index + 1);
            newStart = newStart.toUpperCase();
            //Avoid a word circle
            if (wordladder.contains(newStart))
                return false;
            if (words.contains(newStart)) {
                if (ladderlist.size() != 0 && wordladder.size() >= Collections.max(ladderlist, Comparator.comparing(ArrayList::size)).size())
                    return false;
                wordladder.add(newStart);
                //After adding, start permuting from the least possible index
                int newIndex = (index == 0) ? 1 : 0;
                boolean flag = wordLadderHelper(newStart, end, wordladder, ladderlist, newIndex);
                if (flag) {
                    if (ladderListContainsStart(ladderlist, wordladder)) {
                        wordladder.remove(newStart);
                        return false;
                    }
                    return true;
                } else
                    //Remove newStart if its ladder reaches a dead end
                    wordladder.remove(newStart);
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            //Ignore current and direct replacement cases
            if (c == end.charAt(index) || c == start.charAt(index))
                continue;
            newStart = start.substring(0, index) + c + start.substring(index + 1);
            newStart = newStart.toUpperCase();
            if (wordladder.contains(newStart))
                continue;
            if (words.contains(newStart)) {
                if (ladderlist.size() != 0 && wordladder.size() >= Collections.max(ladderlist, Comparator.comparing(ArrayList::size)).size())
                    return false;
                wordladder.add(newStart);
                int newIndex = (index == 0) ? 1 : 0;
                int temp = iterationNumber;
                iterationNumber = 0; //For the newStart iteration
                boolean flag = wordLadderHelper(newStart, end, wordladder, ladderlist, newIndex);
                if (flag) {
                    if (ladderListContainsStart(ladderlist, wordladder)) {
                        wordladder.remove(newStart);
                        return false;
                    }
                    return true;
                } else {
                    //Remove newStart if its ladder reaches a dead end
                    iterationNumber = temp;
                    wordladder.remove(newStart);
                }
            }
        }
        //Ladder has reached a dead end, or next index should be permuted
        if (index == start.length() - 1) {
            if (iterationNumber == 0) {
                iterationNumber = 1;
                return wordLadderHelper(start, end, wordladder, ladderlist, 0);
            } else
                return false;
        } else
            return wordLadderHelper(start, end, wordladder, ladderlist, index + 1);
    }

    private static boolean ladderListContainsStart(ArrayList<ArrayList<String>> ladderlist, ArrayList<String> wordladder) {
        for (int i = 0; i < ladderlist.size(); i++) {
            ArrayList<String> temp = ladderlist.get(i);
            if (temp.size() < wordladder.size())
                return true;
            boolean flag = true;
            for (int j = 0; j < wordladder.size(); j++) {
                if (!temp.get(j).equals(wordladder.get(j))) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                return true;
        }
        return false;
    }
}
