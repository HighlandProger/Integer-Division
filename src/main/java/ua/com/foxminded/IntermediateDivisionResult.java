package ua.com.foxminded;

public class IntermediateDivisionResult {

    NumberDivisor numberDivisor = new NumberDivisor();
    private String divisionString;
    private final int remainder;
    private final int lastDigit;

    IntermediateDivisionResult(int absDivisible, int absDivisor) {

        StringBuilder secondString = new StringBuilder(" ");

        int divisibleDigitsCount = numberDivisor.getDigitsCount(absDivisible);
        int nextDivisible = 0;
        int digitCount = 0;
        int secondNumber;

        while (nextDivisible < absDivisor) {

            if (digitCount == divisibleDigitsCount) {
                break;
            }
            nextDivisible = numberDivisor.addNextDigit(nextDivisible, digitCount, absDivisible);
            digitCount++;
        }
        secondNumber = nextDivisible - nextDivisible%absDivisor;
        secondString.append(secondNumber);
        secondString.append(numberDivisor.addSpacesToLine(String.valueOf(secondNumber), absDivisible));
        secondString.append("|-----");

        this.divisionString = secondString.toString();
        this.remainder = nextDivisible - absDivisor;
        this.lastDigit = digitCount;
    }

    public String getDivisionString() {
        return divisionString;
    }

    public int getRemainder() {
        return remainder;
    }

    public int getLastDigit() {
        return lastDigit;
    }

}
