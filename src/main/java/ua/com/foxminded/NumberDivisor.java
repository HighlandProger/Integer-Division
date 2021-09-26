package ua.com.foxminded;

public class NumberDivisor {

    public NumberDivisor(int divisible, int divisor) {
        if (divisor == 0) {
            throw new NullPointerException("You can't divide by zero");
        }
        System.out.println(getDivisionString(divisible, divisor));
    }

    public String getDivisionString(int divisible, int divisor) {
        return headOfDivision(divisible, divisor) + tailOfDivision(divisible, divisor);
    }

    private String headOfDivision(int divisible, int divisor) {

        StringBuilder resultHeadString = new StringBuilder();
        StringBuilder secondString = new StringBuilder();

        int[] divisibleDigits = getDigitsFromNumber(Math.abs(divisible));
        int firstDigit = divisibleDigits[0];
        int secondStringDivisible;
        int secondStringNumber = Math.abs(divisor);

        String firstString = "_" + divisible + "|" + divisor;

        secondString.append(" ");

        if (divisible < 0) {
            secondString.append(" ");
        }

        if (firstDigit < divisor) {
            secondStringDivisible = addNextDigit(firstDigit, 0, Math.abs(divisible));
            secondStringNumber = secondStringDivisible - secondStringDivisible % divisor;
        }

        if (firstDigit / divisor >= 2) {
            secondStringDivisible = divisibleDigits[0];
            secondStringNumber = secondStringDivisible - secondStringDivisible % divisor;
        }

        secondString.append(secondStringNumber);
        secondString.append(addSpacesToLine(String.valueOf(secondStringNumber), Math.abs(divisible)));
        secondString.append("|-----");

        String thirdString = " -" + addSpacesToLine("-", divisible) + "|" + divisible / divisor;

        resultHeadString.append(firstString + "\n").append(secondString + "\n").append(thirdString + "\n");
        return resultHeadString.toString();
    }

    private String tailOfDivision(int divisible, int divisor) {

        boolean needExtraSpace = divisible < 0; //if divisible is negative adds space in the begin of line
        divisible = Math.abs(divisible);
        divisor = Math.abs(divisor);

        int[] divisibleDigits = getDigitsFromNumber(divisible);
        int firstDigit = divisibleDigits[0];
        int nextDivisible = firstDigit;

        StringBuilder resultTailString = new StringBuilder();

        for (int digitCount = 0; digitCount < divisibleDigits.length - 1; digitCount++) {

            if (isFirstDigitEqualsDivisor(firstDigit, divisor)) {

                if (isFirstIteration(digitCount)) {
                    nextDivisible = divisibleDigits[1];
                    addNextStringBlock(resultTailString, nextDivisible, divisor, digitCount + 1, needExtraSpace);
                    nextDivisible = getSurplusFromDivision(nextDivisible, divisor);
                    continue;
                }

                nextDivisible = addNextDigit(nextDivisible, digitCount, divisible);
                addNextStringBlock(resultTailString, nextDivisible, divisor, digitCount + 1, needExtraSpace);
                nextDivisible = getSurplusFromDivision(nextDivisible, divisor);

                if (digitCount == divisibleDigits.length - 2) {
                    resultTailString.append(getLastBlockString(digitCount + 1, nextDivisible));
                }
                continue;
            }

            if (isFirstDigitMoreThanDivisor(firstDigit, divisor)) {

                if (isFirstIteration(digitCount)) {
                    nextDivisible = missTheFirstBlock(nextDivisible, divisor, digitCount, divisible);
                    addNextStringBlock(resultTailString, nextDivisible, divisor, digitCount, needExtraSpace);
                    nextDivisible = getSurplusFromDivision(nextDivisible, divisor);
                    continue;
                }
            }

            if (!isFirstDigitMoreThanDivisor(firstDigit, divisor)) {

                if (isFirstIteration(digitCount)) {
                    nextDivisible = missTheFirstBlock(nextDivisible, divisor, digitCount, divisible);
                    digitCount++;
                    nextDivisible = getSurplusFromDivision(nextDivisible, divisor);
                    nextDivisible = addNextDigit(nextDivisible, digitCount, divisible);
                    addNextStringBlock(resultTailString, nextDivisible, divisor, digitCount, needExtraSpace);
                    nextDivisible = getSurplusFromDivision(nextDivisible, divisor);
                    continue;
                }

            }
            nextDivisible = addNextDigit(nextDivisible, digitCount, divisible);
            addNextStringBlock(resultTailString, nextDivisible, divisor, digitCount, needExtraSpace);
            nextDivisible = getSurplusFromDivision(nextDivisible, divisor);

            if (isLastIteration(digitCount, divisibleDigits)) {
                addLastBlockString(resultTailString, digitCount, nextDivisible, needExtraSpace);
            }

        }
        return resultTailString.toString();
    }

    private String addSpaces(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private int[] getDigitsFromNumber(int number) {
        String numberInString = String.valueOf(number);
        int[] digits = new int[numberInString.length()];
        for (int i = 0; i < numberInString.length(); i++) {
            digits[i] = numberInString.charAt(i) - '0';
        }
        return digits;
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

    private void addNextStringBlock(StringBuilder resultTailString, int nextDivisible, int divisor, int digitCount, boolean needExtraSpace) {
        if (needExtraSpace) {
            digitCount++;
        }
        resultTailString.append(getStringBlock(nextDivisible, divisor, digitCount));
    }

    private void addLastBlockString(StringBuilder resultTailString, int digitCount, int nextDivisible, boolean needExtraSpace) {
        if (needExtraSpace) {
            digitCount++;
        }
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
        return firstBlockLine + "\n" + secondBlockLine + "\n" + thirdBlockLine + "\n";
    }

    private String getLastBlockString(int digitCount, int nextDivisible) {
        return addSpaces(digitCount + 2) + nextDivisible;
    }

}
