package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.StudentModel;
import Model.Studiengruppe;
import View.MasterView;

public class StudentController implements ActionListener {
	MasterView gui;

	public StudentController(MasterView gui) { // create reference to GUI
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand().equals("CREATE STUDENT")) { 
				String vorname = this.gui.getTextFieldVornameStudent();
				String nachname = this.gui.getTextFieldNachnameStudent();
				int matrikelNR = Integer.valueOf(this.gui.getTextFieldMarNR());
				String gruppe = this.gui.getTextFieldStudiengruppe();
				
				StudentModel student;
				
				if(gui.getAdresseVorhandenStudent()) {
					String straﬂeStudent = this.gui.getTextfieldStraﬂeStudent();
					String hausnummerStudent = this.gui.getTextFieldHausnummerStudent();
					String ortStudent = this.gui.getTextFieldOrtStudent();
					String plzStudent = this.gui.getTextFieldPLZStudent();
					
					student = new StudentModel(vorname, nachname, matrikelNR, Studiengruppe.valueOf(gruppe), straﬂeStudent, hausnummerStudent, plzStudent, ortStudent);
				} else {
					
					student = new StudentModel(vorname, nachname, matrikelNR, Studiengruppe.valueOf(gruppe));
					
				}
				
				StudentModel.saveStudentInDB(student);
				
				JOptionPane.showMessageDialog(new JFrame(),
						"Folgender Student ist zu Studiengruppe zugeordnet worden:\n" + student.getDetails());
				// set text fields to initial
				this.gui.setTextFieldVornameStudent(null);
				this.gui.setTextFieldNachnameStudent(null);
				this.gui.setTextFieldMarNR(null);
				this.gui.setTextFieldStudiengruppe(null);
				this.gui.setTextfieldStraﬂeStudent(null);
				this.gui.setTextFieldHausnummerStudent(null);
				this.gui.setTextFieldOrtStudent(null);
				this.gui.setTextFieldPLZStudent(null);
			} else { System.out.println("Unknown command"); 
			}
		}
		// if no enum constant for Studiengruppe is available
		catch (IllegalArgumentException ex) {
			this.gui.setTextFieldStudiengruppe(null);
			JOptionPane.showMessageDialog(new JFrame(), "Stellen Sie sicher das die Studiengruppe richtig sowie die Martrikelnummer eine Zahl ist. \\n Des weiteren darf die PLZ maximal aus 5 Zahlen bestehen. \n Auﬂerdem sollen Sie die 'Adresse vorhanden' Checkbox nur aktivieren wenn sie alle ANgaben bef¸llen kˆnnen.");
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "NullPointerException");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Exception");
		}
	}
}