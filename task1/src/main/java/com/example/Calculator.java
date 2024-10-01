package com.example;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class EnhancedCalculator
{
    private static final String RESULT_PREFIX = "Result: ";
    private static final Logger LOGGER = Logger.getLogger(EnhancedCalculator.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            LOGGER.info("\n--- Enhanced Calculator Menu ---");
            LOGGER.info("1. Basic Arithmetic Operations");
            LOGGER.info("2. Scientific Calculations (Square Root, Exponentiation, Logarithm, Trigonometric Functions)");
            LOGGER.info("3. Unit Conversions (Temperature, Currency)");
            LOGGER.info("4. Additional Features (Modulus, Area Calculations, BMI Calculator)");
            LOGGER.info("5. Exit");
            LOGGER.info("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    arithmeticOperations(scanner);
                    break;
                case 2:
                    scientificCalculations(scanner);
                    break;
                case 3:
                    unitConversions(scanner);
                    break;
                case 4:
                    additionalFeatures(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    LOGGER.warning("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void arithmeticOperations(Scanner scanner) {
        LOGGER.info("Enter first number: ");
        double num1 = scanner.nextDouble();
        LOGGER.info("Enter second number: ");
        double num2 = scanner.nextDouble();
        LOGGER.info("Choose operation (+, -, *, /, %): ");
        char operation = scanner.next().charAt(0);
        double result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                logResult(result);
                break;
            case '-':
                result = num1 - num2;
                logResult(result);
                break;
            case '*':
                result = num1 * num2;
                logResult(result);
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                    logResult(result);
                } else {
                    LOGGER.severe("Error: Division by zero.");
                }
                break;
            case '%':
                if (num2 != 0) {
                    result = num1 % num2;
                    logResult(result);
                } else {
                    LOGGER.severe("Error: Division by zero.");
                }
                break;
            default:
                LOGGER.warning("Invalid operation.");
        }
    }

    private static void scientificCalculations(Scanner scanner) {
        LOGGER.info(
                "Choose operation: 1) Square Root 2) Exponentiation 3) Natural Log 4) Log Base 10 5) Sine 6) Cosine 7) Tangent");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                LOGGER.info("Enter the number: ");
                double num = scanner.nextDouble();
                double sqrtResult = Math.sqrt(num);
                logResult(sqrtResult);
                break;
            case 2:
                LOGGER.info("Enter base: ");
                double base = scanner.nextDouble();
                LOGGER.info("Enter exponent: ");
                double exponent = scanner.nextDouble();
                double expResult = Math.pow(base, exponent);
                logResult(expResult);
                break;
            case 3:
                LOGGER.info("Enter number: ");
                double logNum = scanner.nextDouble();
                double logResult = Math.log(logNum);
                logResult(logResult);
                break;
            case 4:
                LOGGER.info("Enter number: ");
                double log10Num = scanner.nextDouble();
                double log10Result = Math.log10(log10Num);
                logResult(log10Result);
                break;
            case 5:
                LOGGER.info("Enter angle in Degrees: ");
                double angle = scanner.nextDouble();
                double sineResult = Math.sin(Math.toRadians(angle));
                logResult(sineResult);
                break;
            case 6:
                LOGGER.info("Enter angle in degrees: ");
                double angleCos = scanner.nextDouble();
                double cosineResult = Math.cos(Math.toRadians(angleCos));
                logResult(cosineResult);
                break;
            case 7:
                LOGGER.info("Enter angle in degrees: ");
                double angleTan = scanner.nextDouble();
                double tangentResult = Math.tan(Math.toRadians(angleTan));
                logResult(tangentResult);
                break;
            default:
                LOGGER.warning("Invalid Choice.");
        }
    }

    private static void unitConversions(Scanner scanner) {
        LOGGER.info("Choose conversion: 1) Celsius to Fahrenheit 2) USD to INR");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                LOGGER.info("Enter temperature in Celsius: ");
                double celsius = scanner.nextDouble();
                double fahrenheit = (celsius * 9 / 5) + 32;
                logResult(fahrenheit);
                break;
            case 2:
                LOGGER.info("Enter amount in USD: ");
                double usd = scanner.nextDouble();
                double inr = usd * 82.34;
                logResult(inr);
                break;
            default:
                LOGGER.warning("Invalid Choice.");
        }
    }

    private static void additionalFeatures(Scanner scanner) {
        LOGGER.info("Choose feature: 1) Modulus 2) Area Calculations 3) BMI Calculator");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                arithmeticOperations(scanner);
                break;
            case 2:
                areaCalculations(scanner);
                break;
            case 3:
                bmiCalculator(scanner);
                break;
            default:
                LOGGER.warning("Invalid choice.");
        }
    }

    private static void areaCalculations(Scanner scanner) {
        LOGGER.info("Choose shape: 1) Circle 2) Rectangle");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                LOGGER.info("Enter radius of the circle: ");
                double radius = scanner.nextDouble();
                double areaCircle = Math.PI * radius * radius;
                logResult(areaCircle);
                break;
            case 2:
                LOGGER.info("Enter length of the rectangle: ");
                double length = scanner.nextDouble();
                LOGGER.info("Enter width of the rectangle: ");
                double width = scanner.nextDouble();
                double areaRectangle = length * width;
                logResult(areaRectangle);
                break;
            default:
                LOGGER.warning("Invalid choice.");
        }
    }

    private static void bmiCalculator(Scanner scanner) {
        LOGGER.info("Enter weight in kilograms: ");
        double weight = scanner.nextDouble();
        LOGGER.info("Enter height in meters: ");
        double height = scanner.nextDouble();
        double bmi = weight / (height * height);
        logResult(bmi);
    }

    private static void logResult(double result) {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.log(Level.INFO, String.format("%s%.2f", RESULT_PREFIX, result));
        }
    }
}
