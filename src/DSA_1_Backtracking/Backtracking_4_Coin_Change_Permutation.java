package DSA_1_Backtracking;

// Here in this code the order doesn't matter so we consider all the permutations of the given coin
public class Backtracking_4_Coin_Change_Permutation {
    static int indexPrinter;
    public static void main(String[] args) {
        Coin_Change_Permutation(new int[] {1, 2, 5, 10}, 10, "");
    }
    public static void Coin_Change_Permutation(int[] denominationArray, int amount, String answer){
        if (amount == 0){
            indexPrinter++;
            System.out.println(indexPrinter + ")\t" + answer);
            return;
        }
        for (int j : denominationArray) {
            if (amount >= j) {
                Coin_Change_Permutation(denominationArray, amount - j, answer + j + " ");
            }
        }
    }
}
