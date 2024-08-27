import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class ToDo {
    private ArrayList<String> tasks = new ArrayList<>();
    public static void main(String[] args){
        ToDo ToDoList = new ToDo();
        ToDoList.menu();
    }

    public void menu(){
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        int choice = 0;

        while (!valid) {
            try {
                System.out.println("\nTo-Do List");
                System.out.println("[1] View Tasks");
                System.out.println("[2] Add Tasks");
                System.out.println("[3] Remove Tasks");
                System.out.println("[4] Exit");
                System.out.print("Enter your choice: ");

                choice = input.nextInt();
                valid = true; 

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); 
            }
        }

        switch (choice) {
            case 1:
                viewTasks();
                break;
            case 2:
                addTask(input);
                break;
            case 3:
                removeTask(input);
                break;
            case 4:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Please enter a number from the menu.\n");
                break;
        }

        menu();
    }

    public void viewTasks(){
        if (tasks.isEmpty()){
            System.out.println("\nTask List empty.");
        } else{
            System.out.println("\nYour tasks: ");
            for (int i = 0; i < tasks.size(); i++){
                System.out.println( "[" + (i + 1) + "] " + tasks.get(i) );
            }
        }
    }

    public void addTask(Scanner input){
        input.nextLine();
        System.out.print("\nEnter task to add: ");
        String task = input.nextLine();
        tasks.add(task);
        System.out.println("\nTask succesfully added.");
    }

    public void removeTask(Scanner input){
        boolean valid = false;
        input.nextLine();

        if (tasks.isEmpty()){
            System.out.println("\nTask List is empty.");
        }else{
            while (!valid) {
            try {
                viewTasks();
                System.out.print("\nEnter the number of the task to be removed: ");
                int choice = input.nextInt();
                valid = true;
                
                if (choice < 0 || choice > tasks.size()){
                    System.out.println("Invalid task number.");
                }else{
                    tasks.remove(choice - 1);
                    System.out.println("\nTask removed.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); 
            }
        }
        }
    
    }

}