package Model;

import static javax.swing.JOptionPane.showMessageDialog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Database.DBConnection;

public class ProfessorModel
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

private Fakultaet gruppe;
 
 // nur zum vegleich für FK gespeichert
 String street, houseNr, zipCode, place;
 
 public ProfessorModel(String firstname, String lastname, Fakultaet sg, String street, String houseNr, String zipCode, String place) throws Exception
 {
	 if(firstname.equals("") || lastname.equals("") || sg.equals(null) || street.equals("") || houseNr.equals("") || zipCode.equals("") || place.equals("")) { 
		 throw new IllegalArgumentException("Fehlende Parameter Für Professor oder Adresse"); 
		 } else {}
	 
		 this.firstname = firstname;
		 this.lastname = lastname;
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
 
 public ProfessorModel(String firstname, String lastname, Fakultaet sg) throws Exception
 {
	 if(firstname.equals("") || lastname.equals("") || sg.equals(null)) { 
		 throw new IllegalArgumentException("Ungültige Parameter für Professor"); 
		 } else {}

		 this.firstname = firstname;
		 this.lastname = lastname;
		 this.gruppe = sg;
		 
 } 
 
 //save Address & save Person muss vor Save Professor aufgerufen werden
 public static void saveProfessorInDB(ProfessorModel a) throws Exception {
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
			
			//select FK person 4 professor
			int idPerson = 0;
			String persFK = "SELECT ID FROM person WHERE firstname = '" + a.firstname + "' AND lastname = '" + a.lastname + "';";
			ResultSet rs = stmt.executeQuery(persFK);
			 while(rs.next()) {
		        idPerson=rs.getInt(1);
		                }
			
			//create professor
			String sql = "INSERT INTO professor (firstname, lastname, fak, countBooks, IDaddress, IDperson) "
					+ "VALUES ('" + a.firstname + "', '" + a.lastname + "', '" + a.gruppe.toString() + "', " + 0 + ", " + idAdress + ", " + idPerson + " )";
			
			stmt.executeUpdate(sql);
			
		} else {
			//create Person
			String pers = "INSERT INTO person (firstname, lastname) VALUES ('" + a.firstname + "', '" + a.lastname + "');";
			stmt.executeUpdate(pers);
			
			//select FK person 4 professor 
			int idPerson = 0;
			String persFK = "SELECT ID FROM person WHERE firstname = '" + a.firstname + "' AND lastname = '" + a.lastname + "';";
			ResultSet rs = stmt.executeQuery(persFK);
			 while(rs.next()) {
		           idPerson=rs.getInt(1);
		                }
			 
			//create professor
			String sql = "INSERT INTO professor (firstname, lastname, fak, countBooks, IDaddress, IDperson) VALUES ('" + a.firstname + "', '" + a.lastname + "', '" + a.gruppe.toString() + "', " + 0 + ", " + 1 + ", " + idPerson + " );";
			
			stmt.executeUpdate(sql);
		}
		
		conn.close();
	}
 
 

 
 public String getDetails()
 {
	 if (adr.isSame(compare)) {
		 return "\nName:\n" + firstname +" "+ lastname + "\nFakultaet:\n" + gruppe;
	 } else if (adr != null) {
		 return "\nName:\n" + firstname +" "+ lastname + "\nAdresse:\n" + street +" "+ houseNr +" "+ zipCode +" "+ place + "\nFakultaet:\n" + gruppe.toString();
	 } else {
		 return "";
	 }
 }

} 







