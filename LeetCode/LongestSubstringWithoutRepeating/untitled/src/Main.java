import java.util.HashSet;
//https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class Main {
    public static void main(String[] args) {

        String str = "acbcde";
        System.out.println(longestNonRepeatingSubstring(str));

    }

    private static String longestNonRepeatingSubstring(String str){
        if(str.length() <=1){
            return str;
        }
        HashSet<Character> set = new HashSet<>();
        int start = 0;
        int maxLength = 0;
        String maxNonRepeated = "";

        for(int i = 0 ; i < str.length() ; i ++){
            if(!set.contains(str.charAt(i))){
                set.add(str.charAt(i));
                if(maxLength < i - start +1 ){
                    maxLength = i - start +1;
                    maxNonRepeated = str.substring(start, i +1);
                }
            }else{
                while(set.contains(str.charAt(i))){
                    set.remove(str.charAt(start));
                    start++;
                }
                set.add(str.charAt(i));
            }
        }
        return maxNonRepeated;
    }
}