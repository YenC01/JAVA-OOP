import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class NumberGuesser {
    public static void main(String[] args) {
        NumberGuesser numberGuesser = new NumberGuesser();
        numberGuesser.menu();
    }

    public void menu(){
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int limit = 100;
        int tries = 3;
        
        int randomNum = random.nextInt(limit) + 1;

        
        while (tries > 0) {
            System.out.println("\nGuesses left: " + tries);

            int number = getInput("Enter a number from 1 to " + limit + ": ", input, limit);

            if (number < 0){
                System.out.println("Invalid input. Please enter a positive integer.");
                continue;
            } else if(number > limit) {
                System.out.println("Invalid input. Please enter a number from 1 to " + limit + ".");
                continue;
            }else{
                if (number < randomNum){
                    System.out.println("The number is higher.");
                }else if (number > randomNum){
                    System.out.println("The number is lower.");
                }else{
                    System.out.println("You have guessed correctly!");
                    break;
                }
            }
            tries--;
        }

        if (tries == 0){
            System.out.println("\nYou are out of guesses. The number was " + randomNum);
        }
        
    }

    public int getInput(String prompt, Scanner input, int limit){
        boolean valid = false;
        int number = 0;

        while (!valid) {
            try {
                System.out.print(prompt);
                number = input.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter an integer.\n");
                input.next();
            }
        }

        input.nextLine(); 
        return number;
    }

}