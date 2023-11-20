import java.util.Scanner;
public class NET_C_2206082114_Clement_Samuel_Marly_Lab02 {
    // program utama
    public static void main(String[] args){
        // buka scanner dan print awalan
        Scanner input = new Scanner (System.in);
        // buka loop
        Boolean loop = true;
        // buka main loop perintah
        while (loop){
            System.out.println("Halo! Apa yang ingin kamu lakukan?");
            System.out.println("[1] Buat kupon");
            System.out.println("[2] Validasi kupon");
            System.out.println("[3] Keluar");
            System.out.print("Perintah : ");
            Integer perintah = input.nextInt();
            // perintah 1, generate id kupon
            if (perintah == 1){
                System.out.print("Nama kupon : ");
                String namaKupon = input.next();
                System.out.println("Kode kupon adalah : " + generateKupon(namaKupon, namaKupon.length() + 1));
            }
            // perintah 2, cek validitas kupon dengan memanggil method generatekupon
            else if (perintah == 2){
                System.out.print("Kupon : ");
                String namaKupon = input.next();
                String validasiKupon = "";
                // jadikan huruf besar
                namaKupon = namaKupon.toUpperCase(); 
                // ambil nama kupon kecuali dua dari belakang
                validasiKupon = namaKupon.substring(0, (namaKupon.length() - 2));
                String kupon = generateKupon(validasiKupon, (validasiKupon.length() + 1 ));
                if (namaKupon.equals(kupon)){
                    System.out.println("Kupon yang diberikan valid");
                }
                else {
                    System.out.println("Kupon yang diberikan tidak valid");
                }
            }
            // perintah 3, keluar loop atau program
            else if (perintah == 3){
                loop = false;
            }
        }
        // tutup scanner
        input.close();
    }
  // method untuk generate id kupon
  public static String generateKupon(String nama, Integer panjangIndex){
    // mengubah nama menjadi uppercase
    nama = nama.toUpperCase(); 
    Integer checksum = 0;
    Character charChecksum = ' ';
    String strChecksum = "";
    // buka loop sepanjang input
    for (Integer x = 0; x < nama.length(); x++){
        // pisah setiap character input
        Character character = nama.charAt(x);
        // character disesuaikan dengan nilainya dan ditambahkan
        checksum += ((int) character-65);
    }
    // dapatkan huruf baru dengan dimod 26
    Integer checksumKeString = checksum % 26;
    charChecksum = (char) (checksumKeString + 'A');
    strChecksum = (nama+charChecksum);
    // recursion
    // Apabila panjang nama sekarang sudah sama, return string hasil checksum
    if (panjangIndex == nama.length()){
        return strChecksum;
    }
    // base case
    // Apabila panjang nama sekarang belum sepanjang panjang yang diinginkan, method dijalankan lagi
    else if (panjangIndex > nama.length()){
        strChecksum = generateKupon(strChecksum, panjangIndex);
    }
    // mencegah error dari java
    return strChecksum;
}
}