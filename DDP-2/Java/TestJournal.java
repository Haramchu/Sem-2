public class TestJournal{
	public static void main(String[] args){
		String[] authors = {"author satu", "author dua", "author tiga"};
		Journal tes1 = new Journal("9786028519939", "Transformative learning", authors, "king saud", "doi.org/10.1016/j.scs.2020.102655");
		System.out.println(tes1);
	}
}