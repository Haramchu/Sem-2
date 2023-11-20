import java.lang.reflect.Array;
import java.util.Arrays;

public class Journal{
	String isbn;
	String title;
	String journalName;
	String doi;
	String[] authors;
	public Journal(){
		this.isbn = " ";
		this.title = " ";
		this.journalName = " ";
		this.doi = " ";
	}
	public Journal(String isbn, String title){
		this.isbn = isbn;
		this.title = title;
		this.journalName = " ";
		this.doi = " ";
	}
	public Journal(String isbn, String title, String journalName){
		this.isbn = isbn;
		this.title = title;
		this.journalName = journalName;
		this.doi = " ";
	}
	public Journal(String isbn, String title, String[] authors, String journalName, String doi){
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
		this.journalName = journalName;
		this.doi = doi;
	}
	public String getTitle (){
		return this.title;
	}
	public String getIsbn(){
		return this.isbn;
	}
	public String getDoi(){
		return this.doi;
	}
	public String getJournalName(){
		return this.journalName;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setIsbn(String isbn){
		this.isbn = isbn;
	}
	public void setJournalName(String journalName){
		this.journalName = journalName;
	}
	public void setDoi(String doi){
		this.doi = doi;
	}
	public void setAuthors(String[] authors){
		this.authors = authors;
	}
	public String getAuthors(){
		String author = String.valueOf(Arrays.toString(this.authors));
		return author;
	}
	public String toString(){
		return ("Book " + this.isbn + " - " + this.title + " - " + this.journalName + " - " + this.doi + "\nAuthors: \n" + this.getAuthors());
	}
	public boolean equalEmail(String authorLain) {
		return this.doi.equals(authorLain.doi);
	}

	}