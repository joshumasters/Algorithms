package jmasters.algorithms.exercises;

public class NumberConversion {
    // Given a positive int convert that to a string...but you are not allowed to
    // use any library routines that do the work
    // for you. You must decide what each character is individually and add to your
    // StringBuffer

    // Ideally, you would take an argument called radix--- which will be a number
    // between 2 and 36 inclusive...that specifies
    // what numbering system you are outputting to. Use digist 0-9 then a-z as
    // necessary.

    private static void addDigit(StringBuffer buffer, int value) {
        char digit;
        if (value < 10) {
            digit = (char) ('0' + value);
        } else {
            digit = (char) ('a' + value - 10);
        }
        buffer.append(digit);
    }

    private static void doMath(StringBuffer statement, int value, int base, int x) {
        System.out.println(value);
        while (value > 0) {
            int valueToAdd = (int) (value / (Math.pow(base, x)));
            int remainder = (int) (value % (Math.pow(base, x)));

            addDigit(statement, valueToAdd);
            x--;
            value = remainder;
        }
    }

    static public String convert(int value, int base) {
        StringBuffer statement = new StringBuffer();
        if (base < 2 || base > 36) {
            System.out.println("Base must be within 2 and 36");
        } else {
            int x = 0;
            while (value / (Math.pow(base, x + 1)) >= 1) {
                x++;
            }
            doMath(statement, value, base, x);
        }
        System.out.println(statement.toString());
        return statement.toString();
    }

    static private int getRealFigure(char figureToConvert, int base) throws Exception {
        int value = 0;
        if(figureToConvert >= 48 && figureToConvert <= 57) {
            value = figureToConvert - 48;
        } else if (figureToConvert >= 97 && figureToConvert <= 122) {
            value = figureToConvert - 87;
        } 
        if(value >= base || value == 0){
            System.out.println("Not a valid value/base combination");
            throw new Exception();
        }
        return value;
    }

    static public int reverseConvert(String valueToConvert, int base) throws Exception{
        // a == 97
        //'0' == 48
        int valueToReturn = 0;
        int numOfFigures = valueToConvert.length() - 1;
        for(char figure : valueToConvert.toCharArray()){
            int realFigure = getRealFigure(figure, base);
            valueToReturn += realFigure * (Math.pow(base, numOfFigures));
            numOfFigures--;      
        }
        System.out.println("valueToReturn is " + valueToReturn);
        return valueToReturn;
        
    }


    public static void main(String[] args) throws Exception {
        convert(56070987, 36);
        reverseConvert("111000100", 2);
    }
}
