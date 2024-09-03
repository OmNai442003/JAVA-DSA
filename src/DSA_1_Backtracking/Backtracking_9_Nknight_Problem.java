package DSA_1_Backtracking;

public class Backtracking_9_Nknight_Problem {
    public static int tellyCounter;

    public static void main(String[] args) {
        Nknight(new boolean[3][3], 0, 0, 0, 3, "");
    }

    public static void Nknight(boolean[][] boxes, int column, int row, int knightsPlacedSoFar, int totalKnights, String answer){
//        This the point where answer to be printed.
        if (knightsPlacedSoFar == totalKnights){
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
//        Place the knight if it is safe to place.
        if (Is_Safe_To_Place(boxes, row, column)){
            boxes[row][column] = true;
//            Here it is known the row in which the knight is placed in that row know there is no more chances the another knight to be placed
//            so the another knight is shifted to the next row and starts from the placing right from the first column of that row
            Nknight(boxes, column + 1, row, knightsPlacedSoFar + 1, totalKnights, answer + "{ row - " + row + "," + "column " + column + " }");
            boxes[row][column] = false;
        }
        Nknight(boxes, column + 1, row, knightsPlacedSoFar, totalKnights, answer);
    }

    //    The below function will inform at the given block if it is safe to place knight or not
    public static boolean Is_Safe_To_Place(boolean [][] board, int currentRow, int currentColumn){
        int[] rowsPlacementArray = new int[] {-1, -2, 2, -2};
        int[] columnsPlacementArray = new int[] {2, 1, -1, -2};

        for (int i = 0; i < 4; i++){
            int tempRow = currentRow + rowsPlacementArray[i];
            int tempColumn = currentColumn + columnsPlacementArray[i];

//            Checking the boundary limit
            if (tempRow >= 0 && tempColumn >= 0 && tempRow < board.length && tempColumn < board[0].length){
//                Checking the knight placed or not
                if (board[tempRow][tempColumn]){
                    return false;
                }
            }
        }
        return true;
    }
}
