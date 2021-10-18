package ua.com.foxminded;

public class IntegerDivision {

    public static void main(String[] args) {

        int dividend = 24532;
        int divisor = 23;

        IntegerDivisionFormatter integerDivisionFormatter = new IntegerDivisionFormatter();

        System.out.println(integerDivisionFormatter.getDivisionString(dividend, divisor));

    }
}
