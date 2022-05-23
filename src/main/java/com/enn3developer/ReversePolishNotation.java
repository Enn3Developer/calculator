package com.enn3developer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ReversePolishNotation {
    private static final Map<String, Function<double[], Double>> operations = new HashMap<>();
    private static boolean isNumeric(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void setupOperations() {
        operations.put("+", (double[] numbers) -> numbers[0] + numbers[1]);
        operations.put("-", (double[] numbers) -> numbers[0] - numbers[1]);
        operations.put("*", (double[] numbers) -> numbers[0] * numbers[1]);
        operations.put("/", (double[] numbers) -> numbers[0] / numbers[1]);
    }

    /*
        This function does various checks against the expression the user provided (never trust the user)
            1. Check if it's combined by at least 3 parts (2 numbers and 1 operation)
            2. Check if the operation is valid (check against the dict we wrote at the beginning)
            3. Check if the amount of operations is exactly short by one compared to the amount of numbers
            4. Check if the first operation is valid (`2 + 1` isn't valid, `2 1 +` is valid)
    */
    public static boolean isValid(String expression) {
        String[] parts = expression.split(" ");
        // First check
        if (parts.length < 3) return false;

        int numbers = 0;
        int ops = 0;

        for (String part : parts) {
            if (isNumeric(part)) {
                numbers += 1;
            } else {
                // Fourth check
                if (numbers < 2) return false;
                // Second check
                if (!operations.containsKey(part)) return false;

                ops += 1;
            }
        }

        // Third check
        return numbers == (ops + 1);
    }

    /*
    You have to check the validity beforehand
    The iteration is simple
        Check if the part is a number
            Yes: add it to the stack of numbers
            No: then it's an operation
            We pop 2 numbers from the numbers' stack (the first one is `b`, the second is `a`, the order in the operation is important, remember)
            We calculate that operation and add it to the stack (it goes at the last index)
    At the end of everything there'll be only one number remaining in the stack, that one is the result
     */
    public static double evaluate(String expression) {
        List<Double> numbers = new ArrayList<>();
        String[] parts = expression.split(" ");

        for (String part : parts) {
            if (isNumeric(part)) {
                numbers.add(Double.parseDouble(part));
            } else {
                double b = numbers.remove(numbers.size() - 1);
                double a = numbers.remove(numbers.size() - 1);

                double[] nums = {a, b};

                numbers.add(operations.get(part).apply(nums));
            }
        }

        return numbers.get(0);
    }
}
