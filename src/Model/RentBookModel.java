package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Database.DBConnection;

public class RentBookModel
{
	private String bookname;
	private String vorname;
	private String nachname;
	private String artAusleiher;
	private String artAusleiherInduviduel;
	
 public RentBookModel(String bookname, String vorname, String nachname, String artAusleiher, String artAusleiherInduviduel) throws Exception
 {
	 if(bookname.equals("") || vorname.equals("") || nachname.equals("") || artAusleiher.equals("") || artAusleiherInduviduel.equals("")) { 
		 throw new IllegalArgumentException("Fehlende Parameter zum Buch anlegen."); 
		 } else {
	 
		 this.bookname = bookname;
		 this.vorname = vorname;
		 this.nachname = nachname;
		 this.artAusleiher = artAusleiher;
		 this.artAusleiherInduviduel = artAusleiherInduviduel;

		 }
 } 
 
 public static void rentBookInDB(RentBookModel a) throws Exception {
	 	
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
					
		//prüfe verfügbarkeit des buches
		int check = 0;
		String book = "SELECT bookcopies.ID AS anzahl FROM studienarbeit.books, studienarbeit.bookcopies WHERE books.ID = bookcopies.IDbook AND bookcopies.status = 'ausleihbar' AND books.name = '"+ a.getBookname() +"' GROUP BY books.name;";
		ResultSet rsa = stmt.executeQuery(book);
		while(rsa.next()) {
			check=rsa.getInt(1);
	    }
		if(check == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Das Buch ist zur Zeit nicht Verfügbar.");
		} else {
			int check1 = 0;
			String user = "SELECT ID FROM person WHERE firstname = '"+ a.getVorname() +"' AND lastname = '"+ a.getNachname() +"';";
			ResultSet rsa1 = stmt.executeQuery(user);
			while(rsa1.next()) {
				check1=rsa1.getInt(1);
			}
			if(check1 == 0) {
				JOptionPane.showMessageDialog(new JFrame(), "User nicht regestriert. Bitte erst die regestrierung abschließen.");
			} else {
				if(a.getArtAusleiher().equals("Matrikelnummer")) {
					int check2 = 0;
					String art = "SELECT person.ID FROM studienarbeit.person, studienarbeit.student WHERE student.matNumber = '"+ a.getStudentAusleiher() +"' AND person.firstname = '"+ a.getVorname() +"' AND person.lastname = '"+ a.getNachname() +"' AND student.firstname = '"+ a.getVorname() +"' AND student.lastname = '"+ a.getNachname() +"';";
					ResultSet rsa2 = stmt.executeQuery(art);
					while(rsa2.next()) {
						check2=rsa2.getInt(1);
					}
					if(check2 == 0) {
						JOptionPane.showMessageDialog(new JFrame(), "User ist kein Student.");
					} else {
						int booksCount = 0;
					    String count = "SELECT countBooks FROM student WHERE firstname = '"+ a.getVorname() +"' AND lastname = '"+ a.getNachname() +"';";
					    try {
					        ResultSet resultSet = stmt.executeQuery(count);
					        resultSet.next();
					        booksCount = resultSet.getInt(1);
					    } catch (Exception e) {}
					    if(booksCount<2) {
					    	booksCount++;
							String updateCountBooks = "UPDATE studienarbeit.student SET countBooks = "+ booksCount +" Where firstname = '"+ a.getVorname() +"' AND lastname = '"+ a.getNachname() +"';";
							stmt.executeUpdate(updateCountBooks);
							
							//buch wird ausgeliehen
							a.rentBookDetails(check1, check);
							JOptionPane.showMessageDialog(new JFrame(),
									"Folgendes Buch ist ausgeliehen worden:\n" + a.getDetails());
					    	
					    } else {
					    	JOptionPane.showMessageDialog(new JFrame(), "Bereits zu viele Bücher ausgeliehen.");
					    }

					}
				} else {
					int check3 = 0;
					String art1 = "SELECT person.ID FROM studienarbeit.person, studienarbeit.professor WHERE professor.fak = '"+ a.getProfessorAusleiher() +"' AND person.firstname = '"+ a.getVorname() +"' AND person.lastname = '"+ a.getNachname() +"' AND professor.firstname = '"+ a.getVorname() +"' AND professor.lastname = '"+ a.getNachname() +"';";
					ResultSet rsa3 = stmt.executeQuery(art1);
					while(rsa3.next()) {
						check3=rsa3.getInt(1);
					}
					if(check3 == 0) {
						JOptionPane.showMessageDialog(new JFrame(), "User ist kein Professor.");
					} else {
						int booksCount = 0;
					    String count = "SELECT countBooks FROM professor WHERE firstname = '"+ a.getVorname() +"' AND lastname = '"+ a.getNachname() +"';";
					    try {
					        ResultSet resultSet = stmt.executeQuery(count);
					        resultSet.next();
					        booksCount = resultSet.getInt(1);
					    } catch (Exception e) {}
					    if(booksCount<2) {
					    	booksCount++;
					    	System.out.println("hier gehts noch");
							String updateCountBooks = "UPDATE studienarbeit.professor SET countBooks = "+ booksCount +" Where firstname = '"+ a.getVorname() +"' AND lastname = '"+ a.getNachname() +"';";
							stmt.executeUpdate(updateCountBooks);
							
							//buch wird ausgeliehen
							a.rentBookDetails(check1, check);
							JOptionPane.showMessageDialog(new JFrame(),
									"Folgendes Buch ist ausgeliehen worden:\n" + a.getDetails());
					    	
					    } else {
					    	JOptionPane.showMessageDialog(new JFrame(), "Bereits zu viele Bücher ausgeliehen.");
					    }
					}
				}
			}
		}
					 
		conn.close();
	}
 
 

 
 	public String getBookname() {
		return bookname;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}
	
	public String getArtAusleiher() {
		return artAusleiher;
	}
	
	public String getProfessorAusleiher() {
		return artAusleiherInduviduel;
	}
	
	public int getStudentAusleiher() {
		return Integer.parseInt(artAusleiherInduviduel);
	}
	
	public void rentBookDetails(int idPerson, int idBookcopie) throws Exception {
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		
		String borrower = "INSERT INTO borrower (IDperson, IDbookcopies) VALUES (" + idPerson + ", " + idBookcopie + ");";
		stmt.executeUpdate(borrower);
		
		String updateCopie = "UPDATE bookcopies SET status = 'ausgeliehen' WHERE ID = " + idBookcopie + ";";
		stmt.executeUpdate(updateCopie);
		
		conn.close();
	}
	
	
	public String getDetails()
	 {
		return "\nBuchname:\n" + getBookname() + "\nWurde für:\n" + getVorname()+ " " + getNachname() + "\nausgeliehen";
	 }

} 







