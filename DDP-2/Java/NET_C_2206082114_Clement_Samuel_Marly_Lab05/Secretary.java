import java.text.DecimalFormat;
public class Secretary extends Employee{
    double tunjangan;
    // set tunjangan
    Secretary (String nama, double tunjangan){
      super(nama);
      this.tunjangan = tunjangan;
    }

    @Override
    public String toString() {
      //override print string employee sesuai format secretary
      DecimalFormat formatter = new DecimalFormat("#");
      formatter.setMaximumFractionDigits(2);
      String statistics = "";
      statistics += "Nama: " + this.nama + "\n";
      statistics += "Pengalaman Kerja: " + this.pengalamanKerja + "\n";
      statistics += "Status: " + this.status + "\n";
      statistics += "NetWorth: Rp" + formatter.format(this.netWorth) + "\n";
      statistics += "Jabatan: " + this.jabatan + "\n";
      statistics += "Role: Secretary \n";
      statistics += "Banyak Tunjangan: " + formatter.format(this.tunjangan) + "\n";
      return statistics;
    }

    @Override
    void nextYear(int n){
      // setup variable awal
      Double netWorthSementara = 0.0;
      int pensiunCounter = 0;
      // setup pensiun
      if (this.pengalamanKerja > 15){
        this.jabatan = "Pensiun";
        this.pengalamanKerja = 16;
      }
      // loop berdasar jabatan
      else {
        for (int i = 0; i < n; i++){
          this.pengalamanKerja = this.pengalamanKerja + 1;
          if (this.pengalamanKerja <= 5){
            netWorthSementara += 3000000;
            this.jabatan = "Junior";
            pensiunCounter += 1;
          }
          else if(this.pengalamanKerja <= 10){
            netWorthSementara += 6000000;
            this.jabatan = "Senior";
            pensiunCounter += 1;
          }
          else if(this.pengalamanKerja <= 15){
            netWorthSementara += 9000000;
            this.jabatan = "Expert";
            pensiunCounter += 1;
          }
        }
        this.netWorth = this.netWorth + netWorthSementara + pensiunCounter*tunjangan;
      }
      // setup pensiun langsung
      if (this.pengalamanKerja > 15){
        this.jabatan = "Pensiun";
        this.pengalamanKerja = 16;
      }
      pensiun();
    }
  }