package DSA_1_Backtracking;

public class Backtracking_10_Blocked_Maze_Problem {
    public static void main(String[] args) {
        int[][] mazeMatrix = new int[][]{
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0}
        };
        Blocked_Maze(mazeMatrix, 0, 0, new boolean[mazeMatrix.length][mazeMatrix[0].length], "");
    }
    public static void Blocked_Maze(int[][] maze, int row, int column, boolean[][] visited, String answer){
//        To print the answer
        if (row == maze.length - 1 && column == maze[0].length - 1){
            System.out.println(answer);
        }
//        To return
//        This also to be blocked the wrong path
        if (row == -1 || row == maze.length || column == -1 || column == maze[0].length || maze[row][column] == 1 || visited[row][column]){
            return;
        }
//        To be mark visited
        visited[row][column] = true;
//        Top move
        Blocked_Maze(maze, row - 1, column, visited, answer + "T");
//        Down move
        Blocked_Maze(maze, row + 1, column, visited, answer + "D");
//        Left move
        Blocked_Maze(maze, row, column - 1, visited, answer + "L");
//        Right move
        Blocked_Maze(maze, row, column + 1, visited, answer + "R");
//        Undo the move
        visited[row][column] = false;
    }
}
