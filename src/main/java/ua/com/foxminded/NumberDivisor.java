package ua.com.foxminded;

public class NumberDivisor {

    private static final String NEXT_LINE = "\n";

    public String getDivisionString(int divisible, int divisor) {

        if (divisor == 0) {
            throw new IllegalArgumentException("You can't divide by zero");
        }

        int absDivisible = Math.abs(divisible);
        int absDivisor = Math.abs(divisor);

        return getHeadOfDivision(absDivisible, absDivisor)
            .append(getTailOfDivision(absDivisible, absDivisor)).toString();
    }

    private StringBuilder getHeadOfDivision(int absDivisible, int absDivisor) {

        StringBuilder firstString = getFirstString(absDivisible, absDivisor);
        StringBuilder secondString = getSecondString(absDivisible, absDivisor);
        StringBuilder thirdString = getThirdString(absDivisible, absDivisor);

        return firstString
            .append(NEXT_LINE)
            .append(secondString)
            .append(NEXT_LINE)
            .append(thirdString)
            .append(NEXT_LINE);
    }

    private StringBuilder getFirstString(int divisible, int divisor) {

        StringBuilder firstString = new StringBuilder("_");
        firstString.append(divisible).append("|").append(divisor);
        return firstString;
    }

    private StringBuilder getSecondString(int absDivisible, int absDivisor) {

        int nextDivisible = getDigitsFromNumber(absDivisible, getDigitsCount(absDivisor));
        int secondNumber;

        if (nextDivisible < absDivisor) {
            nextDivisible = addNextDigit(nextDivisible, 0, absDivisible);
        }

        if (nextDivisible / absDivisor >= 2) {
            secondNumber = nextDivisible - nextDivisible % absDivisor;
        } else {
            secondNumber = absDivisor;
        }

        StringBuilder secondString = new StringBuilder(" ");
        secondString.append(secondNumber);
        secondString.append(addSpacesToLine(String.valueOf(secondNumber), absDivisible));
        secondString.append("|-----");

        return secondString;
    }

    private StringBuilder getThirdString(int absDivisible, int absDivisor) {

        StringBuilder thirdString = new StringBuilder(" -");
        thirdString.append(addSpacesToLine("-", absDivisible))
            .append("|").append(absDivisible / absDivisor);
        return thirdString;
    }

    private StringBuilder getTailOfDivision(int absDivisible, int absDivisor) {

        StringBuilder resultTailString = new StringBuilder();

        int nextDivisible = 0;
        int digitCount = 0;
        int spaceCount = 0;

        outer:
        for (int iterator = 0; digitCount < getDigitsCount(absDivisible); iterator++) {

            while (nextDivisible < absDivisor) {

                if (digitCount == getDigitsCount(absDivisible)) {
                    spaceCount = digitCount - getDigitsCount(nextDivisible) - 1;
                    break outer;
                }
                nextDivisible = addNextDigit(nextDivisible, digitCount, absDivisible);
                digitCount++;
            }

            if (isFirstIteration(iterator)) {
                nextDivisible = getSurplusFromDivision(nextDivisible, absDivisor);
                spaceCount++;
                continue;
            }
            spaceCount = digitCount - getDigitsCount(nextDivisible);
            addNextStringBlock(resultTailString, nextDivisible, absDivisor, spaceCount);
            nextDivisible = getSurplusFromDivision(nextDivisible, absDivisor);
        }

        addLastBlockString(resultTailString, spaceCount + 2, nextDivisible);

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

    public int getDigitsFromNumber(int number, int countOfDigits) {

        int[] digits = getDigitsArrayFromNumber(number);
        int partOfNumber = digits[0];

        for (int i = 1; i < countOfDigits; i++) {
            partOfNumber = partOfNumber * 10 + digits[i];
        }

        return partOfNumber;
    }

    private int getDigitsCount(int number) {
        return (int) (Math.log10(number) + 1);
    }

    private String addSpacesToLine(String firstSymbol, int divisible) {

        String spaces;
        String divisibleInString = String.valueOf(divisible);
        int lengthDifference = divisibleInString.length() - firstSymbol.length();
        spaces = addSpaces(lengthDifference);
        return spaces;
    }

    private boolean isFirstIteration(int digitCount) {
        return digitCount == 0;
    }

    private int getSurplusFromDivision(int nextDivisible, int divisor) {
        return nextDivisible % divisor;
    }

    private void addNextStringBlock(StringBuilder resultTailString, int nextDivisible, int divisor, int digitCount) {
        resultTailString.append(getStringBlock(nextDivisible, divisor, digitCount));
    }

    private void addLastBlockString(StringBuilder resultTailString, int digitCount, int nextDivisible) {
        resultTailString.append(getLastBlockString(digitCount, nextDivisible));
    }

    private int addNextDigit(int digit, int digitCount, int divisible) {

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

    private String getLastBlockString(int digitCount, int nextDivisible) {
        return addSpaces(digitCount) + nextDivisible;
    }

}
