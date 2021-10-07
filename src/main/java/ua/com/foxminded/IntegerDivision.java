package ua.com.foxminded;

public class IntegerDivision {

    public static void main(String[] args) {

        int divisible = 24532;
        int divisor = 1;

        NumberDivisor numberDivisor = new NumberDivisor();

        System.out.println(numberDivisor.getDivisionString(divisible, divisor));
    }
}
