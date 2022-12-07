import java.util.Stack;
//https://leetcode.com/problems/decode-string/
public class Main {
    public static void main(String[] args) {
        String str = "2[abc]3[cd]ef";
        System.out.println(decodeString(str));

        System.out.println("Hello world!");
    }


    private static String decodeString(String str){
        Stack<Character> stack = new Stack<>();

        for (Character ch : str.toCharArray()) {
            if(ch != ']'){
                stack.push(ch);
            }else{
                String s = "";
                while(stack.peek() != '['){
                    s = String.valueOf(stack.pop()) + s;
                }
                stack.pop();
                int numOfTimes = Integer.valueOf(String.valueOf(stack.pop()) );
                String newStr = "";
                for(int i = 0 ; i < numOfTimes; i ++){
                   newStr += s;
                }
                addValuesToStack(stack, newStr);
            }

        }

        return getStringFromStack(stack);


    }

    private static String  getStringFromStack(Stack<Character> stack){
        String str = "";
        while(!stack.isEmpty()){
            str =  String.valueOf(stack.pop()) + str;
        }
        return str;
    }
    private static void  addValuesToStack(Stack<Character> stack, String str){
        for (Character ch : str.toCharArray()) {
            stack.push(ch);
        }
    }
}