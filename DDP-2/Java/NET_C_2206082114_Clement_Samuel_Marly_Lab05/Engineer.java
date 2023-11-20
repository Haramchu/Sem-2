import java.text.DecimalFormat;
public class Engineer extends Employee{
    int banyakSideJobs; 
    // set banyak side jobs
    Engineer (String nama, int banyakSideJobs){
      super(nama);
      this.banyakSideJobs = banyakSideJobs;
    }

    @Override
    public String toString() {
      //override print string employee sesuai format engineer
      DecimalFormat formatter = new DecimalFormat("#");
      formatter.setMaximumFractionDigits(2);
      String statistics = "";
      statistics += "Nama: " + this.nama + "\n";
      statistics += "Pengalaman Kerja: " + this.pengalamanKerja + "\n";
      statistics += "Status: " + this.status + "\n";
      statistics += "NetWorth: Rp" + formatter.format(this.netWorth) + "\n";
      statistics += "Jabatan: " + this.jabatan + "\n";
      statistics += "Role: Engineer \n";
      statistics += "Banyak sidejobs: " + this.banyakSideJobs + "\n";
      return statistics;
    }
    
    @Override
    void nextYear(int n){
      // setup variable awal
      Double gajiSidejobs =  Double.valueOf(this.banyakSideJobs * 500000);
      Double netWorthSementara = 0.0;
      int pensiunCounter = 0;
      // setup pensiun
      if (this.pengalamanKerja > 15){
        this.jabatan = "Pensiun";
        this.pengalamanKerja = 16;
      }
      else {
        // loop harga berdasar jabatan
        for (int i = 0; i < n; i++){
          this.pengalamanKerja = this.pengalamanKerja + 1;
          if (this.pengalamanKerja <= 5){
            netWorthSementara += 4000000;
            this.jabatan = "Junior";
            pensiunCounter += 1;
          }
          else if(this.pengalamanKerja <= 10){
            netWorthSementara += 8000000;
            this.jabatan = "Senior";
            pensiunCounter += 1;
          }
          else if(this.pengalamanKerja <= 15){
            netWorthSementara += 12000000;
            this.jabatan = "Expert";
            pensiunCounter += 1;
          }
        }
        this.netWorth = this.netWorth + netWorthSementara + pensiunCounter*gajiSidejobs;
      }
      // setup catch pensiun langsung
      if (this.pengalamanKerja > 15){
        this.jabatan = "Pensiun";
        this.pengalamanKerja = 16;
      }
    pensiun();
    }
}