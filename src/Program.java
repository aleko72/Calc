import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Program {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (;;){
            System.out.println();
            System.out.println("Введите операцию:");
            try {
                String result = calc(reader.readLine());
                System.out.println(result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String calc(String input){
        String[] args = input.split(" ");
        if(args.length != 3) throw new IllegalArgumentException();
        int x, y, result;
        boolean isArabicOperation = true;
        if(checkArabicNumber(args[0]) && checkArabicNumber(args[2])){
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[2]);
        } else if (checkRomanNumber(args[0]) && checkRomanNumber(args[2])) {
            x = convertRomanToArabicNumber(args[0]);
            y = convertRomanToArabicNumber(args[2]);
            isArabicOperation = false;
        }else {
            throw new IllegalArgumentException();
        }

        switch (args[1]){
            case "+":
                result = x + y;
                break;
            case "-":
                result = x - y;
                break;
            case "*":
                result = x * y;
                break;
            case "/":
                if(y == 0) throw new IllegalArgumentException();
                result = x / y;
                break;
            default: throw new IllegalArgumentException();
        }
        if(isArabicOperation){
            return String.valueOf(result);
        }else {
            if(result <= 0) {
                throw new IllegalArgumentException();
            }else{
                return convertArabicToRomanNumber(result);
            }
        }
    }

    static boolean checkArabicNumber(String arg){
        for (int i = 0; i < arg.length(); i++){
            if(!Character.isDigit(arg.charAt(i)))
                return false;
        }
        return true;
    }

    static boolean checkRomanNumber(String arg){
        for (RomanNumbers number : RomanNumbers.values()){
            if(number.name().equals(arg)) return true;
        }
        return false;
    }

    static int convertRomanToArabicNumber(String arg){
        for (RomanNumbers number : RomanNumbers.values()){
            if(number.name().equals(arg)) return number.ordinal() + 1;
        }
        throw new IllegalArgumentException();
    }

    static String convertArabicToRomanNumber(int arg){
        int index = arg - 1;
        if(index < 0 || index > RomanNumbers.values().length - 1){
            throw new IllegalArgumentException();
        }
        return RomanNumbers.values()[index].name();
    }

    enum RomanNumbers{
        I, II, III, IV, V, VI, VII, VIII, IX, X
    }
}