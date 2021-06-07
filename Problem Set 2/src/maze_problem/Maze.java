package maze_problem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Maze {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //Creating the maze
        Map<Character, Node> maze = new HashMap<>();
        maze.put('A', new Node('0', '0', 'E', 'B'));
        maze.put('B', new Node('0', 'A', 'F', '0'));
        maze.put('C', new Node('0', '0', 'G', 'D'));
        maze.put('D', new Node('0', 'C', '0', '0'));
        maze.put('E', new Node('A', '0', 'I', '0'));
        maze.put('F', new Node('B', '0', '0', 'G'));
        maze.put('G', new Node('C', 'F', 'K', 'H'));
        maze.put('H', new Node('0', 'G', 'L', '0'));
        maze.put('I', new Node('E', '0', '0', 'J'));
        maze.put('J', new Node('0', 'I', '0', '0'));
        maze.put('K', new Node('G', '0', '0', '0'));
        maze.put('L', new Node('H', '0', '0', '0'));
        char currentNode = 'A', newNode, newDirection;
        while (currentNode != 'L') {
            System.out.println("You are in room " + currentNode + " of a maze of twisty little passages, all alike. You can go " + (maze.get(currentNode)).toString() + ".");
            newDirection = Character.toUpperCase(in.next().charAt(0));
            switch (newDirection) {
                case 'N':
                    newNode = maze.get(currentNode).getNorth();
                    if (newNode == '0') {
                        System.out.println("Invalid Direction!");
                        continue;
                    }
                    currentNode = newNode;
                    break;
                case 'W':
                    newNode = maze.get(currentNode).getWest();
                    if (newNode == '0') {
                        System.out.println("Invalid Direction!");
                        continue;
                    }
                    currentNode = newNode;
                    break;
                case 'S':
                    newNode = maze.get(currentNode).getSouth();
                    if (newNode == '0') {
                        System.out.println("Invalid Direction!");
                        continue;
                    }
                    currentNode = newNode;
                    break;
                case 'E':
                    newNode = maze.get(currentNode).getEast();
                    if (newNode == '0') {
                        System.out.println("Invalid Direction!");
                        continue;
                    }
                    currentNode = newNode;
                    break;
                default:
                    System.out.println("Invalid Direction!");
            }
        }
        System.out.println("Congratulations! You have reached the end of the maze (room L).");
    }
}
