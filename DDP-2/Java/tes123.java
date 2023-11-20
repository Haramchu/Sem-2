package assignments.assignment1;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.Scanner;
public class tes123 {
    // buka scanner
    private static final Scanner input = new Scanner(System.in);{
    }
    // class utama
    public static void main(String[] args) {
        try{
            System.out.print("statement1");
            System.out.print("statement2");
            throw new NumberFormatException("A");
            //System.out.print("statement3");
            }
            catch (IOError e){
            }
            catch (NumberFormatException ex1){
                throw ex1;
            }
            finally {
                System.out.print("statement4");
            }
            System.out.print("statement5");     
    }
}
