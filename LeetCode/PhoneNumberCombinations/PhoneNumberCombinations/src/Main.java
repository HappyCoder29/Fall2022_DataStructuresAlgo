import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        printCombinationsPhone("123");
        System.out.println("Hello world!");
    }

    private static void printCombinationsPhone(String phoneNumber){
        int[] result = new int[phoneNumber.length()];
        int current = 0 ;

        printCombinationsPhone(result, current, phoneNumber);
    }

    private static void printCombinationsPhone(int[] result, int current, String phoneNumber){

        if(current == result.length){
            printPhoneNumbers(result, phoneNumber);
            return;
        }

        // We recurse
        int number = Integer.valueOf(phoneNumber.charAt(current));
        int m = getNumberOfTimesRecursion(number);

        if(m == -1){
            result[current] = -1;
            printCombinationsPhone(result, current + 1, phoneNumber);
        }else{
            for(int i = 0 ; i < m ; i ++){
                result[current] = i;
                printCombinationsPhone(result, current + 1, phoneNumber);
            }
        }

    }

    private static void printPhoneNumbers(int[] result, String phoneNumber){
        HashMap<Character, Character[] > map = new HashMap<>();
        map.put('2', new Character[]{'A','B','C'});
        map.put('3', new Character[]{'D','E','F'});
        map.put('4', new Character[]{'G','H','I'});
        map.put('5', new Character[]{'J','K','L'});
        map.put('6', new Character[]{'M','N','O'});
        map.put('7', new Character[]{'P','Q','R', 'S'});
        map.put('8', new Character[]{'T','U', 'V'});
        map.put('9', new Character[]{'W','X','Y', 'Z'});

        for(int i = 0; i < result.length; i ++){
            Character ch = phoneNumber.charAt(i);
            if(ch == '0' || ch == '1'){
                System.out.printf(String.valueOf(ch) + " ");
            }else {
                Character[] arr = map.get(ch);
                String sh = String.valueOf(arr[result[i]] );
                System.out.printf( sh + " " );
            }
        }
        System.out.println();
    }


    private static int getNumberOfTimesRecursion(int number){

        if(number == 0 || number == 1 ){
            return -1;
        }
        if ( (number >= 2 && number <= 6 ) || number == 8 ){
            return 2;
        }
        return  3;
    }


}