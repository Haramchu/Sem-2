import java.util.Scanner;
public class Quiz3 {
    static Integer [] jumlahDigit = {0,0,0,0,0,0,0,0,0};
    public static void main(String[] args){
        Scanner input = new Scanner (System.in);
        System.out.print("Masukkan array berisi angka 1-9 : ");
        String inputAngka = input.nextLine();
        for (Integer i = 0; i < inputAngka.length(); i++){
            Character digitCharacter = inputAngka.charAt(i);
            hitungKemunculan(digitCharacter);
        }
        for (int i = 1; i <= jumlahDigit.length; i++) {
            if (jumlahDigit[i-1] > 0){
                System.out.println("Bilangan " + i + " muncul sebanyak " + jumlahDigit[i-1] + " kali");
            }
            else {

            }
            }
    }
    public static void hitungKemunculan(Character digit) {
        Character satu = '1'; Character dua = '2'; Character tiga = '3'; Character empat = '4'; Character lima = '5'; 
        Character enam = '6'; Character tujuh = '7'; Character delapan = '8'; Character sembilan = '9';
        if (digit.equals(satu)){
            jumlahDigit[0] = jumlahDigit[0] + 1;
        }
        if (digit.equals(dua)){
            jumlahDigit[1] = jumlahDigit[1] + 1;
        }
        if (digit.equals(tiga)){
            jumlahDigit[2] = jumlahDigit[2] + 1;
        }
        if (digit.equals(empat)){
            jumlahDigit[3] = jumlahDigit[3] + 1;
        }
        if (digit.equals(lima)){
            jumlahDigit[4] = jumlahDigit[4] + 1;
        }
        if (digit.equals(enam)){
            jumlahDigit[5] = jumlahDigit[5] + 1;
        }
        if (digit.equals(tujuh)){
            jumlahDigit[6] = jumlahDigit[6] + 1;
        }
        if (digit.equals(delapan)){
            jumlahDigit[7] = jumlahDigit[7] + 1;
        }
        if (digit.equals(sembilan)){
            jumlahDigit[8] = jumlahDigit[8] + 1;
        }
        else {

        }

    }
    
}