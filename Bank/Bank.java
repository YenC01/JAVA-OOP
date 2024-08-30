package Bank;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;


public class Bank {

    private static final String USER_DATA_FILE = "userData.txt"; 
    private static Map<String, User> users = new HashMap<>();
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.loadUserData();
        bank.menu();
    }

    public void menu(){
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
                System.out.println("|    BANKING SYSTEM    |\n");
                System.out.println("[1] Sign Up");
                System.out.println("[2] Log In");
                System.out.println("[3] Exit");
                System.out.print("\nEnter your choice: ");

            try {
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                case 1:
                    singUp(input);
                    break;
                case 2:
                    logIn(input);
                    break;
                case 3:
                    System.out.println("\nExiting system. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid input. Please enter a valid option.\n");
                    break;
                    }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter an integer.\n");
                input.next();
            }
        }
        saveUserData();
    }

    public void singUp(Scanner input){
        try {
            String username, password;
            while (true) {
                System.out.print("\nEnter a username: ");
                username = input.nextLine();
                if (users.containsKey(username)){
                    System.out.println("\nUsername already exists. Please choose another one.");
                } else{
                    break;
                }
            }

            while (true) {
                System.out.print("Enter password: ");
                password = input.nextLine();
                if (password.length() < 3){
                    System.out.println("\nPassword must be at least 3 characters long.");
                } else{
                    break;
                }
            }

            users.put(username, new User(username, password, 0.0));
            saveUserData();
            System.out.println("\nAccocunt created succesfully!\n");
        } catch (Exception e) {
            System.out.println("An error occured during sign up. Please try again.");
        }
    }

    public void logIn(Scanner input){
        try {
            System.out.print("\nEnter your username: ");
            String username = input.nextLine();
            System.out.print("Enter your password: ");
            String password = input.nextLine();

            if (validateCredentials(username, password)){
                System.out.println("\nLogin succesful!\n");
                userMenu(input, username);
            }else{
                System.out.println("\nInvalid username or password. Please try again.\n");
            }
        } catch (Exception e) {
            System.out.println("An error occured during login. Please try again.");
        }

    }

    public boolean validateCredentials(String username, String password){
        loadUserData();
        return users.containsKey(username) && users.get(username).getPassword().equals(password);

    }

    public void loadUserData(){
        try {
            File file = new File(USER_DATA_FILE);
            if (file.exists()){
                users.clear();
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    String[] data = fileReader.nextLine().split(", ");
                    String username = data[0];
                    String password = data[1];
                    double balance = Double.parseDouble(data[2]);
                    users.put(username, new User(username, password, balance));
                }
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading user data.");
        } catch (NoSuchElementException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Corrupt user data file. Please check the data file.");
        }

    }

    public void saveUserData(){
        try {
            FileWriter writer = new FileWriter(USER_DATA_FILE);
            for (User user : users.values()){
                writer.write(user.getUsername() + ", " + user.getPassword() + ", " + user.getBalance() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving user data.");
        }

    }

    public void userMenu(Scanner input, String username){
        boolean logout = false;

        while (!logout) {
            System.out.println("|      USER MENU      |\n");
            System.out.println("[1] View balance");
            System.out.println("[2] Deposit");
            System.out.println("[3] Withdraw");
            System.out.println("[4] Log Out");
            System.out.print("\nEnter your choice: ");

            try {
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        viewBalance(username);
                        break;
                    case 2:
                        deposit(input, username);
                        break;
                    case 3:
                        withdraw(input, username);
                        break;
                    case 4:
                        System.out.println("\nLogging out...\n");
                        logout = true;
                        break;
                    default:
                        System.out.println("\nInvalid input. Please enter a number from the menu.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter an integer.\n");
                input.nextLine();
            }
        }

    }

    public void viewBalance(String username){
        System.out.println("\nCurrent Balance: $" + users.get(username).getBalance() + "\n");
    }

    public void deposit(Scanner input, String username){
        try {
            System.out.print("\nEnter amount to deposit: ");
            double amount = input.nextDouble();
            
            input.nextLine(); // not in the orgnal codde

            if (amount > 0){
                User user = users.get(username);
                user.setBalance(user.getBalance() + amount);
                System.out.println("\nSuccessfully deposited $" + amount + "\n");
                saveUserData();
            }
            else{
                System.out.println("\nInvalid amount. Please enter a positive value.\n");
            }

        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter a valid number.");
            input.nextLine();
        }
    }

    public void withdraw(Scanner input, String username){
        try {
            System.out.print("\nEnter amount to withdraw: ");
            double amount = input.nextDouble();
            input.nextLine();

            User user = users.get(username); //diff

            if (user.getBalance() >= amount && amount > 0){
                user.setBalance(user.getBalance() - amount);
                System.out.println("\nSuccessfully withdrew $" + amount + "\n");
                saveUserData();
            } else{
                System.out.println("\nInvalid input. Please check your balance and try again.\n");
                }
        } catch (InputMismatchException e) {
           System.out.println("\nInvalid input. Please enter a valid number.\n");
           input.nextLine();
        }
    }
}