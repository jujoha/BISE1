package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Fakultaet;
import Model.ProfessorModel;
import View.MasterView;

public class ProfessorController implements ActionListener {
	MasterView gui;

	public ProfessorController(MasterView gui) { 
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand().equals("CREATE PROFESSOR")) {  
				String vorname = this.gui.getTextFieldVornameProf();
				String nachname = this.gui.getTextFieldNachnameProf();
				String fakultaet = this.gui.getTextFieldFakProf();
				
				ProfessorModel professor;
				
				if(gui.getAdresseVorhandenProfessor()) {
					String stra�eProfessor = this.gui.getTexFieldSra�eProf();
					String hausnummerProfessor = this.gui.getTextFieldHNrProf();
					String ortProfessor = this.gui.getTextFieldOrtProf();
					String plzProfessor = this.gui.getTextFieldPLZProf();
					
					professor = new ProfessorModel(vorname, nachname, Fakultaet.valueOf(fakultaet), stra�eProfessor, hausnummerProfessor, plzProfessor, ortProfessor);
				} else {
					
					professor = new ProfessorModel(vorname, nachname, Fakultaet.valueOf(fakultaet));
					
				}

				ProfessorModel.saveProfessorInDB(professor);

				JOptionPane.showMessageDialog(new JFrame(),
						"Folgender Professor ist zu Fakultaet zugeordnet worden:\n" + professor.getDetails());
				// set text fields to initial
				this.gui.setTextFieldVornameProf(null);
				this.gui.setTextFieldNachnameProf(null);
				this.gui.setTextFieldFakProf(null);
				this.gui.setTexFieldSra�eProf(null);
				this.gui.setTextFieldHNrProf(null);
				this.gui.setTextFieldOrtProf(null);
				this.gui.setTextFieldPLZProf(null);
			} else { System.out.println("Unknown command"); 
			}
		}
		// if no enum constant for Fakultaet is available
		catch (IllegalArgumentException ex) {
			this.gui.setTextFieldFakProf(null);
			JOptionPane.showMessageDialog(new JFrame(), "Stellen Sie sicher das die Fakult�t richtig ist. \n Des weiteren darf die PLZ maximal aus 5 Zahlen bestehen. \n Au�erdem sollen Sie die 'Adresse vorhanden' Checkbox nur aktivieren wenn sie alle ANgaben bef�llen k�nnen.");
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "NullPointerException");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Exception");
		}
	}
}