public class TestAuthor{
	public static void main(String[] args){
		Author tes1 = new Author("Tanmay Kumar Behera" , "tanmay.nitr@gmail.com");
		Author tes2 = new Author(" Behera Kumar" , "tanmay.nitr@gmail.com");
		System.out.println(tes1);
		System.out.println(tes2);
		System.out.println(tes1.equals(tes2));
	}
}

