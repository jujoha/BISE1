package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Database.DBConnection;

public class AddressModel {
	private String street;
	private String houseNr;
	private int zipCode;
	private String place;
	
	public AddressModel(String street, String houseNr, String zipCode, String place) throws IllegalArgumentException{
		if(street.equals("") || houseNr.equals("") || zipCode.equals("") || place.equals("")) 
			throw new IllegalArgumentException("Ungültige Parameter");
		//TODO: check if zipCode has 5 digits and is typ int
		if(zipCode.length()>5) 
			throw new IllegalArgumentException("Ungültige Parameter");
		
		this.street = street;
		this.houseNr = houseNr;
		this.zipCode = Integer.parseInt(zipCode);
		this.place = place;
	}
	
	public static void saveAddressInDB(AddressModel a) throws Exception {
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		
		//check if adress already exists
		int check = 0;
		String user = "SELECT ID FROM address WHERE street = '"+ a.street +"' AND houseNr = '"+ a.houseNr +"' AND zipCode = "+ a.zipCode +" AND place = '"+ a.place +"';";
		ResultSet rsa = stmt.executeQuery(user);
		while(rsa.next()) {
			check=rsa.getInt(1);
		}
		if(check == 0) {
			String sql = "INSERT INTO address (street, houseNr, place, zipCode) "
					+ "VALUES ('" + a.street + "', '" + a.houseNr + "', '" + a.place + "', '" + a.zipCode + "')";
			stmt.executeUpdate(sql);
		}

		conn.close();
	}
	
//	public static int getNumberOfAddressesInDB() throws Exception {
//		int number = 0;
//		
//		Connection conn = DBConnection.getConnection();
//		Statement stmt = conn.createStatement();
//		
//		String sql = "SELECT COUNT(ID) as number FROM address";
//		
//		ResultSet rs = stmt.executeQuery(sql);
//		while(rs.next()) {
//			number = rs.getInt("number");
//			System.out.println(number);
//		}
//		
//		conn.close();
//		
//		
//		return number;
//	}
	
	public boolean isSame(AddressModel x) {
		if(x.street.equals(this.street) && x.houseNr.equals(this.houseNr) && x.zipCode == this.zipCode && x.place.equals(this.place)) {
			return true;
		} else {
			return false;
		}
	}
}
