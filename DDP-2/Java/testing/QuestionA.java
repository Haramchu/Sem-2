import java.util.ArrayList;
import java.util.List;

public class QuestionA extends Exception{ public static void main(String[] args) {
    ArrayList<Integer> ints = new ArrayList<>();
  ints.add(1); ints.add(1); ints.add(2);
  System.out.println(salinSebanyak(ints, 2)); // [1, 1, 2, 1, 1, 2]
  
  ArrayList<String> strings = new ArrayList<>();
  strings.add("A"); strings.add("C"); strings.add("D");
  System.out.println(salinSebanyak(strings, 4));
  // [A, C, D, A, C, D, A, C, D, A, C, D]

}
public int mystery(String a, String b){
int hasil=0;
try{
hasil=Integer.parseInt(a)/Integer.parseInt(b);
}catch(ArithmeticException e) {
System.out.print("D");
throw e;

}catch(RuntimeException e) {
System.out.print("E");
}finally{
System.out.print("F");
}
return hasil;
}
public static <E> ArrayList<E> salinSebanyak(ArrayList<E> al, int n) {
    ArrayList<E> alSalin = new ArrayList<>();
    for (int i = 0; i<n; i++) {
      for(int x = 0; x<al.size();x++) {
        alSalin.add(al.get(x));
      }
    }
    return alSalin;
  }
  
}
