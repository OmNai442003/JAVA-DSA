package DSA_2_HashMap;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMap_3_Array_Intersection {
    public static void main(String[] args) {
        int[] arr1 = new int[]{5, 7, 4, 2, 1};
        int[] arr2 = new int[]{2, 4, 3, 7, 10, 11};

//        Now the creating the hash map
        HashMap<Integer, Boolean> map = new HashMap<>();
//        Default insertion of the hash map
        for (int i : arr1) {
            map.put(i, false);
        }
        System.out.println(map);
        for (int j : arr2){
            if (map.containsKey(j)){
                map.put(j, true);
            }
        }
        System.out.println(map);
        ArrayList<Integer> listIntersectionValues = new ArrayList<>();
        for (int k : arr1){
            if (map.get(k)){
                listIntersectionValues.add(k);
            }
        }
        System.out.println(listIntersectionValues);
    }
}
