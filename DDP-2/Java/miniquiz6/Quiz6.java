public class Quiz6 {
    class Hewan {
    private String nama;
    private String warna;
    private Integer berat;
    private Integer jump;
    private Integer attack;
    public Hewan(String nama, String warna, Integer berat, Integer jump, Integer attack) {
        this.nama = nama;
        this.warna = warna;
        this.berat = berat;
        this.jump = jump;
        this.attack = attack;
    }
    public void jump(){
        System.out.println(this.nama + "is jumping" + this.jump);
    }
    public void attack(){
        System.out.println(this.nama + "has an attack " + this.attack);
    }
    public String getnama(){
        return this.nama;
    }
    public class Burung extends Hewan{
        private Integer fly;
        private Integer walk;
        public Burung(String nama, String warna, Integer berat, Integer jump, Integer attack, Integer fly, Integer walk) {
            super(nama, warna, berat, jump, attack);
            this.fly = fly;
            this.walk = walk;
        }
        public void fly(){
            System.out.println(this.getnama() + "is flying" + this.fly + " minutes");
        }
        public void walk(){
            System.out.println(this.getnama() + "is walking" + this.walk + " minutes");
    }
    
    public class Harimau extends Hewan{
        private Integer walk;
        public Harimau(String nama, String warna, Integer berat, Integer jump, Integer attack, Integer walk) {
            super(nama, warna, berat, jump, attack);
            this.walk = walk;
        }
        public void walk(){
            System.out.println(this.getnama() + "is walking" + this.walk + " minutes");
        }
    }
    }
    public class Kelinci extends Hewan{
        public Kelinci(String nama, String warna, Integer berat, Integer jump, Integer attack) {
            super(nama, warna, berat, jump, attack);
        }
    }
}
}
