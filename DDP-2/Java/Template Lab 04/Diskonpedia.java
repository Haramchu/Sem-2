import java.io.*;
import java.util.StringTokenizer;

import assignments.assignment1.NotaGenerator;

import java.util.Arrays;  

public class Diskonpedia {

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    private static Barang[] listBarang;
    private static Pembeli[] listPembeli;

    /*
     * Method utama program
     */
    public static void main(String[] args) {

        // Inisiasi Barang
        int jumlahBarang = in.nextInt();
        listBarang = new Barang[jumlahBarang];

        for(int i = 0; i < jumlahBarang; i++){
            String namaBarang = in.next();
            long harga = in.nextLong();
            int stok = in.nextInt();
            listBarang[i] = new Barang(harga, namaBarang, stok);

        }

        // Inisiasi Pembeli
        int jumlahPembeli = in.nextInt();
        listPembeli = new Pembeli[jumlahPembeli];

        for(int i = 0; i < jumlahPembeli; i++){
            String namaPembeli = in.next();
            long jumlahUang = in.nextLong();
            listPembeli[i] = new Pembeli(namaPembeli, jumlahUang);
        }

        // Jalanin Query
        int jumlahPerintah = in.nextInt();

        for(int i = 0; i < jumlahPerintah; i++){
            String perintah = in.next();
            switch (perintah) {
                case "PESAN" -> {
                    String namaPembeli = in.next();
                    String namaBarang = in.next();
                    int jumlah = in.nextInt();
                    pesan(namaPembeli, namaBarang, jumlah);
                    break;
                }
                case "BAYAR" -> {
                    String namaPembeli = in.next();
                    bayar(namaPembeli);
                    break;
                }
                case "DISKON" -> {
                    String namaPembeli = in.next();
                    diskon(namaPembeli);
                    break;
                }
                case "RESTOCK" -> {
                    String namaBarang = in.next();
                    int jumlah = in.nextInt();
                    restock(namaBarang, jumlah);
                    break;
                }
            }
        }
        out.close();
    }

    /*
     * Method untuk perintah PESAN
     */
    public static void pesan(String namaPembeli, String namaBarang, int jumlah){
        // validasi sesuai soal berdasarkan jumlah stok, kapasitas pembelian, dan jumlah uang
        if (cariBarang(namaBarang).getStok() < jumlah ){
            System.out.println("Tidak bisa memesan " + namaBarang + " sebanyak " + jumlah + " buah. Stok barang tidak cukup");
        }
        else if (cariPembeli(namaPembeli).getJumlahUang() < (cariPembeli(namaPembeli).getHargaTotalSemuaPesanan() + jumlah * cariBarang(namaBarang).getHarga())){
            System.out.println("Tidak bisa memesan " + namaBarang + " sebanyak " + jumlah + " buah. Uang " + namaPembeli + " tidak cukup.");
        }
        else if (20 < cariPembeli(namaPembeli).getTotalSemuaPesanan() + jumlah ){
            System.out.println("Tidak bisa memesan " + namaBarang + " sebanyak " + jumlah + " buah. List pesanan " + namaPembeli + " melebihi kapasitas");
        }
        // kalau benar, masuk ke dalam class
        else {
            cariPembeli(namaPembeli).tambah(cariBarang(namaBarang), jumlah);
            cariBarang(namaBarang).setStok(cariBarang(namaBarang).getStok()-jumlah);
            System.out.println(namaPembeli + " berhasil memesan sebanyak " + namaBarang + " sebanyak " + jumlah + " buah.");
        }
    }

    /*
     * Method untuk perintah BAYAR
     */
    public static void bayar(String namaPembeli){
        System.out.println( namaPembeli + " berhasil melakukan pembelian barang dan pembayaran!");
        System.out.println("########## Detail Pembayaran ##########");
        System.out.println(cariPembeli(namaPembeli).notaBayar());
        System.out.println("_______________________________________");
        System.out.println("Total harga = " + cariPembeli(namaPembeli).getHargaTotalSemuaPesanan());
        System.out.println("Diskon = (" + (hitungDiskon(cariPembeli(namaPembeli)) * cariPembeli(namaPembeli).getHargaTotalSemuaPesanan() / 100) + ")");
        System.out.println("Harga bayar = " + (cariPembeli(namaPembeli).getHargaTotalSemuaPesanan() - (hitungDiskon(cariPembeli(namaPembeli)) * cariPembeli(namaPembeli).getHargaTotalSemuaPesanan() / 100)));
        System.out.println("Sisa uang = " + (cariPembeli(namaPembeli).getJumlahUang() - ((cariPembeli(namaPembeli).getHargaTotalSemuaPesanan() - cariPembeli(namaPembeli).getTotalSemuaPesanan() * cariPembeli(namaPembeli).getHargaTotalSemuaPesanan() / 100))));
        System.out.println("#######################################");
        // kurangi jumlah uang
        cariPembeli(namaPembeli).setJumlahUang(cariPembeli(namaPembeli).getJumlahUang() - ((cariPembeli(namaPembeli).getHargaTotalSemuaPesanan() - cariPembeli(namaPembeli).getTotalSemuaPesanan() * cariPembeli(namaPembeli).getHargaTotalSemuaPesanan() / 100)));
        cariPembeli(namaPembeli).resetPesanan();

    }

    /*
     * Method untuk perintah RESTOCK
     */
    public static void restock(String namaBarang, int jumlah){
        if (jumlah < 1){
            System.out.println("Maaf, stok tambahan yang dimasukkan tidak valid");
        }
        else {
            cariBarang(namaBarang).setStok(cariBarang(namaBarang).getStok() + jumlah);
            System.out.println("Berhasil menambahkan stok barang Aqua. Sisa stok sekarang = " + cariBarang(namaBarang).getStok());
        }
    }

    /*
     * Method untuk perintah DISKON.
     */
    public static void diskon(String namaPembeli){
        System.out.println(namaPembeli + " mendapatkan diskon sebanyak " + hitungDiskon(cariPembeli(namaPembeli)) + "%");
    }

    /*
     * Method untuk mencari persentase diskon yang didapat oleh pembeli.
     * Method ini mengembalikan persentase diskon yang didapat.
     */
    public static int hitungDiskon(Pembeli pembeli){
        return pembeli.getTotalSemuaPesanan();
    }

    /*
     * Method untuk mencari Barang berdasarkan nama
     */
    public static Barang cariBarang(String nama){
        for(Barang barang: listBarang){
            if(barang.getNama().equals(nama))
                return barang;
        }
        return null;
    }

    /*
     * Method untuk mencari Pembeli berdasarkan nama
     */
    public static Pembeli cariPembeli(String nama){
        for(Pembeli pembeli: listPembeli){
            if(pembeli.getNama().equals(nama))
                return pembeli;
        }
        return null;
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
