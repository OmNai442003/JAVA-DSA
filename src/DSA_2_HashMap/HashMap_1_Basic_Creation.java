package DSA_2_HashMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class HashMap_1_Basic_Creation {
    public static void main(String[] args) {
        HashMap<String, Integer> newMap = new HashMap<>();

//        To insert data into the map
        newMap.put("Rohan", 25);
        newMap.put("Mohan", 27);
        newMap.put("Sohan", 20);
        newMap.put("Raman", 23);
        newMap.put("Rajesh", 28);
//        To print the list
        System.out.println(newMap);
//        To get them individually
        System.out.println(newMap.get("Rajesh"));
//        To update the value
        newMap.put("Sohan", 60);
        System.out.println(newMap);
//        Check for the keys
        String key = "Rajesh";
        if (newMap.containsKey(key)){
            System.out.println("Yes, the key is present & the value is : " + newMap.get(key));
        }
        else {
            System.out.println("Key is not present");
        }
//        Print all the keys
        System.out.println(newMap.keySet() + "\nThe type is :- " + newMap.keySet().getClass().getSimpleName());
//        If key is not present and used get function the result is null
//        To get keys only
        Set<String> keysPresentInMap = newMap.keySet();
        System.out.println("The key set :- " + keysPresentInMap);
//        Collections for value, it is used because it is known that keys are unique
        Collection<Integer> theValuesCollection = newMap.values();
        System.out.println(theValuesCollection);
    }
}
