package tourism_app.services.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InputValidator {
    private final Scanner sc;

    public InputValidator(Scanner sc) {
        this.sc = sc;
    }

    public int getValidIntInRange(int min, int max) {
        while (true) {
            System.out.print("Enter a number between " + min + " and " + max + ": ");
            try {
                int input = sc.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Error: Input out of range. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter an integer.");
            }
        }
    }

    public double getValidPositiveDouble() {
        while (true) {
            System.out.print("Enter a positive number: ");
            try {
                double input = sc.nextDouble();
                if (input > 0) {
                    return input;
                } else {
                    System.out.println("Number must be positive. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    public String getValidString(String prompt) {
        System.out.println(prompt);
        while (true) {
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty. Try again.");
            }
        }
    }

    public boolean getYesOrNo() {
        while (true) {
            System.out.print("Enter 'y' for Yes or 'n' for No: ");
            String input = sc.next().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    public int getValidInt() {
        while (true) {
            System.out.print("Enter an integer: ");
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }
        }
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
