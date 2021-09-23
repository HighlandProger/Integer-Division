package ua.com.foxminded;

public class NumberDivisor {

    private final int divisible;
    private final int divisor;
    private final int[] divisibleDigits;

    public NumberDivisor(int divisible, int divisor) {
        this.divisible = divisible;
        this.divisor = divisor;
        this.divisibleDigits = getDigitsFromNumber(divisible);
    }

    private String addSpaces(int countFromStart) {
        String space = "";
        for (int i = 0; i < countFromStart; i++) {
            space += " ";
        }
        return space;
    }

    private int[] getDigitsFromNumber(int number) {
        String numberInString = String.valueOf(number);
        int[] digits = new int[numberInString.length()];
        for (int i = 0; i < numberInString.length(); i++) {
            digits[i] = numberInString.charAt(i) - '0';
        }
        return digits;
    }

    private String addSpacesToLine(String firstSymbol) {
        String spaces = "";
        String divisibleInString = String.valueOf(divisible);
        int lengthDifference = divisibleInString.length() - firstSymbol.length();
        spaces = addSpaces(lengthDifference);
        return spaces;
    }

    private String headOfDivision() {
        String firstString = "_" + divisible + "|" + divisor;
        String secondString = " " + divisor + addSpacesToLine(String.valueOf(divisor)) + "|-----";
        int firstDigit = divisibleDigits[0];

        if (divisibleDigits[0] < divisor) {
            int secondStringDivisible = addNextDigit(firstDigit, 0);
            int secondStringNumber = secondStringDivisible - secondStringDivisible % divisor;
            secondString = " " + secondStringNumber + addSpacesToLine(String.valueOf(secondStringNumber)) + "|-----";
        }

        if (firstDigit / divisor >= 2) {
            int secondStringDivisible = divisibleDigits[0];
            int secondStringNumber = secondStringDivisible - secondStringDivisible % divisor;
            secondString = " " + secondStringNumber + addSpacesToLine(String.valueOf(secondStringNumber)) + "|-----";
        }

        String thirdString = " -" + addSpacesToLine("-") + "|" + divisible / divisor;
        return firstString + "\n" + secondString + "\n" + thirdString + "\n";
    }

    private String tailOfDivision() {

        int firstDigit = divisibleDigits[0];
        int nextDivisible = firstDigit;


        String resultTailString = "";

        for (int digitCount = 0; digitCount < divisibleDigits.length - 1; digitCount++) {

            if (firstDigit > divisor) {

                if (digitCount == 0) {
                    nextDivisible = nextDivisible % divisor;
                    nextDivisible = addNextDigit(nextDivisible, digitCount);
                    resultTailString += getStringBlock(nextDivisible, divisor, digitCount);
                    nextDivisible = nextDivisible % divisor;
                    continue;
                }
                nextDivisible = addNextDigit(nextDivisible, digitCount);
                resultTailString += getStringBlock(nextDivisible, divisor, digitCount);
                nextDivisible = nextDivisible % divisor;

                if (digitCount == divisibleDigits.length - 2) {
                    resultTailString += getLastBlockString(digitCount, nextDivisible);
                }
            }

            if (firstDigit < divisor) {

                if (digitCount == 0) {
                    nextDivisible = addNextDigit(nextDivisible, digitCount);
                    nextDivisible = nextDivisible % divisor;
                    digitCount++;
                    nextDivisible = addNextDigit(nextDivisible, digitCount);
                    resultTailString += getStringBlock(nextDivisible, divisor, digitCount);
                    nextDivisible = nextDivisible % divisor;
                    continue;
                }

                nextDivisible = addNextDigit(nextDivisible, digitCount);
                resultTailString += getStringBlock(nextDivisible, divisor, digitCount);
                nextDivisible = nextDivisible % divisor;

                if (digitCount == divisibleDigits.length - 2) {
                    resultTailString += getLastBlockString(digitCount, nextDivisible);
                }

            }

            if (firstDigit == divisor) {

                if (digitCount == 0) {
                    nextDivisible = divisibleDigits[1];
                    resultTailString += getStringBlock(nextDivisible, divisor, digitCount + 1);
                    nextDivisible = nextDivisible % divisor;
                    continue;
                }

                nextDivisible = addNextDigit(nextDivisible, digitCount);
                resultTailString += getStringBlock(nextDivisible, divisor, digitCount + 1);
                nextDivisible = nextDivisible % divisor;

                if (digitCount == divisibleDigits.length - 2) {
                    resultTailString += getLastBlockString(digitCount + 1, nextDivisible);
                }
            }

        }
        return resultTailString;
    }

    private int addNextDigit(int digit, int digitCount) {
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

    public String divisionString() {
        return headOfDivision() + tailOfDivision();
    }

}
