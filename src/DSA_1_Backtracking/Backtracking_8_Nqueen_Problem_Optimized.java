package DSA_1_Backtracking;

public class Backtracking_8_Nqueen_Problem_Optimized {
    public static int tellyCounter;

    public static void main(String[] args) {
        NQueen(new boolean[4][4], 0, 0, 0, 4, "");
    }

    public static void NQueen(boolean[][] boxes, int column, int row, int queensPlacedSoFar, int totalQueens, String answer){
//        This the point where answer to be printed.
        if (queensPlacedSoFar == totalQueens){
            tellyCounter++;
            System.out.println(tellyCounter + ") " + answer);
            return;
        }
//        The case serves that if the vertical travelling limit exceeds then still the frame is generated
//        so need to just the roll the frame to downward direction and hence the call execution is
//        remaining that need to be done.
        if (boxes[0].length == column){
            column = 0;
            row++;
        }
        if (boxes.length == row){
            return;
        }
//        Place the queen if it is safe to place.
        if (Is_Safe_To_Place(boxes, row, column)){
            boxes[row][column] = true;
//            Here it is known the row in which the queen is placed in that row know there is no more chances the another queen to be placed
//            so the another queen is shifted to the next row and starts from the placing right from the first column of that row
            NQueen(boxes, 0, row + 1, queensPlacedSoFar + 1, totalQueens, answer + "{ row - " + row + "," + "column " + column + " }");
            boxes[row][column] = false;
        }
        NQueen(boxes, column + 1, row, queensPlacedSoFar, totalQueens, answer);
    }

    //    The below function will inform at the given block if it is safe to place queen or not
    public static boolean Is_Safe_To_Place(boolean [][] board, int currentRow, int currentColumn){
//        Checking the condition to place horizontally left
        int tempColumn = currentColumn - 1;
        while (tempColumn >= 0){
            if (board[currentRow][tempColumn]){
                return false;
            }
            tempColumn--;
        }
//        Checking the condition to place vertically upward
        int tempRow = currentRow - 1;
        while (tempRow >= 0){
            if (board[tempRow][currentColumn]){
                return false;
            }
            tempRow --;
        }
//        Checking for left diagonal position
        tempRow = currentRow - 1;
        tempColumn = currentColumn - 1;
        while (tempRow >= 0 && tempColumn >= 0){
            if (board[tempRow][tempColumn]){
                return false;
            }
            tempRow--;
            tempColumn--;
        }
//        Checking for the right diagonal position
        tempRow = currentRow - 1;
        tempColumn = currentColumn + 1;
        while (tempRow >= 0 && tempColumn < board[0].length){
            if (board[tempRow][tempColumn]){
                return false;
            }
            tempRow--;
            tempColumn++;
        }
        return true;
    }
}
