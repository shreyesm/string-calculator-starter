package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StringCalculatorShould {

    @Test
    void empty_string_should_return_0() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    void string_with_single_number_should_return_number_as_int() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    public final void moreThan2NumbersThenHandleThem() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(12 + 46, 13, stringCalculator.add("12,46,13"));
    }

    @Test
    public final void newLineBetweenNumbersReturnTheirSums() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(1 + 2, stringCalculator.add("//;\n1;2"));
    }

    @Test
    public final void whenNegativeNumberThenThrowRuntimeException() {
        StringCalculator stringCalculator = new StringCalculator();
        Exception exception = assertThrows(RuntimeException.class, () -> stringCalculator.add("-1,3,4"));
        assertEquals("Negatives not allowed", exception.getMessage());
    }

    @Test
    public final void whenMultipleNegativeNumbersThenThrowRuntimeException() {
        StringCalculator stringCalculator = new StringCalculator();
        RuntimeException exception = null;
        try {
            stringCalculator.add("-1, 2, 3, -4");
        } catch (RuntimeException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals("Negatives not allowed: [-1, -4]", exception.getMessage());
    }

    @Test
    public final void oneOrMoreNumbersAreGreaterThan1000ThenNotIncludedInSum() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(2, stringCalculator.add("2,1001"));
    }

    @Test
    public final void delimitersCanAnyLengthWithCustomFormat() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(33, stringCalculator.extractDelimiter("//[*][%]\n1*2%3"));
    }

    @Test
    public final void multipleDelimitersCanAnyLengthWithCustomFormat() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(33, stringCalculator.checkMultipleDelemiter("//[***][??][####]\n1***2??3,4,5\n8####10"));
    }

}
