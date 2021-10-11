package ua.com.foxminded;


public class NumberDivisor {

    private static final String NEXT_LINE = "\n";

    public String getDivisionString(int divisible, int divisor) {

        if (divisor == 0) {
            throw new IllegalArgumentException("You can't divide by zero");
        }

        int absDivisible = Math.abs(divisible);
        int absDivisor = Math.abs(divisor);

        IntermediateDivisionResult intermediateResult = buildHeadOfDivision(absDivisible, absDivisor);
        return intermediateResult.getDivisionString().append(getTailOfDivision(intermediateResult, absDivisible, absDivisor)).toString();
    }

    private IntermediateDivisionResult buildHeadOfDivision(int absDivisible, int absDivisor) {

        StringBuilder firstString = buildFirstString(absDivisible, absDivisor);
        IntermediateDivisionResult secondDivisionResult = buildSecondString(absDivisible, absDivisor);
        StringBuilder thirdString = buildThirdString(absDivisible, absDivisor);
        return new IntermediateDivisionResult(
            secondDivisionResult.getRemainder(),
            secondDivisionResult.getLastDigit(),
            firstString
                .append(NEXT_LINE)
                .append(secondDivisionResult.getDivisionString())
                .append(NEXT_LINE)
                .append(thirdString)
                .append(NEXT_LINE)
        );
    }

    private StringBuilder buildFirstString(int divisible, int divisor) {

        StringBuilder firstString = new StringBuilder("_");
        firstString.append(divisible).append("|").append(divisor);
        return firstString;
    }

    private IntermediateDivisionResult buildSecondString(int absDivisible, int absDivisor) {

        int divisibleDigitsCount = getDigitsCount(absDivisible);
        int nextDivisible = 0;
        int digitCount = 0;

        while (nextDivisible < absDivisor) {

            if (digitCount == divisibleDigitsCount) {
                break;
            }
            nextDivisible = addNextDigit(nextDivisible, digitCount, absDivisible);
            digitCount++;
        }

        int remainder = nextDivisible % absDivisor;
        int secondNumber = nextDivisible - remainder;
        StringBuilder secondString = new StringBuilder(" ");

        secondString.append(secondNumber);
        secondString.append(addSpaces(divisibleDigitsCount - getDigitsCount(secondNumber)));
        secondString.append("|-----");

        return new IntermediateDivisionResult(remainder, digitCount, secondString);
    }

    private StringBuilder buildThirdString(int absDivisible, int absDivisor) {

        StringBuilder thirdString = new StringBuilder(" -");
        thirdString.append(addSpaces(getDigitsCount(absDivisible) - 1))
            .append("|").append(absDivisible / absDivisor);
        return thirdString;
    }

    private StringBuilder getTailOfDivision(IntermediateDivisionResult divisionResult, int absDivisible, int absDivisor) {

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
            resultTailString.append(getNextDivisionBlock(nextDivisible, absDivisor, spaceCount));
            nextDivisible = nextDivisible % absDivisor;
        }

        resultTailString.append(getLastDivisionBlock(absDivisible, nextDivisible));

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

    protected int addNextDigit(int digit, int digitCount, int divisible) {

        int[] digits = getDigitsArrayFromNumber(divisible);
        if (digitCount > digits.length - 1) {
            throw new ArrayIndexOutOfBoundsException("No more digits in divisible");
        }
        return (digit * 10) + digits[digitCount];
    }

    private String getNextDivisionBlock(int nextDivisible, int divisor, int spaceCount) {

        String firstBlockLine = addSpaces(spaceCount) + "_" + nextDivisible;
        String secondBlockLine = addSpaces(spaceCount + 1) + (nextDivisible - nextDivisible % divisor);
        String thirdBlockLine = addSpaces(spaceCount + 1) + "--";
        return firstBlockLine + NEXT_LINE + secondBlockLine + NEXT_LINE + thirdBlockLine + NEXT_LINE;
    }

    private String getLastDivisionBlock(int absDivisible, int nextDivisible) {

        int absDivisibleLength = getDigitsCount(absDivisible);
        int nextDivisibleLength = getDigitsCount(nextDivisible);

        if (nextDivisible == 0) {
            nextDivisibleLength = 1;
        }

        int resultLength = absDivisibleLength - nextDivisibleLength + 1;

        return addSpaces(resultLength) + nextDivisible;
    }

}
