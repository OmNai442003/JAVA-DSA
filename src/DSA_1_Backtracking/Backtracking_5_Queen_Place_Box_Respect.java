package DSA_1_Backtracking;

public class Backtracking_5_Queen_Place_Box_Respect {
    static int tellyCounter = 0;
    public static void main(String[] args) {
        QueenPlaceBoxRespect(new boolean[4], 0, 2, 0, "");
    }

    public static void QueenPlaceBoxRespect(boolean[] boxes, int queensPlacedSoFar, int totalQueens, int column, String answer){
//        Base case 1
        if (queensPlacedSoFar == totalQueens){
            tellyCounter++;
            System.out.println(tellyCounter + ") " + answer);
            return;
        }
        if (boxes.length == column){
            return;
        }
//        Placing the queen to the box so occupying that position
        boxes[column] = true;
        QueenPlaceBoxRespect(boxes, queensPlacedSoFar + 1, totalQueens, column + 1, answer + 'b' + column);
//        Now the case of not placing the queen
        boxes[column] = false;
        QueenPlaceBoxRespect(boxes, queensPlacedSoFar, totalQueens, column + 1, answer);
    }
}
