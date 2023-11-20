public class Coffee extends Beverage {
    //modifier tidak diubah
    private boolean hasWhipCream = false;
  
    public Coffee(String nama, String size, boolean isCold) {
      super(nama, size, isCold);
    }
  
    @Override
    public void calculatePrice() {
      // menghitung harga
      int harga = 0;
      if (hasWhipCream){
        harga += 5000;
      }
      if (this.size.equalsIgnoreCase("TALL")){
        harga += 20000;
      }
      else if (this.size.equalsIgnoreCase("GRANDE")){
        harga += 25000;
      }
      else if (this.size.equalsIgnoreCase("VENTI")){
        harga += 30000;
      }
      this.price = harga;
    }
    @Override
    public void addWhipCream() {
      // inisiasi whip cream pada coffee
      this.hasWhipCream = true;
    }
  
    public String toString() {
      String res = super.toString();
      
      if (hasWhipCream) {
        res += " with Whip Cream";
      }
      res += " Rp. " + super.getPrice() + ",-";
  
      return res;
    }
  }