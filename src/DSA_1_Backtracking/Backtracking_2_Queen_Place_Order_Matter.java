package DSA_1_Backtracking;

public class Backtracking_2_Queen_Place_Order_Matter {
    static int theNumberOfArrangement;
    public static void main(String[] args) {
        System.out.println("Problem to place to given queens in the given number of boxes where order matters");
        Queen_Combination(new boolean[4], 0, 2, "", -1);
    }

    public static void Queen_Combination(boolean[] boxes, int queensPlacedSoFar, int totalQueens, String answer, int lastBoxUsed){
        if (queensPlacedSoFar == totalQueens){
            theNumberOfArrangement++;
            System.out.println(theNumberOfArrangement + ".\t" + answer);
            return;
        }
        for (int i = lastBoxUsed + 1; i < boxes.length; i++){
//            Need to check the condition that in the particular box queen should not be placed already
                boxes[i] = true;
//                Now the recursion step of placing the queen
                Queen_Combination(boxes, queensPlacedSoFar + 1, totalQueens, answer + "q" + queensPlacedSoFar + "b" + i + " ", i);
//                Now need to undo the changes which is part of the backtracking
                boxes[i] = false;
        }
    }
}
