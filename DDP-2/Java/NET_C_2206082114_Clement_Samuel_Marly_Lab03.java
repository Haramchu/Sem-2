import java.util.Arrays;
import java.util.Scanner;
public class NET_C_2206082114_Clement_Samuel_Marly_Lab03 {
    private static final Scanner input = new Scanner(System.in);
    // set size array karena tidak bisa menggunakan array list
    private static String[] kumpulanNamaMahasiswa = new String[100];
    private static String[] kumpulanNamaMatkul = new String[100];
    public static int[][] score;
    private static Integer[] banyakMatkul = new Integer[1];
    public static void main(String[] args) {
        // fill array dengan string kosong
        Arrays.fill(kumpulanNamaMahasiswa, "");
        Arrays.fill(kumpulanNamaMatkul, "");
        // print awalan
        init();
        while (true) {
            printMenu();
            System.out.print("Masukkan pilihan: ");
            Integer pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan) {
                case 1:
                    menambahMahasiswa();
                    break;
                case 2:
                    menghapusMahasiswa();
                    break;
                case 3:
                    mencetakTabel();
                    break;
                case 4:
                    mencetakNilai();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan BeJayNG!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
    }

    static void init() {
        System.out.println("Selamat datang di BeJayNG!");
        System.out.println("==============Initial Input==============");
        System.out.print("Masukkan jumlah mata kuliah : ");
        Integer jumlahMatkul = input.nextInt();
        // set 2d array score berdasarkan jumlahmatkul yang diinput dan catat angkanya
        score = new int[jumlahMatkul][100];
        banyakMatkul[0] = jumlahMatkul;
        // mencegah \n
        input.nextLine();
        // buka loop minta input nama matkul dan catat ke array
        for (Integer x = 0; x < jumlahMatkul; x++){
            System.out.print("Masukkan nama mata kuliah : ");
            String namaMatkul = input.nextLine();
            kumpulanNamaMatkul[x] = namaMatkul;
        }
        return;
    }

    static void printMenu() {
        System.out.println("==============Menu==============");
        System.out.println("[1] Menambah Mahasiswa");
        System.out.println("[2] Menghapus Mahasiswa");
        System.out.println("[3] Mencetak Tabel");
        System.out.println("[4] Mencetak Nilai");
        System.out.println("[0] Keluar");
    }

    static void menambahMahasiswa() {
        System.out.println("==============Menambah Mahasiswa==============");
        System.out.print("Masukkan nama mahasiswa : ");
        String nama = input.nextLine();
        Integer nomorMahasiswa = 0;
        // buka loop untuk mencari index array kosong
        for (Integer x = 0; x < 100; x++){
            if (kumpulanNamaMahasiswa[x].equals("")){
                nomorMahasiswa = x;
                break;
            }
        }
        // catat nama mahasiswa di array kosong
        kumpulanNamaMahasiswa[nomorMahasiswa] = nama;
        // buka loop untuk mencatat nilai matkul
        for (Integer x = 0; x < 100; x++){
            // break loop apabila mencapai array kosong di array mata kuliah
            if (kumpulanNamaMatkul[x].equals("")) {
                break;
            }
            else {
                // catat nilai matkul ke array 2d score
                System.out.print("Masukkan nilai " + kumpulanNamaMatkul[x] + " : ");
                Integer nilaiMatkul = input.nextInt();
                score[x][nomorMahasiswa] = nilaiMatkul;
            }
        }
        System.out.println("Nilai mahasiswa bernama " + nama + " berhasil diinput ke BeJayNG");
        return;
    }

    static void menghapusMahasiswa() {
        System.out.println("==============Menghapus Mahasiswa==============");
        System.out.print("Masukkan nama mahasiswa : ");
        String nama = input.nextLine();
        String namaDihapus = "";
        Integer jumlahMatkul = banyakMatkul[0];
        // buka loop untuk mencari nama di array yang sesuai input
        for (Integer x = 0; x < 100; x++){
            if (kumpulanNamaMahasiswa[x].equals(nama)){
                // reset nama dan nilai 
                namaDihapus = kumpulanNamaMahasiswa[x];
                kumpulanNamaMahasiswa[x] = "";
                for (Integer i = 0; i < jumlahMatkul; i++){
                    score[i][x]= 0;
                    }
                break;
                }
            }
        System.out.println("Mahasiswa bernama " + namaDihapus + " telah dihapus dari BeJayNG");
        }

    static void mencetakTabel() {
        System.out.println("==============Mencetak Tabel==============");
        String semuaMatkul = "";
        Integer jumlahMatkul = banyakMatkul[0];
        // gabung nama matkul dengan loop
        for (Integer i = 0; i < jumlahMatkul; i++){
            semuaMatkul = semuaMatkul + kumpulanNamaMatkul[i] + "\t";
        }
        System.out.println("Nama \t\t\t" + semuaMatkul);
        // gabung nilai mahasiswa dengan loop
        for (Integer x = 0; x < 100; x++){
            String nilaiSemuaMatkul = "";
            if (!kumpulanNamaMahasiswa[x].equals("")){
                for (Integer i = 0; i < jumlahMatkul; i++){
                    nilaiSemuaMatkul = nilaiSemuaMatkul + score[i][x] + "\t";
                }
                System.out.println(kumpulanNamaMahasiswa[x] + "\t\t\t" + nilaiSemuaMatkul);
            }
        }
        }

    static void mencetakNilai() {
        System.out.println("==============Mencetak Nilai==============");
        System.out.print("Masukkan nama mahasiswa : ");
        String nama = input.nextLine();
        Integer nomorMahasiswa = 0;
        Integer nomorMatkul = 0;
        // cari nama mahasiswa dengan loop
        for (Integer x = 0; x < 100; x++){
            if (kumpulanNamaMahasiswa[x].equals(nama)){
                nomorMahasiswa = x;
            }
        }
        // cari nama mata kuliah dengan loop
        System.out.print("Masukkan nama mata kuliah : ");
        String matkul = input.nextLine();
        for (Integer x = 0; x < 100; x++){
            if (kumpulanNamaMatkul[x].equals(matkul)){
                nomorMatkul = x;
            }
        }
        // cari nilai mahasiswa di array 2d berdasarkan dua loop diatas
        System.out.println("Nilai " + nama + " di mata kuliah " + matkul + " adalah " + score[nomorMatkul][nomorMahasiswa] );
        return;
    }
}