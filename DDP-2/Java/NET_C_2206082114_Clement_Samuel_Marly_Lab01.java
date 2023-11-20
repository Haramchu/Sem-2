//import package dan scanner untuk input
import java.util.Scanner;

public class NET_C_2206082114_Clement_Samuel_Marly_Lab01 {
  // program utama
  public static void main(String[] args){
    // buka scanner dan print awalan
    Scanner input = new Scanner (System.in);
    System.out.println("Selamat datang di Toko Fotokopi Dek Depe!");
    System.out.println("--------------------------------------------------------");
    System.out.print("Masukkan jumlah mahasiswa yang ingin melakukan fotokopi: ");
    // minta input jumlah mahasiswa kemudian masuk ke dalam loop sebanyak input
    Integer jumlah_mahasiswa = input.nextInt();
    Double Total_harga = 0.0;
    Boolean loop = true;
    for (int i = 1; i <= jumlah_mahasiswa; i++) {
      System.out.println("--------------------DATA MAHASISWA " + i + "--------------------");
      // set input dan output
      System.out.print("Nama: ");
      String Nama = input.next();
      System.out.print("IPK: ");
      Double IPK = input.nextDouble();
      System.out.print("Jumlah lembar: ");
      Integer jumlah_lembar = input.nextInt();
      // masukan input ke dalam method untuk mendapatkan diskon
      Double persentase_diskon = hitung_harga(IPK);
      // operasi hitung
      Double diskon = 555.00 * jumlah_lembar * persentase_diskon;
      Double harga = 555.00 * jumlah_lembar - diskon;
      // print hasil tiap siswa
      System.out.print(Nama + " membayar seharga " + String.format("%.2f", harga) + " dengan diskon sebesar " + Math.round (persentase_diskon*100) +"%");
      System.out.println();
      Total_harga = Total_harga + harga;
      }
    // print hasil akhir
    System.out.println("---------------------RINGKASAN DATA---------------------");
    System.out.print("Hasil pendapatan yang diperoleh Toko Fotokopi dari " + jumlah_mahasiswa +" mahasiswa adalah " + Total_harga);
    // tutup scanner
    input.close();
  }
  // method untuk menyesuaikan IPK dengan diskon yang diberikan
  public static Double hitung_harga(Double IPK) {
      Double persentase_diskon = 0.0;
      if (IPK <= 2.5){
        persentase_diskon = 0.10;
      }
      else if (IPK > 2.5 && IPK <= 3.0){
        persentase_diskon = 0.25;
      }
      else if (IPK > 3.0 && IPK <= 3.5){
        persentase_diskon = 0.35;
      }
      else if (IPK > 3.5 && IPK <= 4){
        persentase_diskon = 0.50;
      }
      else{
        System.out.println("IPK tidak sesuai");
      }
      return persentase_diskon;
  }
    
}