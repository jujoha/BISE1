package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import View.MasterView;


public class SearchController implements ActionListener {
	MasterView gui;

	public SearchController(MasterView gui) { // create reference to GUI
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand().equals("SEARCH USER")) { // access to GUI elements using control.gui

					this.gui.displaySearchedUsers();
				
			} else if (e.getActionCommand().equals("SEARCH BOOK")) { // access to GUI elements using control.gui
				if(gui.getComboBoxSelectedItem() == "alle") {
					this.gui.displaySearchedBooksAll();
				} else {
					this.gui.displaySearchedBooksSelected();
				}
			} else { System.out.println("Unknown command"); 
			}
		}
		catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
		}
	}
	
	public static String getAllUsers() {
		return "SELECT firstname, lastname FROM studienarbeit.student UNION SELECT firstname, lastname FROM studienarbeit.professor;";
	}
	
	public static String getAllBooks() {
		return "SELECT count(bookcopies.ID) AS anzahl, books.name, bookcopies.status FROM studienarbeit.books, studienarbeit.bookcopies WHERE books.ID = bookcopies.IDbook GROUP BY books.name, bookcopies.status;";
	}
	
	public static String getSelectedBooks() {
		return "SELECT count(bookcopies.ID) AS anzahl, books.name, bookcopies.status FROM studienarbeit.books, studienarbeit.bookcopies WHERE books.ID = bookcopies.IDbook AND bookcopies.status = 'ausleihbar' GROUP BY books.name;";
	}
	
}