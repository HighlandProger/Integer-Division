package ua.com.foxminded;

public class IntermediateDivisionResult {

    private final StringBuilder divisionString;
    private final int remainder;
    private final int lastDigit;

    IntermediateDivisionResult(int remainder, int digitCount, StringBuilder divisionString) {
        this.remainder = remainder;
        this.lastDigit = digitCount;
        this.divisionString = divisionString;
    }

    public StringBuilder getDivisionString() {
        return divisionString;
    }

    public int getRemainder() {
        return remainder;
    }

    public int getLastDigit() {
        return lastDigit;
    }

}

