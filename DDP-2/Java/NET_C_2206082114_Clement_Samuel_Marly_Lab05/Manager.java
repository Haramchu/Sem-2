import java.text.DecimalFormat;
public class Manager extends Employee{
    double raise;
    // set raise
    Manager (String nama, double raise){
      super(nama);
      this.raise = raise;
    }

    @Override
    public String toString() {
      //override print string employee sesuai format manager
      DecimalFormat formatter = new DecimalFormat("#");
      formatter.setMaximumFractionDigits(2);
      String statistics = "";
      statistics += "Nama: " + this.nama + "\n";
      statistics += "Pengalaman Kerja: " + this.pengalamanKerja + "\n";
      statistics += "Status: " + this.status + "\n";
      statistics += "NetWorth: Rp" + formatter.format(this.netWorth) + "\n";
      statistics += "Jabatan: " + this.jabatan + "\n";
      statistics += "Role: Manager \n";
      return statistics;
    }
    
    @Override
    void nextYear(int n){
      int pensiunCounter = 0;
      // setup pensiun
      if (pengalamanKerja > 15){
        this.pengalamanKerja = 16;
        this.jabatan = "Pensiun";
      }
      else {
        // cari jabatan
        for (int i = 0; i < n; i++){
          this.pengalamanKerja += 1;
          if (this.pengalamanKerja <= 5){
            this.jabatan = "Junior";
            pensiunCounter += 1;
          }
          else if(this.pengalamanKerja <= 10){
            this.jabatan = "Senior";
            pensiunCounter += 1;
          }
          else if(this.pengalamanKerja <= 15){
            this.jabatan = "Expert";
            pensiunCounter += 1;
          }
        }
        // hitung gaji
        for (int i = 0; i < pensiunCounter; i++){
          this.gaji = this.gaji * this.raise;
          this.netWorth += this.gaji;
        }
      }
      // setup pensiun langsung
      if (pengalamanKerja > 15){
        this.pengalamanKerja = 16;
        this.jabatan = "Pensiun";
      }
      pensiun();
    }
  }