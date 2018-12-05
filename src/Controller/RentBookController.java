package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Model.RentBookModel;
import View.MasterView;

public class RentBookController implements ActionListener {
	MasterView gui;

	public RentBookController(MasterView gui) { // create reference to GUI
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand().equals("RENT BOOK")) { 
				
				RentBookModel book = new RentBookModel(this.gui.getTextFieldBuchNameAusleihen(), this.gui.getTextFieldVornameAusleihender(), this.gui.getTextFieldNachnameAusleiher(), this.gui.getComboBoxSelectedItemArtAusleihender(), this.gui.getTextFieldPersonArtAusleiher());
				
				RentBookModel.rentBookInDB(book);
				 
				
				// set text fields to initial
				this.gui.setTextFieldBuchNameAusleihen(null); 
				this.gui.setTextFieldVornameAusleihender(null); 
				this.gui.setTextFieldNachnameAusleiher(null);
				this.gui.setTextFieldPersonArtAusleiher(null);
			} else { System.out.println("Unknown command"); 
			}
		}
		// if no enum constant for Studiengruppe is available
		catch (IllegalArgumentException ex) {
			this.gui.setTextFieldStudiengruppe(null);
			JOptionPane.showMessageDialog(new JFrame(), "IllegalArgumentException");
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "NullPointerException");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Exception");
		}
	}
}