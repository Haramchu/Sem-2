import java.util.Scanner;
public class quiz2 {

    public static void main(String[] args){
        Scanner input = new Scanner (System.in);
        System.out.print("Masukkan jumlah pegawai =");
        Integer Jumlah_pegawai = input.nextInt();
        for (int i = 0; i < Jumlah_pegawai; i++) {
            System.out.println("Masukkan nama =");
            String Nama = input.next();
            System.out.println("Masukkan gaji pegawai =");
            Integer Gaji = input.nextInt();
            Integer THR = thr(Gaji);
            System.out.print(Nama + " memiliki gaji sebesar " + Gaji + " THR sebesar " + THR);
            System.out.println();
          }
    }
    public static int thr(int x) {
        int result = 0;
        if (x > 25000000){
            result=  x *10 / 100;
        }
        else if (x >= 15000000 && x <= 25000000){
            result = x * 15 / 100;
        }
        else if (x >= 5000000 && x <= 15000000){
            result = x * 25 / 100;
        }
        else if (x < 5000000){
            result = x * 35 / 100;
        }
        return result;
    }
    
}