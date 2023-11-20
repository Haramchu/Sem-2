import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Merger {

    public static void main(String[] args) {
        // setup awal
        Scanner input = new Scanner (System.in);
        System.out.print("File playlist pertama: ");
        String file1 = input.next();
        String line = "";
        ArrayList<String> merge = new ArrayList<String>();
        ArrayList<String> uniqueMerge = new ArrayList<String>();
        // buka try dan baca file
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(file1));
            line = reader1.readLine();
            checkLine(line);
            while (line != null) {
                checkLine(line);
                merge.add(line);
                line = reader1.readLine();
            }
            // buka try lagi dan baca file kedua
            try {
                System.out.print("File playlist kedua: ");
                String file2 = input.next();
                BufferedReader reader2 = new BufferedReader(new FileReader(file2));
                line = reader2.readLine();
                while (line != null) {
                    checkLine(line);
                    merge.add(line);
                    line = reader2.readLine();
                }
                System.out.print("File playlist output: ");
                String output = input.next();
                // buka file untuk menulis hasil baca
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
                    // pengecekan duplikat
                    for (String lines : merge){
                        boolean duplicate = false;
                        for (String uniqueLines : uniqueMerge){
                            if (uniqueLines.equals(lines)){
                                duplicate = true;
                                break;
                            }
                        }
                        if (!duplicate){
                            uniqueMerge.add(lines);
                        }
                    }
                    for (String lines : uniqueMerge){
                        writer.write(lines);
                        writer.newLine();
                    }
                    System.out.println("Berhasil menimpa playlist, jumlah lagu adalah: " + uniqueMerge.size());
                } 
                // catch untuk tiap try, filenotfound dan ioexception kurang lebih sama hanya saja bufferedreader atau writer memerlukan ioexception
                catch (IOException e) {
                    System.err.println("File tidak ditemukan!");
                    System.exit(0);
                }
            }
            catch (FileNotFoundException e) {
                System.err.println("File tidak ditemukan!");
                System.exit(0);
                input.close();
            }
            catch (InvalidPlaylistException e){
                System.err.println("Playlist tidak valid!");
                System.exit(0);
                input.close();
            }
            catch (IOException e) {
                System.err.println("File tidak ditemukan!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("File tidak ditemukan!");
            System.exit(0);
            input.close();
        }
        catch (InvalidPlaylistException e){
            System.err.println("Playlist tidak valid!");
            System.exit(0);
            input.close();
        }
        catch (IOException e) {
            System.err.println("File tidak ditemukan!");
            System.exit(0);
        }
    } 
        
    //method validasi playlist
    public static void checkLine(String line) throws InvalidPlaylistException {
        String[] arr = line.split("\\|\\|");
        if (arr.length >= 3 || arr.length <= 1) {
            throw new InvalidPlaylistException("Playlist tidak valid");
        }
        else if (arr[0].substring(0, 1).equals("|") || arr[0].substring(arr[0].length() - 1, arr[0].length()).equals("|") || arr[1].substring(0, 1).equals("|") || arr[1].substring(arr[1].length() - 1, arr[1].length()).equals("|")){
            throw new InvalidPlaylistException("Playlist tidak valid");
        }
    }
}