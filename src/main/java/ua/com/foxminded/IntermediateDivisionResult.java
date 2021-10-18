package ua.com.foxminded;

public class IntermediateDivisionResult {

    private final StringBuilder divisionString;
    private final int remainder;
    private final int lastDigitNumber;

    IntermediateDivisionResult(int remainder, int lastDigitNumber, StringBuilder divisionString) {
        this.remainder = remainder;
        this.lastDigitNumber = lastDigitNumber;
        this.divisionString = divisionString;
    }

    public StringBuilder getDivisionString() {
        return divisionString;
    }

    public int getRemainder() {
        return remainder;
    }

    public int getLastDigitNumber() {
        return lastDigitNumber;
    }

}

