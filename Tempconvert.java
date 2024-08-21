import java.util.Scanner;

public class Tempconvert {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a celcius value: ");
        float celcius = input.nextFloat();
        float farenheit = (celcius * 9/5) + 32;
        System.out.println(celcius + " celcius is " + farenheit + " in farenheit");
        input.close();

    }
}
