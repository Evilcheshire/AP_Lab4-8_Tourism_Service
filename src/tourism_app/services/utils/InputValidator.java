package tourism_app.services.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InputValidator {
    private final Scanner sc;

    public InputValidator(Scanner sc) {
        this.sc = sc;
    }

    public int getValidIntInRange(int min, int max) {
        while (true) {
            System.out.print("Enter a number between " + min + " and " + max + ": ");
            String input = sc.nextLine();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Error: Input out of range. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter an integer.");
            }
        }
    }

    public double getValidPositiveDouble() {
        while (true) {
            System.out.print("Enter a positive number: ");
            String input = sc.nextLine();
            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Error: Number must be positive. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a numeric value.");
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
                System.out.println("Error: Input cannot be empty. Try again.");
            }
        }
    }

    public boolean getYesOrNo() {
        while (true) {
            System.out.print("Enter 'y' for Yes or 'n' for No: ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Error: Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    public int getValidInt() {
        while (true) {
            System.out.print("Enter an integer: ");
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter an integer.");
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
                System.out.println("Error: Invalid date format. Please try again.");
            }
        }
    }

    public String getValidOption(List<String> validOptions, String prompt) {
        System.out.println(prompt);
        while (true) {
            String input = sc.nextLine().trim();
            if (validOptions.contains(input.toUpperCase())) {
                return input.toUpperCase();
            }
            System.out.println("Error: Invalid input. Allowed options are: " + validOptions);
        }
    }
}
