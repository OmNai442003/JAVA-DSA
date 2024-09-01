package DSA_1_Backtracking;

// The number of boxes and the number of queens will be given
public class Backtracking_1_Queen_Place {
    static int theNumberOfArrangement;
    public static void main(String[] args) {
        System.out.println("Problem to place to given queens in the given number of boxes");
        Queen_Permutation(new boolean[4], 0, 2, "");
    }
// The given function will do the permutation of the queens in the different boxes
    public static void Queen_Permutation(boolean[] boxes, int queensPlacedSoFar, int totalQueens, String answer){
        if (queensPlacedSoFar == totalQueens){
            theNumberOfArrangement++;
            System.out.println(theNumberOfArrangement + ".\t" + answer);
            return;
        }
        for (int i = 0; i < boxes.length; i++){
//            Need to check the condition that in the particular box queen should not be placed already
            if (!boxes[i]){
                boxes[i] = true;
//                Now the recursion step of placing the queen
                Queen_Permutation(boxes, queensPlacedSoFar + 1, totalQueens, answer + "q" + queensPlacedSoFar + "b" + i + " ");
//                Now need to undo the changes which is part of the backtracking
                boxes[i] = false;
            }
        }
    }
}
