package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Database.DBConnection;

public class BookModel
{
	private String bookname;
	private int count;
	private String status;
	
 public BookModel(String bookname, String countVar, String status) throws Exception
 {
	 if(bookname.equals("") || countVar.equals("") || status.equals("")) { 
		 throw new IllegalArgumentException("Fehlende Parameter zum Buch anlegen."); 
		 } else {
	 
		 this.bookname = bookname;
		 this.status = status;
		 this.count = Integer.parseInt(countVar);
		 }
 } 
 

 
 //save Address & save Person muss vor Save Student aufgerufen werden
 public static void saveBookInDB(BookModel a) throws Exception {
	 	
	 	int count = a.getCount();
	 	
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		
		int check = 0;
		String art1 = "SELECT ID FROM studienarbeit.books WHERE name = '"+ a.getBookname() +"';";
		ResultSet rsa3 = stmt.executeQuery(art1);
		while(rsa3.next()) {
			check=rsa3.getInt(1);
		}
		
		if (check==0) {
			//buch anlegen
			String book = "INSERT INTO studienarbeit.books (name) values ('" + a.getBookname() +"');";
			stmt.executeUpdate(book);
		}
		
		
		while(count>0) {
		//exemplar anlegen
		String bookCp = "INSERT INTO studienarbeit.bookcopies (status, IDbook) VALUES ('"+a.getStatus()+"', (SELECT ID FROM studienarbeit.books WHERE name = '"+a.getBookname()+"'));";
		stmt.executeUpdate(bookCp);
		count--;
		}
		
					 
		conn.close();
	}
 
 

 
 public String getBookname() {
		return bookname;
	}

	public int getCount() {
		return count;
	}

	public String getStatus() {
		return status;
	}
	
	
	public String getDetails()
	 {
		return "\nBuchname:\n" + getBookname() + "\nAnzahl:\n" + getCount() + "\nStatus:\n" + getStatus();
	 }

} 







