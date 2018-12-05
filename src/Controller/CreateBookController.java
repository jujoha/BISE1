package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Model.BookModel;
import View.MasterView;

public class CreateBookController implements ActionListener {
	MasterView gui;

	public CreateBookController(MasterView gui) { // create reference to GUI
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand().equals("CREATE BOOK")) { 
				
				BookModel book = new BookModel(this.gui.getTextFieldBuchname(), this.gui.getTextFieldAnzahlExemplare(), this.gui.getComboBoxSelectedItemRentBook());
				
				BookModel.saveBookInDB(book);
				 
				
				JOptionPane.showMessageDialog(new JFrame(),
						"Folgendes Buch ist angelegt worden:\n" + book.getDetails());
				// set text fields to initial
				this.gui.setTextFieldBuchname(null);
				this.gui.setTextFieldAnzahlExemplare(null);
			} else { System.out.println("Unknown command"); 
			}
		}
		// if no enum constant for Studiengruppe is available
		catch (IllegalArgumentException ex) {
			this.gui.setTextFieldStudiengruppe(null);
			JOptionPane.showMessageDialog(new JFrame(), "Bitte geben Sie einen gültigen Wert für die Anzahl der Bücher an");
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "NullPointerException");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Exception");
		}
	}
}