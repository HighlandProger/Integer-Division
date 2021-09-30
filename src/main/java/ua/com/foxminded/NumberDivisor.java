package ua.com.foxminded;

public class NumberDivisor {

    static final String NEXT_LINE = "\n";

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

        return firstString.append(NEXT_LINE).append(secondString + NEXT_LINE).append(thirdString + NEXT_LINE);
    }

    private StringBuilder getFirstString(int divisible, int divisor) {
        StringBuilder firstString = new StringBuilder("_");
        firstString.append(divisible).append("|").append(divisor);
        return firstString;
    }

    private StringBuilder getSecondString(int absDivisible, int absDivisor) {

        StringBuilder secondString = new StringBuilder(" ");

        int firstDigit = getDigitsFromNumber(absDivisible)[0];
        int nextDivisible;
        int secondStringNumber = absDivisor;

        if (firstDigit < absDivisor) {
            nextDivisible = addNextDigit(firstDigit, 0, absDivisible);
            secondStringNumber = nextDivisible - nextDivisible % absDivisor;
        }

        if (firstDigit / absDivisor >= 2) {
            nextDivisible = getDigitsFromNumber(absDivisible)[0];
            secondStringNumber = nextDivisible - nextDivisible % absDivisor;
        }

        secondString.append(secondStringNumber);
        secondString.append(addSpacesToLine(String.valueOf(secondStringNumber), absDivisible));
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

        int[] divisibleDigits = getDigitsFromNumber(absDivisible);
        int firstDigit = divisibleDigits[0];
        int nextDivisible = firstDigit;

        StringBuilder resultTailString = new StringBuilder();

        for (int digitCount = 0; digitCount < divisibleDigits.length - 1; digitCount++) {

            if (isFirstDigitEqualsDivisor(firstDigit, absDivisor)) {

                if (isFirstIteration(digitCount)) {
                    nextDivisible = divisibleDigits[1];
                    addNextStringBlock(resultTailString, nextDivisible, absDivisor, digitCount + 1);
                    nextDivisible = getSurplusFromDivision(nextDivisible, absDivisor);
                    continue;
                }

                nextDivisible = addNextDigit(nextDivisible, digitCount, absDivisible);
                addNextStringBlock(resultTailString, nextDivisible, absDivisor, digitCount + 1);
                nextDivisible = getSurplusFromDivision(nextDivisible, absDivisor);

                if (digitCount == divisibleDigits.length - 2) {
                    resultTailString.append(getLastBlockString(digitCount + 1, nextDivisible));
                }
                continue;
            }

            if (isFirstDigitMoreThanDivisor(firstDigit, absDivisor)) {

                if (isFirstIteration(digitCount)) {
                    nextDivisible = missTheFirstBlock(nextDivisible, absDivisor, digitCount, absDivisible);
                    addNextStringBlock(resultTailString, nextDivisible, absDivisor, digitCount);
                    nextDivisible = getSurplusFromDivision(nextDivisible, absDivisor);
                    continue;
                }
            }

            if (!isFirstDigitMoreThanDivisor(firstDigit, absDivisor)) {

                if (isFirstIteration(digitCount)) {
                    nextDivisible = missTheFirstBlock(nextDivisible, absDivisor, digitCount, absDivisible);
                    digitCount++;
                    nextDivisible = getSurplusFromDivision(nextDivisible, absDivisor);
                    nextDivisible = addNextDigit(nextDivisible, digitCount, absDivisible);
                    addNextStringBlock(resultTailString, nextDivisible, absDivisor, digitCount);
                    nextDivisible = getSurplusFromDivision(nextDivisible, absDivisor);
                    continue;
                }

            }
            nextDivisible = addNextDigit(nextDivisible, digitCount, absDivisible);
            addNextStringBlock(resultTailString, nextDivisible, absDivisor, digitCount);
            nextDivisible = getSurplusFromDivision(nextDivisible, absDivisor);

            if (isLastIteration(digitCount, divisibleDigits)) {
                addLastBlockString(resultTailString, digitCount, nextDivisible);
            }

        }
        return resultTailString;
    }

    private String addSpaces(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private int[] getDigitsFromNumber(int number) {

        int digitsCount = getDigitsCount(number);
        int[] digits = new int[digitsCount];

        for (int remainder = number, i = digitsCount - 1; remainder > 0; remainder /= 10, i--) {
            digits[i] = remainder % 10;
        }

        return digits;
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

    private boolean isFirstDigitMoreThanDivisor(int firstDigit, int divisor) {
        return firstDigit > divisor;
    }

    private boolean isFirstDigitEqualsDivisor(int firstDigit, int divisor) {
        return firstDigit == divisor;
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

    private boolean isLastIteration(int digitCount, int[] divisibleDigits) {
        return digitCount == divisibleDigits.length - 2;
    }

    private int missTheFirstBlock(int nextDivisible, int divisor, int digitCount, int divisible) {
        nextDivisible = getSurplusFromDivision(nextDivisible, divisor);
        nextDivisible = addNextDigit(nextDivisible, digitCount, divisible);
        return nextDivisible;
    }

    private int addNextDigit(int digit, int digitCount, int divisible) {
        int[] digits = getDigitsFromNumber(divisible);
        return Integer.parseInt("" + digit + digits[digitCount + 1]);
    }

    private String getStringBlock(int nextDivisible, int divisor, int digitCount) {
        String firstBlockLine = addSpaces(digitCount) + "_" + nextDivisible;
        String secondBlockLine = addSpaces(digitCount + 1) + (nextDivisible - nextDivisible % divisor);
        String thirdBlockLine = addSpaces(digitCount + 1) + "--";
        return firstBlockLine + NEXT_LINE + secondBlockLine + NEXT_LINE + thirdBlockLine + NEXT_LINE;
    }

    private String getLastBlockString(int digitCount, int nextDivisible) {
        return addSpaces(digitCount + 2) + nextDivisible;
    }

}
