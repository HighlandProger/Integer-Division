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

    private void printHead() {
        String firstString = "_" + divisible + "|" + divisor;
        String secondString = " " + divisor + addSpacesToLine(String.valueOf(divisor)) + "|-----";

        if (divisibleDigits[0] < divisor) {
            int secondStringDivisible = addNextDigit(divisibleDigits[0], 0);
            int secondStringNumber = secondStringDivisible - secondStringDivisible % divisor;
            secondString = " " + secondStringNumber + addSpacesToLine(String.valueOf(secondStringNumber)) + "|-----";
        }

        String thirdString = " -" + addSpacesToLine("-") + "|" + divisible / divisor;
        String result = firstString + "\n" + secondString + "\n" + thirdString;
        System.out.println(result);
    }

    private void printTail() {

        int firstDigit = divisibleDigits[0];
        int nextDivisible = firstDigit;

        for (int digitCount = 0; digitCount < divisibleDigits.length - 1; digitCount++) {

            if (firstDigit > divisor) {

                if (nextDivisible % divisor < divisor && digitCount == 0) {
                    nextDivisible = nextDivisible % divisor;
                    nextDivisible = addNextDigit(nextDivisible, digitCount);
                    printBlock(nextDivisible, divisor, digitCount);
                    nextDivisible = nextDivisible % divisor;
                    continue;
                }
                nextDivisible = addNextDigit(nextDivisible, digitCount);
                printBlock(nextDivisible, divisor, digitCount);
                nextDivisible = nextDivisible % divisor;

                if (digitCount == divisibleDigits.length - 2) {
                    printLastBlock(digitCount, nextDivisible);
                }
            }

            if (firstDigit < divisor) {

                if (digitCount == 0) {
                    nextDivisible = addNextDigit(nextDivisible, digitCount);
                    nextDivisible = nextDivisible % divisor;
                    digitCount++;
                }

                nextDivisible = addNextDigit(nextDivisible, digitCount);
                printBlock(nextDivisible, divisor, digitCount);
                nextDivisible = nextDivisible % divisor;

                if (digitCount == divisibleDigits.length - 2) {
                    printLastBlock(digitCount, nextDivisible);
                }

            }

            if (firstDigit == divisor) {

                if (digitCount == 0) {
                    nextDivisible = divisibleDigits[1];
                    printBlock(nextDivisible, divisor, digitCount + 1);
                    nextDivisible = nextDivisible % divisor;
                    continue;
                }

                nextDivisible = addNextDigit(nextDivisible, digitCount);
                printBlock(nextDivisible, divisor, digitCount + 1);
                nextDivisible = nextDivisible % divisor;

                if (digitCount == divisibleDigits.length - 2) {
                    printLastBlock(digitCount + 1, nextDivisible);
                }
            }

        }
    }

    private int addNextDigit(int digit, int digitCount) {
        int[] digits = getDigitsFromNumber(divisible);
        return Integer.parseInt("" + digit + digits[digitCount + 1]);
    }

    private void printBlock(int nextDivisible, int divisor, int digitCount) {
        String firstBlockLine = addSpaces(digitCount) + "_" + nextDivisible;
        String secondBlockLine = addSpaces(digitCount + 1) + (nextDivisible - nextDivisible % divisor);
        String thirdBlockLine = addSpaces(digitCount + 1) + "--";
        String result = firstBlockLine + "\n" + secondBlockLine + "\n" + thirdBlockLine;
        System.out.println(result);
    }

    private void printLastBlock(int digitCount, int nextDivisible) {
        String lastBlock = addSpaces(digitCount + 2) + nextDivisible;
        System.out.print(lastBlock);
    }

    public void printDivide() {
        printHead();
        printTail();
    }

}
