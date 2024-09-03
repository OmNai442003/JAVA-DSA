package DSA_1_Backtracking;

public class Backtracking_6_Queen_Place_Box_Respect_2D {
    static int tellyCounter;
    public static void main(String[] args) {
        Queen_Place_Combination_Box_Respect_2D(new boolean[2][2], 0, 0, 0, 2, "");
    }

    public static void Queen_Place_Combination_Box_Respect_2D(boolean[][] boxes, int column, int row, int queensPlacedSoFar, int totalQueens, String answer){
//        This the point where answer to be printed.
        if (queensPlacedSoFar == totalQueens){
            tellyCounter++;
            System.out.println(tellyCounter + ") " + answer);
            return;
        }
//        The case serves that if the vertical travelling limit exceeds then still the frame is generated so need to just the roll the frame to downward direction and hence the call execution is remaining that need to be done.
        if (boxes[0].length == column){
            column = 0;
            row++;
        }
        if (boxes.length == row){
            return;
        }
//        Call to place the queen
        Queen_Place_Combination_Box_Respect_2D(boxes, column + 1, row, queensPlacedSoFar + 1, totalQueens, answer + "{ row - " + row + "," + "column " + column + " }" + "\t");
        Queen_Place_Combination_Box_Respect_2D(boxes, column + 1, row, queensPlacedSoFar, totalQueens, answer);
    }
}
