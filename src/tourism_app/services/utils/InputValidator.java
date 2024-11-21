package tourism_app.services.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    private final Scanner sc;

    public InputValidator(Scanner sc) {
        this.sc = sc;
    }

    // Validate an integer in a given range
    public int getValidIntInRange(int min, int max) {
        int input;

        while (true) {
            System.out.print("Enter a number between " + min + " and " + max + ": ");
            try {
                input = sc.nextInt();
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println("Error: Input out of range. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter an integer.");
                sc.next(); // Clear the invalid input
            }
        }
        return input;
    }

    // Validate a positive double
    public double getValidPositiveDouble() {
        double input;

        while (true) {
            System.out.print("Enter a positive number: ");
            try {
                input = sc.nextDouble();
                if (input > 0) {
                    break;
                } else {
                    System.out.println("Number must be positive. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                sc.next(); // Clear the invalid input
            }
        }
        return input;
    }

    // Validate a non-empty string
    public String getValidString() {
        String input;

        while (true) {
            System.out.print("Enter a valid string: ");
            input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                break;
            } else {
                System.out.println("Input cannot be empty. Try again.");
            }
        }
        return input;
    }

    // Validate yes or no input
    public boolean getYesOrNo() {
        String input;

        while (true) {
            System.out.print("Enter 'y' for Yes or 'n' for No: ");
            input = sc.next().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    // Validate an integer (any value)
    public int getValidInt() {
        int input;

        while (true) {
            System.out.print("Enter an integer: ");
            try {
                input = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }
        }
        return input;
    }

    public Date getValidDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        while (true) {
            System.out.print("Enter a date in format (" + format + "): ");
            String input = sc.nextLine().trim();

            try {
                return sdf.parse(input);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
    }

    public Scanner getScanner() {
        return sc;
    }
}
