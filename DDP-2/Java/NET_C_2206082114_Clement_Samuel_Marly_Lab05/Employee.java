public class Employee {
    // set jabatan awal.
    String nama;
    int pengalamanKerja; 
    boolean status = true;
    double netWorth;
    String jabatan = "Junior"; 
    double gaji;
  
    Employee(String nama){
      this.nama = nama;
    }
    
    void nextYear(int tahun) {
       this.pengalamanKerja += tahun;
    }
  
    public String getNama() {
          return nama;
    }
 
    public void setJabatan(String jabatan){
      this.jabatan = jabatan;
    }
  
    public void setNama(String nama) {
        this.nama = nama;
    }
  
    public int getPengalamanKerja(){
      return pengalamanKerja;
    }
  
    public double getNetWorth(){
      return netWorth;
    }
    
    public void setNetWorth(double n){
      netWorth = n;
    }
  
    public void setGaji(double gaji) {
      this.gaji = gaji;
    }
  
    public double getGaji() {
      return gaji;
    }
    public void pensiun() {
      if (this.pengalamanKerja > 15){
        this.status = false;
      }
    }
  }