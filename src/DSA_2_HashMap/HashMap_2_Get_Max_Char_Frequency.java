package DSA_2_HashMap;

// This gives the max frequency of the character from the given string in O(n) time complexity

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMap_2_Get_Max_Char_Frequency {
    public static void main(String[] args) {
        String myRandomString = "aabbbabbbcaacccaaababababacccccababbabba";
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < myRandomString.length(); i++){
            char ch = myRandomString.charAt(i);
            if (map.containsKey(ch)){
                map.put(ch, map.get(ch) + 1);
            }
            else {
                map.put(ch, 1);
            }
        }
        System.out.println(map);
        int maxValue = -1;
        char maxChar = '\0';
//        To break down the hash map to entry map
        Set<Map.Entry<Character, Integer>> theMap = map.entrySet();
        for (Map.Entry<Character, Integer> entry : theMap){
            if (entry.getValue() > maxValue){
                maxValue = entry.getValue();
                maxChar = entry.getKey();
            }
        }
        System.out.println(maxChar + " " + maxValue);
    }
}
