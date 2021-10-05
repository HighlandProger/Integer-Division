package ua.com.foxminded;

public class IntegerDivision {

    public static void main(String[] args) {

        int divisible = 3345432;
        int divisor = 523;

        NumberDivisor numberDivisor = new NumberDivisor();

        System.out.println(numberDivisor.getDivisionString(divisible, divisor));
    }
}
