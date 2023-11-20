public class Beverage {
    //ditambahkan setter getter karena ada pemanggilan method get.name() di starcil
    private String name;
    protected String size;
    protected boolean isCold;
    protected int price;
  
    public Beverage(String name, String size, boolean isCold) {
      this.name = name;
      this.size = size;
      this.isCold = isCold;
    }
  
    // Method sengaja dikosongkan
    public void calculatePrice() {
    }
  
    public String toString() {
      String output = "";
  
      if (isCold) {
        output += "COLD ";
      } else {
        output += "HOT ";
      }
  
      output += this.size + " " + this.name;
      return output;
    }
    // getter setter
    public String getName(){
      return name;
    }
    public int getPrice(){
      calculatePrice();
      return price;
    }
    // untuk method overriding
    public void addWhipCream() {
    }
    public void addMilk() {
    }
  }