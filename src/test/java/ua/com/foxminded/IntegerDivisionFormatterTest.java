package ua.com.foxminded;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerDivisionFormatterTest {

    IntegerDivisionFormatter formatter = new IntegerDivisionFormatter();
    private String expectedExceptionString;
    private String actualExceptionString;
    private String expectedString;
    private String actualString;

    @Test
    void getDivisionString(){
        actualString = formatter.getDivisionString(24532, 23);
        expectedString = "_24532|23\n" +
            " 23   |-----\n" +
            " -    |1066\n" +
            " _153\n" +
            "  138\n" +
            "  --\n" +
            "  _152\n" +
            "   138\n" +
            "   --\n" +
            "    14";

        assertEquals(actualString, expectedString);
    }

    @Test
    void getDivisionString_whenDivideByZero(){
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> formatter.getDivisionString(24532, 0));

        expectedExceptionString = "You can't divide by zero";
        actualExceptionString = exception.getMessage();

        assertEquals(actualExceptionString, expectedExceptionString);
    }

    @Test
    void getDivisionString_whenDivideZero(){
        actualString = formatter.getDivisionString(0, 23);
        expectedString = "_0|23\n" +
            " 0|-----\n" +
            " -|0\n" +
            " 0";

        assertEquals(actualString, expectedString);
    }

    @Test
    void getDivisionString_whenDividendLessThanDivisor(){
        actualString = formatter.getDivisionString(26, 30);
        expectedString = "_26|30\n" +
            " 0 |-----\n" +
            " - |0\n" +
            " 26";

        assertEquals(actualString, expectedString);
    }

    @Test
    void getDivisionString_whenDividendEqualsDivisor(){
        actualString = formatter.getDivisionString(14, 14);
        expectedString = "_14|14\n" +
            " 14|-----\n" +
            " - |1\n" +
            "  0";

        assertEquals(actualString, expectedString);
    }

    @Test
    void getDivisionString_whenDivisionWithoutRemainder(){
        actualString = formatter.getDivisionString(52331, 43);
        expectedString = "_52331|43\n" +
            " 43   |-----\n" +
            " -    |1217\n" +
            " _93\n" +
            "  86\n" +
            "  --\n" +
            "  _73\n" +
            "   43\n" +
            "   --\n" +
            "  _301\n" +
            "   301\n" +
            "   --\n" +
            "     0";

        assertEquals(actualString, expectedString);
    }

    @Test
    void getDivisionString_whenDivisionWithRemainder(){
        actualString = formatter.getDivisionString(634521, 52);
        expectedString = "_634521|52\n" +
            " 52    |-----\n" +
            " -     |12202\n" +
            "_114\n" +
            " 104\n" +
            " --\n" +
            " _105\n" +
            "  104\n" +
            "  --\n" +
            "   _121\n" +
            "    104\n" +
            "    --\n" +
            "     17";

        assertEquals(actualString, expectedString);
    }

    @Test
    void getDivisionString_whenDividendAndDivisorNegative(){
        actualString = formatter.getDivisionString(-42341, -63);
        expectedString = "_42341|63\n" +
            " 378  |-----\n" +
            " -    |672\n" +
            " _454\n" +
            "  441\n" +
            "  --\n" +
            "  _131\n" +
            "   126\n" +
            "   --\n" +
            "     5";

        assertEquals(actualString, expectedString);
    }

    @Test
    void getDivisionString_whenDividendNegative(){
        actualString = formatter.getDivisionString(-52341, 12);
        expectedString = "_52341|12\n" +
            " 48   |-----\n" +
            " -    |4361\n" +
            " _43\n" +
            "  36\n" +
            "  --\n" +
            "  _74\n" +
            "   72\n" +
            "   --\n" +
            "   _21\n" +
            "    12\n" +
            "    --\n" +
            "     9";

        assertEquals(actualString, expectedString);
    }

    @Test
    void getDivisionString_whenDivisorNegative(){
        actualString = formatter.getDivisionString(42341, -63);
        expectedString = "_42341|63\n" +
            " 378  |-----\n" +
            " -    |672\n" +
            " _454\n" +
            "  441\n" +
            "  --\n" +
            "  _131\n" +
            "   126\n" +
            "   --\n" +
            "     5";

        assertEquals(actualString, expectedString);
    }

    @Test
    void addNextDigit_shouldAddNextDigitToPartOfNumberByDigitCount() {
        int fullNumber = 489342;
        int partOfNumber = 934;
        int digitCount = 5;

        int expectedNumber = 9342;
        int actualNumber = formatter.addNextDigit(partOfNumber, digitCount, fullNumber);

        assertEquals(expectedNumber, actualNumber);
    }

    @Test
    void getDigitsCount() {

        int number = 432325345;
        int actualDigitsCount = formatter.getDigitsCount(number);
        int expectedDigitsCount = 9;

        assertEquals(expectedDigitsCount, actualDigitsCount);
    }

    @Test
    void getDigitsCount_whenNumberIsZero() {

        int number = 0;
        int actualDigitsCount = formatter.getDigitsCount(number);
        int expectedDigitsCount = 1;

        assertEquals(expectedDigitsCount, actualDigitsCount);
    }

    @Test
    void getDigitsCount_whenNumberNegative() {

        int number = -52341;
        int actualDigitsCount = formatter.getDigitsCount(number);
        int expectedDigitsCount = 5;

        assertEquals(expectedDigitsCount, actualDigitsCount);
    }

}
