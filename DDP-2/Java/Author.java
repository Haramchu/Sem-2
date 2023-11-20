public class Author{
	String nama;
	String email;
	Author(String nama, String email){
		this.nama = nama;
		this.email = email;
	}
	public String getName (){
		return this.nama;
	}
	public String getEmail (){
		return this.email;
	}
	public void setName(String nama){
		this.nama = nama;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String toString(){
		return ("Author Name = " + this.nama + " - Email = " + this.email);
	}
	public boolean equals(Author authorLain) {
		return this.getEmail().equals(authorLain.getEmail());
	}
}



