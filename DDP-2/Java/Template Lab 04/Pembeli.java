import java.util.Arrays;

class Pembeli {

    String nama;
    long jumlahUang;
    Pesanan[] listPesanan = new Pesanan[20];
    final int MAKS_JUMLAH_BARANG = 20;

    public Pembeli(String nama, Long jumlahUang){
        this.nama = nama;
        this.jumlahUang = jumlahUang;
    }

    /*
     * Method yang akan mengembalikan sebuah String yang merupakan pesan hasil dari
     * query BELI.
     */
    public String tambahPesanan(Barang barang, int jumlah){
        return null;
    }

    public String tambah(Barang barang, int jumlah){
        for (int i = 0; i < listPesanan.length; i++){
                if (listPesanan[i] == (null)){
                    listPesanan[i] = new Pesanan(barang, jumlah);
                    break;
                }
                else if (listPesanan[i].getBarang().getNama().equals(barang.getNama())){
                    listPesanan[i].setJumlahBarang(listPesanan[i].getJumlahBarang()+jumlah);
                    break;
                }
        }
        //System.out.println(Arrays.toString(listPesanan));
        return null;
    }

    /*
     * Method untuk mengosongkan list pesanan
     */
    public void resetPesanan(){
        listPesanan = new Pesanan[20];
    }

    public String getNama() {
        return nama;
    }
    public long getJumlahUang() {
        return jumlahUang;
    }
    public void setJumlahUang(Long jumlahUang) {
        this.jumlahUang = jumlahUang;
    }
    public Integer getTotalBeli() {
        return jumlahUang;
    }
    // method untuk mengambil total pesanan suatu pembeli
    public Integer getTotalSemuaPesanan() {
        Integer jumlahPesanan = 0;
        for (int i = 0; i < listPesanan.length; i++){
            if (listPesanan[i] == (null)){
                continue;
            }
            else {
                jumlahPesanan = listPesanan[i].getJumlahBarang() + jumlahPesanan;
                continue;
            }
    }
        return jumlahPesanan;
    }
     // method untuk mengambil harga total pesanan suatu pembeli
    public Long getHargaTotalSemuaPesanan() {
        Long totalHarga = 0L;
        for (int i = 0; i < listPesanan.length; i++){
            if (listPesanan[i] == (null)){
                continue;
            }
            else {
                totalHarga = totalHarga + listPesanan[i].totalHarga();
                continue;
            }
    }
        return totalHarga;
    }
     // method untuk membuat nota bayar berdasarkan total pesanan
    public String notaBayar(){
        String nota = "";
        for (int i = 0; i < listPesanan.length; i++){
            if (listPesanan[i] == (null)){
                continue;
            }
            else {
                nota =  nota + listPesanan[i].getBarang().getNama() + ": " + listPesanan[i].getBarang().getHarga() + " * " + listPesanan[i].getJumlahBarang() + " = " + listPesanan[i].totalHarga() + "\n" ;
                continue;
            }
        }
        return nota;
    }
}
