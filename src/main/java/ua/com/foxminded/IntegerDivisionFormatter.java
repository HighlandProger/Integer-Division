package ua.com.foxminded;


public class IntegerDivisionFormatter {

    private static final String NEXT_LINE = "\n";
    private static final String SPACE = " ";
    private static final String DASH = "-";

    public String getDivisionString(int dividend, int divisor) {

        if (divisor == 0) {
            throw new IllegalArgumentException("You can't divide by zero");
        }

        int absDividend = Math.abs(dividend);
        int absDivisor = Math.abs(divisor);

        IntermediateDivisionResult intermediateResult = buildHeadOfDivision(absDividend, absDivisor);
        return intermediateResult.getDivisionString().append(getTailOfDivision(intermediateResult, absDividend, absDivisor)).toString();
    }

    private IntermediateDivisionResult buildHeadOfDivision(int absDividend, int absDivisor) {

        StringBuilder firstString = buildFirstString(absDividend, absDivisor);
        IntermediateDivisionResult secondDivisionResult = buildSecondString(absDividend, absDivisor);
        StringBuilder thirdString = buildThirdString(absDividend, absDivisor, secondDivisionResult.getLastDigitNumber());
        return new IntermediateDivisionResult(
            secondDivisionResult.getRemainder(),
            secondDivisionResult.getLastDigitNumber(),
            firstString
                .append(NEXT_LINE)
                .append(secondDivisionResult.getDivisionString())
                .append(NEXT_LINE)
                .append(thirdString)
                .append(NEXT_LINE)
        );
    }

    private StringBuilder buildFirstString(int dividend, int divisor) {

        StringBuilder firstString = new StringBuilder("_");
        firstString.append(dividend).append("|").append(divisor);
        return firstString;
    }

    private IntermediateDivisionResult buildSecondString(int absDividend, int absDivisor) {

        int digitsCount = getDigitsCount(absDividend);
        int nextDividend = 0;
        int digitNumber = 0;

        while (nextDividend < absDivisor) {

            if (digitNumber == digitsCount) {
                break;
            }
            nextDividend = addNextDigit(nextDividend, digitNumber, absDividend);
            digitNumber++;
        }

        int remainder = nextDividend % absDivisor;
        int secondNumber = nextDividend - remainder;

        StringBuilder secondString = new StringBuilder(" ");
        secondString.append(secondNumber);
        secondString.append(addSpaces(digitsCount - getDigitsCount(secondNumber)));
        secondString.append("|");
        secondString.append(getDashesByDigitCount(getDigitsCount(absDividend / absDivisor)));

        return new IntermediateDivisionResult(remainder, digitNumber, secondString);
    }

    private StringBuilder buildThirdString(int absDividend, int absDivisor, int digitNumber) {

        StringBuilder thirdString = new StringBuilder(" ");
        thirdString.append(getDashesByDigitCount(digitNumber));
        thirdString.append(addSpaces(getDigitsCount(absDividend) - digitNumber))
            .append("|").append(absDividend / absDivisor);
        return thirdString;
    }

    private StringBuilder getTailOfDivision(IntermediateDivisionResult divisionResult, int absDividend, int absDivisor) {

        StringBuilder resultTailString = new StringBuilder();

        int nextDividend = divisionResult.getRemainder();
        int digitCount = divisionResult.getLastDigitNumber();
        int spacesCount;
        int divisibleDigitsCount = getDigitsCount(absDividend);

        while (digitCount < divisibleDigitsCount) {

            while (nextDividend < absDivisor) {

                if (digitCount == divisibleDigitsCount) {
                    break;
                }
                nextDividend = addNextDigit(nextDividend, digitCount, absDividend);
                digitCount++;
            }

            spacesCount = digitCount - getDigitsCount(nextDividend);
            resultTailString.append(getNextDivisionBlock(nextDividend, absDivisor, spacesCount));
            nextDividend = nextDividend % absDivisor;
        }

        resultTailString.append(getLastDivisionBlock(absDividend, nextDividend));

        return resultTailString;
    }

    private String addSpaces(int count) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(SPACE);
        }
        return builder.toString();
    }

    private String getDashesByDigitCount(int digitCount) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < digitCount; i++) {
            builder.append(DASH);
        }
        return builder.toString();
    }

    private int[] splitIntoDigits(int number) {

        int digitsCount = getDigitsCount(number);
        int[] digits = new int[digitsCount];

        for (int remainder = number, i = digitsCount - 1; remainder > 0; remainder /= 10, i--) {
            digits[i] = remainder % 10;
        }

        return digits;
    }

    protected int getDigitsCount(int number) {
        if (number == 0) {
            return 1;
        }

        if (number < 0) {
            number = Math.abs(number);
        }

        return (int) (Math.log10(number) + 1);
    }

    protected int addNextDigit(int digit, int digitCount, int dividend) {

        int[] digits = splitIntoDigits(dividend);
        return (digit * 10) + digits[digitCount];
    }

    private String getNextDivisionBlock(int nextDividend, int divisor, int spacesCount) {

        String firstBlockLine = addSpaces(spacesCount) + "_" + nextDividend;
        String secondBlockLine = addSpaces(spacesCount + 1) + (nextDividend - nextDividend % divisor);
        String thirdBlockLine = addSpaces(spacesCount + 1) + getDashesByDigitCount(getDigitsCount(nextDividend));
        return firstBlockLine + NEXT_LINE + secondBlockLine + NEXT_LINE + thirdBlockLine + NEXT_LINE;
    }

    private String getLastDivisionBlock(int absDividend, int nextDividend) {

        int absDividendLength = getDigitsCount(absDividend);
        int nextDividendLength = getDigitsCount(nextDividend);

        if (nextDividend == 0) {
            nextDividendLength = 1;
        }

        int resultLength = absDividendLength - nextDividendLength + 1;

        return addSpaces(resultLength) + nextDividend;
    }

}
