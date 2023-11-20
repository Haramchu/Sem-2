import java.util.Scanner;
public class Main123 {
    public static void main(String[] args){
        int radius;
        int area;
        int alas;
        int tinggi;
        int panjang;
        int lebar;
        int luas_segitiga;
        int luas_persegi_panjang;
        Scanner input = new Scanner (System.in);
        System.out.print("Nilai Radius =");
        radius = input.nextInt();
        if (radius < 0) {
            System.out.println("Radius tidak bisa kurang dari 0");
            }
        else {
            area = radius*radius*22/7;
            System.out.println("Nilai area =" + area);
        }
        System.out.print("Nilai Alas =");
        alas = input.nextInt();
        if (alas < 0) {
            System.out.println("Alas tidak bisa kurang dari 0");
            }
        else {
            System.out.print("Nilai Tinggi =");
            tinggi = input.nextInt();
            if (tinggi < 0) {
                System.out.println("Tinggi tidak bisa kurang dari 0");
                }
            else {
                luas_segitiga = alas * tinggi / 2;
                System.out.println("Nilai luas segitiga =" + luas_segitiga);
            }
        }
        System.out.print("Nilai Panjang =");
        panjang = input.nextInt();
        if (panjang < 0) {
            System.out.println("Panjang tidak bisa kurang dari 0");
            }
        else {
            System.out.print("Nilai Lebar =");
            lebar = input.nextInt();
            if (lebar < 0) {
                System.out.println("Lebar tidak bisa kurang dari 0");
                }
            else {
                luas_persegi_panjang = panjang * lebar;
                System.out.println("Nilai luas persegi panjang =" + luas_persegi_panjang);
            }
        }
    }
    
}