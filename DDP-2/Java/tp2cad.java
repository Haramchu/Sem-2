
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Scanner;
// import java.util.Arrays;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static Nota[] notaList;
    private static Member[] memberList;
    private static int jumlahMember = 0;
    private static int jumlahNota = 0;
    private static int idNota = -1;
    private static String tanggalLaundry = fmt.format(cal.getTime());
    private static int bonusCounter = 0;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        System.out.println("Masukkan nama Anda:");
        String nama = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda:");
        String nomorHP = input.nextLine();

        while (!nomorHP.matches("\\d+")){
            System.out.println("Field nomor hp hanya menerima digit.");
            nomorHP = input.nextLine();
        }

        boolean found = false;
        if (jumlahMember != 0){
            String id = generateId(nama, nomorHP);
            String uniqueId = id.substring(id.length()-2, id.length());
            for (int i = 0; i < memberList.length; i ++){
                if (memberList[i].getUniqueId().equals(uniqueId)){
                    found = true;
                }
            }
        }

        if (found){
            System.out.println("Member dengan nama " + nama + " dan nomor hp " + nomorHP + " sudah ada!");
        }

        else{
            jumlahMember += 1;
            Member calonMember = new Member (nama, nomorHP);
            calonMember.setId(id);
            Member[] arrMember = new Member[jumlahMember];
            String id = generateId(nama, nomorHP);
    
            if(jumlahMember <= 1){
                for (int i = 0; i < jumlahMember; i++){
                    arrMember[i] = calonMember;
                }
            }
    
            else{
                for (int i = 0; i < jumlahMember; i++){
                    try {
                        arrMember[i] = memberList[i];
                    } 
                    
                    catch (Exception e) {
                        if (i == jumlahMember-1){
                            arrMember[i] = calonMember;
                        }
                    }
                }
            }
            memberList = arrMember;
            // System.out.println(Arrays.toString(memberList));
            // System.out.println(memberList[0].getName());
            // System.out.println(memberList[0].getNoHp());
            // System.out.println(memberList[0].getUniqueId());
            System.out.println("Berhasil membuat member dengan ID " + id); 
        }
        
    }

    private static void handleGenerateNota() {
        if (jumlahMember == 0){
            System.out.println("Belum ada member yang terdaftar di dalam sistem!");
        }

        else{
            boolean notFound = false;
            boolean found = false;
            System.out.println("Masukan ID member:");
            String id = input.nextLine();
            int indexMember = -1;

                for (int i = 0; i < memberList.length; i++){
                    if (!id.equals(memberList[i].getId())){
                        notFound = true;
                    }
                    
                    else{
                        notFound = false;
                        found = true;
                        indexMember = i;
                        break;
                    }
                }

            if (notFound == true){
                System.out.println("Member dengan ID " + id + " tidak ditemukan!");
            }

            if(found == true){ 
                bonusCounter += 1;
                idNota += 1;
                jumlahNota += 1;
                System.out.println("Masukan paket laundry: ");
                String paketLaundry = input.nextLine().toLowerCase();

                while (!(paketLaundry.equals("express") || paketLaundry.equals("fast") || paketLaundry.equals("reguler"))){
                    if (paketLaundry.equals("?")){
                        showPaket();
                        System.out.println("Masukkan paket laundry:");
                        paketLaundry = input.nextLine().toLowerCase();
                    }

                    else{
                        System.out.println("Paket " + paketLaundry + " tidak diketahui" + '\n' + "[ketik ? untuk mencari tahu jenis paket]");
                        System.out.println("Masukkan paket laundry:");
                        paketLaundry = input.nextLine().toLowerCase();

                        if (paketLaundry.equals("?")){
                        showPaket();
                        System.out.println("Masukkan paket laundry:");
                        paketLaundry = input.nextLine().toLowerCase();
                        }
                    }
                }

                System.out.println("Masukkan berat cucian Anda [Kg]:");
                String beratLaundry = input.nextLine();

                while (!beratLaundry.matches("\\d+") || beratLaundry.equals("0")){
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    beratLaundry = input.nextLine();
                }
                
                int berat = Integer.valueOf(beratLaundry);
                Nota notaMember = new Nota(memberList[indexMember], paketLaundry, berat, tanggalLaundry);
                Nota[] arrNota = new Nota[jumlahNota];

                if(jumlahMember <= 1){
                    for (int i = 0; i < jumlahNota; i++){
                        arrNota[i] = notaMember;
                    }
                }
        
                else{
                    for (int i = 0; i < jumlahNota; i++){
                        try {
                            arrNota[i] = notaList[i];
                        } 
                        
                        catch (Exception e) {
                            if (i == jumlahNota-1){
                                arrNota[i] = notaMember;
                            }
                        }
                    }
                }
                notaList = arrNota;
                String tanggalSelesai = " ";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate laundryDate = LocalDate.parse(tanggalLaundry, formatter);
                if (paketLaundry.equals("express")){
                    LocalDate newDate = laundryDate.plusDays(1);
                    tanggalSelesai = newDate.format(formatter);
        
                }
                else if (paketLaundry.equals("fast")){
                    LocalDate newDate = laundryDate.plusDays(2);
                    tanggalSelesai = newDate.format(formatter);
                    
                }
                else if (paketLaundry.equals("reguler")){
                    LocalDate newDate = laundryDate.plusDays(3);
                    tanggalSelesai = newDate.format(formatter);
                }

                LocalDate finishDate = LocalDate.parse(tanggalSelesai, formatter);
                int sisaHariPengerjaan = (int) ChronoUnit.DAYS.between(laundryDate, finishDate);
                notaMember.setIdNota(idNota);
                notaMember.setSisaHariPengerjaan(sisaHariPengerjaan);
                notaMember.getMember().setBonusCounter(bonusCounter);
                int checkBonusCounter = notaMember.getMember().getBonusCounter();
                
                String nota = "";
                if (checkBonusCounter % 3 != 0){
                    nota = generateNota(id, paketLaundry, berat, notaMember.getTanggalMasuk());
                }
                else{
                    bonusCounter = 0;
                    nota = generateNotaDiskon(id, paketLaundry, berat, notaMember.getTanggalMasuk());
                    
                }
                System.out.println("[ID Nota = "+ idNota + "]");
                System.out.println(nota);
                if(!tanggalLaundry.equals(tanggalSelesai)){
                    System.out.println("Status       : Belum bisa diambil :(");
                }
            }
        }
    }
    private static void handleListNota() {
        if (notaList == null || notaList.length == 0){
            System.out.println("Terdaftar 0 nota dalam sistem.");
        }
        else{
            System.out.println("Terdaftar " + notaList.length + " nota dalam sistem.");
            for (int i = 0; i < notaList.length; i++){
                if (notaList[i].isReady()){
                    System.out.println("- [" + notaList[i].getIdNota() + "]" + " Status       : Sudah dapat diambil !");
                }
                else{
                    System.out.println("- [" + notaList[i].getIdNota() + "]" + " Status       : Belum bisa diambil :(");
                }
            }
        }
    }

    private static void handleListUser() {
        if (jumlahMember == 0){
            System.out.println("Terdaftar 0 member dalam sistem.");
        }
        else{
            System.out.println("Terdaftar " + memberList.length + " member dalam sistem.");
            for (int i = 0; i < memberList.length; i++){;
                System.out.println("- " + memberList[i].getId() + " : " + memberList[i].getName());
            }
        }
    }

    private static void handleAmbilCucian() {
        if (notaList == null || notaList.length == 0){
            System.out.println("Belum ada nota yang terdaftar di dalam sistem!");
        }
        else{
            System.out.println("Masukan ID nota yang akan diambil:");
            String idNotaDiambil = input.nextLine();
            while (!idNotaDiambil.matches("\\d+")){
                System.out.println("ID nota berbentuk angka!");
                idNotaDiambil = input.nextLine();
            }
            int IdNotaRemove = Integer.valueOf(idNotaDiambil);
            int index = -1;
            boolean notDone = false;
            boolean done = false;
            boolean notFound = false;
            for (int i = 0; i < notaList.length; i++){
                if(IdNotaRemove == notaList[i].getIdNota()){
                    if(notaList[i].isReady()){
                        done = true;
                        notFound = false;
                        notDone = false;
                        index = i;
                        break;
                    }

                    else{
                        notDone = true;
                    }
                }

                else{
                    notFound = true;
                }
            }

            if (notDone == true){
                System.out.println("Nota dengan ID "+ IdNotaRemove + " gagal diambil!");
            }

            if (notFound == true){
                System.out.println("Nota dengan ID "+ IdNotaRemove + " tidak ditemukan!");
            }

            if(done == true){
                System.out.println("Nota dengan ID "+ IdNotaRemove + " berhasil diambil!");
            }


            if (index != -1){
                jumlahNota -= 1;
                Nota[] arrNota = new Nota[jumlahNota];

                for (int i = 0; i < index; i++){
                    arrNota[i] = notaList[i];
                }
        
                for (int i = index + 1; i < notaList.length; i++) {
                    arrNota[i - 1] = notaList[i];
                }

                notaList = arrNota;
            }
        }
    }

    private static void handleNextDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate laundryDate = LocalDate.parse(tanggalLaundry, formatter);
        LocalDate newDate = laundryDate.plusDays(1);
        tanggalLaundry = newDate.format(formatter);

        System.out.println("Dek Depe tidur hari ini... zzz...");
        if (jumlahNota > 0){
            for (int i = 0; i < jumlahNota; i++){
                notaList[i].setSisaHariPengerjaan(notaList[i].getSisaHariPengerjaan()-1);
                if(notaList[i].isReady()){
                    System.out.println("Laundry dengan nota ID " + notaList[i].getIdNota() + " sudah dapat diambill");
                }
            }
        }
        System.out.println("Selamat pagi dunia!" + "\n" + "Dek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", tanggalLaundry);
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

    private static String generateNotaDiskon(String id, String paket, int berat, String tanggalTerima){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate laundryDate = LocalDate.parse(tanggalTerima, formatter);
        String tanggalSelesai = "";

        if (berat < 2){
            berat = 2;
        }
        
        int packagePrice = 0;
        if (paket.equals("express")){
            packagePrice = 12000;
            LocalDate newDate = laundryDate.plusDays(1);
            tanggalSelesai = newDate.format(formatter);

        }
        else if (paket.equals("fast")){
            packagePrice = 10000;
            LocalDate newDate = laundryDate.plusDays(2);
            tanggalSelesai = newDate.format(formatter);
            
        }
        else if (paket.equals("reguler")){
            packagePrice = 7000;
            LocalDate newDate = laundryDate.plusDays(3);
            tanggalSelesai = newDate.format(formatter);
        }
        int price = berat * packagePrice;
        
        String outputNota = "";
        outputNota = "ID    : " + id + '\n' + "Paket : " + paket + '\n' + "Harga :" 
        + '\n' + berat + " kg x " + packagePrice + " = " + price + " = " + price/2 + 
        " (Discount member 50%!!!)" + '\n' + "Tanggal Terima  : " + tanggalTerima + '\n' 
        + "Tanggal Selesai : " + tanggalSelesai;

        return outputNota;
    }
}