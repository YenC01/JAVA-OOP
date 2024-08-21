public class Escseq {
    public static void main(String[] args){
        int count = 1;

        for (int i = 1; i <= 10; i++) { 
            int multipleOf7 = i * 7;
            
            if (count % 2 == 0) {
                System.out.println("\t" + multipleOf7);  
            } else {
                System.out.println(multipleOf7);  
            }
            
            count++;  
        }
    }
}
