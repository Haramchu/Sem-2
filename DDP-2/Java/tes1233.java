import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class tes1233 {
    String nama;
    String[] warnaBulu;
    int beratBadan;
    tes1233(String nama, String[] warnaBulu, int beratBadan){
        this.nama = nama;
        this.warnaBulu = warnaBulu;
        this.beratBadan = beratBadan;
    }
    public String getNama(){
        return nama;
    }
    public int getBeratBadan(){
        return beratBadan;
    }
    public boolean equals(Object o2){
        if (o2 instanceof tes1233){
            return ((tes1233)o2).getNama().equals(this.getNama())&&((tes1233)o2).getBeratBadan()==this.getBeratBadan();
        }
        else{
            return false;
        }
    }
    public static void main(String[] args){
        tes1233 o1 = new tes1233("Tom", new String[]{"grey"}, 4000);
        tes1233 o2 = new tes1233("Zorro", new String[]{"black","gold"}, 4200);
        tes1233 o3 = new tes1233("Tom", new String[]{"grey"}, 4000);
        System.out.println(o1.equals(o2));
        System.out.println(o1.equals(o3));
    }
}