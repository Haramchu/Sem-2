import java.util.NoSuchElementException;
import java.util.Scanner;

public class VideoPlayer {
    // memakai collection array list
    private static VideoList<Movie> movieList;
    private static VideoList<DDPTubeVideo> ddpTubeVideoList;

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isFinish = false;

        movieList = new VideoList<Movie>();
        ddpTubeVideoList = new VideoList<DDPTubeVideo>();

        System.out.println("Selamat datang di DEDEPE Player!");
        while(!isFinish){
            try{
                printMainMenu();
                int menu = Integer.parseInt(in.nextLine());
                switch (menu) {
                    case 1 -> addVideo();
                    case 2 -> nextVideo();
                    case 3 -> deleteVideo();
                    case 4 -> printVideoList();
                    default -> {
                        System.out.println("Terima kasih sudah menggunakan DEDEPE Player!");
                        isFinish = true;
                    }
                }
            }
            catch (IllegalArgumentException e){
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("WRONG INPUT FORMAT!!\nError code: 401");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            }
            catch (NoSuchElementException e){
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("NO VIDEO FOUND!!\nError code: 402");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            }
            catch (Exception e){
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("UNKNOWN ERROR!!\nError code: 444");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            }
        }

    }

    /**
     * Method untuk menambahkan Video baru ke dalam videoList sesuai dengan posisi yang diinginkan
     */
    public static void addVideo(){
        System.out.println("---------------TAMBAH VIDEO----------------");
        System.out.println("Tambah Video Baru");
        System.out.print("Masuk di (1) paling depan atau (2) paling belakang: ");
        int pos = Integer.parseInt(in.nextLine());
        System.out.print("Jenis: ");
        String type = in.nextLine();
        System.out.print("Judul: ");
        String title = in.nextLine();
        System.out.print("Durasi (dalam menit): ");
        int duration = Integer.parseInt(in.nextLine());

        if(duration<=0) throw new IllegalArgumentException();

        if (type.equalsIgnoreCase("Movie")) {
            System.out.print("Sutradara: ");
            String director = in.nextLine();
            System.out.print("Rating: ");
            double rating = Double.parseDouble(in.nextLine());

            if (rating > 5.0) throw new IllegalArgumentException();
            // inisiasi movie baru
            Movie newMovie = new Movie(title, duration, director, rating);
            // masukkan ke awal atau akhir list
            if (pos == 1) {
                movieList.insertVideo(newMovie, true);
            } else {
                movieList.insertVideo(newMovie, false);
            }
        } else if (type.equalsIgnoreCase("DDPTube")) {
            System.out.print("Creator: ");
            String creator = in.nextLine();
            // inisiasi video baru
            DDPTubeVideo newDDPTubeVideo = new DDPTubeVideo(title, duration, creator);
            // masukkan ke awal atau akhir list
            if (pos == 1) {
                ddpTubeVideoList.insertVideo(newDDPTubeVideo, true);
            } else {
                ddpTubeVideoList.insertVideo(newDDPTubeVideo, false);
            }
        } else {
            System.out.println("Tipe video tidak diketahui !!!");
        }
    }

    /**
     * Method untuk membuat Video Player memutar video selanjutnya sesuai dengan tipe video yang ingin
     * di-next
     */
    public static void nextVideo(){
        System.out.print("Putar (1) movie atau (2) DDPTube video selanjutnya? ");
        int type = Integer.parseInt(in.nextLine());

        switch (type) {
            case 1:
                //ambil film pertama dan masukkan ke akhir
                Movie nextMovie = movieList.getFirst();
                if (nextMovie != null) {
                    movieList.insertVideo(movieList.deleteVideo(), false);
                }
                break;
            case 2:
                //ambil video pertama dan masukkan ke akhir
                DDPTubeVideo nextDDPTubeVideo = ddpTubeVideoList.getFirst();
                if (nextDDPTubeVideo != null) {
                    ddpTubeVideoList.insertVideo(ddpTubeVideoList.deleteVideo(), false);
                }
                break;
        }

    }

    /**
     * Method untuk menghapus video yang ada di paling depan movieList atau ddpTubeVideoList
     */
    public static void deleteVideo(){
        System.out.println("---------------HAPUS VIDEO-----------------");

        System.out.print("Hapus (1) movie atau (2) DDPTube video? ");
        int type = Integer.parseInt(in.nextLine());

        switch (type) {
            case 1:
                //ambil film yang ingin diremove
                Movie deletedMovie = movieList.deleteVideo();
                if (deletedMovie != null) {
                    System.out.println(deletedMovie.getTitle() + " - " + deletedMovie.getDuration() + " dihapus!");
                    Video.decrementVideoAmount();
                } else {
                    // throw error tidak ada film
                    throw new NoSuchElementException();
                }
                break;
            case 2:
                //ambil video yang ingin diremove
                DDPTubeVideo deletedDDPTubeVideo = ddpTubeVideoList.deleteVideo();
                if (deletedDDPTubeVideo != null) {
                    System.out.println(deletedDDPTubeVideo.getTitle() + " - " + deletedDDPTubeVideo.getDuration() + " dihapus!");
                    Video.decrementVideoAmount();
                } else {
                    // throw error tidak ada video
                    throw new NoSuchElementException();
                }
                break;
        }
    }

    /**
     * Method untuk mencetak semua Video yang ada di tiap video list dimulai dari video yang
     * paling depan
     */
    public static void printVideoList(){
        System.out.println("---------------DAFTAR VIDEO----------------");
        int counter = 0;
        // print berdasarkan isi list
        System.out.println("Movie anda: ");
        for (Video video : movieList.getVideoList()){
            counter++;
            System.out.println(counter + ". " + video.getTitle() + " - " + video.getDuration());
        }
        if(counter<1){
            System.out.println("List movie anda kosong");
        }

        System.out.println();
        counter = 0;
        System.out.println("DDPTube Video anda: ");
        for (Video video : ddpTubeVideoList.getVideoList()){
            counter++;
            System.out.println(counter + ". " + video.getTitle() + " - " + video.getDuration());
        }
        if(counter<1){
            System.out.println("List DDPTube video anda kosong");
        }
    }

    /**
     * Method untuk mencetak menu dari Video Player beserta jumlah Video yang sudah terdaftar
     */
    public static void printMainMenu(){
        System.out.println("-------------------------------------------");
        System.out.println("Total jumlah video sekarang: " + Video.videoAmount);
        playMovie();
        playDdpTubeVideo();
        System.out.println("-------------------------------------------");
        System.out.print("""
                Silakan pilih menu:\s
                1. Tambah video
                2. Putar video selanjutnya
                3. Hapus video
                4. Lihat daftar video
                5. Keluar
                Pilihan:\s""");
    }

    /**
     * Method untuk memutar movie yang ada di paling depan movieList
     */
    public static void playMovie(){
        try{
            Video current = movieList.getFirst();
            // pengubahan sedikit agar tidak keprint null
            if (current == null){
                System.out.println("Movie sekarang: \nTidak ada");  
            }
            else{
                System.out.println("Movie sekarang: \n" + current);
            }
        }
        catch (Exception e){
            System.out.println("Movie sekarang: \nTidak ada");
        }
    }

    /**
     * Method untuk memutar DDPTube video yang ada di paling depan ddpTubeVideoList
     */
    public static void playDdpTubeVideo(){
        try{
            Video current = ddpTubeVideoList.getFirst();
            // pengubahan sedikit agar tidak keprint null
            if (current == null){
                System.out.println("DDPTube video sekarang: \nTidak ada");
            }
            else{
                System.out.println("DDPTube video sekarang: \n" + current);
            }
        }
        catch (Exception e){
            System.out.println("DDPTube video sekarang: \nTidak ada");
        }
    }
}
