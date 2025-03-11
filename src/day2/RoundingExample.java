package day2;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingExample {
    public static void main(String[] args) {

        roundAndValidate("12345.12345467", "12345.12345");
        roundAndValidate("12345.123456", "12345.12346");
    }

    public static void roundAndValidate(String input, String expected) {
        BigDecimal value = new BigDecimal(input);

        BigDecimal roundedValue = value.setScale(5, RoundingMode.HALF_UP);

        System.out.println("Input: " + input);
        System.out.println("Rounded value: " + roundedValue);


        BigDecimal expectedValue = new BigDecimal(expected);
        if (roundedValue.compareTo(expectedValue) == 0) {
            System.out.println("Result: Correct. Rounded value (" + roundedValue + ") matches the expected value (" + expected + ").");
        } else {
            System.out.println("Result: Incorrect. Rounded value (\" + roundedValue + \") does not match the expected value (" + expected + ").");
        }
    }
}

