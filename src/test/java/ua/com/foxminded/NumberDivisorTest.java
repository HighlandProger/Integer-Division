package ua.com.foxminded;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberDivisorTest {

    @Test
    void divisionString_firstLineStartsFromDivisibleAndDivisorNumbers() {
        NumberDivisor divisor = new NumberDivisor(24532, 5);
        String fullString = divisor.divisionString();

        String expectedString = "_24532|5";
        String actualString = fullString.substring(0, fullString.indexOf("\n"));
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_secondLineStartsFromNumberMultipleOfDivisor_whenFirstDigitOfDivisibleIsLessThanDivisor() {
        NumberDivisor divisor = new NumberDivisor(24532, 5);
        String fullString = divisor.divisionString();

        String expectedString = " 20   |";
        String actualString = fullString.substring(fullString.indexOf("\n") + 1, fullString.indexOf("-"));
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_secondLineStartsFromNumberMultipleOfDivisor_whenFirstDigitOfDivisibleEqualsDivisor() {
        NumberDivisor divisor = new NumberDivisor(54532, 5);
        String fullString = divisor.divisionString();

        String expectedString = " 5    |";
        String actualString = fullString.substring(fullString.indexOf("\n") + 1, fullString.indexOf("-"));
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_secondLineStartsFromNumberMultipleOfDivisor_whenFirstDigitOfDivisibleMoreThanDivisor() {
        NumberDivisor divisor = new NumberDivisor(74532, 5);
        String fullString = divisor.divisionString();

        String expectedString = " 5    |";
        String actualString = fullString.substring(fullString.indexOf("\n") + 1, fullString.indexOf("-"));
        assertEquals(expectedString, actualString);
    }

    @Test
    void divisionString_secondLineStartsFromNumberMultipleOfDivisor_whenFirstDigitOfDivisibleDoubleMoreThanDivisor() {
        NumberDivisor divisor = new NumberDivisor(94532, 4);
        String fullString = divisor.divisionString();

        String expectedString = " 8    |";
        String actualString = fullString.substring(fullString.indexOf("\n") + 1, fullString.indexOf("-"));
        assertEquals(expectedString, actualString);
    }
}
