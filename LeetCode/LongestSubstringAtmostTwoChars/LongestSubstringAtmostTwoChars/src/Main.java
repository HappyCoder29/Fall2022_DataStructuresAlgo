import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println( longestSubStringWithTwoChars("ccaabbb") );
        System.out.println("Hello world!");
    }

    public static String longestSubStringWithTwoChars(String str){
        if(str == null || str.length() <= 2){
            return str;
        }
        String maxString = "";
        int maxIndex = 0;
        int start = 0;
        HashMap<Character, Integer> map = new HashMap<>();
       for(int i = 0 ; i < str.length(); i ++) {
           Character ch = str.charAt(i);
            if(map.size() < 2){
                if(!map.containsKey(ch)){
                    map.put(str.charAt(i), i);
                    maxIndex = i;
                }
            }
            else{
                if(map.containsKey(ch)){
                    maxIndex = i;
                    if(maxString.length() < str.substring(start, i+1).length()){
                        maxString = str.substring(start, i+1);
                    }

                    continue;
                }
                map.remove(str.charAt(maxIndex));
                maxIndex = maxIndex + 1;
                start = maxIndex;

            }
           if(maxString.length() < str.substring(start, i+1).length()){
               maxString = str.substring(start, i+1);
           }
        }



        return maxString;

    }
}