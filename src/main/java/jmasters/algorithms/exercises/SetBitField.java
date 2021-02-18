package jmasters.algorithms.exercises;

public class SetBitField {
    //valueToWrite < 2 ^ fieldLength
    public static long setBitField(long valueToModify, int lowBit, int fieldLength, long valueToWrite){
        if(valueToWrite >= Math.pow(2, fieldLength)){
            System.out.println("valueToWrite must be < 2^fieldLength");
        } else {
            long clearValue = ((1<<fieldLength)-1)<<lowBit;
            clearValue = ~clearValue;
            valueToModify = valueToModify & clearValue;
            long setValue = valueToWrite << lowBit; 
            long valueToReturn = valueToModify | setValue;
            System.out.println("setValue is " + setValue);
            System.out.println("valueToReturn is " + valueToReturn);
            return valueToReturn;
        }
        return 0;
    }

    public static int addMethod(int a, int b)
    {
        // XOR a and b
        int c=a^b;

        // while ANDing a and b does not equal 0
        while((a&b) != 0)
        {
            // AND a and b and SHIFT << 1
            b=(a & b)<<1;
            
            a=c;

            //c = a (which is now c) XOR b
            c=a^b;
        }
        return c;
    }

    public static void main(String[] args) {
        // 93 = 0101 1101
        //10 = 1010
        // 173 = 1111 1101
        // setBitField(93, 4, 4, 10);
        System.out.println(addMethod(15, 305));
    }
}
