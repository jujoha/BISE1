package Model;

import static javax.swing.JOptionPane.showMessageDialog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Database.DBConnection;

public class StudentModel
{
 private String firstname;
 private String lastname;
 private AddressModel adr = new AddressModel("empty", "empty", "11", "empty");
 //nur um nullpointer zu verhindern
 private AddressModel compare = new AddressModel("empty", "empty", "11", "empty");
 
 public AddressModel getAdr() {
	return adr;
 }
 
 public AddressModel getAdrC() {
		return compare;
	 }

private Studiengruppe gruppe;
private int mariekelNR = 0;
 
 // nur zum vegleich für FK gespeichert
 String street, houseNr, zipCode, place;
 
 public StudentModel(String firstname, String lastname, int mariekelNR, Studiengruppe sg, String street, String houseNr, String zipCode, String place) throws Exception
 {
	 if(firstname.equals("") || lastname.equals("") || mariekelNR == 0 || sg.equals(null) || street.equals("") || houseNr.equals("") || zipCode.equals("") || place.equals("")) { 
		 throw new IllegalArgumentException("Fehlende Parameter Für Student oder Adresse"); 
		 } else {}
	 
		 this.firstname = firstname;
		 this.lastname = lastname;
		 this.mariekelNR = mariekelNR;
		 this.gruppe = sg;
		 
		 // nur zum vegleich für FK gespeichert
		 this.street = street;
		 this.houseNr = houseNr;
		 this.zipCode = zipCode;
		 this.place = place;
		 
		 adr = new AddressModel(street, houseNr, zipCode, place);
		 try {
			AddressModel.saveAddressInDB(adr);
		} catch (Exception e) {
			showMessageDialog(null, e.getMessage());
		} 
 } 
 
 public StudentModel(String firstname, String lastname, int mariekelNR, Studiengruppe sg) throws Exception
 {
	 if(firstname.equals("") || lastname.equals("") || mariekelNR == 0 || sg.equals(null)) { 
		 throw new IllegalArgumentException("Ungültige Parameter für Student"); 
		 } else {}

		 this.firstname = firstname;
		 this.lastname = lastname;
		 this.mariekelNR = mariekelNR;
		 this.gruppe = sg;
		 
 } 
 
 //save Address & save Person muss vor Save Student aufgerufen werden
 public static void saveStudentInDB(StudentModel a) throws Exception {
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		
		if(!a.getAdr().isSame(a.getAdrC())) {
			//select die bereits angelegte adresse um den fk zu verknüpfen
			int idAdress = 0;
			String adrFK = "SELECT ID FROM studienarbeit.address WHERE street = '"+ a.street +"' AND houseNr = '"+ a.houseNr +"' AND place = '"+ a.place +"' AND zipCode =  '"+ a.zipCode +"';";
			ResultSet rsa = stmt.executeQuery(adrFK);
			while(rsa.next()) {
				idAdress=rsa.getInt(1);
		                }
			
			//create Person
			String pers = "INSERT INTO person (firstname, lastname) VALUES ('" + a.firstname + "', '" + a.lastname + "');";
			stmt.executeUpdate(pers);
			
			//select FK person 4 student 
			int idPerson = 0;
			String persFK = "SELECT ID FROM person WHERE firstname = '" + a.firstname + "' AND lastname = '" + a.lastname + "';";
			ResultSet rs = stmt.executeQuery(persFK);
			 while(rs.next()) {
		        idPerson=rs.getInt(1);
		                }
			
			//create student
			String sql = "INSERT INTO student (firstname, lastname, stuGroup, matNumber, countBooks, IDaddress2, IDperson2) "
					+ "VALUES ('" + a.firstname + "', '" + a.lastname + "', '" + a.gruppe.toString() + "', " + a.mariekelNR + ", " + 0 + ", " + idAdress + ", " + idPerson + " )";
			
			stmt.executeUpdate(sql);
			
		} else {
			//create Person
			String pers = "INSERT INTO person (firstname, lastname) VALUES ('" + a.firstname + "', '" + a.lastname + "');";
			stmt.executeUpdate(pers);
			
			//select FK person 4 student 
			int idPerson = 0;
			String persFK = "SELECT ID FROM person WHERE firstname = '" + a.firstname + "' AND lastname = '" + a.lastname + "';";
			ResultSet rs = stmt.executeQuery(persFK);
			 while(rs.next()) {
		           idPerson=rs.getInt(1);
		                }
			 
			//create student
			String sql = "INSERT INTO student (firstname, lastname, stuGroup, matNumber, countBooks, IDaddress2, IDperson2) VALUES ('" + a.firstname + "', '" + a.lastname + "', '" + a.gruppe.toString() + "', " + a.mariekelNR + ", " + 0 + ", " + 1 + ", " + idPerson + " );";
			
			stmt.executeUpdate(sql);
		}
		
		conn.close();
	}
 
 

 
 public String getDetails()
 {
	 if (adr.isSame(compare)) {
		 return "\nName:\n" + firstname +" "+ lastname + "\nStudiengruppe:\n" + gruppe;
	 } else if (adr != null) {
		 return "\nName:\n" + firstname +" "+ lastname + "\nAdresse:\n" + street +" "+ houseNr +" "+ zipCode +" "+ place + "\nStudiengruppe:\n" + gruppe.toString();
	 } else {
		 return "";
	 }
 }

} 







