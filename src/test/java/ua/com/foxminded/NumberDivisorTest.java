package ua.com.foxminded;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberDivisorTest {

    NumberDivisor divisor = new NumberDivisor();

    @Test
    void divisionString_firstLineStartsFromDivisibleAndDivisorNumbers() {
        String fullString = divisor.getDivisionString(24532, 5);

        String expectedString = "_24532|5";
        String actualString = fullString.substring(0, fullString.indexOf("\n"));
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_secondLineStartsFromNumberMultipleOfDivisor_whenFirstDigitOfDivisibleIsLessThanDivisor() {
        String fullString = divisor.getDivisionString(24532, 5);

        String expectedString = " 20   |";
        String actualString = fullString.substring(fullString.indexOf("\n") + 1, fullString.indexOf("-"));
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_secondLineStartsFromNumberMultipleOfDivisor_whenFirstDigitOfDivisibleEqualsDivisor() {
        String fullString = divisor.getDivisionString(54532, 5);

        String expectedString = " 5    |";
        String actualString = fullString.substring(fullString.indexOf("\n") + 1, fullString.indexOf("-"));
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_secondLineStartsFromNumberMultipleOfDivisor_whenFirstDigitOfDivisibleMoreThanDivisor() {
        String fullString = divisor.getDivisionString(74532, 5);

        String expectedString = " 5    |";
        String actualString = fullString.substring(fullString.indexOf("\n") + 1, fullString.indexOf("-"));
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_secondLineHaveCorrectTabulation_whenDivisibleIsNegative() {
        String fullString = divisor.getDivisionString(-94532, 4);

        String expectedString = " 8    |";
        String actualString = fullString.substring(fullString.indexOf("\n") + 1, fullString.indexOf("-", 10));
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_secondLineStartsFromNumberMultipleOfDivisor_whenFirstDigitOfDivisibleDoubleMoreThanDivisor() {
        String fullString = divisor.getDivisionString(94532, 4);

        String expectedString = " 8    |";
        String actualString = fullString.substring(fullString.indexOf("\n") + 1, fullString.indexOf("-"));
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_thirdLineContainsResultOfDivision_whenOneOfNumbersIsNegative() {
        String fullString = divisor.getDivisionString(-94532, 4);

        String expectedString = " -    |23633";
        String actualString = fullString.substring(fullString.indexOf("\n", 11) + 1, fullString.indexOf("\n", 25));
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_theLastLineContainsCorrectSurplus(){
        String fullString = divisor.getDivisionString(94532, 4);

        String expectedString = "0";
        String actualString = fullString.substring(fullString.lastIndexOf(" ") + 1);
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_theLastLineContainsCorrectSurplus2(){
        String fullString = divisor.getDivisionString(74535, 4);

        String expectedString = "3";
        String actualString = fullString.substring(fullString.lastIndexOf(" ") + 1);
        assertEquals(expectedString, actualString);
    }

}
