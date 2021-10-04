package ua.com.foxminded;

public class IntegerDivision {

    public static void main(String[] args) {

        int divisible = 42296834;
        int divisor = 500;

        NumberDivisor numberDivisor = new NumberDivisor();

        System.out.println(numberDivisor.getDivisionString(divisible, divisor));
    }
}
