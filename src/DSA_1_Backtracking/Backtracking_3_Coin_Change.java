package DSA_1_Backtracking;

public class Backtracking_3_Coin_Change {
    static int indexPrinter;
    public static void main(String[] args) {
        Coin_Change(new int[] {1, 2, 5, 10}, 10, 0, "");
    }
    public static void Coin_Change(int[] denominationArray, int amount, int lastDenominationUsed, String answer){
        if (amount == 0){
            indexPrinter++;
            System.out.println(indexPrinter + ")\t" + answer);
            return;
        }
        for (int i = lastDenominationUsed; i < denominationArray.length; i++){
            if (amount >= denominationArray[i]){
                Coin_Change(denominationArray, amount - denominationArray[i], i, answer + denominationArray[i] + " ");
            }
        }
    }
}
