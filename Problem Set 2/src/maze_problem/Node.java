package maze_problem;

public class Node {
    private char North;
    private char West;
    private char South;
    private char East;

    public Node(char north, char west, char south, char east) {
        North = north;
        West = west;
        South = south;
        East = east;
    }

    public char getNorth() {
        return North;
    }

    public void setNorth(char north) {
        North = north;
    }

    public char getWest() {
        return West;
    }

    public void setWest(char west) {
        West = west;
    }

    public char getSouth() {
        return South;
    }

    public void setSouth(char south) {
        South = south;
    }

    public char getEast() {
        return East;
    }

    public void setEast(char east) {
        East = east;
    }

    public String toString() {
        int possibleDirections = 0;
        boolean directionArray[] = new boolean[4];
        String lastDirection = "";
        if (North != '0') {
            possibleDirections++;
            directionArray[0] = true;
            lastDirection = "north";
        } else
            directionArray[0] = false;
        if (West != '0') {
            possibleDirections++;
            directionArray[1] = true;
            lastDirection = "west";
        } else
            directionArray[1] = false;
        if (South != '0') {
            possibleDirections++;
            directionArray[2] = true;
            lastDirection = "south";
        } else
            directionArray[2] = false;
        if (East != '0') {
            possibleDirections++;
            directionArray[3] = true;
            lastDirection = "east";
        } else
            directionArray[3] = false;

        String directions = "";
        if (possibleDirections == 1)
            return lastDirection;
        for (int i = 0; i < directionArray.length; i++) {
            if (directionArray[i]) {
                if (i == 0 && lastDirection != "north")
                    directions = directions + "north" + ((possibleDirections > 2) ? "," : "") + " ";
                if (i == 1 && lastDirection != "west")
                    directions = directions + "west" + ((possibleDirections > 2) ? "," : "") + " ";
                if (i == 2 && lastDirection != "south")
                    directions = directions + "south" + ((possibleDirections > 2) ? "," : "") + " ";
                if (i == 3 && lastDirection != "east")
                    directions = directions + "east" + ((possibleDirections > 2) ? "," : "") + " ";
            }
        }
        return (directions + "or " + lastDirection);
    }
}
