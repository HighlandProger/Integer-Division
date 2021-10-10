package ua.com.foxminded;

public class NumberDivisor {

    private static final String NEXT_LINE = "\n";

    public String getDivisionString(int divisible, int divisor) {

        if (divisor == 0) {
            throw new IllegalArgumentException("You can't divide by zero");
        }

        int absDivisible = Math.abs(divisible);
        int absDivisor = Math.abs(divisor);

        return buildHeadOfDivision(absDivisible, absDivisor)
            .append(getTailOfDivision(absDivisible, absDivisor)).toString();
    }

    private StringBuilder buildHeadOfDivision(int absDivisible, int absDivisor) {

        StringBuilder firstString = buildFirstString(absDivisible, absDivisor);
        String secondString = buildSecondString(absDivisible, absDivisor);
        StringBuilder thirdString = buildThirdString(absDivisible, absDivisor);

        return firstString
            .append(NEXT_LINE)
            .append(secondString)
            .append(NEXT_LINE)
            .append(thirdString)
            .append(NEXT_LINE);
    }

    private StringBuilder buildFirstString(int divisible, int divisor) {

        StringBuilder firstString = new StringBuilder("_");
        firstString.append(divisible).append("|").append(divisor);
        return firstString;
    }

    private String buildSecondString(int absDivisible, int absDivisor) {

        IntermediateDivisionResult divisionResult = new IntermediateDivisionResult(absDivisible, absDivisor);

        return divisionResult.getDivisionString();
    }

    private StringBuilder buildThirdString(int absDivisible, int absDivisor) {

        StringBuilder thirdString = new StringBuilder(" -");
        thirdString.append(addSpacesToLine("-", absDivisible))
            .append("|").append(absDivisible / absDivisor);
        return thirdString;
    }

    private StringBuilder getTailOfDivision(int absDivisible, int absDivisor) {

        IntermediateDivisionResult divisionResult = new IntermediateDivisionResult(absDivisible, absDivisor);

        StringBuilder resultTailString = new StringBuilder();

        int nextDivisible = divisionResult.getRemainder();
        int digitCount = divisionResult.getLastDigit();
        int spaceCount;
        int divisibleDigitsCount = getDigitsCount(absDivisible);

        while (digitCount < divisibleDigitsCount) {

            while (nextDivisible < absDivisor) {

                if (digitCount == divisibleDigitsCount) {
                    break;
                }
                nextDivisible = addNextDigit(nextDivisible, digitCount, absDivisible);
                digitCount++;
            }

            spaceCount = digitCount - getDigitsCount(nextDivisible);
            addNextStringBlock(resultTailString, nextDivisible, absDivisor, spaceCount);
            nextDivisible = nextDivisible % absDivisor;
        }

        addLastBlockString(resultTailString, absDivisible, nextDivisible);

        return resultTailString;
    }

    private String addSpaces(int count) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private int[] getDigitsArrayFromNumber(int number) {

        int digitsCount = getDigitsCount(number);
        int[] digits = new int[digitsCount];

        for (int remainder = number, i = digitsCount - 1; remainder > 0; remainder /= 10, i--) {
            digits[i] = remainder % 10;
        }

        return digits;
    }

    protected int getDigitsCount(int number) {
        return (int) (Math.log10(number) + 1);
    }

    protected String addSpacesToLine(String firstSymbol, int divisible) {

        String spaces;
        String divisibleInString = String.valueOf(divisible);
        int lengthDifference = divisibleInString.length() - firstSymbol.length();
        spaces = addSpaces(lengthDifference);
        return spaces;
    }

    private void addNextStringBlock(StringBuilder resultTailString, int nextDivisible, int divisor, int digitCount) {
        resultTailString.append(getStringBlock(nextDivisible, divisor, digitCount));
    }

    private void addLastBlockString(StringBuilder resultTailString, int absDivisible, int nextDivisible) {
        resultTailString.append(getLastBlockString(absDivisible, nextDivisible));
    }

    protected int addNextDigit(int digit, int digitCount, int divisible) {

        int[] digits = getDigitsArrayFromNumber(divisible);
        if (digitCount > digits.length - 1) {
            throw new ArrayIndexOutOfBoundsException("No more digits in divisible");
        }
        return (digit * 10) + digits[digitCount];
    }

    private String getStringBlock(int nextDivisible, int divisor, int digitCount) {

        String firstBlockLine = addSpaces(digitCount) + "_" + nextDivisible;
        String secondBlockLine = addSpaces(digitCount + 1) + (nextDivisible - nextDivisible % divisor);
        String thirdBlockLine = addSpaces(digitCount + 1) + "--";
        return firstBlockLine + NEXT_LINE + secondBlockLine + NEXT_LINE + thirdBlockLine + NEXT_LINE;
    }

    private String getLastBlockString(int absDivisible, int nextDivisible) {

        int absDivisibleLength = getDigitsCount(absDivisible);
        int nextDivisibleLength = getDigitsCount(nextDivisible);

        if (nextDivisible == 0) {
            nextDivisibleLength = 1;
        }

        int resultLength = absDivisibleLength - nextDivisibleLength + 1;

        return addSpaces(resultLength) + nextDivisible;
    }

}
