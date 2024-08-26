import java.util.Scanner;
import java.util.InputMismatchException;

class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.menu();
    }

    public void menu() {
        Scanner input = new Scanner(System.in);
        boolean valid = false;

        System.out.println("SIMPLE CALCULATOR\n");

        int num1 = getInput("Enter a number: ", input);
        char operation = getOperation("Enter the operation (+, -, *, /): ", input);
        int num2 = getInput("Enter another number: ", input);

        result(num1, num2, operation);

        while (!valid) {
            System.out.print("Do you wish to continue (y/n): ");
            String choice = input.nextLine().trim().toLowerCase();

            if (choice.equals("y")) {
                menu(); 
                valid = true;
            } else if (choice.equals("n")) {
                System.exit(0); 
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }

        input.close();
    }

    public void result(int num1, int num2, char operation) {
        switch (operation) {
            case '+':
                System.out.println("Result: " + add(num1, num2));
                break;
            case '-':
                System.out.println("Result: " + subtract(num1, num2));
                break;
            case '*':
                System.out.println("Result: " + multiply(num1, num2));
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("Error: Division by zero is not allowed.");
                } else {
                    System.out.println("Result: " + divide(num1, num2));
                }
                break;
            default:
                System.out.println("Invalid operation.");
                break;
        }
    }

    public int getInput(String prompt, Scanner input) {
        boolean valid = false;
        int number = 0;

        while (!valid) {
            try {
                System.out.print(prompt);
                number = input.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); 
            }
        }

        input.nextLine(); 
        return number;
    }

    public char getOperation(String prompt, Scanner input) {
        boolean valid = false;
        char operation = '@';

        while (!valid) {
            System.out.print(prompt);
            String inputStr = input.nextLine().trim();

            if (inputStr.length() == 1) {
                operation = inputStr.charAt(0);

                switch (operation) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        valid = true;
                        break;
                    default:
                        System.out.println("Invalid input. Please enter a valid operation (+, -, *, /).");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a single character for the operation.");
            }
        }

        return operation;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }
}
