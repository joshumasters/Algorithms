package jmasters.algorithms.exercises;

public class StrCmp {
    
    public static void strcmp(String str1, String str2){
        if(str1.equalsIgnoreCase(str2)){
            System.out.println("These strings are the same");
        } else if (str1.compareToIgnoreCase(str2) > 0){
            System.out.println("String 1 is higher lexicologically than string 2");
        } else if (str1.compareToIgnoreCase(str2) < 0){
            System.out.println("String 2 is higher lexicologically than string 1");
        }
    }
    
    public static void main(String[] args) {
        strcmp("Potato", "patato");
    }
}
