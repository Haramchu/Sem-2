public class Tea extends Beverage {
    //modifier tidak diubah
    private boolean hasMilk;

    public Tea(String nama, String size, boolean isCold) {
        super(nama, size, isCold);
    }

    @Override
    public void calculatePrice() {
        // menghitung harga
        int harga = 0;
        if (hasMilk){
          harga += 7000;
        }
        if (this.size.equalsIgnoreCase("TALL")){
          harga += 15000;
        }
        else if (this.size.equalsIgnoreCase("GRANDE")){
          harga += 20000;
        }
        else if (this.size.equalsIgnoreCase("VENTI")){
          harga += 25000;
        }
        this.price = harga;
    }
    @Override
    public void addMilk() {
        // inisiasi susu pada tea
        this.hasMilk = true;
    }

    @Override
    public String toString() {
        String res = super.toString();

        if (hasMilk) {
          res += " with Milk";
        }
    
        res += " Rp. " + this.getPrice() + ",-";
    
        return res;
    }
}