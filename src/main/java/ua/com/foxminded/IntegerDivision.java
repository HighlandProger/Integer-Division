package ua.com.foxminded;

public class IntegerDivision {

    public static void main(String[] args) {

        int divisible = 54532;
        int divisor = -6;

        NumberDivisor numberDivisor = new NumberDivisor();

        System.out.println(numberDivisor.getDivisionString(divisible, divisor));

    }
}
