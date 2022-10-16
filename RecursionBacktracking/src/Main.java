import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        //generateMarySequence(3, 10);

       // printCombinations(3, "ABCD");

        String[] states = {"Active", "Pending", "Blocked", "Soft Blocked"};

        String[] coins = {"1", "5", "10", "25", "100"};

       // generateAllCoins(3, coins);

        //generateAllSubSets(3, "ABC");

        //generateAllValidParenthesis(5);

       // printPermutations(3, "ABCDEF");
        String[] stateNames = {"AL", "AK", "AZ", "AR", "CA","CO","CT", "DE", "DC", "FL", "GA", "HI","ID","IL", "IN","IA", "KS","KY", "LA","ME", "MD","MA","MI","MN", "MS","MO", "MT", "NB", "NV","NH","NJ", "NM", "NY", "NC", "ND", "OH","OK",
        "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" };

        int[] statesCount = {9,3,11,6,55,9,7,3,3,29,16,4,4,20,11,6,6,8,8,4,
                10,11,16,10,6,10,3,5,6,4,14,5,29,15,3,18,7,7,20,4,9,3,11,38,6,3,13,12,5,10,3};
        printAllPossibleTieSituations(statesCount, stateNames);
    }
    private static void generateBinarySequence(int size){
        if(size <= 0 ){
            return;
        }

        int[] result = new int[size];
        int current = 0;
        generateBinarySequence(result, current);
    }

    private static void generateBinarySequence(int[] result, int current){

        if(current == result.length){
            printArray(result);
            return;
        }

        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            generateBinarySequence(result, current +1);
        }

    }

    private static void generateTernarySequence(int size){
        if(size <= 0 ){
            return;
        }
        int[] result = new int[size];
        int current = 0;
        generateTernarySequence(result, current);
    }

    private static void generateTernarySequence(int[] result, int current){
        if(current == result.length){
            printArray(result);
            return;
        }

        for(int i = 0 ; i < 3; i ++){
            result[current] = i;
            generateTernarySequence(result, current +1);
        }
    }


    private static void generateMarySequence(int size, int m){
        if(size <= 0 ){
            return;
        }
        int[] result = new int[size];
        int current = 0;
        generateMarySequence(result, current, m);
    }

    private static void generateMarySequence(int[] result, int current, int m){
        if(current == result.length){
            printArray(result);
            return;
        }

        for(int i = 0 ; i < m; i ++){
            result[current] = i;
            generateMarySequence(result, current +1, m);
        }
    }
    private static void printArray(int[] arr){
        for (int i: arr) {
            System.out.printf(i + " ");
        }
        System.out.println();
    }


    private static void printCombinations(int size, String str){
        if(size <= 0){
            return;
        }

        int[] result = new int[size];
        int current = 0;
        printCombinations(result, current, str);
    }
    private static void printCombinations(int[] result, int current, String str){

        if(current == result.length){
            printCombinations(result, str);
            return;
        }

        for(int i = 0 ; i < str.length(); i ++){
            result[current] = i;
            printCombinations(result, current +1, str);
        }
    }

    private static void printCombinations(int[] result, String str){
        for(int i = 0 ; i < result.length; i ++){
            System.out.printf(str.charAt(result[i]) + " ");
        }
        System.out.println();
    }


    private static void generateAllStates(int size, String[] states){
        if(size <= 0){
            return;
        }

        int[] result = new int[size];
        int current = 0;
        generateAllStates(result, current, states);
    }

    private static void generateAllStates(int[] result, int current, String[] states) {

        if(current == result.length){
            printStates(result, states);
            return;
        }

        for(int i = 0 ; i < states.length; i ++){
            result[current] = i;
            generateAllStates(result, current +1, states);
        }
    }

    private static void printStates(int[] result, String[] states){
        for(int i = 0 ; i < result.length; i ++){
            System.out.printf(states[result[i]] + " -> ");
        }
        System.out.println("END");
    }


    private static void generateAllCoins(int size, String[] coins){
        if(size <= 0){
            return;
        }

        int[] result = new int[size];
        int current = 0;
        generateAllCoins(result, current, coins);
    }

    private static void generateAllCoins(int[] result, int current, String[] coins) {

        if(current == result.length){
            printAllCoins(result, coins);
            return;
        }

        for(int i = 0 ; i < coins.length; i ++){
            result[current] = i;
            generateAllCoins(result, current +1, coins);
        }
    }

    private static void printAllCoins(int[] result, String[] states){
        for(int i = 0 ; i < result.length; i ++){
            System.out.printf(states[result[i]] + " -> ");
        }
        System.out.println("END");
    }


    private static void generateAllSubSets(int size, String str){
        if(size <= 0 ){
            return;
        }

        int[] result = new int[size];
        int current = 0 ;
        generateAllSubSets(result, current, str);

    }

    private static void generateAllSubSets(int[] result, int current, String str){

        if(current == result.length){
            printSubsets(result, str);
            return;
        }

        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            generateAllSubSets(result, current +1, str);
        }

    }

    private static void printSubsets(int[] result, String str){
        System.out.printf("{ ");
        for(int i = 0 ; i < result.length; i ++){
            if(result[i] == 1){
                System.out.printf(str.charAt(i) + " ");
            }
        }
        System.out.println(" }");

    }

    private static void findNumberOfWaysToReturnCoins(int[] coins, int total){
        if(coins == null || coins.length == 0){
            return;
        }

        int [] result = new int[coins.length];
        int current = 0;
        findNumberOfWaysToReturnCoins(result, current, coins, total);
    }

    private static void findNumberOfWaysToReturnCoins(int[] result, int current, int[] coins, int total){
        if(result.length == current){
            printCoinsSumToTotal(result, coins, total)  ;
            return;
        }
        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            findNumberOfWaysToReturnCoins(result, current +1, coins, total);
        }
    }

    private static void printCoinsSumToTotal(int[] result, int[] coins, int total){
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        int currentTotal = 0;
        for(int i = 0 ; i < result.length; i ++){
            if(result[i] == 1){
                sb.append(coins[i] + " ");
                currentTotal += coins[i];
            }
        }
        sb.append(" }");

        if(currentTotal == total){
            System.out.println(sb.toString());
        }
    }


    private static void generateAllValidParenthesis(int size){
        if(size <= 0 ){
            return;
        }
        int[] result = new int[size*2];
        int current = 0;
        generateAllValidParenthesis(result, current);
    }

    private static void generateAllValidParenthesis(int[] result, int current){
        if(current == result.length){
            printParenthesis(result);
            return;
        }
        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            generateAllValidParenthesis(result, current + 1);
        }
    }

    private static void printParenthesis(int[] result){
        StringBuilder sb = new StringBuilder();
        for (int i: result) {
            sb.append( i == 0 ? "(" : ")");
        }
        if(isValidparenthesis(sb.toString())){
            System.out.println(sb.toString());
        }
    }

    private static boolean isValidparenthesis(String str){
        Stack<Character> stack = new Stack<>();
        for (Character ch : str.toCharArray()) {
            if(ch == '('){
                stack.push(ch);
            }else{
                if(stack.empty()){
                    return false;
                }
                stack.pop();
            }
        }
        return  stack.empty();
    }

    private static void printPermutations(int size, String str){
        if(size <= 0 ){
            return;
        }

        int[] result = new int[size];
        int current = 0;
        printPermutations(result, current, str);
    }

    private static void printPermutations(int[] result, int current, String str){

        if(current == result.length){
            printCombinations(result, str);
            return;
        }

        for(int i = 0 ; i < str.length(); i ++){
            if(isValidPermutation(result, current, i)){
                result[current] = i;
                printPermutations(result, current +1, str);
            }

        }

    }

    private static boolean isValidPermutation(int[] result, int current , int value){
        for(int i = 0 ; i < current; i ++){
            if(result[i] == value){
                return false;
            }
        }
        return true;
    }


    public static void printAllPossibleTieSituations(int[] states, String[] stateNames){
        if(states == null || states.length == 0 ){
            return;
        }
        int[] result = new int[states.length];
        int curent = 0;
        int total = 0;
        for (int i : states) {
            total += i;
        }
        printAllPossibleTieSituations(result, curent, states, stateNames,  total);

    }
    private static  void  printAllPossibleTieSituations(int[] result, int current, int[] states, String[] stateNames, int total){
        if(current== result.length){
            printElectionCombination(result, states,stateNames,  total);
            return;
        }

        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            printAllPossibleTieSituations(result, current+1, states,stateNames, total);
        }
    }

    private static void printElectionCombination(int[] result, int[] states, String[] stateNames,  int total){

        int bidenTotal = 0;

        List<String> bidenStates = new LinkedList<>();
        List<String> trumpStates = new LinkedList<>();

        for(int i = 0 ; i < result.length; i ++){
            if( result[i] == 0){
                bidenTotal += states[i];
                bidenStates.add(stateNames[i]);
            }else{
                trumpStates.add(stateNames[i]);
            }
        }
        int trumpTotal = total - bidenTotal;
        if(trumpTotal == bidenTotal){
            System.out.println("Biden Votes = " + bidenTotal  + " Trump Votes = " + trumpTotal);
            System.out.println("Biden States:");
            for (String state: bidenStates) {
                System.out.printf(state + ", ");
            }
            System.out.println();
            System.out.println("Trump States:");
            for (String state: trumpStates) {
                System.out.printf(state + ", ");
            }
            System.out.println();
            System.out.println("******************************");
        }




    }
}